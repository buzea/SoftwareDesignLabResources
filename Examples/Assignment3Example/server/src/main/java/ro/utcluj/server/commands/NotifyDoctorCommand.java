package ro.utcluj.server.commands;

import ro.utcluj.server.Server;
import ro.utcluj.server.connections.ServerToClientConnection;
import ro.utcluj.server.notification.DoctorNotification;
import ro.utcluj.server.mappers.ConsultationMapper;
import ro.utcluj.server.mappers.DataSourceException;
import ro.utcluj.server.model.Consultation;

import java.io.IOException;
import java.util.List;

public class NotifyDoctorCommand implements Command {
	private final String idConsult;

	public NotifyDoctorCommand(String idConsult) {
		this.idConsult = idConsult;
	}

	@Override
	public Object execute() {
		try {
			Consultation consult = ConsultationMapper.getInstance().getConsultation(Integer.parseInt(idConsult));
			String username = consult.getDoctor().getUsername();
			List<ServerToClientConnection> connections = Server.getInstance().getConnections();
			//System.out.println(ro.utcluj.connections);
			for (ServerToClientConnection c : connections) {
				c.sendObj(new DoctorNotification(username));
			}
		} catch (NumberFormatException | IOException | DataSourceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
