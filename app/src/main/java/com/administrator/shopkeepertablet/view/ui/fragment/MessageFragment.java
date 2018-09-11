package com.administrator.shopkeepertablet.view.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Switch;

import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.databinding.FragmentLineUpBinding;
import com.administrator.shopkeepertablet.databinding.FragmentMessageBinding;
import com.administrator.shopkeepertablet.di.app.AppComponent;
import com.administrator.shopkeepertablet.di.message.DaggerMessageComponent;
import com.administrator.shopkeepertablet.di.message.MessageModule;
import com.administrator.shopkeepertablet.model.entity.OrderEntity;
import com.administrator.shopkeepertablet.model.entity.OrderFoodEntity;
import com.administrator.shopkeepertablet.model.entity.RoomEntity;
import com.administrator.shopkeepertablet.model.entity.TableEntity;
import com.administrator.shopkeepertablet.model.entity.TableType;
import com.administrator.shopkeepertablet.utils.MToast;
import com.administrator.shopkeepertablet.view.ui.BaseFragment;
import com.administrator.shopkeepertablet.view.ui.adapter.MessageAdapter;
import com.administrator.shopkeepertablet.view.ui.adapter.OrderDetailAdapter;
import com.administrator.shopkeepertablet.view.ui.adapter.OrderListAdapter;
import com.administrator.shopkeepertablet.view.ui.adapter.base.AdapterOnItemClick;
import com.administrator.shopkeepertablet.view.widget.BindLineUpDialog;
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

public class MessageFragment extends BaseFragment implements View.OnClickListener {
    FragmentMessageBinding binding;
    @Inject
    MessageViewModel viewModel;

    private MessageAdapter adapter;
    private OrderDetailAdapter detailAdapter;
    private List<OrderEntity> mList = new ArrayList<>();
    private List<OrderFoodEntity> orderFoodEntityList = new ArrayList<>();
    private String type = "all";
    private String[] stringType = {"订单类型", "外卖", "预定菜品"};
    private BindLineUpDialog bindLineUpDialog;

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
                if (entity.getType() == 1) {
                    binding.tvBind.setVisibility(View.VISIBLE);
                    binding.tvConfirm.setVisibility(View.GONE);
                    binding.tvCancel.setVisibility(View.GONE);
                } else if (entity.getType() == 3) {
                    binding.tvBind.setVisibility(View.GONE);
                    binding.tvConfirm.setVisibility(View.VISIBLE);
                    binding.tvCancel.setVisibility(View.VISIBLE);
                }
            }
        });
        detailAdapter = new OrderDetailAdapter(getActivity(), orderFoodEntityList);
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

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(),
                R.layout.item_spinner,
                R.id.tv_name,
                stringType);
        binding.spinnerType.setAdapter(adapter);
        binding.spinnerType.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        switch (position) {
                            case 0:
                                type = "all";
                                break;
                            case 1:
                                type = "3";
                                break;
                            case 2:
                                type = "1";
                                break;
                        }
                        viewModel.getMessage(type);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                }
        );

        binding.tvConfirm.setOnClickListener(this);
        binding.tvCancel.setOnClickListener(this);
        binding.tvBind.setOnClickListener(this);
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
        }else {
            binding.rlvMessage.setLoadingMoreEnabled(true);
        }
    }

    public void loadMore(List<OrderEntity> orderEntities) {
        mList.addAll(orderEntities);
        adapter.notifyDataSetChanged();
        binding.rlvMessage.loadMoreComplete();
        if (orderEntities.size() < viewModel.size) {
            binding.rlvMessage.setLoadingMoreEnabled(false);
        }else {
            binding.rlvMessage.setLoadingMoreEnabled(true);
        }
    }

    public void notifyFood(List<OrderFoodEntity> orderFoodEntities) {
        orderFoodEntityList.clear();
        orderFoodEntityList.addAll(orderFoodEntities);
        detailAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_confirm:
                viewModel.confirm();
                break;
            case R.id.tv_bind:
                viewModel.getRooms();
                break;
            case R.id.tv_cancel:
                viewModel.cancel();
                break;
        }
    }

    public void showBindDialog() {
        if (bindLineUpDialog != null) {
            bindLineUpDialog.refreshTable(viewModel.tableEntityList);
        } else {
            bindLineUpDialog = new BindLineUpDialog();
            bindLineUpDialog.setTableList(viewModel.tableEntityList);
            bindLineUpDialog.setRoomList(viewModel.roomEntityList);
            bindLineUpDialog.setOnConfirmClick(new BindLineUpDialog.OnConfirmClick() {
                @Override
                public void confirm(RoomEntity roomEntity, TableEntity tableEntity) {
                    viewModel.bind(tableEntity);
                }

                @Override
                public void selectRoom(RoomEntity roomEntity) {
                    viewModel.getTables(roomEntity);
                }

                @Override
                public void cancel() {
                    bindLineUpDialog = null;
                }
            });
            bindLineUpDialog.show(getActivity().getFragmentManager(), "");
        }
    }

    public void success(){
        viewModel.getMessage(type);
        binding.llDetail.setVisibility(View.INVISIBLE);
    }
}
