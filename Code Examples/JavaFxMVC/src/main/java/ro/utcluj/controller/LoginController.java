package ro.utcluj.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import ro.utcluj.model.users.User;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

	@FXML
	Button login;

	@FXML
	PasswordField password;

	@FXML
	TextField username;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		login.setOnAction(e -> {

			User user = User.login(username.getText(), password.getText());
			if (user != null) {
				if (user.getType() == User.Type.ADMIN) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText("Admin Page not implemented yet!");
				} else {
					Window window = login.getScene().getWindow();
					
				}
			} else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Invalid Login Credentials");
				alert.setHeaderText("Invalid Login Credentials");
			}
		});
	}
}
