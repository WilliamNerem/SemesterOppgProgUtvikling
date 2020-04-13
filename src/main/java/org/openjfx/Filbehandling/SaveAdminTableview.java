package org.openjfx.Filbehandling;

import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import org.openjfx.Component;
import org.openjfx.ComponentRegister;
import org.openjfx.StartThreadAdmin;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;

public class SaveAdminTableview {

    private static File selectedFile;

    public static void save(ComponentRegister cr, AnchorPane anchorpane) throws InterruptedException {
        StartThreadAdmin thread = new StartThreadAdmin(anchorpane, selectedFile);
        thread.disable();
        FileChooser fc = new FileChooser();
        fc.setTitle("Lagre Komponenter");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("binary files","*.jobj"));
        selectedFile = fc.showSaveDialog(null);
        thread.open();
        try (OutputStream os = Files.newOutputStream(selectedFile.toPath());
             ObjectOutputStream out = new ObjectOutputStream(os))
        {
            out.writeObject(cr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Thread.sleep(3000);
    }
}
