package org.openjfx;

import java.util.List;

public class FormatHandlekurvArray {
    public static String DELIMITTER = ";";

    public static String formatComponent(Component c) {
        return c.getType() + DELIMITTER +c.getName() + DELIMITTER +c.getPrize();
    }

    public static String formatComponents(List<ComponentAndAntall> cList) {
        StringBuffer str = new StringBuffer();
        for(Component c : cList) {
            str.append(formatComponent(c));
            str.append("\n");
        }

        return str.toString();
    }
}
