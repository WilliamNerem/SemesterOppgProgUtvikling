package org.openjfx;

import javafx.beans.property.SimpleIntegerProperty;

public class ComponentAndAntall extends Component {
    private SimpleIntegerProperty number;
    public ComponentAndAntall(String type, int number, String name, int prize) {
        super(type, name, prize);
        this.number = new SimpleIntegerProperty(number);
    }

    public int getNumber() { return number.get(); }
}
