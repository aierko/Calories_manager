package com.javaproject.caloriesmanager;

import com.javaproject.caloriesmanager.model.AbstractBaseEntity;
import com.javaproject.caloriesmanager.model.AbstractNamedEntity;

import static com.javaproject.caloriesmanager.util.MealsUtil.DEFAULT_CALORIES_PER_DAY;

public class AutorizedUser {

    private static int id = AbstractBaseEntity.START_SEQ;
    public static int id() {
        return id;
    }

    public static int get_calories_per_day() {
        return DEFAULT_CALORIES_PER_DAY;
    }
    public static void setId(int id){
        AutorizedUser.id = id;
    }
}


