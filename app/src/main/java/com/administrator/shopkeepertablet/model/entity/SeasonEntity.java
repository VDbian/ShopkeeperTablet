package com.administrator.shopkeepertablet.model.entity;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Description:
 * Author CC
 * Time 2018/7/9
 */

@Entity
public class SeasonEntity {
    @Id
    @SerializedName("GUID")
    private String seasonId;
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

    @Generated(hash = 1711837320)
    public SeasonEntity(String seasonId, String productId, String name,
            String price, String restaurantId, String productName, String type) {
        this.seasonId = seasonId;
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.restaurantId = restaurantId;
        this.productName = productName;
        this.type = type;
    }

    @Generated(hash = 85610702)
    public SeasonEntity() {
    }

    public String getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(String seasonId) {
        this.seasonId = seasonId;
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
                "uId='" + seasonId + '\'' +
                ", productId='" + productId + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", restaurantId='" + restaurantId + '\'' +
                ", productName='" + productName + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
