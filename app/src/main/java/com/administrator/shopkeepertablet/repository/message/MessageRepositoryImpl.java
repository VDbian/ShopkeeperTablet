package com.administrator.shopkeepertablet.repository.message;

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


public class MessageRepositoryImpl extends BaseRepertoryImpl implements MessageRepository {
    private ApiSource apiSource;
    private PreferenceSource preferenceSource;

    public MessageRepositoryImpl(ApiSource apiSource, PreferenceSource preferenceSource) {
        super(apiSource, preferenceSource);
        this.apiSource = apiSource;
        this.preferenceSource = preferenceSource;
    }

    @Override
    public Observable<BaseEntity<String>> getMessage(String type, String id, String leibie, String status, String phone, int index, int size) {
        return apiSource.getMessage(type, id, leibie, status, phone, index, size).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<BaseEntity<String>> getOrderDetail(String id, String type, String billId) {
        return apiSource.getOrderDetail(id, type, billId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<BaseEntity<String>> confirm(String type, String shopID, String orderId, String billId, String types) {
        return apiSource.confirm(type, shopID, orderId, billId, types).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<BaseEntity<String>> cancel(String type, String shopID, String orderId) {
        return apiSource.cancel(type, shopID, orderId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<BaseEntity<String>> bindTable(String type, String orderId, String tableId, String id, String tableWareCount, String name, String tableName) {
        return apiSource.bindTable(type, orderId, tableId, id, tableWareCount, name, tableName).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
