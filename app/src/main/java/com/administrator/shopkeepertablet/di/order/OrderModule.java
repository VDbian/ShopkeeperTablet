package com.administrator.shopkeepertablet.di.order;

import com.administrator.shopkeepertablet.di.Fragment;
import com.administrator.shopkeepertablet.model.api.ApiSource;
import com.administrator.shopkeepertablet.model.preference.PreferenceSource;
import com.administrator.shopkeepertablet.repository.fast.FastRepository;
import com.administrator.shopkeepertablet.repository.fast.FastRepositoryImpl;
import com.administrator.shopkeepertablet.repository.order.OrderRepository;
import com.administrator.shopkeepertablet.repository.order.OrderRepositoryImpl;
import com.administrator.shopkeepertablet.view.ui.fragment.FastFoodFragment;
import com.administrator.shopkeepertablet.view.ui.fragment.OrderFragment;
import com.administrator.shopkeepertablet.viewmodel.fast.FastViewModel;
import com.administrator.shopkeepertablet.viewmodel.order.OrderViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Description:
 * Author CC
 * Time 2018/7/23
 */

@Module
public class OrderModule {
    private OrderFragment fragment;

    public OrderModule(OrderFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @Fragment
    OrderRepository provideOrderRepository(ApiSource apiSource, PreferenceSource preferenceSource) {
        return new OrderRepositoryImpl(apiSource, preferenceSource);
    }

    @Provides
    @Fragment
    OrderViewModel provideOrderViewModel(OrderRepository orderRepository, PreferenceSource preferenceSource) {
        return new OrderViewModel(fragment, preferenceSource,orderRepository);
    }
}
