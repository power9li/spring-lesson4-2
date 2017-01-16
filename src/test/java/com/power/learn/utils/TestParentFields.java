package com.power.learn.utils;

import com.power.spring.service.UserService;
import com.power.spring.service.impl.UserServiceImpl;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by shenli on 2017/1/16.
 */
public class TestParentFields {

    public static void main(String[] args) throws IllegalAccessException {
        UserService us = new UserServiceImpl();

        TreeMap<String, String> map = convertBeanToMap(us);
        for (Map.Entry<String, String> entity : map.entrySet()) {
            System.out.println(entity.getKey()+":"+entity.getValue());
        }
    }

    public static TreeMap<String, String> convertBeanToMap(Object bean) throws IllegalArgumentException,IllegalAccessException {
        TreeMap<String, String> map = new TreeMap<String, String>();
        Class<?> clazz = bean.getClass();
        for(; clazz != Object.class;clazz = clazz.getSuperclass()){
            System.out.println("clazz = " + clazz);
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {// 获取bean的属性和值
                System.out.println("field = " + field);
                field.setAccessible(true);
                if (field.get(bean) != null) {
                    if (field.get(bean) instanceof Double) {
                        map.put(field.getName(), String.valueOf(((Double) field.get(bean))));
                    } else {
                        map.put(field.getName(), field.get(bean).toString());
                    }
                }
            }
        }
        return map;
    }
}
