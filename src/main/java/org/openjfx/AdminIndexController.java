package org.openjfx;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Spliterator;

import javafx.util.Callback;
import org.openjfx.Feilhåndtering.NameException;
import org.openjfx.Feilhåndtering.PriceException;
import org.openjfx.Feilhåndtering.CheckInput;
import org.openjfx.Feilhåndtering.TypeException;
import org.openjfx.Filbehandling.FormatAdminArray;
import org.openjfx.Filbehandling.OpenAdminTableview;
import org.openjfx.Filbehandling.SaveAdminTableview;

public class AdminIndexController implements Initializable {
    ComponentRegister cr = new ComponentRegister();
    IntegerStringConverter intStrConverter = new IntegerStringConverter();
    File fLbl = new File("StandardFileLbl.jobj");
    File f = new File("StandardFile.jobj");
    OpenAdminTableview oat = new OpenAdminTableview();
    SaveAdminTableview save = new SaveAdminTableview();
    OpenAdminTableview open = new OpenAdminTableview();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            open.setLbl(lblStandardFile, fLbl);
        } catch (Exception ignored) {}

        try{
            oat.openDefault(f, cr);
        }catch (Exception ignored){}

        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        typeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        priceColumn.setCellFactory(TextFieldTableCell.forTableColumn(intStrConverter));

        addButtonToTable();

        chBox.getSelectionModel().selectFirst();

        filter();

    }
    @FXML
    private AnchorPane anchorpane;

    @FXML
    private TableView tableviewAdminIndex;

    @FXML
    private TableColumn<Component, String> typeColumn;

    @FXML
    private TableColumn<Component, String> nameColumn;

    @FXML
    private TableColumn<Component, Integer> priceColumn;

    @FXML
    private TableColumn<Component, Void> deleteColumn;

    @FXML
    private TextField txtNewComponent;

    @FXML
    private TextField txtNewPrice;

    @FXML
    private ComboBox<String> cbType;

    @FXML
    private ChoiceBox<String> chBox;

    @FXML
    private Button btnAddComponent;

    @FXML
    private Button logOut;

    @FXML
    private TextField txtSearchComponent;

    @FXML
    private Label errorMsg;

    @FXML
    private Label confirmMsg;

    @FXML
    private Label changeError;

    @FXML
    private Label lblStandardFile;

    @FXML
    private Button btnOpen;

    @FXML
    private Button btnSave;

    @FXML
    private MenuItem quick_save;

    @FXML
    private MenuItem save_as;

    @FXML
    void add(ActionEvent event) {
        confirmMsg.setText("");
        errorMsg.setText("");
        String inType = cbType.getValue();
        String inName = txtNewComponent.getText();
        int inPrice = 0;
        try{
            inType = CheckInput.checkType(inType);
            inName = CheckInput.checkName(inName);
            inPrice = CheckInput.checkPrice(txtNewPrice.getText());
        }catch (PriceException.InvalidPriceException | TypeException.InvalidTypeException | NameException.InvalidNameException e){
            errorMsg.setText(e.getMessage());
            return;
        }

        Component newComponent = new Component(inType, inName, inPrice);
        cr.addComponent(newComponent);
        System.out.println("Type: " + newComponent.getType() + "\nNavn: "
                + newComponent.getName() + "\nPris: " + newComponent.getPrice());
        errorMsg.setText("");
        confirmMsg.setText("Komponent lagt til");
        resetTextFields();

        cr.attachTableView(tableviewAdminIndex);

        filter();

    }

    @FXML
    private void nameEdited(TableColumn.CellEditEvent<Component, String> event) {
        changeError.setText("");
        try{
            CheckInput.checkName(event.getNewValue());
            event.getRowValue().setName(event.getNewValue());
            tableviewAdminIndex.refresh();
        }catch (NameException.InvalidNameException e){
            changeError.setText(e.getMessage());
            tableviewAdminIndex.refresh();
        }
    }

    @FXML
    private void priceEdited(TableColumn.CellEditEvent<Component, Integer> event) {
        changeError.setText("");
        if(intStrConverter.isConversionSuccessful()) {
            try {
                CheckInput.checkPrice(event.getNewValue());
                event.getRowValue().setPrice(event.getNewValue());
                tableviewAdminIndex.refresh();
            } catch (PriceException.InvalidPriceException e) {
                changeError.setText(e.getMessage());
                tableviewAdminIndex.refresh();
            }
        }
        tableviewAdminIndex.refresh();
    }

    @FXML
    private void typeEdited(TableColumn.CellEditEvent<Component, String> event) {
        changeError.setText("");
        try {
            String newType = CheckInput.checkTypeOnchange(event.getNewValue());
            event.getRowValue().setType(newType);
            tableviewAdminIndex.refresh();
        }catch (TypeException.InvalidTypeException e){
            changeError.setText(e.getMessage());
            tableviewAdminIndex.refresh();
        }

    }


    @FXML
    void open(ActionEvent event) throws InterruptedException, IOException {
        open.open(cr, anchorpane, errorMsg, confirmMsg);
        filter();
    }

    @FXML
    void save(ActionEvent event) throws IOException, InterruptedException {
        save.save(cr, anchorpane, errorMsg, confirmMsg);
    }

    @FXML
    void quickSave(ActionEvent event) throws IOException {
        File selectedFile = open.getSelectedFile();
        save.quickSave(cr, selectedFile, confirmMsg, errorMsg);
    }

    @FXML
    void switchToPrimary(ActionEvent event) throws IOException {
        App.setRoot("login");
    }

    @FXML
    void changeStandardFile(ActionEvent event) throws IOException, ClassNotFoundException {
        f = open.openStandardFile();
        try(InputStream fin = Files.newInputStream(f.toPath());
            ObjectInputStream oin = new ObjectInputStream(fin)) {
            ComponentRegister register = (ComponentRegister) oin.readObject();
            save.saveStartup(register, f.toPath().toString());
            open.setLbl(lblStandardFile, fLbl);
            confirmMsg.setText("Standardfil for sluttbruker endret");
        }catch (ClassNotFoundException | IOException | ClassCastException e){
            errorMsg.setText("Noe er galt med filen");
        }catch (RuntimeException e){
        }
    }

    private void resetTextFields(){
        cbType.setValue("");
        txtNewComponent.setText("");
        txtNewPrice.setText("");
    }

    //https://stackoverflow.com/questions/29489366/how-to-add-button-in-javafx-table-view
    private void addButtonToTable() {
        Callback<TableColumn<Component, Void>, TableCell<Component, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell call(final TableColumn<Component, Void> param) {
                final TableCell<Component, Void> cell = new TableCell<>() {
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
                                cr.deleteComponent(c);
                                tableviewAdminIndex.refresh();
                            });

                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };
        deleteColumn.setCellFactory(cellFactory);
    }

    private void filter(){

        //https://code.makery.ch/blog/javafx-8-tableview-sorting-filtering/
        FilteredList<Component> filteredData = new FilteredList<>(cr.getComponents(), c -> true);

        txtSearchComponent.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(component -> {
                // If filter text is empty, display all components.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();
                String category = chBox.getValue();

                System.out.println(component.getType().toLowerCase().contains(lowerCaseFilter));

                switch (category){
                    case "Type":
                        if (component.getType().toLowerCase().startsWith(lowerCaseFilter)) {
                            System.out.println("Matcher");
                            System.out.println(filteredData);
                            return true;
                        }
                        break;
                    case "Pris (min)":
                        int IntLCMin = 0;
                        try{
                            IntLCMin = Integer.parseInt(lowerCaseFilter);
                            errorMsg.setText("");
                        }
                        catch (Exception e){
                            confirmMsg.setText("");
                            errorMsg.setText("Pris må være tall høyere enn 0");
                            return false;
                        }
                        if(component.getPrice() >= IntLCMin){
                            return true;
                        }
                        break;
                    case "Pris (maks)":
                        int IntLCMaks = 0;
                        try{
                            IntLCMaks = Integer.parseInt(lowerCaseFilter);
                            errorMsg.setText("");
                        }
                        catch (Exception e){
                            confirmMsg.setText("");
                            errorMsg.setText("Pris må være tall høyere enn 0");
                            return false;
                        }
                        if(component.getPrice() <= IntLCMaks){
                            return true;
                        }
                        break;
                    case "Navn":
                        if (component.getName().toLowerCase().startsWith(lowerCaseFilter)) {
                            return true;
                        }
                        break;
                }
                return false; // Does not match.
            });
        });

        System.out.println(filteredData);

        SortedList<Component> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(tableviewAdminIndex.comparatorProperty());

        tableviewAdminIndex.setItems(sortedData);
    }

}