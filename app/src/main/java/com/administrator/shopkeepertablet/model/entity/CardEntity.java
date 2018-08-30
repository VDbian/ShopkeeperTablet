package com.administrator.shopkeepertablet.model.entity;

/**
 * Description:
 * Author CC
 * Time 2018/8/4
 */


public class CardEntity {
    private String username;
    private String name;
    private double money;
    private String id;
    private String type;
    private double manPrice;
    private String beginTime;
    private String endTime;
    private boolean select;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public double getManPrice() {
        return manPrice;
    }

    public void setManPrice(double manPrice) {
        this.manPrice = manPrice;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String time(){
        return this.beginTime +"-"+this.endTime;
    }
}
