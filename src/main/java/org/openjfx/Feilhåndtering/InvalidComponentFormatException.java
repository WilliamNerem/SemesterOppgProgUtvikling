package org.openjfx.Feilhåndtering;

import java.io.IOException;

public class InvalidComponentFormatException extends IOException { 
    public InvalidComponentFormatException(String msg) {
        super(msg);
    }
}
