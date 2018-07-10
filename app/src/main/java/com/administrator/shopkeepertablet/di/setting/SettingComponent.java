package com.administrator.shopkeepertablet.di.setting;

import com.administrator.shopkeepertablet.di.Activity;
import com.administrator.shopkeepertablet.di.app.AppComponent;
import com.administrator.shopkeepertablet.di.mian.MainModule;
import com.administrator.shopkeepertablet.repository.setting.SettingRepertory;
import com.administrator.shopkeepertablet.view.ui.activity.MainActivity;
import com.administrator.shopkeepertablet.view.ui.activity.setting.SettingActivity;

import dagger.Component;

/**
 * Description:
 * Author CC
 * Time 2018/7/10
 */

@Activity
@Component(dependencies = AppComponent.class, modules = SettingModule.class)
public interface SettingComponent {
    void inject(SettingActivity settingActivity);
}
