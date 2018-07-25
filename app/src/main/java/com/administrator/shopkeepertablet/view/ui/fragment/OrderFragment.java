package com.administrator.shopkeepertablet.view.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

public class OrderFragment extends BaseFragment {

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
    }

    private void initView() {
        viewModel.getOrderList(type, state);

        adapter = new OrderListAdapter(getActivity(), mList);
        binding.rlvOrder.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rlvOrder.addItemDecoration(new RecyclerViewItemDecoration(15));
        binding.rlvOrder.setAdapter(adapter);
        adapter.setOnItemClick(new AdapterOnItemClick<OrderEntity>() {
            @Override
            public void onItemClick(OrderEntity orderEntity, int position) {
                viewModel.getOrderDetail(orderEntity.getBillid());
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

}
