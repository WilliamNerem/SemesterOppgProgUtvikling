package org.openjfx;

import javafx.concurrent.WorkerStateEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.*;

public class StartThreadAdmin {

    private ThreadAdmin task;
    private AnchorPane anchorpane;
    private File selectedFile;

    public StartThreadAdmin(AnchorPane anchorpane, File selectedFile) {
        this.anchorpane = anchorpane;
        this.selectedFile = selectedFile;
    }

    public void disable(){
        anchorpane.setDisable(true);
    }

    public void open(){
        task = new ThreadAdmin(selectedFile);
        task.setOnSucceeded(this::threadDone);
        Thread thread = new Thread(task);
        thread.start();
    }

    public void threadDone(WorkerStateEvent event) {
        anchorpane.setDisable(false);
    }
}
