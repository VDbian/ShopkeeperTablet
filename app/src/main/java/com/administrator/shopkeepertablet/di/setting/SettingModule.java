package com.administrator.shopkeepertablet.di.setting;


import com.administrator.shopkeepertablet.di.Activity;
import com.administrator.shopkeepertablet.model.api.ApiSource;
import com.administrator.shopkeepertablet.model.preference.PreferenceSource;
import com.administrator.shopkeepertablet.repository.setting.SettingRepertory;
import com.administrator.shopkeepertablet.repository.setting.SettingRepertoryImpl;
import com.administrator.shopkeepertablet.view.ui.activity.setting.SettingActivity;
import com.administrator.shopkeepertablet.viewmodel.SettingViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Description:
 * Author CC
 * Time 2018/6/14
 */
@Module
public class SettingModule {
   private SettingActivity settingActivity;

    public SettingModule(SettingActivity settingActivity) {
        this.settingActivity = settingActivity;
    }

    @Provides
    @Activity
    SettingRepertory provideSettingRepertory(ApiSource apiSource,PreferenceSource preferenceSource){
        return new SettingRepertoryImpl(apiSource,preferenceSource);
    }

    @Provides
    @Activity
    SettingViewModel provideSettingViewModel(SettingRepertory settingRepertory,PreferenceSource preferenceSource){
        return new SettingViewModel(settingActivity,settingRepertory,preferenceSource);
    }
}
