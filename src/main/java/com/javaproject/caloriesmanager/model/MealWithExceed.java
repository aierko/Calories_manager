package com.javaproject.caloriesmanager.model;

import java.time.LocalDateTime;

/**
 * Created by User on 23.01.2018.
 */
public class MealWithExceed {
    protected final LocalDateTime dateTime;
    protected final String description;
    protected final int calories;
    protected final boolean exceed;
    private final Integer id;

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public boolean isExceed() {
        return exceed;
    }

    public Integer getId() {
        return id;
    }

    public MealWithExceed(Integer id, LocalDateTime dateTime, String description, int calories, boolean exceed) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.exceed = exceed;
        this.id = id;

    }

    @Override
    public String toString() {
        return "MealWithExceed{" +
                "id =" + id + " , dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", exceed=" + exceed +
                '}';
    }
}
