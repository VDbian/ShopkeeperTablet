package com.administrator.shopkeepertablet.repository.login;

import com.administrator.shopkeepertablet.model.entity.BaseEntity;
import com.administrator.shopkeepertablet.model.entity.UserInfoEntity;
import com.administrator.shopkeepertablet.repository.BaseRepertory;

import io.reactivex.Observable;

/**
 * Description:
 * Author CC
 * Time 2018/6/14
 */


public interface LoginRepertory extends BaseRepertory {

    /**
     * 登录
     * @param name 用户名
     * @param id 店铺编码
     * @param pwd 密码
     * @return
     */
    Observable<BaseEntity<String>> login(String name, String id, String pwd);
}
