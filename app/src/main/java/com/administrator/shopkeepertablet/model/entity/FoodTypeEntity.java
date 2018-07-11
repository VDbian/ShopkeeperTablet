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
public class FoodTypeEntity {
@Id
    @SerializedName("PRODUCTTYPEID")
    private String productTypeId;

    @SerializedName("RESTAURANTID")
    private String restaurantId;

    @SerializedName("PRODUCTTYPENAME")
    private String productTypeName;

    @SerializedName("ORDERNO")
    private String orderNo;

    @Generated(hash = 927442999)
    public FoodTypeEntity(String productTypeId, String restaurantId,
            String productTypeName, String orderNo) {
        this.productTypeId = productTypeId;
        this.restaurantId = restaurantId;
        this.productTypeName = productTypeName;
        this.orderNo = orderNo;
    }

    @Generated(hash = 431357427)
    public FoodTypeEntity() {
    }

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
