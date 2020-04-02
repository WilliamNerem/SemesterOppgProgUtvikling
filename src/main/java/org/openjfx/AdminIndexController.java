package org.openjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class AdminIndexController {
    ComponentRegister cr = new ComponentRegister();

    @FXML
    private TableView<?> tableviewAdminIndex;

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
    void switchToPrimary(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }

}