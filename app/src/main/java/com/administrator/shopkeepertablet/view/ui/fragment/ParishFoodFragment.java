package com.administrator.shopkeepertablet.view.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.databinding.FragmentParishFoodBinding;
import com.administrator.shopkeepertablet.di.app.AppComponent;
import com.administrator.shopkeepertablet.di.parish.DaggerParishFragmentComponent;
import com.administrator.shopkeepertablet.di.parish.ParishFragmentModule;
import com.administrator.shopkeepertablet.view.ui.BaseFragment;
import com.administrator.shopkeepertablet.viewmodel.parish.ParishFoodViewModel;

import javax.inject.Inject;

/**
 * Description:
 * Author chencheng
 * Time 2018/6/27
 */

public class ParishFoodFragment extends BaseFragment {
    private FragmentParishFoodBinding binding;

    @Inject
    ParishFoodViewModel viewModel;

    @Override
    protected void setupFragmentComponent(AppComponent appComponent) {
        DaggerParishFragmentComponent.builder()
                .appComponent(appComponent)
                .parishFragmentModule(new ParishFragmentModule(this))
                .build()
                .inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_parish_food, container, false);
        binding.setViewModel(viewModel);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        viewModel.getRooms();
        viewModel.getTables();
    }

    private void initView(){

    }
}
