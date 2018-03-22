package com.javaproject.caloriesmanager.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import static com.javaproject.caloriesmanager.MealTestData.*;
import static com.javaproject.caloriesmanager.UserTestData.USER_ID;

@ContextConfiguration({ //для Spring Test
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {
@Autowired
    private MealService service;
@Test
    public void TestDelete()throws Exception{
    service.delete(MEAL_ID+1,USER_ID);
    assertMatch(service.getAll(USER_ID),MEAL6,MEAL5,MEAL4,MEAL3,MEAL2);

}
}


