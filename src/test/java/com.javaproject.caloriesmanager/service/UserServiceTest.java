package com.javaproject.caloriesmanager.service;

import com.javaproject.caloriesmanager.SpringMain;
import com.javaproject.caloriesmanager.model.Role;
import com.javaproject.caloriesmanager.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

import static com.javaproject.caloriesmanager.UserTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class UserServiceTest {
    @Autowired
    private UserService service;

    @Test
    public void create() throws Exception {
        User newUser = new User(null, "New", "new@gmail.com", "newPass",  Collections.singleton(Role.ROLE_USER), 1555, false);
        User created = service.create(newUser);
        newUser.setId(created.getId());
        assertMatch(service.getAll(), ADMIN, newUser, USER);
    }
    @Test
    public void delete() throws Exception{
        service.delete(USER_ID);
        assertMatch(service.getAll(), ADMIN);
    }
    @Test
    public void get() throws Exception{
        User user = service.get(USER_ID);
        assertMatch(user,USER);
    }
    @Test
    public void getByEmail() throws Exception{
        User user = service.getByEmail("user@gmail.com");
        assertMatch(user,USER);
    }
    @Test
    public void getAll() throws Exception{
        List<User> users = service.getAll();
        assertMatch(users,ADMIN,USER);
    }
    @Test
    public void update() throws Exception{
        User user = new User(USER);
        user.setName("UPDATE_NAME");
        user.setCalories_per_day(3000);
        service.update(user);
        assertMatch(service.get(USER_ID),user);
    }



}
