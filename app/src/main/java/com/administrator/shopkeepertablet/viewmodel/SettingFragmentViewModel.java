package com.administrator.shopkeepertablet.viewmodel;

import android.app.Application;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.administrator.shopkeepertablet.AppApplication;
import com.administrator.shopkeepertablet.AppConstant;
import com.administrator.shopkeepertablet.model.entity.BaseEntity;
import com.administrator.shopkeepertablet.model.entity.FoodEntity;
import com.administrator.shopkeepertablet.model.entity.FoodTypeEntity;
import com.administrator.shopkeepertablet.model.entity.KouWeiEntity;
import com.administrator.shopkeepertablet.model.entity.ProductKouWeiEntity;
import com.administrator.shopkeepertablet.model.entity.ResultFoodEntity;
import com.administrator.shopkeepertablet.model.entity.SeasonEntity;
import com.administrator.shopkeepertablet.model.entity.SocketEntity;
import com.administrator.shopkeepertablet.model.entity.SpecEntity;
import com.administrator.shopkeepertablet.model.entity.UserInfoEntity;
import com.administrator.shopkeepertablet.model.greendao.DaoSession;
import com.administrator.shopkeepertablet.model.preference.PreferenceSource;
import com.administrator.shopkeepertablet.repository.setting.SettingRepertory;
import com.administrator.shopkeepertablet.utils.DialogUtils;
import com.administrator.shopkeepertablet.utils.MLog;
import com.administrator.shopkeepertablet.utils.MToast;
import com.administrator.shopkeepertablet.view.ui.activity.LoginActivity;
import com.administrator.shopkeepertablet.view.ui.fragment.SettingFragment;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Description:
 * Author chencheng
 * Time 2018/7/25
 */

public class SettingFragmentViewModel extends BaseViewModel {

    private SettingFragment fragment;
    private PreferenceSource preferenceSource;
    private SettingRepertory repertory;

    public SettingFragmentViewModel(SettingFragment fragment, PreferenceSource preferenceSource, SettingRepertory repertory) {
        this.fragment = fragment;
        this.preferenceSource = preferenceSource;
        this.repertory = repertory;
    }

    public void getFoodList() {
        DialogUtils.showDialog(fragment.getActivity(), "获取数据中");
        repertory.getFoodList("0", preferenceSource.getId())
                .subscribe(new Consumer<ResultFoodEntity>() {
                    @Override
                    public void accept(ResultFoodEntity resultFoodEntity) throws Exception {
                        if (resultFoodEntity.getCode().equals("1")) {
                            DialogUtils.hintDialog();
                            DaoSession mDao = AppApplication.get(fragment.getActivity()).getDaoSession();
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
                        DialogUtils.hintDialog();
                        MToast.showToast(fragment.getActivity(), "菜品刷新失败");
                        MLog.e("api", throwable.getMessage());
                    }
                });
    }

    public void getComboList() {
        DialogUtils.showDialog(fragment.getActivity(), "获取数据中");
        repertory.getComboList("2", preferenceSource.getId(), 1, 100)
                .subscribe(new Consumer<ResultFoodEntity>() {
                    @Override
                    public void accept(ResultFoodEntity resultFoodEntity) throws Exception {
                        DialogUtils.hintDialog();
                        if (resultFoodEntity.getCode().equals("1")) {
                            DaoSession mDao = AppApplication.get(fragment.getActivity()).getDaoSession();
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
                            MToast.showToast(fragment.getActivity(), "菜品已刷新");
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        DialogUtils.hintDialog();
                        MToast.showToast(fragment.getActivity(), "菜品刷新失败");
                        MLog.e("api", throwable.getMessage());
                    }
                });
    }

    public void setAlias() {
        AppApplication.get(fragment.getActivity()).setAlias(preferenceSource.getId(), "PHONE");
//        AppApplication.get(fragment.getActivity()).mPushAgent.addExclusiveAlias(preferenceSource.getId(), "PHONE", (b, s) ->
//                MToast.showToast(fragment.getActivity(), (b ? "绑定成功" : "绑定失败")));
    }

    public void removeAlias() {
        AppApplication.get(fragment.getActivity()).removeAlias(preferenceSource.getId(), "PHONE");
    }

    public void getSocket() {
        repertory.getSocket("6").subscribe(new Consumer<BaseEntity<String>>() {
            @Override
            public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                Log.e("vd", stringBaseEntity.toString());
                if (stringBaseEntity.getCode() == 1) {
                    SocketEntity[] socketEntities = new Gson().fromJson(stringBaseEntity.getResult(), SocketEntity[].class);
                    for (SocketEntity socketEntity : socketEntities) {
                        if (socketEntity.getVersionType().equals("A")) {
                            if (TextUtils.isEmpty(preferenceSource.getId())) {
                                MToast.showToast(fragment.getActivity(),"连接服务器失败");
                                return;
                            }
                            if (TextUtils.isEmpty(socketEntity.getAddress())) {
                                MToast.showToast(fragment.getActivity(),"连接服务器失败");
                                return;
                            }
                            if (socketEntity.getPort() <= 0) {
                                MToast.showToast(fragment.getActivity(),"连接服务器失败");
                                return;
                            }
                          fragment.connectSocket(preferenceSource.getId(), socketEntity.getAddress(), socketEntity.getPort());
//                            fragment.connectSocket(preferenceSource.getId(),"192.168.1.3", 60000);
//
                        }
                    }
                }else {
                    MToast.showToast(fragment.getActivity(),"连接服务器失败");
                }

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                MToast.showToast(fragment.getActivity(),"连接服务器失败");
            }
        });
    }

    public void logout() {
        preferenceSource.setUserId("");
        preferenceSource.setName("");
        AppConstant.setUser(new UserInfoEntity());
        Intent intent = new Intent(fragment.getActivity(), LoginActivity.class);
        fragment.getActivity().startActivity(intent);
        fragment.getActivity().finish();
    }
}
