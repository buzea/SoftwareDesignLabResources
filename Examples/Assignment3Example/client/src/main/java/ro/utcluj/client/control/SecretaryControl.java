package ro.utcluj.client.control;

import ro.utcluj.client.gui.SecretaryWindow;
import ro.utcluj.server.model.Doctor;
import ro.utcluj.server.model.Patient;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import static ro.utcluj.client.gui.SecretaryWindow.df;

public class SecretaryControl implements Observer {

	private final SecretaryWindow    view;
	private final ObjectOutputStream output;
	private final String             username;

	public SecretaryControl(ObjectOutputStream pOutput, String pUsername) {
		this.output = pOutput;
		this.username = pUsername;
		view = new SecretaryWindow();
		view.getBtnUpdatePatientInfo().addActionListener(e -> updatePatientInfo());
		view.getBtnUpdate().addActionListener(e -> updateConsult());
		view.getBtnDelete().addActionListener(e -> deleteConsult());
		view.getBtnNotifyDoctor().addActionListener(e -> notifyDoctor());
		view.getBtnAddConsultation().addActionListener(e -> addConsultation());
		view.getBtnAddPatient().addActionListener(e -> {
			try {
				int pnc = (int) view.getSpinnerPNC().getValue();
				String name = view.getNameTextField().getText();
				Date date = (Date) view.getSpinnerBirthdate().getValue();
				String birthdate = df.format(date);
				String address = view.getAddressTextField().getText();
				output.writeObject("createPatient\n" + username + "\n" + pnc + "\n" + name + "\n" + birthdate + "\n" + address + "\n");
				output.writeObject("getPatients\n");
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(view.getFrmHospitalSecretary(), "Invalid Data entered!", "ERROR", 0);
			}
		});
	}

	private void addConsultation() {
		Patient p = (Patient) view.getPatientComboBox().getSelectedItem();
		Doctor d = (Doctor) view.getDoctorComboBox().getSelectedItem();
		Date time = (Date) view.getSpinnerTime().getValue();
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			output.writeObject("createConsultation\n" + p.getPnc() + "\n" + d.getIdDoctor() + "\n" + dft.format(time) + "\n");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private void notifyDoctor() {
		try {
			JTable table = view.getTable();
			int row = table.getSelectedRow();
			if (row >= 0) {
				int id = (int) table.getValueAt(row, 0);
				output.writeObject("notifyDoctor\n" + id + "\n");
			}
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(view.getFrmHospitalSecretary(), "Unable to perform update", "ERROR", 0);
		}
	}

	private void deleteConsult() {
		try {
			JTable table = view.getTable();
			int row = table.getSelectedRow();
			int id = (int) table.getValueAt(row, 0);

			output.writeObject("deleteConsult\n" + id + "\n");
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(view.getFrmHospitalSecretary(), "Unable to perform update", "ERROR", 0);
		}
	}

	private void updateConsult() {
		try {
			JTable table = view.getTable();
			int row = table.getSelectedRow();
			int id = (int) table.getValueAt(row, 0);
			String prescription = (String) table.getValueAt(row, 6);
			String diagnosis = (String) table.getValueAt(row, 5);
			String observations = (String) table.getValueAt(row, 4);

			output.writeObject("updateConsult\n" + id + "\n" + prescription + "\n" + diagnosis + "\n" + observations + "\n");
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(view.getFrmHospitalSecretary(), "Unable to perform update", "ERROR", 0);
		}
	}

	private void updatePatientInfo() {
		try {
			int pnc = (int) view.getSpinnerPNC().getValue();
			String name = view.getNameTextField().getText();
			Date date = (Date) view.getSpinnerBirthdate().getValue();
			String birthdate = df.format(date);
			String address = view.getAddressTextField().getText();
			output.writeObject("updatePatient\n" + "\n" + pnc + "\n" + name + "\n" + birthdate + "\n" + address + "\n");
			output.writeObject("getPatients\n");
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(view.getFrmHospitalSecretary(), "Invalid Data entered!", "ERROR", 0);
		}
	}

	public void start() throws IOException {
		output.writeObject("viewConsults\nsecretary\n" + this.username + "\n");
		output.writeObject("getPatients\n");
		output.writeObject("getDoctors\n");
		view.getFrmHospitalSecretary().setVisible(true);
	}

	@Override
	public void update(Observable o, Object argument) {
		if (argument == null) {
			return;
		}
		if (argument instanceof String) {
			showMessage((String) argument);
		}
		if (argument instanceof Object[][]) {
			view.updateTable((Object[][]) argument);
		}
		if (argument instanceof List<?>) {
			List<?> list = (List<?>) argument;
			if (list.get(0) instanceof Patient) {
				addPatients((List<Patient>) list);
			}
			if (list.get(0) instanceof Doctor) {
				addDoctors((List<Doctor>) list);
			}
		}
	}

	private void addDoctors(List<Doctor> list) {
		List<Doctor> doctors = list;
		DefaultComboBoxModel<Doctor> dcm = new DefaultComboBoxModel<>();
		for (Doctor p : doctors) {
			dcm.addElement(p);
		}
		view.getDoctorComboBox().setModel(dcm);
	}

	private void addPatients(List<Patient> list) {
		List<Patient> patients = list;
		DefaultComboBoxModel<Patient> dcm = new DefaultComboBoxModel<>();
		for (Patient p : patients) {
			dcm.addElement(p);
		}
		view.getPatientComboBox().setModel(dcm);
	}

	private void showMessage(String message) {
		JFrame frmHospitalSecretary = view.getFrmHospitalSecretary();
		switch (message) {
			case "PatientCreated":
				JOptionPane.showMessageDialog(frmHospitalSecretary, "Patient Created");
				break;
			case "PatientNotCreated":
				JOptionPane.showMessageDialog(frmHospitalSecretary, "Patient NOT Created", "ERROR", 0);
				break;
			case "PatientUpdated":
				JOptionPane.showMessageDialog(frmHospitalSecretary, "Patient Updated");
				break;
			case "PatientNotUpdated":
				JOptionPane.showMessageDialog(frmHospitalSecretary, "Patient NOT Updated", "ERROR", 0);
				break;
			case "ConsultDeleted":
				JOptionPane.showMessageDialog(frmHospitalSecretary, "Consult Deleted");
				try {
					output.writeObject("viewConsults\nsecretary\n" + this.username + "\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case "ConsultNotDeleted":
				JOptionPane.showMessageDialog(frmHospitalSecretary, "Consult Not Deleted", "ERROR", 0);
				break;

			case "ConsultCreated":
				JOptionPane.showMessageDialog(frmHospitalSecretary, "Consult Created");
				try {
					output.writeObject("viewConsults\nsecretary\n" + this.username + "\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case "ConsultNotCreated":
				JOptionPane.showMessageDialog(frmHospitalSecretary, "Consult Not Created", "ERROR", 0);
				break;
		}
	}
}
