package com.administrator.shopkeepertablet.viewmodel;

import com.administrator.shopkeepertablet.model.entity.BaseEntity;
import com.administrator.shopkeepertablet.model.preference.PreferenceSource;
import com.administrator.shopkeepertablet.repository.LoginRepertory;
import com.administrator.shopkeepertablet.utils.MLog;
import com.administrator.shopkeepertablet.view.ui.activity.LoginActivity;

import io.reactivex.functions.Consumer;

/**
 * Description:
 * Author CC
 * Time 2018/6/14
 */


public class LoginViewModel extends BaseViewModel {
    public PreferenceSource preferenceSource;
    private LoginRepertory loginRepertory;
    private LoginActivity loginActivity;

    public LoginViewModel(LoginRepertory loginRepertory, LoginActivity loginActivity, PreferenceSource preferenceSource) {
        this.loginRepertory = loginRepertory;
        this.loginActivity = loginActivity;
        this.preferenceSource = preferenceSource;
    }

    public void loginViewModel(){
        loginRepertory.login("老板","4B176F0E-0553-4094-8181-5048641B20EF","123")
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        MLog.e("api",stringBaseEntity.getCode()+"");
                        MLog.e("api",stringBaseEntity.getResult()+"");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        MLog.e("api",throwable.getMessage());
                    }
                });

    }
}
