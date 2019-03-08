package ro.utcluj.server.commands;

import ro.utcluj.server.mappers.ConsultationMapper;
import ro.utcluj.server.mappers.DataSourceException;
import ro.utcluj.server.mappers.DoctorMapper;
import ro.utcluj.server.mappers.PatientMapper;
import ro.utcluj.server.model.Consultation;
import ro.utcluj.server.model.Doctor;
import ro.utcluj.server.model.Patient;

import java.sql.Timestamp;

public class CreateConsultationCommand implements Command {
	private final String pnc;
	private final String idDoctor;
	private final String time;

	public CreateConsultationCommand(String pnc, String idDoctor, String time) {
		this.pnc = pnc;
		this.idDoctor = idDoctor;
		this.time = time;
	}

	@Override
	public Object execute() {
		try {
			Patient patient = PatientMapper.getInstance().getPatient(Integer.parseInt(pnc));
			Doctor doctor = DoctorMapper.getInstance().getDoctor(Integer.parseInt(idDoctor));
			Timestamp date = Timestamp.valueOf(time);
			Consultation consult = new Consultation();
			consult.setDoctor(doctor);
			consult.setPatient(patient);
			consult.setTime(date);
			ConsultationMapper.getInstance().create(consult);

			return "ConsultCreated";
		} catch (NumberFormatException | DataSourceException e) {
			e.printStackTrace();
		}

		return "ConsultNotCreated";
	}
}
