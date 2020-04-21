package org.openjfx.Feilhåndtering;

public class PriceException extends Exception {

    public static class InvalidPriceException extends IllegalArgumentException{
        public InvalidPriceException(String msg) { super(msg); }
    }
}
