package com.administrator.shopkeepertablet.repository.login;

import com.administrator.shopkeepertablet.model.api.ApiSource;
import com.administrator.shopkeepertablet.model.entity.BaseEntity;
import com.administrator.shopkeepertablet.model.entity.UserInfoEntity;
import com.administrator.shopkeepertablet.model.preference.PreferenceSource;
import com.administrator.shopkeepertablet.repository.BaseRepertoryImpl;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Description:
 * Author CC
 * Time 2018/6/14
 */


public class LoginRepertoryImpl extends BaseRepertoryImpl implements LoginRepertory {
    private ApiSource apiSource;
    private PreferenceSource preferenceSource;

    public LoginRepertoryImpl(ApiSource apiSource, PreferenceSource preferenceSource) {
        super(apiSource, preferenceSource);
        this.apiSource = apiSource;
        this.preferenceSource = preferenceSource;
    }

    @Override
    public Observable<BaseEntity<String>> login(String name, String id, String pwd) {
        return apiSource.login(name,id,pwd).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
