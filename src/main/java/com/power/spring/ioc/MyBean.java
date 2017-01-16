package com.power.spring.ioc;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by shenli on 2017/1/16.
 */
public class MyBean {

    private String name;
    private Type genericType;
    private Object targetObject;
    private Set<Field> dependentFields = new HashSet<>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Type getGenericType() {
        return genericType;
    }

    public void setGenericType(Type genericType) {
        this.genericType = genericType;
    }

    public void addDependentField(Field field) {
        dependentFields.add(field);
    }

    public Set<Field> getDependentFields() {
        return dependentFields;
    }

    public void setTargetObject(Object targetObject) {
        this.targetObject = targetObject;
    }

    public Object getTargetObject() {
        return targetObject;
    }


    @Override
    public String toString() {
        return "MyBean{" +
                "name='" + name + '\'' +
                ", genericType=" + genericType +
                ", targetObject=" + targetObject +
                ", dependentFields=" + dependentFields +
                '}';
    }
}
