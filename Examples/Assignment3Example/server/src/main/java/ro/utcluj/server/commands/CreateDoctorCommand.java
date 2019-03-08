package ro.utcluj.server.commands;

import ro.utcluj.server.mappers.DataSourceException;
import ro.utcluj.server.mappers.DoctorMapper;
import ro.utcluj.server.model.Doctor;

public class CreateDoctorCommand implements Command {
	private final String username;
	private final String password;
	private final String name;

	public CreateDoctorCommand(String username, String password, String name) {
		this.username = username;
		this.password = password;
		this.name = name;
	}

	@Override
	public Object execute() {
		try {
			Doctor doctor = new Doctor();
			doctor.setName(name);
			doctor.setUsername(username);
			doctor.setPassword(password);
			DoctorMapper.getInstance().create(doctor);

			return DoctorMapper.getInstance().getDoctor(username);
		} catch (DataSourceException e) {
			e.printStackTrace();
			return "NotCreated";
		}
	}
}
