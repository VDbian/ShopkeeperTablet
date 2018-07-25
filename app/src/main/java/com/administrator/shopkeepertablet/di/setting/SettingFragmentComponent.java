package com.administrator.shopkeepertablet.di.setting;

import com.administrator.shopkeepertablet.di.Fragment;
import com.administrator.shopkeepertablet.di.app.AppComponent;
import com.administrator.shopkeepertablet.view.ui.fragment.SettingFragment;

import dagger.Component;

/**
 * Description:
 * Author CC
 * Time 2018/7/10
 */

@Fragment
@Component(dependencies = AppComponent.class, modules = SettingFragmentModule.class)
public interface SettingFragmentComponent {
    void inject(SettingFragment settingFragment);
}
