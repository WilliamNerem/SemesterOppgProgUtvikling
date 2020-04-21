package org.openjfx;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Label errorMsg;

    @FXML
    private void switchToUserIndex() throws IOException {
        App.setRoot("userIndex");
    }

    @FXML
    private void switchToThird() throws IOException {
        if(username.getText().equals("sensor") && password.getText().equals("Sensor123")){
            App.setRoot("adminIndex");
        }else {
            errorMsg.setText("Brukernavn eller passord er feil");
        }

    }

}
