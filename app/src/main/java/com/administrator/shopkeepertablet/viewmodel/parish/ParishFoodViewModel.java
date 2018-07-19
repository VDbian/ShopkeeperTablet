package com.administrator.shopkeepertablet.viewmodel.parish;

import android.content.Intent;
import android.databinding.ObservableField;
import android.util.Log;

import com.administrator.shopkeepertablet.AppConstant;
import com.administrator.shopkeepertablet.di.app.AppComponent;
import com.administrator.shopkeepertablet.model.entity.BaseEntity;
import com.administrator.shopkeepertablet.model.entity.OrderFoodEntity;
import com.administrator.shopkeepertablet.model.entity.ReturnReasonEntity;
import com.administrator.shopkeepertablet.model.entity.RoomEntity;
import com.administrator.shopkeepertablet.model.entity.TableEntity;
import com.administrator.shopkeepertablet.model.preference.PreferenceSource;
import com.administrator.shopkeepertablet.repository.parish.ParishRepertory;
import com.administrator.shopkeepertablet.utils.MLog;
import com.administrator.shopkeepertablet.utils.MToast;
import com.administrator.shopkeepertablet.utils.Print;
import com.administrator.shopkeepertablet.view.ui.BaseFragment;
import com.administrator.shopkeepertablet.view.ui.fragment.ParishFoodFragment;
import com.administrator.shopkeepertablet.viewmodel.BaseViewModel;
import com.google.gson.Gson;
import com.zhy.autolayout.utils.L;

import org.greenrobot.greendao.annotation.Id;

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
    public ObservableField<Double> totalPrice =new ObservableField<>(0.00);

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
        parishRepertory.getRooms("1", preferenceSource.getId(), 1, 100)
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        MLog.e("api_room", stringBaseEntity.getResult());
                        List<RoomEntity> rooms = Arrays.asList(new Gson().fromJson(stringBaseEntity.getResult(), RoomEntity[].class));
                        fragment.initTabView(rooms);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    public void getTables(RoomEntity roomEntity) {
        parishRepertory.getTables("0", roomEntity.getId(), preferenceSource.getId(), 1, 100)
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        MLog.e("api_table", stringBaseEntity.getResult());
                        TableEntity[] tableEntities = new Gson().fromJson(stringBaseEntity.getResult(), TableEntity[].class);
                        List<TableEntity> mList = Arrays.asList(tableEntities);
                        fragment.refreshVariety(mList);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    public void openTable(final boolean flag) {
        parishRepertory.openTable("1", tableId.get(), table.get(), preferenceSource.getId(),
                people.get(), tableware.get(), preferenceSource.getUserId(), preferenceSource.getName())
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
//                       MLog.e("api_kai",stringBaseEntity.getResult());
                        if (stringBaseEntity.getCode() == 1) {
                            MToast.showToast(fragment.getActivity(), "开台成功");
                            billId.set(stringBaseEntity.getResult());
                            fragment.openSuccess(flag);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    public void clearTable(String billId) {
        parishRepertory.clearTable("3", tableId.get(), billId, preferenceSource.getId())
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        MLog.e("api", stringBaseEntity.toString());
                        MToast.showToast(fragment.getActivity(), stringBaseEntity.getMessage());
                        if (stringBaseEntity.getCode() == AppConstant.REQUEST_SUCCESS) {
                            fragment.clearSuccess();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });

    }

    public void getOrderFoodList(final TableEntity entity) {
        parishRepertory.getOrderFoodList("13", preferenceSource.getId(), billId.get()).subscribe(
                new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        Log.e("vd_order", stringBaseEntity.getCode() + "--" + stringBaseEntity.getResult());
                        if (stringBaseEntity.getCode() == 1) {
                            OrderFoodEntity[] orderFoodEntities = new Gson().fromJson(stringBaseEntity.getResult(), OrderFoodEntity[].class);
                            List<OrderFoodEntity> mList = Arrays.asList(orderFoodEntities);
                            double a = 0;
                            for (OrderFoodEntity entity : mList) {
                                a += entity.getChargeMoney();
                            }
                            price.set(a);
                            fragment.initPayPop(mList, entity);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("VD", throwable.getMessage());
                    }
                }
        );
    }

    public void cancelOrder() {
        parishRepertory.cancelOrder("4", tableId.get(), billId.get(), preferenceSource.getId(), table.get(), preferenceSource.getName(), preferenceSource.getUserId())
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        Log.e("vd", stringBaseEntity.toString());
                        if (stringBaseEntity.getCode() == 1) {
                            MToast.showToast(fragment.getActivity(), "撤单成功");
                            fragment.cancelOrderSuccess();
                        } else {
                            MToast.showToast(fragment.getActivity(), "撤单失败");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        MToast.showToast(fragment.getActivity(), "撤单失败");
                    }
                });
    }

    public void changePeople(String peopleNum, String wareNum) {
        parishRepertory.changePeople("2", tableId.get(), peopleNum, wareNum, billId.get(), preferenceSource.getId())
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        Log.e("vd", stringBaseEntity.toString());
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

                    }
                });
    }

    public void pushFood(String detailId) {
        parishRepertory.pushFood("2", detailId, billId.get(), preferenceSource.getId(), tableId.get(), preferenceSource.getName(), table.get())
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        Log.e("vd", stringBaseEntity.toString());
                        if (stringBaseEntity.getCode() == 1) {
                            MToast.showToast(fragment.getActivity(), "催菜成功");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    public void pushFoodAll() {
//        Log.e("vd",billId.get()+"**"+tableId.get()+"**"+table.get());
        parishRepertory.pushFoodAll("1", "1", preferenceSource.getId(), billId.get(), tableId.get(), table.get(), "2", "1", "2")
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        if (stringBaseEntity.getCode() == 1) {
                            MToast.showToast(fragment.getActivity(), "催菜成功");
                        } else {
                            MToast.showToast(fragment.getActivity(), "催菜失败");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        MToast.showToast(fragment.getActivity(), "催菜失败");
                    }
                });
    }

    //"1", "1", App.INSTANCE().getShopID(), billid, tableId, tableName, personcount, "6", "0", App.INSTANCE().getUser().getName()
    public void print() {
        parishRepertory.printAfter("1", "1", preferenceSource.getId(), billId.get(), tableId.get(), table.get(), people.get(), "6", "0", preferenceSource.getName())
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        Log.e("vd", stringBaseEntity.toString());
                        if (stringBaseEntity.getCode() == 1) {
                            MToast.showToast(fragment.getActivity(), "打印成功");
                        } else {
                            MToast.showToast(fragment.getActivity(), "打印失败");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        MToast.showToast(fragment.getActivity(), "打印失败");
                    }
                });
    }

    public void getReason(OrderFoodEntity orderFoodEntity) {
        parishRepertory.getReason(preferenceSource.getId(), "7")
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        Log.e("vd", stringBaseEntity.toString());
                        if (stringBaseEntity.getCode() == 1) {
                            List<ReturnReasonEntity> reasonEntities = Arrays.asList(new Gson().fromJson(stringBaseEntity.getResult(), ReturnReasonEntity[].class));
                            fragment.showReturn(orderFoodEntity, reasonEntities);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("vd", throwable.getMessage());
                    }
                });
    }

    public void cancelPay() {
        parishRepertory.cancelPay("19", billId.get())
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        if (stringBaseEntity.getCode() == 1) {
                            MToast.showToast(fragment.getActivity(), "取消成功");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    public void returnFood(OrderFoodEntity orderFoodEntity, String remark, ReturnReasonEntity returnReasonEntity, String num) {
        String count = String.valueOf(orderFoodEntity.getCount() - Double.valueOf(num));
        parishRepertory.returnFood(preferenceSource.getId(), "3", orderFoodEntity.getDetailId(), billId.get(), tableId.get(), preferenceSource.getName(),
                table.get(), count, String.valueOf(orderFoodEntity.getPrice()), num, String.valueOf(orderFoodEntity.getWeight()), remark, returnReasonEntity.getId(), returnReasonEntity.getRemark())
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        Log.e("vd", stringBaseEntity.toString());
                        if (stringBaseEntity.getCode()==1){
                            MToast.showToast(fragment.getActivity(),"退菜成功");
                            fragment.clearSuccess();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    public void givingFood(String orderDetailId,String num){
        parishRepertory.givingFood("14",orderDetailId,num).subscribe(new Consumer<BaseEntity<String>>() {
            @Override
            public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                Log.e("vd",stringBaseEntity.getResult());
                if (stringBaseEntity.getCode()==1){
                    MToast.showToast(fragment.getActivity(),"赠送成功");
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        });
    }

}
