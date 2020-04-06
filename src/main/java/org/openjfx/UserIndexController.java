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
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserIndexController implements Initializable {

    Component test1 = new Component("ingen","Ingen",1999);
    Component test2 = new Component("Mus","Wrtt",1999);
    Component test3 = new Component("Skjermkort", "Yeeeey", 1479);
    Component test4 = new Component("Harddisk", "jhsfgdjhf", 1479);
    ObservableList<Component> yeetskrt = FXCollections.observableArrayList(test1, test2, test3, test4);
    ObservableList<Component> skjermKort123 = FXCollections.observableArrayList();
    ObservableList<Component> harddisk123 = FXCollections.observableArrayList();
    ObservableList<Component> mus123 = FXCollections.observableArrayList();
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
<<<<<<< HEAD
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

=======
    void chooseHarddisk(KeyEvent event) {
        CheckArrayOfComponents checkArray = new CheckArrayOfComponents();
        for(Component c : checkArray.checkComponentAll) {
            if(cmbHarddisk.getValue().toString().equals(c.getName())) {
                lblHarddisk.setText((CalculatePrice.calcComponent(c.getPrize(),Integer.parseInt(txtAntallHarddisk.getText())))+" kr");
            }
        }
>>>>>>> master
    }

    @FXML
    void chooseMinne(KeyEvent event) {
        CheckArrayOfComponents checkArray = new CheckArrayOfComponents();
        for(Component c : checkArray.checkComponentAll) {
            if(cmbMinne.getValue().toString().equals(c.getName())) {
                lblMinne.setText((CalculatePrice.calcComponent(c.getPrize(),Integer.parseInt(txtAntallMinne.getText())))+" kr");
            }
        }

    }

    @FXML
    void chooseMonitor(KeyEvent event) {
        CheckArrayOfComponents checkArray = new CheckArrayOfComponents();
        for(Component c : checkArray.checkComponentAll) {
            if(cmbMonitor.getValue().toString().equals(c.getName())) {
                lblMonitor.setText((CalculatePrice.calcComponent(c.getPrize(),Integer.parseInt(txtAntallMonitor.getText())))+" kr");
            }
        }

    }

    @FXML
    void chooseMotherboard(KeyEvent event) {
        CheckArrayOfComponents checkArray = new CheckArrayOfComponents();
        for(Component c : checkArray.checkComponentAll) {
            if(cmbMotherboard.getValue().toString().equals(c.getName())) {
                lblMotherboard.setText((CalculatePrice.calcComponent(c.getPrize(),Integer.parseInt(txtAntallMotherboard.getText())))+" kr");
            }
        }

    }

    @FXML
    void chooseMus(KeyEvent event) {
        CheckArrayOfComponents checkArray = new CheckArrayOfComponents();
        for(Component c : checkArray.checkComponentAll) {
            if(cmbMus.getValue().toString().equals(c.getName())) {
                lblMus.setText((CalculatePrice.calcComponent(c.getPrize(),Integer.parseInt(txtAntallMus.getText())))+" kr");
            }
        }
    }

    @FXML
<<<<<<< HEAD
    void chooseSkjermkort(ActionEvent event) {
        if(CountException.check(txtAntallSkjermkort.getText()).equals("")) {
            lblSkjermkort.setText(CalculatePrice.calcComponent(oneComponent.getPrize(), Integer.parseInt(txtAntallSkjermkort.getText())));
=======
    void chooseSkjermkort(KeyEvent event) {
        CheckArrayOfComponents checkArray = new CheckArrayOfComponents();
        for(Component c : checkArray.checkComponentAll) {
            if(cmbSkjermkort.getValue().toString().equals(c.getName())) {
                lblSkjermkort.setText((CalculatePrice.calcComponent(c.getPrize(),Integer.parseInt(txtAntallSkjermkort.getText())))+" kr");
            }
>>>>>>> master
        }
    }

    @FXML
    void chooseTastatur(KeyEvent event) {
        CheckArrayOfComponents checkArray = new CheckArrayOfComponents();
        for(Component c : checkArray.checkComponentAll) {
            if(cmbTastatur.getValue().toString().equals(c.getName())) {
                lblTastatur.setText((CalculatePrice.calcComponent(c.getPrize(),Integer.parseInt(txtAntallTastatur.getText())))+" kr");
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
