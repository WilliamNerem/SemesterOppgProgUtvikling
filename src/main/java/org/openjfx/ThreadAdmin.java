package org.openjfx;

import javafx.concurrent.Task;

import java.io.File;

public class ThreadAdmin extends Task<File> {
    private final File file;

    public ThreadAdmin(File file){
        this.file = file;
    }


    protected File call() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {

        }
        return file;
    }
}