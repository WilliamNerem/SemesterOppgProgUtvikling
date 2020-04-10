package org.openjfx.Filbehandling;

import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import org.openjfx.ComponentAndAntall;
import org.openjfx.Filbehandling.ParseKjøpshistorikkArray;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

public class OpenKjøpshistorikkTxt {
    public static void open(ObservableList<ComponentAndAntall> kjøpshistorikkArray){
        FileChooser fc = new FileChooser();
        fc.setTitle("Åpne tidligere kjøp");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("text files","*.txt"));
        File selectedFile = fc.showOpenDialog(null);
        try (BufferedReader reader = Files.newBufferedReader(selectedFile.toPath())) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                ComponentAndAntall c = ParseKjøpshistorikkArray.parseComponent(line);
                kjøpshistorikkArray.add(c);
            }
        }catch (FileNotFoundException f) {
            System.out.println(selectedFile.getPath() + " does not exist");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
