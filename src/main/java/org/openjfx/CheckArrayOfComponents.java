package org.openjfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class CheckArrayOfComponents {
    UserIndexController check1 = new UserIndexController();
    /*
    ComboBox <Component> cmbMus1 = check1.cmbMus;
    ComboBox <Component> cmbSkjermkort1 = check1.cmbSkjermkort;
    ComboBox <Component> cmbHarddisk1 = check1.cmbHarddisk;
    */
    ObservableList <Component> mus = check1.mus123;
    ObservableList <Component> skjermKort = check1.skjermKort123;
    ObservableList <Component> harddisk = check1.harddisk123;


    Component test1 = new Component("ingen","Ingen",1999);
    Component test2 = new Component("Mus","Wrtt",1999);
    Component test3 = new Component("Skjermkort", "Yeeeey", 1479);
    Component test4 = new Component("Harddisk", "jhsfgdjhf", 1479);
    ObservableList<Component> checkComponent = FXCollections.observableArrayList(test1, test2, test3, test4);

    /*                                                                              hvis jeg bruker den kommenterte koden fungerer ikke programmet, det blir nullpointer exception
                                                                                    tror det er fordi når "cmbMus1.setItems(mus);" og de andre prøver å legge til en UserIndexController
                                                                                    av en eller annen grunn...
                                                                                    den andre koden fungerer fint, men det er litt mer rotete og mer kode i UserIndexController.
    public void check() {

        for(Component c : checkComponent) {
            if(c.getType().equals("Mus")||c.getType().equals("ingen")) {
                mus.add(c);
            }
            if(c.getType().equals("Skjermkort")||c.getType().equals("ingen")) {
                skjermKort.add(c);
            }
            if(c.getType().equals("Harddisk")||c.getType().equals("ingen")) {
                harddisk.add(c);
            }
        }

        cmbMus1.setItems(mus);
        cmbSkjermkort1.setItems(skjermKort);
        cmbHarddisk1.setItems(harddisk);
    }
     */

    public ObservableList<Component> checkmus(){
        for(Component c : checkComponent) {
            if(c.getType().equals("Mus")||c.getType().equals("ingen")) {
                mus.add(c);
            }
        }
        return mus;
    }

    public ObservableList<Component> checkSkjermkort(){
        for(Component c : checkComponent) {
            if(c.getType().equals("Skjermkort")||c.getType().equals("ingen")) {
                skjermKort.add(c);
            }
        }
        return skjermKort;
    }

    public ObservableList<Component> checkHarddisk(){
        for(Component c : checkComponent) {
            if(c.getType().equals("Harddisk")||c.getType().equals("ingen")) {
                harddisk.add(c);
            }
        }
        return harddisk;
    }

}
