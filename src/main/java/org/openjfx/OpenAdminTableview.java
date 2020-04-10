package org.openjfx;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.shape.Path;
import javafx.stage.FileChooser;

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

    static void open(ComponentRegister cr, Button btnOpen, Button btnSave) throws InterruptedException, IOException {
        StartThreadAdmin thread = new StartThreadAdmin(btnOpen, btnSave, selectedFile);
        thread.disable();
        FileChooser fc = new FileChooser();
        fc.setTitle("Åpne lister med komponenter");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("binary files","*.jobj"));
        selectedFile = fc.showOpenDialog(null);
        thread.open();
        /*
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

         */
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
        Thread.sleep(2000);

    }
}
