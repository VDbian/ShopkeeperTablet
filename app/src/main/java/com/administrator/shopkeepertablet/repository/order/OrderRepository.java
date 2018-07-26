package com.administrator.shopkeepertablet.repository.order;

import com.administrator.shopkeepertablet.model.entity.BaseEntity;
import com.administrator.shopkeepertablet.repository.BaseRepertory;

import io.reactivex.Observable;

/**
 * Description:
 * Author CC
 * Time 2018/7/23
 */


public interface OrderRepository extends BaseRepertory{

    /**
     * 获取订单列表
     * @param type 0
     * @param id 店铺ID
     * @param orderType 订单类别
     * @param phone 电话
     * @param index 页数
     * @param size 每页数量
     * @param state 状态
     * @return
     */
    Observable<BaseEntity<String>> getOrderList(String type, String id, String orderType, String phone, int index, int size,String state);

    /**
     * 获取订单详情
     * @param id 店铺ID
     * @param type 9
     * @param billId 订单ID
     * @return
     */
    Observable<BaseEntity<String>> getOrderDetail(String id, String type,String billId);
}
