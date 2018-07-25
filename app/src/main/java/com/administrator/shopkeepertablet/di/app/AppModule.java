package com.administrator.shopkeepertablet.di.app;

import android.app.Application;

import com.administrator.shopkeepertablet.model.preference.PreferenceSource;
import com.cocosw.favor.FavorAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Description:
 * Author CC
 * Time 2018/6/11
 */
@Module
public class AppModule {

    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    PreferenceSource providePreferenceSource(Application application) {
        return new FavorAdapter.Builder(application.getApplicationContext()).build().create(PreferenceSource.class);
    }
}
