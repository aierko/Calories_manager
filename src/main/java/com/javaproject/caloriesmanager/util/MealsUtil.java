package com.javaproject.caloriesmanager.util;

import com.javaproject.caloriesmanager.model.UserMeal;
import com.javaproject.caloriesmanager.model.UserMealWithExceed;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

/**
 * Created by User on 26.01.2018.
 */
public class MealsUtil {
    public static final List<UserMeal> list = Arrays.asList(
            new UserMeal(LocalDateTime.of(2018, Month.JANUARY, 25, 11, 20), "breakfast", 500),
            new UserMeal(LocalDateTime.of(2018, Month.JANUARY, 26, 6, 01), "breakfast", 1000));

    public static void main(String[] args) {
        List<UserMealWithExceed> filteredWithExceededByCycle = getFilteredWithExceededByCycle(list, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        filteredWithExceededByCycle.forEach(System.out::println);


    }

    public static UserMealWithExceed createWithExceeded(UserMeal meal, boolean exceeded) {
        return new UserMealWithExceed(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories(), exceeded);
    }

    public static List<UserMealWithExceed> getFilteredWithExceededByCycle(List<UserMeal> list, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        final Map<LocalDateTime, Integer> caloriesSomByDay = new HashMap<>();
        list.forEach(meal -> caloriesSomByDay.merge(meal.getDateTime(), meal.getCalories(), Integer::sum));
        final List<UserMealWithExceed> mealsWithExceeded = new ArrayList<>();
        list.forEach(meal -> {
            if (TimeUtil.isBetween(meal.getDateTime(), startTime, endTime)) {
                mealsWithExceeded.add(createWithExceeded(meal, caloriesSomByDay.get(meal.getDateTime()) > caloriesPerDay));
            }
        });
        return mealsWithExceeded;
    }

}