package ro.utcluj.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import ro.utcluj.model.library.Book;
import ro.utcluj.model.library.Library;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {

	private Library model;

	@FXML
	private TableView<Book> table;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.model = Library.getInstance();
		table.setItems(model.getBooks());
	}
}
