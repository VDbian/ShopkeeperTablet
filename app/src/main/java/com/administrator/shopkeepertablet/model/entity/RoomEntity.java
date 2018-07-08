package com.administrator.shopkeepertablet.model.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Description:
 * Author chencheng
 * Time 2018/7/5
 */

public class RoomEntity {

    /**
     * ID  : 3066 a3e0 - 9 a85 - 4 a96 - bc11 - 50 a9081b5651
     * RESTAURANTID  : 4 B176F0E - 0553 - 4094 - 8181 - 5048641 B20EF
     * NAME  : 大厅
     * SORTNO  : 1
     * STATE  : 1
     * RoomTypeID  : 63257 b4e - 7 fa1 - 4 d1a - a9b7 - f663f5d000c4
     * AreaId  :
     * Price  : 10.0
     * Counts  : 20.0
     */

    @SerializedName("ID")
    private String id;
    @SerializedName("RESTAURANTID")
    private String restaurantId;
    @SerializedName("NAME")
    private String name;
    @SerializedName("SORTNO")
    private String sortno;
    @SerializedName("STATE")
    private int state;
    @SerializedName("RoomTypeID")
    private String roomTypeID;
    @SerializedName("AreaId")
    private String areaId;
    @SerializedName("Price")
    private double price;
    @SerializedName("Counts")
    private double counts;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSortno() {
        return sortno;
    }

    public void setSortno(String sortno) {
        this.sortno = sortno;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getRoomTypeID() {
        return roomTypeID;
    }

    public void setRoomTypeID(String roomTypeID) {
        this.roomTypeID = roomTypeID;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCounts() {
        return counts;
    }

    public void setCounts(double counts) {
        this.counts = counts;
    }

    @Override
    public String toString() {
        return "RoomEntity{" +
                "id='" + id + '\'' +
                ", restaurantId='" + restaurantId + '\'' +
                ", name='" + name + '\'' +
                ", sortno='" + sortno + '\'' +
                ", state=" + state +
                ", roomTypeID='" + roomTypeID + '\'' +
                ", areaId='" + areaId + '\'' +
                ", price=" + price +
                ", counts=" + counts +
                '}';
    }
}
