package org.openjfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import org.openjfx.*;
import org.openjfx.Feilhåndtering.InputException;

import java.io.IOException;

public class UserIndexController {
    public ObservableList<Component> skjermkortChoose = FXCollections.observableArrayList();
    public ObservableList<Component> harddiskChoose = FXCollections.observableArrayList();
    public ObservableList<Component> musChoose = FXCollections.observableArrayList();
    public ObservableList<Component> tastaturChoose = FXCollections.observableArrayList();
    public ObservableList<Component> minneChoose = FXCollections.observableArrayList();
    public ObservableList<Component> motherboardChoose = FXCollections.observableArrayList();
    public ObservableList<Component> monitorChoose = FXCollections.observableArrayList();
    public ObservableList<ComponentAndAntall> componentsBought = FXCollections.observableArrayList();

    @FXML
    public ComboBox<Component> cmbSkjermkort;

    @FXML
    public ComboBox<Component> cmbHarddisk;

    @FXML
    public ComboBox<Component>cmbMus;

    @FXML
    public ComboBox<Component> cmbTastatur;

    @FXML
    public ComboBox<Component> cmbMinne;

    @FXML
    public ComboBox<Component> cmbMotherboard;

    @FXML
    public ComboBox<Component> cmbMonitor;

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
    private Label lblWrong;

    @FXML
    private ComboBox<Integer> txtAntallSkjermkort;

    @FXML
    private ComboBox<Integer> txtAntallHarddisk;

    @FXML
    private ComboBox<Integer> txtAntallMinne;

    @FXML
    private ComboBox<Integer> txtAntallMonitor;

    @FXML
    private ComboBox<Integer> txtAntallTastatur;

    @FXML
    private ComboBox<Integer> txtAntallMus;

    @FXML
    private ComboBox<Integer> txtAntallMotherboard;

    @FXML
    private Label lblTotalPrice;

    @FXML
    private Label lblAntallInHandlekurv;

    @FXML
    void chooseHarddisk(ActionEvent event) {
            String output = "";
            try {
                output = InputException.checkInput(cmbHarddisk, txtAntallHarddisk.getValue());
                lblWrong.setText("");
            } catch (InputException.InvalidInputException e) {
                lblWrong.setText(e.getMessage());
                lblHarddisk.setText("");
            }
            lblHarddisk.setText(output);
    }

    @FXML
    void chooseMinne(ActionEvent event) {
        String output = "";
        try {
            output = InputException.checkInput(cmbMinne, txtAntallMinne.getValue());
            lblWrong.setText("");
        } catch (InputException.InvalidInputException e) {
            lblWrong.setText(e.getMessage());
            lblMinne.setText("");
        }
        lblMinne.setText(output);
    }

    @FXML
    void chooseMonitor(ActionEvent event) {
        String output = "";
        try {
            output = InputException.checkInput(cmbMonitor, txtAntallMonitor.getValue());
            lblWrong.setText("");
        } catch (InputException.InvalidInputException e) {
            lblWrong.setText(e.getMessage());
            lblMonitor.setText("");
        }
        lblMonitor.setText(output);
    }

    @FXML
    void chooseMotherboard(ActionEvent event) {
        String output = "";
        try {
            output = InputException.checkInput(cmbMotherboard, txtAntallMotherboard.getValue());
            lblWrong.setText("");
        } catch (InputException.InvalidInputException e) {
            lblWrong.setText(e.getMessage());
            lblMotherboard.setText("");
        }
        lblMotherboard.setText(output);
    }

    @FXML
    void chooseMus(ActionEvent event) {
        String output = "";
        try {
            output = InputException.checkInput(cmbMus, txtAntallMus.getValue());
            lblWrong.setText("");
        } catch (InputException.InvalidInputException e) {
            lblWrong.setText(e.getMessage());
            lblMus.setText("");
        }
        lblMus.setText(output);
    }

    @FXML
    void chooseSkjermkort(ActionEvent event) {
        String output = "";
        try {
            output = InputException.checkInput(cmbSkjermkort, txtAntallSkjermkort.getValue());
            lblWrong.setText("");
        } catch (InputException.InvalidInputException e) {
            lblWrong.setText(e.getMessage());
            lblSkjermkort.setText("");
        }
        lblSkjermkort.setText(output);
    }

    @FXML
    void chooseTastatur(ActionEvent event) {
        String output = "";
        try {
            lblTastatur.setText(InputException.checkInput(cmbTastatur, txtAntallTastatur.getValue()));
            lblWrong.setText("");
        } catch (InputException.InvalidInputException e) {
            lblWrong.setText(e.getMessage());
            lblTastatur.setText("");
        }
        lblTastatur.setText(output);
    }

    @FXML
    void switchToPrimary() throws IOException {
        App.setRoot("login");
    }

    @FXML
    void switchToHandlevogn() throws IOException {
        App.setRoot("handlekurv");
    }

    @FXML
    void addToHandlekurv(ActionEvent event) throws IOException {
        componentsBought = FXCollections.observableArrayList();
        CheckArrayOfComponents checkArray = new CheckArrayOfComponents();
        for (Component c : checkArray.checkComponentAll) {
            if (lblWrong.getText().equals("") && !c.getName().equals("Ingen")) {
                try {
                    lblTastatur.setText(InputException.checkInput(cmbTastatur,txtAntallTastatur.getValue()));
                    lblMinne.setText(InputException.checkInput(cmbMinne,txtAntallMinne.getValue()));
                    lblSkjermkort.setText(InputException.checkInput(cmbSkjermkort,txtAntallSkjermkort.getValue()));
                    lblMonitor.setText(InputException.checkInput(cmbMonitor,txtAntallMonitor.getValue()));
                    lblMotherboard.setText(InputException.checkInput(cmbMotherboard,txtAntallMotherboard.getValue()));
                    lblHarddisk.setText(InputException.checkInput(cmbHarddisk,txtAntallHarddisk.getValue()));
                    lblMus.setText(InputException.checkInput(cmbMus,txtAntallMus.getValue()));
                } catch (InputException.InvalidInputException e) {
                    lblWrong.setText(e.getMessage());
                    return;
                }
                if (c.getName().equals(cmbHarddisk.getValue().getName())) {
                    ComponentAndAntall compBoughtHarddisk = new ComponentAndAntall(c.getType(), c.getName(), txtAntallHarddisk.getValue(), c.getPrice());
                    componentsBought.add(compBoughtHarddisk);
                }
                if (c.getName().equals(cmbSkjermkort.getValue().getName())) {
                    ComponentAndAntall compBoughtSkjermkort = new ComponentAndAntall(c.getType(), c.getName(),txtAntallSkjermkort.getValue(), c.getPrice());
                    componentsBought.add(compBoughtSkjermkort);
                }
                if (c.getName().equals(cmbMonitor.getValue().getName())) {
                    ComponentAndAntall compBoughtMonitor = new ComponentAndAntall(c.getType(), c.getName(), txtAntallMonitor.getValue(), c.getPrice());
                    componentsBought.add(compBoughtMonitor);
                }
                if (c.getName().equals(cmbMotherboard.getValue().getName())) {
                    ComponentAndAntall compBoughtMotherboard = new ComponentAndAntall(c.getType(), c.getName(), txtAntallMotherboard.getValue(), c.getPrice());
                    componentsBought.add(compBoughtMotherboard);
                }
                if (c.getName().equals(cmbMus.getValue().getName())) {
                    ComponentAndAntall compBoughtMus = new ComponentAndAntall(c.getType(), c.getName(), txtAntallMus.getValue(), c.getPrice());
                    componentsBought.add(compBoughtMus);
                }
                if (c.getName().equals(cmbTastatur.getValue().getName())) {
                    ComponentAndAntall compBoughtTastatur = new ComponentAndAntall(c.getType(), c.getName(), txtAntallTastatur.getValue(), c.getPrice());
                    componentsBought.add(compBoughtTastatur);
                }
                if (c.getName().equals(cmbMinne.getValue().getName())) {
                    ComponentAndAntall compBoughtMinne = new ComponentAndAntall(c.getType(), c.getName(), txtAntallMinne.getValue(), c.getPrice());
                    componentsBought.add(compBoughtMinne);
                }
                App.setRoot("handlekurv");
            }
        }
        System.out.print(componentsBought);
    }
    @FXML
    private void initialize(){
        CheckArrayOfComponents checkArray = new CheckArrayOfComponents();
        HandlekurvController hkc = new HandlekurvController();
        lblAntallInHandlekurv.setText("("+hkc.numberInHandlevogn+")");
        cmbMus.setItems(checkArray.checkmus());
        cmbMus.getSelectionModel().selectFirst();
        cmbSkjermkort.setItems(checkArray.checkSkjermkort());
        cmbSkjermkort.getSelectionModel().selectFirst();
        cmbHarddisk.setItems(checkArray.checkHarddisk());
        cmbHarddisk.getSelectionModel().selectFirst();
        cmbMotherboard.setItems((checkArray.checkMotherboard()));
        cmbMotherboard.getSelectionModel().selectFirst();
        cmbTastatur.setItems((checkArray.checkTastatur()));
        cmbTastatur.getSelectionModel().selectFirst();
        cmbMinne.setItems((checkArray.checkMinne()));
        cmbMinne.getSelectionModel().selectFirst();
        cmbMonitor.setItems((checkArray.checkMonitor()));
        cmbMonitor.getSelectionModel().selectFirst();
        txtAntallSkjermkort.setItems(checkArray.antall);
        txtAntallSkjermkort.getSelectionModel().selectFirst();
        txtAntallHarddisk.setItems(checkArray.antall);
        txtAntallHarddisk.getSelectionModel().selectFirst();
        txtAntallTastatur.setItems(checkArray.antall);
        txtAntallTastatur.getSelectionModel().selectFirst();
        txtAntallMotherboard.setItems(checkArray.antall);
        txtAntallMotherboard.getSelectionModel().selectFirst();
        txtAntallMonitor.setItems(checkArray.antall);
        txtAntallMonitor.getSelectionModel().selectFirst();
        txtAntallMinne.setItems(checkArray.antall);
        txtAntallMinne.getSelectionModel().selectFirst();
        txtAntallMus.setItems(checkArray.antall);
        txtAntallMus.getSelectionModel().selectFirst();
    }

}
