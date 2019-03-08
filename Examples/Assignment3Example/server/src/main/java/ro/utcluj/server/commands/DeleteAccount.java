package ro.utcluj.server.commands;

import ro.utcluj.server.mappers.StaffMapper;
import ro.utcluj.server.mappers.DoctorMapper;

public class DeleteAccount implements Command {
	private final String id;
	private final String type;

	public DeleteAccount(String id, String type) {
		this.id = id;
		this.type = type;
	}

	@Override
	public Object execute() {
		try {
			int role = Integer.parseInt(type);
			int idInt = Integer.parseInt(id);
			if (role == 2) {
				DoctorMapper.getInstance().delete(idInt);
			} else {
				StaffMapper.getInstance().delete(idInt);
			}
			return "accountDeleted";
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "accountNotDeleted";
	}
}
