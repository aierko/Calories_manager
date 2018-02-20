package com.javaproject.caloriesmanager.web.meal;

import com.javaproject.caloriesmanager.AutorizedUser;
import com.javaproject.caloriesmanager.model.Meal;
import com.javaproject.caloriesmanager.service.MealService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class MealRestController {
    private final MealService service;
     private  static  final Logger log = LoggerFactory.getLogger(MealRestController.class);
@Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }
    public Meal get(int id) {
        int user_id = AutorizedUser.id();
        log.info("get meal {} for user {}", id, user_id);
        return service.get(id, user_id);
    }
    //добавить методы CRUD по аналогии с abstract user controller only for meal and get meal.Autorizeduser_id
}
