package com.javaproject.caloriesmanager.web.meal;

import com.javaproject.caloriesmanager.AutorizedUser;
import com.javaproject.caloriesmanager.model.Meal;
import com.javaproject.caloriesmanager.service.MealService;
import com.javaproject.caloriesmanager.to.MealWithExceed;
import com.javaproject.caloriesmanager.util.DateTimeUtil;
import com.javaproject.caloriesmanager.util.MealsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static com.javaproject.caloriesmanager.util.ValidationUtil.assureIdConsistent;
import static com.javaproject.caloriesmanager.util.ValidationUtil.checkNew;


@Controller
public class MealRestController {
    private final MealService service;
    private static final Logger log = LoggerFactory.getLogger(MealRestController.class);

    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }

    public Meal get(int id) {
        int user_id = AutorizedUser.id();
        log.info("get meal {} for user {}", id, user_id);
        return service.get(id, user_id);
    }

    public Meal create(Meal meal) {
        int user_id = AutorizedUser.id();
        log.info("create {} for user {}", meal, user_id);
        checkNew(meal);
        return service.create(meal, user_id);
    }

    public void delete(int id) {
        int user_id = AutorizedUser.id();
        log.info("delete {} for user {}", id, user_id);
        service.delete(id, user_id);
    }

    public void update(Meal meal, int id) {
        int user_id = AutorizedUser.id();
        log.info("update {} for user {}", id, user_id);
        assureIdConsistent(meal,id);
        service.update(meal,user_id);
    }
    public List<MealWithExceed> getAll(){
        int user_id = AutorizedUser.id();
        log.info("get all for user {}", user_id);
        return MealsUtil.getWithExceeded(service.getAll(user_id),AutorizedUser.get_calories_per_day());
    }
    public List<MealWithExceed> getBetween(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime){
        int user_id = AutorizedUser.id();
        log.info("get between Date {} - {} time {} - {} for user {} ", startDate, endDate,startTime, endTime, user_id);
        List<Meal> mealListFiltered = service.getBetweenDates(startDate != null? startDate : DateTimeUtil.minDate, endDate != null? endDate : DateTimeUtil.maxDate, user_id);
        return MealsUtil.getFilteredWithExceeded(mealListFiltered,startTime != null? startTime : LocalTime.MIN, endTime != null? endTime : LocalTime.MAX, AutorizedUser.get_calories_per_day());
    }

    //добавить методы CRUD по аналогии с abstract user controller only for meal and get meal.Autorized.user_id
}
