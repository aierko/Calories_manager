package com.javaproject.caloriesmanager;

import com.javaproject.caloriesmanager.model.Role;
import com.javaproject.caloriesmanager.model.User;
import com.javaproject.caloriesmanager.repository.UserRepository;
import com.javaproject.caloriesmanager.service.UserService;
import com.javaproject.caloriesmanager.web.User.AdminRestController;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext appContext = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        System.out.println("beans" + Arrays.toString(appContext.getBeanDefinitionNames()));
        UserRepository userRepos = (UserRepository) appContext.getBean("mockUserRepository");
        userRepos.getAll();
        //UserService userServ = appContext.getBean(UserService.class);
        AdminRestController adminRestControl = appContext.getBean(AdminRestController.class);
        //userServ.create(new User(null, "user name", "email","password", Role.ROLE_ADMIN));
        adminRestControl.create(new User(null, "user name","email", "password",Role.ROLE_ADMIN));
        appContext.close();

    }



}

