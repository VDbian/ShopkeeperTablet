package com.administrator.shopkeepertablet.model.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Description:
 * Author CC
 * Time 2018/7/1
 */


public class UserInfoEntity implements Serializable{

    /**
     * Names : 智慧餐厅
     * USERID : 544a82c2-ba2e-4306-837f-56494a20c6e9
     * USERNAME : 收银
     * STATE : 1
     * RESTAURANTID : 4b176f0e-0553-4094-8181-5048641b20ef
     * CREATETIME : 2017-02-12 18:07:44
     * PASSWORD : 111
     * ROLEID : 4
     * OperaType : 1,2
     * CashPayType : 1,2,5,6,8
     * PayType : 1
     * PermissionName : 确认结账,确认免单,减免金额,权限打折,取消结账,加菜,换桌,撤单,并单,后厨打印,前台打印,整单催菜,修改人数,账单结算,交班打印,清台
     * PermissionValue : queren,quanxianmiandan,quanxianjianmian,quanxiandazhe,quxiaojiezhang,jiacai,huanzhuo,chedan,bindan,houchuprint,qiantaiprint,zhendancuicai,jiucancount,jiesuan,jiaoban,qingtai
     * PrintSet : 2
     * UserNameID : 15982187353
     * MasterType : 0
     */

    @SerializedName("Names")
    private String names;

    @SerializedName("USERID")
    private String userId;

    @SerializedName("USERNAME")
    private String userName;

    @SerializedName("STATE")
    private String state;

    @SerializedName("RESTAURANTID")
    private String restaurantId;

    @SerializedName("CREATETIME")
    private String createTime;

    @SerializedName("PASSWORD")
    private String password;

    @SerializedName("ROLEID")
    private String roleId;

    @SerializedName("OperaType")
    private String operaType;

    @SerializedName("CashPayType")
    private String cashPayType;

    @SerializedName("PayType")
    private String payType;

    @SerializedName("PermissionName")
    private String permissionName;

    @SerializedName("PermissionValue")
    private String permissionValue;

    @SerializedName("PrintSet")
    private String printSet;

    @SerializedName("UserNameID")
    private String userNameID;

    @SerializedName("MasterType")
    private String masterType;

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getOperaType() {
        return operaType;
    }

    public void setOperaType(String operaType) {
        this.operaType = operaType;
    }

    public String getCashPayType() {
        return cashPayType;
    }

    public void setCashPayType(String cashPayType) {
        this.cashPayType = cashPayType;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionValue() {
        return permissionValue;
    }

    public void setPermissionValue(String permissionValue) {
        this.permissionValue = permissionValue;
    }

    public String getPrintSet() {
        return printSet;
    }

    public void setPrintSet(String printSet) {
        this.printSet = printSet;
    }

    public String getUserNameID() {
        return userNameID;
    }

    public void setUserNameID(String userNameID) {
        this.userNameID = userNameID;
    }

    public String getMasterType() {
        return masterType;
    }

    public void setMasterType(String masterType) {
        this.masterType = masterType;
    }

    @Override
    public String toString() {
        return "UserInfoEntity{" +
                "names='" + names + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", state=" + state +
                ", restaurantId='" + restaurantId + '\'' +
                ", createTime='" + createTime + '\'' +
                ", password='" + password + '\'' +
                ", roleId='" + roleId + '\'' +
                ", operaType='" + operaType + '\'' +
                ", cashPayType='" + cashPayType + '\'' +
                ", payType='" + payType + '\'' +
                ", permissionName='" + permissionName + '\'' +
                ", permissionValue='" + permissionValue + '\'' +
                ", printSet='" + printSet + '\'' +
                ", userNameID='" + userNameID + '\'' +
                ", masterType='" + masterType + '\'' +
                '}';
    }
}
