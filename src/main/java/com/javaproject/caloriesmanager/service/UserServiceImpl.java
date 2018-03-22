package com.javaproject.caloriesmanager.service;

import com.javaproject.caloriesmanager.model.User;
import com.javaproject.caloriesmanager.repository.UserRepository;
import com.javaproject.caloriesmanager.util.exception.NFE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static com.javaproject.caloriesmanager.util.ValidationUtil.checkNotFound;
import static com.javaproject.caloriesmanager.util.ValidationUtil.checkNotFoundWithId;
@Service
public class UserServiceImpl implements UserService{
    private UserRepository repository;
    @Autowired
public  UserServiceImpl(UserRepository repository){
    this.repository = repository;
}
    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User create(User user) {
        Assert.notNull(user,"user must not be null");
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
        Assert.notNull(email, "email must not be null");
        return checkNotFound(repository.getByEmail(email),email);
    }

    @Override
    public void update(User user) {
        Assert.notNull(user,"user must not be null");
        checkNotFoundWithId(repository.save(user), user.getId());
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }
}
