package org.openjfx.Filbehandling;

import javafx.concurrent.WorkerStateEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import org.openjfx.Component;
import org.openjfx.ComponentRegister;
import org.openjfx.ThreadAdmin;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SaveAdminTableview {

    private static File selectedFile;
    private AnchorPane anchorpane;
    private ThreadAdmin task;
    private Label errorMsg;
    private Label confirmMsg;
    private boolean failed = false;
    private boolean exited = false;

    public void save(ComponentRegister cr, AnchorPane anchorpane, Label errorMsg, Label confirmMsg) throws InterruptedException {
        this.anchorpane = anchorpane;
        this.confirmMsg = confirmMsg;
        disable();
        FileChooser fc = new FileChooser();
        fc.setTitle("Lagre Komponenter");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("binary files","*.jobj"));
        fc.setInitialDirectory(new File(System.getProperty("user.dir")));
        selectedFile = fc.showSaveDialog(null);
        try (OutputStream os = Files.newOutputStream(selectedFile.toPath());
             ObjectOutputStream out = new ObjectOutputStream(os))
        {
            out.writeObject(cr);
            anchorpane.setDisable(false);
            errorMsg.setText("");
            confirmMsg.setText("Ny fil lagret");
        } catch (IOException | ClassCastException e) {
            e.printStackTrace();
            failed = true;
            anchorpane.setDisable(false);
            confirmMsg.setText("");
            errorMsg.setText("Feil med innlastning av fil");
        } catch (NullPointerException e){
            exited = true;
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
