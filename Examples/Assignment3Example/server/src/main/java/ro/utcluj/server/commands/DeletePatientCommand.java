package ro.utcluj.server.commands;

import ro.utcluj.server.mappers.PatientMapper;
import ro.utcluj.server.mappers.DataSourceException;

public class DeletePatientCommand implements Command {
	private final String pnc;

	public DeletePatientCommand(String pnc) {
		this.pnc = pnc;
	}

	@Override
	public Object execute() {
		try {
			PatientMapper.getInstance().delete(Integer.parseInt(pnc));
			return "PatientDeleted";
		} catch (DataSourceException e) {
			e.printStackTrace();
			return "PatientNotDeleted";
		}
	}
}
