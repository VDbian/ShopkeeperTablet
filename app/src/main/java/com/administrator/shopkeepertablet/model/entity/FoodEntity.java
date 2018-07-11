package com.administrator.shopkeepertablet.model.entity;

import com.google.gson.annotations.SerializedName;

import org.litepal.crud.LitePalSupport;

/**
 * Description:
 * Author CC
 * Time 2018/7/1
 */


public class FoodEntity extends LitePalSupport {

    /**
     * PRODUCTID : 1fffea9a-2cfe-4dfd-840d-033a36ba8f2b
     * RESTAURANTID : 4b176f0e-0553-4094-8181-5048641b20ef
     * ID : 1
     * PRODUCTNAME : 香芋味奶茶
     * PINYIN : xywnc香芋味奶茶
     * UNIT : 杯
     * MINUNIT : 1
     * PRODUCTTYPEID : c4ddb67d-7e70-436b-b3b3-5bea97b8bbb8
     * PRODUCTTYPENAME : 奶茶
     * PRICE : 1.0
     * PRODUCTFile : 香芋味奶茶.jpg
     * PRODUCTIMAGE : null
     * STATE : 1
     * REMARK :
     * TasteID : null
     * IsDaZhe : null
     * DaZhe : null
     * WarCount : 5
     * IsClose : 0
     * IsCloseName : 否
     * ProductCount : 1
     * ChuCaiType : 0
     * CanDiscount : 0
     * MemberPice : 0.01
     * SalesType : 1
     * AccordIng : 0
     * ProtuctShuXing : 0
     * ProductGive : null
     * TasteType : 0
     * PrintWay : 1
     */

    @SerializedName("PRODUCTID")
    private String productId;

    @SerializedName("RESTAURANTID")
    private String restaurantId;

//    @SerializedName("ID")
//    private String uId;

    @SerializedName("PRODUCTNAME")
    private String productName;

    @SerializedName("PINYIN")
    private String pinYin;

    @SerializedName("UNIT")
    private String unit;

    @SerializedName("MINUNIT")
    private String minUnit;

    @SerializedName("PRODUCTTYPEID")
    private String productTypeId;

    @SerializedName("PRODUCTTYPENAME")
    private String productTypeName;

    @SerializedName("PRICE")
    private double price;

    @SerializedName("PRODUCTFile")
    private String productFile;

    @SerializedName("PRODUCTIMAGE")
    private String productImage;

    @SerializedName("STATE")
    private int state;

    @SerializedName("REMARK")
    private String remark;

    @SerializedName("TasteID")
    private String tasteID;

    @SerializedName("isDaZhe")
    private String IsDaZhe;

    @SerializedName("DaZhe")
    private String daZhe;

    @SerializedName("WarCount")
    private String warCount;

    @SerializedName("isClose")
    private int IsClose;

    @SerializedName("IsCloseName")
    private String isCloseName;

    @SerializedName("ProductCount")
    private int productCount;

    @SerializedName("ChuCaiType")
    private String chuCaiType;

    @SerializedName("CanDiscount")
    private int canDiscount;

    @SerializedName("MemberPice")
    private double memberPice;

    @SerializedName("SalesType")
    private int salesType;

    @SerializedName("AccordIng")
    private String accordIng;

    @SerializedName("ProtuctShuXing")
    private String productProperty;

    @SerializedName("ProductGive")
    private String productGive;

    @SerializedName("TasteType")
    private String tasteType;

    @SerializedName("PrintWay")
    private String printWay;


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? "" : productId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId == null ? "" :restaurantId;
    }

//    public String getuId() {
//        return uId;
//    }
//
//    public void setuId(String uId) {
//        this.uId  = uId == null ? "" : uId;
//    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName  = productName == null ? "" : productName;
    }

    public String getPinYin() {
        return pinYin;
    }

    public void setPinYin(String pinYin) {
        this.pinYin  = pinYin == null ? "" : pinYin;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit  = unit == null ? "" : unit;
    }

    public String getMinUnit() {
        return minUnit;
    }

    public void setMinUnit(String minUnit) {
        this.minUnit  = minUnit == null ? "" : minUnit;
    }

    public String getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(String productTypeId) {
        this.productTypeId  = productTypeId == null ? "" : productTypeId;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName  = productTypeName == null ? "" : productTypeName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductFile() {
        return productFile;
    }

    public void setProductFile(String productFile) {
        this.productFile  = productFile == null ? "" : productFile;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage  = productImage == null ? "" : productImage;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark  = remark == null ? "" : remark;
    }

    public String getTasteID() {
        return tasteID;
    }

    public void setTasteID(String tasteID) {
        this.tasteID  = tasteID == null ? "" : tasteID;
    }

    public String getIsDaZhe() {
        return IsDaZhe;
    }

    public void setIsDaZhe(String isDaZhe) {
        IsDaZhe  = isDaZhe == null ? "" : isDaZhe;
    }

    public String getDaZhe() {
        return daZhe;
    }

    public void setDaZhe(String daZhe) {
        this.daZhe  = daZhe == null ? "" : daZhe;
    }

    public String getWarCount() {
        return warCount;
    }

    public void setWarCount(String warCount) {
        this.warCount  = warCount == null ? "" : warCount;
    }

    public int getIsClose() {
        return IsClose;
    }

    public void setIsClose(int isClose) {
        this.IsClose  =isClose;
    }

    public String getIsCloseName() {
        return isCloseName;
    }

    public void setIsCloseName(String isCloseName) {
        this.isCloseName  = isCloseName == null ? "" : isCloseName;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount  =  productCount;
    }

    public String getChuCaiType() {
        return chuCaiType;
    }

    public void setChuCaiType(String chuCaiType) {
        this.chuCaiType  = chuCaiType == null ? "" : chuCaiType;
    }

    public int getCanDiscount() {
        return canDiscount;
    }

    public void setCanDiscount(int canDiscount) {
        this.canDiscount = canDiscount;
    }

    public double getMemberPice() {
        return memberPice;
    }

    public void setMemberPice(double memberPice) {
        this.memberPice = memberPice;
    }

    public int getSalesType() {
        return salesType;
    }

    public void setSalesType(int salesType) {
        this.salesType  = salesType;
    }

    public String getAccordIng() {
        return accordIng;
    }

    public void setAccordIng(String accordIng) {
        this.accordIng  = accordIng == null ? "" : accordIng;
    }

    public String getProductProperty() {
        return productProperty;
    }

    public void setProductProperty(String productProperty) {
        this.productProperty  = productProperty == null ? "" : productProperty;
    }

    public String getProductGive() {
        return productGive;
    }

    public void setProductGive(String productGive) {
        this.productGive  = productGive == null ? "" : productGive;
    }

    public String getTasteType() {
        return tasteType;
    }

    public void setTasteType(String tasteType) {
        this.tasteType  = tasteType == null ? "" : tasteType;
    }

    public String getPrintWay() {
        return printWay;
    }

    public void setPrintWay(String printWay) {
        this.printWay  = printWay == null ? "" : printWay;
    }

    @Override
    public String toString() {
        return "FoodEntity{" +
                ", productId='" + productId + '\'' +
                ", restaurantId='" + restaurantId + '\'' +
//                ", uId='" + uId + '\'' +
                ", productName='" + productName + '\'' +
                ", pinYin='" + pinYin + '\'' +
                ", unit='" + unit + '\'' +
                ", minUnit='" + minUnit + '\'' +
                ", productTypeId='" + productTypeId + '\'' +
                ", productTypeName='" + productTypeName + '\'' +
                ", price=" + price +
                ", productFile='" + productFile + '\'' +
                ", productImage='" + productImage + '\'' +
                ", state=" + state +
                ", remark='" + remark + '\'' +
                ", tasteID='" + tasteID + '\'' +
                ", IsDaZhe='" + IsDaZhe + '\'' +
                ", daZhe='" + daZhe + '\'' +
                ", warCount='" + warCount + '\'' +
                ", IsClose=" + IsClose +
                ", isCloseName='" + isCloseName + '\'' +
                ", productCount=" + productCount +
                ", chuCaiType='" + chuCaiType + '\'' +
                ", canDiscount=" + canDiscount +
                ", memberPice=" + memberPice +
                ", salesType=" + salesType +
                ", accordIng='" + accordIng + '\'' +
                ", productProperty='" + productProperty + '\'' +
                ", productGive='" + productGive + '\'' +
                ", tasteType='" + tasteType + '\'' +
                ", printWay='" + printWay + '\'' +
                '}';
    }
}
