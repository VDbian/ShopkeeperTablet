package com.administrator.shopkeepertablet.view.ui.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.administrator.shopkeepertablet.AppConstant;
import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.databinding.FragmentParishFoodBinding;
import com.administrator.shopkeepertablet.di.app.AppComponent;
import com.administrator.shopkeepertablet.di.parish.DaggerParishFragmentComponent;
import com.administrator.shopkeepertablet.di.parish.ParishFragmentModule;
import com.administrator.shopkeepertablet.model.entity.EventOrderDishesEntity;
import com.administrator.shopkeepertablet.model.entity.OrderEntity;
import com.administrator.shopkeepertablet.model.entity.OrderFoodEntity;
import com.administrator.shopkeepertablet.model.entity.ReturnReasonEntity;
import com.administrator.shopkeepertablet.model.entity.RoomEntity;
import com.administrator.shopkeepertablet.model.entity.TableEntity;
import com.administrator.shopkeepertablet.model.entity.bean.BillJson;
import com.administrator.shopkeepertablet.model.entity.bean.EventPayBean;
import com.administrator.shopkeepertablet.model.entity.bean.EventTableBean;
import com.administrator.shopkeepertablet.utils.DataEvent;
import com.administrator.shopkeepertablet.utils.DateUtils;
import com.administrator.shopkeepertablet.utils.MLog;
import com.administrator.shopkeepertablet.utils.MToast;
import com.administrator.shopkeepertablet.view.ui.BaseFragment;
import com.administrator.shopkeepertablet.view.ui.activity.parish.OrderDishesActivity;
import com.administrator.shopkeepertablet.view.ui.activity.parish.PayActivity;
import com.administrator.shopkeepertablet.view.ui.activity.parish.TableActivity;
import com.administrator.shopkeepertablet.view.ui.adapter.ParishTableAdapter;
import com.administrator.shopkeepertablet.view.widget.ChangePeopleDialog;
import com.administrator.shopkeepertablet.view.widget.ConfirmDialog;
import com.administrator.shopkeepertablet.view.widget.PopupWindowBeginTable;
import com.administrator.shopkeepertablet.view.widget.PopupWindowOrderAndClear;
import com.administrator.shopkeepertablet.view.widget.PopupWindowPay;
import com.administrator.shopkeepertablet.view.widget.PopupWindowReturnFood;
import com.administrator.shopkeepertablet.view.widget.RecyclerViewItemDecoration;
import com.administrator.shopkeepertablet.viewmodel.ParishFoodViewModel;
import com.google.gson.Gson;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
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
    private List<OrderFoodEntity> orderFoodEntityList = new ArrayList<>();


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
                MLog.e("vd",entity.getIsOpen());
                switch (entity.getIsOpen()) {
                    case "0":
                        viewModel.people.set("1");
                        viewModel.tableware.set("1");
                        popBeginTable = new PopupWindowBeginTable(getActivity(), viewModel);
                        popBeginTable.showPopupWindow(binding.tabRoom);
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
                                eventOrderDishesEntity.setOrderFoodEntityList(orderFoodEntityList);
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
                        viewModel.tableware.set(entity.getTableWareCount());
                        viewModel.time.set(entity.getTime());
                        viewModel.billId.set(entity.getBillId());
                        viewModel.totalPrice.set(entity.getPrice());
//                        viewModel.getOrderFoodList(entity);
                        viewModel.getOrder(entity);
                        break;
                    case "4":
                        viewModel.billId.set(entity.getBillId());
                        ConfirmDialog confirmDialog =new ConfirmDialog();
                        confirmDialog.setMessage("是否取消结账");
                        confirmDialog.setOnDialogSure(new ConfirmDialog.OnDialogSure() {
                            @Override
                            public void confirm() {
                                viewModel.cancelPay();
                            }

                            @Override
                            public void cancel() {

                            }
                        });
                        confirmDialog.show(getActivity().getFragmentManager(),"");
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
            eventOrderDishesEntity.setOrderFoodEntityList(orderFoodEntityList);
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

    public void initPayPop(final List<OrderFoodEntity> mList, OrderEntity orderEntity,final TableEntity entity) {
        popupWindowPay = new PopupWindowPay(getActivity(), viewModel, mList);
        popupWindowPay.showPopupWindow(binding.tabRoom);
        popupWindowPay.setOnCallBackListener(new PopupWindowPay.OnCallBackListener() {
            @Override
            public void pay() {
               viewModel.inBill(0,mList,orderEntity,entity);
            }

            @Override
            public void scanPay() {
                viewModel.inBill(1,mList,orderEntity,entity);

            }

            @Override
            public void more(int position) {
                switch (position) {
                    case 0:
                        EventOrderDishesEntity eventOrderDishesEntity = new EventOrderDishesEntity();
                        eventOrderDishesEntity.setTableId(viewModel.tableId.get());
                        eventOrderDishesEntity.setPeopleNum(viewModel.people.get());
                        eventOrderDishesEntity.setRoomName(viewModel.room.get());
                        eventOrderDishesEntity.setTableName(viewModel.table.get());
                        eventOrderDishesEntity.setBillId(viewModel.billId.get());
                        eventOrderDishesEntity.setTime(viewModel.time.get());
                        eventOrderDishesEntity.setOrderFoodEntityList(mList);
                        EventBus.getDefault().postSticky(DataEvent.make().setMessageTag(AppConstant.EVENT_ORDER_DISHES).setMessageData(eventOrderDishesEntity));
                        Intent intent = new Intent(getActivity(), OrderDishesActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        popupWindowPay.dismiss();
                        eventTable(entity,"换桌",viewModel.room.get(),"");
                        break;
                    case 2:
                        popupWindowPay.dismiss();
                        ConfirmDialog confirmDialog = new ConfirmDialog();
                        confirmDialog.setTitle("撤单");
                        confirmDialog.setMessage("是否要进行撤单操作");
                        confirmDialog.setOnDialogSure(new ConfirmDialog.OnDialogSure() {
                            @Override
                            public void confirm() {
                                viewModel.cancelOrder();
                            }

                            @Override
                            public void cancel() {

                            }
                        });
                        confirmDialog.show(getActivity().getFragmentManager(), "");
                        break;
                    case 3:
                        popupWindowPay.dismiss();
                        eventTable(entity,"并单",viewModel.room.get(),"");
                        break;
                    case 4:
                        popupWindowPay.dismiss();
                        viewModel.print();
                        break;
                    case 5:
                        popupWindowPay.dismiss();
                        viewModel.pushFoodAll();
                        break;
                    case 6:
                        popupWindowPay.dismiss();
                        final ChangePeopleDialog changePeopleDialog = new ChangePeopleDialog();
                        changePeopleDialog.setPeopleNum(viewModel.people.get());
                        changePeopleDialog.setWareNum(viewModel.tableware.get());
                        changePeopleDialog.setOnConfirmClick(new ChangePeopleDialog.OnConfirmClick() {
                            @Override
                            public void confirm(String peopleNum, String wareNum) {
                                viewModel.changePeople(peopleNum,wareNum);
                            }
                        });
                        changePeopleDialog.show(getActivity().getFragmentManager(), "");
                        break;

                }
            }

            @Override
            public void item(OrderFoodEntity orderFoodEntity, int position) {
                switch (position){
                    case 0:
                        popupWindowPay.dismiss();
                        eventTable(entity,"转菜("+orderFoodEntity.getProductName()+")",viewModel.room.get(),orderFoodEntity.getDetailId());
                        break;
                    case 1:
                        viewModel.getReason(orderFoodEntity);
                        break;
                }

            }
        });
    }

    public void showReturn(OrderFoodEntity orderFoodEntity, List<ReturnReasonEntity> mList){
        PopupWindowReturnFood popupWindowReturnFood =new PopupWindowReturnFood(getActivity(),orderFoodEntity,mList,viewModel);
        popupWindowReturnFood.showPopupWindowUp();
    }

    public void eventTable(TableEntity tableEntity,String title,String room,String detailId){
        EventTableBean bean = new EventTableBean();
        bean.setTableEntity(tableEntity);
        bean.setRoomName(room);
        bean.setTitle(title);
        bean.setDetailId(detailId);
        EventBus.getDefault().postSticky(DataEvent.make().setMessageTag(AppConstant.EVENT_TABLE).setMessageData(bean));
        Intent intent = new Intent(getActivity(), TableActivity.class);
        startActivity(intent);
    }

    public void inBillSuccess(int state,List<OrderFoodEntity> mList, OrderEntity orderEntity,final TableEntity entity){
        if (popupWindowPay != null && popupWindowPay.isShowing()) {
            popupWindowPay.dismiss();
        }
        switch (state){
            case 0:
                EventPayBean bean =new EventPayBean();
                bean.setFlag(1);
                bean.setOrder(orderEntity);
                bean.setTableEntity(entity);
                bean.setmList(mList);
                bean.setRoomName(viewModel.room.get());
                bean.setPrice(viewModel.totalPrice.get());
                EventBus.getDefault().postSticky(DataEvent.make(AppConstant.EVENT_PAY,bean));
                Intent intent = new Intent(ParishFoodFragment.this.getActivity(), PayActivity.class);
                startActivity(intent);
                break;
            case 1:
                Intent intent1 = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent1, 1001);
                break;
        }
    }

    private void scanOrQuickbill(String payType, String result, double money, String memberId) {
        List<BillJson.BillJsonBase> t = new ArrayList<>();
        BillJson.BillJsonBase base2 = new BillJson.BillJsonBase();
        t.add(base2);

        BillJson.BillJsonBase base = new BillJson.BillJsonBase();
        base.setGuid(String.valueOf(System.currentTimeMillis()) + result);
        base.setPice(String.valueOf(money));
        base.setPiceGuid(payType);
        base.setSate("0");
        base.setType("1");
        base.setIsSql("1");
        t.add(base);

        BillJson.TeacherJson teacherJson = new BillJson.TeacherJson();
        List<BillJson.BillJsonBase> youHui = new ArrayList<>();
        youHui.add(base2);
        teacherJson.setTeacher(youHui);
        String tStr = new Gson().toJson(teacherJson);
        Log.i("ttt", "---tStr:" + tStr);

        BillJson.Quanxian quanxian = new BillJson.Quanxian();
        List<BillJson.BillJsonBase> q = new ArrayList<>();
        q.add(base2);
        quanxian.setQuanxian(q);
        String qStr = new Gson().toJson(quanxian);
        Log.i("ttt", "---qStr:" + qStr);
        BillJson.Pays pays = new BillJson.Pays();
        List<BillJson.BillJsonBase> p = new ArrayList<>();
        p.add(base);
        pays.setQuanxian(p);
        String pStr = new Gson().toJson(pays);
        Log.i("ttt", "---pStr:" + pStr);

        viewModel.bill(result, "", money, 0, qStr
                , tStr, pStr, payType, 1, money, "", 0, "4", memberId);
    }

    public void billSuccess(String msg) {
        MToast.showToast(getActivity(), msg);

    }

    public void bill(String payType, String result, double money, String memberId, String str) {
        if (str.contains("支付中")) {
            ConfirmDialog confirmDialog = new ConfirmDialog();
            confirmDialog.setTitle("支付中");
            confirmDialog.setMessage("用户正在支付中，是否确认已支付");
            confirmDialog.setOnDialogSure(new ConfirmDialog.OnDialogSure() {
                @Override
                public void confirm() {
                    scanOrQuickbill(payType, result, money, memberId);
                }

                @Override
                public void cancel() {

                }
            });
            confirmDialog.show(getActivity().getFragmentManager(), "");
        } else {
            scanOrQuickbill(payType, result, money, memberId);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        Bundle bundle = data.getExtras();
        if (bundle == null) {
            return;
        }
        if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
            String result = bundle.getString(CodeUtils.RESULT_STRING);
            viewModel.scanBill(result, viewModel.price.get(), viewModel.billId.get());
        } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
            MToast.showToast(getActivity(), "解析二维码失败");
        }
    }

    public void cancelOrderSuccess() {
        if (popupWindowPay != null && popupWindowPay.isShowing()) {
            popupWindowPay.dismiss();
        }
        int selectedTabPosition = binding.tabRoom.getSelectedTabPosition();
        viewModel.getTables(roomEntities.get(selectedTabPosition));
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!first) {
            int selectedTabPosition = binding.tabRoom.getSelectedTabPosition();
            if (selectedTabPosition<0){
                selectedTabPosition =0;
            }
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
