package com.administrator.shopkeepertablet.repository.order;

import com.administrator.shopkeepertablet.model.api.ApiSource;
import com.administrator.shopkeepertablet.model.entity.BaseEntity;
import com.administrator.shopkeepertablet.model.preference.PreferenceSource;
import com.administrator.shopkeepertablet.repository.BaseRepertoryImpl;

import java.io.IOError;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Description:
 * Author CC
 * Time 2018/7/23
 */


public class OrderRepositoryImpl extends BaseRepertoryImpl implements OrderRepository {
    private ApiSource apiSource;
    private PreferenceSource preferenceSource;

    public OrderRepositoryImpl(ApiSource apiSource, PreferenceSource preferenceSource) {
        super(apiSource, preferenceSource);
        this.apiSource = apiSource;
        this.preferenceSource = preferenceSource;
    }

    @Override
    public Observable<BaseEntity<String>> getOrderList(String type, String id, String orderType, String orderState, String phone, int index, int size, String state) {
        return apiSource.getOrderList(type, id, orderType, orderState, phone, index, size, state).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<BaseEntity<String>> getOrderDetail(String id, String type, String billId) {
        return apiSource.getOrderDetail(id, type, billId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
