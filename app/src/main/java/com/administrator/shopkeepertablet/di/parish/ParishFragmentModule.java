package com.administrator.shopkeepertablet.di.parish;


import com.administrator.shopkeepertablet.di.Activity;
import com.administrator.shopkeepertablet.model.api.ApiSource;
import com.administrator.shopkeepertablet.model.preference.PreferenceSource;
import com.administrator.shopkeepertablet.repository.login.LoginRepertory;
import com.administrator.shopkeepertablet.repository.login.LoginRepertoryImpl;
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
public class ParishFragmentModule {
    private LoginActivity loginActivity;


    public ParishFragmentModule(LoginActivity loginActivity) {
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
