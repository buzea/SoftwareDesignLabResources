package ro.utcluj.server.connections;

import ro.utcluj.server.commands.Command;
import ro.utcluj.server.commands.CommandFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerToClientConnection extends Thread {
	private final Socket             connection;
	private final int                id;
	private       ObjectOutputStream outStream;

	public ServerToClientConnection(Socket connection, int id) {
		this.connection = connection;
		this.id = id;
	}

	@Override
	public void run() {
		try {
			ObjectInputStream inStream = new ObjectInputStream(connection.getInputStream());
			outStream = new ObjectOutputStream(connection.getOutputStream());
			while (true) {
				String received = (String) inStream.readObject();
				String[] args = received.split("\n");
				Command command = CommandFactory.getCommand(args);
				if (command != null) {
					outStream.writeObject(command.execute());
				} else {
					outStream.writeObject(null);
				}
			}
		} catch (Exception e1) {
			System.out.println("Client " + id + " lost");
		}
	}

	public void sendObj(Object msg) throws IOException {
		if (outStream != null) {
			outStream.writeObject(msg);
		}
	}
}
