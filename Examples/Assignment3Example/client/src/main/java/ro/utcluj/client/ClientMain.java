package ro.utcluj.client;

import ro.utcluj.client.control.LoginControl;

import java.awt.*;

public class ClientMain {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				LoginControl control = new LoginControl();
				control.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
