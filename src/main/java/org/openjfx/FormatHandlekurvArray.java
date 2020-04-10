package org.openjfx;

import java.util.List;

public class FormatHandlekurvArray {
    public static String DELIMITTER = ";";

    public static String formatComponent(ComponentAndAntall c) {
        return c.getType() + DELIMITTER +c.getName() + DELIMITTER + c.getNumber() + DELIMITTER + c.getPrice() + DELIMITTER + c.getTotal();
    }

    public static String formatTitles(){
        return "Type" + DELIMITTER + "Navn" + DELIMITTER + "Antall" + DELIMITTER + "Pris" + DELIMITTER + "Totalpris"+"\n";
    }

    public static String formatComponents(List<ComponentAndAntall> cList) {
        StringBuffer str = new StringBuffer();
        str.append(formatTitles());
        for(ComponentAndAntall c : cList) {
            str.append(formatComponent(c));
            str.append("\n");
        }

        return str.toString();
    }
}
