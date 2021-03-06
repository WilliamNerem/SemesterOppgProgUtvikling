package org.openjfx.Filbehandling;


import javafx.scene.control.*;
import org.openjfx.ComponentAndAntall;
import org.openjfx.Feilhåndtering.CheckInput;
import org.openjfx.Feilhåndtering.TypeException;

import java.util.Arrays;
import java.util.Optional;

class ParseKjopshistorikkArray {
    static ComponentAndAntall parseComponent(String str, TabPane tabPane, Tab tab) {
        String [] strings = str.split(FormatHandlekurvArray.DELIMITTER);
        if(strings.length != 5) {
            return errorInFile(tabPane, tab);
        }

        String type = strings[0];
        try{
            CheckInput.checkTypeOnchange(type);
        }catch (TypeException.InvalidTypeException e){
            return errorInFile(tabPane, tab);
        }
        String name = strings[1];
        int number;
        try {
            number = Integer.parseInt(strings[2]);
            if (number > 9 || number < 0){
                return errorInFile(tabPane, tab);
            }
        } catch(Exception e) {
            return errorInFile(tabPane, tab);
        }
        int price;
        try {
            price = Integer.parseInt(strings[3]);
            if (price < 0){
                return errorInFile(tabPane, tab);
            }
        } catch(Exception e) {
            return errorInFile(tabPane, tab);
        }

        return new ComponentAndAntall(type, name, number, price);
    }

    static boolean checkString(String str, TabPane tabPane, Tab tab) {
        String[] strings = str.split(FormatHandlekurvArray.DELIMITTER);
        String[] stringscheck = {"Type", "Navn", "Antall", "Pris", "Totalpris"};
        if (!Arrays.equals(strings, stringscheck)){
            errorInFile(tabPane, tab);
            return false;
        }
        return true;
    }

    private static ComponentAndAntall errorInFile(TabPane tabPane, Tab tab) {
        Alert alert = new Alert(Alert.AlertType.ERROR,"" ,ButtonType.OK);
        alert.setTitle("Feil!");
        alert.setHeaderText("Feil i kjøpshistorikk fil!");
        alert.setContentText("Det er en feil i filen testSaveTxtUser.txt.\n" +
                "Vennligst trykk 'Slett kjøpshistorikk'-knappen i kjøpshistorikk\nfor å nullstille filen.");
        Optional<ButtonType> result = alert.showAndWait();
        try{
            if (result.get() == ButtonType.OK){
                tabPane.getSelectionModel().select(tab);
            }
        }catch (Exception e){
            if (!result.isPresent()){
                tabPane.getSelectionModel().select(tab);
            }
        }

        return null;
    }
}
