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

    /**
     * 绑定桌位
     *
     * @param type           3
     * @param selvalue       桌位类别Id
     * @param name           操作员
     * @param userID         操作员Id
     * @param tableId        桌位Id
     * @param tableName      桌位名称
     * @param tableWareCount 用餐人数
     * @param orderid        排号Id
     * @param shopId         店铺Id
     * @return
     */
    Observable<BaseEntity<String>> bindQueue(String type, String selvalue, String name, String userID, String tableId, String tableName, String tableWareCount, String orderid, String shopId);

    /**
     * 结账
     * @param type 3
     * @param id 账单ID
     * @param rid 商家ID
     * @param memberID 会员ID
     * @param tableId 桌位ID
     * @param zon 总金额
     * @param can 餐具费
     * @param pei 配送费
     * @param daBao 打包费
     * @param types 类别
     * @param jsonQuanXian 权限json
     * @param jsonObj 积分或优惠券促销
     * @param payType 支付类型
     * @param jsonPay 组合支付
     * @param guaID 挂账ID
     * @param personMoney 会员余额
     * @param id1 操作员ID
     * @param name 操作员姓名
     * @param zonwWeight 总重量
     * @param zonPrice 总价格
     * @param zonState 总状态
     * @param personCount 人数
     * @param price 价格
     * @param tableName 桌位名字
     * @param maLing 抹零金额
     * @param rounding 四舍五入，入时的金额
     * @param free 优惠金额
     * @return
     */
    Observable<BaseEntity<String>> bill(String type,String id,String rid,String memberID,String tableId,double zon,double can,double pei,double daBao,
                                        String types,String jsonQuanXian,String jsonObj,String payType,String jsonPay,String guaID,String personMoney,String id1, String name,String zonwWeight,
                                        String zonPrice,String zonState, int personCount,double price,String tableName, double maLing,double rounding,double free);

    /**
     * 反结账
     * @param type 3
     * @param id 账单ID
     * @param rid 商家ID
     * @param memberID 会员ID
     * @param tableId 桌位ID
     * @param zon 总金额
     * @param can 餐具费
     * @param pei 配送费
     * @param daBao 打包费
     * @param types 类别
     * @param jsonObjquanxian 权限json
     * @param jsonObj 积分或优惠券促销
     * @param payType 支付类型
     * @param jsonPay 组合支付
     * @param guaID 挂账ID
     * @param personMoney 会员余额
     * @param userId 操作员ID
     * @param name 操作员姓名
     * @param zonWeight 总重量
     * @param zonPrice 总价格
     * @param zonState 总状态
     * @param price 价格
     * @param maLing 抹零金额
     * @param rounding 四舍五入，入时的金额
     * @param free 优惠金额
     * @param fnaBill 反结账Id
     * @return
     */
    Observable<BaseEntity<String>> reBill(String type,String id,String rid,String memberID,String tableId,double zon,double can,double pei, double daBao, String types,
                                          String jsonObjquanxian,String jsonObj, String payType,String jsonPay,String guaID,String personMoney,String userId,String name,String zonWeight,
                                          String zonPrice,String zonState,double free, double price,double maLing,double rounding,String fnaBill);

    /**
     * 扫码支付
     * @param type 21
     * @param code 扫码结果
     * @param price 价格
     * @param shopId 店铺ID
     * @param payId 订单ID
     * @return
     */
    Observable<BaseEntity<String>> scanBill(String type,String code,double price,String shopId,String payId);




    Observable<BaseEntity<String>> updatePrint(String type, String billId,String ipAddress);
}
