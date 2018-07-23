package com.administrator.shopkeepertablet.di.fast;

import com.administrator.shopkeepertablet.di.Fragment;
import com.administrator.shopkeepertablet.di.app.AppComponent;
import com.administrator.shopkeepertablet.view.ui.fragment.FastFoodFragment;

import dagger.Component;

/**
 * Description:
 * Author CC
 * Time 2018/7/23
 */

@Fragment
@Component(dependencies = AppComponent.class,modules = FastModule.class)
public interface FastComponent {
   void inject(FastFoodFragment fastFoodFragment);
}
