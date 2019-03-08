package ro.utcluj.server.commands;

import ro.utcluj.server.model.Patient;
import ro.utcluj.server.mappers.PatientMapper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdatePatientCommand implements Command {
	@SuppressWarnings("unused")
	private final String           pncString;
	@SuppressWarnings("unused")
	private final String           name;
	@SuppressWarnings("unused")
	private final String           birthdate;
	@SuppressWarnings("unused")
	private final String           address;
	private final SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

	public UpdatePatientCommand(String pncString, String name, String birthdate, String address) {
		this.pncString = pncString;
		this.name = name;
		this.birthdate = birthdate;
		this.address = address;
	}

	@Override
	public Object execute() {

		try {
			Patient patient = new Patient();
			int pnc = Integer.parseInt(pncString);
			patient.setPnc(pnc);
			patient.setName(name);
			Date date = df.parse(birthdate);
			patient.setBirthday(date);
			patient.setAddress(address);
			PatientMapper.getInstance().update(patient);
			return "PatientUpdated";
		} catch (Exception e) {
			return "PatientNotUpdated";
		}
	}
}
