package com.javaproject.caloriesmanager.service;

import com.javaproject.caloriesmanager.model.Meal;
import com.javaproject.caloriesmanager.util.exception.NFE;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface MealService {
    Meal get(int id, int userId) throws NFE;

    void delete(int id, int userId) throws NFE;

    default List<Meal> getBetweenDates(LocalDate startDate, LocalDate endDate, int userId) {
        return getBetweenDateTimes(LocalDateTime.of(startDate, LocalTime.MIN), LocalDateTime.of(endDate, LocalTime.MAX), userId);
    }
    List<Meal> getBetweenDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId);

    List<Meal> getAll(int userId);

    Meal update(Meal meal, int userId) throws NFE;

    Meal create(Meal meal, int userId);
}
