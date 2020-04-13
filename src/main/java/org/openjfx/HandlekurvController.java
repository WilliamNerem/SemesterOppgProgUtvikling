package org.openjfx;

import java.io.*;
import java.nio.file.Files;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import org.openjfx.Filbehandling.FormatHandlekurvArray;
import org.openjfx.Filbehandling.OpenKjøpshistorikkTxt;

public class HandlekurvController {
    UserIndexController uic = new UserIndexController();
    ObservableList<ComponentAndAntall> kjøpshistorikkArray = FXCollections.observableArrayList();
    ObservableList<ComponentAndAntall> handlekurvArray = FXCollections.observableArrayList();

    public void handlekurvArray(ObservableList<ComponentAndAntall> ){

    }

    int numberInHandlevogn;

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
    private TableColumn<ComponentAndAntall, String> col_type1;

    @FXML
    private TableColumn<ComponentAndAntall, String> col_navn1;

    @FXML
    private TableColumn<ComponentAndAntall, Integer> col_antall1;

    @FXML
    private TableColumn<ComponentAndAntall, Integer> col_pris1;

    @FXML
    private TableColumn<ComponentAndAntall, Integer> col_totalt1;

    @FXML
    private TableColumn<?, ?> col_slett1;


    @FXML
    private TableColumn<ComponentAndAntall, String> col_type;

    @FXML
    private TableColumn<ComponentAndAntall, String> col_Navn;

    @FXML
    private TableColumn<ComponentAndAntall, Integer> col_Antall;

    @FXML
    private TableColumn<ComponentAndAntall, Integer> col_Pris;

    @FXML
    private TableColumn<ComponentAndAntall, Integer> col_Totalt;

    @FXML
    void open(ActionEvent event) {
        kjøpshistorikkArray = FXCollections.observableArrayList();
        OpenKjøpshistorikkTxt.open(kjøpshistorikkArray);
        col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_Navn.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_Pris.setCellValueFactory(new PropertyValueFactory<>("price"));
        col_Antall.setCellValueFactory(new PropertyValueFactory<>("number"));
        col_Totalt.setCellValueFactory(new PropertyValueFactory<>("total"));
        tableviewPrishistorikk.setItems(kjøpshistorikkArray);
    }

    @FXML
    void save(ActionEvent event) throws IOException {
        FileChooser fc = new FileChooser();
        fc.setTitle("Lagre kjøpshistorikk");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("text files","*.txt"));
        File aFile = fc.showSaveDialog(null);

        Files.write(aFile.toPath(), FormatHandlekurvArray.formatComponents(kjøpshistorikkArray).getBytes());
    }

    @FXML
    void kjop(ActionEvent event) {

    }
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("login");
    }


    @FXML
    void switchToUserIndex() throws IOException {
        App.setRoot("userIndex");
    }

    @FXML
    private void initialize(){
        kjøpshistorikkArray = uic.componentsBought;
        for (ComponentAndAntall ca : uic.componentsBought) {
            numberInHandlevogn += ca.getNumber();
        }
        col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_Navn.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_Pris.setCellValueFactory(new PropertyValueFactory<>("price"));
        col_Antall.setCellValueFactory(new PropertyValueFactory<>("number"));
        col_Totalt.setCellValueFactory(new PropertyValueFactory<>("total"));
        tableviewPrishistorikk.setItems(kjøpshistorikkArray);

        col_type1.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_navn1.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_pris1.setCellValueFactory(new PropertyValueFactory<>("price"));
        col_antall1.setCellValueFactory(new PropertyValueFactory<>("number"));
        col_totalt1.setCellValueFactory(new PropertyValueFactory<>("total"));
        tableviewPrishistorikk.setItems(kjøpshistorikkArray);
    }


}