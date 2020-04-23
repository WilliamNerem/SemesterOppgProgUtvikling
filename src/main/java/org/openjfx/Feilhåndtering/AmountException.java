package org.openjfx.Feilhåndtering;

public class AmountException extends Exception {
    public static class InvalidAmountException extends IllegalArgumentException{
        public InvalidAmountException(String msg) { super(msg); }
    }
}
