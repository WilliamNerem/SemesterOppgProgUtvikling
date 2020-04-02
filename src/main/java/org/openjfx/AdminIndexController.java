package org.openjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AdminIndexController {

    @FXML
    private TableView<?> tableviewAdminIndex;

    @FXML
    private TextField txtNewComponent;

    @FXML
    private TextField txtNewPrice;

    @FXML
    private Button btnAddComponent;

    @FXML
    private Button logOut;

    @FXML
    private TextField txtSearchComponent;

    @FXML
    private TextField txtType;

    @FXML
    void add(ActionEvent event) {

    }

    @FXML
    void switchToPrimary(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }

}