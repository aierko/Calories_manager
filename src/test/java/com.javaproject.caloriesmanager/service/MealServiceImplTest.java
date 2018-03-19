package com.javaproject.caloriesmanager.service;

import com.javaproject.caloriesmanager.model.Role;
import com.javaproject.caloriesmanager.model.User;
import com.javaproject.caloriesmanager.util.exception.NFE;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static com.javaproject.caloriesmanager.UserTestData.*;
import static org.junit.Assert.*;
@ContextConfiguration({ //для Spring Test
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceImplTest {
    @Autowired
    private UserService service;


    @Test
    public void get() throws NFE{
        User user = service.get(USER_ID);
        assertMatch(user, USER);
    }

    @Test(expected = NFE.class)
    public void getNotFound() throws NFE {
        service.get(1);
    }

    @Test
    public void getByEmail() throws NFE {
        User user = service.getByEmail("user@yandex.ru");
        assertMatch(user, USER);
    }


        @Test
    public void delete() throws NFE {
        service.delete(USER_ID);
        assertMatch(service.getAll(), ADMIN);

    }

    @Test(expected = NFE.class)
    public void notFoundDelete() throws NFE {
        service.delete(1);
    }


    @Test
    public void getAll() throws NFE{
        List<User> all = service.getAll();
        assertMatch(all, ADMIN, USER);
    }

    @Test
    public void update() throws NFE{
        User updated = new User(USER);
        updated.setName("UpdatedName");
        updated.setCaloriesPerDay(330);
        service.update(updated);
        assertMatch(service.get(USER_ID), updated);
    }

    @Test
    public void create() throws NFE  {
        User newUser = new User(null, "New", "new@gmail.com", "newPass", 1555, false, Collections.singleton(Role.ROLE_USER));
        User created = service.create(newUser);
        newUser.setId(created.getId());
        assertMatch(service.getAll(), ADMIN, newUser, USER);

    }
}