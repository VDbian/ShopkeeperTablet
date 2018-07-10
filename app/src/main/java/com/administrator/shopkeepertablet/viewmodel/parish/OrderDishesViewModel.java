package com.administrator.shopkeepertablet.viewmodel.parish;

import android.databinding.ObservableField;

import com.administrator.shopkeepertablet.AppConstant;
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

import javax.xml.transform.sax.TemplatesHandler;

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
    public ObservableField<String> room = new ObservableField<>("");
    public ObservableField<String> table = new ObservableField<>("");
    public ObservableField<String> tableId = new ObservableField<>("");
    public ObservableField<String> people = new ObservableField<>("1");
    public ObservableField<String> tableware = new ObservableField<>("1");
    public ObservableField<String> time = new ObservableField<>("");


    public OrderDishesViewModel(OrderDishesActivity activity, ParishRepertory repertory,PreferenceSource preferenceSource) {
        this.activity = activity;
        this.repertory = repertory;
        this.preferenceSource = preferenceSource;
    }



    public void getFoodType(){
        repertory.getFoodTypeList("1",preferenceSource.getId(),1,100)
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        MLog.e("api",stringBaseEntity.toString());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        MLog.e("api",throwable.getMessage());
                    }
                });
    }

}
