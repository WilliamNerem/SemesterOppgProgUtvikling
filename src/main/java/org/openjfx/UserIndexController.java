package org.openjfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import org.openjfx.Feilhåndtering.CheckInput;
import org.openjfx.Feilhåndtering.InputException;
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
    private void initialize(){
        CheckArrayOfComponents checkArray = new CheckArrayOfComponents();
        checkArray.setComponents();
        checkArray.setAll(cmbMus,cmbSkjermkort,cmbHarddisk,cmbMotherboard,cmbTastatur,cmbMinne,cmbMonitor,
                txtAntallSkjermkort,txtAntallHarddisk,txtAntallTastatur,txtAntallMotherboard,txtAntallMonitor,
                txtAntallMinne,txtAntallMus);
        System.out.println(checkArray.checkComponentAll);
    }

    @FXML
    public ComboBox<Component> cmbSkjermkort;

    @FXML
    public ComboBox<Component> cmbHarddisk;

    @FXML
    public ComboBox<Component> cmbMus;

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
    private Label lblWrong;

    @FXML
    public ComboBox<Integer> txtAntallSkjermkort;

    @FXML
    public ComboBox<Integer> txtAntallHarddisk;

    @FXML
    public ComboBox<Integer> txtAntallMinne;

    @FXML
    public ComboBox<Integer> txtAntallMonitor;

    @FXML
    public ComboBox<Integer> txtAntallTastatur;

    @FXML
    public ComboBox<Integer> txtAntallMus;

    @FXML
    public ComboBox<Integer> txtAntallMotherboard;

    @FXML
    public Label lblTotalPrice;

    @FXML
    private Label lblAntallInHandlekurv;

    @FXML
    void chooseHarddisk(ActionEvent event) {
            String output = "";
            try {
                output = CheckInput.checkUI(cmbHarddisk, txtAntallHarddisk.getValue());
                lblWrong.setText("");
                lblTotalPrice.setText(CheckInput.checkForTotalPrice(this));
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
            output = CheckInput.checkUI(cmbMinne, txtAntallMinne.getValue());
            lblWrong.setText("");
            lblTotalPrice.setText(CheckInput.checkForTotalPrice(this));
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
            output = CheckInput.checkUI(cmbMonitor, txtAntallMonitor.getValue());
            lblWrong.setText("");
            lblTotalPrice.setText(CheckInput.checkForTotalPrice(this));
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
            output = CheckInput.checkUI(cmbMotherboard, txtAntallMotherboard.getValue());
            lblWrong.setText("");
            lblTotalPrice.setText(CheckInput.checkForTotalPrice(this));
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
            output = CheckInput.checkUI(cmbMus, txtAntallMus.getValue());
            lblWrong.setText("");
            lblTotalPrice.setText(CheckInput.checkForTotalPrice(this));
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
            output = CheckInput.checkUI(cmbSkjermkort, txtAntallSkjermkort.getValue());
            lblWrong.setText("");
            lblTotalPrice.setText(CheckInput.checkForTotalPrice(this));
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
            output = CheckInput.checkUI(cmbTastatur, txtAntallTastatur.getValue());
            lblWrong.setText("");
            lblTotalPrice.setText(CheckInput.checkForTotalPrice(this));
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
        App.switchToHandlekurv(componentsBought);
    }

    @FXML
    void addToHandlekurv(ActionEvent event) throws IOException {
        componentsBought = FXCollections.observableArrayList();
        CheckArrayOfComponents checkArray = new CheckArrayOfComponents();
        checkArray.setComponents();
        for (Component c : checkArray.checkComponentAll) {
            if (lblWrong.getText().equals("") && !c.getName().equals("Ingen")) {
                try {
                    lblTastatur.setText(CheckInput.checkUI(cmbTastatur, txtAntallTastatur.getValue()));
                    lblMinne.setText(CheckInput.checkUI(cmbMinne, txtAntallMinne.getValue()));
                    lblSkjermkort.setText(CheckInput.checkUI(cmbSkjermkort, txtAntallSkjermkort.getValue()));
                    lblMonitor.setText(CheckInput.checkUI(cmbMonitor, txtAntallMonitor.getValue()));
                    lblMotherboard.setText(CheckInput.checkUI(cmbMotherboard, txtAntallMotherboard.getValue()));
                    lblHarddisk.setText(CheckInput.checkUI(cmbHarddisk, txtAntallHarddisk.getValue()));
                    lblMus.setText(CheckInput.checkUI(cmbMus, txtAntallMus.getValue()));
                } catch (InputException.InvalidInputException e) {
                    lblWrong.setText(e.getMessage());
                    return;
                }
                if (c.getName().equals(cmbHarddisk.getValue().getName())) {
                    ComponentAndAntall compBoughtHarddisk = new ComponentAndAntall(c.getType(), c.getName(), txtAntallHarddisk.getValue(), c.getPrice());
                    componentsBought.add(compBoughtHarddisk);
                }
                if (c.getName().equals(cmbSkjermkort.getValue().getName())) {
                    ComponentAndAntall compBoughtSkjermkort = new ComponentAndAntall(c.getType(), c.getName(), txtAntallSkjermkort.getValue(), c.getPrice());
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
            }
        }
        if (lblWrong.getText().equals("")) {
            App.switchToHandlekurv(componentsBought);

        }
    }

    public void addToHandlekurvNumber(int i){
        lblAntallInHandlekurv.setText("("+i+")");
    }

}
