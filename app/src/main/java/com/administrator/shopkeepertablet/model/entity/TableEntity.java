package com.administrator.shopkeepertablet.model.entity;

import android.view.View;

import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.utils.DateUtils;
import com.google.gson.annotations.SerializedName;

/**
 * Description:
 * Author CC
 * Time 2018/7/7
 */


public class TableEntity {

    /**
     * ROOMTABLEID : 5b208d68-9a13-4ca6-9013-e516eeb8dcf2
     * TABLENAME : 中南海
     * PerconManager : d83ee004-2038-4722-8e2e-8bf164b59efb
     * KaiTime : 2018-06-19 11:24:45
     * Price : 0
     * RESTAURANTID : 4B176F0E-0553-4094-8181-5048641B20EF
     * ROOMID : 60e0db20-a723-4416-b74f-66aeae76ce97
     * SORTNO : 3
     * BILLId : null
     * IsOpen : 0
     * PersonNo : null
     * TableWareCount : null
     * PERSONCOUNTS : null
     */

    @SerializedName("ROOMTABLEID")
    private String RoomTableId;
    @SerializedName("TABLENAME")
    private String TableName;
    @SerializedName("PerconManager")
    private String personManager;
    @SerializedName("KaiTime")
    private String kaiTime;
    @SerializedName("Price")
    private double price;
    @SerializedName("RESTAURANTID")
    private String restaurantId;
    @SerializedName("ROOMID")
    private String roomId;
    @SerializedName("SORTNO")
    private int sortNo;
    @SerializedName("BILLId")
    private String billId;
    @SerializedName("IsOpen")
    private String isOpen;
    @SerializedName("PersonNo")
    private String personNo;
    @SerializedName("TableWareCount")
    private String tableWareCount;
    @SerializedName("PERSONCOUNTS")
    private String personCounts;


    public String getRoomTableId() {
        return RoomTableId;
    }

    public void setRoomTableId(String roomTableId) {
        RoomTableId = roomTableId;
    }

    public String getTableName() {
        return TableName;
    }

    public void setTableName(String tableName) {
        TableName = tableName;
    }

    public String getPersonManager() {
        return personManager;
    }

    public void setPersonManager(String personManager) {
        this.personManager = personManager;
    }

    public String getKaiTime() {
        return kaiTime;
    }

    public void setKaiTime(String kaiTime) {
        this.kaiTime = kaiTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public int getSortNo() {
        return sortNo;
    }

    public void setSortNo(int sortNo) {
        this.sortNo = sortNo;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(String isOpen) {
        this.isOpen = isOpen;
    }

    public String getPersonNo() {
        return personNo;
    }

    public void setPersonNo(String personNo) {
        this.personNo = personNo;
    }

    public String getTableWareCount() {
        return tableWareCount;
    }

    public void setTableWareCount(String tableWareCount) {
        this.tableWareCount = tableWareCount;
    }

    public String getPersonCounts() {
        return personCounts;
    }

    public void setPersonCounts(String personCounts) {
        this.personCounts = personCounts;
    }

    @Override
    public String toString() {
        return "TableEntity{" +
                "RoomTableId='" + RoomTableId + '\'' +
                ", TableName='" + TableName + '\'' +
                ", personManager='" + personManager + '\'' +
                ", kaiTime='" + kaiTime + '\'' +
                ", price=" + price +
                ", restaurantId='" + restaurantId + '\'' +
                ", roomId='" + roomId + '\'' +
                ", sortNo=" + sortNo +
                ", billId=" + billId +
                ", isOpen='" + isOpen + '\'' +
                ", personNo=" + personNo +
                ", tableWareCount=" + tableWareCount +
                ", personCounts=" + personCounts +
                '}';
    }

    public String getTime() {
        return DateUtils.friendly_time(DateUtils.stringToDate(kaiTime));
    }

    public int drawableId() {
        if (isOpen.equals("0")) {
            return R.drawable.shape_table_leisure_bg;
        } else if (isOpen.equals("1")) {
            return R.drawable.shape_table_open_bg;
        }
        return R.drawable.shape_table_order_bg;
    }

    public int textVisibility() {
        return isOpen.equals("0") ? View.GONE : View.VISIBLE;
    }

    public int llVisibility() {
        return !isOpen.equals("0") ? View.VISIBLE : View.GONE;
    }
}
