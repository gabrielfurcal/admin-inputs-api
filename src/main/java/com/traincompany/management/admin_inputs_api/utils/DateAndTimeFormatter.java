package com.traincompany.management.admin_inputs_api.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public final class DateAndTimeFormatter {
    private DateAndTimeFormatter() {}

    public static String toString(Date date, String pattern) {
        
        LocalDateTime localDate = Instant.ofEpochMilli(date.getTime())
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime();

        return localDate.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String toString(LocalTime time, String pattern) {
        if (time == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return time.format(formatter);
    }

    public static Date toDate(String date, String pattern) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        try {
            return formatter.parse(date);
        } catch(ParseException e) {
            e.printStackTrace();
            throw new Exception("Could not parse String: " + date + " to Date");
        }
    }

    public static LocalTime toTime(String time, String pattern) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        try {
            return LocalTime.parse(time, formatter);
        } catch(DateTimeParseException e) {
            e.printStackTrace();
            throw new Exception("Could not parse String: " + time + " to LocalTime");
        }
    }
}
