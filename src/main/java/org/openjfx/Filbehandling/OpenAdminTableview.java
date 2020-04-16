package org.openjfx.Filbehandling;

import javafx.concurrent.WorkerStateEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import org.openjfx.ComponentRegister;
import org.openjfx.ThreadAdmin;

import java.io.*;
import java.nio.file.Files;

public class OpenAdminTableview {

    private static File selectedFile;
    private ThreadAdmin task;
    private AnchorPane anchorpane;
    private Label errorMsg;
    private ComponentRegister cr;
    private boolean failed = false;
    private boolean exited = false;
    private ComponentRegister register;

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

    public void open(ComponentRegister cr, AnchorPane anchorpane, Label errorMsg) throws InterruptedException, IOException {
        this.anchorpane = anchorpane;
        this.errorMsg = errorMsg;
        this.cr = cr;
        disable();
        FileChooser fc = new FileChooser();
        fc.setTitle("Åpne lister med komponenter");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("binary files","*.jobj"));
        selectedFile = fc.showOpenDialog(null);
        try(InputStream fin = Files.newInputStream(selectedFile.toPath());
            ObjectInputStream oin = new ObjectInputStream(fin)) {
            register = (ComponentRegister) oin.readObject();
            cr.removeAll();
            open();
        } catch (ClassNotFoundException | IOException | ClassCastException e) {
            e.printStackTrace();
            failed = true;
            open();
            throw new IOException("Something is wrong with the implementation. See debug information");
        } catch (NullPointerException e){
            exited = true;
            open();
        }
    }

    public void disable(){
        anchorpane.setDisable(true);
    }

    public void open(){
        task = new ThreadAdmin(selectedFile, failed, exited);
        task.setOnSucceeded(this::threadDone);
        task.setOnFailed(this::threadError);
        Thread thread = new Thread(task);
        thread.start();
    }

    public void threadDone(WorkerStateEvent event) {
        errorMsg.setText("");
        anchorpane.setDisable(false);
        register.getComponents().forEach(cr::addComponent);
    }

    public void threadError(WorkerStateEvent event){
        errorMsg.setText("");
        if (!exited){
            errorMsg.setText("Feil med innlasting av fil");
        }
        anchorpane.setDisable(false);
    }
}
