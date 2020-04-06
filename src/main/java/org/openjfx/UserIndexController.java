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

    ObservableList<Component> skjermKort123 = FXCollections.observableArrayList();
    ObservableList<Component> harddisk123 = FXCollections.observableArrayList();
    ObservableList<Component> mus123 = FXCollections.observableArrayList();

    @FXML
    public ComboBox<Component> cmbSkjermkort;

    @FXML
    public ComboBox<Component> cmbHarddisk;

    @FXML
    private TextField txtAntallHarddisk;

    @FXML
    private TextField txtAntallSkjermkort;

    @FXML
    public ComboBox<Component>cmbMus;

    @FXML
    public ComboBox<Component> cmbTastatur;

    @FXML
    public ComboBox<Component> cmbMinne;

    @FXML
    public ComboBox<Component> cmbMotherboard;

    @FXML
    private TextField txtAntallMotherboard;

    @FXML
    private TextField txtAntallMinne;

    @FXML
    private TextField txtAntallTastatur;

    @FXML
    private TextField txtAntallMus;

    @FXML
    public ComboBox<Component> cmbMonitor;

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
        CheckArrayOfComponents aaa = new CheckArrayOfComponents();
        cmbMus.setItems(aaa.checkmus());
        cmbSkjermkort.setItems(aaa.checkSkjermkort());
        cmbHarddisk.setItems(aaa.checkHarddisk());
    }

}
