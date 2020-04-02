package org.openjfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserIndexController implements Initializable {

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
    public void initialize(URL url, ResourceBundle resourceBundle) {
        actualRegister.addComponent(oneComponent);
        actualRegister.addComponent(twoComponent);
        ObservableList<Component> components = FXCollections.observableArrayList();
        components.add(oneComponent);
        components.add(twoComponent);
        cmbSkjermkort.valueProperty();
        componentcmb.setItems(components);
        cmbSkjermkort.setItems();
    }

    ComboBox<Component> componentcmb = new ComboBox<>();
    ComponentRegister actualRegister = new ComponentRegister();
    Component oneComponent = new Component("Skjermkort","ASUS ROG",1900);
    Component twoComponent = new Component("Skjermkort","GEFORCE GTX 1080", 3500);

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
        if(CountException.check(txtAntallSkjermkort.getText()).equals("")) {
            lblSkjermkort.setText(CalculatePrice.calcComponent(oneComponent.getPrize(), Integer.parseInt(txtAntallSkjermkort.getText())));
        }
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

}
