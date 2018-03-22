package com.javaproject.caloriesmanager.util;

import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.zip.DataFormatException;

/**
 * Created by User on 23.01.2018.
 */
public class DateTimeUtil {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static <T extends Comparable<? super T>> boolean isBetween(T lt, T startTime, T endTime) {
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) <= 0;
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }
    public static final LocalDate maxDate = LocalDate.of(3000,1,1);
    public static final LocalDate minDate = LocalDate.of(1,1,1);
    public static LocalDate parseLocalDate(String s){
        return StringUtils.isEmpty(s)?null : LocalDate.parse(s);
    }
    public static LocalTime parseLocalTime(String d){
        return StringUtils.isEmpty(d)?null : LocalTime.parse(d);
    }

    private DateTimeUtil() {
    }
}

