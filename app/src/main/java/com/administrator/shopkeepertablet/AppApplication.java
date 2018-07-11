package com.administrator.shopkeepertablet;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;


import com.administrator.shopkeepertablet.di.app.AppComponent;
import com.administrator.shopkeepertablet.di.app.AppModule;
import com.administrator.shopkeepertablet.di.app.DaggerAppComponent;
import com.administrator.shopkeepertablet.di.app.NetworkModule;

import org.litepal.LitePalApplication;

import java.util.Map;


/**
 * Description:
 * Author CC
 * Time 2018/6/11
 */
public class AppApplication extends LitePalApplication {

    private AppComponent appComponent;

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(context);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //SugarContext.init(new SugarContextWrapper(this, AppConstant.DB_PATH));  //数据库保存指定路径下
        initComponent();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    private void initComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public static AppApplication get(Context context) {
        return (AppApplication) context.getApplicationContext();
    }
}
