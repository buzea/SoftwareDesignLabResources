package ro.utcluj.client.gui;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import ro.utcluj.server.model.Doctor;
import ro.utcluj.server.model.Patient;
import ro.utcluj.server.model.Staff;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.util.Calendar;
import java.util.Date;

public class AdminWindow {

	private JFrame            frame;
	private JTextField        patientNameTextField;
	private JTextField        addressTextField;
	private JTextField        nameTextField;
	private JLabel            lblName_1;
	private JTextField        passwordTextField;
	private JTextField        usernameTextField;
	private JSpinner          spinnerBirthdate;
	private JSpinner          spinnerPNC;
	private JLabel            lblIdAccount;
	private JComboBox<String> comboBox;
	private JButton           btnDeleteAccount;
	private JButton           btnUpdateAccount;
	private JButton           btnFindAccount;
	private JButton           btnCreateAccount;
	private JButton           btnDeletePatient;
	private JButton           btnUpdatePatient;
	private JButton           btnLoadPatient;
	private JButton           btnCreate;

	public AdminWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Hospital Admin");
		frame.setBounds(100, 100, 600, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(e -> System.exit(0));
		mnFile.add(mntmExit);

		JTabbedPane tabbedPane = new JTabbedPane();
		frame.setContentPane(tabbedPane);
		JPanel patientPanel = new JPanel();
		JPanel accountPanel = new JPanel();
		tabbedPane.addTab("Patient", patientPanel);

		JPanel panel = new JPanel();

		JPanel panel_2 = new JPanel();
		GroupLayout gl_patientPanel = new GroupLayout(patientPanel);
		gl_patientPanel.setHorizontalGroup(gl_patientPanel.createParallelGroup(Alignment.TRAILING).addGroup(gl_patientPanel.createSequentialGroup().addContainerGap().addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE).addPreferredGap(ComponentPlacement.RELATED).addComponent(panel, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE).addGap(14)));
		gl_patientPanel.setVerticalGroup(gl_patientPanel.createParallelGroup(Alignment.TRAILING).addGroup(gl_patientPanel.createSequentialGroup().addContainerGap().addGroup(gl_patientPanel.createParallelGroup(Alignment.TRAILING).addComponent(panel, GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE).addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)).addGap(6)));
		panel_2.setLayout(new FormLayout(new ColumnSpec[]{FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"),}, new RowSpec[]{FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,}));

		JLabel lblPnc = new JLabel("PNC:");
		panel_2.add(lblPnc, "4, 2, right, default");

		spinnerPNC = new JSpinner();
		spinnerPNC.setModel(new SpinnerNumberModel(1, 1, null, 1));
		panel_2.add(spinnerPNC, "6, 2");

		JLabel lblName = new JLabel("Name:");
		panel_2.add(lblName, "4, 4, right, default");

		patientNameTextField = new JTextField();
		patientNameTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_2.add(patientNameTextField, "6, 4, fill, default");
		patientNameTextField.setColumns(10);

		JLabel lblBirthdate = new JLabel("Birthdate:");
		panel_2.add(lblBirthdate, "4, 6, right, default");

		spinnerBirthdate = new JSpinner();
		Date datenow = Calendar.getInstance().getTime();
		SpinnerDateModel smb2 = new SpinnerDateModel(datenow, null, null, Calendar.HOUR_OF_DAY);
		spinnerBirthdate.setModel(smb2);
		JSpinner.DateEditor de_spinnerBirthdate = new JSpinner.DateEditor(spinnerBirthdate, "dd-MM-yyyy");
		de_spinnerBirthdate.getTextField().setHorizontalAlignment(JTextField.RIGHT);
		spinnerBirthdate.setEditor(de_spinnerBirthdate);
		panel_2.add(spinnerBirthdate, "6, 6");

		JLabel lblAddress = new JLabel("Address:");
		panel_2.add(lblAddress, "4, 8, right, default");

		addressTextField = new JTextField();
		addressTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_2.add(addressTextField, "6, 8, fill, default");
		addressTextField.setColumns(10);
		panel.setLayout(new FormLayout(new ColumnSpec[]{FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC,}, new RowSpec[]{FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,}));

		btnCreate = new JButton("Create Patient");

		panel.add(btnCreate, "2, 2, fill, default");

		btnLoadPatient = new JButton("Load Patient using PNC");

		panel.add(btnLoadPatient, "2, 4, fill, default");

		btnUpdatePatient = new JButton("Update Patient");

		panel.add(btnUpdatePatient, "2, 6, fill, default");

		btnDeletePatient = new JButton("Delete Patient");

		panel.add(btnDeletePatient, "2, 8, fill, default");
		patientPanel.setLayout(gl_patientPanel);
		tabbedPane.addTab("Account", accountPanel);

		JPanel panel_1 = new JPanel();

		JPanel panel_3 = new JPanel();
		GroupLayout gl_accountPanel = new GroupLayout(accountPanel);
		gl_accountPanel.setHorizontalGroup(gl_accountPanel.createParallelGroup(Alignment.TRAILING).addGroup(gl_accountPanel.createSequentialGroup().addContainerGap().addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE).addGap(10).addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE).addContainerGap()));
		gl_accountPanel.setVerticalGroup(gl_accountPanel.createParallelGroup(Alignment.TRAILING).addGroup(gl_accountPanel.createSequentialGroup().addContainerGap().addGroup(gl_accountPanel.createParallelGroup(Alignment.TRAILING).addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE).addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)).addGap(9)));
		panel_3.setLayout(new FormLayout(new ColumnSpec[]{FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"),}, new RowSpec[]{FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,}));

		JLabel lblId = new JLabel("ID:");
		panel_3.add(lblId, "4, 2, right, default");

		lblIdAccount = new JLabel("ID:");
		lblIdAccount.setVisible(false);
		panel_3.add(lblIdAccount, "6, 2, center, default");

		JLabel lblUsername = new JLabel("Username:");
		panel_3.add(lblUsername, "4, 4, right, default");

		usernameTextField = new JTextField();
		panel_3.add(usernameTextField, "6, 4, fill, default");
		usernameTextField.setColumns(10);

		JLabel lblPassword = new JLabel("Password:");
		panel_3.add(lblPassword, "4, 6, right, default");

		passwordTextField = new JTextField();
		panel_3.add(passwordTextField, "6, 6, fill, default");
		passwordTextField.setColumns(10);

		JLabel lblType = new JLabel("Type:");
		panel_3.add(lblType, "4, 8, right, default");

		comboBox = new JComboBox<>();
		comboBox.addItemListener(e -> {
			if (comboBox.getSelectedIndex() == 2) {
				lblName_1.setVisible(true);
				nameTextField.setVisible(true);
			} else {
				lblName_1.setVisible(false);
				nameTextField.setVisible(false);
			}
		});
		comboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Secretary", "Admin", "Doctor"}));
		panel_3.add(comboBox, "6, 8, fill, default");

		lblName_1 = new JLabel("Name:");
		panel_3.add(lblName_1, "4, 10, right, default");
		lblName_1.setVisible(false);

		nameTextField = new JTextField();
		panel_3.add(nameTextField, "6, 10, fill, default");
		nameTextField.setColumns(10);
		nameTextField.setVisible(false);
		panel_1.setLayout(new FormLayout(new ColumnSpec[]{FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC,}, new RowSpec[]{FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,}));

		btnCreateAccount = new JButton("Create Account");

		panel_1.add(btnCreateAccount, "2, 2");

		btnFindAccount = new JButton("Find Account by username");

		panel_1.add(btnFindAccount, "2, 4");

		btnUpdateAccount = new JButton("Update Account");

		panel_1.add(btnUpdateAccount, "2, 6");

		btnDeleteAccount = new JButton("Delete Account");
		panel_1.add(btnDeleteAccount, "2, 8");
		accountPanel.setLayout(gl_accountPanel);
	}

	public void updateFields(Staff staff) {
		lblIdAccount.setText("" + staff.getId());
		usernameTextField.setText(staff.getUsername());
		passwordTextField.setText(staff.getPassword());
		if (staff.getRole().equals(Staff.Type.ADMIN)) {
			comboBox.setSelectedIndex(1);
		} else {
			comboBox.setSelectedIndex(0);
		}
		lblIdAccount.setVisible(true);
	}

	public void updateFields(Doctor doc) {
		lblIdAccount.setText("" + doc.getIdDoctor());
		usernameTextField.setText(doc.getUsername());
		passwordTextField.setText(doc.getPassword());
		nameTextField.setText(doc.getName());
		comboBox.setSelectedIndex(2);
		lblIdAccount.setVisible(true);
	}

	public void clearFields() {
		spinnerPNC.setValue(1);
		patientNameTextField.setText("");
		spinnerBirthdate.setValue(new Date());
		addressTextField.setText("");
		lblIdAccount.setText("");
		usernameTextField.setText("");
		passwordTextField.setText("");
		nameTextField.setText("");
		comboBox.setSelectedIndex(0);
		lblIdAccount.setVisible(false);
	}

	public void updateFields(Patient patient) {
		spinnerPNC.setValue(patient.getPnc());
		patientNameTextField.setText(patient.getName());
		spinnerBirthdate.setValue(patient.getBirthday());
		addressTextField.setText(patient.getAddress());
	}

	public JFrame getFrame() {
		return frame;
	}

	public JTextField getPatientNameTextField() {
		return patientNameTextField;
	}

	public JTextField getAddressTextField() {
		return addressTextField;
	}

	public JTextField getNameTextField() {
		return nameTextField;
	}

	public JLabel getLblName_1() {
		return lblName_1;
	}

	public JTextField getPasswordTextField() {
		return passwordTextField;
	}

	public JTextField getUsernameTextField() {
		return usernameTextField;
	}

	public JSpinner getSpinnerBirthdate() {
		return spinnerBirthdate;
	}

	public JSpinner getSpinnerPNC() {
		return spinnerPNC;
	}

	public JLabel getLblIdAccount() {
		return lblIdAccount;
	}

	public JComboBox<String> getComboBox() {
		return comboBox;
	}

	public JButton getBtnDeleteAccount() {
		return btnDeleteAccount;
	}

	public JButton getBtnUpdateAccount() {
		return btnUpdateAccount;
	}

	public JButton getBtnFindAccount() {
		return btnFindAccount;
	}

	public JButton getBtnCreateAccount() {
		return btnCreateAccount;
	}

	public JButton getBtnDeletePatient() {
		return btnDeletePatient;
	}

	public JButton getBtnUpdatePatient() {
		return btnUpdatePatient;
	}

	public JButton getBtnLoadPatient() {
		return btnLoadPatient;
	}

	public JButton getBtnCreate() {
		return btnCreate;
	}
}
