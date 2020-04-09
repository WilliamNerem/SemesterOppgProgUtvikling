package org.openjfx;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

import java.io.Serializable;

public class Component implements Serializable {
    private SimpleStringProperty type;
    private SimpleStringProperty name;
    private SimpleIntegerProperty price;

    public Component(String type, String name, int price){
        this.type = new SimpleStringProperty(type);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleIntegerProperty(price);
    }

    @Override
    public String toString() {
        return getName();
    }

    public String getType() { return type.get(); }

    public String getName() { return name.get(); }

    public int getPrice() { return price.get(); }

    public void setType(String type) { this.type.set(type); }

    public void setName(String name) { this.name.set(name); }

    public void setPrice(int price) { this.price.set(price); }
}
