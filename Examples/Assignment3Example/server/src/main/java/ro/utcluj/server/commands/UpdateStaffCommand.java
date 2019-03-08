package ro.utcluj.server.commands;

import ro.utcluj.server.mappers.DataSourceException;
import ro.utcluj.server.mappers.StaffMapper;
import ro.utcluj.server.model.Staff;

public class UpdateStaffCommand implements Command {
	private final String id;
	private final String username;
	private final String password;
	private final String type;

	public UpdateStaffCommand(String id, String username, String password, String type) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.type = type;
	}

	@Override
	public Object execute() {
		try {
			Staff staff = new Staff();
			staff.setId(Integer.parseInt(id));
			staff.setUsername(username);
			staff.setPassword(password);
			if (type.equalsIgnoreCase("admin")) {
				staff.setRole(Staff.Type.ADMIN);
			} else {
				staff.setRole(Staff.Type.SECRETARY);
			}
			StaffMapper.getInstance().update(staff);
			return "accountUpdated";
		} catch (DataSourceException e) {
			e.printStackTrace();
		}

		return "accountNotUpdated";
	}
}
