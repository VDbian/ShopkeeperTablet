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
}
