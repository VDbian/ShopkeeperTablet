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

}
