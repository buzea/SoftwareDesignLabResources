package ro.utcluj.server.commands;

import ro.utcluj.server.mappers.PatientMapper;
import ro.utcluj.server.model.Patient;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreatePatientCommand implements Command {
	@SuppressWarnings("unused")
	private final String           pncString;
	@SuppressWarnings("unused")
	private final String           name;
	@SuppressWarnings("unused")
	private final String           birthday;
	@SuppressWarnings("unused")
	private final String           address;
	private final SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

	public CreatePatientCommand(String pncString, String name, String birthday, String address) {
		this.pncString = pncString;
		this.name = name;
		this.birthday = birthday;
		this.address = address;
	}

	@Override
	public Object execute() {

		try {
			Patient patient = new Patient();
			int pnc = Integer.parseInt(pncString);
			patient.setPnc(pnc);
			patient.setName(name);
			Date date = df.parse(birthday);
			patient.setBirthday(date);
			patient.setAddress(address);
			PatientMapper.getInstance().create(patient);
			return "PatientCreated";
		} catch (Exception e) {
			return "PatientNotCreated";
		}
	}
}
