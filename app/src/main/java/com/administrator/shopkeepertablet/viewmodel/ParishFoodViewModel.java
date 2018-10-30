package com.administrator.shopkeepertablet.viewmodel;

import android.databinding.ObservableField;
import android.util.Log;

import com.administrator.shopkeepertablet.AppConstant;
import com.administrator.shopkeepertablet.model.entity.BaseEntity;
import com.administrator.shopkeepertablet.model.entity.OrderEntity;
import com.administrator.shopkeepertablet.model.entity.OrderFoodEntity;
import com.administrator.shopkeepertablet.model.entity.ReturnReasonEntity;
import com.administrator.shopkeepertablet.model.entity.RoomEntity;
import com.administrator.shopkeepertablet.model.entity.TableEntity;
import com.administrator.shopkeepertablet.model.preference.PreferenceSource;
import com.administrator.shopkeepertablet.repository.BaseRepertory;
import com.administrator.shopkeepertablet.repository.parish.ParishRepertory;
import com.administrator.shopkeepertablet.utils.DialogUtils;
import com.administrator.shopkeepertablet.utils.MLog;
import com.administrator.shopkeepertablet.utils.MToast;
import com.administrator.shopkeepertablet.utils.Print;
import com.administrator.shopkeepertablet.view.ui.fragment.ParishFoodFragment;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Description:
 * Author CC
 * Time 2018/7/3
 */

public class ParishFoodViewModel extends BaseViewModel {
    private ParishFoodFragment fragment;
    private ParishRepertory parishRepertory;
    private PreferenceSource preferenceSource;
    public ObservableField<String> room = new ObservableField<>("");
    public ObservableField<String> table = new ObservableField<>("");
    public ObservableField<String> tableId = new ObservableField<>("");
    public ObservableField<String> people = new ObservableField<>("1");
    public ObservableField<String> tableware = new ObservableField<>("1");
    public ObservableField<String> time = new ObservableField<>("");
    public ObservableField<String> billId = new ObservableField<>("");
    public ObservableField<Double> price = new ObservableField<>(0.00);
    public ObservableField<Double> totalPrice = new ObservableField<>(0.00);

    public ParishFoodViewModel(ParishFoodFragment fragment, ParishRepertory parishRepertory, PreferenceSource preferenceSource) {
        this.fragment = fragment;
        this.parishRepertory = parishRepertory;
        this.preferenceSource = preferenceSource;
    }

    private void printResult(final String result) {
        final Print print = new Print(parishRepertory);
        new Thread(new Runnable() {
            @Override
            public void run() {
                print.socketDataArrivalHandler(result);
            }
        }).start();
    }

    public void getRooms() {
        DialogUtils.showDialog(fragment.getActivity(), "获取数据中");
        parishRepertory.getRooms("1", preferenceSource.getId(), 1, 100)
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        MLog.e("api_room", stringBaseEntity.getResult());
                        DialogUtils.hintDialog();
                        if (stringBaseEntity.getCode()==1) {
                            List<RoomEntity> rooms = Arrays.asList(new Gson().fromJson(stringBaseEntity.getResult(), RoomEntity[].class));
                            fragment.initTabView(rooms);
                        }else {
                            MToast.showToast(fragment.getActivity(),"获取房间信息失败");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        MToast.showToast(fragment.getActivity(),"获取房间信息失败");
                        DialogUtils.hintDialog();
                    }
                });
    }

    public void getTables(RoomEntity roomEntity) {
        DialogUtils.showDialog(fragment.getActivity(), "获取数据中");
        parishRepertory.getTables("0", roomEntity.getId(), preferenceSource.getId(), 1, 100)
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        MLog.e("api_table", stringBaseEntity.getResult());
                        DialogUtils.hintDialog();
                        if (stringBaseEntity.getCode()==1) {
                            TableEntity[] tableEntities = new Gson().fromJson(stringBaseEntity.getResult(), TableEntity[].class);
                            List<TableEntity> mList = Arrays.asList(tableEntities);
                            fragment.refreshVariety(mList);
                        }else {
                            MToast.showToast(fragment.getActivity(),"获取桌位信息失败");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        DialogUtils.hintDialog();
                        MToast.showToast(fragment.getActivity(),"获取桌位信息失败");
                    }
                });
    }

    public void openTable(final boolean flag) {
        DialogUtils.showDialog(fragment.getActivity(), "数据提交中");
        parishRepertory.openTable("1", tableId.get(), table.get(), preferenceSource.getId(),
                people.get(), tableware.get(), preferenceSource.getUserId(), preferenceSource.getName())
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
//                       MLog.e("api_kai",stringBaseEntity.getResult());
                        DialogUtils.hintDialog();
                        if (stringBaseEntity.getCode() == 1) {
                            MToast.showToast(fragment.getActivity(), "开台成功");
                            billId.set(stringBaseEntity.getResult());
                            fragment.openSuccess(flag);
                        }else {
                            MToast.showToast(fragment.getActivity(),"开台失败");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        DialogUtils.hintDialog();
                        MToast.showToast(fragment.getActivity(),"开台失败");
                    }
                });
    }

    public void clearTable(String billId) {
        DialogUtils.showDialog(fragment.getActivity(), "数据提交中");
        parishRepertory.clearTable("3", tableId.get(), billId, preferenceSource.getId())
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        MLog.e("api", stringBaseEntity.toString());
                        DialogUtils.hintDialog();
                        if (stringBaseEntity.getCode() == AppConstant.REQUEST_SUCCESS) {
                            fragment.clearSuccess();
                            MToast.showToast(fragment.getActivity(),"清台成功");
                        }else {
                            MToast.showToast(fragment.getActivity(),"清台失败");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        DialogUtils.hintDialog();
                        MToast.showToast(fragment.getActivity(),"清台失败");
                    }
                });

    }

    public void getOrderFoodList(final TableEntity entity) {
        DialogUtils.showDialog(fragment.getActivity(), "获取数据中");
        parishRepertory.getOrderFoodList("13", preferenceSource.getId(), billId.get()).subscribe(
                new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        MLog.e("vd_order", stringBaseEntity.getCode() + "--" + stringBaseEntity.getResult());
                        DialogUtils.hintDialog();
                        if (stringBaseEntity.getCode() == 1) {
                            OrderFoodEntity[] orderFoodEntities = new Gson().fromJson(stringBaseEntity.getResult(), OrderFoodEntity[].class);
                            List<OrderFoodEntity> mList = Arrays.asList(orderFoodEntities);
                            double a = 0;
                            for (OrderFoodEntity entity : mList) {
                                a += entity.getChargeMoney();
                            }
                            price.set(a);
//                            fragment.initPayPop(mList,entity);
                        }else {
                            MToast.showToast(fragment.getActivity(),"获取订单菜品信息失败");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        MLog.e("VD", throwable.getMessage());
                        DialogUtils.hintDialog();
                        MToast.showToast(fragment.getActivity(),"获取订单菜品信息失败");
                    }
                }
        );
    }

    public void cancelOrder() {
        DialogUtils.showDialog(fragment.getActivity(), "获取数据中");
        parishRepertory.cancelOrder("4", tableId.get(), billId.get(), preferenceSource.getId(), table.get(), preferenceSource.getName(), preferenceSource.getUserId())
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        MLog.e("vd", stringBaseEntity.toString());
                        DialogUtils.hintDialog();
                        if (stringBaseEntity.getCode() == 1) {
                            MToast.showToast(fragment.getActivity(), "撤单成功");
                            printResult(stringBaseEntity.getResult());
                            fragment.cancelOrderSuccess();
                        } else {
                            MToast.showToast(fragment.getActivity(), "撤单失败");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        DialogUtils.hintDialog();
                        MToast.showToast(fragment.getActivity(), "撤单失败");
                    }
                });
    }

    public void changePeople(String peopleNum, String wareNum) {
        DialogUtils.showDialog(fragment.getActivity(), "数据提交中");
        parishRepertory.changePeople("2", tableId.get(), peopleNum, wareNum, billId.get(), preferenceSource.getId())
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        MLog.e("vd", stringBaseEntity.toString());
                        DialogUtils.hintDialog();
                        if (stringBaseEntity.getCode() == 1) {
                            MToast.showToast(fragment.getActivity(), "修改成功");
                            fragment.cancelOrderSuccess();
                        } else {
                            MToast.showToast(fragment.getActivity(), "修改失败");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        DialogUtils.hintDialog();
                        MToast.showToast(fragment.getActivity(), "修改失败");
                    }
                });
    }

    public void pushFood(String detailId) {
        DialogUtils.showDialog(fragment.getActivity(), "获取数据中");
        parishRepertory.pushFood("2", detailId, billId.get(), preferenceSource.getId(), tableId.get(), preferenceSource.getName(), table.get())
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        MLog.e("vd", stringBaseEntity.toString());
                        DialogUtils.hintDialog();
                        if (stringBaseEntity.getCode() == 1) {
                            printResult(stringBaseEntity.getResult());
                        }else{
                            MToast.showToast(fragment.getActivity(),"催菜失败");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        DialogUtils.hintDialog();
                        MToast.showToast(fragment.getActivity(),"催菜失败");
                    }
                });
    }

    public void pushFoodAll() {
        DialogUtils.showDialog(fragment.getActivity(), "数据提交中");
//        MLog.e("vd",billId.get()+"**"+tableId.get()+"**"+table.get());
        parishRepertory.pushFoodAll("1", "1", preferenceSource.getId(), billId.get(), tableId.get(), table.get(), "2", "1", "2")
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        DialogUtils.hintDialog();
                        if (stringBaseEntity.getCode() == 1) {
                            printResult(stringBaseEntity.getResult());
                            MToast.showToast(fragment.getActivity(), "催菜成功");
                        } else {
                            MToast.showToast(fragment.getActivity(), "催菜失败");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        DialogUtils.hintDialog();
                        MToast.showToast(fragment.getActivity(), "催菜失败");
                    }
                });
    }

    //"1", "1", App.INSTANCE().getShopID(), billid, tableId, tableName, personcount, "6", "0", App.INSTANCE().getUser().getName()
    public void print() {
        DialogUtils.showDialog(fragment.getActivity(), "获取打印数据中");
        parishRepertory.printAfter("1", "1", preferenceSource.getId(), billId.get(), tableId.get(), table.get(), people.get(), "6", "0", preferenceSource.getName())
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        MLog.e("vd", stringBaseEntity.toString());
                        DialogUtils.hintDialog();
                        if (stringBaseEntity.getCode() == 1) {
                            printResult(stringBaseEntity.getResult());
                        } else {
                            MToast.showToast(fragment.getActivity(), "打印失败");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        DialogUtils.hintDialog();
                        MToast.showToast(fragment.getActivity(), "打印失败");
                    }
                });
    }

    public void getReason(OrderFoodEntity orderFoodEntity) {
        DialogUtils.showDialog(fragment.getActivity(), "获取数据中");
        parishRepertory.getReason(preferenceSource.getId(), "7")
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        DialogUtils.hintDialog();
                        MLog.e("vd", stringBaseEntity.toString());
                        if (stringBaseEntity.getCode() == 1) {
                            List<ReturnReasonEntity> reasonEntities = Arrays.asList(new Gson().fromJson(stringBaseEntity.getResult(), ReturnReasonEntity[].class));
                            fragment.showReturn(orderFoodEntity, reasonEntities);
                        }else {
                            MToast.showToast(fragment.getActivity(),"获取退菜理由失败");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        DialogUtils.hintDialog();
                        MLog.e("vd", throwable.getMessage());
                        MToast.showToast(fragment.getActivity(),"获取退菜理由失败");
                    }
                });
    }

    public void cancelPay() {
        DialogUtils.showDialog(fragment.getActivity(), "数据提交中");
        parishRepertory.cancelPay("19", billId.get())
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        DialogUtils.hintDialog();
                        if (stringBaseEntity.getCode() == 1) {
                            MToast.showToast(fragment.getActivity(), "取消成功");
                            fragment.clearSuccess();
                        }else {
                            MToast.showToast(fragment.getActivity(),"取消成功");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        DialogUtils.hintDialog();
                        MToast.showToast(fragment.getActivity(),"取消失败");
                    }
                });
    }

    public void returnFood(OrderFoodEntity orderFoodEntity, String remark, ReturnReasonEntity returnReasonEntity, String num) {
        String count = String.valueOf(orderFoodEntity.getCount() - Double.valueOf(num));
        DialogUtils.showDialog(fragment.getActivity(), "数据提交中");
        parishRepertory.returnFood(preferenceSource.getId(), "3", orderFoodEntity.getDetailId(), billId.get(), tableId.get(), preferenceSource.getName(),
                table.get(), count, String.valueOf(orderFoodEntity.getPrice()), num, String.valueOf(orderFoodEntity.getWeight()), remark, returnReasonEntity.getId(), returnReasonEntity.getRemark())
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        MLog.e("vd", stringBaseEntity.toString());
                        DialogUtils.hintDialog();
                        if (stringBaseEntity.getCode() == 1) {
                            MToast.showToast(fragment.getActivity(), "退菜成功");
                            printResult(stringBaseEntity.getResult());
                            fragment.clearSuccess();
                        }else {
                            MToast.showToast(fragment.getActivity(),"退菜失败");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        DialogUtils.hintDialog();
                        MToast.showToast(fragment.getActivity(),"退菜失败");
                    }
                });
    }

    public void givingFood(String orderDetailId, String num) {
        DialogUtils.showDialog(fragment.getActivity(), "数据提交中");
        MLog.e("vd","14"+"**"+orderDetailId+"**"+num);
        parishRepertory.givingFood("14", orderDetailId, num).subscribe(new Consumer<BaseEntity<String>>() {
            @Override
            public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                DialogUtils.hintDialog();
                MLog.e("vd", stringBaseEntity.getResult());
                if (stringBaseEntity.getCode() == 1) {
                    MToast.showToast(fragment.getActivity(), "赠送成功");
                    fragment.giveOrderSuccess();
                    printResult(stringBaseEntity.getResult());
                }else {
                    MToast.showToast(fragment.getActivity(),"赠送失败");
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                DialogUtils.hintDialog();
                MToast.showToast(fragment.getActivity(),"赠送失败");
            }
        });
    }

    public void getOrder(TableEntity entity) {
        DialogUtils.showDialog(fragment.getActivity(), "获取数据中");
        parishRepertory.getOrder("9", entity.getRoomTableId()).subscribe(
                new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        DialogUtils.hintDialog();
                        if (stringBaseEntity.getCode() == 1) {
                            String[] result = stringBaseEntity.getResult().split("∞");
                            OrderEntity order = new Gson().fromJson(result[0], OrderEntity.class);
                            MLog.e("vd", result[1]);
                            List<OrderFoodEntity> mList = Arrays.asList(new Gson().fromJson(result[1], OrderFoodEntity[].class));
                            double a = 0;
                            for (OrderFoodEntity entity : mList) {
                                a += entity.getChargeMoney();
                            }
                            price.set(a);
                            fragment.initPayPop(mList, order, entity);
                            MLog.e("vd", order.toString());
                        }else {
                            MToast.showToast(fragment.getActivity(),"数据获取失败");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        DialogUtils.hintDialog();
                        MToast.showToast(fragment.getActivity(),"数据获取失败");
                    }
                }
        );
    }

    public void inBill(int state,List<OrderFoodEntity> mList, OrderEntity orderEntity,final TableEntity entity) {
        DialogUtils.showDialog(fragment.getActivity(), "数据提交中");
        parishRepertory.inBill("18", billId.get()).subscribe(
                new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        DialogUtils.hintDialog();
                        if (stringBaseEntity.getCode()==1){
                            fragment.inBillSuccess(state,mList,orderEntity,entity);
                        }else {
                            MToast.showToast(fragment.getActivity(),"更新结账状态失败");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        DialogUtils.hintDialog();
                        MToast.showToast(fragment.getActivity(),"更新结账状态失败");
                    }
                });
    }

    public void scanBill(String code, double price, String billId) {
        DialogUtils.showDialog(fragment.getActivity(), "数据提交中...");
        parishRepertory.scanBill("21", code, price,preferenceSource.getId(), billId)
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        DialogUtils.hintDialog();
                        if(stringBaseEntity.getCode()==1){
                            if (stringBaseEntity.getResult().contains("SUCCESS")) {
                                String parType[] = stringBaseEntity.getResult().split("&");
                                fragment.bill(parType[1] ,billId , price ,"" ,"");
                            }else if(stringBaseEntity.getResult().contains("FAILED")){
                                MToast.showToast(fragment.getActivity(),"支付失败");
                            }else if(stringBaseEntity.getResult().contains("UNKNOWN")){
                                MToast.showToast(fragment.getActivity(),"支付错误");
                            }else if(stringBaseEntity.getResult().contains("USERPAYING")){
                                fragment.bill("3" ,billId , price ,"" ,"支付中");
                            }else if(stringBaseEntity.getResult().contains("ORDERPAID")){
                                MToast.showToast(fragment.getActivity(),"订单已支付");
                            }else if(stringBaseEntity.getResult().contains("AUTHCODEEXPIRE")){
                                MToast.showToast(fragment.getActivity(),"二维码已过期");
                            }else if(stringBaseEntity.getResult().contains("NOTENOUGH")){
                                MToast.showToast(fragment.getActivity(),"余额不足");
                            }else if(stringBaseEntity.getResult().contains("OUT_TRADE_NO_USED")){
                                MToast.showToast(fragment.getActivity(),"订单号重复");
                            }else if(stringBaseEntity.getResult().contains("QITA")){
                                MToast.showToast(fragment.getActivity(),"其他错误");
                            }else if(stringBaseEntity.getResult().contains("CODEUNKNOWN")){
                                MToast.showToast(fragment.getActivity(),"二维码错误");
                            }else{
                                String parType[] = stringBaseEntity.getResult().split("&");
                                fragment.bill(parType[1] ,billId , price ,parType[0],"");
                            }
                        }else {
                            MToast.showToast(fragment.getActivity(),"支付失败");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        DialogUtils.hintDialog();
                        MToast.showToast(fragment.getActivity(),"支付失败");
                    }
                });

    }

    public void bill(String id, String TableId, double zon, double can, String jsonObjquanxian,
                     String jsonObj, String jsonPay, String payType, int peoplecount, double price, String tablename, double free, String types , String memberID) {
        DialogUtils.showDialog(fragment.getActivity(), "数据提交中");
        parishRepertory.bill("3", id, preferenceSource.getId(), memberID, TableId, zon, can, 0, 0, types, jsonObjquanxian, jsonObj, payType, jsonPay,
                "", "", preferenceSource.getUserId(), preferenceSource.getName(), "", "", "", peoplecount,
                price, tablename, free,0,0).subscribe(new Consumer<BaseEntity<String>>() {
            @Override
            public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                DialogUtils.hintDialog();
                MLog.e("vd", stringBaseEntity.toString());
                if (stringBaseEntity.getCode() == 1) {
                    if (stringBaseEntity.getResult().equals("0")) {
                        MToast.showToast(fragment.getActivity(), "结账失败");
                    } else {
                        fragment.billSuccess("结账成功");
                        printResult(stringBaseEntity.getResult());
                    }
                }else {
                    MToast.showToast(fragment.getActivity(),"结账失败");
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                DialogUtils.hintDialog();
                MToast.showToast(fragment.getActivity(),"结账失败");
            }
        });
    }

}
