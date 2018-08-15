package com.administrator.shopkeepertablet.model.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Description:
 * Author CC
 * Time 2018/8/15
 */


public class ElseCouponEntity {
    @SerializedName("GUID")
    private String id;
    @SerializedName("Name")
    private String name;
    @SerializedName("Counts")
    private int counts;
    @SerializedName("MaxCout")
    private int maxUseCount;
    @SerializedName("Pice")
    private double price;
    @SerializedName("State")
    private String state;
    @SerializedName("RESTAURANTID")
    private String shopId;
    @SerializedName("MerchantName")
    private String merchantName;

    private boolean select;

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    public int getMaxUseCount() {
        return maxUseCount;
    }

    public void setMaxUseCount(int maxUseCount) {
        this.maxUseCount = maxUseCount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }
}
