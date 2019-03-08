package ro.utcluj.server.commands;

import ro.utcluj.server.mappers.DataSourceException;
import ro.utcluj.server.mappers.PatientMapper;

public class GetPattientsCommand implements Command {

	@Override
	public Object execute() {
		try {
			return PatientMapper.getInstance().getAll();
		} catch (DataSourceException e) {
			e.printStackTrace();
		}
		return null;
	}
}
