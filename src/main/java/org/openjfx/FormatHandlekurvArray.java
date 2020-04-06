package org.openjfx;

import java.util.List;

public class FormatHandlekurvArray {
    public static String DELIMITTER = ";";

    public static String formatPerson(Component c) {
        return c.getType() + DELIMITTER +c.getName() + DELIMITTER +c.getPrize();
    }

    public static String formatComponent(List<Component> cList) {
        StringBuffer str = new StringBuffer();
        for(Component c : cList) {
            str.append(formatPerson(c));
            str.append("\n");
        }

        return str.toString();
    }
}
