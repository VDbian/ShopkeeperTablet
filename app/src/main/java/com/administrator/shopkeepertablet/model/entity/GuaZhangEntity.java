package com.administrator.shopkeepertablet.model.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Description:
 * Author CC
 * Time 2018/8/19
 */


public class GuaZhangEntity {
    @SerializedName("GUID")
    String guid;
    @SerializedName("Name")
    String name;
    @SerializedName("RESTAURANTID")
    String restaurantId;
    @SerializedName("Phone")
    private String phone;
    @SerializedName("Ramark")
    private String remark;

    boolean isSelected;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @Override
    public String toString() {
        return "GuaZhangEntity{" +
                "guid='" + guid + '\'' +
                ", name='" + name + '\'' +
                ", restaurantId='" + restaurantId + '\'' +
                ", phone='" + phone + '\'' +
                ", remark='" + remark + '\'' +
                ", isSelected=" + isSelected +
                '}';
    }
}
