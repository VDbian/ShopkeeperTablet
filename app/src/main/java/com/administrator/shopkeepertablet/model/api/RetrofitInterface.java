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
public interface RetrofitInterface {
    //用户登录
    @FormUrlEncoded
    @POST(AppConstant.PORT_URL + "PortLoginAshx.ashx")
    Observable<BaseEntity<String>> login(
            @Field("LoginName") String loginName,//登录名
            @Field("ID") String id,//店铺ID
            @Field("Passd") String pwd//登录密码
    );

    //获取房间
    @FormUrlEncoded
    @POST(AppConstant.PORT_URL + "PortKaiDanAshx.ashx")
    Observable<BaseEntity<String>> getRooms(
            @Field("Type") String type,
            @Field("id") String id,
            @Field("Pindex1") int Pindex,
            @Field("Psize1") int Psize
    );

    //获取桌号信息
    @FormUrlEncoded
    @POST(AppConstant.PORT_URL + "PortKaiDanAshx.ashx")
    Observable<BaseEntity<String>> getTables(
            @Field("Type") String type,
            @Field("leibie") String leibie,
            @Field("id") String id,
            @Field("Pindex1") int Pindex,
            @Field("Psize1") int Psize
    );

    //开桌
    @FormUrlEncoded
    @POST(AppConstant.PORT_URL + "PortKaiTaiAshx.ashx")
    Observable<BaseEntity<String>> openTable(
            @Field("Type") String type,
            @Field("ltableid") String tableId,
            @Field("itablename") String tableName,
            @Field("id") String id,
            @Field("peoplecount") String people,
            @Field("canju") String ware,
            @Field("UserID") String userId,
            @Field("Name") String name
    );

    //清台
    @FormUrlEncoded
    @POST(AppConstant.PORT_URL + "PortKaiTaiAshx.ashx")
    Observable<BaseEntity<String>> clearTable(
            @Field("Type") String type,
            @Field("tableid") String tableId,
            @Field("billid") String billId,
            @Field("id") String id
    );


    //菜单列表PortTakeFoodAshx.ashx
    @FormUrlEncoded
    @POST(AppConstant.PORT_URL + "PortTakeFoodAshx.ashx")
    Observable<ResultFoodEntity> getFoodList(
            @Field("Type") String type,
            @Field("id") String id//店铺ID
    );

    //菜品类型PortTakeFoodAshx.ashx
    @FormUrlEncoded
    @POST(AppConstant.PORT_URL + "PortTakeFoodAshx.ashx")
    Observable<BaseEntity<String>> getFoodTypeList(
            @Field("Type") String type,
            @Field("id") String id,//店铺ID
            @Field("Pindex1") int Pindex,
            @Field("Psize1") int Psize
    );


    //套餐PortTakeFoodAshx.ashx
    @FormUrlEncoded
    @POST(AppConstant.PORT_URL + "PortTakeFoodAshx.ashx")
    Observable<ResultFoodEntity> getComboList(
            @Field("Type") String type,
            @Field("id") String id,//店铺ID
            @Field("Pindex1") int Pindex,
            @Field("Psize1") int Psize
    );


    //堂点下单 PortTakeFoodServer.ashx
    @FormUrlEncoded
    @POST(AppConstant.PORT_URL + "PortTakeFoodServer.ashx")
    Observable<BaseEntity<String>> order(
            @Field("Type") String type,
            @Field("id") String id,//店铺ID
            @Field("tableid") String tableId,
            @Field("billid") String billId,
            @Field("info") String info,
            @Field("UserID") String UserId,
            @Field("Name") String name,
            @Field("TableName") String tableName,
            @Field("APrice") String price,
            @Field("FoodType") String foodType,
            @Field("FanBill") String fanBill
    );

    //获取订单菜品列表
    @FormUrlEncoded
    @POST(AppConstant.PORT_URL + "PortKaiDanAshx.ashx")
    Observable<BaseEntity<String>> getOrderFoodList(
            @Field("type") String type,
            @Field("id") String id,
            @Field("BILLID") String billId
    );

    //获取批量品注的口味
    @FormUrlEncoded
    @POST(AppConstant.PORT_URL + "PortKouWeiAshx.ashx")
    Observable<BaseEntity<String>> getFoodKouweiList(
            @Field("id") String id,//店铺ID
            @Field("Pindex1") int Pindex,
            @Field("Psize1") int Psize
    );

    //撤单
    @FormUrlEncoded
    @POST(AppConstant.PORT_URL + "PortKaiDanAshx.ashx")
    Observable<BaseEntity<String>> cancelOrder(
            @Field("type") String type,
            @Field("TableId") String tableId,
            @Field("billid") String billId,
            @Field("id") String id,
            @Field("TableName") String tableName,
            @Field("Username") String userName,
            @Field("USERID") String userId
    );

    //修改人数
    @FormUrlEncoded
    @POST(AppConstant.PORT_URL + "PortKaiTaiAshx.ashx")
    Observable<BaseEntity<String>> changePeople(
            @Field("type") String type,
            @Field("tableid") String tableId,
            @Field("peoplecount") String peopleNum,
            @Field("canju") String wareNum,
            @Field("billid") String billId,
            @Field("id") String id
    );

    //换桌
    @FormUrlEncoded
    @POST(AppConstant.PORT_URL + "PortTurnFoodKaiDan.ashx")
    Observable<BaseEntity<String>> changeTable(
            @Field("type") String type,
            @Field("TableId") String newTableId,
            @Field("TableName") String newTableName,
            @Field("xtableid") String oldTableId ,
            @Field("billid") String billId
    );

    //单个菜品催菜
    @FormUrlEncoded
    @POST(AppConstant.PORT_URL + "PortTakeFoodServer.ashx")
    Observable<BaseEntity<String>> pushFood(
            @Field("Type") String type,
            @Field("DETAILID") String detailId,
            @Field("billid") String billId,
            @Field("id") String id,
            @Field("tableid") String tableId,
            @Field("Name") String name,
            @Field("TableName") String tableName
    );

    @FormUrlEncoded
    @POST(AppConstant.PORT_URL + "PortPrinterManager.ashx")
    Observable<BaseEntity<String>> pushPrint(
            @Field("type") String type,
            @Field("printsouce") String printSource,
            @Field("id") String id,
            @Field("billid") String billId,
            @Field("tableid") String tableId,
            @Field("tablename") String tableName,
            @Field("foodid") String foodId,
            @Field("Sate") String state

    );


    //催菜
    @FormUrlEncoded
    @POST(AppConstant.PORT_URL + "PortPrinterManager.ashx")
    Observable<BaseEntity<String>> pushFoodAll(
            @Field("Type") String type,
            @Field("printsouce") String printSource,
            @Field("id") String id,
            @Field("billid") String billId,
            @Field("tableid") String tableId,
            @Field("tablename") String tableName,
            @Field("FoodType") String foodType,
            @Field("Name") String name,
            @Field("Sate") String state);

    //商品补打
    @FormUrlEncoded
    @POST(AppConstant.PORT_URL + "PortPrinterManager.ashx")
    Observable<BaseEntity<String>> printAfter(
            @Field("type") String type,
            @Field("printsouce") String printSource,
            @Field("id") String id,
            @Field("billid") String billId,
            @Field("tableid") String tableId,
            @Field("tablename") String tableName,
            @Field("personcount") String personCount,
            @Field("Sate") String state,
            @Field("FoodType") String foodType,
            @Field("Name") String name);

    //获取退菜原因
    @FormUrlEncoded
    @POST(AppConstant.PORT_URL + "PortTakeFoodServer.ashx")
    Observable<BaseEntity<String>> getReason(
            @Field("id") String id,
            @Field("type") String type);

    //赠菜
    @FormUrlEncoded
    @POST(AppConstant.PORT_URL + "PortKaiDanAshx.ashx")
    Observable<BaseEntity<String>> givingFood(
            @Field("type") String type,
            @Field("DETAILID") String detailId,
            @Field("sum") String sum);

    //退菜
    @FormUrlEncoded
    @POST(AppConstant.PORT_URL + "PortTakeFoodServer.ashx")
    Observable<BaseEntity<String>> returnFood(
            @Field("Id") String id,
            @Field("Type") String type,
            @Field("DETAILID") String detailId,
            @Field("billid") String billId,
            @Field("TABLEID") String tableId,
            @Field("Name") String name,
            @Field("TableName") String tableName,
            @Field("count") String count,
            @Field("pice") String price,
            @Field("tuicount") String tuiCount,
            @Field("zencount") String zenCount,
            @Field("zidinyi") String remark,
            @Field("dirtoatal") String reasonId,
            @Field("toasttoatal") String reasonName
    );

    //取消结账
    @FormUrlEncoded
    @POST(AppConstant.PORT_URL + "PortBillManagerNweASHX.ashx")
    Observable<BaseEntity<String>> cancelPay(
            @Field("Type") String type,
            @Field("BILLId") String billId
    );

    //转菜
    @FormUrlEncoded
    @POST(AppConstant.PORT_URL + "PortKaiTaiAshx.ashx")
    Observable<BaseEntity<String>> TransferFood(
            @Field("Type") String type,
            @Field("id") String id,
            @Field("TableId") String tableId,
            @Field("DETAILID") String detailId
    );

    //获取会员信息
    @FormUrlEncoded
    @POST(AppConstant.PORT_URL + "PortBillManagerNweASHX.ashx")
    Observable<BaseEntity<String>> getMember(
            @Field("Type") String type,
            @Field("coue") String num,
            @Field("id") String billId,
            @Field("rid") String id
    );

    //获取优惠券信息
    @FormUrlEncoded
    @POST(AppConstant.PORT_URL + "PortBillManagerNweASHX.ashx")
    Observable<BaseEntity<String>> getDiscount(
            @Field("Type") String type,
            @Field("id") String billId,
            @Field("coue") String discountNum

    );

    //获取餐具价格
    @FormUrlEncoded
    @POST(AppConstant.PORT_URL + "PortBillManagerNweASHX.ashx")
    Observable<BaseEntity<String>> getWarePrice(
            @Field("Type") String type,
            @Field("Rid") String id
    );


    //获取订单列表
    @FormUrlEncoded
    @POST(AppConstant.PORT_URL + "PortOrderManagerASHX.ashx")
    Observable<BaseEntity<String>> getOrderList(
            @Field("Type") String type,
            @Field("id") String id,
            @Field("leibie") String orderType,
            @Field("OrderSate") String orderState,
            @Field("Phone") String phone,
            @Field("Pindex") int index,
            @Field("Psize") int size,
            @Field("STATE") String state
    );

    @FormUrlEncoded
    @POST(AppConstant.PORT_URL + "PortKaiDanAshx.ashx")
    Observable<BaseEntity<String>> getOrderDetail(
            @Field("id") String id,
            @Field("Type") String type,
            @Field("BILLID") String billId);


//    @FormUrlEncoded
//    @POST(AppConstant.PORT_URL + "PortKaiTaiAshx.ashx")
//    Observable<BaseEntity<String>> changePeople(
//    );
    @FormUrlEncoded
    @POST(AppConstant.PORT_URL + "PortPrinterManager.ashx")
    Observable<BaseEntity<String>> updatePrint(
            @Field("type") String type,
            @Field("BILLID") String billId,
            @Field("IPADDRESS") String ipAddress);

}
