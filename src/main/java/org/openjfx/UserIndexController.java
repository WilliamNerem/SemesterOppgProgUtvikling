package org.openjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class UserIndexController {

    @FXML
    private TextField txtAntallHarddisk;

    @FXML
    private TextField txtAntallSkjermkort;

    @FXML
    private TextField txtAntallMotherboard;

    @FXML
    private TextField txtAntallMinne;

    @FXML
    private TextField txtAntallTastatur;

    @FXML
    private TextField txtAntallMus;

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
    private Button userIndex;

    @FXML
    void switchToPrimary(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }
    @FXML
    void switchToHandlevogn(ActionEvent event) throws IOException {
        App.setRoot("secondary");
    }

}
