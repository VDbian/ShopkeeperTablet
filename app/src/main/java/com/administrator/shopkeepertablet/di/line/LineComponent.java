package com.administrator.shopkeepertablet.di.line;

import com.administrator.shopkeepertablet.di.Fragment;
import com.administrator.shopkeepertablet.di.app.AppComponent;
import com.administrator.shopkeepertablet.view.ui.fragment.LineUpFragment;

import dagger.Component;

/**
 * Description:
 * Author CC
 * Time 2018/7/23
 */

@Fragment
@Component(dependencies = AppComponent.class, modules = LineModule.class)
public interface LineComponent {
    void inject(LineUpFragment lineUpFragment);
}
