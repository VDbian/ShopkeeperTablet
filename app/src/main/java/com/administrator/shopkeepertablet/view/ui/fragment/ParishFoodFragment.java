package com.administrator.shopkeepertablet.view.ui.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.administrator.shopkeepertablet.AppConstant;
import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.databinding.FragmentParishFoodBinding;
import com.administrator.shopkeepertablet.databinding.PopupwindowOrderDiasherBinding;
import com.administrator.shopkeepertablet.databinding.PopupwindowOrderPayBinding;
import com.administrator.shopkeepertablet.di.app.AppComponent;
import com.administrator.shopkeepertablet.di.parish.DaggerParishFragmentComponent;
import com.administrator.shopkeepertablet.di.parish.ParishFragmentModule;
import com.administrator.shopkeepertablet.model.entity.EventOrderDishesEntity;
import com.administrator.shopkeepertablet.model.entity.OrderFoodEntity;
import com.administrator.shopkeepertablet.model.entity.RoomEntity;
import com.administrator.shopkeepertablet.model.entity.TableEntity;
import com.administrator.shopkeepertablet.utils.DataEvent;
import com.administrator.shopkeepertablet.utils.DateUtils;
import com.administrator.shopkeepertablet.utils.MLog;
import com.administrator.shopkeepertablet.view.ui.BaseFragment;
import com.administrator.shopkeepertablet.view.ui.activity.parish.OrderDishesActivity;
import com.administrator.shopkeepertablet.view.ui.adapter.ParishTableAdapter;
import com.administrator.shopkeepertablet.view.widget.PopupWindowBeginTable;
import com.administrator.shopkeepertablet.view.widget.PopupWindowOrderAndClear;
import com.administrator.shopkeepertablet.view.widget.PopupWindowPay;
import com.administrator.shopkeepertablet.view.widget.RecyclerViewItemDecoration;
import com.administrator.shopkeepertablet.viewmodel.parish.ParishFoodViewModel;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    private ParishTableAdapter adapter;
    private List<TableEntity> mList = new ArrayList<>();
    private List<RoomEntity> roomEntities = new ArrayList<>();
    private PopupWindowBeginTable popBeginTable;
    private PopupWindowOrderAndClear popOrderAndClear;
    private PopupWindowPay popupWindowPay;
    private boolean first = true;


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
//        EventBus.getDefault().register(this);
        initView();
        viewModel.getRooms();
    }

    private void initView() {
        adapter = new ParishTableAdapter(getActivity(), mList);
        binding.rlvTable.setAdapter(adapter);
        binding.rlvTable.setLayoutManager(new GridLayoutManager(getActivity(), 8));
        binding.rlvTable.addItemDecoration(new RecyclerViewItemDecoration(5));
        adapter.setOnItemClick(new ParishTableAdapter.OnItemClick() {
            @Override
            public void onItemClick(final TableEntity entity, final int position) {
                viewModel.table.set(entity.getTableName());
                viewModel.tableId.set(entity.getRoomTableId());
                switch (entity.getIsOpen()) {
                    case "0":
                        viewModel.people.set("1");
                        viewModel.tableware.set("1");
                        popBeginTable = new PopupWindowBeginTable(getActivity(), viewModel);
                        popBeginTable.showPopupWindowUp(binding.tabRoom);
                        popBeginTable.setOnCallBackListener(new PopupWindowBeginTable.OnCallBackListener() {
                            @Override
                            public void open() {
                                viewModel.openTable(false);
                            }

                            @Override
                            public void order() {
                                viewModel.openTable(true);
                            }
                        });
                        break;
                    case "1":
                        viewModel.people.set(entity.getPersonCounts());
                        viewModel.time.set(entity.getTime());
                        popOrderAndClear = new PopupWindowOrderAndClear(getActivity(), viewModel);
                        popOrderAndClear.showPopupWindow(binding.tabRoom);
                        popOrderAndClear.setOnCallBackListener(new PopupWindowOrderAndClear.OnCallBackListener() {
                            @Override
                            public void clear() {
                                viewModel.clearTable(entity.getBillId());
                            }

                            @Override
                            public void order() {
                                EventOrderDishesEntity eventOrderDishesEntity = new EventOrderDishesEntity();
                                eventOrderDishesEntity.setPeopleNum(viewModel.people.get());
                                eventOrderDishesEntity.setRoomName(viewModel.room.get());
                                eventOrderDishesEntity.setTableName(viewModel.table.get());
                                eventOrderDishesEntity.setBillId(entity.getBillId());
                                eventOrderDishesEntity.setTableId(viewModel.tableId.get());
                                eventOrderDishesEntity.setTime(entity.getKaiTime());
                                EventBus.getDefault().postSticky(DataEvent.make().setMessageTag(AppConstant.EVENT_ORDER_DISHES).setMessageData(eventOrderDishesEntity));
                                if (popOrderAndClear != null) {
                                    popOrderAndClear.dismiss();
                                }
                                Intent intent = new Intent(getActivity(), OrderDishesActivity.class);
                                startActivity(intent);
                            }
                        });
                        break;
                    case "2":
                        viewModel.people.set(entity.getPersonCounts());
                        viewModel.time.set(entity.getTime());
                        viewModel.billId.set(entity.getBillId());
                        viewModel.getOrderFoodList();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    public void initTabView(final List<RoomEntity> list) {
        roomEntities = list;
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                TabLayout.Tab newTab = binding.tabRoom.newTab();
                newTab.setText(list.get(i).getName());
                binding.tabRoom.addTab(newTab);
            }
            viewModel.getTables(list.get(0));
            viewModel.room.set(list.get(0).getName());
        }
        binding.tabRoom.setTabMode(TabLayout.MODE_SCROLLABLE);
        binding.tabRoom.setTabTextColors(getActivity().getResources().getColor(R.color.color848d95), getActivity().getResources().getColor(R.color.color000000));
        binding.tabRoom.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                MLog.e("VD",tab.getPosition()+"sdd");
                RoomEntity roomEntity = list.get(tab.getPosition());
                viewModel.getTables(roomEntity);
                viewModel.room.set(roomEntity.getName());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void refreshVariety(List<TableEntity> tableEntities) {
        mList.clear();
        mList.addAll(tableEntities);
        adapter.notifyDataSetChanged();
    }

    public void openSuccess(boolean flag) {
        if (popBeginTable != null) {
            popBeginTable.dismiss();
        }
        if (flag) {
            EventOrderDishesEntity eventOrderDishesEntity = new EventOrderDishesEntity();
            eventOrderDishesEntity.setTableId(viewModel.tableId.get());
            eventOrderDishesEntity.setPeopleNum(viewModel.people.get());
            eventOrderDishesEntity.setRoomName(viewModel.room.get());
            eventOrderDishesEntity.setTableName(viewModel.table.get());
            eventOrderDishesEntity.setBillId(viewModel.billId.get());
            eventOrderDishesEntity.setTime(DateUtils.getCurrentDate());
            EventBus.getDefault().postSticky(DataEvent.make().setMessageTag(AppConstant.EVENT_ORDER_DISHES).setMessageData(eventOrderDishesEntity));
            Intent intent = new Intent(getActivity(), OrderDishesActivity.class);
            startActivity(intent);
        } else {
            int selectedTabPosition = binding.tabRoom.getSelectedTabPosition();
            viewModel.getTables(roomEntities.get(selectedTabPosition));
        }
    }

    public void clearSuccess() {
        if (popOrderAndClear != null) {
            popOrderAndClear.dismiss();
        }
        int selectedTabPosition = binding.tabRoom.getSelectedTabPosition();
        viewModel.getTables(roomEntities.get(selectedTabPosition));
    }

    public void initPayPop(List<OrderFoodEntity> mList) {
        popupWindowPay = new PopupWindowPay(getActivity(), viewModel, mList);
        popupWindowPay.showPopupWindow(binding.tabRoom);
        popupWindowPay.setOnCallBackListener(new PopupWindowPay.OnCallBackListener() {
            @Override
            public void pay() {

            }

            @Override
            public void scanPay() {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!first) {
            int selectedTabPosition = binding.tabRoom.getSelectedTabPosition();
            viewModel.getTables(roomEntities.get(selectedTabPosition));
        } else {
            first = false;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);
    }
}
