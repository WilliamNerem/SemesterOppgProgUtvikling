package org.openjfx;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class InputException extends Exception{

    public static class InvalidInputException extends IllegalArgumentException {
        public InvalidInputException(String msg) { super(msg); }
    }

    public static String checkInput(ComboBox<Component> cb, Integer antall) throws InvalidInputException{
        if (cb.getValue().getName().equals("Ingen") && !antall.equals(0)) {
            throw new InvalidInputException("Du må legge til en komponent der du har skrevet antall");
        }
        else if(!cb.getValue().getName().equals("Ingen") && antall.equals(0)) {
            throw new InvalidInputException( "Du må velge antall der du har valgt komponent");
        }
        else if(cb.getValue().getName().equals("Ingen") && antall.equals(0)) {
            return "";
        }
        else return CalculatePrice.calcComponent(cb.getValue().getPrize(),antall)+" kr";
    }
}
