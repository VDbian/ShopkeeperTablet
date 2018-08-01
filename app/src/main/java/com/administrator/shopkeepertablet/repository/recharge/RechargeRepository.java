package com.administrator.shopkeepertablet.repository.recharge;

import com.administrator.shopkeepertablet.model.entity.BaseEntity;
import com.administrator.shopkeepertablet.repository.BaseRepertory;

import io.reactivex.Observable;

/**
 * Description:
 * Author chencheng
 * Time 2018/7/25
 */

public interface RechargeRepository extends BaseRepertory {

    /**
     * 获取会员列表
     * @param type 1
     * @param id 店铺Id
     * @param pageIndex 页数
     * @param pageSize 每页数量
     * @param name 名字
     * @param phone 电话
     * @return
     */
    Observable<BaseEntity<String>> getRechargeMember(String type, String id, String pageIndex, String pageSize, String name, String phone);

    /**
     * 获取充值类别
     * @param type 6
     * @param id 店铺Id
     * @return
     */
    Observable<BaseEntity<String>> getRecharge(String type,String id);

    /**
     * 新增会员
     * @param type 8
     * @param shopId 店铺Id
     * @param staffTel 电话
     * @param staffDepart 名字
     * @param staffLanguage 操作员
     * @param staffCatalogue 操作员ID
     * @return
     */
    Observable<BaseEntity<String>> addRecharge(String type,String shopId,String staffTel,String staffDepart,String staffLanguage,String staffCatalogue);

    /**
     * 验证校验码
     * @param type 10
     * @param shopId 店铺Id
     * @param passWord 校验码
     * @return
     */
    Observable<BaseEntity<String>> checkCode(String type,String shopId,String passWord);

    /**
     * 自定义充值
     * @param type 9
     * @param userID 会员Id
     * @param shopId 店铺Id
     * @param price 金额
     * @param payType 支付方式
     * @param operaName 操作员
     * @param operaId 操作员Id
     * @return
     */
    Observable<BaseEntity<String>> moneyCharge(String type,String userID,String shopId,String price,int payType,String operaName,String operaId);

    /**
     * 产品充值
     * @param type 7
     * @param userID 会员Id
     * @param shopId 店铺Id
     * @param cardID 充值方案Id
     * @param payType 支付方式
     * @param operaName 操作员
     * @param operaId 操作员Id
     * @return
     */
    Observable<BaseEntity<String>> productCharge(String type,String userID,String shopId,String cardID,int payType,String operaName,String operaId);
}
