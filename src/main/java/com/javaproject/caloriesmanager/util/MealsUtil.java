package com.javaproject.caloriesmanager.util;

import com.javaproject.caloriesmanager.model.Meal;
import com.javaproject.caloriesmanager.to.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Created by User on 26.01.2018.
 */
public class MealsUtil {

    public static final int DEFAULT_CALORIES_PER_DAY = 2000;

    public static List<MealWithExceed> getWithExceeded(Collection<Meal> meals, int caloriesPerDay) {
        return getFilteredWithExceeded(meals, LocalTime.MIN, LocalTime.MAX, caloriesPerDay);
    }

    public static List<MealWithExceed> getFilteredWithExceeded(Collection<Meal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesSumByDate = meals.stream()
                .collect(
                        Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories))
//                      Collectors.toMap(Meal::getDate, Meal::getCalories, Integer::sum)
                );

        return meals.stream()
                .filter(meal -> DateTimeUtil.isBetween(meal.getTime(), startTime, endTime))
                .map(meal -> createWithExceed(meal, caloriesSumByDate.get(meal.getDate()) > caloriesPerDay))
                .collect(toList());
    }

    private static MealWithExceed createWithExceed(Meal meal, boolean exceeded) {

        return new MealWithExceed(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories(), exceeded);
    }
//    public static List<MealWithExceed> getFilteredWithExceededByCycle(List<Meal> list, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
//        final Map<LocalDateTime, Integer> caloriesSomByDay = new HashMap<>();
//        list.forEach(meal -> caloriesSomByDay.merge(meal.getDateTime(), meal.getCalories(), Integer::sum));
//        final List<MealWithExceed> mealsWithExceeded = new ArrayList<>();
//        list.forEach(meal -> {
//            if (DateTimeUtil.isBetween(meal.getDateTime(), startTime, endTime)) {
//                mealsWithExceeded.add(createWithExceeded(meal, caloriesSomByDay.get(meal.getDateTime()) > caloriesPerDay));
//            }
//        });
//        return mealsWithExceeded;
//    }

}