package org.openjfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class CheckArrayOfComponents {
    UserIndexController uic = new UserIndexController();
    /*
    ComboBox <Component> cmbMus1 = check1.cmbMus;
    ComboBox <Component> cmbSkjermkort1 = check1.cmbSkjermkort;
    ComboBox <Component> cmbHarddisk1 = check1.cmbHarddisk;
    */
    ObservableList <Component> mus = uic.musChoose;
    ObservableList <Component> skjermkort = uic.skjermkortChoose;
    ObservableList <Component> harddisk = uic.harddiskChoose;
    ObservableList <Component> tastatur = uic.tastaturChoose;
    ObservableList <Component> minne = uic.minneChoose;
    ObservableList <Component> monitor = uic.monitorChoose;
    ObservableList <Component> motherboard = uic.motherboardChoose;

    Component test1 = new Component("Ingen","Ingen",0);
    Component test2 = new Component("Mus","EnMus",1999);
    Component test3 = new Component("Skjermkort", "EtSkjermkort", 1479);
    Component test4 = new Component("Harddisk", "EnHarddisk", 1479);
    Component test5 = new Component("Tastatur", "EtTastatur", 2500);
    Component test6 = new Component("Minne", "EtMinne", 599);
    Component test7 = new Component("Monitor", "EnMonitor", 2900);
    Component test8 = new Component("Motherboard", "EtMotherboard", 1450);

    ObservableList<Component> checkComponentAll = FXCollections.observableArrayList(test1, test2, test3, test4, test5, test6, test7, test8);
    ObservableList<Integer> antall = FXCollections.observableArrayList(0,1,2,3,4,5,6,7,8,9);

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
        for(Component c : checkComponentAll) {
            if(c.getType().equals("Mus")||c.getType().equals("Ingen")) {
                mus.add(c);
            }
        }
        return mus;
    }

    public ObservableList<Component> checkSkjermkort(){
        for(Component c : checkComponentAll) {
            if(c.getType().equals("Skjermkort")||c.getType().equals("Ingen")) {
                skjermkort.add(c);
            }
        }
        return skjermkort;
    }

    public ObservableList<Component> checkHarddisk(){
        for(Component c : checkComponentAll) {
            if(c.getType().equals("Harddisk")||c.getType().equals("Ingen")) {
                harddisk.add(c);
            }
        }
        return harddisk;
    }

    public ObservableList<Component> checkTastatur(){
        for(Component c : checkComponentAll) {
            if(c.getType().equals("Tastatur")||c.getType().equals("Ingen")) {
                tastatur.add(c);
            }
        }
        return tastatur;
    }

    public ObservableList<Component> checkMotherboard(){
        for(Component c : checkComponentAll) {
            if(c.getType().equals("Motherboard")||c.getType().equals("Ingen")) {
                motherboard.add(c);
            }
        }
        return motherboard;
    }

    public ObservableList<Component> checkMinne(){
        for(Component c : checkComponentAll) {
            if(c.getType().equals("Minne")||c.getType().equals("Ingen")) {
                minne.add(c);
            }
        }
        return minne;
    }

    public ObservableList<Component> checkMonitor(){
        for(Component c : checkComponentAll) {
            if(c.getType().equals("Monitor")||c.getType().equals("Ingen")) {
                monitor.add(c);
            }
        }
        return monitor;
    }


}
