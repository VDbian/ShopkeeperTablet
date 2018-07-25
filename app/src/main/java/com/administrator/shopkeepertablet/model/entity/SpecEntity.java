package com.administrator.shopkeepertablet.model.entity;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Description:
 * Author CC
 * Time 2018/7/9
 */

@Entity
public class SpecEntity  {
    @Id
    @SerializedName("GUID")
    private String uId;
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

    @Generated(hash = 1743207129)
    public SpecEntity(String uId, String productId, String name, double price,
            String restaurantId, String productName) {
        this.uId = uId;
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.restaurantId = restaurantId;
        this.productName = productName;
    }

    @Generated(hash = 168102231)
    public SpecEntity() {
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
                "uId='" + uId + '\'' +
                ", productId='" + productId + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", restaurantId='" + restaurantId + '\'' +
                ", productName='" + productName + '\'' +
                '}';
    }

    public String getUId() {
        return this.uId;
    }

    public void setUId(String uId) {
        this.uId = uId;
    }
}
