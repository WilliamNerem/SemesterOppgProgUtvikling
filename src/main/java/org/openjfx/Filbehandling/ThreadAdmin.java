package org.openjfx.Filbehandling;

import javafx.concurrent.Task;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.File;

public class ThreadAdmin extends Task<File> {
    private final File file;
    private boolean failed;
    private boolean exited;

    public ThreadAdmin(File file, boolean failed, boolean exited){
        this.file = file;
        this.failed = failed;
        this.exited = exited;
    }


    @Override
    protected File call() {
        if (!exited) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {

            }
        }else {
            throw new ArithmeticException();
        }

        if (failed) {
            throw new ArithmeticException();
        }
        return file;
    }
}