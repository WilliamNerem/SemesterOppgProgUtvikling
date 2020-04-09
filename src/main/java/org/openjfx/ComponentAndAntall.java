package org.openjfx;

import javafx.beans.property.SimpleIntegerProperty;

public class ComponentAndAntall extends Component {
    private SimpleIntegerProperty number;
    public ComponentAndAntall(String type, String name, int number, int prize) {
        super(type, name, prize);
        this.number = new SimpleIntegerProperty(number);
    }

    public int getNumber() { return number.get(); }
    public int getTotal() {
        int ut = getNumber()*getPrice();
        return ut;}
}
