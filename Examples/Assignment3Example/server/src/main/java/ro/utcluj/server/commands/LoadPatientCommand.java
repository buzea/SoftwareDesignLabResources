package ro.utcluj.server.commands;

import ro.utcluj.server.mappers.DataSourceException;
import ro.utcluj.server.mappers.PatientMapper;

public class LoadPatientCommand implements Command {
	private final String pnc;

	public LoadPatientCommand(String pnc) {
		this.pnc = pnc;
	}

	@Override
	public Object execute() {
		try {
			return PatientMapper.getInstance().getPatient(Integer.parseInt(pnc));
		} catch (DataSourceException e) {
			e.printStackTrace();
		}
		return null;
	}
}
