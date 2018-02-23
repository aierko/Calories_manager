package com.javaproject.caloriesmanager.repository.mock;

import com.javaproject.caloriesmanager.model.Meal;
import com.javaproject.caloriesmanager.repository.MealRepository;
import com.javaproject.caloriesmanager.util.DateTimeUtil;
import com.javaproject.caloriesmanager.util.MealsUtil;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.javaproject.caloriesmanager.repository.mock.InMemoryUserRepositoryImpl.ADMIN_ID;
import static com.javaproject.caloriesmanager.repository.mock.InMemoryUserRepositoryImpl.USER_ID;
@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {

    private Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();

    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(meal -> save(meal, USER_ID));
        save(new Meal(LocalDateTime.of(2018, Month.FEBRUARY, 20, 14, 0), "admin_lunch", 500), ADMIN_ID);
        save(new Meal(LocalDateTime.of(2018, Month.FEBRUARY, 20, 20, 0), "admin_dinner", 450), ADMIN_ID);

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

    private Stream<Meal> getAllasStream(int user_id) {
        Map<Integer, Meal> mealMap = repository.get(user_id);
        return mealMap == null ? Stream.empty() : mealMap.values().stream().sorted(Comparator.comparing(Meal::getDateTime).reversed());
    }

    @Override
    public List<Meal> getAll(int user_id) {
        return getAllasStream(user_id).collect(Collectors.toList());
    }

    @Override
    public List<Meal> getBetween(LocalDateTime start_time, LocalDateTime end, int user_id) {
        return getAllasStream(user_id).filter(meal -> DateTimeUtil.isBetween(meal.getDateTime(),start_time,end)).collect(Collectors.toList());
    }

}
