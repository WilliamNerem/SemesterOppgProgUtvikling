package org.openjfx;

import javafx.concurrent.WorkerStateEvent;
import javafx.scene.control.Button;

import java.io.*;

public class StartThreadAdmin {

    private ThreadAdmin task;
    private Button btnOpen;
    private Button btnSave;
    private File selectedFile;

    public StartThreadAdmin(Button btnOpen, Button btnSave, File selectedFile) {
        this.btnOpen = btnOpen;
        this.btnSave = btnSave;
        this.selectedFile = selectedFile;
    }

    void open(){
        task = new ThreadAdmin(selectedFile);
        task.setOnSucceeded(this::threadDone);
        task.setOnFailed(this::threadFailed);
        Thread thread = new Thread(task);
        btnSave.setDisable(true);
        btnOpen.setDisable(true);
        thread.start();
    }
    void threadDone(WorkerStateEvent e) {
        this.btnOpen.setDisable(false);
        this.btnSave.setDisable(false);
    }

    void threadFailed(WorkerStateEvent event) {
        btnOpen.setDisable(false);
        btnSave.setDisable(false);
    }




}
