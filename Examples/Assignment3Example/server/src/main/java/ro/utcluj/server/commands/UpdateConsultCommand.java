package ro.utcluj.server.commands;

import ro.utcluj.server.model.Consultation;
import ro.utcluj.server.mappers.ConsultationMapper;
import ro.utcluj.server.mappers.DataSourceException;

public class UpdateConsultCommand implements Command {
	private final String id;
	private final String prescription;
	private final String diagnosis;
	private final String observations;

	public UpdateConsultCommand(String id, String prescription, String diagnosis, String observations) {
		this.id = id;
		this.prescription = prescription;
		this.diagnosis = diagnosis;
		this.observations = observations;
	}

	@Override
	public Object execute() {

		try {
			int idInt = Integer.parseInt(id);
			Consultation consultation = ConsultationMapper.getInstance().getConsultation(idInt);
			consultation.setDiagnosis(diagnosis);
			consultation.setObservations(observations);
			consultation.setPrescription(prescription);
			ConsultationMapper.getInstance().update(consultation);
		} catch (DataSourceException e) {
			e.printStackTrace();
		}

		return "Reload Consults";
	}
}
