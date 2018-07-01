package com.administrator.shopkeepertablet.viewmodel.parish;

import com.administrator.shopkeepertablet.model.entity.BaseEntity;
import com.administrator.shopkeepertablet.model.entity.FoodEntity;
import com.administrator.shopkeepertablet.model.entity.ResultFoodEntity;
import com.administrator.shopkeepertablet.model.preference.PreferenceSource;
import com.administrator.shopkeepertablet.repository.parish.ParishRepertory;
import com.administrator.shopkeepertablet.utils.MLog;
import com.administrator.shopkeepertablet.view.ui.activity.parish.OrderDishesActivity;
import com.administrator.shopkeepertablet.viewmodel.BaseViewModel;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Description:
 * Author CC
 * Time 2018/7/1
 */


public class OrderDishesViewModel extends BaseViewModel {

    private OrderDishesActivity activity;
    private ParishRepertory repertory;
    private PreferenceSource preferenceSource;

    public OrderDishesViewModel(OrderDishesActivity activity, ParishRepertory repertory,PreferenceSource preferenceSource) {
        this.activity = activity;
        this.repertory = repertory;
    }

    public void getFoodList(){
        repertory.getFoodList("4B176F0E-0553-4094-8181-5048641B20EF")
                .subscribe(new Consumer<ResultFoodEntity>() {
                    @Override
                    public void accept(ResultFoodEntity resultFoodEntity) throws Exception {
                        MLog.e("api",resultFoodEntity.toString());
//                        ResultFoodEntity resultFoodEntity = new Gson().fromJson(baseEntity.getResult(),ResultFoodEntity.class);
                        List<FoodEntity> foodEntities = Arrays.asList(new Gson().fromJson(resultFoodEntity.getResult().getFood(),FoodEntity[].class));
                        activity.refreshVariety(foodEntities);
//                        MLog.e("api",foodEntities.size()+"");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        MLog.e("api",throwable.getMessage());
                    }
                });
    }

}
