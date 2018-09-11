package com.administrator.shopkeepertablet.viewmodel;

import android.databinding.ObservableField;
import android.util.Log;

import com.administrator.shopkeepertablet.model.entity.BaseEntity;
import com.administrator.shopkeepertablet.model.entity.OrderEntity;
import com.administrator.shopkeepertablet.model.entity.OrderFoodEntity;
import com.administrator.shopkeepertablet.model.entity.PayTypeEntity;
import com.administrator.shopkeepertablet.model.preference.PreferenceSource;
import com.administrator.shopkeepertablet.repository.order.OrderRepository;
import com.administrator.shopkeepertablet.utils.DialogUtils;
import com.administrator.shopkeepertablet.utils.MLog;
import com.administrator.shopkeepertablet.utils.MToast;
import com.administrator.shopkeepertablet.utils.Print;
import com.administrator.shopkeepertablet.view.ui.fragment.OrderFragment;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Description:
 * Author chencheng
 * Time 2018/7/24
 */

public class OrderViewModel extends BaseViewModel {
    private OrderFragment fragment;
    private PreferenceSource preferenceSource;
    private OrderRepository orderRepository;
    public int index = 1;
    public int size = 10;

    public ObservableField<List<OrderFoodEntity>> detailFoods =new ObservableField<>();
    public ObservableField<OrderEntity> orderEntity =new ObservableField<>();
    public ObservableField<String> payInfo =new ObservableField<>("");
    private Print print;

    public OrderViewModel(OrderFragment fragment, PreferenceSource preferenceSource, OrderRepository orderRepository) {
        this.fragment = fragment;
        this.preferenceSource = preferenceSource;
        this.orderRepository = orderRepository;
        this.print = new Print(orderRepository);
    }

    private void printResult(final String result) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                print.socketDataArrivalHandler(result);
            }
        }).start();
    }

    public void getOrderList(String type, String state) {
        index = 0;
        DialogUtils.showDialog(fragment.getActivity(), "获取数据中");
        orderRepository.getOrderList("0", preferenceSource.getId(), type, "all", index, size, state)
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        MLog.e("vd", stringBaseEntity.toString());
                        DialogUtils.hintDialog();
                        if (stringBaseEntity.getCode() == 1) {
                            List<OrderEntity> orderEntityList = Arrays.asList(new Gson().fromJson(stringBaseEntity.getResult(), OrderEntity[].class));
                            fragment.refresh(orderEntityList);
                        }else {
                            MToast.showToast(fragment.getActivity(),"获取订单信息失败");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        MLog.e("vd", throwable.getMessage());
                        DialogUtils.hintDialog();
                        MToast.showToast(fragment.getActivity(),"获取订单信息失败");
                    }
                });
    }

    public void getOrderMoreList(String type, String state) {
        index = index + 1;
        DialogUtils.showDialog(fragment.getActivity(), "获取更多订单中");
        orderRepository.getOrderList("0", preferenceSource.getId(), type, "all", index, size, state)
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        DialogUtils.hintDialog();
                        MLog.e("vd", stringBaseEntity.toString());
                        if (stringBaseEntity.getCode() == 1) {
                            List<OrderEntity> orderEntityList = Arrays.asList(new Gson().fromJson(stringBaseEntity.getResult(), OrderEntity[].class));
                            fragment.loadMore(orderEntityList);
                        }else {
                            MToast.showToast(fragment.getActivity(),"获取更多订单信息失败");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        DialogUtils.hintDialog();
                        MLog.e("vd", throwable.getMessage());
                        MToast.showToast(fragment.getActivity(),"获取更多订单信息失败");
                    }
                });
    }

    public void getOrderDetail(String billId){
        DialogUtils.showDialog(fragment.getActivity(), "获取数据中");
        orderRepository.getOrderDetail(preferenceSource.getId(),"9",billId)
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        MLog.e("vd",stringBaseEntity.getResult());
                        DialogUtils.hintDialog();
                        if (stringBaseEntity.getCode()==1){
                            String[] result = stringBaseEntity.getResult().split("\\^");
                           detailFoods.set(Arrays.asList(new Gson().fromJson(result[0], OrderFoodEntity[].class)));
                            if (result.length > 1) {
                                List<PayTypeEntity> tPayTypes =  Arrays.asList(new Gson().fromJson(result[1], PayTypeEntity[].class));
                                initPayInfo(tPayTypes);
                            }
                            fragment.showPop();
                        }else {
                            MToast.showToast(fragment.getActivity(),"获取订单详情失败");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        MLog.e("vd", throwable.getMessage());
                        DialogUtils.hintDialog();
                        MToast.showToast(fragment.getActivity(),"获取订单详情失败");
                    }
                });
    }

    public void  print(){
        DialogUtils.showDialog(fragment.getActivity(), "获取打印数据中");
        orderRepository.print("3", preferenceSource.getId(), "1", "7", orderEntity.get().getBillId(), preferenceSource.getName(), orderEntity.get().getPersonCount(),
                orderEntity.get().getTableId(), orderEntity.get().getTableName(), orderEntity.get().orderPrice(), orderEntity.get().getPayPrice(), orderEntity.get().getFreeMoney(), "1")
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        MLog.e("vd",stringBaseEntity.getResult());
                        DialogUtils.hintDialog();
                        if (stringBaseEntity.getCode()==1){
                            printResult(stringBaseEntity.getResult());
                        }else {
                            MToast.showToast(fragment.getActivity(),"获取打印信息失败");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        MLog.e("vd",throwable.getMessage());
                        DialogUtils.hintDialog();
                        MToast.showToast(fragment.getActivity(),"获取打印信息失败");
                    }
                });
    }

    private void initPayInfo(List<PayTypeEntity> tPayTypes){
        StringBuilder builder = new StringBuilder();
        if (tPayTypes != null && tPayTypes.size() > 0) {
            for (int i = 0; i < tPayTypes.size(); i++) {
                switch (tPayTypes.get(i).getPayType()) {
                    case "1":
                        builder = builder.append("现金：" + tPayTypes.get(i).getPice() + " |");
                        break;
                    case "2":
                        builder = builder.append("银行卡：" + tPayTypes.get(i).getPice() + " |");
                        break;
                    case "3":
                        builder = builder.append("主扫微信：" + tPayTypes.get(i).getPice() + " |");
                        break;
                    case "4":
                        builder = builder.append("挂账：" + tPayTypes.get(i).getPice() + " |");
                        break;
                    case "5":
                        builder = builder.append("会员卡：" + tPayTypes.get(i).getPice() + " |");
                        break;
                    case "6":
                        builder = builder.append("被扫支付宝：" + tPayTypes.get(i).getPice() + " |");
                        break;
                    case "7":
                        builder = builder.append("被扫微信：" + tPayTypes.get(i).getPice() + " |");
                        break;
                    case "8":
                        builder = builder.append("美团券：" + tPayTypes.get(i).getPice() + " |");
                        break;
                    case "9":
                        builder = builder.append("大众点评券：" + tPayTypes.get(i).getPice() + " |");
                        break;
                    default:
                        builder = builder.append("主扫支付宝：" + tPayTypes.get(i).getPice() + " |");
                        break;
                }
            }
            builder.deleteCharAt(builder.length() - 1);
        }
        payInfo.set(builder.toString());
    }

}
