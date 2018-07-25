package com.administrator.shopkeepertablet.di.mian;


import com.administrator.shopkeepertablet.di.Activity;
import com.administrator.shopkeepertablet.model.api.ApiSource;
import com.administrator.shopkeepertablet.model.preference.PreferenceSource;
import com.administrator.shopkeepertablet.repository.main.MainRepertory;
import com.administrator.shopkeepertablet.repository.main.MainRepertoryImpl;
import com.administrator.shopkeepertablet.view.ui.activity.MainActivity;
import com.administrator.shopkeepertablet.viewmodel.MainViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Description:
 * Author CC
 * Time 2018/6/14
 */
@Module
public class MainModule {
    private MainActivity mainActivity;


    public MainModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }


    @Provides
    @Activity
    MainRepertory provideMainRepertory(ApiSource apiSource, PreferenceSource preferenceSource) {
        return new MainRepertoryImpl(apiSource, preferenceSource);
    }

    @Provides
    @Activity
    MainViewModel provideMainViewModel(MainRepertory mainRepertory, PreferenceSource preferenceSource) {
        return new MainViewModel(mainActivity, mainRepertory, preferenceSource);
    }

}
