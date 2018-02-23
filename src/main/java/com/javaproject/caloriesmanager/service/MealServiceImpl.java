package com.javaproject.caloriesmanager.service;

import com.javaproject.caloriesmanager.model.Meal;
import com.javaproject.caloriesmanager.repository.MealRepository;
import com.javaproject.caloriesmanager.util.ValidationUtil;
import com.javaproject.caloriesmanager.util.exception.NFE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MealServiceImpl implements MealService {
    private final MealRepository repository;// analog User Repository/

    @Autowired
    public MealServiceImpl(MealRepository repository) {
        this.repository = repository;
    }

    @Override
    public Meal get(int id, int userId) {
        return ValidationUtil.checkNotFoundWithId(repository.get(id, userId), id);
    }

    @Override
    public void delete(int id, int userId) {
        ValidationUtil.checkNotFoundWithId(repository.get(id,userId),id);
    }

    @Override
    public List<Meal> getBetweenDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return repository.getBetween(startDateTime,endDateTime,userId);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return repository.getAll(userId);
    }

    @Override
    public Meal update(Meal meal, int userId) {
        return ValidationUtil.checkNotFoundWithId(repository.save(meal,userId),meal.getId());
    }

    @Override
    public Meal create(Meal meal, int userId) {
        return repository.save(meal,userId);
    }
}
