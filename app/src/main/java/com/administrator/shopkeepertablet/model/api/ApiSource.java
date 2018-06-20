package com.administrator.shopkeepertablet.model.api;

import com.administrator.shopkeepertablet.model.entity.BaseEntity;
import com.administrator.shopkeepertablet.model.entity.LoginEntity;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

/**
 * Description:
 * Author CC
 * Time 2018/6/11
 */
public interface ApiSource {

    Observable<BaseEntity<String>> login(String name,String id,String pwd);


}
