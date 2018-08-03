package com.administrator.shopkeepertablet.view.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.databinding.FragmentLineUpBinding;
import com.administrator.shopkeepertablet.databinding.FragmentMessageBinding;
import com.administrator.shopkeepertablet.di.app.AppComponent;
import com.administrator.shopkeepertablet.di.message.DaggerMessageComponent;
import com.administrator.shopkeepertablet.di.message.MessageModule;
import com.administrator.shopkeepertablet.model.entity.OrderEntity;
import com.administrator.shopkeepertablet.utils.MToast;
import com.administrator.shopkeepertablet.view.ui.BaseFragment;
import com.administrator.shopkeepertablet.view.ui.adapter.MessageAdapter;
import com.administrator.shopkeepertablet.view.ui.adapter.OrderDetailAdapter;
import com.administrator.shopkeepertablet.view.ui.adapter.OrderListAdapter;
import com.administrator.shopkeepertablet.view.ui.adapter.base.AdapterOnItemClick;
import com.administrator.shopkeepertablet.view.widget.RecyclerViewItemDecoration;
import com.administrator.shopkeepertablet.viewmodel.MessageViewModel;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Description:
 * Author chencheng
 * Time 2018/7/25
 */

public class MessageFragment extends BaseFragment {
    FragmentMessageBinding binding;
    @Inject
    MessageViewModel viewModel;

    private MessageAdapter adapter;
    private OrderDetailAdapter detailAdapter;
    private List<OrderEntity> mList = new ArrayList<>();
    private String type = "all";

    @Override
    protected void setupFragmentComponent(AppComponent appComponent) {
        DaggerMessageComponent.builder()
                .appComponent(appComponent)
                .messageModule(new MessageModule(this))
                .build()
                .inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_message, container, false);
       binding.setViewModel(viewModel);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        EventBus.getDefault().register(this);
        initView();
        viewModel.getMessage(type);
    }

    private void initView() {
        binding.llDetail.setVisibility(View.INVISIBLE);
        adapter = new MessageAdapter(getActivity(), mList);
        binding.rlvMessage.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rlvMessage.setAdapter(adapter);
        adapter.setOnItemClick(new MessageAdapter.OnItemClick() {
            @Override
            public void onItemClick(OrderEntity entity, int position) {
                viewModel.orderEntity.set(entity);
                viewModel.getOrderDetail(entity.getBillId());
                binding.llDetail.setVisibility(View.VISIBLE);
            }
        });
        detailAdapter = new OrderDetailAdapter(getActivity(), viewModel.detailFoods.get());
        binding.rlvOrderFood.setAdapter(detailAdapter);
        binding.rlvOrderFood.setLayoutManager(new LinearLayoutManager(getActivity()));

        binding.rlvMessage.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                viewModel.getMessage(type);
            }

            @Override
            public void onLoadMore() {
                viewModel.getMoreMessage(type);
            }
        });
    }

    public void refresh(List<OrderEntity> orderEntities) {
        binding.llDetail.setVisibility(View.INVISIBLE);
        mList.clear();
        mList.addAll(orderEntities);
        adapter.notifyDataSetChanged();
        binding.rlvMessage.refreshComplete();
        if (orderEntities.isEmpty()) {
            MToast.showToast(getActivity(), "无消息数据");
        } else if (orderEntities.size() < viewModel.size) {
            binding.rlvMessage.setLoadingMoreEnabled(false);
        }
    }

    public void loadMore(List<OrderEntity> orderEntities) {
        mList.addAll(orderEntities);
        adapter.notifyDataSetChanged();
        binding.rlvMessage.loadMoreComplete();
        if (orderEntities.size() < viewModel.size) {
            binding.rlvMessage.setLoadingMoreEnabled(false);
        }
    }

    public void notifyFood(){
        detailAdapter.notifyDataSetChanged();
    }

}
