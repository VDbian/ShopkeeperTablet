package com.administrator.shopkeepertablet.di.app;

import android.app.Application;

import com.administrator.shopkeepertablet.model.api.ApiSource;
import com.administrator.shopkeepertablet.model.preference.PreferenceSource;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Description:
 * Author CC
 * Time 2018/6/11
 */
@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {
    Application getApplication();

    ApiSource getApiSource();

    PreferenceSource getPreferenceSource();
}
