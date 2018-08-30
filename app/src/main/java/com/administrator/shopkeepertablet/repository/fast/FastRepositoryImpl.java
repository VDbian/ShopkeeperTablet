package com.administrator.shopkeepertablet.repository.fast;

import com.administrator.shopkeepertablet.model.api.ApiSource;
import com.administrator.shopkeepertablet.model.entity.BaseEntity;
import com.administrator.shopkeepertablet.model.preference.PreferenceSource;
import com.administrator.shopkeepertablet.repository.BaseRepertoryImpl;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Description:
 * Author CC
 * Time 2018/7/23
 */


public class FastRepositoryImpl extends BaseRepertoryImpl implements FastRepository {
    private ApiSource apiSource;
    private PreferenceSource preferenceSource;

    public FastRepositoryImpl(ApiSource apiSource, PreferenceSource preferenceSource) {
        super(apiSource, preferenceSource);
        this.apiSource = apiSource;
        this.preferenceSource =preferenceSource;
    }

    @Override
    public Observable<BaseEntity<String>> fastFood(String type, String mark, String id, String info, String data, String time, String names, String address, String phone, String userId, String name, String remark, double money, String tableId, String tableName, String types, String fanBill, double price, String billType) {
        return apiSource.fastFood(type, mark, id, info, data, time, names, address, phone, userId, name, remark, money, tableId, tableName, types, fanBill, price, billType)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<BaseEntity<String>> getPay(String id, String type) {
        return apiSource.getPay(id, type).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<BaseEntity<String>> getOrderFoodList(String type, String id, String billId) {
        return apiSource.getOrderFoodList(type,id,billId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
