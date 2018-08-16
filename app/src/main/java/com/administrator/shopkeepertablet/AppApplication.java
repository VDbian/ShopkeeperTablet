package com.administrator.shopkeepertablet;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.multidex.MultiDexApplication;

import com.administrator.shopkeepertablet.di.app.AppComponent;
import com.administrator.shopkeepertablet.di.app.AppModule;
import com.administrator.shopkeepertablet.di.app.DaggerAppComponent;
import com.administrator.shopkeepertablet.di.app.NetworkModule;
import com.administrator.shopkeepertablet.model.entity.UserInfoEntity;
import com.administrator.shopkeepertablet.model.greendao.DaoMaster;
import com.administrator.shopkeepertablet.model.greendao.DaoSession;
import com.iflytek.cloud.SpeechUtility;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;


/**
 * Description:
 * Author CC
 * Time 2018/6/11
 */
public class AppApplication extends MultiDexApplication {

    private AppComponent appComponent;

    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    //    @Override
//    protected void attachBaseContext(Context context) {
//        super.attachBaseContext(context);
//        MultiDex.install(context);
//    }

    @Override
    public void onCreate() {
        super.onCreate();
        //SugarContext.init(new SugarContextWrapper(this, AppConstant.DB_PATH));  //数据库保存指定路径下
        initComponent();
        setDatabase();
        SpeechUtility.createUtility(this, "appid=59c1e9f6");
        ZXingLibrary.initDisplayOpinion(this);
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

    /**
     * 设置greenDao
     */
    private void setDatabase() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        mHelper = new DaoMaster.DevOpenHelper(this, "shopkeeper_tablet-db", null);
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }
    public DaoSession getDaoSession() {
        return mDaoSession;
    }
    public SQLiteDatabase getDb() {
        return db;
    }

}
