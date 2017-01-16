package com.power.spring.base;

import com.power.spring.annotations.MyAutowired;

import java.io.Serializable;

/**
 * Created by shenli on 2017/1/16.
 */
public abstract class BaseService<T> {

    @MyAutowired
    private BaseDao<T> dao;

    public boolean saveDomain(T domain){
        System.out.println("check domain obj " + domain);
        doCheckDomain(domain);
        System.out.println("save domain obj " + domain);
        dao.saveDomain(domain);
        return true;
    }

    public boolean deleteDomain(Serializable id) {
        return dao.deleteDomain(id);
    }

    public BaseDao<T> getDao() {
        return dao;
    }

    protected void doCheckDomain(T domain) {
        // child can override
    }

}
