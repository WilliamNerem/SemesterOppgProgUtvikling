package org.openjfx.Filbehandling;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import org.openjfx.Component;
import org.openjfx.StartThreadAdmin;

import java.io.*;

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

    public static void open(ObservableList<Component> adminArray, Button btnOpen, Button btnSave) throws InterruptedException {
        StartThreadAdmin thread = new StartThreadAdmin(btnOpen, btnSave, selectedFile);
        thread.disable();
        FileChooser fc = new FileChooser();
        fc.setTitle("Åpne lister med komponenter");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("binary files","*.jobj"));
        selectedFile = fc.showOpenDialog(null);
        thread.open();
        try (BufferedReader readerJobj = new BufferedReader(new StringReader(readFile(selectedFile)));) {
            String line;
            while ((line = readerJobj.readLine()) != null) {
                Component c = ParseAdminArray.parseComponent(line);
                adminArray.add(c);
            }
        }catch (FileNotFoundException f) {
            System.out.println(selectedFile.getPath() + " does not exist");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Thread.sleep(2000);

    }
}
