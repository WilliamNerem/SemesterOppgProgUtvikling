package org.openjfx;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class HandlekurvController {

    @FXML
    private Button secondaryButton;

    @FXML
    private TableView<Component> tableviewHandlekurv;

    @FXML
    private Button tilbakeTilUserIndex;

    @FXML
    private TableView<Component> tableviewPrishistorikk;

    @FXML
    private Button btnKjop;

    @FXML
    private TextField searchHistory;

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