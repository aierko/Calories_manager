package com.javaproject.caloriesmanager.service;

import com.javaproject.caloriesmanager.model.User;
import com.javaproject.caloriesmanager.util.exception.NFE;

import java.util.List;

public interface UserService {
    User create(User user);

    void delete(int id) throws NFE;

    User get(int id) throws NFE;

    User getByEmail(String email) throws NFE;

    void update(User user);

    List<User> getAll();

}
