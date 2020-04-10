package org.openjfx.Filbehandling;

import org.openjfx.Component;
import org.openjfx.Feilhåndtering.InvalidComponentFormatException;

public class ParseAdminArray {
    public static Component parseComponent(String str)
            throws InvalidComponentFormatException {
        String [] strings = str.split(FormatHandlekurvArray.DELIMITTER);
        if(strings.length != 3) {
            throw new InvalidComponentFormatException("Feil bruk av spesialtegn");
        }

        String type = strings[0];
        String name = strings[1];
        int price;
        try {
            price = Integer.parseInt(strings[2]);
        } catch(NumberFormatException e) {
            throw new InvalidComponentFormatException("Ugyldig pris");
        }

        return new Component(type, name, price);
    }
}
