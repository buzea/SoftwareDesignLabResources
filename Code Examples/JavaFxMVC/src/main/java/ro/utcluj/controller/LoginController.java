package ro.utcluj.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ro.utcluj.model.users.User;

import java.io.IOException;
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
					changeScene();
				}
			} else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Invalid Login Credentials");
				alert.setHeaderText("Invalid Login Credentials");
			}
		});
	}

	private void changeScene() {
		try {
			Stage window = (Stage) login.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/employee.fxml"));
			Parent root = loader.load();
			window.setTitle("Library");
			window.setScene(new Scene(root, 300, 275));
		} catch (IOException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error loading library");
			alert.setContentText(e.getMessage());
		}
	}
}
