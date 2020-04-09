package org.openjfx;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

import java.io.Serializable;

public class Component implements Serializable {
    private SimpleStringProperty type;
    private SimpleStringProperty name;
    private SimpleIntegerProperty prize;

    public Component(String type, String name, int prize){
        this.type = new SimpleStringProperty(type);
        this.name = new SimpleStringProperty(name);
        this.prize = new SimpleIntegerProperty(prize);
    }

    @Override
    public String toString() {
        return getName();
    }

    public String getType() { return type.get(); }

    public String getName() { return name.get(); }

    public int getPrize() { return prize.get(); }

}
