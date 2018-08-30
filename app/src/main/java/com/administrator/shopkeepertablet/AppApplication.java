package com.administrator.shopkeepertablet;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.administrator.shopkeepertablet.di.app.AppComponent;
import com.administrator.shopkeepertablet.di.app.AppModule;
import com.administrator.shopkeepertablet.di.app.DaggerAppComponent;
import com.administrator.shopkeepertablet.di.app.NetworkModule;
import com.administrator.shopkeepertablet.model.entity.UserInfoEntity;
import com.administrator.shopkeepertablet.model.greendao.DaoMaster;
import com.administrator.shopkeepertablet.model.greendao.DaoSession;
import com.administrator.shopkeepertablet.push.MyPushIntentService;
import com.administrator.shopkeepertablet.repository.BaseRepertory;
import com.administrator.shopkeepertablet.repository.BaseRepertoryImpl;
import com.administrator.shopkeepertablet.utils.MToast;
import com.iflytek.cloud.SpeechUtility;
import com.umeng.message.IUmengCallback;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
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
    private PushAgent mPushAgent;
    public static final String UPDATE_STATUS_ACTION = "com.administrator.shopkeepertablet.action.UPDATE_STATUS";
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
        initPush();
        SpeechUtility.createUtility(this, "appid=59c1e9f6");
        ZXingLibrary.initDisplayOpinion(this);
    }

    private void initPush() {
        mPushAgent = PushAgent.getInstance(this);
        //设置debug模式
        mPushAgent.setDebugMode(false);

        mPushAgent.register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String deviceToken) {
                sendBroadcast(new Intent(UPDATE_STATUS_ACTION));
            }

            @Override
            public void onFailure(String s, String s1) {
                sendBroadcast(new Intent(UPDATE_STATUS_ACTION));
            }
        });
        //此处是完全自定义处理设置
        mPushAgent.setPushIntentServiceClass(MyPushIntentService.class);

    }

    //设置别名
    public void setAlias(String id, String type) {
        mPushAgent.addExclusiveAlias(id, type, (b, s) -> MToast.showToast( this, (b ? "绑定成功" : "绑定失败")));
    }

    //移除别名
    public void removeAlias(String id, String type) {
        mPushAgent.removeAlias(id, type, (b, s) -> MToast.showToast( this, b ? "解绑成功" : "解绑失败"));
    }

    //关闭推送
    public void disablePush() {
        mPushAgent.disable(new IUmengCallback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onFailure(String s, String s1) {

            }
        });
    }

    //打开推送
    public void enablePush() {
        mPushAgent.enable(new IUmengCallback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onFailure(String s, String s1) {

            }
        });
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
