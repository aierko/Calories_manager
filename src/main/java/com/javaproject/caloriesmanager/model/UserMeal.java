package com.javaproject.caloriesmanager.model;

import sun.util.resources.LocaleData;

import java.time.LocalDateTime;
import java.util.Locale;

/**
 * Created by User on 23.01.2018.
 */
public class UserMeal {
    protected final LocalDateTime dateTime;
    protected final String description;
    protected final int calories;
    private Integer id;

    public Integer getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public UserMeal(LocalDateTime dateTime, String description, int calories) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }
}
