package com.administrator.shopkeepertablet.view.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.databinding.FragmentLineUpBinding;
import com.administrator.shopkeepertablet.di.app.AppComponent;
import com.administrator.shopkeepertablet.di.line.DaggerLineComponent;
import com.administrator.shopkeepertablet.di.line.LineModule;
import com.administrator.shopkeepertablet.view.ui.BaseFragment;
import com.administrator.shopkeepertablet.viewmodel.LineUpViewModel;

import javax.inject.Inject;

/**
 * Description:
 * Author chencheng
 * Time 2018/7/24
 */

public class LineUpFragment extends BaseFragment {

    FragmentLineUpBinding binding;
    @Inject
    LineUpViewModel viewModel;

//    private SpeechSynthesizer speechSynthesizer;

    @Override
    protected void setupFragmentComponent(AppComponent appComponent) {
        DaggerLineComponent.builder()
                .appComponent(appComponent)
                .lineModule(new LineModule(this))
                .build()
                .inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_line_up, container, false);
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
