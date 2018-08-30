package com.administrator.shopkeepertablet.viewmodel;

import android.databinding.ObservableField;
import android.mtp.MtpConstants;
import android.util.Log;

import com.administrator.shopkeepertablet.model.entity.BaseEntity;
import com.administrator.shopkeepertablet.model.entity.OrderEntity;
import com.administrator.shopkeepertablet.model.entity.OrderFoodEntity;
import com.administrator.shopkeepertablet.model.entity.RoomEntity;
import com.administrator.shopkeepertablet.model.entity.TableEntity;
import com.administrator.shopkeepertablet.model.preference.PreferenceSource;
import com.administrator.shopkeepertablet.repository.parish.ParishRepertory;
import com.administrator.shopkeepertablet.utils.DateUtils;
import com.administrator.shopkeepertablet.utils.DialogUtils;
import com.administrator.shopkeepertablet.utils.MLog;
import com.administrator.shopkeepertablet.utils.MToast;
import com.administrator.shopkeepertablet.view.ui.activity.parish.TableActivity;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

import io.reactivex.functions.Consumer;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Description:
 * Author CC
 * Time 2018/7/15
 */


public class TableViewModel extends BaseViewModel {
    private ParishRepertory parishRepertory;
    private PreferenceSource preferenceSource;
    private TableActivity activity;
    public ObservableField<TableEntity> tableEntity = new ObservableField<>(new TableEntity());
    public ObservableField<String> title = new ObservableField<>("");
    public ObservableField<String> roomName = new ObservableField<>("");
    public ObservableField<String> time = new ObservableField<>("");
    public ObservableField<String> detailId =new ObservableField<>("");

    public TableViewModel(ParishRepertory parishRepertory, PreferenceSource preferenceSource, TableActivity activity) {
        this.parishRepertory = parishRepertory;
        this.preferenceSource = preferenceSource;
        this.activity = activity;
    }

    public String getTime(String time) {
        return DateUtils.friendly_time(DateUtils.stringToDate(time));
    }

    public void getRooms() {
        DialogUtils.showDialog(activity, "获取数据中");
        parishRepertory.getRooms("1", preferenceSource.getId(), 1, 100)
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        DialogUtils.hintDialog();
                        MLog.e("api_room", stringBaseEntity.getResult());
                        if (stringBaseEntity.getCode()==1) {
                            List<RoomEntity> rooms = Arrays.asList(new Gson().fromJson(stringBaseEntity.getResult(), RoomEntity[].class));
                            activity.refreshRoom(rooms);
                        }else {
                            MToast.showToast(activity,"房间信息获取失败");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        DialogUtils.hintDialog();
                        MToast.showToast(activity,"房间信息获取失败");
                    }
                });
    }

    public void getTables(RoomEntity roomEntity) {
        DialogUtils.showDialog(activity, "获取数据中");
        parishRepertory.getTables("0", roomEntity.getId(), preferenceSource.getId(), 1, 100)
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        DialogUtils.hintDialog();
                        MLog.e("api_table", stringBaseEntity.getResult());
                        if (stringBaseEntity.getCode()==1) {
                            TableEntity[] tableEntities = new Gson().fromJson(stringBaseEntity.getResult(), TableEntity[].class);
                            List<TableEntity> mList = Arrays.asList(tableEntities);
                            activity.refreshTable(mList);
                        }else {
                            MToast.showToast(activity,"获取桌位信息失败");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        DialogUtils.hintDialog();
                        MToast.showToast(activity,"获取桌位信息失败");
                    }
                });
    }

    public void changeTable(TableEntity entity){
        DialogUtils.showDialog(activity, "获取数据中");
        parishRepertory.changeTable("2",entity.getRoomTableId(),entity.getTableName(),tableEntity.get().getRoomTableId(),tableEntity.get().getBillId())
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        DialogUtils.hintDialog();
                        Log.e("vd",stringBaseEntity.toString());
                        if (stringBaseEntity.getCode()==1){
                            MToast.showToast(activity,"换桌成功");
                            activity.finish();
                        }else {
                            MToast.showToast(activity,"换桌失败");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("vd",throwable.getMessage());
                        DialogUtils.hintDialog();
                        MToast.showToast(activity,"换桌失败");
                    }
                });
    }

    public void transferFood(String tableId){
        DialogUtils.showDialog(activity, "获取数据中");
        parishRepertory.TransferFood("2",preferenceSource.getId(),tableId,detailId.get())
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        DialogUtils.hintDialog();
                        Log.e("vd",stringBaseEntity.toString());
                        if (stringBaseEntity.getCode()==1){
                            MToast.showToast(activity,"转菜成功");
                            activity.finish();
                        }else {
                            MToast.showToast(activity,"转菜失败");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        DialogUtils.hintDialog();
                        Log.e("vd",throwable.getMessage());
                        MToast.showToast(activity,"转菜失败");
                    }
                });
    }

    public void getOrderFoodList(TableEntity entity) {
        DialogUtils.showDialog(activity, "获取数据中");
        parishRepertory.getOrderFoodList("13", preferenceSource.getId(), entity.getBillId()).subscribe(
                new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        DialogUtils.hintDialog();
                        Log.e("vd_order", stringBaseEntity.getCode() + "--" + stringBaseEntity.getResult());
                        if (stringBaseEntity.getCode() == 1) {
                            OrderFoodEntity[] orderFoodEntities = new Gson().fromJson(stringBaseEntity.getResult(), OrderFoodEntity[].class);
                            List<OrderFoodEntity> mList = Arrays.asList(orderFoodEntities);
                            activity.orderFoodEntityList.addAll(mList);
//                            activity.showDialogMerge();
                        }else {
                            MToast.showToast(activity,"获取订单菜品信息失败");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        DialogUtils.hintDialog();
                        Log.e("VD", throwable.getMessage());
                        MToast.showToast(activity,"获取订单菜品信息失败");
                    }
                }
        );
    }

    public void getMerge(String tableId){
        DialogUtils.showDialog(activity,"获取并单信息中");
       parishRepertory.getMergeOrderList("12",preferenceSource.getId(),tableId).subscribe(new Consumer<BaseEntity<String>>() {
           @Override
           public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
               DialogUtils.hintDialog();
                if (stringBaseEntity.getCode()==1){
                    String[] result = stringBaseEntity.getResult().split("∞");
                    OrderEntity[] orders = new Gson().fromJson(result[0], OrderEntity[].class);
                    OrderFoodEntity[] detailFoods = new Gson().fromJson(result[1], OrderFoodEntity[].class);

                    OrderEntity order = new OrderEntity();
                    int tableWareCount = 0;
                    int peopleCount = 0;
                    String billId = "";
                    String oTableId = "";
                    String oTableName = "";
                    String id = "";
                    double price = 0;
                    for (int i = 0; i < orders.length; i++) {
                        OrderEntity o = orders[i];
                        tableWareCount += o.getTableWareCount();
                        peopleCount += o.getPersonCount();
                        price += o.getPayPrice();
                        if (i == orders.length - 1) {
                            id += o.getId();
                            billId += o.getBillId();
                            oTableId += o.getTableId();
                            oTableName += o.getTableName();
                        } else {
                            id += o.getId() + ",";
                            billId += o.getBillId() + ",";
                            oTableId += o.getTableId() + ",";
                            oTableName += o.getTableName() + ",";
                        }
                    }
                    order.setId(id);
                    order.setTableWareCount(tableWareCount);
                    order.setPersonCount(peopleCount);
                    order.setBillId(billId);
                    order.setTableId(oTableId);
                    order.setType(orders[0].getType());
                    order.setPayPrice(price);
                    order.setTableName(oTableName);
                    activity.showDialogMerge(order, Arrays.asList(detailFoods));
                }else {
                    MToast.showToast(activity,"获取并单信息失败");
                }
           }
       }, new Consumer<Throwable>() {
           @Override
           public void accept(Throwable throwable) throws Exception {
               DialogUtils.hintDialog();
               MToast.showToast(activity,"获取并单信息失败");
           }
       });
    }
}
