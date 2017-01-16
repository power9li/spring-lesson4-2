package com.power.spring.service;

import com.power.spring.annotations.MyComponent;
import com.power.spring.base.BaseService;
import com.power.spring.bean.Org;

/**
 * Created by shenli on 2017/1/16.
 */
@MyComponent(value = "myOrgService")
public class OrgService extends BaseService<Org> {
}
