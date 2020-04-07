package org.openjfx;

import java.io.*;
import java.nio.file.Files;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

public class HandlekurvController {
    ObservableList<ComponentAndAntall> kjøpshistorikkArray = FXCollections.observableArrayList();
    UserIndexController handlekurvArray = new UserIndexController();

    @FXML
    private Button secondaryButton;

    @FXML
    private TableView<Component> tableviewHandlekurv;

    @FXML
    private Button tilbakeTilUserIndex;

    @FXML
    private TableView<ComponentAndAntall> tableviewPrishistorikk;

    @FXML
    private Button btnKjop;

    @FXML
    private TextField searchHistory;

    @FXML
    private Button btnOpenHandlekurv;

    @FXML
    private Button btnSaveHandlekurv;

    @FXML
    void open(ActionEvent event) {
        kjøpshistorikkArray = FXCollections.observableArrayList();
        OpenKjøpshistorikkTxt.open(kjøpshistorikkArray);
        tableviewPrishistorikk.setItems(kjøpshistorikkArray);
    }

    @FXML
    void save(ActionEvent event) throws IOException {
        FileChooser fc = new FileChooser();
        fc.setTitle("Lagre kjøpshistorikk");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("text files","*.txt"));
        File aFile = fc.showSaveDialog(null);

        //Files.write(aFile.toPath(), FormatHandlekurvArray.formatComponents(handlekurvArray.componentsBought).getBytes());
    }

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