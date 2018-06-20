package com.administrator.shopkeepertablet.di.login;


import com.administrator.shopkeepertablet.di.Activity;
import com.administrator.shopkeepertablet.model.api.ApiSource;
import com.administrator.shopkeepertablet.model.preference.PreferenceSource;
import com.administrator.shopkeepertablet.repository.LoginRepertory;
import com.administrator.shopkeepertablet.repository.impl.LoginRepertoryImpl;
import com.administrator.shopkeepertablet.view.ui.activity.LoginActivity;
import com.administrator.shopkeepertablet.viewmodel.LoginViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Description:
 * Author CC
 * Time 2018/6/14
 */
@Module
public class LoginModule {
    private LoginActivity loginActivity;


    public LoginModule(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
    }


    @Provides
    @Activity
    LoginRepertory provideLoginRepertory(ApiSource apiSource, PreferenceSource preferenceSource) {
        return new LoginRepertoryImpl(apiSource, preferenceSource);
    }

    @Provides
    @Activity
    LoginViewModel provideLoginViewModel(LoginRepertory loginRepertory, PreferenceSource preferenceSource) {
        return new LoginViewModel(loginRepertory, loginActivity, preferenceSource);
    }

}
