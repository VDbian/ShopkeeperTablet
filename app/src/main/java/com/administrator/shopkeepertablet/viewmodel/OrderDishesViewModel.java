package com.administrator.shopkeepertablet.viewmodel;

import android.databinding.ObservableField;
import android.util.Log;

import com.administrator.shopkeepertablet.AppApplication;
import com.administrator.shopkeepertablet.model.entity.BaseEntity;
import com.administrator.shopkeepertablet.model.entity.FoodEntity;
import com.administrator.shopkeepertablet.model.entity.FoodTypeEntity;
import com.administrator.shopkeepertablet.model.entity.FoodTypeSelectEntity;
import com.administrator.shopkeepertablet.model.entity.KouWeiEntity;
import com.administrator.shopkeepertablet.model.greendao.DaoSession;
import com.administrator.shopkeepertablet.model.preference.PreferenceSource;
import com.administrator.shopkeepertablet.repository.parish.ParishRepertory;
import com.administrator.shopkeepertablet.utils.MToast;
import com.administrator.shopkeepertablet.utils.Print;
import com.administrator.shopkeepertablet.view.ui.activity.parish.OrderDishesActivity;

import java.util.ArrayList;
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
    private DaoSession dao;
    public ObservableField<String> room = new ObservableField<>("");
    public ObservableField<String> table = new ObservableField<>("");
    public ObservableField<String> tableId = new ObservableField<>("");
    public ObservableField<String> people = new ObservableField<>("1");
    public ObservableField<String> tableware = new ObservableField<>("1");
    public ObservableField<String> billId = new ObservableField<>("");
    public ObservableField<String> time = new ObservableField<>("");
    public ObservableField<Double> price = new ObservableField<>(0.00);
    private Print print;


    public OrderDishesViewModel(OrderDishesActivity activity, ParishRepertory repertory, PreferenceSource preferenceSource) {
        this.activity = activity;
        this.repertory = repertory;
        this.preferenceSource = preferenceSource;
        this.dao = AppApplication.get(activity).getDaoSession();
        this.print = new Print(repertory);
    }

    private void printResult(final String result) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                print.socketDataArrivalHandler(result);
            }
        }).start();
    }

    public void getFoodList() {
        List<FoodEntity> foodEntities = dao.getFoodEntityDao().loadAll();
        if (foodEntities.size() > 0) {
            activity.refreshVariety(foodEntities);
        } else {
            MToast.showToast(activity, "请先刷新菜品");
        }
    }


    public void getFoodType() {
        List<FoodTypeEntity> foodTypeEntities = dao.getFoodTypeEntityDao().loadAll();
        List<FoodTypeSelectEntity> selectList = new ArrayList<>();
        for (int i = 0; i < foodTypeEntities.size(); i++) {
            FoodTypeSelectEntity selectEntity = new FoodTypeSelectEntity();
//            if (i==0){
//                selectEntity.setSelect(true);
//            }else {
            selectEntity.setSelect(false);
//            }
            selectEntity.setFoodTypeEntity(foodTypeEntities.get(i));
            selectList.add(selectEntity);
        }
        activity.refreshFoodType(selectList);
//        for (FoodTypeEntity foodTypeEntity:foodTypeEntities){
//            MLog.e("api",foodTypeEntity.toString());
//        }

    }

    public void order(String info,String foodType,String fanBill){
        Log.e("info",info);

        repertory.order("6", preferenceSource.getId(), tableId.get(), billId.get(), info, preferenceSource.getUserId(), preferenceSource.getName(),
                table.get(),String.valueOf(price.get()), foodType, fanBill).subscribe(new Consumer<BaseEntity<String>>() {
            @Override
            public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                Log.e("vd",stringBaseEntity.toString());
                if (stringBaseEntity.getCode()==1){
                    MToast.showToast(activity,"下单成功");
                    printResult(stringBaseEntity.getResult());
                    activity.finish();
                }else {
                    MToast.showToast(activity,"下单失败");
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                MToast.showToast(activity,"下单失败");
            }
        });
    }

    public void  getAllKouwei(){
        List<KouWeiEntity> list = AppApplication.get(activity).getDaoSession().getKouWeiEntityDao().loadAll();
        activity.showPopAllKouwei(list);
    }

}
