package org.openjfx;

public class TypeException extends Exception{

    public static class InvalidTypeException extends IllegalArgumentException {
        public InvalidTypeException(String msg) { super(msg); }
    }

    public static String checkType(String inType) throws InvalidTypeException {
        if (inType == null) {
            throw new InvalidTypeException("Du må velge type!");
        }
        return inType;
    }
}
