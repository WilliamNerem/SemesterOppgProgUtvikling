package org.openjfx.Feilhåndtering;

import javafx.scene.control.ComboBox;
import org.openjfx.CheckArrayOfComponents;
import org.openjfx.Component;
import org.openjfx.UserIndexController;

public class CheckInput {

    public static String checkName(String inName) throws NameException.InvalidNameException {
        if (inName.isEmpty() || inName == null) {
            throw new NameException.InvalidNameException("Du må skrive inn et navn!");
        }
        return inName;
    }

    public static int checkPrice(String inPrice) throws PriceException.InvalidPriceException {
        int outPrice;
        try{
            outPrice = Integer.parseInt(inPrice);
        }catch (Exception e){
            throw new PriceException.InvalidPriceException("Pris må være et heltall!");
        }
        if(outPrice <= 0){
            throw new PriceException.InvalidPriceException("Pris må være større enn 0!");
        }

        return outPrice;
    }

    public static boolean checkPrice(int inPrice) throws PriceException.InvalidPriceException {
        if(inPrice <= 0){
            throw new PriceException.InvalidPriceException("Pris må være større enn 0!");
        }
        return true;
    }

    public static String checkType(String inType) throws TypeException.InvalidTypeException {
        if (inType == null || inType.isEmpty()) {
            throw new TypeException.InvalidTypeException("Du må velge type!");
        }
        return inType;
    }

    public static String checkTypeOnchange(String inType) throws TypeException.InvalidTypeException {
        if (inType.toLowerCase().equals("skjermkort")
                || inType.toLowerCase().equals("harddisk")
                || inType.toLowerCase().equals("mus")
                || inType.toLowerCase().equals("tastatur")
                || inType.toLowerCase().equals("minne")
                || inType.toLowerCase().equals("motherboard")
                || inType.toLowerCase().equals("monitor")){
            String validType = inType.substring(0,1).toUpperCase() + inType.substring(1);
            return validType;
        }
        throw new TypeException.InvalidTypeException("Denne typen finnes ikke");
    }

    public static String checkUI(ComboBox<Component> cb, Integer antall) throws InputException.InvalidInputException {
        if (cb.getValue().getName().equals("Ingen") && !antall.equals(0)) {
            throw new InputException.InvalidInputException("Du må legge til en komponent der du har skrevet antall");
        }
        else if(!cb.getValue().getName().equals("Ingen") && antall.equals(0)) {
            throw new InputException.InvalidInputException( "Du må velge antall der du har valgt komponent");
        }
        else if(cb.getValue().getName().equals("Ingen") && antall.equals(0)) {
            return "";
        }
        else return CheckArrayOfComponents.calcComponent(cb.getValue().getPrice(),antall)+",-";
    }

    public static String checkForTotalPrice(UserIndexController uic) {
        return "Totalpris : "+((uic.cmbMotherboard.getValue().getPrice()*uic.txtAntallMotherboard.getValue())+(uic.cmbHarddisk.getValue().getPrice()*uic.txtAntallHarddisk.getValue())+(uic.cmbSkjermkort.getValue().getPrice()*uic.txtAntallSkjermkort.getValue())+(uic.cmbMus.getValue().getPrice()*uic.txtAntallMus.getValue())+(uic.cmbTastatur.getValue().getPrice()*uic.txtAntallTastatur.getValue())+(uic.cmbMinne.getValue().getPrice()*uic.txtAntallMinne.getValue())+(uic.cmbMonitor.getValue().getPrice()*uic.txtAntallMonitor.getValue()))+",-";
    }
}
