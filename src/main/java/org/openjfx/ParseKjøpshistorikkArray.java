package org.openjfx;


public class ParseKjøpshistorikkArray {
    public static ComponentAndAntall parseComponent(String str)
            throws InvalidComponentFormatException {
        String [] strings = str.split(FormatHandlekurvArray.DELIMITTER);
        if(strings.length != 4) {
            throw new InvalidComponentFormatException("Feil bruk av spesialtegn");
        }

        String type = strings[0];
        int number;
        try {
            number = Integer.parseInt(strings[1]);
        } catch(NumberFormatException e) {
            throw new InvalidComponentFormatException("Ugyldig antall");
        }
        String name = strings[2];
        int price;

        try {
            price = Integer.parseInt(strings[3]);
        } catch(NumberFormatException e) {
            throw new InvalidComponentFormatException("Ugyldig pris");
        }

        return new ComponentAndAntall(type, number, name, price);
    }
}
