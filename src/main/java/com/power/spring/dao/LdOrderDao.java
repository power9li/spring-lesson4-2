package com.power.spring.dao;

import com.power.spring.annotations.MyComponent;
import com.power.spring.base.BaseDao;
import com.power.spring.bean.LdOrder;

import java.io.Serializable;

/**
 * Created by shenli on 2017/1/17.
 */
@MyComponent
public class LdOrderDao extends BaseDao<LdOrder> {

    @Override
    public void saveDomain(LdOrder ldOrder) {
        System.out.println("LdOrderDao.saveDomain");
    }

    @Override
    public boolean deleteDomain(Serializable id) {
        System.out.println("LdOrderDao.deleteDomain");
        return false;
    }
}
