package com.javaproject.caloriesmanager.repository.mock;

import com.javaproject.caloriesmanager.model.Meal;
import com.javaproject.caloriesmanager.repository.MealRepository;
import com.javaproject.caloriesmanager.util.DateTimeUtil;
import com.javaproject.caloriesmanager.util.MealsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.javaproject.caloriesmanager.UserTestData.ADMIN_ID;
import static com.javaproject.caloriesmanager.UserTestData.USER_ID;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryMealRepositoryImpl.class);

    private Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();

    private AtomicInteger counter = new AtomicInteger(0);


    @PostConstruct
    public void postConstructor() {
        log.info("++ postConstructor");
    }
    @PreDestroy
    public void preDestroy(){
        log.info("++ preDestroy");
    }
    public Meal save(Meal meal, int user_id) {
        Map<Integer, Meal> mealMap = repository.computeIfAbsent(user_id, ConcurrentHashMap::new);
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            mealMap.put(meal.getId(), meal);
            return meal;
        }
        return mealMap.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id, int user_id) {
        Map<Integer, Meal> mealMap = repository.get(user_id);
        return mealMap != null && mealMap.remove(id) != null;
    }

    @Override
    public Meal get(int id, int user_id) {
        Map<Integer, Meal> mealMap = repository.get(user_id);
        return mealMap == null ? null : mealMap.get(id);
    }

    private List<Meal> getAllasStream(int userId, Predicate<Meal> filter) {
        Map<Integer, Meal> meals = repository.get(userId);
        return CollectionUtils.isEmpty(meals) ? Collections.emptyList() :
                meals.values().stream()
                        .filter(filter)
                        .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                        .collect(Collectors.toList());
    }


        @Override
    public List<Meal> getAll(int user_id) {
        return getAllasStream(user_id, meal -> true);
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId)  {
        return getAllasStream(userId, meal -> DateTimeUtil.isBetween(meal.getDateTime(), startDateTime, endDateTime));
    }

}
