package ro.utcluj.client.control;

import ro.utcluj.client.gui.AdminWindow;
import ro.utcluj.server.model.Doctor;
import ro.utcluj.server.model.Patient;
import ro.utcluj.server.model.Staff;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

public class AdminControl implements Observer {

	private final ObjectOutputStream output;
	private final String             username;
	private final AdminWindow        view;
	private final SimpleDateFormat   df = new SimpleDateFormat("dd-MM-yyyy");

	public AdminControl(ObjectOutputStream pOutput, String username) {
		this.output = pOutput;
		this.username = username;

		view = new AdminWindow();
		view.getBtnDeleteAccount().addActionListener(e -> deleteAccount());
		view.getBtnUpdateAccount().addActionListener(e -> updateAccount());
		view.getBtnFindAccount().addActionListener(e -> findAccount());
		view.getBtnCreateAccount().addActionListener(e -> createAccount());
		view.getBtnCreate().addActionListener(e -> createPatient());
		view.getBtnDeletePatient().addActionListener(e -> deletePatient());
		view.getBtnUpdatePatient().addActionListener(e -> updatePatient());
		view.getBtnLoadPatient().addActionListener(e -> loadPatient());
	}

	private void loadPatient() {
		try {
			int pnc = (int) view.getSpinnerPNC().getValue();
			output.writeObject("loadPatient\n" + pnc + "\n");
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(view.getFrame(), "Invalid Data entered!", "ERROR", 0);
		}
	}

	private void updatePatient() {
		try {
			int pnc = (int) view.getSpinnerPNC().getValue();
			String name = view.getPatientNameTextField().getText();
			Date date = (Date) view.getSpinnerBirthdate().getValue();
			String birthday = df.format(date);
			String address = view.getAddressTextField().getText();
			output.writeObject("updatePatient\n" + pnc + "\n" + name + "\n" + birthday + "\n" + address + "\n");
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(view.getFrame(), "Invalid Data entered!", "ERROR", 0);
		}
	}

	private void deletePatient() {
		try {
			int pnc = (int) view.getSpinnerPNC().getValue();
			output.writeObject("deletePatient\n" + pnc + "\n");
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(view.getFrame(), "Invalid PNC entered!", "ERROR", 0);
		}
	}

	private void createPatient() {
		try {
			int pnc = (int) view.getSpinnerPNC().getValue();
			String name = view.getPatientNameTextField().getText();
			Date date = (Date) view.getSpinnerBirthdate().getValue();
			String birthday = df.format(date);
			String address = view.getAddressTextField().getText();
			output.writeObject("createPatient\n" + pnc + "\n" + name + "\n" + birthday + "\n" + address + "\n");
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(view.getFrame(), "Invalid Data entered!", "ERROR", 0);
		}
	}

	private void createAccount() {
		try {
			String username = view.getUsernameTextField().getText();
			String password = view.getPasswordTextField().getText();
			String type = (String) view.getComboBox().getSelectedItem();
			if (view.getNameTextField().isVisible()) {
				String name = view.getNameTextField().getText();
				output.writeObject("createDoctor\n" + username + "\n" + password + "\n" + name + "\n");
			} else {
				output.writeObject("createStaff\n" + username + "\n" + password + "\n" + type + "\n");
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(view.getFrame(), "Invalid Data entered!", "ERROR", 0);
		}
	}

	private void findAccount() {
		String username = view.getUsernameTextField().getText();
		try {
			output.writeObject("findAccount\n" + username + "\n");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private void updateAccount() {
		try {
			JTextField usernameTextField = view.getUsernameTextField();
			JTextField passwordTextField = view.getPasswordTextField();
			JTextField nameTextField = view.getNameTextField();
			JLabel lblIdAccount = view.getLblIdAccount();

			String id = lblIdAccount.getText();
			String username = usernameTextField.getText();
			String password = passwordTextField.getText();
			String type = (String) view.getComboBox().getSelectedItem();
			if (nameTextField.isVisible()) {
				String name = nameTextField.getText();
				output.writeObject("updateDoctor\n" + id + "\n" + username + "\n" + password + "\n" + name + "\n");
			} else {
				output.writeObject("updateStaff\n" + id + "\n" + username + "\n" + password + "\n" + type + "\n");
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(view.getFrame(), "Invalid Data entered!", "ERROR", 0);
		}
	}

	private void deleteAccount() {
		try {
			String id = view.getLblIdAccount().getText();
			int type = view.getComboBox().getSelectedIndex();
			output.writeObject("deleteAccount\n" + id + "\n" + type + "\n");
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(view.getFrame(), "Invalid Data entered!", "ERROR", 0);
		}
	}

	public void start() {
		view.getFrame().setVisible(true);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg1 == null) {
			return;
		}
		if (arg1 instanceof Patient) {
			view.updateFields((Patient) arg1);
		}
		JFrame frame = view.getFrame();
		if (arg1 instanceof Doctor) {
			JOptionPane.showMessageDialog(frame, "Success");
			view.updateFields((Doctor) arg1);
		}
		if (arg1 instanceof Staff) {
			JOptionPane.showMessageDialog(frame, "Success");
			view.updateFields((Staff) arg1);
		}
		if (arg1 instanceof String) {
			String message = (String) arg1;
			showMessage(frame, message);
		}
	}

	// Must replace this monster method with a Map
	private void showMessage(JFrame frame, String message) {
		switch (message) {
			case "PatientCreated":
				JOptionPane.showMessageDialog(frame, "Patient Created");
				break;
			case "PatientNotCreated":
				JOptionPane.showMessageDialog(frame, "Patient NOT Created", "ERROR", 0);
				break;
			case "PatientUpdated":
				JOptionPane.showMessageDialog(frame, "Patient Updated");
				break;
			case "PatientNotUpdated":
				JOptionPane.showMessageDialog(frame, "Patient NOT Updated", "ERROR", 0);
				break;
			case "PatientDeleted":
				JOptionPane.showMessageDialog(frame, "Patient Deleted");
				view.clearFields();
				break;
			case "PatientNotDeleted":
				JOptionPane.showMessageDialog(frame, "Patient NOT Deleted", "ERROR", 0);
				break;
			case "NotCreated":
				JOptionPane.showMessageDialog(frame, "Account NOT Created", "ERROR", 0);
				break;
			case "accountUpdated":
				JOptionPane.showMessageDialog(frame, "Account Updated");
				break;
			case "accountNotUpdated":
				JOptionPane.showMessageDialog(frame, "Account NOT Updated", "ERROR", 0);
				break;
			case "accountDeleted":
				JOptionPane.showMessageDialog(frame, "Account Deleted");
				view.clearFields();
				break;
			case "accountNotDeleted":
				JOptionPane.showMessageDialog(frame, "Account NOT Deleted", "ERROR", 0);
				break;
			case "NotFound":
				JOptionPane.showMessageDialog(frame, "Account NOT Found");
				break;
		}
	}
}
