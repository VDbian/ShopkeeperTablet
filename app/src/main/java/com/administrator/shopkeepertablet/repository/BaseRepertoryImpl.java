package com.administrator.shopkeepertablet.repository;

import com.administrator.shopkeepertablet.model.api.ApiSource;
import com.administrator.shopkeepertablet.model.entity.BaseEntity;
import com.administrator.shopkeepertablet.model.preference.PreferenceSource;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Description:
 * Author CC
 * Time 2018/6/14
 */


public class BaseRepertoryImpl implements BaseRepertory {
    private ApiSource apiSource;
    private PreferenceSource preferenceSource;

    public BaseRepertoryImpl(ApiSource apiSource, PreferenceSource preferenceSource) {
        this.apiSource = apiSource;
        this.preferenceSource = preferenceSource;
    }

    @Override
    public Observable<BaseEntity<String>> getRooms(String type, String id, int index, int size) {
        return apiSource.getRooms(type,id,index,size).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<BaseEntity<String>> getTables(String type, String leibie, String id, int index, int size) {
        return apiSource.getTables(type,leibie,id,index,size).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<BaseEntity<String>> updatePrint(String type, String billId, String ipAddress) {
        return apiSource.updatePrint(type,billId,ipAddress);
    }
}
