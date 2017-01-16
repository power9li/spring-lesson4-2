package com.power.learn.utils;

import java.lang.reflect.Field;

/**
 * Created by shenli on 2017/1/16.
 */
public class TestParentField {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        System.out.println(Son.class.isAssignableFrom(Parent.class));
        System.out.println(Parent.class.isAssignableFrom(Son.class));
        System.out.println(Son.class.isInstance(new Parent()));
        System.out.println(Parent.class.isInstance(new Son()));
        Son son = new Son();
        Field age = son.getClass().getSuperclass().getDeclaredField("age");
        age.setAccessible(true);
        age.set(son, 5);
        System.out.println("son.getAge() = " + son.getAge());
    }

}

class Parent{

    private int age;

    public int getAge(){
        return age;
    }
}

class Son extends Parent{


}
