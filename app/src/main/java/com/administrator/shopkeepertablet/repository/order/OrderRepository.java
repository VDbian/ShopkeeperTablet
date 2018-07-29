package com.administrator.shopkeepertablet.repository.order;

import com.administrator.shopkeepertablet.model.entity.BaseEntity;
import com.administrator.shopkeepertablet.repository.BaseRepertory;

import io.reactivex.Observable;
import retrofit2.http.Field;

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

    //    type	接口类型	3账单/5并单
//    id	商家ID
//    printsouce	是否本地打印	1
//    Sate	状态	7堂点 3外卖  4快餐
//    billid	订单ID	  并单以,号分割
//    Name	操作人员名称
//    personcount	人员数量
//    tableid	桌位id
//    tablename	桌位名称
//    priceold	总金额
//    price	实收金额
//    free	优惠
//    PayType	支付状态

    /**
     * 账单补打
     * @param type 3
     * @param shopID 店铺ID
     * @param printSouce 是否本地打印	1
     * @param sate 状态  7堂点 3外卖  4快餐
     * @param billId  订单Id
     * @param name 操作员
     * @param personCount 人数
     * @param tableId 桌位ID
     * @param tableName 桌位名称
     * @param priceOld 总价格
     * @param price 实收金额
     * @param free 优惠
     * @param payType 支付状态
     * @return
     */
    Observable<BaseEntity<String>> print(String type, String shopID, String printSouce, String sate, String billId, String name, int personCount, String tableId, String tableName, double priceOld, double price, @Field("free") double free, @Field("PayType") String payType);

}
