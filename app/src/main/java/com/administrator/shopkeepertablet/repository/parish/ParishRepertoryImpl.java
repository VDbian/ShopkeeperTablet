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
    public Observable<BaseEntity<String>> getFoodTypeList(String type, String id, int index, int size) {
        return apiSource.getFoodTypeList(type,id,index,size).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<BaseEntity<String>> order(String type, String id, String tableId, String billId, String info, String UserId, String name, String tableName, String price, String foodType, String fanBill) {
        return apiSource.order(type, id, tableId, billId, info, UserId, name, tableName, price, foodType, fanBill).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    @Override
    public Observable<BaseEntity<String>> getOrderFoodList(String type, String id, String billId) {
        return apiSource.getOrderFoodList(type,id,billId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<BaseEntity<String>> getFoodKouweiList(String id, int index, int size) {
        return apiSource.getFoodKouweiList(id, index, size).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<BaseEntity<String>> cancelOrder(String type, String tableId, String billId, String id, String tableName, String userName, String userId) {
        return apiSource.cancelOrder(type, tableId, billId, id, tableName, userName, userId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<BaseEntity<String>> changePeople(String type, String tableId, String peopleNum, String wareNum, String billId, String id) {
        return apiSource.changePeople(type, tableId, peopleNum, wareNum, billId, id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<BaseEntity<String>> changeTable(String type, String newTableId, String newTableName, String oldTableId, String billId) {
        return apiSource.changeTable(type, newTableId, newTableName, oldTableId, billId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
