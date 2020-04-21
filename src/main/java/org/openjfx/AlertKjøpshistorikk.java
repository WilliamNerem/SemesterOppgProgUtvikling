package org.openjfx;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.stage.Window;
import org.openjfx.Filbehandling.FormatHandlekurvArray;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

public class AlertKjøpshistorikk {
    public void alert(ObservableList<ComponentAndAntall> kjøpshistorikkArray, File afile, TableView<ComponentAndAntall> tableviewPrishistorikk){
        ButtonType buttonJa = new ButtonType("Ja");
        ButtonType buttonNei = new ButtonType("Nei");
        if(kjøpshistorikkArray.size() > 0){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,("Er du sikker på at du vil slette kjøpshistorikk?"), buttonJa, buttonNei);
            alert.setTitle("Slett kjøpshistorikk");
            alert.setHeaderText("Slett kjøpshistorikk");
            //https://stackoverflow.com/questions/52472046/alerts-in-javafx-do-not-close-when-x-button-is-pressed
            Window window = alert.getDialogPane().getScene().getWindow();
            window.setOnCloseRequest(e -> alert.hide());
            Optional<ButtonType> result = alert.showAndWait();
            result.ifPresent(res->{
                if (res.equals(buttonJa)) {
                    kjøpshistorikkArray.clear();
                    tableviewPrishistorikk.setItems(kjøpshistorikkArray);
                    try {
                        Files.write(afile.toPath(), FormatHandlekurvArray.formatComponents(kjøpshistorikkArray).getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (res.equals(buttonNei)) {
                }
            });

        }
    }
}
