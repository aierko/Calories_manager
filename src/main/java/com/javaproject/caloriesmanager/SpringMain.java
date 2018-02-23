package com.javaproject.caloriesmanager;

import com.javaproject.caloriesmanager.model.Role;
import com.javaproject.caloriesmanager.model.User;
import com.javaproject.caloriesmanager.repository.UserRepository;
import com.javaproject.caloriesmanager.to.MealWithExceed;
import com.javaproject.caloriesmanager.web.User.AdminRestController;
import com.javaproject.caloriesmanager.web.meal.MealRestController;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class SpringMain {
    public static void main(String[] args) {
        try 
            ( ConfigurableApplicationContext appContext = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("beans" + Arrays.toString(appContext.getBeanDefinitionNames()));
            //UserRepository userRepos = (UserRepository) appContext.getBean(UserRepository.class);
            //userRepos.getAll();
            //UserService userServ = appContext.getBean(UserService.class);
            AdminRestController adminRestControl = appContext.getBean(AdminRestController.class);
            //userServ.create(new User(null, "user name", "email","password", Role.ROLE_ADMIN));
            adminRestControl.create(new User(null, "user name", "email", "password", Role.ROLE_ADMIN));
            System.out.println();
            MealRestController mealController = appContext.getBean(MealRestController.class);
            List<MealWithExceed> filteredMeals = mealController.getBetween(LocalDate.of(2017, Month.DECEMBER, 30), LocalTime.of(12, 20), LocalDate.of(2018, Month.FEBRUARY, 23), LocalTime.of(12, 30));
            filteredMeals.forEach(System.out::println);
            appContext.close();
        }


    }



}

