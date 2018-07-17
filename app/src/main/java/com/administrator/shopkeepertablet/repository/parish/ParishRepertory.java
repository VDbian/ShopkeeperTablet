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
     *开台
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
     *清台
     * @param type  类型 3
     * @param tableId 桌位ID
     * @param billId 开台ID
     * @param id 店铺ID
     * @return
     */
    Observable<BaseEntity<String>> clearTable(String type,String tableId,String billId, String id);


    /**
     * 获取菜品类别
     * @param type 类型 1
     * @param id 店铺ID
     * @param index 当前页
     * @param size 每页数量
     * @return
     */
    Observable<BaseEntity<String>> getFoodTypeList(String type, String id, int index, int size);

    /**
     * 下单
     * @param type 6
     * @param id 店铺Id
     * @param tableId 桌位ID
     * @param billId 开台ID
     * @param info 菜品字符串  菜品名称@ 会员价格@  状态@ 单价$  菜品ID$ 单价$ 份数$  口味$  口味ID$  份数$ 总价$ 规格$ 规格ID
     * @param UserId 操作人ID
     * @param name 操作人
     * @param tableName 桌位ID
     * @param price 实际支付价格
     * @param foodType 是否加菜
     * @param fanBill 订单ID
     * @return
     */
    Observable<BaseEntity<String>> order(String type, String id, String tableId, String billId, String info, String UserId, String name, String tableName, String price, String foodType, String fanBill);

    /**
     *获取订单菜品列表
     * @param type 13
     * @param id 店铺ID
     * @param billId 订单ID
     * @return
     */
    Observable<BaseEntity<String>> getOrderFoodList(String type, String id, String billId);

    /**
     * 获取批量品注口味
     * @param id 店铺ID
     * @param index 页数
     * @param size 每页数量
     * @return
     */
    Observable<BaseEntity<String>> getFoodKouweiList(String id,int index, int size);

    /**
     * 撤单
     * @param type 4
     * @param tableId 桌位id
     * @param billId 订单id
     * @param id 店铺ID
     * @param tableName 桌位名称
     * @param userName 操作员
     * @param userId 操作员Id
     * @return
     */
    Observable<BaseEntity<String>> cancelOrder(String type,String tableId,String billId,String id, String tableName, String userName,String userId );


    /**
     * 修改人数
     * @param type 2
     * @param tableId 桌位ID
     * @param peopleNum 人数
     * @param wareNum 餐具数
     * @param billId 订单Id
     * @param id 店铺ID
     * @return
     */
    Observable<BaseEntity<String>> changePeople(String type,String tableId, String peopleNum, String wareNum,String billId,String id);

    /**
     * 换桌
     * @param type 2
     * @param newTableId 换到的桌位ID
     * @param newTableName 换到的桌位名字
     * @param oldTableId 之前的桌位ID
     * @param billId 之前的订单ID
     * @return
     */
    Observable<BaseEntity<String>> changeTable(String type,String newTableId,String newTableName,String oldTableId , String billId );


    /**
     * 催菜
     * @param type 2
     * @param detailId 订单详情ID
     * @param billId 订单ID
     * @param id 店铺ID
     * @param tableId 桌位ID
     * @param name 操作员
     * @param tableName 桌位名称
     * @return
     */
    Observable<BaseEntity<String>> pushFood(String type, String detailId,String billId, String id, String tableId,String name, String tableName);

}
