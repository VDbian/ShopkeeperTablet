package com.administrator.shopkeepertablet.repository.setting;

import com.administrator.shopkeepertablet.model.entity.ResultFoodEntity;
import com.administrator.shopkeepertablet.repository.BaseRepertory;

import io.reactivex.Observable;

/**
 * Description:
 * Author CC
 * Time 2018/7/10
 */


public interface SettingRepertory extends BaseRepertory {


    /**
     * 获取菜单列表
     * @param type 0
     * @param id 店铺id
     * @return
     */
    Observable<ResultFoodEntity> getFoodList(String type, String id);

    /**
     * 获取套餐
     * @param type 2
     * @param id 店铺id
     * @param index 页数
     * @param size 每页数量
     * @return
     */
    Observable<ResultFoodEntity> getComboList(String type, String id, int index, int size);
}
