package ro.utcluj.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ro.utcluj.model.library.Book;
import ro.utcluj.model.library.Library;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {

    private Library model;

    @FXML
    private TableView<Book> table;

    @FXML
    TableColumn<Book, Integer> stockColumn;

    @FXML
    private Button sell;

    @FXML
    private Spinner<Integer> spinner;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.model = Library.getInstance();
        table.setItems(model.getBooks());

        stockColumn.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());

        sell.setOnAction(event -> sellBook());
    }

    private void sellBook() {
        TableView.TableViewSelectionModel<Book> selectionModel = table.getSelectionModel();
        Book selectedItem = selectionModel.getSelectedItem();
        if (selectedItem != null) {
            model.sell(selectedItem, spinner.getValue());
        }
    }
}
