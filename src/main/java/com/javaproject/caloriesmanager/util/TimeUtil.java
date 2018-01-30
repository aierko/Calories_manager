package com.javaproject.caloriesmanager.util;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;

/**
 * Created by User on 23.01.2018.
 */
public class TimeUtil {

    public static boolean isBetween(LocalDateTime first1, LocalTime start, LocalTime end) {
        return first1.compareTo(ChronoLocalDateTime.from(start)) >= 0 && first1.compareTo(ChronoLocalDateTime.from(end)) <= 0;
    }

}
