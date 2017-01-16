package com.power.learn.utils;

import com.power.spring.base.BaseService;
import com.power.spring.bean.LdOrder;
import com.power.spring.bean.Org;
import com.power.spring.ioc.LdIocFactory;
import com.power.spring.service.OrderService;
import com.power.spring.service.OrgService;

/**
 * Created by shenli on 2017/1/16.
 */
public class TestLdIocFactory {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        LdIocFactory factory=new LdIocFactory();
        factory.scanPackages(new String[]{"com.power.spring"});

        System.out.println("==============================");
        BaseService<LdOrder> orderService = factory.getBean(OrderService.class);
        System.out.println("factory.getBean(OrderService.class) = " + orderService);

        LdOrder ldOrder = new LdOrder();
        ldOrder.setPrice(100);
        ldOrder.setProductName("table");
        ldOrder.setUserId(3L);
        orderService.saveDomain(ldOrder);

        System.out.println("==============================");
        BaseService<Org> orgService2 = factory.getBean(OrgService.class);
        System.out.println("factory.getBean(OrgService.class)=" + orgService2);
        orgService2.saveDomain(new Org("RD",1));

        System.out.println("==============================");
        BaseService orgService = factory.getBean("myOrgService");
        System.out.println("factory.getBean(\"myOrgService\") = " + orgService);
        orgService.deleteDomain(1);


    }
}
