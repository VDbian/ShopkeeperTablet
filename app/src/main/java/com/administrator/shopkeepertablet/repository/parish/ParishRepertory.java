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
     * @param type 1
     * @param id 店铺id
     * @param index 当前页
     * @param size 每页显示数量
     * @return
     */
    Observable<BaseEntity<String>> getRooms(String type,String id,int index, int size);

    /**
     * 获取桌号信息
     * @param type 0
     * @param leibie 房间类型ID
     * @param id 商店ID
     * @param index 当前页
     * @param size 每页数量
     * @return
     */
    Observable<BaseEntity<String>> getTables(String type,String leibie, String id, int index, int size);


    /**
     *
     * @param type 类型 1
     * @param tableId 桌位id
     * @param tableName 桌位名称
     * @param id 店铺id
     * @param people 用餐数
     * @param ware 餐具
     * @param userId 收银员Id
     * @param name 收银员名字
     * @return
     */
    Observable<BaseEntity<String>> openTable(String type, String tableId, String tableName, String id, String people, String ware, String userId, String name);


    /**
     *
     * @param type  类型 3
     * @param tableId 桌位ID
     * @param billId 开台ID
     * @param id 店铺ID
     * @return
     */
    Observable<BaseEntity<String>> clearTable(String type,String tableId,String billId, String id);



    /**
     *
     * @param type 类型 1
     * @param id 店铺ID
     * @param index 当前页
     * @param size 每页数量
     * @return
     */
    Observable<BaseEntity<String>> getFoodTypeList(String type, String id, int index, int size);
}
