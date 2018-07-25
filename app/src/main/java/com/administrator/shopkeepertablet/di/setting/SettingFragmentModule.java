package com.administrator.shopkeepertablet.di.setting;


import com.administrator.shopkeepertablet.di.Fragment;
import com.administrator.shopkeepertablet.model.api.ApiSource;
import com.administrator.shopkeepertablet.model.preference.PreferenceSource;
import com.administrator.shopkeepertablet.repository.setting.SettingRepertory;
import com.administrator.shopkeepertablet.repository.setting.SettingRepertoryImpl;
import com.administrator.shopkeepertablet.view.ui.fragment.SettingFragment;
import com.administrator.shopkeepertablet.viewmodel.SettingFragmentViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Description:
 * Author CC
 * Time 2018/6/14
 */
@Module
public class SettingFragmentModule {
   private SettingFragment settingFragment;

    public SettingFragmentModule(SettingFragment settingFragment) {
        this.settingFragment = settingFragment;
    }

    @Provides
    @Fragment
    SettingRepertory provideSettingRepertory(ApiSource apiSource,PreferenceSource preferenceSource){
        return new SettingRepertoryImpl(apiSource,preferenceSource);
    }

    @Provides
    @Fragment
    SettingFragmentViewModel provideSettingFragmentViewModel(SettingRepertory settingRepertory, PreferenceSource preferenceSource){
        return new SettingFragmentViewModel(settingFragment,preferenceSource,settingRepertory);
    }
}
