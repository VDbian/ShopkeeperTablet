package com.administrator.shopkeepertablet.view.ui.activity.parish;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.administrator.shopkeepertablet.AppConstant;
import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.databinding.ActivityTableBinding;
import com.administrator.shopkeepertablet.di.app.AppComponent;
import com.administrator.shopkeepertablet.di.parish.DaggerParishActivityComponent;
import com.administrator.shopkeepertablet.di.parish.ParishActivityModule;
import com.administrator.shopkeepertablet.model.entity.OrderEntity;
import com.administrator.shopkeepertablet.model.entity.OrderFoodEntity;
import com.administrator.shopkeepertablet.model.entity.RoomEntity;
import com.administrator.shopkeepertablet.model.entity.TableEntity;
import com.administrator.shopkeepertablet.model.entity.bean.EventPayBean;
import com.administrator.shopkeepertablet.model.entity.bean.EventTableBean;
import com.administrator.shopkeepertablet.utils.DataEvent;
import com.administrator.shopkeepertablet.utils.MToast;
import com.administrator.shopkeepertablet.view.ui.BaseActivity;
import com.administrator.shopkeepertablet.view.ui.adapter.ParishTableAdapter;
import com.administrator.shopkeepertablet.view.ui.adapter.TableRoomAdapter;
import com.administrator.shopkeepertablet.view.widget.ConfirmDialog;
import com.administrator.shopkeepertablet.view.widget.RecyclerViewItemDecoration;
import com.administrator.shopkeepertablet.viewmodel.TableViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Description:
 * Author CC
 * Time 2018/7/15
 */


public class TableActivity extends BaseActivity {

    ActivityTableBinding binding;
    @Inject
    TableViewModel viewModel;

    private TableRoomAdapter tableRoomAdapter;
    private List<RoomEntity> roomEntityList = new ArrayList<>();
    private ParishTableAdapter parishTableAdapter;
    private List<TableEntity> tableEntityList = new ArrayList<>();
    private List<TableEntity> tableSelectList = new ArrayList<>();
    private String roomName = "";
    public List<OrderFoodEntity> orderFoodEntityList = new ArrayList<>();
    private String str = "";
    private int index = 0;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerParishActivityComponent.builder()
                .parishActivityModule(new ParishActivityModule(this))
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_table);
        binding.setViewModel(viewModel);
        initView();
        viewModel.getRooms();
    }

    private void initView() {
        if (viewModel.title.get().equals("并单")) {
            binding.tvConfirm.setVisibility(View.VISIBLE);
        }
        tableRoomAdapter = new TableRoomAdapter(this, roomEntityList);
        binding.rlvRoom.setAdapter(tableRoomAdapter);
        binding.rlvRoom.setLayoutManager(new LinearLayoutManager(this));
        binding.rlvRoom.addItemDecoration(new RecyclerViewItemDecoration(5));
        tableRoomAdapter.setOnItemClick(new TableRoomAdapter.OnItemClick() {
            @Override
            public void onItemClick(RoomEntity entity, int position) {
                roomName = entity.getName();
                viewModel.getTables(entity);
            }
        });

        parishTableAdapter = new ParishTableAdapter(this, tableEntityList);
        binding.rlvTable.setAdapter(parishTableAdapter);
        binding.rlvTable.setLayoutManager(new GridLayoutManager(this, 8));
        binding.rlvTable.addItemDecoration(new RecyclerViewItemDecoration(5));
        parishTableAdapter.setOnItemClick(new ParishTableAdapter.OnItemClick() {
            @Override
            public void onItemClick(TableEntity entity, int position) {
                if (viewModel.title.get().equals("换桌")) {
                    showDialog(entity, 0);
                } else if (viewModel.title.get().equals("并单")) {
                    tableEntityList.get(position).setSelect(!tableEntityList.get(position).isSelect());
                    if (tableEntityList.get(position).isSelect()) {
                        tableSelectList.add(tableEntityList.get(position));
                    } else {
                        tableSelectList.remove(tableEntityList.get(position));
                    }
                    parishTableAdapter.notifyDataSetChanged();

                } else {
                    showDialog(entity, 1);
                }
            }
        });

        binding.rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tableSelectList.size() > 0) {
                    confirm();
                } else {
                    MToast.showToast(TableActivity.this, "请先选择需要并单处理的桌位");
                }
            }
        });

    }

    private void confirm() {
//        viewModel.getOrderFoodList(viewModel.tableEntity.get());
        str = viewModel.tableEntity.get().getTableName();
        String tableId = viewModel.tableEntity.get().getRoomTableId();
        for (TableEntity entity:tableSelectList){
            str += "、" + entity.getTableName();
            tableId += "," + entity.getRoomTableId();
        }
        viewModel.getMerge(tableId);
        binding.tvConfirm.setEnabled(false);
    }

    public void showDialogMerge(OrderEntity orderEntity ,List<OrderFoodEntity> mList ) {
            ConfirmDialog confirmDialog = new ConfirmDialog();
            confirmDialog.setMessage("是否将" + str + "并单处理");
            confirmDialog.setOnDialogSure(new ConfirmDialog.OnDialogSure() {
                @Override
                public void confirm() {
                    EventPayBean bean = new EventPayBean();
                    bean.setFlag(2);
                    bean.setTableEntity(viewModel.tableEntity.get());
                    bean.setmList(mList);
                    bean.setOrder(orderEntity);
                    bean.setRoomName(viewModel.roomName.get());
                    bean.setTableEntityList(tableSelectList);
                    EventBus.getDefault().postSticky(DataEvent.make(AppConstant.EVENT_PAY, bean));
                    Intent intent = new Intent(TableActivity.this, PayActivity.class);
                    startActivity(intent);
                }

                @Override
                public void cancel() {

                }
            });
            confirmDialog.show(getFragmentManager(), "");
    }

    private void showDialog(final TableEntity entity, final int flag) {
        final ConfirmDialog confirmDialog = new ConfirmDialog();
        switch (flag) {
            case 0:
                confirmDialog.setMessage("是否换桌到" + roomName + entity.getTableName());
                break;
            case 1:
                confirmDialog.setMessage("是否转菜到" + roomName + entity.getTableName());
                break;
        }
        confirmDialog.setOnDialogSure(new ConfirmDialog.OnDialogSure() {
            @Override
            public void confirm() {
                switch (flag) {
                    case 0:
                        viewModel.changeTable(entity);
                        break;
                    case 1:
                        viewModel.transferFood(entity.getRoomTableId());
                        break;
                }
            }

            @Override
            public void cancel() {

            }
        });
        confirmDialog.show(getFragmentManager(), "");
    }

    public void refreshRoom(List<RoomEntity> roomEntities) {
        roomEntityList.clear();
        roomEntityList.addAll(roomEntities);
        if (roomEntityList.size() > 0) {
            roomEntityList.get(0).setSelect(true);
            roomName = roomEntityList.get(0).getName();
            viewModel.getTables(roomEntityList.get(0));
        }
        tableRoomAdapter.notifyDataSetChanged();
    }

    public void refreshTable(List<TableEntity> tableEntities) {
        tableEntityList.clear();
        for (TableEntity table : tableEntities) {
            if (viewModel.title.get().equals("换桌") && table.getIsOpen().equals("0")) {
                tableEntityList.add(table);
            } else if (table.getIsOpen().equals("2") && !viewModel.tableEntity.get().getRoomTableId().equals(table.getRoomTableId()) && viewModel.title.get().contains("转菜")) {
                tableEntityList.add(table);
            } else if (table.getIsOpen().equals("2") && !viewModel.tableEntity.get().getRoomTableId().equals(table.getRoomTableId()) && viewModel.title.get().equals("并单")) {
                for (TableEntity tableEntity : tableSelectList) {
                    if (table.getRoomTableId().equals(tableEntity.getRoomTableId())) {
                        table.setSelect(true);
                    }
                }
                tableEntityList.add(table);
            }
        }
        if (tableEntityList.size() <= 0 && viewModel.title.get().equals("换桌")) {
            MToast.showToast(this, "该房间暂无空闲的桌位");
        } else if (tableEntityList.size() <= 0) {
            MToast.showToast(this, "该房间暂无已下单的桌位");
        }
        parishTableAdapter.notifyDataSetChanged();
    }


    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onDataEvent(DataEvent event) {
        if (event.getMessageTag() == AppConstant.EVENT_TABLE) {
            EventTableBean bean = (EventTableBean) event.getMessageData();
            viewModel.title.set(bean.getTitle());
            viewModel.tableEntity.set(bean.getTableEntity());
            viewModel.roomName.set(bean.getRoomName());
            viewModel.detailId.set(bean.getDetailId());
            viewModel.time.set(viewModel.getTime(bean.getTableEntity().getKaiTime()));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
