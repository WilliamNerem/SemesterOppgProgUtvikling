package org.openjfx.Filbehandling;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import org.openjfx.ComponentRegister;
import org.openjfx.StartThreadAdmin;

import java.io.*;
import java.nio.file.Files;

public class OpenAdminTableview {

    private static File selectedFile;

    public static String readFile(File filename) {
        String liste = "";
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(filename));
            liste = (String) is.readObject();
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return liste;
    }

    public static void open(ComponentRegister cr, AnchorPane anchorpane) throws InterruptedException, IOException {
        StartThreadAdmin thread = new StartThreadAdmin(anchorpane, selectedFile);
        thread.disable();
        FileChooser fc = new FileChooser();
        fc.setTitle("Åpne lister med komponenter");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("binary files","*.jobj"));
        selectedFile = fc.showOpenDialog(null);
        thread.open();
        try (InputStream fin = Files.newInputStream(selectedFile.toPath());
             ObjectInputStream oin = new ObjectInputStream(fin))
        {
            ComponentRegister register = (ComponentRegister) oin.readObject();
            cr.removeAll();
            register.getComponents().forEach(cr::addComponent);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            throw new IOException("Something is wrong with the implementation. See debug information");
        }
        Thread.sleep(3000);

    }
}
