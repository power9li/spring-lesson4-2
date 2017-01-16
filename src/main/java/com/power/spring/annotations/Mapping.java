package com.power.spring.annotations;

import com.power.spring.enums.EventType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by shenli on 2017/1/2.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Mapping {
    public String path();
    public EventType[] events() default {
            EventType.PRE_INVOKE,
            EventType.AFTER_INVOKE};
}
