package org.openjfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

public class UserIndexController {
    ObservableList<Component> skjermkortChoose = FXCollections.observableArrayList();
    ObservableList<Component> harddiskChoose = FXCollections.observableArrayList();
    ObservableList<Component> musChoose = FXCollections.observableArrayList();
    ObservableList<Component> tastaturChoose = FXCollections.observableArrayList();
    ObservableList<Component> minneChoose = FXCollections.observableArrayList();
    ObservableList<Component> motherboardChoose = FXCollections.observableArrayList();
    ObservableList<Component> monitorChoose = FXCollections.observableArrayList();
    ObservableList<Component> componentsBought = FXCollections.observableArrayList();

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
    void chooseHarddisk(KeyEvent event) {
        CheckArrayOfComponents checkArray = new CheckArrayOfComponents();
        for(Component c : checkArray.checkComponentAll) {
            if(cmbHarddisk.getValue().toString().equals(c.getName())) {
                lblHarddisk.setText((CalculatePrice.calcComponent(c.getPrice(),Integer.parseInt(txtAntallHarddisk.getText())))+" kr");
            }
        }
    }

    @FXML
    void chooseMinne(KeyEvent event) {
        CheckArrayOfComponents checkArray = new CheckArrayOfComponents();
        for(Component c : checkArray.checkComponentAll) {
            if(cmbMinne.getValue().toString().equals(c.getName())) {
                lblMinne.setText((CalculatePrice.calcComponent(c.getPrice(),Integer.parseInt(txtAntallMinne.getText())))+" kr");
            }
        }

    }

    @FXML
    void chooseMonitor(KeyEvent event) {
        CheckArrayOfComponents checkArray = new CheckArrayOfComponents();
        for(Component c : checkArray.checkComponentAll) {
            if(cmbMonitor.getValue().toString().equals(c.getName())) {
                lblMonitor.setText((CalculatePrice.calcComponent(c.getPrice(),Integer.parseInt(txtAntallMonitor.getText())))+" kr");
            }
        }

    }

    @FXML
    void chooseMotherboard(KeyEvent event) {
        CheckArrayOfComponents checkArray = new CheckArrayOfComponents();
        for(Component c : checkArray.checkComponentAll) {
            if(cmbMotherboard.getValue().toString().equals(c.getName())) {
                lblMotherboard.setText((CalculatePrice.calcComponent(c.getPrice(),Integer.parseInt(txtAntallMotherboard.getText())))+" kr");
            }
        }

    }

    @FXML
    void chooseMus(KeyEvent event) {
        CheckArrayOfComponents checkArray = new CheckArrayOfComponents();
        for(Component c : checkArray.checkComponentAll) {
            if(cmbMus.getValue().toString().equals(c.getName())) {
                lblMus.setText((CalculatePrice.calcComponent(c.getPrice(),Integer.parseInt(txtAntallMus.getText())))+" kr");
            }
        }
    }

    @FXML
    void chooseSkjermkort(KeyEvent event) {
        CheckArrayOfComponents checkArray = new CheckArrayOfComponents();
        for(Component c : checkArray.checkComponentAll) {
            if(cmbSkjermkort.getValue().toString().equals(c.getName())) {
                lblSkjermkort.setText((CalculatePrice.calcComponent(c.getPrice(),Integer.parseInt(txtAntallSkjermkort.getText())))+" kr");
            }
        }
    }

    @FXML
    void chooseTastatur(KeyEvent event) {
        CheckArrayOfComponents checkArray = new CheckArrayOfComponents();
        for(Component c : checkArray.checkComponentAll) {
            if(cmbTastatur.getValue().toString().equals(c.getName())) {
                lblTastatur.setText((CalculatePrice.calcComponent(c.getPrice(),Integer.parseInt(txtAntallTastatur.getText())))+" kr");
            }
        }
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
    void addToHandlekurv(ActionEvent event) throws IOException {
        componentsBought = FXCollections.observableArrayList();
        CheckArrayOfComponents checkArray = new CheckArrayOfComponents();
        if(lblWrongNumber.getText().equals("")) {
            for(Component c : checkArray.checkComponentAll) { {
                if (c.getName().equals(cmbHarddisk.getValue().toString()) || c.getName().equals((cmbSkjermkort.getValue().toString())) || c.getName().equals(cmbMus.getValue().toString())) {
                    componentsBought.add(c);
                }
            }
            }
        }
        System.out.print(componentsBought);
        App.setRoot("handlekurv");
    }
    @FXML
    private void initialize(){
        CheckArrayOfComponents checkArray = new CheckArrayOfComponents();
        cmbMus.setItems(checkArray.checkmus());
        cmbSkjermkort.setItems(checkArray.checkSkjermkort());
        cmbHarddisk.setItems(checkArray.checkHarddisk());
        cmbMotherboard.setItems((checkArray.checkMotherboard()));
        cmbTastatur.setItems((checkArray.checkTastatur()));
        cmbMinne.setItems((checkArray.checkMinne()));
        cmbMonitor.setItems((checkArray.checkMonitor()));
    }

}
