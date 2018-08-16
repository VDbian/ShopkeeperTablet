package com.administrator.shopkeepertablet.viewmodel;

import android.databinding.ObservableField;
import android.util.Log;

import com.administrator.shopkeepertablet.AppApplication;
import com.administrator.shopkeepertablet.model.entity.BaseEntity;
import com.administrator.shopkeepertablet.model.entity.FoodEntity;
import com.administrator.shopkeepertablet.model.entity.FoodTypeEntity;
import com.administrator.shopkeepertablet.model.entity.FoodTypeSelectEntity;
import com.administrator.shopkeepertablet.model.entity.KouWeiEntity;
import com.administrator.shopkeepertablet.model.entity.OrderEntity;
import com.administrator.shopkeepertablet.model.entity.OrderFoodEntity;
import com.administrator.shopkeepertablet.model.greendao.DaoSession;
import com.administrator.shopkeepertablet.model.preference.PreferenceSource;
import com.administrator.shopkeepertablet.repository.BaseRepertory;
import com.administrator.shopkeepertablet.repository.fast.FastRepository;
import com.administrator.shopkeepertablet.utils.MToast;
import com.administrator.shopkeepertablet.utils.Print;
import com.administrator.shopkeepertablet.view.ui.fragment.FastFoodFragment;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.functions.Consumer;

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
    public ObservableField<String> table =new ObservableField<>("");

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

    public void fastFood(String info,int type){
        fastRepository.fastFood("0","",preferenceSource.getId(),info,"","","","","",
                preferenceSource.getUserId(),preferenceSource.getName(),"",0,"",table.get(),"4",""
        ,price.get(),"0").subscribe(new Consumer<BaseEntity<String>>() {
            @Override
            public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                Log.e("vd",stringBaseEntity.toString());
                if (stringBaseEntity.getCode()==1){
                    fragment.fastSuccess(stringBaseEntity.getResult(),type);
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e("vd",throwable.getMessage());
            }
        });
    }

    public void reserve(String info,String name,String phone,String remark,double money){
        fastRepository.fastFood("0","",preferenceSource.getId(),info,"","",name,"",phone,
                preferenceSource.getUserId(),preferenceSource.getName(),remark,money,"","","1",""
                ,price.get(),"0").subscribe(new Consumer<BaseEntity<String>>() {
            @Override
            public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                Log.e("vd",stringBaseEntity.toString());
                if (stringBaseEntity.getCode()==1){
                    fragment.reserveSuccess(stringBaseEntity.getResult());
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e("vd",throwable.getMessage());
            }
        });
    }

    public void getPayWay(){
        fastRepository.getPay(preferenceSource.getId(),"13")
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        Log.e("vd",stringBaseEntity.toString());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("vd",throwable.getMessage());
                    }
                });
    }

    public void  getAllKouwei(){
        List<KouWeiEntity> list = AppApplication.get(fragment.getActivity()).getDaoSession().getKouWeiEntityDao().loadAll();
        fragment.showPopAllKouwei(list);
    }

}
