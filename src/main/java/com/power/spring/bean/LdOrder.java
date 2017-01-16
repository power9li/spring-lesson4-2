package com.power.spring.bean;

/**
 * Created by shenli on 2017/1/12.
 */
public class LdOrder {

    private Long id;
    private int price;
    private Long userId;
    private String productName;

    public int getPrice() {
        return price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "LdOrder{" +
                "id=" + id +
                ", price=" + price +
                ", userId=" + userId +
                ", productName='" + productName + '\'' +
                '}';
    }
}
