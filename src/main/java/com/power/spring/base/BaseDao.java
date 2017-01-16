package com.power.spring.base;

import java.io.Serializable;

/**
 * Created by shenli on 2017/1/16.
 */
public abstract class BaseDao<T> {

    public abstract void saveDomain(T t);

    public abstract boolean deleteDomain(Serializable id);

}
