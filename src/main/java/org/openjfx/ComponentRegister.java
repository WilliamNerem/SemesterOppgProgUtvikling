package org.openjfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class ComponentRegister implements Serializable {

    private transient ObservableList<Component> components = FXCollections.observableArrayList();

    public void clearList() { components.removeAll(); }

    public void removeAll() { components.clear(); }

    public void addComponent(Component newComponent) { components.add(newComponent); }

    public void deleteComponent(int index) { components.remove(index); }

    public ObservableList<Component> getComponents() { return components; }

    public void attachTableView(TableView tv){ tv.setItems(components); }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeObject(new ArrayList<>(components));
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        List<Component> list = (List<Component>) s.readObject();
        components = FXCollections.observableArrayList();
        components.addAll(list);
    }


}
