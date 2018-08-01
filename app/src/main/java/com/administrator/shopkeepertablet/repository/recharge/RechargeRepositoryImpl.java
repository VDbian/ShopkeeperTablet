package com.administrator.shopkeepertablet.repository.recharge;

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


public class RechargeRepositoryImpl extends BaseRepertoryImpl implements RechargeRepository {
    private ApiSource apiSource;
    private PreferenceSource preferenceSource;

    public RechargeRepositoryImpl(ApiSource apiSource, PreferenceSource preferenceSource) {
        super(apiSource, preferenceSource);
        this.apiSource = apiSource;
        this.preferenceSource =preferenceSource;
    }

    @Override
    public Observable<BaseEntity<String>> getRechargeMember(String type, String id, String pageIndex, String pageSize, String name, String phone) {
        return apiSource.getRechargeMember(type, id, pageIndex, pageSize, name, phone).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<BaseEntity<String>> getRecharge(String type, String id) {
        return apiSource.getRecharge(type, id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<BaseEntity<String>> addRecharge(String type, String shopId, String staffTel, String staffDepart, String staffLanguage, String staffCatalogue) {
        return apiSource.addRecharge(type, shopId, staffTel, staffDepart, staffLanguage, staffCatalogue).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<BaseEntity<String>> checkCode(String type, String shopId, String passWord) {
        return apiSource.checkCode(type, shopId, passWord).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<BaseEntity<String>> moneyCharge(String type, String userID, String shopId, String price, int payType, String operaName, String operaId) {
        return apiSource.moneyCharge(type, userID, shopId, price, payType, operaName, operaId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<BaseEntity<String>> productCharge(String type, String userID, String shopId, String cardID, int payType, String operaName, String operaId) {
        return apiSource.productCharge(type, userID, shopId, cardID, payType, operaName, operaId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
