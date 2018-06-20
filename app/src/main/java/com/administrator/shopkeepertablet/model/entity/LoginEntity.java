package com.administrator.shopkeepertablet.model.entity;

import retrofit2.http.Field;

/**
 * Description:
 * Author CC
 * Time 2018/6/14
 */


public class LoginEntity {
    String LoginName;
    String ID;
    String Passd;

    public LoginEntity(String loginName, String ID, String passd) {
        LoginName = loginName;
        this.ID = ID;
        Passd = passd;
    }
}
