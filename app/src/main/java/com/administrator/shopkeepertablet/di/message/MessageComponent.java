package com.administrator.shopkeepertablet.di.message;

import com.administrator.shopkeepertablet.di.Fragment;
import com.administrator.shopkeepertablet.di.app.AppComponent;
import com.administrator.shopkeepertablet.view.ui.fragment.MessageFragment;

import dagger.Component;

/**
 * Description:
 * Author CC
 * Time 2018/7/23
 */

@Fragment
@Component(dependencies = AppComponent.class, modules = MessageModule.class)
public interface MessageComponent {
    void inject(MessageFragment messageFragment);
}
