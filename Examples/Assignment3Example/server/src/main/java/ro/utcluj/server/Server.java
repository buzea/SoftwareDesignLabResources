package ro.utcluj.server;

import org.hibernate.Session;
import ro.utcluj.server.connections.ServerToClientConnection;
import ro.utcluj.server.hibernate.HibernateUtil;
import ro.utcluj.server.model.Doctor;
import ro.utcluj.server.model.Staff;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server extends Thread {

	public static final int PORT_NUMBER = 9990;

	private final List<ServerToClientConnection> connections = new ArrayList<>();

	private static Server instance;

	public static Server getInstance() {
		if (instance == null) {
			instance = new Server();
		}
		return instance;
	}

	public void run() {
		int count = 0;
		System.out.println("Starting the multiple socket server at port: " + PORT_NUMBER);
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(PORT_NUMBER);
			System.out.println("Multiple Socket Server Initialized");

			while (true) {
				count = waitForClients(count, serverSocket);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSocket(serverSocket);
		}
	}

	private int waitForClients(int count, ServerSocket serverSocket) throws IOException {
		Socket client = serverSocket.accept();
		ServerToClientConnection thread = new ServerToClientConnection(client, ++count);
		connections.add(thread);
		thread.start();
		return count;
	}

	private void closeSocket(ServerSocket serverSocket) {
		if (serverSocket != null) {
			try {
				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public List<ServerToClientConnection> getConnections() {
		return connections;
	}
}
