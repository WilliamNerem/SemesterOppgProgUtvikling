package org.openjfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class UserIndexController {

    Component test1 = new Component("ingen","Ingen",1999);
    Component test2 = new Component("Mus","Wrtt",1999);
    Component test3 = new Component("Skjermkort", "Yeeeey", 1479);
    Component test4 = new Component("Harddisk", "jhsfgdjhf", 1479);
    ObservableList<Component> yeetskrt = FXCollections.observableArrayList(test1, test2, test3, test4);
    ObservableList<Component> skjermKort123 = FXCollections.observableArrayList();
    ObservableList<Component> harddisk123 = FXCollections.observableArrayList();
    ObservableList<Component> mus123 = FXCollections.observableArrayList();

    @FXML
    private ComboBox<Component> cmbSkjermkort;

    @FXML
    private ComboBox<Component> cmbHarddisk;

    @FXML
    private TextField txtAntallHarddisk;

    @FXML
    private TextField txtAntallSkjermkort;

    @FXML
    private ComboBox<Component>cmbMus;

    @FXML
    private ComboBox<?> cmbTastatur;

    @FXML
    private ComboBox<?> cmbMinne;

    @FXML
    private ComboBox<?> cmbMotherboard;

    @FXML
    private TextField txtAntallMotherboard;

    @FXML
    private TextField txtAntallMinne;

    @FXML
    private TextField txtAntallTastatur;

    @FXML
    private TextField txtAntallMus;

    @FXML
    private ComboBox<?> cmbMonitor;

    @FXML
    private TextField txtAntallMonitor;

    @FXML
    private Label lblSkjermkort;

    @FXML
    private Label lblMonitor;

    @FXML
    private Label lblMotherboard;

    @FXML
    private Label lblMinne;

    @FXML
    private Label lblTastatur;

    @FXML
    private Label lblMus;

    @FXML
    private Label lblHarddisk;

    @FXML
    private Button handlevognKnapp;

    @FXML
    private Button userIndex;

    @FXML
    private Label lblWrongNumber;

    @FXML
    void chooseHarddisk(ActionEvent event) {

    }

    @FXML
    void chooseMinne(ActionEvent event) {

    }

    @FXML
    void chooseMonitor(ActionEvent event) {

    }

    @FXML
    void chooseMotherboard(ActionEvent event) {

    }

    @FXML
    void chooseMus(ActionEvent event) {

    }

    @FXML
    void chooseSkjermkort(ActionEvent event) {

    }

    @FXML
    void chooseTastatur(ActionEvent event) {

    }

    @FXML
    void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    void switchToHandlevogn() throws IOException {
        App.setRoot("handlekurv");
    }

    @FXML
    private void initialize(){
        for(Component c : yeetskrt) {
            if(c.getType().equals("Mus")||c.getType().equals("ingen")) {
                mus123.add(c);
                cmbMus.setItems(mus123);
            }
            if(c.getType().equals("Skjermkort")||c.getType().equals("ingen")) {
                skjermKort123.add(c);
                cmbSkjermkort.setItems(skjermKort123);
            }
            if(c.getType().equals("Harddisk")||c.getType().equals("ingen")) {
                harddisk123.add(c);
                cmbHarddisk.setItems(harddisk123);
            }
        }
    }

}
