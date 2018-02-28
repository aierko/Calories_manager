package com.javaproject.caloriesmanager;

import com.javaproject.caloriesmanager.model.Role;
import com.javaproject.caloriesmanager.model.User;

public class UserTestData {
    public static final int USER_ID = 1;
    public static final int ADMIN_ID = 2;
    public static final User USER = new User(USER_ID,"User","user@gmail.com","password", Role.ROLE_USER);
    public static final User ADMIN = new User(ADMIN_ID,"Admin","admin@gmail.com","password", Role.ROLE_ADMIN);
}

