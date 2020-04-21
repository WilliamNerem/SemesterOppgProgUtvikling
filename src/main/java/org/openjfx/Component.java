package org.openjfx;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Component implements Serializable {
    private transient SimpleStringProperty type;
    private transient SimpleStringProperty name;
    private transient SimpleIntegerProperty price;

    public Component(String type, String name, int price){
        this.type = new SimpleStringProperty(type);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleIntegerProperty(price);
    }

    @Override
    public String toString() {
        if(getName().equals("Ingen")) {
            return name.get();
        }
        else return name.get()+"  "+price.get()+",-";
    }

    public String getType() { return type.get(); }

    public String getName() { return name.get(); }

    public int getPrice() { return price.get(); }

    public void setType(String type) { this.type.set(type); }

    public void setName(String name) { this.name.set(name); }

    public void setPrice(int price) { this.price.set(price); }

    private void writeObject(ObjectOutputStream out)
            throws IOException {
        out.writeUTF(getType());
        out.writeUTF(getName());
        out.writeInt(getPrice());
    }
    private void readObject(ObjectInputStream in)
            throws IOException, ClassNotFoundException {
        String type = in.readUTF();
        String name = in.readUTF();
        int price = in.readInt();

        this.type = new SimpleStringProperty();
        this.name = new SimpleStringProperty();
        this.price = new SimpleIntegerProperty();

        setType(type);
        setName(name);
        setPrice(price);
    }
}
