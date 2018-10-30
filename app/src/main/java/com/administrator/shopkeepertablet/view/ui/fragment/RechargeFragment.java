package com.administrator.shopkeepertablet.view.ui.fragment;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.databinding.FragmentLineUpBinding;
import com.administrator.shopkeepertablet.databinding.FragmentRechargeBinding;
import com.administrator.shopkeepertablet.databinding.PopupwindowRechargeBinding;
import com.administrator.shopkeepertablet.di.app.AppComponent;
import com.administrator.shopkeepertablet.di.recharge.DaggerRechargeComponent;
import com.administrator.shopkeepertablet.di.recharge.RechargeModule;
import com.administrator.shopkeepertablet.model.entity.RechargeEntity;
import com.administrator.shopkeepertablet.model.entity.RechargeTypeEntity;
import com.administrator.shopkeepertablet.model.entity.TableType;
import com.administrator.shopkeepertablet.utils.MToast;
import com.administrator.shopkeepertablet.view.ui.BaseFragment;
import com.administrator.shopkeepertablet.view.ui.adapter.RechargeAdapter;
import com.administrator.shopkeepertablet.view.ui.adapter.TableTypeAdapter;
import com.administrator.shopkeepertablet.view.widget.NewMemberDialog;
import com.administrator.shopkeepertablet.view.widget.PopupWindowRechargeDetail;
import com.administrator.shopkeepertablet.viewmodel.RechargeViewModel;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Description:
 * Author chencheng
 * Time 2018/7/25
 */

public class RechargeFragment extends BaseFragment implements View.OnClickListener {
    FragmentRechargeBinding binding;

    @Inject
    RechargeViewModel viewModel;

    private List<RechargeEntity> mList = new ArrayList<>();
    private RechargeAdapter adapter;
    private PopupWindowRechargeDetail pop;

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
        binding.setViewModel(viewModel);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        EventBus.getDefault().register(this);
        initView();
        viewModel.getRecharge("", "");
    }

    private void initView() {
        Drawable drawable1 = getResources().getDrawable(R.mipmap.search);
        drawable1.setBounds(25, 0, 45, 20);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
        binding.etSearch.setCompoundDrawables(drawable1, null, null, null);//只放左边

        adapter = new RechargeAdapter(getActivity(), mList);
        binding.rlvRecharge.setAdapter(adapter);
        binding.rlvRecharge.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter.setOnItemClick(new RechargeAdapter.OnClick() {
            @Override
            public void recharge(RechargeEntity entity, int position) {
                viewModel.recharge.set(entity);
                pop = new PopupWindowRechargeDetail(getActivity(), viewModel);
                pop.showPopupWindowRight(binding.getRoot());
            }
        });
        binding.rlvRecharge.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                viewModel.getRecharge("", "");
            }

            @Override
            public void onLoadMore() {
                viewModel.getMoreRecharge("", "");
            }
        });

        binding.tvSearch.setOnClickListener(this);
        binding.tvAdd.setOnClickListener(this);

    }

    public void refreshRecharge(List<RechargeEntity> rechargeEntities) {
        if (rechargeEntities.isEmpty()){
            MToast.showToast(getActivity(),"无会员信息");
        }
        mList.clear();
        mList.addAll(rechargeEntities);
        adapter.notifyDataSetChanged();
        binding.rlvRecharge.refreshComplete();
        if (mList.size() < viewModel.index * viewModel.size) {
            binding.rlvRecharge.setLoadingMoreEnabled(false);
        }
    }

    public void loadMoreRecharge(List<RechargeEntity> rechargeEntities) {
        mList.addAll(rechargeEntities);
        adapter.notifyDataSetChanged();
        binding.rlvRecharge.loadMoreComplete();
        if (mList.size() < viewModel.index * viewModel.size) {
            binding.rlvRecharge.setLoadingMoreEnabled(false);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_search:
                viewModel.getRecharge("", viewModel.search.get());
                break;
            case R.id.tv_add:
                NewMemberDialog dialog = new NewMemberDialog();
                dialog.setOnConfirmClick(new NewMemberDialog.OnConfirmClick() {
                    @Override
                    public void confirm(String name, String phone) {
                        viewModel.addRecharge(name, phone);
                    }
                });
                dialog.show(getActivity().getFragmentManager(), "");
                break;
        }
    }

    public void setSpinnerName(List<RechargeTypeEntity> mList) {
        if (pop != null && pop.isShowing()) {
            pop.setSpinnerName(mList);
        }
    }

    public void success() {
        if (pop != null && pop.isShowing()) {
            pop.dismiss();
            pop = null;
            viewModel.proCode.set("");
            viewModel.money.set("");

        }
        MToast.showToast(getActivity(),"充值成功");
    }
}
