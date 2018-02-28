package com.javaproject.caloriesmanager.web;

import com.javaproject.caloriesmanager.UserTestData;
import com.javaproject.caloriesmanager.model.User;
import com.javaproject.caloriesmanager.repository.mock.InMemoryUserRepositoryImpl;
import com.javaproject.caloriesmanager.util.exception.NFE;
import com.javaproject.caloriesmanager.web.User.AdminRestController;
import org.junit.*;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.Collection;

import static com.javaproject.caloriesmanager.UserTestData.ADMIN;

public class InMemoryAdminRestControllerTest {
    public static ConfigurableApplicationContext appContext;
    public static AdminRestController controller;

    @BeforeClass
    public static void beforeClass() {
        appContext = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        System.out.println("\n" + Arrays.toString(appContext.getBeanDefinitionNames()) + "\n");
        controller = appContext.getBean(AdminRestController.class);
    }

    @AfterClass
    public static void afterClass() {
        appContext.close();
    }

    @Before
    public void setUp() throws Exception {
        InMemoryUserRepositoryImpl repository = appContext.getBean(InMemoryUserRepositoryImpl.class);
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
