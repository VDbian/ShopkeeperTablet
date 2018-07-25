package com.administrator.shopkeepertablet.di.order;

import com.administrator.shopkeepertablet.di.Fragment;
import com.administrator.shopkeepertablet.di.app.AppComponent;
import com.administrator.shopkeepertablet.view.ui.fragment.OrderFragment;

import dagger.Component;

/**
 * Description:
 * Author CC
 * Time 2018/7/23
 */

@Fragment
@Component(dependencies = AppComponent.class, modules = OrderModule.class)
public interface OrderComponent {
    void inject(OrderFragment orderFragment);
}
