package org.openjfx;

import javafx.beans.property.SimpleIntegerProperty;

public class ComponentAndAntall extends Component {
    private SimpleIntegerProperty number;
    private SimpleIntegerProperty total;
    public ComponentAndAntall(String type, String name, int number, int prize, int total) {
        super(type, name, prize);
        this.number = new SimpleIntegerProperty(number);
        this.total = new SimpleIntegerProperty(total);
    }

    public int getNumber() { return number.get(); }
    public int getTotal() {
        int ut = getNumber()*getPrize();
        return ut;}
}
