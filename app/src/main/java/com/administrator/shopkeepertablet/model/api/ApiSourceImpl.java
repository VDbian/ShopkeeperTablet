package com.administrator.shopkeepertablet.model.api;

import com.administrator.shopkeepertablet.model.entity.BaseEntity;
import com.administrator.shopkeepertablet.model.entity.ResultFoodEntity;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * Description:
 * Author CC
 * Time 2018/6/11
 */
public class ApiSourceImpl implements ApiSource {

    private RetrofitInterface retrofitInterface;

    public ApiSourceImpl(Retrofit retrofit) {
        retrofitInterface = retrofit.create(RetrofitInterface.class);
    }


    @Override
    public Observable<BaseEntity<String>> login(String name, String id, String pwd) {
        return retrofitInterface.login(name, id, pwd);
    }

    @Override
    public Observable<BaseEntity<String>> getRooms(String type, String id, int index, int size) {
        return retrofitInterface.getRooms(type, id, index, size);
    }

    @Override
    public Observable<BaseEntity<String>> getTables(String type, String leibie, String id, int Pindex, int Psize) {
        return retrofitInterface.getTables(type, leibie, id, Pindex, Psize);
    }

    @Override
    public Observable<BaseEntity<String>> openTable(String type, String tableId, String tableName, String id, String people, String ware, String userId, String name) {
        return retrofitInterface.openTable(type, tableId, tableName, id, people, ware, userId, name);
    }

    @Override
    public Observable<ResultFoodEntity> getComboList(String type, String id, int index, int size) {
        return retrofitInterface.getComboList(type,id,index,size);
    }

    @Override
    public Observable<BaseEntity<String>> getFoodTypeList(String type, String id, int index, int size) {
        return retrofitInterface.getFoodTypeList(type,id,index,size);
    }

    @Override
    public Observable<BaseEntity<String>> order(String type, String id, String tableId, String billId, String info, String UserId, String name, String tableName, String price, String foodType, String fanBill) {
        return retrofitInterface.order(type,id,tableId,billId,info,UserId,name,tableName,price,foodType,fanBill);
    }

    @Override
    public Observable<BaseEntity<String>> clearTable(String type, String tableId, String billId, String id) {
        return retrofitInterface.clearTable(type,tableId,billId,id);
    }

    @Override
    public Observable<ResultFoodEntity> getFoodList(String type, String id) {
        return retrofitInterface.getFoodList(type, id);
    }

    @Override
    public Observable<BaseEntity<String>> getOrderFoodList(String type, String id, String billId) {
        return retrofitInterface.getOrderFoodList(type, id, billId);
    }

    @Override
    public Observable<BaseEntity<String>> getFoodKouweiList(String id, int index, int size) {
        return retrofitInterface.getFoodKouweiList(id, index, size);
    }

    @Override
    public Observable<BaseEntity<String>> cancelOrder(String type, String tableId, String billId, String id, String tableName, String userName, String userId) {
        return retrofitInterface.cancelOrder(type, tableId, billId, id, tableName, userName, userId);
    }

    @Override
    public Observable<BaseEntity<String>> changePeople(String type, String tableId, String peopleNum, String wareNum, String billId, String id) {
        return retrofitInterface.changePeople(type, tableId, peopleNum, wareNum, billId, id);
    }

    @Override
    public Observable<BaseEntity<String>> changeTable(String type, String newTableId, String newTableName, String oldTableId, String billId) {
        return retrofitInterface.changeTable(type, newTableId, newTableName, oldTableId, billId);
    }

    @Override
    public Observable<BaseEntity<String>> pushFood(String type, String detailId, String billId, String id, String tableId, String name, String tableName) {
        return retrofitInterface.pushFood(type, detailId, billId, id, tableId, name, tableName);
    }

    @Override
    public Observable<BaseEntity<String>> pushFoodAll(String type, String printSource, String id, String billId, String tableId, String tableName, String foodType, String name, String state) {
        return retrofitInterface.pushFoodAll(type, printSource, id, billId, tableId, tableName, foodType, name, state);
    }

    @Override
    public Observable<BaseEntity<String>> printAfter(String type, String printSource, String id, String billId, String tableId, String tableName, String personCount, String state, String foodType, String name) {
        return retrofitInterface.printAfter(type, printSource, id, billId, tableId, tableName, personCount, state, foodType, name);
    }

    @Override
    public Observable<BaseEntity<String>> getReason(String id, String type) {
        return retrofitInterface.getReason(id, type);
    }

    @Override
    public Observable<BaseEntity<String>> givingFood(String type, String detailId, String sum) {
        return retrofitInterface.givingFood(type, detailId, sum);
    }

    @Override
    public Observable<BaseEntity<String>> returnFood(String id, String type, String detailId, String billId, String tableId, String name, String tableName, String count, String price, String tuiCount, String zenCount, String remark, String reasonId, String reasonName) {
        return retrofitInterface.returnFood(id, type, detailId, billId, tableId, name, tableName, count, price, tuiCount, zenCount, remark, reasonId, reasonName);
    }

    @Override
    public Observable<BaseEntity<String>> updatePrint(String type, String billId, String ipAddress) {
        return retrofitInterface.updatePrint(type, billId, ipAddress);
    }

    @Override
    public Observable<BaseEntity<String>> cancelPay(String type, String billId) {
        return retrofitInterface.cancelPay(type, billId);
    }

    @Override
    public Observable<BaseEntity<String>> TransferFood(String type, String id, String tableId, String detailId) {
        return retrofitInterface.TransferFood(type, id, tableId, detailId);
    }

    @Override
    public Observable<BaseEntity<String>> getMember(String type, String phone,String billId,String id) {
        return retrofitInterface.getMember(type, phone,billId,id);
    }

    @Override
    public Observable<BaseEntity<String>> getDiscount(String type, String billId, String discountNum) {
        return retrofitInterface.getDiscount(type, billId, discountNum);
    }

    @Override
    public Observable<BaseEntity<String>> getWarePrice(String type, String id) {
        return retrofitInterface.getWarePrice(type, id);
    }
}
