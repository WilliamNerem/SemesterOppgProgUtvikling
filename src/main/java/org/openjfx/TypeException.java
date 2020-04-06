package org.openjfx;

public class TypeException extends Exception{

    public static class InvalidTypeException extends IllegalArgumentException {
        public InvalidTypeException(String msg) { super(msg); }
    }

    public static String checkType(String inType) throws InvalidTypeException {
        if (inType == null || inType.isEmpty()) {
            throw new InvalidTypeException("Du må velge type!");
        }
        return inType;
    }

    public static String checkTypeOnchange(String inType) throws InvalidTypeException{
        if (inType.toLowerCase().equals("skjermkort")
                || inType.toLowerCase().equals("harddisk")
                || inType.toLowerCase().equals("mus")
                || inType.toLowerCase().equals("tastatur")
                || inType.toLowerCase().equals("minne")
                || inType.toLowerCase().equals("motherboard")
                || inType.toLowerCase().equals("monitor")){
            String validType = inType.substring(0,1).toUpperCase() + inType.substring(1);
            return validType;
        }
        throw new InvalidTypeException("Denne typen finnes ikke");
    }
}
