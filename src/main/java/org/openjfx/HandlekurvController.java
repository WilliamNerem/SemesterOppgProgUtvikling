package org.openjfx;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

public class HandlekurvController {

    @FXML
    private Button secondaryButton;

    @FXML
    private TableView<?> tableviewHandlekurv;

    @FXML
    private Button tilbakeTilUserIndex;


    @FXML
    private Button btnKjop;

    @FXML
    void kjop(ActionEvent event) {

    }
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }


    @FXML
    void switchToUserIndex() throws IOException {
        App.setRoot("userIndex");
    }
}