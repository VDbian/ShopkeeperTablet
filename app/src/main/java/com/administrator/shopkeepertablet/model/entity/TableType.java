package com.administrator.shopkeepertablet.model.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Description:
 * Author CC
 * Time 2018/7/30
 */


public class TableType {


    /**
     * Guid : f14452ea-872c-4232-b0b4-039e46e1223d
     * RESTAURANTID : 4b176f0e-0553-4094-8181-5048641b20ef
     * Name : 小桌
     * PersonCount : da48a4fe-4563-411c-aa21-fcb7041d22a7
     * PersonCountInfo : 2-3人
     * Types : B
     */

    @SerializedName("Guid")
    private String id;
    @SerializedName("RESTAURANTID")
    private String restaurantId;
    @SerializedName("Name")
    private String name;
    @SerializedName("PersonCount")
    private String personCount;
    @SerializedName("PersonCountInfo")
    private String personCountInfo;
    @SerializedName("Types")
    private String types;

    private boolean select;


    public TableType(String id, String restaurantId, String name, String personCount, String personCountInfo, String types, boolean select) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.name = name;
        this.personCount = personCount;
        this.personCountInfo = personCountInfo;
        this.types = types;
        this.select = select;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersonCount() {
        return personCount;
    }

    public void setPersonCount(String personCount) {
        this.personCount = personCount;
    }

    public String getPersonCountInfo() {
        return personCountInfo;
    }

    public void setPersonCountInfo(String personCountInfo) {
        this.personCountInfo = personCountInfo;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    @Override
    public String toString() {
        return "TableType{" +
                "id='" + id + '\'' +
                ", restaurantId='" + restaurantId + '\'' +
                ", name='" + name + '\'' +
                ", personCount='" + personCount + '\'' +
                ", personCountInfo='" + personCountInfo + '\'' +
                ", types='" + types + '\'' +
                '}';
    }
}
