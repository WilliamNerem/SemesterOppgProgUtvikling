package org.openjfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import org.openjfx.Filbehandling.OpenAdminTableview;

import java.io.File;

public class CheckArrayOfComponents {
    UserIndexController uic = new UserIndexController();
    ObservableList <Component> mus = uic.musChoose;
    ObservableList <Component> skjermkort = uic.skjermkortChoose;
    ObservableList <Component> harddisk = uic.harddiskChoose;
    ObservableList <Component> tastatur = uic.tastaturChoose;
    ObservableList <Component> minne = uic.minneChoose;
    ObservableList <Component> monitor = uic.monitorChoose;
    ObservableList <Component> motherboard = uic.motherboardChoose;
    Component i = new Component("Ingen", "Ingen", 0);
    ObservableList<Component> checkComponentAll = FXCollections.observableArrayList();
    ObservableList<Integer> antall = FXCollections.observableArrayList(0,1,2,3,4,5,6,7,8,9);

    public void setComponents(){
        checkComponentAll.add(i);
        ComponentRegister cr = new ComponentRegister();
        File f = new File("adminDummy.jobj");
        OpenAdminTableview oat = new OpenAdminTableview();
        try{
            oat.openDefault(f, cr);
        }catch (Exception ignored){}

        checkComponentAll.addAll(cr.getComponents());

    }

    public void setAll(ComboBox<Component> cmbMus, ComboBox<Component> cmbSkjermkort,
                       ComboBox<Component> cmbHarddisk, ComboBox<Component> cmbMotherboard,
                       ComboBox<Component> cmbTastatur, ComboBox<Component> cmbMinne,
                       ComboBox<Component> cmbMonitor, ComboBox<Integer> txtAntallSkjermkort,
                       ComboBox<Integer> txtAntallHarddisk, ComboBox<Integer> txtAntallTastatur,
                       ComboBox<Integer> txtAntallMotherboard, ComboBox<Integer> txtAntallMonitor,
                       ComboBox<Integer> txtAntallMinne, ComboBox<Integer> txtAntallMus) {
        cmbMus.setItems(checkmus());
        cmbMus.getSelectionModel().selectFirst();
        cmbSkjermkort.setItems(checkSkjermkort());
        cmbSkjermkort.getSelectionModel().selectFirst();
        cmbHarddisk.setItems(checkHarddisk());
        cmbHarddisk.getSelectionModel().selectFirst();
        cmbMotherboard.setItems((checkMotherboard()));
        cmbMotherboard.getSelectionModel().selectFirst();
        cmbTastatur.setItems((checkTastatur()));
        cmbTastatur.getSelectionModel().selectFirst();
        cmbMinne.setItems((checkMinne()));
        cmbMinne.getSelectionModel().selectFirst();
        cmbMonitor.setItems((checkMonitor()));
        cmbMonitor.getSelectionModel().selectFirst();
        txtAntallSkjermkort.setItems(antall);
        txtAntallSkjermkort.getSelectionModel().selectFirst();
        txtAntallHarddisk.setItems(antall);
        txtAntallHarddisk.getSelectionModel().selectFirst();
        txtAntallTastatur.setItems(antall);
        txtAntallTastatur.getSelectionModel().selectFirst();
        txtAntallMotherboard.setItems(antall);
        txtAntallMotherboard.getSelectionModel().selectFirst();
        txtAntallMonitor.setItems(antall);
        txtAntallMonitor.getSelectionModel().selectFirst();
        txtAntallMinne.setItems(antall);
        txtAntallMinne.getSelectionModel().selectFirst();
        txtAntallMus.setItems(antall);
        txtAntallMus.getSelectionModel().selectFirst();
    }





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
