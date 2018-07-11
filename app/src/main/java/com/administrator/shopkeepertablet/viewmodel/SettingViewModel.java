package com.administrator.shopkeepertablet.viewmodel;

import android.databinding.ObservableField;
import android.text.TextUtils;
import android.util.Log;

import com.administrator.shopkeepertablet.model.entity.FoodEntity;
import com.administrator.shopkeepertablet.model.entity.FoodTypeEntity;
import com.administrator.shopkeepertablet.model.entity.KouWeiEntity;
import com.administrator.shopkeepertablet.model.entity.ProductKouWeiEntity;
import com.administrator.shopkeepertablet.model.entity.ResultFoodEntity;
import com.administrator.shopkeepertablet.model.entity.SeasonEntity;
import com.administrator.shopkeepertablet.model.entity.SpecEntity;
import com.administrator.shopkeepertablet.model.preference.PreferenceSource;
import com.administrator.shopkeepertablet.repository.setting.SettingRepertory;
import com.administrator.shopkeepertablet.utils.MLog;
import com.administrator.shopkeepertablet.utils.MToast;
import com.administrator.shopkeepertablet.view.ui.activity.setting.SettingActivity;
import com.google.gson.Gson;

import org.litepal.LitePal;

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
            MToast.showToast(activity,"店铺ID已保存");
        }
    }

    public void getFoodList() {
        repertory.getFoodList("0", preferenceSource.getId())
                .subscribe(new Consumer<ResultFoodEntity>() {
                    @Override
                    public void accept(ResultFoodEntity resultFoodEntity) throws Exception {
                        if (resultFoodEntity.getCode().equals("1")) {
                            List<FoodEntity> foodEntities = Arrays.asList(new Gson().fromJson(resultFoodEntity.getResult().getFood(), FoodEntity[].class));
                            List<SpecEntity> specEntities = Arrays.asList(new Gson().fromJson(resultFoodEntity.getResult().getSpec(), SpecEntity[].class));
                            List<KouWeiEntity> kouWeiEntities = Arrays.asList(new Gson().fromJson(resultFoodEntity.getResult().getKouWei(), KouWeiEntity[].class));
                            List<ProductKouWeiEntity> productKouWeiEntities = Arrays.asList(new Gson().fromJson(resultFoodEntity.getResult().getProductKouWei(), ProductKouWeiEntity[].class));
                            List<SeasonEntity> seasonEntities = Arrays.asList(new Gson().fromJson(resultFoodEntity.getResult().getSeason(), SeasonEntity[].class));
                            List<FoodTypeEntity> foodTypeEntities = Arrays.asList(new Gson().fromJson(resultFoodEntity.getResult().getFoodType(), FoodTypeEntity[].class));
                            LitePal.deleteAll(FoodEntity.class);
                            LitePal.deleteAll(SpecEntity.class);
                            LitePal.deleteAll(KouWeiEntity.class);
                            LitePal.deleteAll(ProductKouWeiEntity.class);
                            LitePal.deleteAll(SeasonEntity.class);
                            LitePal.deleteAll(FoodTypeEntity.class);
                            LitePal.saveAll(foodEntities);
                            LitePal.saveAll(specEntities);
                            LitePal.saveAll(kouWeiEntities);
                            LitePal.saveAll(productKouWeiEntities);
                            LitePal.saveAll(seasonEntities);
                            LitePal.saveAll(foodTypeEntities);
                            MToast.showToast(activity,"菜品已刷新");
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        MLog.e("api", throwable.getMessage());
                    }
                });
    }

}
