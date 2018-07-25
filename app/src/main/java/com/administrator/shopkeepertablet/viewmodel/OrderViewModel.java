package com.administrator.shopkeepertablet.viewmodel;

import android.databinding.ObservableField;
import android.util.Log;

import com.administrator.shopkeepertablet.model.entity.BaseEntity;
import com.administrator.shopkeepertablet.model.entity.OrderEntity;
import com.administrator.shopkeepertablet.model.preference.PreferenceSource;
import com.administrator.shopkeepertablet.repository.order.OrderRepository;
import com.administrator.shopkeepertablet.view.ui.fragment.OrderFragment;
import com.google.gson.Gson;

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


    public OrderViewModel(OrderFragment fragment, PreferenceSource preferenceSource, OrderRepository orderRepository) {
        this.fragment = fragment;
        this.preferenceSource = preferenceSource;
        this.orderRepository = orderRepository;
    }

    public void getOrderList(String type, String state) {
        index = 0;
        orderRepository.getOrderList("0", preferenceSource.getId(), type, "all", "all", index, size, state)
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
        orderRepository.getOrderList("0", preferenceSource.getId(), type, "all", "all", index, size, state)
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
//                            OrderEntity orderEntity = new Gson().fromJson(stringBaseEntity.getResult(),OrderEntity.class);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("vd", throwable.getMessage());
                    }
                });
    }


}
