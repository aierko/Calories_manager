package com.javaproject.caloriesmanager.repository;

import com.javaproject.caloriesmanager.model.User;

import java.io.StringReader;
import java.util.List;

public interface  UserRepository {
    User save(User user);
     boolean delete(int id);
     User get(int id);
     User getByEmail(String email);
     List<User> getAll();
}
