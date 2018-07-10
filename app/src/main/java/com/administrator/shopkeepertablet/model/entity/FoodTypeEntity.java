package com.administrator.shopkeepertablet.model.entity;

import com.google.gson.annotations.SerializedName;

import org.litepal.crud.LitePalSupport;

/**
 * Description:
 * Author CC
 * Time 2018/7/9
 */


public class FoodTypeEntity extends LitePalSupport {

    @SerializedName("PRODUCTTYPEID")
    private String productTypeId;

    @SerializedName("RESTAURANTID")
    private String restaurantId;

    @SerializedName("PRODUCTTYPENAME")
    private String productTypeName;

    @SerializedName("ORDERNO")
    private String orderNo;

    public String getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(String productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Override
    public String toString() {
        return "FoodTypeEntity{" +
                "productTypeId='" + productTypeId + '\'' +
                ", restaurantId='" + restaurantId + '\'' +
                ", productTypeName='" + productTypeName + '\'' +
                ", orderNo='" + orderNo + '\'' +
                '}';
    }
}
