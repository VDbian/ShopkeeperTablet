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
}
