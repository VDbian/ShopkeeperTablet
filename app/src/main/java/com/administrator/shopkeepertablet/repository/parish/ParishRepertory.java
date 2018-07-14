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

}
