package org.openjfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class History {
    private ObservableList<Component> components = FXCollections.observableArrayList();

    public void addComponent(Component newComponent) {
        components.add(newComponent);
    }

    public ObservableList<Component> getComponents(){
        return components;
    }
}
