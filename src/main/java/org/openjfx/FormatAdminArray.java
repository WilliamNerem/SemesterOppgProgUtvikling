package org.openjfx;

import java.util.List;

public class FormatAdminArray {
    public static String DELIMITTER = ";";

    public static String formatComponent(Component c) {
        return c.getType() + DELIMITTER +c.getName() + DELIMITTER + c.getPrice();
    }

    public static String formatComponents(List<Component> cList) {
        StringBuffer str = new StringBuffer();
        for(Component c : cList) {
            str.append(formatComponent(c));
            str.append("\n");
        }

        return str.toString();
    }
}
