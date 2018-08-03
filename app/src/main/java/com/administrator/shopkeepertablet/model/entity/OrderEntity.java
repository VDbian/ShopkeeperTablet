package com.administrator.shopkeepertablet.model.entity;

import android.text.TextUtils;

import com.administrator.shopkeepertablet.AppConstant;
import com.administrator.shopkeepertablet.R;
import com.google.gson.annotations.SerializedName;

public class OrderEntity {

    /**
     * RESTAURANTID : 4B176F0E-0553-4094-8181-5048641B20EF
     * BILLIDMERGER : null
     * ID : e96d2865-a87b-4d76-b4a2-ccc05e44f904
     * OrderNumber : 18072521282083096
     * Type : 1
     * OrderSate : 3
     * OrderSource : 2
     * BILLID : 82fa6235-1ca8-491c-83c8-2f9e0581066f
     * PayType : null
     * PayPrice : 5.0
     * ActuelPayPrice : 1.0
     * IsPay : 1
     * RecordDate : 2018-07-25 21:28:20
     * Remark : 1
     * USERNAME : 收银
     * TABLEID :
     * TABLENAME :
     * STATE : 3
     * PERSONCOUNT : 1
     * TableWareCount : 1
     * Name : 1
     */

    @SerializedName("RESTAURANTID")
    private String restaurantId;
    @SerializedName("BILLIDMERGER")
    private String billIdMerger;
    @SerializedName("ID")
    private String id;
    @SerializedName("OrderNumber")
    private String orderNumber;
    @SerializedName("Type")
    private int type;
    @SerializedName("OrderSate")
    private int orderSate;
    @SerializedName("OrderSource")
    private int orderSource;
    @SerializedName("BILLID")
    private String billId;
    @SerializedName("PayType")
    private String payType;
    @SerializedName("PayPrice")
    private double payPrice;
    @SerializedName("ActuelPayPrice")
    private double actuelPayPrice;
    @SerializedName("IsPay")
    private String isPay;
    @SerializedName("RecordDate")
    private String recordDate;
    @SerializedName("Remark")
    private String remark;
    @SerializedName("USERNAME")
    private String userName;
    @SerializedName("TABLEID")
    private String tableId;
    @SerializedName("TABLENAME")
    private String tableName;
    @SerializedName("STATE")
    private int state;
    @SerializedName("PERSONCOUNT")
    private int personCount;
    @SerializedName("TableWareCount")
    private int tableWareCount;
    @SerializedName("Name")
    private String name;
    @SerializedName("Invoice")
    private String invoice;
    @SerializedName("FREEMONEY")
    private double freeMoney;

    private boolean select;

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public String showOrderType() {
        String orderType = "";
//        Type  1.预定菜品 2 预定桌位  3.外卖  4.快餐 5.扫码点餐 6.排队点餐 7.店内点餐
        switch (type) {
            case 1:
                orderType = "预定菜品";
                break;
            case 2:
                orderType = "预定桌位";
                break;
            case 3:
                if(!TextUtils.isEmpty(AppConstant.getUser().getMasterType())&&AppConstant.getUser().getMasterType().equals("1")) {
                    orderType = "商家采购";
                }else {
                    orderType = "外卖";
                }
                break;
            case 4:
                orderType = "快餐";
                break;
            case 5:
                orderType = "扫码点餐";
                break;
            case 6:
                orderType = "排队点餐";
                break;
            case 7:
                orderType = "店内点餐";
                break;

        }
        return orderType;
    }

    public String showOrderState() {
        String orderState = "";
//        1.待处理（未支付） 2.已确定（已支付） 3.已完成（已经开单）4.已取消 5.制作中（暂时未使用） 6.等待配送 （暂时未使用）
        switch (this.orderSate) {
            case 1:
                orderState = "待处理";
                break;
            case 2:
                orderState = "已撤销";
                break;
            case 3:
                orderState = "已结账";
                break;
        }
        return orderState;
    }

    public String showPayState() {
        String payStatus = "";
        if (isPay!=null&&isPay.equals("1")) {
            if (type == 1) {
                payStatus = "已预付" + "(￥" + actuelPayPrice + ")";
            } else {
                payStatus = "已结账";
            }

        } else if (isPay!=null&&isPay.equals("2")) {
            payStatus = "未支付";
        }
        return payStatus;
    }

    public String showInvoice() {
        return invoice!=null&&invoice.equals("1") ? "已开票" : "未开票";
    }

    public double orderPrice() {
        return payPrice +freeMoney;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getBillIdMerger() {
        return billIdMerger;
    }

    public void setBillIdMerger(String billIdMerger) {
        this.billIdMerger = billIdMerger;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getOrderSate() {
        return orderSate;
    }

    public void setOrderSate(int orderSate) {
        this.orderSate = orderSate;
    }

    public int getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(int orderSource) {
        this.orderSource = orderSource;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public double getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(double payPrice) {
        this.payPrice = payPrice;
    }

    public double getActuelPayPrice() {
        return actuelPayPrice;
    }

    public void setActuelPayPrice(double actuelPayPrice) {
        this.actuelPayPrice = actuelPayPrice;
    }

    public String getIsPay() {
        return isPay;
    }

    public void setIsPay(String isPay) {
        this.isPay = isPay;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getPersonCount() {
        return personCount;
    }

    public void setPersonCount(int personCount) {
        this.personCount = personCount;
    }

    public int getTableWareCount() {
        return tableWareCount;
    }

    public void setTableWareCount(int tableWareCount) {
        this.tableWareCount = tableWareCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getFreeMoney() {
        return freeMoney;
    }

    public void setFreeMoney(double freeMoney) {
        this.freeMoney = freeMoney;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "restaurantId='" + restaurantId + '\'' +
                ", billIdMerger='" + billIdMerger + '\'' +
                ", id='" + id + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", type=" + type +
                ", orderSate=" + orderSate +
                ", orderSource=" + orderSource +
                ", billId='" + billId + '\'' +
                ", payType='" + payType + '\'' +
                ", payPrice=" + payPrice +
                ", actuelPayPrice=" + actuelPayPrice +
                ", isPay='" + isPay + '\'' +
                ", recordDate='" + recordDate + '\'' +
                ", remark='" + remark + '\'' +
                ", userName='" + userName + '\'' +
                ", tableId='" + tableId + '\'' +
                ", tableName='" + tableName + '\'' +
                ", state=" + state +
                ", personCount=" + personCount +
                ", tableWareCount=" + tableWareCount +
                ", name='" + name + '\'' +
                ", invoice='" + invoice + '\'' +
                ", freeMoney=" + freeMoney +
                '}';
    }
}
