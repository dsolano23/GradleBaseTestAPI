package com.gdt.baseClient.beans;

import java.lang.reflect.Field;

public abstract class AbstractBaseJson {

    public String getFieldValue(String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field field = this.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        Object o = field.get(this);
        if (o != null)
            return field.get(this).toString();
        return null;
    }

    public Object getFieldValue2(String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field field = this.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(this);
    }

    public Field getField(String fieldName) throws NoSuchFieldException {
        Field field = this.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field;
    }

}
