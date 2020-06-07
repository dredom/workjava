package com.dredom.time;

import static java.lang.System.out;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * Java 8 added the Time package.
 *
 */
public class DateAndTime {

    public static void main(String[] args) {
        DateAndTime instance = new DateAndTime();
        
        instance.basicLocalDateAndTime();

    }

    public void basicLocalDateAndTime() {
        LocalDateTime dt = LocalDateTime.now();
        out.println(" now(), default zone, default format: " + dt);
        out.println(" now(), default zone, ISO format: " + dt.format(DateTimeFormatter.BASIC_ISO_DATE) );
        out.println(" now(), default zone, custom format: " + dt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd.hh:mm")) );
        out.println(" default time zone: " + ZoneId.systemDefault().getId());
        out.println(" Zone offset of 1 hours: " + ZoneOffset.ofHours(1).getId());
    }
}
