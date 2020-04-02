package org.openjfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ComponentRegister {

    private ObservableList<Component> components = FXCollections.observableArrayList();

    public void clearList() { components.removeAll(); }

    public void addComponent(Component newComponent) { components.add(newComponent); }

    public void deleteComponent(int index) { components.remove(index); }

    public ObservableList<Component> getComponents() { return components; }

}
