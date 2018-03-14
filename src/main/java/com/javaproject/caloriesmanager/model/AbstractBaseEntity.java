package com.javaproject.caloriesmanager.model;

public abstract class AbstractBaseEntity {
    public AbstractBaseEntity(Integer id) {
        this.id = id;
    }

    protected Integer id;
    public static final int START_SEQ = 100000;

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
        return String.format("Entity %s (%s)", getClass().getName(), id);
    }

    public AbstractBaseEntity() {
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AbstractBaseEntity that = (AbstractBaseEntity) obj;
        return id != null && id.equals(that.id);
    }

}

