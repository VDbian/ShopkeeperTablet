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
public class ProductKouWeiEntity  {
    @Id
    @SerializedName("GUID")
    private String uId;
    @SerializedName("No")
    private String no;
    @SerializedName("RESTAURANTID")
    private String restaurantId;
    @SerializedName("Type")
    private String type;
    @SerializedName("Name")
    private String name;
    @SerializedName("PatientId")
    private String productId;

    private boolean select;

    @Generated(hash = 523434934)
    public ProductKouWeiEntity(String uId, String no, String restaurantId,
            String type, String name, String productId, boolean select) {
        this.uId = uId;
        this.no = no;
        this.restaurantId = restaurantId;
        this.type = type;
        this.name = name;
        this.productId = productId;
        this.select = select;
    }

    @Generated(hash = 1633960264)
    public ProductKouWeiEntity() {
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String id) {
        this.uId = id;
    }

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

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    @Override
    public String toString() {
        return "ProductKouWeiEntity{" +
                "uId='" + uId + '\'' +
                ", no='" + no + '\'' +
                ", restaurantId='" + restaurantId + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getUId() {
        return this.uId;
    }

    public void setUId(String uId) {
        this.uId = uId;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public boolean getSelect() {
        return this.select;
    }
}
