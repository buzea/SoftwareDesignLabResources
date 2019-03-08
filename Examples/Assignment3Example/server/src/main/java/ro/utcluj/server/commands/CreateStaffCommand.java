package ro.utcluj.server.commands;

import ro.utcluj.server.model.Staff;
import ro.utcluj.server.mappers.DataSourceException;
import ro.utcluj.server.mappers.StaffMapper;

public class CreateStaffCommand implements Command {
	private final String username;
	private final String password;
	private final String type;

	public CreateStaffCommand(String username, String password, String type) {
		this.username = username;
		this.password = password;
		this.type = type;
	}

	@Override
	public Object execute() {
		Staff staff = new Staff();
		staff.setPassword(password);
		staff.setUsername(username);
		if (type.equalsIgnoreCase("admin")) {
			staff.setRole(Staff.Type.ADMIN);
		} else {
			staff.setRole(Staff.Type.SECRETARY);
		}
		try {
			StaffMapper.getInstance().create(staff);
			return StaffMapper.getInstance().getStaff(username);
		} catch (DataSourceException e) {
			e.printStackTrace();
			return "NotCreated";
		}
	}
}
