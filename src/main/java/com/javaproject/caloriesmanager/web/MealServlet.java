package com.javaproject.caloriesmanager.web;

import com.javaproject.caloriesmanager.AutorizedUser;
import com.javaproject.caloriesmanager.model.Meal;
import com.javaproject.caloriesmanager.repository.mock.InMemoryMealRepositoryImpl;
import com.javaproject.caloriesmanager.repository.MealRepository;
import com.javaproject.caloriesmanager.util.MealsUtil;
import com.javaproject.caloriesmanager.web.meal.MealRestController;
import org.slf4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private ConfigurableApplicationContext springContext;
    private MealRestController mealRestController;
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
         springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml");
         mealRestController = springContext.getBean(MealRestController.class);
    }
    public void destroy(){
        springContext.close();
        super.destroy();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); //работаем по UTF-8
        String id = request.getParameter("id"); //берем запросы по айди

        Meal meal = new Meal(
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.valueOf(request.getParameter("calories")));

        if (request.getParameter("id").isEmpty()) {
            mealRestController.create(meal);
        } else {
            mealRestController.update(meal, getId(request));
        }

//        log.info(meal.isNew() ? "Create {}" : "Update {}", meal);
//        repository.save(meal, AutorizedUser.id()); //делаем сейв
        response.sendRedirect("meals");
    }
    private static final Logger log = getLogger(MealServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) { //если экшена нету даем весь список
            case "delete":
                int id = getId(request); //берем айдишник из запроса через метод
                mealRestController.delete(id);
               // repository.delete(id,AutorizedUser.id()); //удаляем из репозитория
                response.sendRedirect("meals"); //и делаем редирект
                break;
            case "create":
            case "update":
                final Meal meal = "create".equals(action) ?
                        new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000) :
                        mealRestController.get(getId(request));
                request.setAttribute("meal", meal);
                request.getRequestDispatcher("/mealForm.jsp").forward(request, response); //отправляемся на mealForm где у нас будет форма для редактирования
                break;
            case "all":
            default:
                request.setAttribute("meals",
                       mealRestController.getAll());
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
        }

        }
        private int getId(HttpServletRequest request) {
            String paramId = Objects.requireNonNull(request.getParameter("id"));
            return Integer.valueOf(paramId);
        }

    }
