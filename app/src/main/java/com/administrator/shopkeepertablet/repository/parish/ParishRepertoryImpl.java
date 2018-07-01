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
    public Observable<ResultFoodEntity> getFoodList(String id) {
        return apiSource.getFoodList(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
