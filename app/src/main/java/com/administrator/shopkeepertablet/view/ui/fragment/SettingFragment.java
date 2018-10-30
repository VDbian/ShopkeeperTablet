package com.administrator.shopkeepertablet.view.ui.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.administrator.shopkeepertablet.AppApplication;
import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.databinding.FragmentLineUpBinding;
import com.administrator.shopkeepertablet.databinding.FragmentSettingBinding;
import com.administrator.shopkeepertablet.di.app.AppComponent;
import com.administrator.shopkeepertablet.di.setting.DaggerSettingFragmentComponent;
import com.administrator.shopkeepertablet.di.setting.SettingFragmentModule;
import com.administrator.shopkeepertablet.utils.MToast;
import com.administrator.shopkeepertablet.utils.SocketClientService;
import com.administrator.shopkeepertablet.view.ui.BaseFragment;
import com.administrator.shopkeepertablet.view.widget.ConfirmDialog;
import com.administrator.shopkeepertablet.viewmodel.SettingFragmentViewModel;

import javax.inject.Inject;

/**
 * Description:
 * Author chencheng
 * Time 2018/7/25
 */

public class SettingFragment extends BaseFragment implements View.OnClickListener {
    FragmentSettingBinding binding;

    @Inject
    SettingFragmentViewModel viewModel;
    private Intent intent;

    @Override
    protected void setupFragmentComponent(AppComponent appComponent) {
        DaggerSettingFragmentComponent.builder()
                .appComponent(appComponent)
                .settingFragmentModule(new SettingFragmentModule(this))
                .build()
                .inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_setting, container, false);
        binding.setViewModel(viewModel);
        intent = new Intent(getActivity(),SocketClientService.class);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        EventBus.getDefault().register(this);
        initView();
    }

    private void initView() {
        binding.tvFood.setOnClickListener(this);
        binding.tvRoom.setOnClickListener(this);
        binding.tvSetting.setOnClickListener(this);
        binding.tvCancel.setOnClickListener(this);
        binding.tvLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_room:
                MToast.showToast(getActivity(), "房间刷新成功");
                break;
            case R.id.tv_food:
                viewModel.getFoodList();
                break;
            case R.id.tv_setting:
                ConfirmDialog confirmDialogSetting = new ConfirmDialog();
                confirmDialogSetting.setTitle("设置主机");
                confirmDialogSetting.setMessage("是否设置本机为主机");
                confirmDialogSetting.setOnDialogSure(new ConfirmDialog.OnDialogSure() {
                    @Override
                    public void confirm() {
//                        viewModel.setAlias();
                        viewModel.getSocket();
                    }

                    @Override
                    public void cancel() {

                    }
                });
                confirmDialogSetting.show(getActivity().getFragmentManager(), "set");
                break;
            case R.id.tv_cancel:
                ConfirmDialog confirmDialogCancel = new ConfirmDialog();
                confirmDialogCancel.setTitle("取消主机");
                confirmDialogCancel.setMessage("是否取消设置本机为主机");
                confirmDialogCancel.setOnDialogSure(new ConfirmDialog.OnDialogSure() {
                    @Override
                    public void confirm() {
                        stopSocket();
                    }

                    @Override
                    public void cancel() {

                    }
                });
                confirmDialogCancel.show(getActivity().getFragmentManager(), "set");
                break;
            case R.id.tv_logout:
                viewModel.logout();
                break;
        }
    }


    public void connectSocket(String id, String ip, int port) {
        intent.putExtra("id", id);
        intent.putExtra("ip", ip);
        intent.putExtra("duankou", port);
        getActivity().startService(intent);
    }

    public void stopSocket() {
        if (intent != null) {
            getActivity().stopService(intent);
            viewModel.removeAlias();
        } else {
            MToast.showToast(getActivity(), "请先确认已设置主机");
        }
    }
}
