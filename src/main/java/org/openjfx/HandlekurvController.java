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
import org.openjfx.Filbehandling.FormatHandlekurvArray;
import org.openjfx.Filbehandling.OpenKjøpshistorikkTxt;

public class HandlekurvController {
    ObservableList<ComponentAndAntall> kjøpshistorikkArray = FXCollections.observableArrayList();
    ObservableList<ComponentAndAntall> handlekurvArray = FXCollections.observableArrayList();
    int numberInHandlevogn;
    File afile = new File("testSaveTxtUser.txt");

    @FXML
    private Button secondaryButton;

    @FXML
    private TableView<ComponentAndAntall> tableviewHandlekurv;

    @FXML
    private Button tilbakeTilUserIndex;

    @FXML
    private TableView<ComponentAndAntall> tableviewPrishistorikk;

    @FXML
    private Button btnKjop;

    @FXML
    private TextField searchHistory;

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
    void kjop(ActionEvent event) throws IOException {
        kjøpshistorikkArray.clear();
        kjøpshistorikkArray.addAll(handlekurvArray);
        OpenKjøpshistorikkTxt.open(kjøpshistorikkArray, afile);
        tableviewPrishistorikk.setItems(kjøpshistorikkArray);
        Files.write(afile.toPath(), FormatHandlekurvArray.formatComponents(kjøpshistorikkArray).getBytes());
        handlekurvArray.clear();
    }
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("login");
    }

    public void saveHandlekurvArray(ObservableList<ComponentAndAntall> ol){
        handlekurvArray = ol;
        setTable();
    }

    public void setTable() {
        col_type1.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_navn1.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_pris1.setCellValueFactory(new PropertyValueFactory<>("price"));
        col_antall1.setCellValueFactory(new PropertyValueFactory<>("number"));
        col_totalt1.setCellValueFactory(new PropertyValueFactory<>("total"));
        tableviewHandlekurv.setItems(handlekurvArray);
        handlekurvArray = tableviewHandlekurv.getItems();
        System.out.print(handlekurvArray);
        for (ComponentAndAntall ca : handlekurvArray) {
            numberInHandlevogn += ca.getNumber();
        }
        System.out.println(numberInHandlevogn);
    }


    @FXML
    void switchToUserIndex() throws IOException {
        App.switchToUserIndex(numberInHandlevogn);
    }

    @FXML
    private void initialize(){
        OpenKjøpshistorikkTxt.open(kjøpshistorikkArray, afile);
        col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_Navn.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_Pris.setCellValueFactory(new PropertyValueFactory<>("price"));
        col_Antall.setCellValueFactory(new PropertyValueFactory<>("number"));
        col_Totalt.setCellValueFactory(new PropertyValueFactory<>("total"));
        tableviewPrishistorikk.setItems(kjøpshistorikkArray);
    }


}