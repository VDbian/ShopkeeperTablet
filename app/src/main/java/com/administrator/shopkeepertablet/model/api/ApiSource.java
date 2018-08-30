package com.administrator.shopkeepertablet.model.api;

import com.administrator.shopkeepertablet.AppConstant;
import com.administrator.shopkeepertablet.model.entity.BaseEntity;
import com.administrator.shopkeepertablet.model.entity.ResultFoodEntity;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Description:
 * Author CC
 * Time 2018/6/11
 */
public interface ApiSource {

    Observable<BaseEntity<String>> login(String name, String id, String pwd);

    Observable<BaseEntity<String>> getRooms(String type, String id, int Pindex, int Psize);

    Observable<BaseEntity<String>> getTables(String type, String leibie, String id, int Pindex, int Psize);

    Observable<BaseEntity<String>> openTable(String type, String tableId, String tableName, String id, String people, String ware, String userId, String name);

    Observable<BaseEntity<String>> clearTable(String type, String tableId, String billId, String id);

    Observable<ResultFoodEntity> getFoodList(String type, String id);

    Observable<ResultFoodEntity> getComboList(String type,String id, int index,int size);

    Observable<BaseEntity<String>> getFoodTypeList(String type, String id, int index, int size);

    Observable<BaseEntity<String>> order(String type, String id, String tableId, String billId, String info, String UserId, String name, String tableName, String price, String foodType,String tableWareCount,String fanBill);

    Observable<BaseEntity<String>> getOrderFoodList(String type, String id, String billId);

    Observable<BaseEntity<String>> getFoodKouweiList(String id,int index, int size);

    Observable<BaseEntity<String>> cancelOrder(String type,String tableId,String billId,String id, String tableName, String userName,String userId );

    Observable<BaseEntity<String>> changePeople(String type,String tableId, String peopleNum, String wareNum,String billId,String id);

    Observable<BaseEntity<String>> changeTable(String type,String newTableId,String newTableName,String oldTableId , String billId );

    Observable<BaseEntity<String>> pushFood(String type, String detailId,String billId, String id, String tableId,String name, String tableName);

    Observable<BaseEntity<String>> pushFoodAll( String type,String printSource, String id, String billId, String tableId, String tableName, String foodType, String name, String state);

    Observable<BaseEntity<String>> printAfter(String type,String printSource,String id,String billId,String tableId,String tableName,String personCount, String state,String foodType,String name);

    Observable<BaseEntity<String>> getReason(String id,String type);

    Observable<BaseEntity<String>> givingFood(String type,String detailId,String sum);

    Observable<BaseEntity<String>> returnFood(String id,String type,String detailId,String billId,String tableId, String name,String tableName,String count,String price,String tuiCount,String zenCount,String remark, String reasonId, String reasonName);

    Observable<BaseEntity<String>> updatePrint(String type, String billId,String ipAddress);

    Observable<BaseEntity<String>> cancelPay(String type,String billId);

    Observable<BaseEntity<String>> TransferFood(String type,String id,String tableId,String detailId);

    Observable<BaseEntity<String>> getMember(String type,String phone,String billId,String id);

    Observable<BaseEntity<String>> getDiscount(String type,String billId,String discountNum);

    Observable<BaseEntity<String>> getWarePrice(String type,String id);

    Observable<BaseEntity<String>> getOrderList(String type,String id,String orderType,String phone,int index,int size,String state);

    Observable<BaseEntity<String>> getOrderDetail(String id, String type,String billId);

    Observable<BaseEntity<String>> print(String type,String shopID, String printSouce, String sate, String billId,String name, int personCount,String tableId, String tableName, double priceOld, double price,double free, String payType);

    Observable<BaseEntity<String>> reBillTangDian(String type,String id,String foodInfo, String userId,String name,String tableId,String tableName,String types,double price,String billType,String fanBill);

    Observable<BaseEntity<String>> fastFood(String type,String mark,String id,String info,String data,String time,String names,String address, String phone, String userId,String name,String remark,double money,String tableId,String tableName,String types,String fanBill,double price,String billType);

    Observable<BaseEntity<String>> getPay(String id,String type);

    Observable<BaseEntity<String>> getTableType(String type,String id);

    Observable<BaseEntity<String>> getQueue(String type,String leibie,String Phone,String shopId);

    Observable<BaseEntity<String>> addQueue(String type,String selvalue,String shopId);

    Observable<BaseEntity<String>> bindQueue(String type,String selvalue,String name,String userID,String tableId,String tableName,String tableWareCount,String orderid, String shopId);

    Observable<BaseEntity<String>> deleteQueue(String type,String orderid);

    Observable<BaseEntity<String>> getRechargeMember(String type,String id,String pageIndex,String pageSize,String name,String phone);

    Observable<BaseEntity<String>> getRecharge(String type,String id);

    Observable<BaseEntity<String>> addRecharge(String type,String shopId,String staffTel,String staffDepart,String staffLanguage,String staffCatalogue);

    Observable<BaseEntity<String>> checkCode(String type,String shopId,String passWord);

    Observable<BaseEntity<String>> moneyCharge(String type,String userID,String shopId,String price,int payType,String operaName,String operaId);

    Observable<BaseEntity<String>> productCharge(String type,String userID,String shopId,String cardID,int payType,String operaName,String operaId);

    Observable<BaseEntity<String>> getMessage(String type,String id,String leibie,String status,String phone,int index,int size);

    Observable<BaseEntity<String>> confirm(String type,String shopID,String orderId,String billId,String types);

    Observable<BaseEntity<String>> cancel(String type,String shopID,String orderId);

    Observable<BaseEntity<String>> bindTable(String type,String orderId,String tableId,String id,String tableWareCount,String name,String tableName);

    Observable<BaseEntity<String>> jiaoBanPrint(String s,String s1,String shopID,String name,String s2,String id);

    Observable<BaseEntity<String>> getDiscountList(String shopID,String type);

    Observable<BaseEntity<String>> getDazhe(String s,String billid,String shopId,String chengdazhe,int dazhe,String daId);

    Observable<BaseEntity<String>> getMergeOrderList(String s,String shopID,String tableId);

    Observable<BaseEntity<String>> getLineDownInfo(String type,String shopId,int pageSize,int pageIndex,String product);

    Observable<BaseEntity<String>> getOherYouhui(String type,String couponId,String billId,String shopId,double xiaPrice,double yinFu,String json);

    Observable<BaseEntity<String>> bill(String type,String id,String rid,String memberID,String tableId,double zon,double can,double pei,double daBao,
         String types,String jsonQuanXian,String jsonObj,String payType,String jsonPay,String guaID,String personMoney,String id1, String name,String zonwWeight,
                                        String zonPrice,String zonState, int personCount,double price,String tableName, double maLing,double rounding,double free);

    Observable<BaseEntity<String>> reBill(String type,String id,String rid,String memberID,String tableId,double zon,double can,double pei, double daBao, String types,
       String jsonObjquanxian,String jsonObj, String payType,String jsonPay,String guaID,String personMonery,String userId,String name,String zonweight,
           String zonprice,String zonstate,double free, double price,double maLing,double rounding,String fnaBill);

    Observable<BaseEntity<String>> scanBill(String type,String code,double price,String shopId,String payId);

    Observable<BaseEntity<String>> getOrderData(String type,String shopId,String billId,String types);

    Observable<BaseEntity<String>> inBill(String type,String billID);

    Observable<BaseEntity<String>> getOrder(String s,String roomTableID);

    Observable<BaseEntity<String>> weixinBill(String type,String id,String orderID, String types,int price);

    Observable<BaseEntity<String>> query(String type,String id,String OrderID);

    Observable<BaseEntity<String>> getGuazhangData(String shopID,String type);
}

