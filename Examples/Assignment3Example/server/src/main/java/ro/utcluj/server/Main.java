package ro.utcluj.server;

import ro.utcluj.server.hibernate.DataInitializer;

public class Main {

	public static void main(String[] args) {
		DataInitializer.insertInitialData();
		Server server = Server.getInstance();
		server.start();
	}
}
