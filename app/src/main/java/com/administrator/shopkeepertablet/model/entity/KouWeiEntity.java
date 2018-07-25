package com.administrator.shopkeepertablet.model.entity;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Description:
 * Author CC
 * Time 2018/7/9
 */


@Entity
public class KouWeiEntity {
    @Id
    @SerializedName("GUID")
    private String guId;
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

    @Generated(hash = 512498507)
    public KouWeiEntity(String guId, String name, String no, String restaurantId,
            String patientId, String accord) {
        this.guId = guId;
        this.name = name;
        this.no = no;
        this.restaurantId = restaurantId;
        this.patientId = patientId;
        this.accord = accord;
    }

    @Generated(hash = 833556541)
    public KouWeiEntity() {
    }

    public String getGuId() {
        return guId;
    }

    public void setGuId(String guId) {
        this.guId = guId;
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
                "uId='" + guId + '\'' +
                ", name='" + name + '\'' +
                ", no='" + no + '\'' +
                ", restaurantId='" + restaurantId + '\'' +
                ", patientId='" + patientId + '\'' +
                ", accord='" + accord + '\'' +
                '}';
    }
}
