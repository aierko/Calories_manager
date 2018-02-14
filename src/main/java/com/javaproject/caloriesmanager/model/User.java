package com.javaproject.caloriesmanager.model;

import java.util.Date;
import java.util.Set;

public class User extends AbstractNamedEntity {

    private String email;
    private String password;
    private Date registred_time = new Date();
    private Set<Role> roles;
    private int calories_per_day;
    private boolean enabled_or = true;

    public boolean isEnabled_or() {
        return enabled_or;
    }

    public void setEnabled_or(boolean enabled_or) {
        this.enabled_or = enabled_or;
    }


    public User(Integer id, String name, String email, String password, Date regitred_time, Set<Role> roles, int calories_per_day,boolean enabled_or_not) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.registred_time = regitred_time;
        this.roles = roles;
        this.calories_per_day = calories_per_day;
        this.enabled_or = enabled_or_not;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistred_time() {
        return registred_time;
    }

    public void setRegistred_time(Date registred_time) {
        this.registred_time = registred_time;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public int getCalories_per_day() {
        return calories_per_day;
    }

    public void setCalories_per_day(int calories_per_day) {
        this.calories_per_day = calories_per_day;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", registred_time=" + registred_time +
                ", roles=" + roles +
                ", calories_per_day=" + calories_per_day +
                ", enabled_or=" + enabled_or +
                '}';
    }
}