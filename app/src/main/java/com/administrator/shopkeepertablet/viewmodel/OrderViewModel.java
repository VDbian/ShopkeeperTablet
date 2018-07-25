package com.administrator.shopkeepertablet.viewmodel;

import com.administrator.shopkeepertablet.model.preference.PreferenceSource;
import com.administrator.shopkeepertablet.repository.order.OrderRepository;
import com.administrator.shopkeepertablet.view.ui.fragment.OrderFragment;

/**
 * Description:
 * Author chencheng
 * Time 2018/7/24
 */

public class OrderViewModel extends BaseViewModel {
    private OrderFragment fragment;
    private PreferenceSource preferenceSource;
    private OrderRepository orderRepository;

    public OrderViewModel(OrderFragment fragment, PreferenceSource preferenceSource, OrderRepository orderRepository) {
        this.fragment = fragment;
        this.preferenceSource = preferenceSource;
        this.orderRepository = orderRepository;
    }
}
