package com.administrator.shopkeepertablet.model.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Description:
 * Author CC
 * Time 2018/7/9
 */


public class KouWeiEntity {

    @SerializedName("GUID")
    private String id;
    @SerializedName("Name")
    private String name;
    @SerializedName("NO")
    private String no;
    @SerializedName("RESTAURANTID")
    private String restaurantId;
    @SerializedName("PatientId")
    private String patientId;
    @SerializedName("Accord")
    private String accord;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getAccord() {
        return accord;
    }

    public void setAccord(String accord) {
        this.accord = accord;
    }

    @Override
    public String toString() {
        return "KouWeiEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", no='" + no + '\'' +
                ", restaurantId='" + restaurantId + '\'' +
                ", patientId='" + patientId + '\'' +
                ", accord='" + accord + '\'' +
                '}';
    }
}
