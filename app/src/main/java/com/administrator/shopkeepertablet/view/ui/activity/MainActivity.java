package com.administrator.shopkeepertablet.view.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.databinding.ActivityMainBinding;
import com.administrator.shopkeepertablet.di.app.AppComponent;
import com.administrator.shopkeepertablet.view.ui.BaseActivity;

/**
 * Description:
 * Author CC
 * Time 2018/6/23
 */


public class MainActivity extends BaseActivity {
    ActivityMainBinding binding;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.tabParishFood.setViewSelect(true);
    }
}
