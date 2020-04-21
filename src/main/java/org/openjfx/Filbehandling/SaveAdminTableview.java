package org.openjfx.Filbehandling;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import org.openjfx.ComponentRegister;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SaveAdminTableview {

    private AnchorPane anchorpane;

    public void save(ComponentRegister cr, AnchorPane anchorpane, Label errorMsg, Label confirmMsg) {
        this.anchorpane = anchorpane;
        disable();
        FileChooser fc = new FileChooser();
        fc.setTitle("Lagre Komponenter");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("binary files","*.jobj"));
        fc.setInitialDirectory(new File(System.getProperty("user.dir")));
        File selectedFile = fc.showSaveDialog(null);
        try (OutputStream os = Files.newOutputStream(selectedFile.toPath());
             ObjectOutputStream out = new ObjectOutputStream(os))
        {
            out.writeObject(cr);
            anchorpane.setDisable(false);
            errorMsg.setText("");
            confirmMsg.setText("Ny fil lagret");
        } catch (IOException | ClassCastException e) {
            e.printStackTrace();
            anchorpane.setDisable(false);
            confirmMsg.setText("");
            errorMsg.setText("Feil med innlastning av fil");
        } catch (NullPointerException e){
            confirmMsg.setText("");
            errorMsg.setText("");
            anchorpane.setDisable(false);
        }
    }

    public void disable(){
        anchorpane.setDisable(true);
    }


    public void quickSave(ComponentRegister componentRegister, File filepath, Label confirmMsg, Label errorMsg) throws IOException {
        try{
            OutputStream os = Files.newOutputStream(filepath.toPath());
            ObjectOutputStream out = new ObjectOutputStream(os);
            out.writeObject(componentRegister);
            errorMsg.setText("");
            confirmMsg.setText("Fil lagret");
        } catch (Exception ignored){
            OutputStream os = Files.newOutputStream(Paths.get("StandardFile.jobj"));
            ObjectOutputStream out = new ObjectOutputStream(os);
            out.writeObject(componentRegister);
            errorMsg.setText("");
            confirmMsg.setText("Fil lagret");
        }
    }

    public void saveStartup(ComponentRegister componentRegister, String str) throws IOException {
        File afile = new File("StandardFile.jobj");
        File selectedFileLbl = new File("StandardFileLbl.jobj");
        OutputStream os = Files.newOutputStream(afile.toPath());
        ObjectOutputStream out = new ObjectOutputStream(os);
        out.writeObject(componentRegister);
        OutputStream osLbl = Files.newOutputStream(selectedFileLbl.toPath());
        ObjectOutputStream outLbl = new ObjectOutputStream(osLbl);
        outLbl.writeObject(str);
    }
}
