package com.administrator.shopkeepertablet.model.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Description:
 * Author CC
 * Time 2018/7/30
 */


public class LineUpEntity {

    /**
     * STATE : 0
     * GUIDS : 9bc6fecb-54eb-44e7-97ff-a627a1391ca1
     * RESTAURANTID : 4B176F0E-0553-4094-8181-5048641B20EF
     * username :
     * Phone :
     * DINNERDATE : 2018-07-29 00:00:00
     * DINNERTIME : 2018-07-29 18:46:21
     * tablename : 大桌
     * TABLEID : 4cae1f81-1a03-46a7-83d3-a1e222734c13
     * CALLNO : 1
     * Types : A
     */

    @SerializedName("STATE")
    private int state;
    @SerializedName("GUIDS")
    private String id;
    @SerializedName("RESTAURANTID")
    private String restaurantId;
    @SerializedName("username")
    private String username;
    @SerializedName("Phone")
    private String phone;
    @SerializedName("DINNERDATE")
    private String dinnerDate;
    @SerializedName("DINNERTIME")
    private String dinnerTime;
    @SerializedName("tablename")
    private String tableName;
    @SerializedName("TABLEID")
    private String tableId;
    @SerializedName("CALLNO")
    private int callNo;
    @SerializedName("Types")
    private String types;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDinnerDate() {
        return dinnerDate;
    }

    public void setDinnerDate(String dinnerDate) {
        this.dinnerDate = dinnerDate;
    }

    public String getDinnerTime() {
        return dinnerTime;
    }

    public void setDinnerTime(String dinnerTime) {
        this.dinnerTime = dinnerTime;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public int getCallNo() {
        return callNo;
    }

    public void setCallNo(int callNo) {
        this.callNo = callNo;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String no() {
        return types + callNo;
    }

    @Override
    public String toString() {
        return "LineUpEntity{" +
                "state=" + state +
                ", id='" + id + '\'' +
                ", restaurantId='" + restaurantId + '\'' +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", dinnerDate='" + dinnerDate + '\'' +
                ", dinnerTime='" + dinnerTime + '\'' +
                ", tableName='" + tableName + '\'' +
                ", tableId='" + tableId + '\'' +
                ", callNo=" + callNo +
                ", types='" + types + '\'' +
                '}';
    }
}
