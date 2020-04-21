package org.openjfx.Feilhåndtering;

import java.io.IOException;

public class ComponentFormatException extends Exception {
    
    public static class InvalidComponentFormatException extends IOException {
        public InvalidComponentFormatException(String msg) { super(msg); }
    }
}
