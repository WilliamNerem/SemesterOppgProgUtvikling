package org.openjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

        typeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        priceColumn.setCellFactory(TextFieldTableCell.forTableColumn(intStrConverter));
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
    private TextField txtNewComponent;

    @FXML
    private TextField txtNewPrice;

    @FXML
    private ComboBox<String> cbType;

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
        }catch (PriceException.InvalidPriceException e){
            errorMsg.setText(e.getMessage());
            return;
        }catch (TypeException.InvalidTypeException e){
            errorMsg.setText(e.getMessage());
            return;
        }catch (NameException.InvalidNameException e){
            errorMsg.setText(e.getMessage());
            return;
        }

        Component newComponent = new Component(inType, inName, inPrice);
        cr.addComponent(newComponent);
        System.out.println("Type: " + newComponent.getType() + "\nNavn: "
                + newComponent.getName() + "\nPris: " + newComponent.getPrice());
        confirmMsg.setText("Komponent lagt til");
        resetTextFields();

        cr.attachTableView(tableviewAdminIndex);

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
        if(intStrConverter)
        try {
            PriceException.checkPrice(event.getNewValue());
            event.getRowValue().setPrice(event.getNewValue());
            tableviewAdminIndex.refresh();
        }catch (PriceException.InvalidPriceException e){
            changeError.setText(e.getMessage());
            tableviewAdminIndex.refresh();
        }
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

}