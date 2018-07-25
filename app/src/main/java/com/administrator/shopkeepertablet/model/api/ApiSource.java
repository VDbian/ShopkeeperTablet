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

    Observable<BaseEntity<String>> order(String type, String id, String tableId, String billId, String info, String UserId, String name, String tableName, String price, String foodType, String fanBill);

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

    Observable<BaseEntity<String>> getOrderList(String type,String id,String orderType,String orderState,String phone,int index,int size,String state);

    Observable<BaseEntity<String>> getOrderDetail(String id, String type,String billId);
}
