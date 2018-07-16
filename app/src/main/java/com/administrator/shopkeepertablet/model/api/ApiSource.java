package com.administrator.shopkeepertablet.model.api;

import com.administrator.shopkeepertablet.model.entity.BaseEntity;
import com.administrator.shopkeepertablet.model.entity.ResultFoodEntity;
import com.administrator.shopkeepertablet.model.entity.UserInfoEntity;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;

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
}
