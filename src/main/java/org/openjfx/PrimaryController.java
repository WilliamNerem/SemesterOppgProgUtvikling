package org.openjfx;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToUserIndex() throws IOException {
        App.setRoot("userIndex");
    }

    @FXML
    private void switchToThird() throws IOException {
        App.setRoot("adminIndex");
    }

}
