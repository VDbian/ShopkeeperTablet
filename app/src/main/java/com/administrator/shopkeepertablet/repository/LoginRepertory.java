package com.administrator.shopkeepertablet.repository;

import com.administrator.shopkeepertablet.model.entity.BaseEntity;
import com.administrator.shopkeepertablet.model.entity.LoginEntity;

import io.reactivex.Observable;

/**
 * Description:
 * Author CC
 * Time 2018/6/14
 */


public interface LoginRepertory extends BaseRepertory {
    Observable<BaseEntity<String>> login(String name, String id, String pwd);
}
