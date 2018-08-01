package com.administrator.shopkeepertablet.repository.parish;

import com.administrator.shopkeepertablet.model.api.ApiSource;
import com.administrator.shopkeepertablet.model.entity.BaseEntity;
import com.administrator.shopkeepertablet.model.preference.PreferenceSource;
import com.administrator.shopkeepertablet.repository.BaseRepertoryImpl;

import java.io.IOError;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Description:
 * Author CC
 * Time 2018/7/1
 */


public class ParishRepertoryImpl extends BaseRepertoryImpl implements ParishRepertory {
    private ApiSource apiSource;
    private PreferenceSource preferenceSource;

    public ParishRepertoryImpl(ApiSource apiSource, PreferenceSource preferenceSource) {
        super(apiSource, preferenceSource);
        this.apiSource = apiSource;
        this.preferenceSource = preferenceSource;
    }

    @Override
    public Observable<BaseEntity<String>> openTable(String type, String tableId, String tableName, String id, String people, String ware, String userId, String name) {
        return apiSource.openTable(type,tableId,tableName,id,people,ware,userId,name).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<BaseEntity<String>> clearTable(String type, String tableId, String billId, String id) {
        return apiSource.clearTable(type,tableId,billId,id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<BaseEntity<String>> getFoodTypeList(String type, String id, int index, int size) {
        return apiSource.getFoodTypeList(type,id,index,size).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<BaseEntity<String>> order(String type, String id, String tableId, String billId, String info, String UserId, String name, String tableName, String price, String foodType, String fanBill) {
        return apiSource.order(type, id, tableId, billId, info, UserId, name, tableName, price, foodType, fanBill).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    @Override
    public Observable<BaseEntity<String>> getOrderFoodList(String type, String id, String billId) {
        return apiSource.getOrderFoodList(type,id,billId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<BaseEntity<String>> getFoodKouweiList(String id, int index, int size) {
        return apiSource.getFoodKouweiList(id, index, size).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<BaseEntity<String>> cancelOrder(String type, String tableId, String billId, String id, String tableName, String userName, String userId) {
        return apiSource.cancelOrder(type, tableId, billId, id, tableName, userName, userId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<BaseEntity<String>> changePeople(String type, String tableId, String peopleNum, String wareNum, String billId, String id) {
        return apiSource.changePeople(type, tableId, peopleNum, wareNum, billId, id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<BaseEntity<String>> changeTable(String type, String newTableId, String newTableName, String oldTableId, String billId) {
        return apiSource.changeTable(type, newTableId, newTableName, oldTableId, billId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<BaseEntity<String>> pushFood(String type, String detailId, String billId, String id, String tableId, String name, String tableName) {
        return apiSource.pushFood(type, detailId, billId, id, tableId, name, tableName).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<BaseEntity<String>> pushFoodAll(String type, String printSource, String id, String billId, String tableId, String tableName, String foodType, String name, String state) {
        return apiSource.pushFoodAll(type, printSource, id, billId, tableId, tableName, foodType, name, state).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<BaseEntity<String>> printAfter(String type, String printSource, String id, String billId, String tableId, String tableName, String personCount, String state, String foodType, String name) {
        return apiSource.printAfter(type, printSource, id, billId, tableId, tableName, personCount, state, foodType, name).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<BaseEntity<String>> getReason(String id, String type) {
        return apiSource.getReason(id, type).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<BaseEntity<String>> givingFood(String type, String detailId, String sum) {
        return apiSource.givingFood(type, detailId, sum).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<BaseEntity<String>> returnFood(String id, String type, String detailId, String billId, String tableId, String name, String tableName, String count, String price, String tuiCount, String zenCount, String remark, String reasonId, String reasonName) {
        return apiSource.returnFood(id, type, detailId, billId, tableId, name, tableName, count, price, tuiCount, zenCount, remark, reasonId, reasonName).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<BaseEntity<String>> cancelPay(String type, String billId) {
        return apiSource.cancelPay(type, billId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<BaseEntity<String>> TransferFood(String type, String id, String tableId, String detailId) {
        return apiSource.TransferFood(type, id, tableId, detailId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<BaseEntity<String>> getDiscount(String type, String billId, String discountNum) {
        return apiSource.getDiscount(type, billId, discountNum).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<BaseEntity<String>> getWarePrice(String type, String id) {
        return apiSource.getWarePrice(type, id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<BaseEntity<String>> reBillTangDian(String type, String id, String foodInfo, String userId, String name, String tableId, String tableName, String types, double price, String billType, String fanBill) {
        return apiSource.reBillTangDian(type, id, foodInfo, userId, name, tableId, tableName, types, price, billType, fanBill).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
