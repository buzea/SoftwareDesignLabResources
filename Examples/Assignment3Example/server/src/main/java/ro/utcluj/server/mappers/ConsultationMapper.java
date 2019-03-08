package ro.utcluj.server.mappers;

import ro.utcluj.server.model.Consultation;
import ro.utcluj.server.model.Doctor;
import ro.utcluj.server.model.Patient;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConsultationMapper extends Mapper {
	private static ConsultationMapper         instance;
	private        DoctorMapper               doctorMapper;
	private        PatientMapper              patientMapper;
	private        Map<Integer, Consultation> buffer;

	private ConsultationMapper() throws DataSourceException {

		doctorMapper = DoctorMapper.getInstance();
		patientMapper = PatientMapper.getInstance();
		buffer = new HashMap<>();
	}

	public static ConsultationMapper getInstance() throws DataSourceException {
		if (instance == null) {
			instance = new ConsultationMapper();
		}
		return instance;
	}

	public void create(Consultation consult) throws DataSourceException {

		String sql = "INSERT INTO `hospital`.`consultation` (`pnc`, `idDoctor`, `time`, `observations`, `diagnosis`, `prescription`) VALUES (?, ?, ?, ?, ?, ?);";

		try {
			if (!isFree(consult)) {
				throw new DataSourceException("Time not available!");
			}
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, consult.getPatient().getPnc());
			statement.setInt(2, consult.getDoctor().getIdDoctor());
			// java.sql.Date sqlDate = new
			// java.sql.Date(consult.getTime().getTime());
			statement.setTimestamp(3, consult.getTime());
			statement.setString(4, consult.getObservations());
			statement.setString(5, consult.getDiagnosis());
			statement.setString(6, consult.getPrescription());

			statement.executeUpdate();
			// Can't update buffer due to missing ID. Will update on read.

		} catch (SQLException e) {
			throw new DataSourceException(e);
		}
	}

	public void update(Consultation consult) throws DataSourceException {
		String sql = "UPDATE `hospital`.`consultation` SET" + " `pnc`=?, `idDoctor`=?, `time`=?, `observations`=?, `diagnosis`=?, `prescription`=?" + " WHERE `idconsultation`=?;";

		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, consult.getPatient().getPnc());
			statement.setInt(2, consult.getDoctor().getIdDoctor());
			// java.sql.Date sqlDate = new
			// java.sql.Date(consult.getTime().getTime());
			statement.setTimestamp(3, consult.getTime());
			statement.setString(4, consult.getObservations());
			statement.setString(5, consult.getDiagnosis());
			statement.setString(6, consult.getPrescription());
			statement.setInt(7, consult.getIdConsultation());

			statement.executeUpdate();
			// Modified Object should be also modified in the buffer
			// (theoretically!!!)
			buffer.put(consult.getIdConsultation(), consult);
		} catch (SQLException e) {

			throw new DataSourceException(e);
		}
	}

	public void delete(int id) throws DataSourceException {
		String sql = "DELETE FROM `hospital`.`consultation` WHERE `idconsultation`=?;";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			statement.executeUpdate();
			buffer.remove(id);
		} catch (SQLException e) {
			throw new DataSourceException(e);
		}
	}

	public List<Consultation> getAll() throws DataSourceException {
		List<Consultation> consultations = new ArrayList<>();
		String sql = "Select * from consultation";
		try {
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int idConsult = rs.getInt("idconsultation");
				if (buffer.containsKey(idConsult)) {
					consultations.add(buffer.get(idConsult));
				} else {
					Consultation consultation = new Consultation();
					consultation.setDiagnosis(rs.getString("diagnosis"));
					consultation.setDoctor(doctorMapper.getDoctor(rs.getInt("idDoctor")));
					consultation.setIdConsultation(idConsult);
					consultation.setObservations(rs.getString("observations"));
					consultation.setPatient(patientMapper.getPatient(rs.getInt("pnc")));
					consultation.setPrescription(rs.getString("prescription"));
					consultation.setTime(rs.getTimestamp("time"));
					buffer.put(idConsult, consultation);
					consultations.add(consultation);
				}
			}
		} catch (SQLException e) {
			throw new DataSourceException(e);
		}

		return consultations;
	}

	public List<Consultation> getAll(Doctor doctor) throws DataSourceException {
		List<Consultation> consultations = new ArrayList<>();
		String sql = "Select * from consultation where idDoctor=" + doctor.getIdDoctor();
		try {
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int idConsult = rs.getInt("idconsultation");
				if (buffer.containsKey(idConsult)) {
					consultations.add(buffer.get(idConsult));
				} else {
					Consultation consultation = new Consultation();
					consultation.setDiagnosis(rs.getString("diagnosis"));
					consultation.setDoctor(doctor);
					consultation.setIdConsultation(idConsult);
					consultation.setObservations(rs.getString("observations"));
					consultation.setPatient(patientMapper.getPatient(rs.getInt("pnc")));
					consultation.setPrescription(rs.getString("prescription"));
					consultation.setTime(rs.getTimestamp("time"));
					buffer.put(idConsult, consultation);
					consultations.add(consultation);
				}
			}
		} catch (SQLException e) {
			throw new DataSourceException(e);
		}

		return consultations;
	}

	public List<Consultation> getAll(Patient patient) throws DataSourceException {
		List<Consultation> consultations = new ArrayList<>();
		String sql = "Select * from consultation where pnc=" + patient.getPnc();
		try {
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int idConsult = rs.getInt("idconsultation");
				if (buffer.containsKey(idConsult)) {
					consultations.add(buffer.get(idConsult));
				} else {
					Consultation consultation = new Consultation();
					consultation.setDiagnosis(rs.getString("diagnosis"));
					consultation.setDoctor(doctorMapper.getDoctor(rs.getInt("idDoctor")));
					consultation.setIdConsultation(idConsult);
					consultation.setObservations(rs.getString("observations"));
					consultation.setPatient(patient);
					consultation.setPrescription(rs.getString("prescription"));
					consultation.setTime(rs.getTimestamp("time"));
					buffer.put(idConsult, consultation);
					consultations.add(consultation);
				}
			}
		} catch (SQLException e) {

			throw new DataSourceException(e);
		}

		return consultations;
	}

	public Consultation getLastConsultation(Doctor doctor) throws DataSourceException {
		Consultation consultation = null;
		String sql = "SELECT * FROM hospital.consultation where idDoctor=? and time<=NOW() order by time desc LIMIT 1;";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, doctor.getIdDoctor());
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				int idConsult = rs.getInt("idconsultation");
				if (buffer.containsKey(idConsult)) {
					return (buffer.get(idConsult));
				} else {
					consultation = new Consultation();
					consultation.setDiagnosis(rs.getString("diagnosis"));
					consultation.setDoctor(doctor);
					consultation.setIdConsultation(idConsult);
					consultation.setObservations(rs.getString("observations"));
					consultation.setPatient(patientMapper.getPatient(rs.getInt("pnc")));
					consultation.setPrescription(rs.getString("prescription"));
					consultation.setTime(rs.getTimestamp("time"));
					buffer.put(idConsult, consultation);
				}
			}
		} catch (SQLException e) {
			throw new DataSourceException(e);
		}

		return consultation;
	}

	public Consultation getConsultation(int id) throws DataSourceException {
		Consultation consultation = null;
		String sql = "SELECT * FROM hospital.consultation where idconsultation=? ";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				int idConsult = rs.getInt("idconsultation");
				if (buffer.containsKey(idConsult)) {
					return buffer.get(idConsult);
				} else {
					consultation = new Consultation();
					consultation.setDiagnosis(rs.getString("diagnosis"));
					consultation.setDoctor(doctorMapper.getDoctor(rs.getInt("idDoctor")));
					consultation.setIdConsultation(idConsult);
					consultation.setObservations(rs.getString("observations"));
					consultation.setPatient(patientMapper.getPatient(rs.getInt("pnc")));
					consultation.setPrescription(rs.getString("prescription"));
					consultation.setTime(rs.getTimestamp("time"));
					buffer.put(idConsult, consultation);
				}
			}
		} catch (SQLException e) {

			throw new DataSourceException(e);
		}

		return consultation;
	}

	public boolean isFree(Consultation consult) throws SQLException {
		String sql = "select * from consultation where idDoctor = ? and consultation.time between ? and ? ";

		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, consult.getDoctor().getIdDoctor());
		Timestamp start = subtract15Min(consult.getTime());
		preparedStatement.setTimestamp(2, start);
		Timestamp end = add15Min(consult.getTime());
		preparedStatement.setTimestamp(3, end);
		ResultSet rs = preparedStatement.executeQuery();
		return !rs.next();
	}

	private Timestamp add15Min(Timestamp stamp) {
		long current = stamp.getTime();
		long subtracted = current + 15 * 60 * 1000;
		return new Timestamp(subtracted);
	}

	private Timestamp subtract15Min(Timestamp stamp) {
		long current = stamp.getTime();
		long subtracted = current - 15 * 60 * 1000;
		return new Timestamp(subtracted);
	}
}
