package com.administrator.shopkeepertablet.model.api;

import com.administrator.shopkeepertablet.AppConstant;
import com.administrator.shopkeepertablet.model.entity.BaseEntity;
import com.administrator.shopkeepertablet.model.entity.ResultFoodEntity;
import com.administrator.shopkeepertablet.model.entity.UserInfoEntity;

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


}
