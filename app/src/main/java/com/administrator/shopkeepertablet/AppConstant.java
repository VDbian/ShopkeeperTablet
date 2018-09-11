package com.administrator.shopkeepertablet;

import com.administrator.shopkeepertablet.model.entity.UserInfoEntity;

/**
 * Description:
 * Author CC
 * Time 2018/6/11
 */
public class AppConstant {
    public static final String PORT_URL = "Port/";
    public static final String MASTE_URL = "Maste/";


    public static final int REQUEST_SUCCESS = 1;
    public static final int REQUEST_FAILED = 0;//失败
    public static final int REQUEST_ERROR = -1;//数据库查询错误

    public static final int EVENT_ORDER_DISHES = 0;
    public static final int EVENT_TABLE = 1;
    public static final int EVENT_PAY = 2;
    public static final int EVENT_RETURN_BILL =3;
    public static final int EVENT_FAST_PAY = 4;
    public static final int EVENT_SUCCESS = 5;

    private static UserInfoEntity user;

    public static UserInfoEntity getUser() {
        return user;
    }

    public static void setUser(UserInfoEntity userInfoEntity) {
        user = userInfoEntity;
    }
}
