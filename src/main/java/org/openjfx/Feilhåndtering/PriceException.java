package org.openjfx.Feilhåndtering;

public class PriceException extends Exception {

    public static class InvalidPriceException extends IllegalArgumentException{
        public InvalidPriceException(String msg) { super(msg); }
    }

    public static int checkPrice(String inPrice) throws InvalidPriceException{
        int outPrice;
        try{
            outPrice = Integer.parseInt(inPrice);
        }catch (Exception e){
            throw new InvalidPriceException("Pris må være et heltall!");
        }
        if(outPrice <= 0){
            throw new InvalidPriceException("Pris må være større enn 0!");
        }

        return outPrice;
    }

    public static void checkPrice(int inPrice) throws InvalidPriceException{
        if(inPrice <= 0){
            throw new InvalidPriceException("Pris må være større enn 0!");
        }
    }

}
