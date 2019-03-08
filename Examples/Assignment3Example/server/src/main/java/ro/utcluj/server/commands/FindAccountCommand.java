package ro.utcluj.server.commands;

import ro.utcluj.server.mappers.DataSourceException;
import ro.utcluj.server.mappers.DoctorMapper;
import ro.utcluj.server.mappers.StaffMapper;
import ro.utcluj.server.model.Doctor;
import ro.utcluj.server.model.Staff;

public class FindAccountCommand implements Command {
	private final String username;

	public FindAccountCommand(String username) {
		this.username = username;
	}

	@Override
	public Object execute() {
		try {
			Doctor doc = DoctorMapper.getInstance().getDoctor(username);
			if (doc != null) {
				return doc;
			}
		} catch (DataSourceException e) {
			e.printStackTrace();
		}

		try {
			Staff staff = StaffMapper.getInstance().getStaff(username);
			if (staff != null) {
				return staff;
			}
		} catch (DataSourceException e) {
			e.printStackTrace();
		}

		return "NotFound";
	}
}
