package org.openjfx.Feilhåndtering;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.openjfx.CalculatePrice;
import org.openjfx.Component;
import org.openjfx.UserIndexController;

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
        else return CalculatePrice.calcComponent(cb.getValue().getPrice(),antall)+" kr";
    }

    public static String checkForTotalPrice(UserIndexController uic) {
        return "Totalpris : "+((uic.cmbMotherboard.getValue().getPrice()*uic.txtAntallMotherboard.getValue())+(uic.cmbHarddisk.getValue().getPrice()*uic.txtAntallHarddisk.getValue())+(uic.cmbSkjermkort.getValue().getPrice()*uic.txtAntallSkjermkort.getValue())+(uic.cmbMus.getValue().getPrice()*uic.txtAntallMus.getValue())+(uic.cmbTastatur.getValue().getPrice()*uic.txtAntallTastatur.getValue())+(uic.cmbMinne.getValue().getPrice()*uic.txtAntallMinne.getValue())+(uic.cmbMonitor.getValue().getPrice()*uic.txtAntallMonitor.getValue()))+" kr";
    }
}
