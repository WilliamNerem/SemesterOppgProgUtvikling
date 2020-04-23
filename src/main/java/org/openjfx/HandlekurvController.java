package org.openjfx;

import java.io.*;
import java.nio.file.Files;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Window;
import org.openjfx.Feilhåndtering.CheckInput;
import org.openjfx.Feilhåndtering.IntegerStringConverter;
import org.openjfx.Feilhåndtering.PriceException;
import org.openjfx.Filbehandling.FormatHandlekurvArray;
import org.openjfx.Filbehandling.OpenKjopshistorikkTxt;
import javafx.util.Callback;

public class HandlekurvController {
    private ObservableList<ComponentAndAntall> kjøpshistorikkArray = FXCollections.observableArrayList();
    private ObservableList<ComponentAndAntall> handlekurvArray = FXCollections.observableArrayList();
    private int numberInHandlevogn;
    private File afile = new File("testSaveTxtUser.txt");
    IntegerStringConverter intStrConverter = new IntegerStringConverter();

    @FXML
    private TabPane tabPane = new TabPane();

    @FXML
    private Tab tab2;

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
        ButtonType buttonKjop = new ButtonType("Fortsett å handle");
        ButtonType buttonOK = new ButtonType("OK");
        if(handlekurvArray.size() > 0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION,("Ditt kjøp til " + sumPrice(handlekurvArray) +
                    " kr ble vellykket.\nGå til kjøpshistorikk for å se tidligere kjøp."),buttonKjop,buttonOK);
            alert.setTitle("Kjøp vellykket!");
            alert.setHeaderText("Kjøp vellykket!");
            //https://stackoverflow.com/questions/52472046/alerts-in-javafx-do-not-close-when-x-button-is-pressed
            Window window = alert.getDialogPane().getScene().getWindow();
            window.setOnCloseRequest(e -> alert.hide());
            Optional<ButtonType> result = alert.showAndWait();
            result.ifPresent(res->{
                if(res.equals(buttonKjop)) {
                    try {
                        App.switchToUserIndex(0);
                    } catch (IOException ignored) {
                    }
                }
            });
        }
        kjøpshistorikkArray.clear();
        kjøpshistorikkArray.addAll(handlekurvArray);
        OpenKjopshistorikkTxt.open(kjøpshistorikkArray, afile, tabPane, tab2);
        tableviewPrishistorikk.setItems(kjøpshistorikkArray);
        Files.write(afile.toPath(), FormatHandlekurvArray.formatComponents(kjøpshistorikkArray).getBytes());

        handlekurvArray.clear();
        numberInHandlevogn = 0;
        lblTotalPrice.setText("Totalpris: " + 0 + ",-");
        filter();
    }

    @FXML
    void deleteKjøpshistorikk(ActionEvent event){
       AlertKjopshistorikk alert = new AlertKjopshistorikk();
       alert.alert(kjøpshistorikkArray, afile, tableviewPrishistorikk);
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
        OpenKjopshistorikkTxt.open(kjøpshistorikkArray, afile, tabPane, tab2);
        col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_Navn.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_Pris.setCellValueFactory(new PropertyValueFactory<>("price"));
        col_Antall.setCellValueFactory(new PropertyValueFactory<>("number"));
        col_Totalt.setCellValueFactory(new PropertyValueFactory<>("total"));

        col_antall1.setCellFactory(TextFieldTableCell.forTableColumn(intStrConverter));
        tableviewPrishistorikk.setItems(kjøpshistorikkArray);
        addButtonToTable();

        filter();
    }

    @FXML
    private void amountEdited(TableColumn.CellEditEvent<ComponentAndAntall, Integer> event) {
        if(intStrConverter.isConversionSuccessful()) {
            try {
                CheckInput.checkAmount(event.getNewValue());
                event.getRowValue().setNumber(event.getNewValue());
                tableviewHandlekurv.refresh();
                lblTotalPrice.setText("Totalpris: " + sumPrice(handlekurvArray) + ",-");
            } catch (PriceException.InvalidPriceException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Feil!");
                alert.setHeaderText("Ugyldig data!");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
                tableviewHandlekurv.refresh();
            }
        }
        tableviewHandlekurv.refresh();
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