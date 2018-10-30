package com.administrator.shopkeepertablet.model.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Description:
 * Author CC
 * Time 2018/9/18
 */


public class SocketEntity {

    /**
     * VersionID : 2dc1728b-98a8-4023-9f82-2c2cbc612bb2
     * VersionCode : 38
     * VersionType : A
     * Type : 1
     * Address : 182.140.132.196
     * Port : 8095
     */

    @SerializedName("VersionID")
    private String versionID;

    @SerializedName("VersionCode")
    private String versionCode;

    @SerializedName("VersionType")
    private String versionType;

    @SerializedName("Type")
    private String type;

    @SerializedName("Address")
    private String address;

    @SerializedName("Port")
    private int port;

    public String getVersionID() {
        return versionID;
    }

    public void setVersionID(String versionID) {
        this.versionID = versionID;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionType() {
        return versionType;
    }

    public void setVersionType(String versionType) {
        this.versionType = versionType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "SocketEntity{" +
                "versionID='" + versionID + '\'' +
                ", versionCode='" + versionCode + '\'' +
                ", versionType='" + versionType + '\'' +
                ", type='" + type + '\'' +
                ", address='" + address + '\'' +
                ", port=" + port +
                '}';
    }
}
