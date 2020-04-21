package org.openjfx.Feilhåndtering;

public class InputException extends Exception{

    public static class InvalidInputException extends IllegalArgumentException {
        public InvalidInputException(String msg) { super(msg); }
    }
}
