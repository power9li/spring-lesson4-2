package com.power.spring.dao;

import com.power.spring.annotations.MyComponent;
import com.power.spring.base.BaseDao;
import com.power.spring.bean.Org;

import java.io.Serializable;

/**
 * Created by shenli on 2017/1/16.
 */
@MyComponent
public class OrgDao extends BaseDao<Org> {

    @Override
    public void saveDomain(Org org) {
        System.out.println("OrgDao.saveDomain");
    }

    @Override
    public boolean deleteDomain(Serializable id) {
        System.out.println("OrgDao.deleteDomain");
        return false;
    }
}
