package com.javaproject.caloriesmanager.repository.mock;

import com.javaproject.caloriesmanager.model.User;
import com.javaproject.caloriesmanager.repository.UserRepository;

import java.util.Collections;
import java.util.List;

public class MockUserRepositoryImpl implements UserRepository{
    @Override
    public User save(User user) {
        return user;
    }

    @Override
    public boolean delete(int id) {
        return true;
    }

    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public User getByEmail(String email) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return Collections.emptyList();
    }
    //сделать логирование...
}
