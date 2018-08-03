package com.administrator.shopkeepertablet.view.ui.fragment;

import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.administrator.shopkeepertablet.AppConstant;
import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.databinding.FragmentOrderBinding;
import com.administrator.shopkeepertablet.di.app.AppComponent;
import com.administrator.shopkeepertablet.di.order.DaggerOrderComponent;
import com.administrator.shopkeepertablet.di.order.OrderModule;
import com.administrator.shopkeepertablet.model.entity.OrderEntity;
import com.administrator.shopkeepertablet.utils.DataEvent;
import com.administrator.shopkeepertablet.view.ui.BaseFragment;
import com.administrator.shopkeepertablet.view.ui.adapter.OrderListAdapter;
import com.administrator.shopkeepertablet.view.ui.adapter.base.AdapterOnItemClick;
import com.administrator.shopkeepertablet.view.widget.PopupWindowOrderDetail;
import com.administrator.shopkeepertablet.view.widget.RecyclerViewItemDecoration;
import com.administrator.shopkeepertablet.viewmodel.OrderViewModel;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Description:
 * Author chencheng
 * Time 2018/7/24
 */

public class OrderFragment extends BaseFragment implements View.OnClickListener {

    FragmentOrderBinding binding;
    @Inject
    OrderViewModel viewModel;

    private OrderListAdapter adapter;
    private List<OrderEntity> mList = new ArrayList<>();

    private String type = "all";
    private String state = "all";

    @Override
    protected void setupFragmentComponent(AppComponent appComponent) {
        DaggerOrderComponent.builder()
                .appComponent(appComponent)
                .orderModule(new OrderModule(this))
                .build()
                .inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_order, container, false);
        binding.setViewModel(viewModel);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        EventBus.getDefault().register(this);
        initView();
        viewModel.getOrderList(type, state);
    }

    private void initView() {

        adapter = new OrderListAdapter(getActivity(), mList);
        binding.rlvOrder.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rlvOrder.addItemDecoration(new RecyclerViewItemDecoration(15));
        binding.rlvOrder.setAdapter(adapter);
        adapter.setOnItemClick(new AdapterOnItemClick<OrderEntity>() {
            @Override
            public void onItemClick(OrderEntity orderEntity, int position) {
                viewModel.orderEntity.set(orderEntity);
                viewModel.getOrderDetail(orderEntity.getBillId());
            }
        });

        binding.rlvOrder.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                viewModel.getOrderList(type, state);
            }

            @Override
            public void onLoadMore() {
                viewModel.getOrderMoreList(type, state);
            }
        });

        binding.tvType.setOnClickListener(this);
        binding.tvState.setOnClickListener(this);

    }

    public void refresh(List<OrderEntity> orderEntities) {
        mList.clear();
        mList.addAll(orderEntities);
        adapter.notifyDataSetChanged();
        binding.rlvOrder.refreshComplete();
        if (mList.size() < viewModel.index * viewModel.size) {
            binding.rlvOrder.setLoadingMoreEnabled(false);
        }
    }

    public void loadMore(List<OrderEntity> orderEntities) {
        mList.addAll(orderEntities);
        adapter.notifyDataSetChanged();
        binding.rlvOrder.loadMoreComplete();
        if (mList.size() < viewModel.index * viewModel.size) {
            binding.rlvOrder.setLoadingMoreEnabled(false);
        }
    }

    public void showPop() {
        PopupWindowOrderDetail orderDetail = new PopupWindowOrderDetail(getActivity(), viewModel);
        orderDetail.showPopupWindowRight(binding.getRoot());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_type:
                AlertDialog.Builder builderType = new AlertDialog.Builder(OrderFragment.this.getActivity());
                builderType.setSingleChoiceItems(R.array.orderType, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which==0){
                            type ="all";
                        }else {
                            type = String.valueOf(which);
                        }
                        viewModel.getOrderList(type, state);
                        dialog.dismiss();
                    }
                });
                builderType.show();
                break;
            case R.id.tv_state:
                AlertDialog.Builder builderState = new AlertDialog.Builder(OrderFragment.this.getActivity());
                builderState.setSingleChoiceItems(R.array.orderStatus, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       switch (which){
                           case 0:
                               state ="all";
                               break;
                           case 1:
                               state ="2";
                               break;
                           case 2:
                               state ="3";
                               break;
                       }
                        viewModel.getOrderList(type, state);
                        dialog.dismiss();
                    }
                });
                builderState.show();
                break;
        }
    }


}
