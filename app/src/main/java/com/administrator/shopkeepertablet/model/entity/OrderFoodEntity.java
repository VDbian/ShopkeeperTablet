package com.administrator.shopkeepertablet.model.entity;

import android.text.TextUtils;
import android.view.View;

import com.google.gson.annotations.SerializedName;

/**
 * Description:
 * Author CC
 * Time 2018/7/14
 */


public class OrderFoodEntity {

    /**
     * RESTAURANTID : 4B176F0E-0553-4094-8181-5048641B20EF
     * OrderID : 920b317a-b4b9-4583-8b01-e497a8f44769
     * ID : 258c6ec3-58dd-4fbb-8dd3-e73305e581ec
     * ProductNmae : 测试2
     * Ammount : 1.0
     * Weight : 1.0
     * COUNT : 1.0
     * MemberPrice : 88.0
     * Price : 111.0
     * DETAILID : 258c6ec3-58dd-4fbb-8dd3-e73305e581ec
     * CanDiscount : null
     * type : 0
     * SeasonID :
     * SeasonName :
     * SeasonPrice : 0.0
     * Giving : 0
     * PRODUCTSHUXIN : 鹿茸^木须肉^过油肉^龙肉^牛肉汤^抄手^牛肉面^排骨米线^干锅鸡翅^清炒花菜^生椒牛肉粉^仔肺粉
     * PRODUCTSHUXINID : 1^1^1^1^1^1^1^1^1^1^1^1
     * CHARGEMONEY : 111.0
     * REMARK :
     * REMARKID :
     */

    @SerializedName("RESTAURANTID")
    private String restaurantId;
    @SerializedName("OrderID")
    private String orderId;
    @SerializedName("ID")
    private String id;
    @SerializedName("ProductNmae")
    private String productName;
    @SerializedName("Ammount")
    private double ammount;
    @SerializedName("Weight")
    private double weight;
    @SerializedName("COUNT")
    private double count;
    @SerializedName("MemberPrice")
    private double memberPrice;
    @SerializedName("Price")
    private double price;
    @SerializedName("DETAILID")
    private String detailId;
    @SerializedName("CanDiscount")
    private Object canDiscount;
    @SerializedName("type")
    private String type;
    @SerializedName("SeasonID")
    private String seasonID;
    @SerializedName("SeasonName")
    private String seasonName;
    @SerializedName("SeasonPrice")
    private double seasonPrice;
    @SerializedName("Giving")
    private int giving;
    @SerializedName("PRODUCTSHUXIN")
    private String productShuXin;
    @SerializedName("PRODUCTSHUXINID")
    private String productShuXinId;
    @SerializedName("CHARGEMONEY")
    private double chargeMoney;
    @SerializedName("REMARK")
    private String remark;
    @SerializedName("REMARKID")
    private String remarkId;
    @SerializedName("UNIT")
    private String unit;
    @SerializedName("SeasonSum")
    private String seasonNum;

    private int discount;
    private boolean isSelect;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getAmmount() {
        return ammount;
    }

    public void setAmmount(double ammount) {
        this.ammount = ammount;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public double getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(double memberPrice) {
        this.memberPrice = memberPrice;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    public Object getCanDiscount() {
        return canDiscount;
    }

    public void setCanDiscount(Object canDiscount) {
        this.canDiscount = canDiscount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSeasonID() {
        return seasonID;
    }

    public void setSeasonID(String seasonID) {
        this.seasonID = seasonID;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
    }

    public double getSeasonPrice() {
        return seasonPrice;
    }

    public void setSeasonPrice(double seasonPrice) {
        this.seasonPrice = seasonPrice;
    }

    public int getGiving() {
        return this.giving;
    }

    public void setGiving(int giving) {
        this.giving = giving;
    }

    public String getProductShuXin() {
        return productShuXin;
    }

    public void setProductShuXin(String productShuXin) {
        this.productShuXin = productShuXin;
    }

    public String getProductShuXinId() {
        return productShuXinId;
    }

    public void setProductShuXinId(String productShuXinId) {
        this.productShuXinId = productShuXinId;
    }

    public double getChargeMoney() {
        return chargeMoney;
    }

    public void setChargeMoney(double chargeMoney) {
        this.chargeMoney = chargeMoney;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemarkId() {
        return remarkId;
    }

    public void setRemarkId(String remarkId) {
        this.remarkId = remarkId;
    }

    public int giveShow() {
        return this.giving == 0 ? View.GONE : View.VISIBLE;
    }

    public int seasonShow() {
        return TextUtils.isEmpty(seasonName) ? View.GONE : View.VISIBLE;
    }

    public String setNum() {
        return count > weight ? count + "" : weight + "";
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSeasonNum() {
        return seasonNum;
    }

    public void setSeasonNum(String seasonNum) {
        this.seasonNum = seasonNum;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "OrderFoodEntity{" +
                "restaurantId='" + restaurantId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", id='" + id + '\'' +
                ", productName='" + productName + '\'' +
                ", ammount=" + ammount +
                ", weight=" + weight +
                ", count=" + count +
                ", memberPrice=" + memberPrice +
                ", price=" + price +
                ", detailId='" + detailId + '\'' +
                ", canDiscount=" + canDiscount +
                ", type='" + type + '\'' +
                ", seasonID='" + seasonID + '\'' +
                ", seasonName='" + seasonName + '\'' +
                ", seasonPrice=" + seasonPrice +
                ", giving=" + giving +
                ", productShuXin='" + productShuXin + '\'' +
                ", productShuXinId='" + productShuXinId + '\'' +
                ", chargeMoney=" + chargeMoney +
                ", remark='" + remark + '\'' +
                ", remarkId='" + remarkId + '\'' +
                ", unit='" + unit + '\'' +
                ", seasonNum='" + seasonNum + '\'' +
                '}';
    }
}
