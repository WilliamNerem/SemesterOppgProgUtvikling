package org.openjfx.Filbehandling;

import javafx.concurrent.WorkerStateEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import org.openjfx.ComponentRegister;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class OpenAdminTableview {

    private static File selectedFile;
    private boolean failed = false;
    private boolean exited = false;
    private AnchorPane anchorpane;
    private Label errorMsg;
    private Label confirmMsg;
    private ComponentRegister cr;
    private ComponentRegister register;


    public File getSelectedFile(){
        return selectedFile;
    }

    public void open(ComponentRegister cr, AnchorPane anchorpane, Label errorMsg, Label confirmMsg){
        this.anchorpane = anchorpane;
        this.errorMsg = errorMsg;
        this.confirmMsg = confirmMsg;
        this.cr = cr;
        disable();
        FileChooser fc = new FileChooser();
        fc.setTitle("Åpne lister med komponenter");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("binary files","*.jobj"));
        fc.setInitialDirectory(new File(System.getProperty("user.dir")));
        selectedFile = fc.showOpenDialog(null);
        try(InputStream fin = Files.newInputStream(selectedFile.toPath());
            ObjectInputStream oin = new ObjectInputStream(fin)) {
            register = (ComponentRegister) oin.readObject();
            open();
        } catch (ClassNotFoundException | IOException | ClassCastException e) {
            failed = true;
            anchorpane.setDisable(false);
            errorMsg.setText("Noe er galt med filen");
            confirmMsg.setText("");
        } catch (NullPointerException e){
            exited = true;
            anchorpane.setDisable(false);
            errorMsg.setText("");
            confirmMsg.setText("");
        }
    }

    public void disable(){
        anchorpane.setDisable(true);
    }

    public void open(){
        ThreadAdmin task = new ThreadAdmin(selectedFile, failed, exited);
        task.setOnSucceeded(this::threadDone);
        Thread thread = new Thread(task);
        thread.start();
    }

    public void threadDone(WorkerStateEvent event) {
        errorMsg.setText("");
        anchorpane.setDisable(false);
        cr.removeAll();
        register.getComponents().forEach(cr::addComponent);
        confirmMsg.setText("");
    }

    public void openDefault(File f, ComponentRegister cr) throws IOException, ClassNotFoundException{
        InputStream fin = Files.newInputStream(f.toPath());
        ObjectInputStream oin = new ObjectInputStream(fin);
        register = (ComponentRegister) oin.readObject();
        cr.removeAll();
        register.getComponents().forEach(cr::addComponent);
    }

    public File openStandardFile(){
        try {
            FileChooser fc = new FileChooser();
            fc.setTitle("Åpne lister med komponenter");
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("binary files","*.jobj"));
            fc.setInitialDirectory(new File(System.getProperty("user.dir")));
            selectedFile = fc.showOpenDialog(null);
            return selectedFile;
        }catch (RuntimeException e){
             return null;
        }
    }

    public void setLbl(Label standardLbl, File StandardFileLbl) throws IOException, ClassNotFoundException {
        InputStream fin = Files.newInputStream(StandardFileLbl.toPath());
        ObjectInputStream oin = new ObjectInputStream(fin);
        Path fileName = Paths.get((String) oin.readObject());
        standardLbl.setText("Komponentfil sluttbruker:\n" + fileName.getFileName());
    }
}
