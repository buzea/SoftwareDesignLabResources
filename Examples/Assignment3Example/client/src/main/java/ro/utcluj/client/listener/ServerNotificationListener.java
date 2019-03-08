package ro.utcluj.client.listener;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Observer;

public class ServerNotificationListener extends Thread {
	private final ObjectInputStream input;
	private       Observer          observer;

	public ServerNotificationListener(ObjectInputStream input, Observer observer) {
		this.input = input;
		this.observer = observer;
	}

	public void run() {
		try {
			while (true) {
				Object o = input.readObject();
				observer.update(null, o);
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void setObserver(Observer view) {
		this.observer = view;
	}
}
