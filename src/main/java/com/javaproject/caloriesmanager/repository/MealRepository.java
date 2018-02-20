package com.javaproject.caloriesmanager.repository;

import com.javaproject.caloriesmanager.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

public interface MealRepository {
     Meal save(Meal meal,int user_id);
     boolean delete(int id, int user_id);
     Meal get(int id, int user_id);
     List<Meal> getAll(int user_id);
     List<Meal> getBetween(LocalDateTime start_time, LocalDateTime end, int user_id);
}

