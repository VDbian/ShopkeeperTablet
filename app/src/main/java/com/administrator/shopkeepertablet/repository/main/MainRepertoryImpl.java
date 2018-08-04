package com.administrator.shopkeepertablet.repository.main;

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
 * Time 2018/7/1
 */


public class MainRepertoryImpl extends BaseRepertoryImpl implements MainRepertory {
    private ApiSource apiSource;
    private PreferenceSource preferenceSource;

    public MainRepertoryImpl(ApiSource apiSource, PreferenceSource preferenceSource) {
        super(apiSource, preferenceSource);
        this.apiSource = apiSource;
        this.preferenceSource = preferenceSource;
    }


    @Override
    public Observable<BaseEntity<String>> jiaoBanPrint(String s, String s1, String shopID, String name, String s2, String id) {
        return apiSource.jiaoBanPrint(s, s1, shopID, name, s2, id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
