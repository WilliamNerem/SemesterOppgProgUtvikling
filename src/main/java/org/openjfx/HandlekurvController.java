package org.openjfx;

import java.io.*;
import java.nio.file.Files;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
    private TableColumn<ComponentAndAntall, Void> col_slett1;


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
        addButtonToTable();

        filter();
    }

    private void filter(){

        //https://code.makery.ch/blog/javafx-8-tableview-sorting-filtering/
        FilteredList<ComponentAndAntall> filteredData = new FilteredList<>(kjøpshistorikkArray, c -> true);

        searchHistory.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(component -> {
                // If filter text is empty, display all components.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (component.getType().toLowerCase().startsWith(lowerCaseFilter)) {
                    return true;
                }else return component.getName().toLowerCase().startsWith((lowerCaseFilter));

                // Does not match.
            });
        });

        System.out.println(filteredData);

       SortedList<ComponentAndAntall> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(tableviewPrishistorikk.comparatorProperty());

        tableviewPrishistorikk.setItems(sortedData);

    }

    //https://stackoverflow.com/questions/29489366/how-to-add-button-in-javafx-table-view
    private void addButtonToTable() {
        Callback<TableColumn<ComponentAndAntall, Void>, TableCell<ComponentAndAntall, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell call(final TableColumn<ComponentAndAntall, Void> param) {
                final TableCell<ComponentAndAntall, Void> cell = new TableCell<>() {
                    final Button btn = new Button("x");

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            //onClick event som sletter elementet fra lista
                            btn.setOnAction(event -> {
                                System.out.println("Slettknapp klikket");
                            });

                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };
        col_slett1.setCellFactory(cellFactory);
    }

}