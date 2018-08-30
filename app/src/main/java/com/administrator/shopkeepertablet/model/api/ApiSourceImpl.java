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
    public Observable<BaseEntity<String>> order(String type, String id, String tableId, String billId, String info, String UserId, String name, String tableName, String price, String foodType,String tableWareCount, String fanBill) {
        return retrofitInterface.order(type,id,tableId,billId,info,UserId,name,tableName,price,foodType,tableWareCount,fanBill);
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
    public Observable<BaseEntity<String>> getMember(String type, String num,String billId,String id) {
        return retrofitInterface.getMember(type,num,billId,id);
    }

    @Override
    public Observable<BaseEntity<String>> getDiscount(String type, String billId, String discountNum) {
        return retrofitInterface.getDiscount(type, billId, discountNum);
    }

    @Override
    public Observable<BaseEntity<String>> getWarePrice(String type, String id) {
        return retrofitInterface.getWarePrice(type, id);
    }

    @Override
    public Observable<BaseEntity<String>> getOrderList(String type, String id, String orderType, String phone, int index, int size, String state) {
        return retrofitInterface.getOrderList(type, id, orderType, phone, index, size, state);
    }

    @Override
    public Observable<BaseEntity<String>> getOrderDetail(String id, String type, String billId) {
        return retrofitInterface.getOrderDetail(id, type, billId);
    }

    @Override
    public Observable<BaseEntity<String>> print(String type, String shopID, String printSouce, String sate, String billId, String name, int personCount, String tableId, String tableName, double priceOld, double price, double free, String payType) {
        return retrofitInterface.print(type, shopID, printSouce, sate, billId, name, personCount, tableId, tableName, priceOld, price, free, payType);
    }

    @Override
    public Observable<BaseEntity<String>> reBillTangDian(String type, String id, String foodInfo, String userId, String name, String tableId, String tableName, String types, double price, String billType, String fanBill) {
        return retrofitInterface.reBillTangDian(type, id, foodInfo, userId, name, tableId, tableName, types, price, billType, fanBill);
    }

    @Override
    public Observable<BaseEntity<String>> fastFood(String type, String mark, String id, String info, String data, String time, String names, String address, String phone, String userId, String name, String remark, double money, String tableId, String tableName, String types, String fanBill, double price, String billType) {
        return retrofitInterface.fastFood(type, mark, id, info, data, time, names, address, phone, userId, name, remark, money, tableId, tableName, types, fanBill, price, billType);
    }

    @Override
    public Observable<BaseEntity<String>> getPay(String id, String type) {
        return retrofitInterface.getPay(id,type);
    }

    @Override
    public Observable<BaseEntity<String>> getTableType(String type, String id) {
        return retrofitInterface.getTableType(type, id);
    }

    @Override
    public Observable<BaseEntity<String>> getQueue(String type, String leibie, String phone, String shopId) {
        return retrofitInterface.getQueue(type, leibie, phone, shopId);
    }

    @Override
    public Observable<BaseEntity<String>> addQueue(String type, String selvalue, String shopId) {
        return retrofitInterface.addQueue(type, selvalue, shopId);
    }

    @Override
    public Observable<BaseEntity<String>> bindQueue(String type, String selvalue, String name, String userID, String tableId, String tableName, String tableWareCount, String orderid, String shopId) {
        return retrofitInterface.bindQueue(type, selvalue, name, userID, tableId, tableName, tableWareCount, orderid, shopId);
    }

    @Override
    public Observable<BaseEntity<String>> deleteQueue(String type, String orderid) {
        return retrofitInterface.deleteQueue(type, orderid);
    }

    @Override
    public Observable<BaseEntity<String>> getRechargeMember(String type, String id, String pageIndex, String pageSize, String name, String phone) {
        return retrofitInterface.getRechargeMember(type, id, pageIndex, pageSize, name, phone);
    }

    @Override
    public Observable<BaseEntity<String>> getRecharge(String type, String id) {
        return retrofitInterface.getRecharge(type, id);
    }

    @Override
    public Observable<BaseEntity<String>> addRecharge(String type, String shopId, String staffTel, String staffDepart, String staffLanguage, String staffCatalogue) {
        return retrofitInterface.addRecharge(type, shopId, staffTel, staffDepart, staffLanguage, staffCatalogue);
    }

    @Override
    public Observable<BaseEntity<String>> checkCode(String type, String shopId, String passWord) {
        return retrofitInterface.checkCode(type, shopId, passWord);
    }

    @Override
    public Observable<BaseEntity<String>> moneyCharge(String type, String userID, String shopId, String price, int payType, String operaName, String operaId) {
        return retrofitInterface.moneyCharge(type, userID, shopId, price, payType, operaName, operaId);
    }

    @Override
    public Observable<BaseEntity<String>> productCharge(String type, String userID, String shopId, String cardID, int payType, String operaName, String operaId) {
        return retrofitInterface.productCharge(type, userID, shopId, cardID, payType, operaName, operaId);
    }

    @Override
    public Observable<BaseEntity<String>> getMessage(String type, String id, String leibie, String status, String phone, int index, int size) {
        return retrofitInterface.getMessage(type, id, leibie, status, phone, index, size);
    }

    @Override
    public Observable<BaseEntity<String>> confirm(String type, String shopID, String orderId, String billId, String types) {
        return retrofitInterface.confirm(type, shopID, orderId, billId, types);
    }

    @Override
    public Observable<BaseEntity<String>> cancel(String type, String shopID, String orderId) {
        return retrofitInterface.cancel(type, shopID, orderId);
    }

    @Override
    public Observable<BaseEntity<String>> bindTable(String type, String orderId, String tableId, String id, String tableWareCount, String name, String tableName) {
        return retrofitInterface.bindTable(type, orderId, tableId, id, tableWareCount, name, tableName);
    }

    @Override
    public Observable<BaseEntity<String>> jiaoBanPrint(String s, String s1, String shopID, String name, String s2, String id) {
        return retrofitInterface.jiaoBanPrint(s, s1, shopID, name, s2, id);
    }

    @Override
    public Observable<BaseEntity<String>> getDiscountList(String shopID, String type) {
        return retrofitInterface.getDiscountList(shopID, type);
    }

    @Override
    public Observable<BaseEntity<String>> getDazhe(String s, String billid, String shopId, String chengdazhe, int dazhe, String daId) {
        return retrofitInterface.getDazhe(s, billid, shopId, chengdazhe, dazhe, daId);
    }

    @Override
    public Observable<BaseEntity<String>> getMergeOrderList(String s, String shopID, String tableId) {
        return retrofitInterface.getMergeOrderList(s, shopID, tableId);
    }

    @Override
    public Observable<BaseEntity<String>> getLineDownInfo(String type, String shopId, int pageSize, int pageIndex, String product) {
        return retrofitInterface.getLineDownInfo(type, shopId, pageSize, pageIndex, product);
    }

    @Override
    public Observable<BaseEntity<String>> getOherYouhui(String type, String couponId, String billId, String shopId, double xiaPrice, double yinFu, String json) {
        return retrofitInterface.getOherYouhui(type, couponId, billId, shopId, xiaPrice, yinFu, json);
    }

    @Override
    public Observable<BaseEntity<String>> bill(String type, String id, String rid, String memberID, String tableId, double zon, double can, double pei, double daBao, String types, String jsonQuanXian, String jsonObj, String payType, String jsonPay, String guaID, String personMoney, String id1, String name, String zonwWeight, String zonPrice, String zonState, int personCount, double price, String tableName, double maLing, double rounding, double free) {
        return retrofitInterface.bill(type, id, rid, memberID, tableId, zon, can, pei, daBao, types, jsonQuanXian, jsonObj, payType, jsonPay, guaID, personMoney, id1, name, zonwWeight, zonPrice, zonState, personCount, price, tableName, maLing, rounding, free);
    }

    @Override
    public Observable<BaseEntity<String>> reBill(String type, String id, String rid, String memberID, String tableId, double zon, double can, double pei, double daBao, String types, String jsonObjquanxian, String jsonObj, String payType, String jsonPay, String guaID, String personMonery, String userId, String name, String zonweight, String zonprice, String zonstate, double free, double price, double maLing, double rounding, String fnaBill) {
        return retrofitInterface.reBill(type, id, rid, memberID, tableId, zon, can, pei, daBao, types, jsonObjquanxian, jsonObj, payType, jsonPay, guaID, personMonery, userId, name, zonweight, zonprice, zonstate, free, price, maLing, rounding, fnaBill);
    }

    @Override
    public Observable<BaseEntity<String>> scanBill(String type, String code, double price, String shopId, String payId) {
        return retrofitInterface.scanBill(type, code, price, shopId, payId);
    }

    @Override
    public Observable<BaseEntity<String>> getOrderData(String type, String shopId, String billId, String types) {
        return retrofitInterface.getOrderData(type, shopId, billId, types);
    }

    @Override
    public Observable<BaseEntity<String>> inBill(String type, String billID) {
        return retrofitInterface.inBill(type, billID);
    }

    @Override
    public Observable<BaseEntity<String>> getOrder(String s, String roomTableID) {
        return retrofitInterface.getOrder(s, roomTableID);
    }

    @Override
    public Observable<BaseEntity<String>> weixinBill(String type, String id, String orderID, String types, int price) {
        return retrofitInterface.weixinBill(type, id, orderID, types, price);
    }

    @Override
    public Observable<BaseEntity<String>> query(String type, String id, String OrderID) {
        return retrofitInterface.query(type, id, OrderID);
    }

    @Override
    public Observable<BaseEntity<String>> getGuazhangData(String shopID, String type) {
        return retrofitInterface.getGuazhangData(shopID, type);
    }
}
