package com.administrator.shopkeepertablet.model.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Description:
 * Author CC
 * Time 2018/7/18
 */


public class ReturnReasonEntity {

    /**
     * GUID : eafdcc78-df9c-430a-aa42-0d7d0d63598f
     * RESTAURANTID : 4B176F0E-0553-4094-8181-5048641B20EF
     * Remark : 菜品不好吃
     */
    @SerializedName("GUID")
    private String id;
    @SerializedName("RESTAURANTID")
    private String restaurantId;
    @SerializedName("Remark")
    private String remark;

    private boolean select;

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }
}
