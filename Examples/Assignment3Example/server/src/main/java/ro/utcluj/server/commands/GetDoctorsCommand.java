package ro.utcluj.server.commands;

import ro.utcluj.server.mappers.DataSourceException;
import ro.utcluj.server.mappers.DoctorMapper;

public class GetDoctorsCommand implements Command {

	@Override
	public Object execute() {
		try {
			return DoctorMapper.getInstance().getAll();
		} catch (DataSourceException e) {
			e.printStackTrace();
		}
		return null;
	}
}
