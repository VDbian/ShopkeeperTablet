package com.administrator.shopkeepertablet.model.api;

import com.administrator.shopkeepertablet.model.entity.BaseEntity;
import com.administrator.shopkeepertablet.model.entity.ResultFoodEntity;
import com.administrator.shopkeepertablet.model.entity.UserInfoEntity;


import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * Description:
 * Author CC
 * Time 2018/6/11
 */
public class ApiSourceImpl implements ApiSource {

    private RetrofitInterface retrofitInterface;

    public ApiSourceImpl(Retrofit retrofit) {
        retrofitInterface = retrofit.create(RetrofitInterface.class);
    }


    @Override
    public Observable<BaseEntity<String>> login(String name, String id, String pwd) {
        return retrofitInterface.login(name,id,pwd);
    }

    @Override
    public Observable<BaseEntity<String>> getRooms(String type, String id) {
        return retrofitInterface.getRooms(type,id);
    }

    @Override
    public Observable<BaseEntity<String>> getTables(String type, String leibie, String id, int Pindex, int Psize) {
        return retrofitInterface.getTables(type,leibie,id,Pindex,Psize);
    }

    @Override
    public Observable<ResultFoodEntity> getFoodList(String type,String id) {
        return retrofitInterface.getFoodList(type,id);
    }
}
