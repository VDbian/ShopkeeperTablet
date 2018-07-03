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
     * 获取房间列表
     * @param type
     * @param id 店铺id
     * @return
     */
    Observable<BaseEntity<String>> getRooms(String type,String id);

    /**
     * 获取桌号信息
     * @param type
     * @param leibie
     * @param id
     * @param Pindex
     * @param Psize
     * @return
     */
    Observable<BaseEntity<String>> getTables(String type,String leibie, String id, int Pindex, int Psize);

    /**
     * 获取菜单列表
     * @param type 0
     * @param id 店铺id
     * @return
     */
    Observable<ResultFoodEntity> getFoodList(String type,String id);
}
