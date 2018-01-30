package com.javaproject.caloriesmanager.util;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;

/**
 * Created by User on 23.01.2018.
 */
public class DateTimeUtil {

    public static boolean isBetween(LocalTime lt, LocalTime start, LocalTime end) {
        return lt.compareTo(start) >= 0 && lt.compareTo(end) <= 0;
    }

}
