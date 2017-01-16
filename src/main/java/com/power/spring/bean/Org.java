package com.power.spring.bean;

/**
 * Created by shenli on 2017/1/16.
 */
public class Org {

    private String orgName;
    private Integer id;

    public Org(){}

    public Org(String orgName, Integer id) {
        this.orgName = orgName;
        this.id = id;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Org{" +
                "id=" + id +
                ", orgName='" + orgName + '\'' +
                '}';
    }
}
