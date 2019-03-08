package ro.utcluj.server.commands;

import ro.utcluj.server.mappers.DataSourceException;
import ro.utcluj.server.mappers.DoctorMapper;
import ro.utcluj.server.mappers.StaffMapper;
import ro.utcluj.server.model.Doctor;
import ro.utcluj.server.model.Staff;

public class LoginCommand implements Command {

	private final String username;
	private final String password;

	public LoginCommand(String username, String password) {
		this.username = username;
		this.password = password;
	}

	@Override
	public Object execute() {
		try {
			DoctorMapper doctorMapper = DoctorMapper.getInstance();
			StaffMapper staffMapper = StaffMapper.getInstance();

			Doctor doctor = doctorMapper.getDoctor(username);
			if (doctor != null && doctor.getPassword().equals(password)) {
				return doctor;
			}

			Staff staff = staffMapper.getStaff(username);
			if (staff != null && staff.getPassword().equals(password)) {
				return staff;
			}
		} catch (DataSourceException e) {
			e.printStackTrace();
		}
		return null;
	}
}
