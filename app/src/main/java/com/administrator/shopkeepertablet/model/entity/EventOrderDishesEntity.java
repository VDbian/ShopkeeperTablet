package com.administrator.shopkeepertablet.model.entity;

import java.util.List;

/**
 * Description:
 * Author CC
 * Time 2018/7/7
 */


public class EventOrderDishesEntity {
    private String tableName;
    private String tableId;
    private String roomName;
    private String time;
    private String peopleNum;
    private String billId;
    private List<OrderFoodEntity> orderFoodEntityList;

    public List<OrderFoodEntity> getOrderFoodEntityList() {
        return orderFoodEntityList;
    }

    public void setOrderFoodEntityList(List<OrderFoodEntity> orderFoodEntityList) {
        this.orderFoodEntityList = orderFoodEntityList;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(String peopleNum) {
        this.peopleNum = peopleNum;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }
}
