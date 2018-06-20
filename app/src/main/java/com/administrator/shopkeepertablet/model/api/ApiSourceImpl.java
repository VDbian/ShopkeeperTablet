package com.administrator.shopkeepertablet.model.api;

import com.administrator.shopkeepertablet.model.entity.BaseEntity;
import com.administrator.shopkeepertablet.model.entity.LoginEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
}
