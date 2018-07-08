package com.administrator.shopkeepertablet.repository.parish;

import com.administrator.shopkeepertablet.model.api.ApiSource;
import com.administrator.shopkeepertablet.model.entity.BaseEntity;
import com.administrator.shopkeepertablet.model.entity.ResultFoodEntity;
import com.administrator.shopkeepertablet.model.preference.PreferenceSource;
import com.administrator.shopkeepertablet.repository.BaseRepertoryImpl;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Description:
 * Author CC
 * Time 2018/7/1
 */


public class ParishRepertoryImpl extends BaseRepertoryImpl implements ParishRepertory {
    private ApiSource apiSource;
    private PreferenceSource preferenceSource;

    public ParishRepertoryImpl(ApiSource apiSource, PreferenceSource preferenceSource) {
        super(apiSource, preferenceSource);
        this.apiSource = apiSource;
        this.preferenceSource = preferenceSource;
    }

    @Override
    public Observable<BaseEntity<String>> getRooms(String type, String id,int index, int size) {
        return apiSource.getRooms(type,id,index,size).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<BaseEntity<String>> getTables(String type, String leibie, String id, int index, int size) {
        return apiSource.getTables(type,leibie,id,index,size).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<BaseEntity<String>> openTable(String type, String tableId, String tableName, String id, String people, String ware, String userId, String name) {
        return apiSource.openTable(type,tableId,tableName,id,people,ware,userId,name).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<BaseEntity<String>> clearTable(String type, String tableId, String billId, String id) {
        return apiSource.clearTable(type,tableId,billId,id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<ResultFoodEntity> getFoodList(String type, String id) {
        return apiSource.getFoodList(type,id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<BaseEntity<String>> getFoodTypeList(String type, String id, int index, int size) {
        return apiSource.getFoodTypeList(type,id,index,size).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
