package com.administrator.shopkeepertablet.viewmodel;

import android.app.Application;
import android.databinding.ObservableField;
import android.text.TextUtils;
import android.util.Log;

import com.administrator.shopkeepertablet.AppApplication;
import com.administrator.shopkeepertablet.model.entity.FoodEntity;
import com.administrator.shopkeepertablet.model.entity.FoodTypeEntity;
import com.administrator.shopkeepertablet.model.entity.KouWeiEntity;
import com.administrator.shopkeepertablet.model.entity.ProductKouWeiEntity;
import com.administrator.shopkeepertablet.model.entity.ResultFoodEntity;
import com.administrator.shopkeepertablet.model.entity.SeasonEntity;
import com.administrator.shopkeepertablet.model.entity.SpecEntity;
import com.administrator.shopkeepertablet.model.greendao.DaoSession;
import com.administrator.shopkeepertablet.model.preference.PreferenceSource;
import com.administrator.shopkeepertablet.repository.setting.SettingRepertory;
import com.administrator.shopkeepertablet.utils.MLog;
import com.administrator.shopkeepertablet.utils.MToast;
import com.administrator.shopkeepertablet.view.ui.activity.setting.SettingActivity;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Description:
 * Author CC
 * Time 2018/7/10
 */


public class SettingViewModel extends BaseViewModel {

    private SettingActivity activity;
    private SettingRepertory repertory;
    private PreferenceSource preferenceSource;

    public ObservableField<String> id = new ObservableField<>("");

    public SettingViewModel(SettingActivity activity, SettingRepertory repertory, PreferenceSource preferenceSource) {
        this.activity = activity;
        this.repertory = repertory;
        this.preferenceSource = preferenceSource;
        this.id.set(this.preferenceSource.getId());
    }

    public void setPreferenceId() {
        if (TextUtils.isEmpty(id.get())) {
            MToast.showToast(activity, "店铺ID为空");
        } else {
            preferenceSource.setId(id.get());
            MToast.showToast(activity, "店铺ID已保存");
        }
    }

    public void getFoodList() {
        repertory.getFoodList("0", preferenceSource.getId())
                .subscribe(new Consumer<ResultFoodEntity>() {
                    @Override
                    public void accept(ResultFoodEntity resultFoodEntity) throws Exception {
                        if (resultFoodEntity.getCode().equals("1")) {
                            DaoSession mDao = AppApplication.get(activity).getDaoSession();
                            if (!TextUtils.isEmpty(resultFoodEntity.getResult().getFood())) {
                                List<FoodEntity> foodEntities = Arrays.asList(new Gson().fromJson(resultFoodEntity.getResult().getFood(), FoodEntity[].class));
                                mDao.getFoodEntityDao().insertOrReplaceInTx(foodEntities);
                            }
                            if (!TextUtils.isEmpty(resultFoodEntity.getResult().getSpec())) {
                                List<SpecEntity> specEntities = Arrays.asList(new Gson().fromJson(resultFoodEntity.getResult().getSpec(), SpecEntity[].class));
                                mDao.getSpecEntityDao().insertOrReplaceInTx(specEntities);
                            }
                            if (!TextUtils.isEmpty(resultFoodEntity.getResult().getKouWei())) {
                                List<KouWeiEntity> kouWeiEntities = Arrays.asList(new Gson().fromJson(resultFoodEntity.getResult().getKouWei(), KouWeiEntity[].class));
                                mDao.getKouWeiEntityDao().insertOrReplaceInTx(kouWeiEntities);
                            }
                            if (!TextUtils.isEmpty(resultFoodEntity.getResult().getProductKouWei())) {
                                List<ProductKouWeiEntity> productKouWeiEntities = Arrays.asList(new Gson().fromJson(resultFoodEntity.getResult().getProductKouWei(), ProductKouWeiEntity[].class));
                                mDao.getProductKouWeiEntityDao().insertOrReplaceInTx(productKouWeiEntities);
                            }
                            if (!TextUtils.isEmpty(resultFoodEntity.getResult().getSeason())) {
                                List<SeasonEntity> seasonEntities = Arrays.asList(new Gson().fromJson(resultFoodEntity.getResult().getSeason(), SeasonEntity[].class));
                                mDao.getSeasonEntityDao().insertOrReplaceInTx(seasonEntities);

                            }
                            if (!TextUtils.isEmpty(resultFoodEntity.getResult().getFoodType())) {
                                List<FoodTypeEntity> foodTypeEntities = Arrays.asList(new Gson().fromJson(resultFoodEntity.getResult().getFoodType(), FoodTypeEntity[].class));
                                mDao.getFoodTypeEntityDao().insertOrReplaceInTx(foodTypeEntities);
                            }
                            getComboList();
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        MToast.showToast(activity, "菜品刷新失败");
                        MLog.e("api", throwable.getMessage());
                    }
                });
    }

    public void getComboList() {
        repertory.getComboList("2", preferenceSource.getId(), 1, 100)
                .subscribe(new Consumer<ResultFoodEntity>() {
                    @Override
                    public void accept(ResultFoodEntity resultFoodEntity) throws Exception {
                        if (resultFoodEntity.getCode().equals("1")) {
                            DaoSession mDao = AppApplication.get(activity).getDaoSession();
                            if (!TextUtils.isEmpty(resultFoodEntity.getResult().getFood())) {
                                List<FoodEntity> foodEntities = Arrays.asList(new Gson().fromJson(resultFoodEntity.getResult().getFood(), FoodEntity[].class));
                                mDao.getFoodEntityDao().insertOrReplaceInTx(foodEntities);
                            }
                            if (!TextUtils.isEmpty(resultFoodEntity.getResult().getSpec())) {
                                List<SpecEntity> specEntities = Arrays.asList(new Gson().fromJson(resultFoodEntity.getResult().getSpec(), SpecEntity[].class));
                                mDao.getSpecEntityDao().insertOrReplaceInTx(specEntities);
                            }
                            if (!TextUtils.isEmpty(resultFoodEntity.getResult().getKouWei())) {
                                List<KouWeiEntity> kouWeiEntities = Arrays.asList(new Gson().fromJson(resultFoodEntity.getResult().getKouWei(), KouWeiEntity[].class));
                                mDao.getKouWeiEntityDao().insertOrReplaceInTx(kouWeiEntities);
                            }
                            if (!TextUtils.isEmpty(resultFoodEntity.getResult().getProductKouWei())) {
                                List<ProductKouWeiEntity> productKouWeiEntities = Arrays.asList(new Gson().fromJson(resultFoodEntity.getResult().getProductKouWei(), ProductKouWeiEntity[].class));
                                mDao.getProductKouWeiEntityDao().insertOrReplaceInTx(productKouWeiEntities);
                            }
                            if (!TextUtils.isEmpty(resultFoodEntity.getResult().getSeason())) {
                                List<SeasonEntity> seasonEntities = Arrays.asList(new Gson().fromJson(resultFoodEntity.getResult().getSeason(), SeasonEntity[].class));
                                mDao.getSeasonEntityDao().insertOrReplaceInTx(seasonEntities);

                            }
                            if (!TextUtils.isEmpty(resultFoodEntity.getResult().getFoodType())) {
                                List<FoodTypeEntity> foodTypeEntities = Arrays.asList(new Gson().fromJson(resultFoodEntity.getResult().getFoodType(), FoodTypeEntity[].class));
                                mDao.getFoodTypeEntityDao().insertOrReplaceInTx(foodTypeEntities);
                            }
                            MToast.showToast(activity, "菜品已刷新");
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        MToast.showToast(activity, "菜品刷新失败");
                        MLog.e("api", throwable.getMessage());
                    }
                });
    }
}
