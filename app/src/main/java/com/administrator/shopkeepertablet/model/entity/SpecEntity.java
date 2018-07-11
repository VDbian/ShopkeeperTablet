package com.administrator.shopkeepertablet.model.entity;

import com.google.gson.annotations.SerializedName;

import org.litepal.crud.LitePalSupport;

/**
 * Description:
 * Author CC
 * Time 2018/7/9
 */


public class SpecEntity extends LitePalSupport {
//    @SerializedName("GUID")
//    private String uId;
    @SerializedName("ProtuctID")
    private String productId;
    @SerializedName("Name")
    private String name;
    @SerializedName("Price")
    private double price;
    @SerializedName("RESTAURANTID")
    private String restaurantId;
    @SerializedName("ProtuctName")
    private String productName;

//    public String getuId() {
//        return uId;
//    }
//
//    public void setuId(String id) {
//        this.uId = id;
//    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "SpecEntity{" +
//                "uId='" + uId + '\'' +
                ", productId='" + productId + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", restaurantId='" + restaurantId + '\'' +
                ", productName='" + productName + '\'' +
                '}';
    }
}
