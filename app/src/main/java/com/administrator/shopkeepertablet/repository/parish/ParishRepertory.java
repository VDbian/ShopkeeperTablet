package com.administrator.shopkeepertablet.repository.parish;

import com.administrator.shopkeepertablet.model.entity.BaseEntity;
import com.administrator.shopkeepertablet.repository.BaseRepertory;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

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
    Observable<BaseEntity<String>> order(String type, String id, String tableId, String billId, String info, String UserId, String name, String tableName, String price, String foodType,String tableWareCount, String fanBill);

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


    /**
     * 整单催菜"1","1",preferenceSource.getId(),billId.get(),tableId.get(),table.get(),"2","1","2"
     * @param type 类型 1
     * @param printSource 是否本地打印 1
     * @param id 店铺Id
     * @param billId 订单ID
     * @param tableId 桌位ID
     * @param tableName 桌位名称
     * @param foodType 2
     * @param name 1
     * @param state 2
     * @return
     */
    Observable<BaseEntity<String>> pushFoodAll( String type,String printSource, String id, String billId, String tableId, String tableName, String foodType, String name, String state);


    /**
     * 商品打印//"1", "1", App.INSTANCE().getShopID(), billid, tableId, tableName, personcount, "6", "0", App.INSTANCE().getUser().getName()
     * @param type 类型 1
     * @param printSource 本地打印 1
     * @param id 店铺ID
     * @param billId 订单Id
     * @param tableId 桌位ID
     * @param tableName 桌位名称
     * @param personCount 人数
     * @param state 状态 6
     * @param foodType 0
     * @param name 操作员
     * @return
     */
    Observable<BaseEntity<String>> printAfter(String type,String printSource,String id,String billId,String tableId,String tableName,String personCount, String state,String foodType,String name);

    /**
     * 获取退菜原因列表
     * @param id 店铺id
     * @param type 7
     * @return
     */
    Observable<BaseEntity<String>> getReason(String id,String type);

    /**
     * 赠菜
     * @param type 14
     * @param detailId 订单详情ID
     * @param sum 赠送数量
     * @return
     */
    Observable<BaseEntity<String>> givingFood(String type,String detailId,String sum);

    /**
     * 退菜
     * @param id 店铺ID
     * @param type 3
     * @param detailId 订单详情ID
     * @param billId 订单Id
     * @param tableId 桌位ID
     * @param name 操作人
     * @param tableName 桌位名称
     * @param count 剩余数量
     * @param price 价格
     * @param tuiCount 退菜数量
     * @param zenCount 赠送数量
     * @param remark 退菜自定义原因
     * @param reasonId 退菜原因ID
     * @param reasonName 退菜原因
     * @return
     */
    Observable<BaseEntity<String>> returnFood(String id,String type,String detailId,String billId,String tableId, String name,String tableName,String count,String price,String tuiCount,String zenCount,String remark, String reasonId, String reasonName);

    /**
     * 取消结账状态
     * @param type 19
     * @param billId 订单Id
     * @return
     */
    Observable<BaseEntity<String>> cancelPay(String type,String billId);

    /**
     * 转菜
     * @param type 2
     * @param id 店铺ID
     * @param tableId 转到桌位id
     * @param detailId 菜品ID
     * @return
     */
    Observable<BaseEntity<String>> TransferFood(String type,String id,String tableId,String detailId);


    /**
     * 搜索优惠券
     * @param type 2
     * @param billId 订单ID
     * @param discountNum 优惠券号码
     * @return
     */
    Observable<BaseEntity<String>> getDiscount(String type,String billId,String discountNum);

    /**
     * 获取餐具价格
     * @param type 6
     * @param id 店铺Id
     * @return
     */
    Observable<BaseEntity<String>> getWarePrice(String type,String id);

    /**
     * 反结账
     * @param type 0
     * @param id 店铺Id
     * @param foodInfo 菜品信息
     * @param userId 操作人ID
     * @param name 操作员
     * @param tableId 桌位Id
     * @param tableName 桌位名称
     * @param types 4
     * @param price 价格
     * @param billType 0
     * @param fanBill 订单Id
     * @return
     */
    Observable<BaseEntity<String>> reBillTangDian(String type,String id,String foodInfo, String userId,String name,String tableId,String tableName,String types,double price,String billType,String fanBill);

    /**
     * 获取打折列表
     * @param shopID 店铺Id
     * @param type 12
     * @return
     */
    Observable<BaseEntity<String>> getDiscountList(String shopID,String type);

    /**
     * 权限打折
     * @param s 24
     * @param billid 订单Id
     * @param shopId 店铺Id
     * @param chengdazhe
     * @param dazhe 折扣数
     * @param daId 打折ID
     * @return
     */
    Observable<BaseEntity<String>> getDazhe(String s,String billid,String shopId,String chengdazhe,int dazhe,String daId);

    /**
     * 获取其他优惠列表
     * @param type 5
     * @param shopId 店铺ID
     * @param pageSize 每页条数
     * @param pageIndex 页码
     * @param product 模糊查询  传空
     * @return
     */
    Observable<BaseEntity<String>> getLineDownInfo(String type,String shopId,int pageSize,int pageIndex,String product);

    /**
     * 提交其他优惠
      * @param type 23
     * @param couponId 其他优惠卷ID
     * @param billId 订单ID
     * @param shopId 店铺ID
     * @param xiaPrice 优惠金额
     * @param yinFu 应付金额
     * @param json 权限json
     * @param json 权限json
     * @return
     */
    Observable<BaseEntity<String>> getOherYouhui(String type,String couponId,String billId,String shopId,double xiaPrice,double yinFu,String json);

    /** 获取订单价格相关
     * @param type 17
     * @param shopId 店铺ID
     * @param billId 订单ID
     * @param types 类型
     * @return
     */
    Observable<BaseEntity<String>> getOrderData(String type,String shopId,String billId,String types);

    /**
     * 订单查询
     * @param type 10
     * @param id 店铺Id
     * @param OrderID 订单Id
     * @return
     */
    Observable<BaseEntity<String>> query(String type,String id,String OrderID);

    /**
     * 获取挂账信息
     * @param shopID 店铺Id
     * @param type 17
     * @return
     */
    Observable<BaseEntity<String>> getGuazhangData(String shopID,String type);

    /**
     * 获取并单桌位信息
     * @param s 12
     * @param shopID 店铺Id
     * @param tableId 桌位ID 多个桌位用,隔开
     * @return
     */
    Observable<BaseEntity<String>> getMergeOrderList(String s,String shopID,String tableId);

    /**
     * 账单预打
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
