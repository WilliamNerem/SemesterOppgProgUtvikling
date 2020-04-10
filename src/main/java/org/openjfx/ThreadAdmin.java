package org.openjfx;

import javafx.concurrent.Task;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.File;

public class ThreadAdmin extends Task<File> {
    private final File file;

    public ThreadAdmin(File file){
        this.file = file;
    }


    @Override
    protected File call() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {

        }
        return file;
    }
}