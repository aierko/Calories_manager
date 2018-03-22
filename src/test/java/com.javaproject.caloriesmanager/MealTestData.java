package com.javaproject.caloriesmanager;

import com.javaproject.caloriesmanager.model.Meal;
import com.javaproject.caloriesmanager.model.User;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static com.javaproject.caloriesmanager.model.AbstractBaseEntity.START_SEQ;
import static java.time.LocalDateTime.of;
import static java.time.Month.MARCH;
import static org.assertj.core.api.Assertions.assertThat;

public class MealTestData {
    public static final int MEAL_ID = START_SEQ + 2;
    public static final int ADMIN_ID = START_SEQ + 8;
    public static final Meal MEAL1 = new Meal(MEAL_ID+1, of(2018, MARCH,12, 7,10),"завтрак",420);
    public static final Meal MEAL2 = new Meal(MEAL_ID+3, of(2018, MARCH,14, 20,40),"ужин",350);
    public static final Meal MEAL3 = new Meal(MEAL_ID+2, of(2018, MARCH,24, 14,00),"обед",1200);
    public static final Meal MEAL4 = new Meal(MEAL_ID+4, of(2018, MARCH,23, 7,20),"завтрак",350);
    public static final Meal MEAL5 = new Meal(MEAL_ID+6, of(2018, MARCH,12, 14,30),"обед",500);
    public static final Meal MEAL6 = new Meal(MEAL_ID+7, of(2018, MARCH,19, 20,10),"ужин",600);
    public static final Meal MEAL9 = new Meal(ADMIN_ID+7, of(2018, MARCH,11, 10,10),"завтрак",500);

    public static Meal getCreated(){
        return new Meal(null,of(2018,MARCH,12,14,30 ),"созданный обед",500);
    }
    public static Meal getApdated(){
        return new Meal(MEAL_ID,MEAL1.getDateTime(),"обновленный завтрак", 600);
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "registered", "roles");
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registered", "roles").isEqualTo(expected);
    }
}
