package com.traincompany.management.admin_inputs.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public final class DateFormatter {
    private DateFormatter() {}

    public static String format(Date date, String pattern) {
        
        LocalDateTime localDate = Instant.ofEpochMilli(date.getTime())
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime();

        return localDate.format(DateTimeFormatter.ofPattern(pattern));
    }
}
