package com.administrator.shopkeepertablet.model.entity.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/8/23.
 */

public class PayType {
    @SerializedName("PayType")
    String payType;

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }
}
