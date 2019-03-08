package ro.utcluj.client.control;

import ro.utcluj.client.gui.LoginWindow;
import ro.utcluj.client.listener.ServerNotificationListener;
import ro.utcluj.server.model.Doctor;
import ro.utcluj.server.model.Staff;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

import static javax.swing.JOptionPane.showMessageDialog;

public class LoginControl implements Observer {

	private LoginWindow                view;
	private ServerNotificationListener listener;
	private Socket                     socketClient;
	private ObjectOutputStream         output;

	public LoginControl() {
		view = new LoginWindow();
		view.getBtnLogin().addActionListener(e -> verifyCredentials());
	}

	private void verifyCredentials() {
		try {
			String username = view.getUsernameText().getText();
			String password = new String(view.getPasswordField().getPassword());
			output.writeObject("login\n" + username + "\n" + password);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(view.getFrame(), "Connection Error", "ERROR", 0);
		}
	}

	public void start() throws IOException {
		socketClient = new Socket("localhost", 9990);
		output = new ObjectOutputStream(socketClient.getOutputStream());
		ObjectInputStream input = new ObjectInputStream(socketClient.getInputStream());
		listener = new ServerNotificationListener(input, this);
		listener.start();
		view.setVisible(true);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg == null) {
			showMessageDialog(view.getFrame(), "Invalid Credentials");
			return;
		}
		if (arg instanceof Doctor) {
			loginDoctor((Doctor) arg);
			return;
		}

		if (arg instanceof Staff) {
			loginStaff((Staff) arg);
		}
	}

	private void loginStaff(Staff staff) {
		try {
			if (staff.getRole() == Staff.Type.ADMIN) {
				AdminControl w = new AdminControl(output, staff.getUsername());
				listener.setObserver(w);
				view.dispose();
				w.start();
			} else {
				SecretaryControl w = new SecretaryControl(output, staff.getUsername());
				listener.setObserver(w);
				view.dispose();
				w.start();
			}
		} catch (IOException e) {
			showMessageDialog(view.getFrame(), "Connection Lost", "ERROR", 0);
		}
	}

	private void loginDoctor(Doctor doctor) {
		DoctorControl w = new DoctorControl(output, doctor);
		listener.setObserver(w);
		view.dispose();
		w.start();
	}
}
