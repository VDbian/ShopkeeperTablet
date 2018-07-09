package com.administrator.shopkeepertablet.model.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Description:
 * Author CC
 * Time 2018/7/9
 */


public class SeasonEntity {

    @SerializedName("GUID")
    private String id;
    @SerializedName("ProtuctID")
    private String productId;
    @SerializedName("Name")
    private String name;
    @SerializedName("Price")
    private String price;
    @SerializedName("RESTAURANTID")
    private String restaurantId;
    @SerializedName("ProtuctName")
    private String productName;
    @SerializedName("Type")
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "SeasonEntity{" +
                "id='" + id + '\'' +
                ", productId='" + productId + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", restaurantId='" + restaurantId + '\'' +
                ", productName='" + productName + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
