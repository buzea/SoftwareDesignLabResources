package ro.utcluj.server.notification;

import java.io.Serializable;

public class DoctorNotification implements Serializable {

	private static final long   serialVersionUID = 2556969293905850171L;
	private              String username;

	public DoctorNotification(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
