package com.javaproject.caloriesmanager.web;

import com.javaproject.caloriesmanager.UserTestData;
import com.javaproject.caloriesmanager.model.User;
import com.javaproject.caloriesmanager.repository.mock.InMemoryUserRepositoryImpl;
import com.javaproject.caloriesmanager.util.exception.NFE;
import com.javaproject.caloriesmanager.web.User.AdminRestController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

import static com.javaproject.caloriesmanager.UserTestData.ADMIN;

@ContextConfiguration ("classpath:spring/spring-app.xml")
@RunWith(SpringRunner.class)
public class InMemoryAdminRestControllerSpringTest {
    @Autowired
    private AdminRestController controller;
    @Autowired
    private InMemoryUserRepositoryImpl repository;
    @Before
    public void setUp() throws Exception {
        repository.init();
    }
    @Test
    public void testDelete() throws Exception{
        controller.delete(UserTestData.USER_ID);
        Collection<User> users = controller.getAll();
        Assert.assertEquals(users.size(),1);
        Assert.assertEquals(users.iterator().next(),ADMIN);
    }
    @Test(expected = NFE.class)
    public void testDeleteNotFound() throws Exception{
        controller.delete(10);

    }
}
