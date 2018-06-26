package com.administrator.shopkeepertablet.view.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.administrator.shopkeepertablet.AppApplication;
import com.administrator.shopkeepertablet.di.app.AppComponent;
import com.gyf.barlibrary.ImmersionBar;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.List;


/**
 * Description:
 * Author CC
 * Time 2018/6/11
 */
public abstract class BaseActivity extends AutoLayoutActivity {

    protected abstract void setupActivityComponent(AppComponent appComponent);

    private static final String TAG = "BaseActivity";
    private ImmersionBar immersionBar;
    private static long lastClickTime = 0;
    private static long DIFF = 1000;
    private static int lastButtonId = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivityComponent(AppApplication.get(this).getAppComponent());
        immersionBar = ImmersionBar.with(this)
                .keyboardEnable(true);
        immersionBar.init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (immersionBar != null) {
            immersionBar.destroy();
        }
    }

    public boolean isFastDoubleClick(int buttonId, long diff) {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (lastButtonId == buttonId && lastClickTime > 0 && timeD < diff) {
            return true;
        }
        lastClickTime = time;
        lastButtonId = buttonId;
        return false;
    }

    public void hideInput(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null && imm.isActive()) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}
