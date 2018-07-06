package com.administrator.shopkeepertablet.di.mian;


import com.administrator.shopkeepertablet.di.Activity;
import com.administrator.shopkeepertablet.di.app.AppComponent;
import com.administrator.shopkeepertablet.view.ui.activity.LoginActivity;
import com.administrator.shopkeepertablet.view.ui.activity.MainActivity;

import dagger.Component;

/**
 * Description:
 * Author CC
 * Time 2018/6/14
 */
@Activity
@Component(dependencies = AppComponent.class, modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
