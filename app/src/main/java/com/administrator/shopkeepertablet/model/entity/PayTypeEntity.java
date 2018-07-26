package com.administrator.shopkeepertablet.model.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Description:
 * Author CC
 * Time 2018/7/26
 */


public class PayTypeEntity {
    @SerializedName("pice")
    String pice;
    @SerializedName("PayType")
    String payType;

    public String getPice() {
        return pice;
    }

    public void setPice(String pice) {
        this.pice = pice;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    @Override
    public String toString() {
        return "PayTypeEntity{" +
                "pice='" + pice + '\'' +
                ", payType='" + payType + '\'' +
                '}';
    }
}
