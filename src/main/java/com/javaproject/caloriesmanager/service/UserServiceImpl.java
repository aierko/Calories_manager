package com.javaproject.caloriesmanager.service;

import com.javaproject.caloriesmanager.model.User;
import com.javaproject.caloriesmanager.repository.UserRepository;
import com.javaproject.caloriesmanager.util.exception.NFE;

import java.util.List;

import static com.javaproject.caloriesmanager.util.ValidationUtil.checkNotFound;
import static com.javaproject.caloriesmanager.util.ValidationUtil.checkNotFoundWithId;

public class UserServiceImpl implements UserService{
    private UserRepository repository;
    @Override
    public User create(User user) {
        return repository.save(user);
    }

    @Override
    public void delete(int id) throws NFE {
        checkNotFoundWithId(repository.delete(id),id);
    }

    @Override
    public User get(int id) throws NFE {
        return checkNotFoundWithId(repository.get(id),id);
    }

    @Override
    public User getByEmail(String email) throws NFE {
        return checkNotFound(repository.getByEmail(email),email);
    }

    @Override
    public void update(User user) {
            repository.save(user);
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }
}