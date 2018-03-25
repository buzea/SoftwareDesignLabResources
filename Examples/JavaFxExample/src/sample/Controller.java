package sample;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class Controller {

    @FXML
    private Button generate;

    @FXML
    private Button remove;

    @FXML
    private ListView listView;

    private Model model;

    @FXML
    public void initialize() {
        model = Model.getInstance();
        listView.setItems(model.getNames());
    }

    @FXML
    void generateName(ActionEvent event) {
        model.generateName();
    }

    @FXML
    void removeLast(ActionEvent event) {
        model.removeLast();
    }


}
