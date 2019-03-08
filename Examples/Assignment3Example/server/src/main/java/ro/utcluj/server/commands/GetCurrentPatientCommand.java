package ro.utcluj.server.commands;

import ro.utcluj.server.mappers.ConsultationMapper;
import ro.utcluj.server.mappers.DataSourceException;
import ro.utcluj.server.mappers.DoctorMapper;
import ro.utcluj.server.model.Doctor;
import ro.utcluj.server.model.Patient;

public class GetCurrentPatientCommand implements Command {

	private final String username;

	public GetCurrentPatientCommand(String string) {
		username = string;
	}

	@Override
	public Object execute() {
		try {
			Doctor doctor = DoctorMapper.getInstance().getDoctor(username);
			Patient patient = ConsultationMapper.getInstance().getLastConsultation(doctor).getPatient();

			patient.setConsultations(ConsultationMapper.getInstance().getAll(patient));

			return patient;
		} catch (DataSourceException e) {
			e.printStackTrace();
		}
		return null;
	}
}
