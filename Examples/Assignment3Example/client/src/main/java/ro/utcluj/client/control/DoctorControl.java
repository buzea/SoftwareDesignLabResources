package ro.utcluj.client.control;

import ro.utcluj.client.gui.DoctorWindow;
import ro.utcluj.server.model.Doctor;
import ro.utcluj.server.model.Patient;
import ro.utcluj.server.notification.DoctorNotification;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Observable;
import java.util.Observer;

public class DoctorControl implements Observer {

	private final DoctorWindow       view;
	private final ObjectOutputStream output;
	private final Doctor             doctor;

	public DoctorControl(ObjectOutputStream output, Doctor doctor) {
		this.output = output;
		this.doctor = doctor;
		view = new DoctorWindow();
		view.getBtnUpdate().addActionListener(e -> updateConsult(output, doctor));
		view.getBtnLoadCurrentPatient().addActionListener(event -> loadPatient(output, doctor));
		view.getBtnReload().addActionListener(e -> reloadConsults());
	}

	private void loadPatient(ObjectOutputStream output, Doctor doctor) {
		try {
			output.writeObject("getCurrentPatient\n" + doctor.getUsername() + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void updateConsult(ObjectOutputStream output, Doctor doctor) {
		JTable table = view.getTable();
		try {
			int row = getRow(table);
			int id = (int) table.getValueAt(row, 0);
			String prescription = (String) table.getValueAt(row, 6);
			String diagnosis = (String) table.getValueAt(row, 5);
			String observations = (String) table.getValueAt(row, 4);

			output.writeObject("updateConsult\n" + id + "\n" + prescription + "\n" + diagnosis + "\n" + observations + "\n");
			output.writeObject("getCurrentPatient\n" + doctor.getUsername() + "\n");
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(view.getFrame(), "Unable to perform update", "ERROR", 0);
		}
	}

	private int getRow(JTable table) {
		int row = table.getSelectedRow();
		if (row < 0) {
			JOptionPane.showMessageDialog(view.getFrame(), "You must select a row to update", "ERROR", 0);
			throw new IllegalArgumentException();
		}
		return row;
	}

	public void start() {
		reloadConsults();
		view.getFrame().setVisible(true);
	}

	@Override
	public void update(Observable ignored, Object argument) {
		if (argument == null) {
			return;
		}
		if (argument instanceof DoctorNotification) {
			DoctorNotification dn = (DoctorNotification) argument;
			if (dn.getUsername().equals(doctor.getUsername())) {
				JOptionPane.showMessageDialog(view.getFrame(), "Patient has arrived for consultation");
			}
			return;
		}
		if (argument instanceof Object[][]) {
			view.updateTable((Object[][]) argument);
			return;
		}
		if (argument instanceof Patient) {
			view.updateLabels((Patient) argument);
			return;
		}
		if (argument instanceof String) {
			String value = (String) argument;
			if ("Reload Consults".equals(value)) {
				reloadConsults();
			}
		}
	}

	private void reloadConsults() {
		try {
			output.writeObject("viewConsults\ndoctor\n" + this.doctor.getUsername() + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
