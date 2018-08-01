package com.administrator.shopkeepertablet.model.entity;

/**
 * Description:
 * Author CC
 * Time 2018/8/1
 */


public class MemberEntity {

    private String no;
    private String name;
    private String phone;
    private int score;
    private double money;
    private String id;
    private double rate;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "MemberEntity{" +
                "no='" + no + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", score=" + score +
                ", money=" + money +
                ", id='" + id + '\'' +
                ", rate=" + rate +
                '}';
    }
}
