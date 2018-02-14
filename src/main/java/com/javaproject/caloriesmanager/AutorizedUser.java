package com.javaproject.caloriesmanager;

import static com.javaproject.caloriesmanager.util.MealsUtil.DEFAULT_CALORIES_PER_DAY;

public class AutorizedUser {

    public static int id(){
        return 1;
    }
    public static int get_calories_per_day(){
        return DEFAULT_CALORIES_PER_DAY;
    }
}
