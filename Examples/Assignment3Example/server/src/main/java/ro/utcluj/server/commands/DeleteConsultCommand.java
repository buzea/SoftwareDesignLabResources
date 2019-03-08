package ro.utcluj.server.commands;

import ro.utcluj.server.mappers.ConsultationMapper;
import ro.utcluj.server.mappers.DataSourceException;

public class DeleteConsultCommand implements Command {
	private final String id;

	public DeleteConsultCommand(String id) {
		this.id = id;
	}

	@Override
	public Object execute() {
		try {
			ConsultationMapper.getInstance().delete(Integer.parseInt(id));
			return "ConsultDeleted";
		} catch (NumberFormatException | DataSourceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "ConsultNotDeleted";
	}
}
