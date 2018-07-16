package com.administrator.shopkeepertablet.di.parish;


import com.administrator.shopkeepertablet.di.Activity;
import com.administrator.shopkeepertablet.di.app.AppComponent;
import com.administrator.shopkeepertablet.di.login.LoginModule;
import com.administrator.shopkeepertablet.view.ui.activity.LoginActivity;
import com.administrator.shopkeepertablet.view.ui.activity.parish.OrderDishesActivity;
import com.administrator.shopkeepertablet.view.ui.activity.parish.TableActivity;

import dagger.Component;

/**
 * Description:
 * Author CC
 * Time 2018/6/14
 */
@Activity
@Component(dependencies = AppComponent.class, modules = ParishActivityModule.class)
public interface ParishActivityComponent {
    void inject(OrderDishesActivity orderDishesActivity);
    void inject(TableActivity tableActivity);
}
