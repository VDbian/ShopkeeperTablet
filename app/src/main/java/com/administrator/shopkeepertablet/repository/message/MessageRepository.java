package com.administrator.shopkeepertablet.repository.message;

import com.administrator.shopkeepertablet.model.entity.BaseEntity;
import com.administrator.shopkeepertablet.repository.BaseRepertory;

import io.reactivex.Observable;

/**
 * Description:
 * Author chencheng
 * Time 2018/7/25
 */

public interface MessageRepository extends BaseRepertory {

    /**
     * 获取消息列表
     * @param type 8
     * @param id 店铺ID
     * @param leibie 订单类别
     * @param status 状态
     * @param phone 电话
     * @param index 页数
     * @param size 每页数量
     * @return
     */
    Observable<BaseEntity<String>> getMessage(String type, String id, String leibie, String status, String phone, int index, int size);


    /**
     * 获取订单详情
     * @param id 店铺ID
     * @param type 9
     * @param billId 订单ID
     * @return
     */
    Observable<BaseEntity<String>> getOrderDetail(String id, String type,String billId);

    /**
     * 确认
     * @param type 1
     * @param shopID 店铺Id
     * @param orderId 订单Id
     * @param billId 结账ID
     * @param types 类型 3快餐 4外卖
     * @return
     */
    Observable<BaseEntity<String>> confirm(String type,String shopID,String orderId,String billId,String types);

    /**
     * 取消
     * @param type 2
     * @param shopID 店铺Id
     * @param orderId 订单Id
     * @return
     */
    Observable<BaseEntity<String>> cancel(String type,String shopID,String orderId);

    /**
     * 绑定桌位
     * @param type 4
     * @param orderId 订单Id
     * @param tableId 桌位Id
     * @param id 店铺Id
     * @param tableWareCount 餐具数
     * @param name 操作员
     * @param tableName 桌位Id
     * @return
     */
    Observable<BaseEntity<String>> bindTable(String type,String orderId,String tableId,String id,String tableWareCount,String name,String tableName);

}
