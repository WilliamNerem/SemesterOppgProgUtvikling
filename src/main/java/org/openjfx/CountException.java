package org.openjfx;

public class CountException {

    public static String check(String countInn) {
        int countNumber;
        try { countNumber = Integer.parseInt(countInn); }
        catch(Exception e) {
            return countInn+" er feil, du må skrive inn et tall mellom 0-9!";
        }
        if(countNumber < 0 || countNumber > 9) {
            return countInn+" er feil, du må skrive inn et tall mellom 0-9!";
        }
        else return "";
    }
}
