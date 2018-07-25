package com.administrator.shopkeepertablet.viewmodel;

import android.databinding.ObservableField;

import com.administrator.shopkeepertablet.AppApplication;
import com.administrator.shopkeepertablet.model.entity.FoodEntity;
import com.administrator.shopkeepertablet.model.entity.FoodTypeEntity;
import com.administrator.shopkeepertablet.model.entity.FoodTypeSelectEntity;
import com.administrator.shopkeepertablet.model.entity.KouWeiEntity;
import com.administrator.shopkeepertablet.model.greendao.DaoSession;
import com.administrator.shopkeepertablet.model.preference.PreferenceSource;
import com.administrator.shopkeepertablet.repository.fast.FastRepository;
import com.administrator.shopkeepertablet.utils.MToast;
import com.administrator.shopkeepertablet.utils.Print;
import com.administrator.shopkeepertablet.view.ui.fragment.FastFoodFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Author CC
 * Time 2018/7/23
 */


public class FastViewModel extends BaseViewModel {
    private FastFoodFragment fragment;
    private PreferenceSource preferenceSource;
    private FastRepository fastRepository;

    private DaoSession dao;
    private Print print;
    public ObservableField<Double> price =new ObservableField<>(0.0);

    public FastViewModel(FastFoodFragment fragment, PreferenceSource preferenceSource, FastRepository fastRepository) {
        this.fragment = fragment;
        this.preferenceSource = preferenceSource;
        this.fastRepository = fastRepository;
        this.print = new Print(fastRepository);
        this.dao = AppApplication.get(fragment.getActivity()).getDaoSession();
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
            fragment.refreshVariety(foodEntities);
        } else {
            MToast.showToast(fragment.getActivity(), "请先刷新菜品");
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
        fragment.refreshFoodType(selectList);
//        for (FoodTypeEntity foodTypeEntity:foodTypeEntities){
//            MLog.e("api",foodTypeEntity.toString());
//        }

    }
//
//    public void order(String info,String foodType,String fanBill){
//        Log.e("info",info);
//
//        repertory.order("6", preferenceSource.getId(), tableId.get(), billId.get(), info, preferenceSource.getUserId(), preferenceSource.getName(),
//                table.get(),String.valueOf(price.get()), foodType, fanBill).subscribe(new Consumer<BaseEntity<String>>() {
//            @Override
//            public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
//                Log.e("vd",stringBaseEntity.toString());
//                if (stringBaseEntity.getCode()==1){
//                    MToast.showToast(activity,"下单成功");
//                    printResult(stringBaseEntity.getResult());
//                    activity.finish();
//                }else {
//                    MToast.showToast(activity,"下单失败");
//                }
//            }
//        }, new Consumer<Throwable>() {
//            @Override
//            public void accept(Throwable throwable) throws Exception {
//                MToast.showToast(activity,"下单失败");
//            }
//        });
//    }

    public void  getAllKouwei(){
        List<KouWeiEntity> list = AppApplication.get(fragment.getActivity()).getDaoSession().getKouWeiEntityDao().loadAll();
        fragment.showPopAllKouwei(list);
    }
}
