package com.administrator.shopkeepertablet.repository.line;

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


public class LineUpRepositoryImpl extends BaseRepertoryImpl implements LineUpRepository {
    private ApiSource apiSource;
    private PreferenceSource preferenceSource;

    public LineUpRepositoryImpl(ApiSource apiSource, PreferenceSource preferenceSource) {
        super(apiSource, preferenceSource);
        this.apiSource = apiSource;
        this.preferenceSource =preferenceSource;
    }

    @Override
    public Observable<BaseEntity<String>> getTableType(String type, String id) {
        return apiSource.getTableType(type, id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<BaseEntity<String>> getQueue(String type, String leibie, String phone, String shopId) {
        return apiSource.getQueue(type, leibie, phone, shopId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<BaseEntity<String>> addQueue(String type, String selvalue, String shopId) {
        return apiSource.addQueue(type, selvalue, shopId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<BaseEntity<String>> bindQueue(String type, String selvalue, String name, String userID, String tableId, String tableName, String tableWareCount, String orderid, String shopId) {
        return apiSource.bindQueue(type, selvalue, name, userID, tableId, tableName, tableWareCount, orderid, shopId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<BaseEntity<String>> deleteQueue(String type, String orderid) {
        return apiSource.deleteQueue(type, orderid).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
