package com.administrator.shopkeepertablet.model.api;

import com.administrator.shopkeepertablet.model.entity.BaseEntity;
import com.administrator.shopkeepertablet.model.entity.ResultFoodEntity;
import com.administrator.shopkeepertablet.model.entity.UserInfoEntity;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;

/**
 * Description:
 * Author CC
 * Time 2018/6/11
 */
public interface ApiSource {

    Observable<BaseEntity<String>> login(String name, String id, String pwd);

    Observable<BaseEntity<String>> getRooms(String type,String id);

    Observable<BaseEntity<String>> getTables(String type,String leibie, String id, int Pindex, int Psize);

    Observable<ResultFoodEntity> getFoodList(String type,String id);

}
