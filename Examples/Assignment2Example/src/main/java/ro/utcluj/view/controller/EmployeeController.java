package ro.utcluj.view.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ro.utcluj.view.controller.helper.DialogsHelper;
import ro.utcluj.view.model.library.Book;
import ro.utcluj.view.model.library.Library;

import java.net.URL;
import java.util.ResourceBundle;

import static ro.utcluj.view.controller.helper.DialogsHelper.showCustomDialog;

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

    @FXML
    private Button create;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.model = Library.getInstance();
        table.setItems(model.getBooks());

        stockColumn.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());

        sell.setOnAction(event -> sellBook());
        create.setOnAction(event -> createBook());
    }

    private void createBook() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/addBook.fxml"));
            Parent parent = fxmlLoader.load();
            AddBookController dialogController = fxmlLoader.getController();
            showCustomDialog("Add Book", parent, () -> dialogController.getBook())
                    .ifPresent(newBook -> {
                        model.addBook(newBook);
                    });

        } catch (Exception e) {
            DialogsHelper.showErrorDialog("Unable to add book!");
            e.printStackTrace();
        }
    }

    private void sellBook() {
        TableView.TableViewSelectionModel<Book> selectionModel = table.getSelectionModel();
        Book selectedItem = selectionModel.getSelectedItem();
        if (selectedItem != null) {
            model.sell(selectedItem, spinner.getValue());
        }
    }
}
