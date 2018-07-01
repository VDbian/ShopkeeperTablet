package com.administrator.shopkeepertablet.repository.parish;

import com.administrator.shopkeepertablet.model.entity.BaseEntity;
import com.administrator.shopkeepertablet.model.entity.ResultFoodEntity;
import com.administrator.shopkeepertablet.repository.BaseRepertory;

import io.reactivex.Observable;

/**
 * Description:
 * Author CC
 * Time 2018/7/1
 */


public interface ParishRepertory extends BaseRepertory {
    /**
     * 获取店铺菜单
     * @param id 店铺ID
     * @return
     */
    Observable<ResultFoodEntity> getFoodList(String id);
}
