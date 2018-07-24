package com.administrator.shopkeepertablet.view.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.databinding.ActivityMainBinding;
import com.administrator.shopkeepertablet.di.app.AppComponent;
import com.administrator.shopkeepertablet.di.mian.DaggerMainComponent;
import com.administrator.shopkeepertablet.di.mian.MainModule;
import com.administrator.shopkeepertablet.view.ui.BaseActivity;
import com.administrator.shopkeepertablet.view.ui.fragment.FastFoodFragment;
import com.administrator.shopkeepertablet.view.ui.fragment.ParishFoodFragment;
import com.administrator.shopkeepertablet.viewmodel.MainViewModel;

import javax.inject.Inject;

/**
 * Description:
 * Author CC
 * Time 2018/6/23
 */


public class MainActivity extends BaseActivity implements View.OnClickListener {
    ActivityMainBinding binding;

    @Inject
    MainViewModel viewModel;


    public static final int FRAGMENT_PARISH = 0;
    public static final int FRAGMENT_FAST = 1;
//    public static final int FRAGMENT_ORDER = 2;
//    public static final int FRAGMENT_RESERVE = 3;
//    public static final int FRAGMENT_LINEUP = 4;
//    public static final int FRAGMENT_RECHARGE = 5;
//    public static final int FRAGMENT_MESSAGE = 6;
//    public static final int FRAGMENT_PRINTER = 7;
//    public static final int FRAGMENT_SETTING = 8;

    protected FragmentManager fragmentManager;
    private Fragment myFragment[] = new Fragment[2];

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        binding.setViewModel(viewModel);
        initView();
    }

    private void initView() {
        initFragment();
        binding.tvCashier.setText(viewModel.getUserName());
        binding.tabParishFood.setOnClickListener(this);
        binding.tabFastFood.setOnClickListener(this);
        binding.tabLineup.setOnClickListener(this);
        binding.tabMessage.setOnClickListener(this);
        binding.tabOrderList.setOnClickListener(this);
        binding.tabPrinter.setOnClickListener(this);
        binding.tabRecharge.setOnClickListener(this);
        binding.tabReserve.setOnClickListener(this);
        binding.tabSetting.setOnClickListener(this);
    }

    private void initFragment() {
        fragmentManager = getSupportFragmentManager();
        myFragment[FRAGMENT_PARISH] = new ParishFoodFragment();
        myFragment[FRAGMENT_FAST] = new FastFoodFragment();
//        myFragment[FRAGMENT_SELF] = new SelfFragment();
        fragmentManager.beginTransaction().add(R.id.frame_layout, myFragment[FRAGMENT_PARISH]).commit();
        showTabView(FRAGMENT_PARISH);
    }

    public void showTabView(int index) {
        binding.tabParishFood.setViewSelect(false);
        binding.tabFastFood.setViewSelect(false);
        binding.tabLineup.setViewSelect(false);
        binding.tabMessage.setViewSelect(false);
        binding.tabOrderList.setViewSelect(false);
        binding.tabPrinter.setViewSelect(false);
        binding.tabRecharge.setViewSelect(false);
        binding.tabReserve.setViewSelect(false);
        binding.tabSetting.setViewSelect(false);

        switch (index) {
            case FRAGMENT_PARISH:
                binding.tabParishFood.setViewSelect(true);
                break;
            case FRAGMENT_FAST:
                binding.tabFastFood.setViewSelect(true);
                break;
//            case FRAGMENT_ORDER:
//                binding.tabOrderList.setViewSelect(true);
//                break;
//            case FRAGMENT_RESERVE:
//                binding.tabReserve.setViewSelect(true);
//                break;
//            case FRAGMENT_LINEUP:
//                binding.tabLineup.setViewSelect(true);
//                break;
//            case FRAGMENT_RECHARGE:
//                binding.tabRecharge.setViewSelect(true);
//                break;
//            case FRAGMENT_MESSAGE:
//                binding.tabMessage.setViewSelect(true);
//                break;
//            case FRAGMENT_PRINTER:
//                binding.tabPrinter.setViewSelect(true);
//                break;
//            case FRAGMENT_SETTING:
//                binding.tabSetting.setViewSelect(true);
//                break;
        }
    }

    public void displayFrg(int index) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout, myFragment[index]).commitAllowingStateLoss();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tab_parish_food:
                showTabView(FRAGMENT_PARISH);
                displayFrg(FRAGMENT_PARISH);
                break;
            case R.id.tab_fast_food:
                showTabView(FRAGMENT_FAST);
                displayFrg(FRAGMENT_FAST);
                break;
            case R.id.tab_lineup:
                break;
            case R.id.tab_message:
                break;
            case R.id.tab_order_list:
                break;
            case R.id.tab_printer:
                break;
            case R.id.tab_recharge:
                break;
            case R.id.tab_reserve:
                break;
            case R.id.tab_setting:
                break;
        }
    }
}
