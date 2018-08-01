package com.administrator.shopkeepertablet.model.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Description:
 * Author CC
 * Time 2018/8/1
 */


public class RechargeEntity {

    @SerializedName("ID")
    private String id;
    @SerializedName("StaffDepart")
    private String staffDepart;
    @SerializedName("StaffCard")
    private String staffCard;
    @SerializedName("StaffName")
    private String staffName;
    @SerializedName("StaffTel")
    private String staffTel;
    @SerializedName("StaffEmail")
    private String staffEmail;
    @SerializedName("StaffAddTime")
    private String staffAddTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStaffDepart() {
        return staffDepart;
    }

    public void setStaffDepart(String staffDepart) {
        this.staffDepart = staffDepart;
    }

    public String getStaffCard() {
        return staffCard;
    }

    public void setStaffCard(String staffCard) {
        this.staffCard = staffCard;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffTel() {
        return staffTel;
    }

    public void setStaffTel(String staffTel) {
        this.staffTel = staffTel;
    }

    public String getStaffEmail() {
        return staffEmail;
    }

    public void setStaffEmail(String staffEmail) {
        this.staffEmail = staffEmail;
    }

    public String getStaffAddTime() {
        return staffAddTime;
    }

    public void setStaffAddTime(String staffAddTime) {
        this.staffAddTime = staffAddTime;
    }

    @Override
    public String toString() {
        return "RechargeEntity{" +
                "id='" + id + '\'' +
                ", staffDepart='" + staffDepart + '\'' +
                ", staffCard='" + staffCard + '\'' +
                ", staffName='" + staffName + '\'' +
                ", staffTel='" + staffTel + '\'' +
                ", staffEmail='" + staffEmail + '\'' +
                ", staffAddTime='" + staffAddTime + '\'' +
                '}';
    }
}
