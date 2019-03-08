package ro.utcluj.server.commands;

import ro.utcluj.server.model.Consultation;
import ro.utcluj.server.model.Doctor;
import ro.utcluj.server.mappers.ConsultationMapper;
import ro.utcluj.server.mappers.DataSourceException;
import ro.utcluj.server.mappers.DoctorMapper;

import java.util.List;

public class ViewConsultsCommand implements Command {
	private final String username;
	private final String type;

	public ViewConsultsCommand(String string, String username) {
		type = string;
		this.username = username;
	}

	@Override
	public Object execute() {
		Object[][] matrix = null;
		try {
			if (type.equalsIgnoreCase("doctor")) {
				Doctor doc = DoctorMapper.getInstance().getDoctor(username);
				List<Consultation> consults = ConsultationMapper.getInstance().getAll(doc);
				matrix = new Object[consults.size()][7];
				int index = 0;
				for (Consultation c : consults) {
					matrix[index][0] = c.getIdConsultation();
					matrix[index][1] = c.getPatient().getName();
					matrix[index][2] = c.getDoctor().getName();
					matrix[index][3] = c.getTime();
					matrix[index][4] = c.getObservations();
					matrix[index][5] = c.getDiagnosis();
					matrix[index][6] = c.getPrescription();
					index++;
				}
			} else {
				if (type.equalsIgnoreCase("secretary")) {
					List<Consultation> consults = ConsultationMapper.getInstance().getAll();
					matrix = new Object[consults.size()][7];
					int index = 0;
					for (Consultation c : consults) {
						matrix[index][0] = c.getIdConsultation();
						matrix[index][2] = c.getPatient().getName();
						matrix[index][1] = c.getDoctor().getName();
						matrix[index][3] = c.getTime();
						matrix[index][4] = c.getObservations();
						matrix[index][5] = c.getDiagnosis();
						matrix[index][6] = c.getPrescription();
						index++;
					}
				}
			}
		} catch (DataSourceException e) {
			e.printStackTrace();
		}

		return matrix;
	}
}
