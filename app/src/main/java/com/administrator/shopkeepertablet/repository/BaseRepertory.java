package com.administrator.shopkeepertablet.repository;

import com.administrator.shopkeepertablet.model.entity.BaseEntity;

import io.reactivex.Observable;

/**
 * Description:
 * Author CC
 * Time 2018/6/11
 */
public interface BaseRepertory {
    /**
     * 获取房间列表
     * @param type 1
     * @param id 店铺id
     * @param index 当前页
     * @param size 每页显示数量
     * @return
     */
    Observable<BaseEntity<String>> getRooms(String type, String id, int index, int size);

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
     * 会员搜索
     * @param type 15
     * @param phone 会员号
     * @param billId 订单号，多个订单号用，隔开
     * @param id 店铺Id
     * @return
     */
    Observable<BaseEntity<String>> getMember(String type,String phone,String billId,String id);


    Observable<BaseEntity<String>> updatePrint(String type, String billId,String ipAddress);
}
