package com.administrator.shopkeepertablet.di.parish;


import com.administrator.shopkeepertablet.di.Activity;
import com.administrator.shopkeepertablet.di.app.AppComponent;
import com.administrator.shopkeepertablet.di.login.LoginModule;
import com.administrator.shopkeepertablet.view.ui.activity.LoginActivity;

import dagger.Component;

/**
 * Description:
 * Author CC
 * Time 2018/6/14
 */
@Activity
@Component(dependencies = AppComponent.class, modules = LoginModule.class)
public interface ParishFragmentComponent {
    void inject(LoginActivity loginActivity);
}
