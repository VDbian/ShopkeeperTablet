package com.administrator.shopkeepertablet.model.entity;

/**
 * Description:
 * Author CC
 * Time 2018/7/7
 */


public class EventOrderDishesEntity {
    private String tableName;
    private String roomName;
    private String time;
    private String peopleNum;

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
}
