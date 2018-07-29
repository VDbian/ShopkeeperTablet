package com.administrator.shopkeepertablet.repository.fast;

import com.administrator.shopkeepertablet.model.entity.BaseEntity;
import com.administrator.shopkeepertablet.repository.BaseRepertory;

import io.reactivex.Observable;

/**
 * Description:
 * Author CC
 * Time 2018/7/23
 */


public interface FastRepository extends BaseRepertory{

    /**
     * 快餐、预定
     * @param type 0
     * @param mark 标记
     * @param id 店铺Id
     * @param info 菜品信息
     * @param data 外卖日期
     * @param time 外卖时间
     * @param names 收货人姓名
     * @param address 收获人地址
     * @param phone 收货人电话
     * @param userId 操作员Id
     * @param name 操作员姓名
     * @param remark 备注
     * @param money 预付款
     * @param tableId 桌位Id
     * @param tableName 桌位名称
     * @param types 1预定  2 堂点  3.外卖  4.快餐
     * @param fanBill 反结账Id
     * @param price 价格
     * @param billType
     * @return
     */
    Observable<BaseEntity<String>> fastFood(String type, String mark, String id, String info, String data, String time, String names, String address, String phone, String userId, String name, String remark, double money, String tableId, String tableName, String types, String fanBill, double price, String billType);

    /**
     * 获取支付方式
     * @param id 店铺id
     * @param type 13
     * @return
     */
    Observable<BaseEntity<String>> getPay(String id,String type);


}
