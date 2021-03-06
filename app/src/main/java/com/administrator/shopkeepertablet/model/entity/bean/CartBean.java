package com.administrator.shopkeepertablet.model.entity.bean;

import com.administrator.shopkeepertablet.model.entity.FoodEntity;
import com.administrator.shopkeepertablet.model.entity.ProductKouWeiEntity;
import com.administrator.shopkeepertablet.model.entity.SpecEntity;

import java.util.List;

/**
 * Description:
 * Author chencheng
 * Time 2018/7/13
 */

public class CartBean {
    private FoodEntity foodEntity;
    private double price;
    private List<FoodAddBean> foodAddBeanList;
    private String num;
    private String weight;
    private SpecEntity spec;
    private String unit;
    private List<ProductKouWeiEntity> productKouWeiEntity;
    private int giveNum;
    private String kouwei;

    public CartBean() {
        num="0";
        unit = "份";
        giveNum = 0;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public FoodEntity getFoodEntity() {
        return foodEntity;
    }

    public void setFoodEntity(FoodEntity foodEntity) {
        this.foodEntity = foodEntity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<FoodAddBean> getFoodAddBeanList() {
        return foodAddBeanList;
    }

    public void setFoodAddBeanList(List<FoodAddBean> foodAddBeanList) {
        this.foodAddBeanList = foodAddBeanList;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public SpecEntity getSpec() {
        return spec;
    }

    public void setSpec(SpecEntity spec) {
        this.spec = spec;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public List<ProductKouWeiEntity> getProductKouWeiEntity() {
        return productKouWeiEntity;
    }

    public void setProductKouWeiEntity(List<ProductKouWeiEntity> productKouWeiEntity) {
        this.productKouWeiEntity = productKouWeiEntity;
    }

    public int getGiveNum() {
        return giveNum;
    }

    public void setGiveNum(int giveNum) {
        this.giveNum = giveNum;
    }

    public String getKouwei() {
        return kouwei;
    }

    public void setKouwei(String kouwei) {
        this.kouwei = kouwei;
    }

    @Override
    public String toString() {
        return "CartBean{" +
                "foodEntity=" + foodEntity +
                ", price=" + price +
                ", foodAddBeanList=" + foodAddBeanList +
                ", num='" + num + '\'' +
                ", spec=" + spec +
                ", unit='" + unit + '\'' +
                ", productKouWeiEntity=" + productKouWeiEntity +
                ", giveNum='" + giveNum + '\'' +
                ", kouwei='" + kouwei + '\'' +
                '}';
    }
}
