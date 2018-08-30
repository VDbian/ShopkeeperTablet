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
            @Field("TableWareCount") String tableWareCount,
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
//            @Field("OrderSate") String orderState,
            @Field("Phone") String phone,
            @Field("Pindex") int index,
            @Field("Psize") int size,
            @Field("state") String state
    );

    //获取订单详情
    @FormUrlEncoded
    @POST(AppConstant.PORT_URL + "PortKaiDanAshx.ashx")
    Observable<BaseEntity<String>> getOrderDetail(
            @Field("id") String id,
            @Field("Type") String type,
            @Field("BILLID") String billId);


    //补打账单
    @FormUrlEncoded
    @POST(AppConstant.PORT_URL + "PortPrinterManager.ashx")
    Observable<BaseEntity<String>> print(
            @Field("type") String type,
            @Field("id") String shopID,
            @Field("printsouce") String printSouce,
            @Field("Sate") String sate,
            @Field("billid") String billId,
            @Field("Name") String name,
            @Field("personcount") int personCount,
            @Field("tableid") String tableId,
            @Field("tablename") String tableName,
            @Field("priceold") double priceOld,
            @Field("price") double price,
            @Field("free") double free,
            @Field("PayType") String payType);

    //反结账
    @FormUrlEncoded
    @POST(AppConstant.PORT_URL + "PortPlaceOrderASHX.ashx")
    Observable<BaseEntity<String>> reBillTangDian(
            @Field("Type") String type,
            @Field("id") String id,
            @Field("foodinfo") String foodInfo,
            @Field("UserId") String userId,
            @Field("Name") String name,
            @Field("tableid") String tableId,
            @Field("tablename") String tableName,
            @Field("types") String types,
            @Field("price") double price,
            @Field("BillType") String billType,
            @Field("FanBill") String fanBill);

    //快餐、预定
    @FormUrlEncoded
    @POST(AppConstant.PORT_URL + "PortPlaceOrderASHX.ashx")
    Observable<BaseEntity<String>> fastFood(
            @Field("Type") String type,
            @Field("dukabiaoji") String mark,
            @Field("id") String id,
            @Field("foodinfo") String info,
            @Field("pdata") String data,
            @Field("ptime") String time,
            @Field("names") String names,
            @Field("address") String address,
            @Field("phone") String phone,
            @Field("UserID") String userId,
            @Field("Name") String name,
            @Field("remark") String remark,
            @Field("monery") double money,
            @Field("tablid") String tableId,
            @Field("tablename") String tableName,
            @Field("types") String types,
            @Field("FanBill") String fanBill,
            @Field("price") double price,
            @Field("BillType")  String billType);//字段 BillType 0=无桌位  1=有桌位

    //获取支付方式
    @FormUrlEncoded
    @POST(AppConstant.PORT_URL + "PortBillManagerNweASHX.ashx")
    Observable<BaseEntity<String>> getPay(
            @Field("id") String id,
            @Field("type") String type);

    //获取桌位类别
    @FormUrlEncoded
    @POST(AppConstant.MASTE_URL +"TableManager.ashx")
    Observable<BaseEntity<String>> getTableType(
            @Field("type") String type,
            @Field("RESTAURANTID") String id);

    //获取排队信息列表
    @FormUrlEncoded
    @POST(AppConstant.PORT_URL + "PortLineUPOrder.ashx")
    Observable<BaseEntity<String>> getQueue(
            @Field("Type") String type,
            @Field("leibie") String leibie,
            @Field("Phone") String Phone,
            @Field("id") String shopId);

    //新增排队
    @FormUrlEncoded
    @POST(AppConstant.PORT_URL + "PortLineUPOrder.ashx")
    Observable<BaseEntity<String>> addQueue(
            @Field("Type") String type,
            @Field("selvalue") String selvalue,
            @Field("id") String shopId);

    //绑定桌位
    @FormUrlEncoded
    @POST(AppConstant.PORT_URL + "PortLineUPOrder.ashx")
    Observable<BaseEntity<String>> bindQueue(
            @Field("Type") String type,
            @Field("selvalue") String selvalue,
            @Field("Name") String name,
            @Field("UserID") String userID,
            @Field("TableId") String tableId,
            @Field("TableName") String tableName,
            @Field("TableWareCount") String tableWareCount,
            @Field("orderid") String orderid,
            @Field("id") String shopId);

    //删除排队
    @FormUrlEncoded
    @POST(AppConstant.PORT_URL + "PortLineUPOrder.ashx")
    Observable<BaseEntity<String>> deleteQueue(
            @Field("Type") String type,
            @Field("orderid") String orderid);

    //获取会员列表
    @FormUrlEncoded
    @POST(AppConstant.MASTE_URL + "Merchants.ashx")
    Observable<BaseEntity<String>> getRechargeMember(
            @Field("Type") String type,
            @Field("RESTAURANTID") String id,
            @Field("pageIndex") String pageIndex,
            @Field("pageSize") String pageSize,
            @Field("Name") String name,
            @Field("phone") String phone);

    //获取充值类别
    @FormUrlEncoded
    @POST(AppConstant.MASTE_URL + "Merchants.ashx")
    Observable<BaseEntity<String>> getRecharge(
            @Field("Type") String type,
            @Field("RESTAURANTID") String id);

    //新增会员
    @FormUrlEncoded
    @POST(AppConstant.MASTE_URL+ "Merchants.ashx")
    Observable<BaseEntity<String>> addRecharge(
            @Field("Type") String type,
            @Field("RESTAURANTID") String shopId,
            @Field("StaffTel") String staffTel,
            @Field("StaffDepart") String staffDepart,
            @Field("StaffLanguage") String staffLanguage,
            @Field("StaffCatalogue") String staffCatalogue
    );

    //验证校验码
    @FormUrlEncoded
    @POST(AppConstant.MASTE_URL + "Merchants.ashx")
    Observable<BaseEntity<String>> checkCode(
            @Field("Type") String type,
            @Field("RESTAURANTID") String shopId,
            @Field("PassWord") String passWord);

    // 自定义充值
    @FormUrlEncoded
    @POST(AppConstant.MASTE_URL + "Merchants.ashx")
    Observable<BaseEntity<String>> moneyCharge(
            @Field("Type") String type,
            @Field("UserID") String userID,
            @Field("RESTAURANTID") String shopId,
            @Field("Price") String price,
            @Field("PayType") int payType,
            @Field("OperaName") String operaName,
            @Field("OperaID") String operaId);

    //产品充值
    @FormUrlEncoded
    @POST(AppConstant.MASTE_URL + "Merchants.ashx")
    Observable<BaseEntity<String>> productCharge(
            @Field("Type") String type,
            @Field("UserID") String userID,
            @Field("RESTAURANTID") String shopId,
            @Field("CardID") String cardID,
            @Field("PayType") int payType,
            @Field("OperaName") String operaName,
            @Field("OperaID") String operaId);

    //获取消息列表
    @FormUrlEncoded
    @POST(AppConstant.PORT_URL + "PortOrderManagerASHX.ashx")
    Observable<BaseEntity<String>> getMessage(
            @Field("Type") String type,
            @Field("id") String id,
            @Field("leibie") String leibie,
            @Field("OrderSate") String status,
            @Field("Phone") String phone,
            @Field("Pindex") int index,
            @Field("Psize") int size
    );

    //消息确认
    @FormUrlEncoded
    @POST(AppConstant.PORT_URL + "PortOrderManagerASHX.ashx")
    Observable<BaseEntity<String>> confirm(
            @Field("Type") String type,
            @Field("Id") String shopID,
            @Field("OrderId") String orderId,
            @Field("BILLID") String billId,
            @Field("Types") String types);

    //消息取消
    @FormUrlEncoded
    @POST(AppConstant.PORT_URL + "PortOrderManagerASHX.ashx")
    Observable<BaseEntity<String>> cancel(
            @Field("Type") String type,
            @Field("ID") String shopID,
            @Field("OrderID") String orderId);

    //绑定桌位
    @FormUrlEncoded
    @POST(AppConstant.PORT_URL + "PortOrderManagerASHX.ashx")
    Observable<BaseEntity<String>> bindTable(
            @Field("type") String s,
            @Field("OrderID") String orderId,
            @Field("TableID") String tableId,
            @Field("id") String id,
            @Field("TableWareCount") String tableWareCount,
            @Field("Name") String name,
            @Field("TableName") String tableName);

    //交班打印
    @FormUrlEncoded
    @POST(AppConstant.PORT_URL + "PortPrinterManager.ashx")
    Observable<BaseEntity<String>> jiaoBanPrint(
            @Field("type") String s,
            @Field("printsouce") String s1,
            @Field("id") String shopID,
            @Field("Name") String name,
            @Field("price") String s2,
            @Field("userid") String id);

    //获取打折列表
    @FormUrlEncoded
    @POST(AppConstant.PORT_URL + "PortBillManagerNweASHX.ashx")
    Observable<BaseEntity<String>> getDiscountList(
            @Field("id") String shopID,
            @Field("type") String type);

    //权限打折
    @FormUrlEncoded
    @POST(AppConstant.PORT_URL + "PortBillManagerNweASHX.ashx")
    Observable<BaseEntity<String>> getDazhe(
            @Field("Type") String s,
            @Field("id") String billid,
            @Field("mid") String shopId,
            @Field("chengdazhe") String chengdazhe,
            @Field("dazhe") int dazhe,
            @Field("daid") String daId
    );

    //获取并单后菜品信息
    @FormUrlEncoded
    @POST(AppConstant.PORT_URL + "PortKaiDanAshx.ashx")
    Observable<BaseEntity<String>> getMergeOrderList(
            @Field("type") String s,
            @Field("id") String shopID,
            @Field("TABLEID") String tableId);

    //获取其他优惠
    @FormUrlEncoded
    @POST(AppConstant.MASTE_URL + "NewCardManagerQiTaASHX.ashx")
    Observable<BaseEntity<String>> getLineDownInfo(
            @Field("Type") String type,
            @Field("RID") String shopId,
            @Field("pageSize") int pageSize,
            @Field("pageIndex") int pageIndex,
            @Field("Product") String product
    );

    //其他优惠
    @FormUrlEncoded
    @POST(AppConstant.PORT_URL + "PortBillManagerNweASHX.ashx")
    Observable<BaseEntity<String>> getOherYouhui(
            @Field("Type") String type,
            @Field("daid") String couponId,
            @Field("bilid") String billId,
            @Field("id") String shopId,
            @Field("xiapice") double xiaPrice,
            @Field("yingfu") double yinFu,
            @Field("jsonObjquanxian") String json
    );

   //结账
    @FormUrlEncoded
    @POST(AppConstant.PORT_URL + "PortBillManagerNweASHX.ashx")
    Observable<BaseEntity<String>> bill(
            @Field("Type") String type,
            @Field("id") String id,
            @Field("Rid") String rid,
            @Field("MenberID") String memberID,
            @Field("TableId") String tableId,
            @Field("zon") double zon,
            @Field("can") double can,
            @Field("pei") double pei,
            @Field("dabao") double daBao,
            @Field("types") String types,
            @Field("jsonObjquanxian") String jsonQuanXian,
            @Field("jsonObj") String jsonObj,
            @Field("PayType") String payType,
            @Field("jsonPay") String jsonPay,
            @Field("GuaID") String guaID,
            @Field("PersonMonery") String personMoney,
            @Field("Changeid") String id1,
            @Field("Username") String name,
            @Field("zonweight") String zonwWeight,
            @Field("zonprice") String zonPrice,
            @Field("zonstate") String zonState,
            @Field("personcount") int personCount,
            @Field("price") double price,
            @Field("tablename") String tableName,
            @Field("MaLing") double maLing,
            @Field("Rounding") double rounding,
            @Field("free") double free);

    //反结账
    @FormUrlEncoded
    @POST(AppConstant.PORT_URL + "PortBillManagerNweASHX.ashx")
    Observable<BaseEntity<String>> reBill(
            @Field("Type") String type,
            @Field("id") String id,
            @Field("Rid") String rid,
            @Field("MenberID") String menberID,
            @Field("TableId") String tableId,
            @Field("zon") double zon,
            @Field("can") double can,
            @Field("pei") double pei,
            @Field("dabao") double dabao,
            @Field("types") String types,
            @Field("jsonObjquanxian") String jsonObjquanxian,
            @Field("jsonObj") String jsonObj,
            @Field("PayType") String payType,
            @Field("jsonPay") String jsonPay,
            @Field("GuaID") String guaID,
            @Field("PersonMonery") String personMonery,
            @Field("Changeid") String id1,
            @Field("Username") String name,
            @Field("zonweight") String zonweight,
            @Field("zonprice") String zonprice,
            @Field("zonstate") String zonstate,
            @Field("free") double free,
            @Field("price") double price,
            @Field("MaLing") double maLing,
            @Field("Rounding") double rounding,
            @Field("FnaBill") String fnaNill);

    //扫码支付
    @FormUrlEncoded
    @POST(AppConstant.PORT_URL + "PortBillManagerNweASHX.ashx")
    Observable<BaseEntity<String>> scanBill(
            @Field("type") String type,
            @Field("Code") String code,
            @Field("Pice") double price,
            @Field("id") String shopId,
            @Field("PayID") String payId
    );

    //微信支付
    @FormUrlEncoded
    @POST(AppConstant.PORT_URL + "PortBillManagerNweASHX.ashx")
    Observable<BaseEntity<String>> weixinBill(
            @Field("type") String type,
            @Field("id") String id,
            @Field("OrderID") String OrderID,
            @Field("Types") String Types,
            @Field("Price") int Price
    );

    //获取价格信息
    @FormUrlEncoded
    @POST(AppConstant.PORT_URL + "PortBillManagerNweASHX.ashx")
    Observable<BaseEntity<String>> getOrderData(
            @Field("Type") String type,
            @Field("id") String shopId,
            @Field("BILLID") String billId,
            @Field("Types") String types);

    //修改订单状态
    @FormUrlEncoded
    @POST(AppConstant.PORT_URL + "PortBillManagerNweASHX.ashx")
    Observable<BaseEntity<String>> inBill(
            @Field("Type") String type,
            @Field("BILLId") String billID);

    //获取订单信息
    @FormUrlEncoded
    @POST(AppConstant.PORT_URL + "PortOrderManagerASHX.ashx")
    Observable<BaseEntity<String>> getOrder(
            @Field("type") String s,
            @Field("Tableid") String roomTableID);

    //订单查询
    @FormUrlEncoded
    @POST(AppConstant.PORT_URL + "PortBillManagerNweASHX.ashx")
    Observable<BaseEntity<String>> query(
            @Field("type") String type,
            @Field("id") String id,
            @Field("OrderID") String OrderID
    );

    //获取挂账信息
    @FormUrlEncoded
    @POST(AppConstant.PORT_URL+ "PortKaiDanAshx.ashx")
    Observable<BaseEntity<String>> getGuazhangData(
            @Field("id") String shopID,
            @Field("Type") String type);


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
