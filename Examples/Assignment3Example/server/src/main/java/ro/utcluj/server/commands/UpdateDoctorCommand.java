package ro.utcluj.server.commands;

import ro.utcluj.server.model.Doctor;
import ro.utcluj.server.mappers.DataSourceException;
import ro.utcluj.server.mappers.DoctorMapper;

public class UpdateDoctorCommand implements Command {
	private final String id;
	private final String username;
	private final String password;
	private final String name;

	public UpdateDoctorCommand(String id, String username, String password, String name) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
	}

	@Override
	public Object execute() {
		Doctor doc = new Doctor();
		doc.setIdDoctor(Integer.parseInt(id));
		doc.setUsername(username);
		doc.setPassword(password);
		doc.setName(name);
		try {
			DoctorMapper.getInstance().update(doc);
			return "accountUpdated";
		} catch (DataSourceException e) {
			e.printStackTrace();
		}

		return "accountNotUpdated";
	}
}
