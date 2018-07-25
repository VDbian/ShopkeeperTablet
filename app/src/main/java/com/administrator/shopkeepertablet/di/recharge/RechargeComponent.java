package com.administrator.shopkeepertablet.di.recharge;

import com.administrator.shopkeepertablet.di.Fragment;
import com.administrator.shopkeepertablet.di.app.AppComponent;
import com.administrator.shopkeepertablet.view.ui.fragment.RechargeFragment;

import dagger.Component;

/**
 * Description:
 * Author CC
 * Time 2018/7/23
 */

@Fragment
@Component(dependencies = AppComponent.class, modules = RechargeModule.class)
public interface RechargeComponent {
    void inject(RechargeFragment rechargeFragment);
}
