package com.administrator.shopkeepertablet.model.entity;

import com.google.gson.annotations.SerializedName;

import org.litepal.crud.LitePalSupport;

/**
 * Description:
 * Author CC
 * Time 2018/7/9
 */
public class ProductKouWeiEntity  extends LitePalSupport {
//    @SerializedName("GUID")
//    private String uId;
    @SerializedName("No")
    private String no;
    @SerializedName("RESTAURANTID")
    private String restaurantId;
    @SerializedName("Type")
    private String type;
    @SerializedName("Name")
    private String name;

//    public String getuId() {
//        return uId;
//    }
//
//    public void setuId(String id) {
//        this.uId = id;
//    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ProductKouWeiEntity{" +
//                "uId='" + uId + '\'' +
                ", no='" + no + '\'' +
                ", restaurantId='" + restaurantId + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
