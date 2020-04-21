package org.openjfx.Filbehandling;

import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import org.openjfx.ComponentAndAntall;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

public class OpenKjøpshistorikkTxt {
    public static void open(ObservableList<ComponentAndAntall> kjøpshistorikkArray, File afile, TabPane tabPane, Tab tab){
        try (BufferedReader reader = Files.newBufferedReader(afile.toPath())) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                ComponentAndAntall c = ParseKjøpshistorikkArray.parseComponent(line, tabPane, tab);
                if(c == null){
                    kjøpshistorikkArray.clear();
                    break;
                }
                kjøpshistorikkArray.add(c);
            }
        }catch (FileNotFoundException f) {
            System.out.println(afile.getPath() + " does not exist");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
