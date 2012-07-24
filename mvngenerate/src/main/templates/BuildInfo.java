package com.lvls;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.rosetta.rose.gen.BuildInfoGenerator;

public class BuildInfo {

    public static Date buildDate;
    public static String dateAndTime;
    public static String version = "${version}";
    
    static {
        try {
            buildDate = BuildInfoGenerator.DATE_FORMAT.parse("${buildDate}");
//            buildDate = DateFormat.getDateInstance().parse("${buildDate}");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        dateAndTime = new SimpleDateFormat("yyyy.MM.dd HH:mm").format(buildDate);
    }

}
