package com.javaproject.caloriesmanager.web.User;

import com.javaproject.caloriesmanager.AutorizedUser;
import com.javaproject.caloriesmanager.model.User;
import org.springframework.stereotype.Controller;

@Controller
public class ProfileRestController extends AbstractUserCntroller{

    public User get() {
        return super.get(AutorizedUser.id());
    }


    public void delete() {
        super.delete(AutorizedUser.id());
    }


    public void update(User user) {
        super.update(user, AutorizedUser.id());
    }
}
