package com.administrator.shopkeepertablet.repository.line;

import com.administrator.shopkeepertablet.model.entity.BaseEntity;
import com.administrator.shopkeepertablet.repository.BaseRepertory;

import io.reactivex.Observable;

/**
 * Description:
 * Author chencheng
 * Time 2018/7/25
 */

public interface LineUpRepository extends BaseRepertory {

    /**
     * 获取桌位类别
     *
     * @param type 1
     * @param id   店铺Id
     * @return
     */
    Observable<BaseEntity<String>> getTableType(String type, String id);

    /**
     * 获取排队信息
     *
     * @param type   1
     * @param leibie 类别
     * @param phone  电话
     * @param shopId 店铺Id
     * @return
     */
    Observable<BaseEntity<String>> getQueue(String type, String leibie, String phone, String shopId);

    /**
     * 新增排队
     *
     * @param type     2
     * @param selvalue 类别id
     * @param shopId   店铺Id
     * @return
     */
    Observable<BaseEntity<String>> addQueue(String type, String selvalue, String shopId);

    /**
     * 绑定桌位
     *
     * @param type           3
     * @param selvalue       桌位类别Id
     * @param name           操作员
     * @param userID         操作员Id
     * @param tableId        桌位Id
     * @param tableName      桌位名称
     * @param tableWareCount 用餐人数
     * @param orderid        排号Id
     * @param shopId         店铺Id
     * @return
     */
    Observable<BaseEntity<String>> bindQueue(String type, String selvalue, String name, String userID, String tableId, String tableName, String tableWareCount, String orderid, String shopId);

    /**
     * 删除排队
     *
     * @param type    4
     * @param orderid 排队Id
     * @return
     */
    Observable<BaseEntity<String>> deleteQueue(String type, String orderid);
}
