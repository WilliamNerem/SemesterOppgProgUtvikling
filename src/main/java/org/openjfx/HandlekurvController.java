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
import javafx.util.Callback;

public class HandlekurvController {
    private ObservableList<ComponentAndAntall> kjøpshistorikkArray = FXCollections.observableArrayList();
    private ObservableList<ComponentAndAntall> handlekurvArray = FXCollections.observableArrayList();
    private int numberInHandlevogn;
    private File afile = new File("testSaveTxtUser.txt");


    @FXML
    private TableView<ComponentAndAntall> tableviewHandlekurv;

    @FXML
    private TableView<ComponentAndAntall> tableviewPrishistorikk;

    @FXML
    private TextField searchHistory;

    @FXML
    private Label lblTotalPrice;

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
        numberInHandlevogn = 0;
        filter();
    }
    @FXML
    private void switchToPrimary() throws IOException {
        handlekurvArray.clear();
        App.setRoot("login");
    }

    public void saveHandlekurvArray(ObservableList<ComponentAndAntall> ol){
        handlekurvArray = ol;
        setTable();
    }

    public void setTable() {
        tableviewHandlekurv.setItems(handlekurvArray);
        col_type1.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_navn1.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_pris1.setCellValueFactory(new PropertyValueFactory<>("price"));
        col_antall1.setCellValueFactory(new PropertyValueFactory<>("number"));
        col_totalt1.setCellValueFactory(new PropertyValueFactory<>("total"));
        handlekurvArray = tableviewHandlekurv.getItems();

        lblTotalPrice.setText("Totalpris: " + sumPrice(handlekurvArray) + ",-");
    }


    @FXML
    void switchToUserIndex() throws IOException {
        for (ComponentAndAntall ca : handlekurvArray) {
            numberInHandlevogn += ca.getNumber();
        }
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
                                int c = getTableRow().getIndex();
                                handlekurvArray.remove(c);
                                lblTotalPrice.setText("Totalpris: " + sumPrice(handlekurvArray) + ",-");
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

    public int sumPrice(ObservableList<ComponentAndAntall> list){
        int sum = 0;

        for(ComponentAndAntall c : list){
            sum += c.getTotal();
        }

        return sum;
    }

}