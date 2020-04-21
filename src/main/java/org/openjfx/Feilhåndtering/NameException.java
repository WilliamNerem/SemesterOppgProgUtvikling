package org.openjfx.Feilhåndtering;

public class NameException extends Exception {

    public static class InvalidNameException extends IllegalArgumentException {
        public InvalidNameException(String msg) { super(msg); }
    }
}