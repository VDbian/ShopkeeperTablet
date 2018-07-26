package com.administrator.shopkeepertablet.viewmodel;

import android.databinding.ObservableField;
import android.util.Log;

import com.administrator.shopkeepertablet.model.entity.BaseEntity;
import com.administrator.shopkeepertablet.model.entity.OrderEntity;
import com.administrator.shopkeepertablet.model.entity.OrderFoodEntity;
import com.administrator.shopkeepertablet.model.entity.PayTypeEntity;
import com.administrator.shopkeepertablet.model.preference.PreferenceSource;
import com.administrator.shopkeepertablet.repository.order.OrderRepository;
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


    public OrderViewModel(OrderFragment fragment, PreferenceSource preferenceSource, OrderRepository orderRepository) {
        this.fragment = fragment;
        this.preferenceSource = preferenceSource;
        this.orderRepository = orderRepository;
    }

    public void getOrderList(String type, String state) {
        index = 0;
        orderRepository.getOrderList("0", preferenceSource.getId(), type, "all", index, size, state)
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        Log.e("vd", stringBaseEntity.toString());
                        if (stringBaseEntity.getCode() == 1) {
                            List<OrderEntity> orderEntityList = Arrays.asList(new Gson().fromJson(stringBaseEntity.getResult(), OrderEntity[].class));
                            fragment.refresh(orderEntityList);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("vd", throwable.getMessage());
                    }
                });
    }

    public void getOrderMoreList(String type, String state) {
        index = index + 1;
        orderRepository.getOrderList("0", preferenceSource.getId(), type, "all", index, size, state)
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        Log.e("vd", stringBaseEntity.toString());
                        if (stringBaseEntity.getCode() == 1) {
                            List<OrderEntity> orderEntityList = Arrays.asList(new Gson().fromJson(stringBaseEntity.getResult(), OrderEntity[].class));
                            fragment.loadMore(orderEntityList);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("vd", throwable.getMessage());
                    }
                });
    }

    public void getOrderDetail(String billId){
        orderRepository.getOrderDetail(preferenceSource.getId(),"9",billId)
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        Log.e("vd",stringBaseEntity.getResult());
                        if (stringBaseEntity.getCode()==1){
                            String[] result = stringBaseEntity.getResult().split("\\^");
                           detailFoods.set(Arrays.asList(new Gson().fromJson(result[0], OrderFoodEntity[].class)));
                            if (result.length > 1) {
                                List<PayTypeEntity> tPayTypes =  Arrays.asList(new Gson().fromJson(result[1], PayTypeEntity[].class));
                                initPayInfo(tPayTypes);
                            }
                            fragment.showPop();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("vd", throwable.getMessage());
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
