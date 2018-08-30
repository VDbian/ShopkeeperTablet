package com.administrator.shopkeepertablet.viewmodel;

import android.app.Dialog;
import android.databinding.ObservableField;
import android.util.Log;

import com.administrator.shopkeepertablet.AppApplication;
import com.administrator.shopkeepertablet.model.entity.BaseEntity;
import com.administrator.shopkeepertablet.model.entity.FoodEntity;
import com.administrator.shopkeepertablet.model.entity.FoodTypeEntity;
import com.administrator.shopkeepertablet.model.entity.FoodTypeSelectEntity;
import com.administrator.shopkeepertablet.model.entity.KouWeiEntity;
import com.administrator.shopkeepertablet.model.entity.OrderEntity;
import com.administrator.shopkeepertablet.model.entity.OrderFoodEntity;
import com.administrator.shopkeepertablet.model.entity.RoomEntity;
import com.administrator.shopkeepertablet.model.entity.TableEntity;
import com.administrator.shopkeepertablet.model.entity.bean.PayType;
import com.administrator.shopkeepertablet.model.greendao.DaoSession;
import com.administrator.shopkeepertablet.model.greendao.FoodEntityDao;
import com.administrator.shopkeepertablet.model.preference.PreferenceSource;
import com.administrator.shopkeepertablet.repository.BaseRepertory;
import com.administrator.shopkeepertablet.repository.fast.FastRepository;
import com.administrator.shopkeepertablet.utils.DialogUtils;
import com.administrator.shopkeepertablet.utils.MLog;
import com.administrator.shopkeepertablet.utils.MToast;
import com.administrator.shopkeepertablet.utils.Print;
import com.administrator.shopkeepertablet.view.ui.fragment.FastFoodFragment;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Description:
 * Author CC
 * Time 2018/7/23
 */


public class FastViewModel extends BaseViewModel {
    private FastFoodFragment fragment;
    private PreferenceSource preferenceSource;
    private FastRepository fastRepository;

    private DaoSession dao;
    private Print print;
    public ObservableField<Double> price =new ObservableField<>(0.0);
    public ObservableField<String> table =new ObservableField<>("");
    public ObservableField<String> search =new ObservableField<>("");
    public List<RoomEntity> roomEntityList = new ArrayList<>();
    public List<TableEntity> tableEntityList = new ArrayList<>();

    public FastViewModel(FastFoodFragment fragment, PreferenceSource preferenceSource, FastRepository fastRepository) {
        this.fragment = fragment;
        this.preferenceSource = preferenceSource;
        this.fastRepository = fastRepository;
        this.print = new Print(fastRepository);
        this.dao = AppApplication.get(fragment.getActivity()).getDaoSession();
    }

    private void printResult(final String result) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                print.socketDataArrivalHandler(result);
            }
        }).start();
    }

    public void getFoodList() {
        List<FoodEntity> foodEntities = dao.getFoodEntityDao().loadAll();
        if (foodEntities.size() > 0) {
            fragment.refreshVariety(foodEntities);
        } else {
            MToast.showToast(fragment.getActivity(), "请先刷新菜品");
        }
    }

    public void getFoodType() {
        List<FoodTypeEntity> foodTypeEntities = dao.getFoodTypeEntityDao().loadAll();
        List<FoodTypeSelectEntity> selectList = new ArrayList<>();
        for (int i = 0; i < foodTypeEntities.size(); i++) {
            FoodTypeSelectEntity selectEntity = new FoodTypeSelectEntity();
//            if (i==0){
//                selectEntity.setSelect(true);
//            }else {
            selectEntity.setSelect(false);
//            }
            selectEntity.setFoodTypeEntity(foodTypeEntities.get(i));
            selectList.add(selectEntity);
        }
        fragment.refreshFoodType(selectList);
//        for (FoodTypeEntity foodTypeEntity:foodTypeEntities){
//            MLog.e("api",foodTypeEntity.toString());
//        }

    }

    public void search(String str){
        Log.e("vd",str);
        List<FoodEntity> foodEntities = dao.getFoodEntityDao().queryBuilder().where(FoodEntityDao.Properties.ProductName.like("%" + str + "%")).list();;
        if (foodEntities.size() > 0) {
            fragment.refreshVariety(foodEntities);
        } else {
            MToast.showToast(fragment.getActivity(), "无查询结果");
        }
    }

    //快餐
    public void fastFood(String info,int type,String billType,String tableId,String tableName){
        DialogUtils.showDialog(fragment.getActivity(), "数据提交中");
        fastRepository.fastFood("0","",preferenceSource.getId(),info,"","","","","",
                preferenceSource.getUserId(),preferenceSource.getName(),"",0,tableId,tableName,"4",""
        ,price.get(),billType).subscribe(new Consumer<BaseEntity<String>>() {
            @Override
            public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                Log.e("vd",stringBaseEntity.toString());
                DialogUtils.hintDialog();
                if (stringBaseEntity.getCode()==1){
                    MToast.showToast(fragment.getActivity(),"下单成功");
                    printResult(stringBaseEntity.getResult());
                    fragment.fastSuccess(stringBaseEntity.getResult(),type);

                }else{
                    MToast.showToast(fragment.getActivity(),"下单失败");
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                DialogUtils.hintDialog();
                MToast.showToast(fragment.getActivity(),"下单失败");
            }
        });
    }

    //预定
    public void reserve(String info,String name,String phone,String remark,double money){
        DialogUtils.showDialog(fragment.getActivity(), "数据提交中");
        fastRepository.fastFood("0","",preferenceSource.getId(),info,"","",name,"",phone,
                preferenceSource.getUserId(),preferenceSource.getName(),remark,money,"","","1",""
                ,price.get(),"0").subscribe(new Consumer<BaseEntity<String>>() {
            @Override
            public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                Log.e("vd",stringBaseEntity.toString());
                DialogUtils.hintDialog();
                if (stringBaseEntity.getCode()==1){
                    MToast.showToast(fragment.getActivity(),"预定成功");
                    fragment.reserveSuccess(stringBaseEntity.getResult());
                }else {
                    MToast.showToast(fragment.getActivity(),"预定失败");
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                DialogUtils.hintDialog();
                MToast.showToast(fragment.getActivity(),"预定失败");
            }
        });
    }

    public void  getAllKouwei(){
        List<KouWeiEntity> list = AppApplication.get(fragment.getActivity()).getDaoSession().getKouWeiEntityDao().loadAll();
        fragment.showPopAllKouwei(list);
    }

    public void getRooms() {
        DialogUtils.showDialog(fragment.getActivity(), "获取数据中");
        fastRepository.getRooms("1", preferenceSource.getId(), 1, 100)
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        MLog.e("api_room", stringBaseEntity.getResult());
                        DialogUtils.hintDialog();
                        if (stringBaseEntity.getCode() == 1) {
                            List<RoomEntity> rooms = Arrays.asList(new Gson().fromJson(stringBaseEntity.getResult(), RoomEntity[].class));
                            roomEntityList = rooms;
                            if (!rooms.isEmpty()) {
                                getTables(rooms.get(0));
                            }
                        }else {
                            MToast.showToast(fragment.getActivity(),"获取房间信息失败");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        DialogUtils.hintDialog();
                        MToast.showToast(fragment.getActivity(),"获取房间信息失败");
                    }
                });
    }

    public void getTables(RoomEntity roomEntity) {
        DialogUtils.showDialog(fragment.getActivity(), "获取数据中");
        fastRepository.getTables("0", roomEntity.getId(), preferenceSource.getId(), 1, 100)
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        MLog.e("api_table", stringBaseEntity.getResult());
                        DialogUtils.hintDialog();
                        if (stringBaseEntity.getCode() == 1) {
                            TableEntity[] tableEntities = new Gson().fromJson(stringBaseEntity.getResult(), TableEntity[].class);
                            List<TableEntity> mList = Arrays.asList(tableEntities);
                            tableEntityList.clear();
                            for (TableEntity entity : mList) {
                                if (entity.getIsOpen().equals("0")) {
                                    tableEntityList.add(entity);
                                }
                            }
                            fragment.showBindDialog();
                        }else {
                            MToast.showToast(fragment.getActivity(),"获取桌位信息失败");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        DialogUtils.hintDialog();
                        MToast.showToast(fragment.getActivity(),"获取桌位信息失败");
                    }
                });
    }

    public void getPay(String result){
        fastRepository.getPay(preferenceSource.getId(),"13").subscribe(new Consumer<BaseEntity<String>>() {
            @Override
            public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                if (stringBaseEntity.getCode() == 1) {
                    PayType[] payTypes = new Gson().fromJson(stringBaseEntity.getResult(), PayType[].class);
                    if (payTypes != null && payTypes.length > 0) {
                        fragment.bill(payTypes[0].getPayType(), result, price.get(),"","");
                    } else {
                        MToast.showToast(fragment.getActivity(),"支付失败");
                    }
                }else {
                    MToast.showToast(fragment.getActivity(),"支付失败");
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                MToast.showToast(fragment.getActivity(),"支付失败");
            }
        });
    }

    public void getOrderFoodList(String billId,boolean b) {
        DialogUtils.showDialog(fragment.getActivity(), "获取数据中");
        fastRepository.getOrderFoodList("13", preferenceSource.getId(), billId).subscribe(
                new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        DialogUtils.hintDialog();
                        Log.e("vd_order", stringBaseEntity.getCode() + "--" + stringBaseEntity.getResult());
                        if (stringBaseEntity.getCode() == 1) {
                            OrderFoodEntity[] orderFoodEntities = new Gson().fromJson(stringBaseEntity.getResult(), OrderFoodEntity[].class);
                            List<OrderFoodEntity> mList = Arrays.asList(orderFoodEntities);
                            fragment.intentToPay(mList,billId,b);
//                            activity.showDialogMerge();
                        }else {
                            MToast.showToast(fragment.getActivity(),"获取订单菜品信息失败");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        DialogUtils.hintDialog();
                        Log.e("VD", throwable.getMessage());
                        MToast.showToast(fragment.getActivity(),"获取订单菜品信息失败");
                    }
                }
        );
    }

    public void scanBill(String code, double price, String billId) {
        DialogUtils.showDialog(fragment.getActivity(), "数据提交中...");
        fastRepository.scanBill("21", code, price,preferenceSource.getId(), billId)
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        DialogUtils.hintDialog();
                        if(stringBaseEntity.getCode()==1){
                            if (stringBaseEntity.getResult().contains("SUCCESS")) {
                                String parType[] = stringBaseEntity.getResult().split("&");
                                fragment.bill(parType[1] ,billId , price ,"" ,"");
                            }else if(stringBaseEntity.getResult().contains("FAILED")){
                                MToast.showToast(fragment.getActivity(),"支付失败");
                            }else if(stringBaseEntity.getResult().contains("UNKNOWN")){
                                MToast.showToast(fragment.getActivity(),"支付错误");
                            }else if(stringBaseEntity.getResult().contains("USERPAYING")){
                                fragment.bill("3" ,billId , price ,"" ,"支付中");
                            }else if(stringBaseEntity.getResult().contains("ORDERPAID")){
                                MToast.showToast(fragment.getActivity(),"订单已支付");
                            }else if(stringBaseEntity.getResult().contains("AUTHCODEEXPIRE")){
                                MToast.showToast(fragment.getActivity(),"二维码已过期");
                            }else if(stringBaseEntity.getResult().contains("NOTENOUGH")){
                                MToast.showToast(fragment.getActivity(),"余额不足");
                            }else if(stringBaseEntity.getResult().contains("OUT_TRADE_NO_USED")){
                                MToast.showToast(fragment.getActivity(),"订单号重复");
                            }else if(stringBaseEntity.getResult().contains("QITA")){
                                MToast.showToast(fragment.getActivity(),"其他错误");
                            }else if(stringBaseEntity.getResult().contains("CODEUNKNOWN")){
                                MToast.showToast(fragment.getActivity(),"二维码错误");
                            }else{
                                String parType[] = stringBaseEntity.getResult().split("&");
                                fragment.bill(parType[1] ,billId , price ,parType[0],"");
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        DialogUtils.hintDialog();
                        MToast.showToast(fragment.getActivity(),"支付失败");
                    }
                });

    }

    public void bill(String id, String TableId, double zon, double can, String jsonObjquanxian,
                     String jsonObj, String jsonPay, String payType, int peoplecount, double price, String tablename, double free, String types , String memberID) {
        DialogUtils.showDialog(fragment.getActivity(), "数据提交中");
        fastRepository.bill("3", id, preferenceSource.getId(), memberID, TableId, zon, can, 0, 0, types, jsonObjquanxian, jsonObj, payType, jsonPay,
                "", "", preferenceSource.getUserId(), preferenceSource.getName(), "", "", "", peoplecount,
                price, tablename, free,0,0).subscribe(new Consumer<BaseEntity<String>>() {
            @Override
            public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                DialogUtils.hintDialog();
                Log.e("vd", stringBaseEntity.toString());
                if (stringBaseEntity.getCode() == 1) {
                    if (stringBaseEntity.getResult().equals("0")) {
                        MToast.showToast(fragment.getActivity(), "结账失败");
                    } else {
                        fragment.billSuccess("结账成功");
                        new Thread(() -> print.socketDataArrivalHandler(stringBaseEntity.getResult())).start();
                    }
                }else {
                    MToast.showToast(fragment.getActivity(),"结账失败");
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                DialogUtils.hintDialog();
                MToast.showToast(fragment.getActivity(),"结账失败");
            }
        });
    }

}
