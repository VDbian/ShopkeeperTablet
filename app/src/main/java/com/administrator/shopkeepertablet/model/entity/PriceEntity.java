package com.administrator.shopkeepertablet.model.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Description:
 * Author CC
 * Time 2018/8/16
 */


public class PriceEntity {
    @SerializedName("zenupice")//赠送
    private double zenupice;
    @SerializedName("memberpice")//会员优惠
    private double memberpice;
    @SerializedName("mansonpice")//满送优惠
    private double mansonpice;
    @SerializedName("kaquanpice")//卡券优惠
    private double kaquanpice;
    @SerializedName("yufupice")//预付
    private double yufupice;
    @SerializedName("DistancePice")
    private double distancePice;
    @SerializedName("PackagPice")
    private double packagPice;
    @SerializedName("canju")
    private double canju;
    @SerializedName("youhui")
    private double youhui;
    @SerializedName("yuanjia")
    private double yuanjia;
    @SerializedName("yintui")
    private double yintui;
    @SerializedName("yinfu")
    private double yinfu;
    @SerializedName("memberpiceNew")
    private double memberpiceNew;
    @SerializedName("memberzenupice")//会员赠送
    private double memberzenupice;

    public double getZenupice() {
        return zenupice;
    }

    public void setZenupice(double zenupice) {
        this.zenupice = zenupice;
    }

    public double getMemberpice() {
        return memberpice;
    }

    public void setMemberpice(double memberpice) {
        this.memberpice = memberpice;
    }

    public double getMansonpice() {
        return mansonpice;
    }

    public void setMansonpice(double mansonpice) {
        this.mansonpice = mansonpice;
    }

    public double getKaquanpice() {
        return kaquanpice;
    }

    public void setKaquanpice(double kaquanpice) {
        this.kaquanpice = kaquanpice;
    }

    public double getYufupice() {
        return yufupice;
    }

    public void setYufupice(double yufupice) {
        this.yufupice = yufupice;
    }

    public double getDistancePice() {
        return distancePice;
    }

    public void setDistancePice(double distancePice) {
        this.distancePice = distancePice;
    }

    public double getPackagPice() {
        return packagPice;
    }

    public void setPackagPice(double packagPice) {
        this.packagPice = packagPice;
    }

    public double getCanju() {
        return canju;
    }

    public void setCanju(double canju) {
        this.canju = canju;
    }

    public double getYouhui() {
        return youhui;
    }

    public void setYouhui(double youhui) {
        this.youhui = youhui;
    }

    public double getYuanjia() {
        return yuanjia;
    }

    public void setYuanjia(double yuanjia) {
        this.yuanjia = yuanjia;
    }

    public double getYintui() {
        return yintui;
    }

    public void setYintui(double yintui) {
        this.yintui = yintui;
    }

    public double getYinfu() {
        return yinfu;
    }

    public void setYinfu(double yinfu) {
        this.yinfu = yinfu;
    }

    public double getMemberpiceNew() {
        return memberpiceNew;
    }

    public void setMemberpiceNew(double memberpiceNew) {
        this.memberpiceNew = memberpiceNew;
    }

    public double getMemberzenupice() {
        return memberzenupice;
    }

    public void setMemberzenupice(double memberzenupice) {
        this.memberzenupice = memberzenupice;
    }

    @Override
    public String toString() {
        return "PriceEntity{" +
                "zenupice=" + zenupice +
                ", memberpice=" + memberpice +
                ", mansonpice=" + mansonpice +
                ", kaquanpice=" + kaquanpice +
                ", yufupice=" + yufupice +
                ", distancePice=" + distancePice +
                ", packagPice=" + packagPice +
                ", canju=" + canju +
                ", youhui=" + youhui +
                ", yuanjia=" + yuanjia +
                ", yintui=" + yintui +
                ", yinfu=" + yinfu +
                ", memberpiceNew=" + memberpiceNew +
                ", memberzenupice=" + memberzenupice +
                '}';
    }
}
