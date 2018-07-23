package com.administrator.shopkeepertablet.view.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.databinding.FragmentFastFoodBinding;
import com.administrator.shopkeepertablet.di.app.AppComponent;
import com.administrator.shopkeepertablet.di.fast.DaggerFastComponent;
import com.administrator.shopkeepertablet.di.fast.FastModule;
import com.administrator.shopkeepertablet.view.ui.BaseFragment;
import com.administrator.shopkeepertablet.viewmodel.fast.FastViewModel;

import javax.inject.Inject;

/**
 * Description:
 * Author CC
 * Time 2018/7/23
 */


public class FastFoodFragment extends BaseFragment {

    private FragmentFastFoodBinding binding;
    @Inject
    FastViewModel viewModel;

    @Override
    protected void setupFragmentComponent(AppComponent appComponent) {
        DaggerFastComponent.builder()
                .appComponent(appComponent)
                .fastModule(new FastModule(this))
                .build()
                .inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_fast_food, container, false);
        binding.setViewModel(viewModel);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        EventBus.getDefault().register(this);
    }
}
