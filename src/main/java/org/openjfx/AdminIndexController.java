package org.openjfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class AdminIndexController {


    ComponentRegister cr = new ComponentRegister();
    ObservableList<Component> adminArray = FXCollections.observableArrayList();
    Component a = new Component("a", "b", 1);

    @FXML
    private TableView<Component> tableviewAdminIndex;

    @FXML
    private TableColumn<Component, String> col_Type;

    @FXML
    private TableColumn<Component, String> col_Name;

    @FXML
    private TableColumn<Component, Integer> col_Price;

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
    private Button btnOpen;

    @FXML
    private Button btnSave;

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
                + newComponent.getName() + "\nPris: " + newComponent.getPrize());
        confirmMsg.setText("Komponent lagt til");
        cbType.setValue("");
        txtNewComponent.setText("");
        txtNewPrice.setText("");

    }

    @FXML
    void open(ActionEvent event) throws InterruptedException {
        adminArray = FXCollections.observableArrayList();
        OpenAdminTableview.open(adminArray, btnOpen, btnSave);
        col_Type.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_Price.setCellValueFactory(new PropertyValueFactory<>("prize"));
        tableviewAdminIndex.setItems(adminArray);
    }

    @FXML
    void save(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Lagre Komponenter");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("binary files","*.jobj"));
        File aFile = fc.showSaveDialog(null);
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(new File(aFile.getPath())));
            os.writeObject(FormatAdminArray.formatComponents(adminArray));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void switchToPrimary(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }

    /*
    @FXML
    private void initialize(){
        adminArray.add(a);
        col_Type.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_Price.setCellValueFactory(new PropertyValueFactory<>("prize"));
        tableviewAdminIndex.setItems(adminArray);
    }

     */

}