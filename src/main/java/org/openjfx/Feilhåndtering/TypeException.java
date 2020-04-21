package org.openjfx.Feilhåndtering;

public class TypeException extends Exception{

    public static class InvalidTypeException extends IllegalArgumentException {
        public InvalidTypeException(String msg) { super(msg); }
    }
}
