package ro.utcluj.client.gui;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

public class LoginWindow {

	private JFrame         frame;
	private JTextField     usernameText;
	private JPasswordField passwordField;
	private JButton        btnLogin;

	public LoginWindow() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Hospital Login");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(Alignment.LEADING, groupLayout.createSequentialGroup().addGap(93).addComponent(panel, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE).addContainerGap(101, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(panel, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE).addContainerGap(12, Short.MAX_VALUE)));
		panel.setLayout(new FormLayout(new ColumnSpec[]{FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"),}, new RowSpec[]{FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,}));

		JLabel lblUsername = new JLabel("Username:");
		panel.add(lblUsername, "6, 8, right, default");

		usernameText = new JTextField();
		panel.add(usernameText, "8, 8, fill, default");
		usernameText.setColumns(10);

		JLabel lblPassword = new JLabel("Password:");
		panel.add(lblPassword, "6, 10, right, default");

		btnLogin = new JButton("Login");

		passwordField = new JPasswordField();
		panel.add(passwordField, "8, 10, fill, default");
		panel.add(btnLogin, "8, 12");
		frame.getContentPane().setLayout(groupLayout);
	}

	public void setVisible(boolean visible) {
		frame.setVisible(visible);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void dispose() {
		frame.dispose();
	}

	public JTextField getUsernameText() {
		return usernameText;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public JButton getBtnLogin() {
		return btnLogin;
	}
}
