package com.administrator.shopkeepertablet.di.parish;


import com.administrator.shopkeepertablet.di.Activity;
import com.administrator.shopkeepertablet.model.api.ApiSource;
import com.administrator.shopkeepertablet.model.preference.PreferenceSource;
import com.administrator.shopkeepertablet.repository.parish.ParishRepertory;
import com.administrator.shopkeepertablet.repository.parish.ParishRepertoryImpl;
import com.administrator.shopkeepertablet.view.ui.activity.parish.OrderDishesActivity;
import com.administrator.shopkeepertablet.view.ui.activity.parish.PayActivity;
import com.administrator.shopkeepertablet.view.ui.activity.parish.TableActivity;
import com.administrator.shopkeepertablet.viewmodel.OrderDishesViewModel;
import com.administrator.shopkeepertablet.viewmodel.PayViewModel;
import com.administrator.shopkeepertablet.viewmodel.TableViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Description:
 * Author CC
 * Time 2018/6/14
 */
@Module
public class ParishActivityModule {
    private OrderDishesActivity orderDishesActivity;
    private TableActivity tableActivity;
    private PayActivity payActivity;


    public ParishActivityModule(OrderDishesActivity orderDishesActivity) {
        this.orderDishesActivity = orderDishesActivity;
    }

    public ParishActivityModule(TableActivity tableActivity) {
        this.tableActivity = tableActivity;
    }

    public ParishActivityModule(PayActivity payActivity) {
        this.payActivity = payActivity;
    }

    @Provides
    @Activity
    ParishRepertory provideParishRepertory(ApiSource apiSource, PreferenceSource preferenceSource) {
        return new ParishRepertoryImpl(apiSource, preferenceSource);
    }

    @Provides
    @Activity
    OrderDishesViewModel provideOrderDishesViewModel(ParishRepertory parishRepertory, PreferenceSource preferenceSource) {
        return new OrderDishesViewModel(orderDishesActivity, parishRepertory, preferenceSource);
    }

    @Provides
    @Activity
    TableViewModel provideTableViewModel(ParishRepertory parishRepertory, PreferenceSource preferenceSource){
        return new TableViewModel(parishRepertory,preferenceSource,tableActivity);
    }

    @Provides
    @Activity
    PayViewModel providePayViewModel(ParishRepertory parishRepertory,PreferenceSource preferenceSource){
        return new PayViewModel(parishRepertory,preferenceSource,payActivity);
    }
}
