package org.openjfx;

public class NameException extends Exception {

    public static class InvalidNameException extends IllegalArgumentException {
        public InvalidNameException(String msg) { super(msg); }
    }

    public static String checkName(String inName) throws InvalidNameException {
        if (inName.isEmpty() || inName == null) {
            throw new InvalidNameException("Du må skrive inn et navn!");
        }
        return inName;
    }

}