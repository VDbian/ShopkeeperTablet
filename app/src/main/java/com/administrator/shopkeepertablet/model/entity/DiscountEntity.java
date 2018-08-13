package com.administrator.shopkeepertablet.model.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Description:
 * Author CC
 * Time 2018/8/13
 */


public class DiscountEntity {

    @SerializedName("Guid")
    private String id;

    @SerializedName("RESTAURANTID")
    private String restaurantId;

    @SerializedName("Count")
    private String count;

    @SerializedName("Name")
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DiscountEntity{" +
                "id='" + id + '\'' +
                ", restaurantId='" + restaurantId + '\'' +
                ", count='" + count + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
