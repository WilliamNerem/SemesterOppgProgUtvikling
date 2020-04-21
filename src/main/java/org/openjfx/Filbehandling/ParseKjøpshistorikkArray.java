package org.openjfx.Filbehandling;


import org.openjfx.ComponentAndAntall;
import org.openjfx.Feilhåndtering.ComponentFormatException;

class ParseKjøpshistorikkArray {
    static ComponentAndAntall parseComponent(String str)
            throws ComponentFormatException.InvalidComponentFormatException {
        String [] strings = str.split(FormatHandlekurvArray.DELIMITTER);
        if(strings.length != 5) {
            throw new ComponentFormatException.InvalidComponentFormatException("Feil bruk av spesialtegn");
        }

        String type = strings[0];
        String name = strings[1];
        int number;
        try {
            number = Integer.parseInt(strings[2]);
        } catch(NumberFormatException e) {
            throw new ComponentFormatException.InvalidComponentFormatException("Ugyldig antall");
        }
        int price;
        try {
            price = Integer.parseInt(strings[3]);
        } catch(NumberFormatException e) {
            throw new ComponentFormatException.InvalidComponentFormatException("Ugyldig pris");
        }

        return new ComponentAndAntall(type, name, number, price);
    }
}
