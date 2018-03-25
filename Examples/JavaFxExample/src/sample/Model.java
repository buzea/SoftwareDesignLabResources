package sample;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Model {

    private static Model INSTANCE;
    private final ListProperty<String> names;
    private static int number = 0;

    private Model() {
        this.names = new SimpleListProperty<>(FXCollections.observableArrayList());
    }

    /*
    Please note that the model MUST be observable in order for the MVC Pattern to work
     */
    public ObservableList<String> getNames() {
        return names.getValue();
    }

    public static Model getInstance() {
        if (INSTANCE == null) INSTANCE = new Model();
        return INSTANCE;
    }

    public void generateName() {
        getNames().add("Student" + number);
        number++;
    }

    public void removeLast() {
        if (getNames().size() > 0)
            getNames().remove(getNames().size() - 1);
    }
}
