package org.openjfx;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;

public class AdminIndexController implements Initializable {
    ComponentRegister cr = new ComponentRegister();
    IntegerStringConverter intStrConverter = new IntegerStringConverter();


    //testdata:

    Component component1 = new Component("Monitor", "Samsung 32'' Curved skjerm C32F39M", 1995);
    Component component2 = new Component("Skjermkort", "Gigabyte GeForce RTX 2060 OC", 4599);
    Component component3 = new Component("Harddisk", "Seagate Expansion Portable 2TB", 899);
    Component component4 = new Component("Mus", "Logitech M171 Trådløs Mud Sort", 159);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cr.addComponent(component1);
        cr.addComponent(component2);
        cr.addComponent(component3);
        cr.addComponent(component4);
        cr.attachTableView(tableviewAdminIndex);

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
    void xD(DragEvent event) {
        filter();
    }

    @FXML
    void add(ActionEvent event) {
        confirmMsg.setText("");
        errorMsg.setText("");
        String inType = cbType.getValue();
        String inName = txtNewComponent.getText();
        int inPrice = 0;
        try{
            inType = TypeException.checkType(inType);
            inName = NameException.checkName(inName);
            inPrice = PriceException.checkPrice(txtNewPrice.getText());
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
            NameException.checkName(event.getNewValue());
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
                PriceException.checkPrice(event.getNewValue());
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
            String newType = TypeException.checkTypeOnchange(event.getNewValue());
            event.getRowValue().setType(newType);
            tableviewAdminIndex.refresh();
        }catch (TypeException.InvalidTypeException e){
            changeError.setText(e.getMessage());
            tableviewAdminIndex.refresh();
        }

    }


    @FXML
    void switchToPrimary(ActionEvent event) throws IOException {
        App.setRoot("primary");
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
                    final Button btn = new Button("Slett");

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
                    case "Pris":
                        int IntLCF = 0;
                        try{
                            IntLCF = Integer.parseInt(lowerCaseFilter);
                            errorMsg.setText("");
                        }
                        catch (Exception e){
                            confirmMsg.setText("");
                            errorMsg.setText("Pris må være tall høyere enn 0");
                            return false;
                        }
                        if(component.getPrice() == IntLCF){
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