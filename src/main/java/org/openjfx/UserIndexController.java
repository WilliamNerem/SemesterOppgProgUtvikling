package org.openjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class UserIndexController {

    @FXML
    private ComboBox<?> cmbSkjermkort;

    @FXML
    private ComboBox<?> cmbHarddisk;

    @FXML
    private TextField txtAntallHarddisk;

    @FXML
    private TextField txtAntallSkjermkort;

    @FXML
    private ComboBox<?> cmbMus;

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
    void switchToPrimary(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }

    @FXML
    void switchToHandlevogn(ActionEvent event) throws IOException {
        App.setRoot("handlekurv");
    }

}
