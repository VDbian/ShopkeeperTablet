package com.administrator.shopkeepertablet.view.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.databinding.ActivityLoginBinding;
import com.administrator.shopkeepertablet.di.app.AppComponent;
import com.administrator.shopkeepertablet.di.login.DaggerLoginComponent;
import com.administrator.shopkeepertablet.di.login.LoginModule;
import com.administrator.shopkeepertablet.view.ui.BaseActivity;
import com.administrator.shopkeepertablet.viewmodel.LoginViewModel;

import javax.inject.Inject;

/**
 * Description:
 * Author CC
 * Time 2018/6/14
 */


public class LoginActivity extends BaseActivity implements View.OnClickListener {

    ActivityLoginBinding binding;
    @Inject
    LoginViewModel viewModel;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerLoginComponent.builder()
                .appComponent(appComponent)
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setViewModel(viewModel);
        initView();
    }

    private void initView() {
        binding.btnLogin.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                viewModel.loginViewModel();
                break;
            default:
                break;
        }
    }
}
