package com.administrator.shopkeepertablet.model.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Description:
 * Author CC
 * Time 2018/8/1
 */


public class RechargeTypeEntity {

    @SerializedName("GUID")
    private String id;
    @SerializedName("Type")
    private String type;
    @SerializedName("Counts")
    private String counts;
    @SerializedName("Fill")
    private String fill;
    @SerializedName("Name")
    private String name;
    @SerializedName("Times")
    private String times;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCounts() {
        return counts;
    }

    public void setCounts(String counts) {
        this.counts = counts;
    }

    public String getFill() {
        return fill;
    }

    public void setFill(String fill) {
        this.fill = fill;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    @Override
    public String toString() {
        return "RechargeTypeEntity{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", counts='" + counts + '\'' +
                ", fill='" + fill + '\'' +
                ", name='" + name + '\'' +
                ", times='" + times + '\'' +
                '}';
    }
}
