package com.administrator.shopkeepertablet.view.ui.activity.setting;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.databinding.ActivitySettingBinding;
import com.administrator.shopkeepertablet.di.app.AppComponent;
import com.administrator.shopkeepertablet.di.setting.DaggerSettingComponent;
import com.administrator.shopkeepertablet.di.setting.SettingModule;
import com.administrator.shopkeepertablet.utils.MToast;
import com.administrator.shopkeepertablet.view.ui.BaseActivity;
import com.administrator.shopkeepertablet.viewmodel.SettingViewModel;

import javax.inject.Inject;

/**
 * Description:
 * Author CC
 * Time 2018/7/10
 */


public class SettingActivity extends BaseActivity implements View.OnClickListener {

   private  ActivitySettingBinding binding;

    @Inject
    SettingViewModel viewModel;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerSettingComponent.builder()
                .appComponent(appComponent)
                .settingModule(new SettingModule(this))
                .build()
                .inject(this);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_setting);
        binding.setViewModel(viewModel);
        initView();
    }


    private void initView(){
        binding.ivBack.setOnClickListener(this);
        binding.tvFood.setOnClickListener(this);
        binding.tvRoom.setOnClickListener(this);
        binding.tvConfirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_food:
                viewModel.getFoodList();
                break;
            case R.id.tv_room:
                MToast.showToast(this,"房间刷新成功");
                break;
            case R.id.tv_confirm:
                viewModel.setPreferenceId();
                break;
        }
    }
}
