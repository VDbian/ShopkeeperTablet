package com.administrator.shopkeepertablet.model.entity.bean;

/**
 * Description:
 * Author chencheng
 * Time 2018/7/12
 */

public class FoodAddBean {
    private String id;
    private String name;
    private double price;
    private int num;
    private boolean select;

    public FoodAddBean(String id, String name, double price, int num, boolean select) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.num = num;
        this.select = select;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
