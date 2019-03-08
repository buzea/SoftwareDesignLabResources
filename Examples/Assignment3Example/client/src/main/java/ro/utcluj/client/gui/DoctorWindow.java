package ro.utcluj.client.gui;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import ro.utcluj.server.model.Patient;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;

public class DoctorWindow {

	private JFrame            frame;
	private JTable            table;
	private DefaultTableModel tableModel;
	private JLabel            lblPnc;
	private JLabel            lblName;
	private JLabel            lblBirthdate;
	private JLabel            lblAddress;
	private JTextArea         textArea;
	private JButton           btnUpdate;
	private JButton           btnLoadCurrentPatient;
	private JButton           btnReload;

	public DoctorWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		tableModel = new DefaultTableModel(new Object[7][7], new String[]{"ID", "Doctor", "Patient", "Time", "Observations", "Diagnosis", "Prescription"}) {

			final boolean[] editableColumns = new boolean[]{false, false, false, false, true, true, true};

			public boolean isCellEditable(int row, int column) {
				return editableColumns[column];
			}
		};

		frame = new JFrame();
		frame.setTitle("Hospital Doctor");
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

		tabbedPane.addTab("Patient", patientPanel);

		JPanel panel_1 = new JPanel();

		textArea = new JTextArea();
		GroupLayout gl_patientPanel = new GroupLayout(patientPanel);
		gl_patientPanel.setHorizontalGroup(gl_patientPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_patientPanel.createSequentialGroup().addGap(2).addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(textArea, GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE).addContainerGap()));
		gl_patientPanel.setVerticalGroup(gl_patientPanel.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, gl_patientPanel.createSequentialGroup().addContainerGap().addGroup(gl_patientPanel.createParallelGroup(Alignment.TRAILING).addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE).addComponent(textArea, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)).addContainerGap()));

		btnLoadCurrentPatient = new JButton("Load Latest Patient");

		lblPnc = new JLabel("PNC:");

		lblName = new JLabel("Name:");

		lblBirthdate = new JLabel("Birthdate:");

		lblAddress = new JLabel("Address:");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_1.createSequentialGroup().addComponent(btnLoadCurrentPatient).addGap(100)).addComponent(lblPnc, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE).addComponent(lblName, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE).addComponent(lblBirthdate, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE).addComponent(lblAddress, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_1.createSequentialGroup().addComponent(btnLoadCurrentPatient).addPreferredGap(ComponentPlacement.RELATED).addComponent(lblPnc).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lblName).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lblBirthdate).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lblAddress, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE).addContainerGap(47, Short.MAX_VALUE)));
		panel_1.setLayout(gl_panel_1);
		patientPanel.setLayout(gl_patientPanel);

		initConsultations(tabbedPane);
	}

	private void initConsultations(JTabbedPane tabbedPane) {
		JPanel consultationsPanel = new JPanel();
		tabbedPane.addTab("Consultations", consultationsPanel);

		btnUpdate = new JButton("Update");
		btnReload = new JButton("Reload");

		JPanel rightPanel = new JPanel();
		//@formatter:off
		rightPanel.setLayout(new FormLayout(
				new ColumnSpec[]{	FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC,
									FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC},
				new RowSpec[]{		FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
									FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
									FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC}));
		//@formatter:on
		rightPanel.add(btnUpdate, "2, 2");
		rightPanel.add(btnReload, "2, 4");

		JScrollPane leftPanel = new JScrollPane();
		table = new JTable();
		table.setModel(tableModel);
		leftPanel.setViewportView(table);

		GroupLayout groupLayout = new GroupLayout(consultationsPanel);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(leftPanel, GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(rightPanel, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE).addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup().addContainerGap().addGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addComponent(leftPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE).addComponent(rightPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)).addGap(8)));

		consultationsPanel.setLayout(groupLayout);
	}

	public void updateLabels(Patient p) {
		lblPnc.setText("PNC: " + p.getPnc());
		lblName.setText("Name: " + p.getName());
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		lblBirthdate.setText("Birthday: " + df.format(p.getBirthday()));
		lblAddress.setText("Address: " + p.getAddress());
		textArea.setText(p.getMedicalHistory());
	}

	public void updateTable(Object[][] arg1) {
		tableModel = new DefaultTableModel(arg1, new String[]{"ID", "Doctor", "Patient", "Time", "Observations", "Diagnosis", "Prescription"}) {
			final boolean[] columnEditable = new boolean[]{false, false, false, false, true, true, true};

			public boolean isCellEditable(int row, int column) {
				return columnEditable[column];
			}
		};
		table.setModel(tableModel);
	}

	public JFrame getFrame() {
		return frame;
	}

	public JButton getBtnUpdate() {
		return btnUpdate;
	}

	public JTable getTable() {
		return table;
	}

	public JButton getBtnLoadCurrentPatient() {
		return btnLoadCurrentPatient;
	}

	public JButton getBtnReload() {
		return btnReload;
	}
}
