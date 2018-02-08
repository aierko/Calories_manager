package com.javaproject.caloriesmanager.model;

import javax.naming.Name;

public abstract class AbstractBaseEntity {
    public AbstractBaseEntity(Integer id) {
        this.id = id;
    }

    protected Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isNew() {
        return this.id == null;
    }

    @Override
    public String toString() {
        return String.format("Entity %s (%s)", getClass().getName(),id);
    }
}

