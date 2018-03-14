package com.javaproject.caloriesmanager;

import com.javaproject.caloriesmanager.model.Role;
import com.javaproject.caloriesmanager.model.User;

import java.util.Arrays;

import static com.javaproject.caloriesmanager.model.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;

public class UserTestData {
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ +1;
    public static final User USER = new User(USER_ID,"User","user@gmail.com","password", Role.ROLE_USER);
    public static final User ADMIN = new User(ADMIN_ID,"Admin","admin@gmail.com","password", Role.ROLE_ADMIN);
    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "registered", "roles");
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registered", "roles").isEqualTo(expected);
    }

}

