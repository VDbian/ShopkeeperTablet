package com.administrator.shopkeepertablet.view.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.databinding.FragmentLineUpBinding;
import com.administrator.shopkeepertablet.databinding.FragmentRechargeBinding;
import com.administrator.shopkeepertablet.di.app.AppComponent;
import com.administrator.shopkeepertablet.di.recharge.DaggerRechargeComponent;
import com.administrator.shopkeepertablet.di.recharge.RechargeModule;
import com.administrator.shopkeepertablet.view.ui.BaseFragment;

/**
 * Description:
 * Author chencheng
 * Time 2018/7/25
 */

public class RechargeFragment extends BaseFragment {
    FragmentRechargeBinding binding;

    @Override
    protected void setupFragmentComponent(AppComponent appComponent) {
        DaggerRechargeComponent.builder()
                .appComponent(appComponent)
                .rechargeModule(new RechargeModule(this))
                .build()
                .inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recharge, container, false);
//        binding.setViewModel(viewModel);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        EventBus.getDefault().register(this);
        initView();
    }

    private void initView() {

    }
}
