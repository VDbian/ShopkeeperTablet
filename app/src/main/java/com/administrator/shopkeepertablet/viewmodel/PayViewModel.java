package com.administrator.shopkeepertablet.viewmodel;

import android.databinding.ObservableField;
import android.text.TextUtils;
import android.util.Log;

import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.model.entity.BaseEntity;
import com.administrator.shopkeepertablet.model.entity.CardEntity;
import com.administrator.shopkeepertablet.model.entity.DiscountEntity;
import com.administrator.shopkeepertablet.model.entity.ElseCouponEntity;
import com.administrator.shopkeepertablet.model.entity.GuaZhangEntity;
import com.administrator.shopkeepertablet.model.entity.MemberEntity;
import com.administrator.shopkeepertablet.model.entity.PayMeEntity;
import com.administrator.shopkeepertablet.model.entity.PriceEntity;
import com.administrator.shopkeepertablet.model.entity.TableEntity;
import com.administrator.shopkeepertablet.model.preference.PreferenceSource;
import com.administrator.shopkeepertablet.repository.parish.ParishRepertory;
import com.administrator.shopkeepertablet.utils.DateUtils;
import com.administrator.shopkeepertablet.utils.DialogUtils;
import com.administrator.shopkeepertablet.utils.MLog;
import com.administrator.shopkeepertablet.utils.MToast;
import com.administrator.shopkeepertablet.utils.Print;
import com.administrator.shopkeepertablet.view.ui.activity.parish.PayActivity;
import com.google.gson.Gson;
import com.iflytek.cloud.Setting;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Description:
 * Author CC
 * Time 2018/7/22
 */


public class PayViewModel extends BaseViewModel {
    private ParishRepertory parishRepertory;
    private PreferenceSource preferenceSource;
    private PayActivity activity;
    private Print print;
    public ObservableField<TableEntity> tableEntity = new ObservableField<>();
    public ObservableField<String> billId = new ObservableField<>();
    public ObservableField<String> roomName = new ObservableField<>("");
    public ObservableField<String> time = new ObservableField<>("");
    public ObservableField<Double> price = new ObservableField<>(0.0);//原价
    public ObservableField<Double> warePrice = new ObservableField<>(0.0);//餐具
    public ObservableField<Double> discount = new ObservableField<>(0.0);//优惠
    public ObservableField<Double> payment = new ObservableField<>(0.0);//预定
    public ObservableField<Double> shouldPay = new ObservableField<>(0.0);//应付
    public ObservableField<Double> shouldReturn = new ObservableField<>(0.0);//应退
    public ObservableField<Double> needPay = new ObservableField<>(0.0);//还需支付
    public ObservableField<List<TableEntity>> tableList = new ObservableField<>();
    public ObservableField<String> memberNum = new ObservableField<>("");
    public ObservableField<MemberEntity> member = new ObservableField<>();
    public ObservableField<String> integral = new ObservableField<>("");
    public ObservableField<List<CardEntity>> cardList = new ObservableField<>();
    public ObservableField<CardEntity> cardEntity = new ObservableField<>();
    public ObservableField<String> couponNum = new ObservableField<>("");
    public ObservableField<CardEntity> cardSearch = new ObservableField<>();
    public ObservableField<String> discountNum = new ObservableField<>();
    public ObservableField<List<DiscountEntity>> discountList = new ObservableField<>();
    public ObservableField<List<GuaZhangEntity>> guaZhangList = new ObservableField<>();
    public ObservableField<Double> permissionDiscount = new ObservableField<>(0.0);//权限打折
    public ObservableField<Double> permissionRemission = new ObservableField<>(0.0);//权限优惠
    public ObservableField<String> elsePermission = new ObservableField<>("");
    public ObservableField<Double> elsePrice = new ObservableField<>(0.0);//其他优惠金额
    public ObservableField<PriceEntity> priceEntity = new ObservableField<>();


    public PayViewModel(ParishRepertory parishRepertory, PreferenceSource preferenceSource, PayActivity activity) {
        this.parishRepertory = parishRepertory;
        this.preferenceSource = preferenceSource;
        this.activity = activity;
        this.print = new Print(parishRepertory);
    }

    public String getTime(String time) {
        return DateUtils.friendly_time(DateUtils.stringToDate(time));
    }

    public void getMember(String num) {
        DialogUtils.showDialog(activity, "会员信息查询中");
        parishRepertory.getMember("15", num, billId.get(), preferenceSource.getId())
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        MLog.e("vd", stringBaseEntity.toString());
                        DialogUtils.hintDialog();
                        if (stringBaseEntity.getCode() == 1) {
                            if (stringBaseEntity.getResult().equals("0")) {
                                MToast.showToast(activity, "查询失败");
                            } else {
                                String[] split = stringBaseEntity.getResult().split("/");
                                String[] memberStrs = split[0].split("@");
                                MemberEntity memberBean = new MemberEntity();
                                memberBean.setNo(memberStrs[2]);
                                memberBean.setName(memberStrs[0]);
                                memberBean.setPhone(memberStrs[1]);
                                memberBean.setScore(Integer.parseInt(memberStrs[3]));
                                memberBean.setMoney(Double.parseDouble(memberStrs[4]));
                                memberBean.setId(memberStrs[5]);
                                memberBean.setRate(Double.parseDouble(memberStrs[6]));
                                member.set(memberBean);
//                                MLog.e("vd",member.get().toString());
                                //笑笑@满30送5块@5.00@908fe556-e5b7-4446-ac26-0848c991061c@1^
                                List<CardEntity> cardBeanList = new ArrayList<CardEntity>();
                                if (split.length >= 2 && !TextUtils.isEmpty(split[1])) {
                                    String[] strings = split[1].split("\\^");
                                    for (String str : strings) {
                                        String[] cardStr = str.split("@");
                                        CardEntity cardBean = new CardEntity();
                                        cardBean.setId(cardStr[3]);
                                        cardBean.setMoney(Double.parseDouble(cardStr[2]));
                                        cardBean.setType(cardStr[4]);
                                        cardBean.setName(cardStr[1]);
                                        cardBean.setUsername(cardStr[0]);
                                        cardBean.setManPrice(Double.parseDouble(cardStr[5]));
                                        cardBean.setBeginTime(cardStr[6]);
                                        cardBean.setEndTime(cardStr[7]);
                                        MLog.e("vd", cardBean.toString());
                                        cardBeanList.add(cardBean);
                                    }
                                }
                                cardList.set(cardBeanList);
                                activity.searchSuccess();
                            }
                        } else {
                            MToast.showToast(activity, "查询失败");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        DialogUtils.hintDialog();
                        MToast.showToast(activity, "查询失败");
                    }
                });
    }

    public void getDiscount(String num) {
        DialogUtils.showDialog(activity, "优惠券信息查询中");
        parishRepertory.getDiscount("2", billId.get(), num).subscribe(
                new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        DialogUtils.hintDialog();
                        MLog.e("vd", stringBaseEntity.toString());
                        if (stringBaseEntity.getCode() == 1) {
                            String[] cardStr = stringBaseEntity.getResult().split("@");
                            CardEntity cardBean = new CardEntity();
                            cardBean.setId(cardStr[3]);
                            cardBean.setMoney(Double.parseDouble(cardStr[2]));
                            cardBean.setType(cardStr[4]);
                            cardBean.setName(cardStr[1]);
                            cardBean.setUsername(cardStr[0]);
                            cardBean.setManPrice(Double.parseDouble(cardStr[5]));
                            cardBean.setBeginTime(cardStr[6]);
                            cardBean.setEndTime(cardStr[7]);
                            cardSearch.set(cardBean);
                            activity.searchSuccess();
                        }else {
                            MToast.showToast(activity,"查询失败");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        MLog.e("vd",throwable.getMessage());
                        DialogUtils.hintDialog();
                        MToast.showToast(activity,"查询失败");
                    }
                }
        );
    }

    public void getDiscountList() {
        DialogUtils.showDialog(activity, "获取数据中");
        parishRepertory.getDiscountList(preferenceSource.getId(), "12")
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        MLog.e("vd", stringBaseEntity.toString());
                        DialogUtils.hintDialog();
                        if (stringBaseEntity.getCode() == 1) {
                            DiscountEntity[] discountEntities = new Gson().fromJson(stringBaseEntity.getResult(), DiscountEntity[].class);
                            discountList.set(Arrays.asList(discountEntities));
                            activity.showDialogDiscount();
                        } else {
                            activity.showDialogDiscount();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        DialogUtils.hintDialog();
                        MToast.showToast(activity,"折扣信息获取失败");
                        activity.showDialogDiscount();
                    }
                });
    }

    public void getDazhe(String billId, int num, String id) {
        DialogUtils.showDialog(activity, "数据提交中");
        parishRepertory.getDazhe("24", billId, preferenceSource.getId(), "0", num, id)
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        MLog.e("vd", stringBaseEntity.toString());
                        DialogUtils.hintDialog();
                        if (stringBaseEntity.getCode() == 1) {
                            if (stringBaseEntity.getResult().equals("0")) {
                                MToast.showToast(activity, "存在无法打折菜品");
                            } else {
                                MToast.showToast(activity, "打折优惠价格为" + stringBaseEntity.getResult());
                                activity.discountSuccess(stringBaseEntity.getResult());
                            }
                        }else {
                            MToast.showToast(activity,"打折金额获取失败");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        DialogUtils.hintDialog();
                        MToast.showToast(activity,"打折金额获取失败");
                    }
                });
    }

    public void getElseCoupon() {
        DialogUtils.showDialog(activity, "获取数据中");
        parishRepertory.getLineDownInfo("5", preferenceSource.getId(), 20, 1, "")
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        DialogUtils.hintDialog();
                        MLog.e("vd", stringBaseEntity.toString());
                        if (stringBaseEntity.getCode() == 1) {
                            List<ElseCouponEntity> elseCouponEntities = Arrays.asList(new Gson().fromJson(stringBaseEntity.getResult(), ElseCouponEntity[].class));
                            activity.showDialogElseDiscount(elseCouponEntities);
                        } else {
                            MToast.showToast(activity, "其他优惠信息获取失败");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        DialogUtils.hintDialog();
                        MToast.showToast(activity, "其他优惠信息获取失败");
                    }
                });
    }

    public void getOtherYouhui(String couponId, String billId, double couponPrice, double yingFu, String json) {
        DialogUtils.showDialog(activity, "数据提交中");
        parishRepertory.getOherYouhui("23", couponId, billId, preferenceSource.getId(), couponPrice, yingFu, json)
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        DialogUtils.hintDialog();
                        MLog.e("vd", stringBaseEntity.toString());
                        if (stringBaseEntity.getCode() == 1) {
                            if (stringBaseEntity.getResult().equals("0")) {
                                MToast.showToast(activity, "获取其他优惠金额失败");
                            } else {
                                activity.elseCouponSuccess(Double.parseDouble(stringBaseEntity.getResult()));
                            }
                        }else {
                            MToast.showToast(activity,"获取其他优惠金额失败");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        DialogUtils.hintDialog();
                        MToast.showToast(activity,"获取其他优惠金额失败");
                    }
                });
    }

    public void bill(String id, String tableId, double zon, double can, String jsonObjquanxian,
                     String jsonObj, String jsonPay, int peoplecount, double price, String tablename, double free,
                     String types, String guiId, String payType, double maling, double rounding) {
        DialogUtils.showDialog(activity, "数据提交中");
        parishRepertory.bill("3", id, preferenceSource.getId(), member.get() == null ? "" : member.get().getId(), tableId, zon, can, 0, 0, types, jsonObjquanxian, jsonObj,
                payType, jsonPay, guiId, "", preferenceSource.getId(), preferenceSource.getName(), "", "", "",
                peoplecount, price, tablename, maling, rounding, free).subscribe(new Consumer<BaseEntity<String>>() {
            @Override
            public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                DialogUtils.hintDialog();
                MLog.e("vd", stringBaseEntity.toString());
                if (stringBaseEntity.getCode() == 1) {
                    if (stringBaseEntity.getResult().equals("0")) {
                        MToast.showToast(activity, "结账失败");
                    } else {
                        activity.billSuccess("结账成功");
                        new Thread(() -> print.socketDataArrivalHandler(stringBaseEntity.getResult())).start();
                    }
                }else {
                    MToast.showToast(activity,"结账失败");
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                DialogUtils.hintDialog();
                MToast.showToast(activity,"结账失败");
            }
        });
    }

    public void getOrderData(int type) {
        DialogUtils.showDialog(activity, "获取数据中");
//        MLog.e("vd",billId.get());
        parishRepertory.getOrderData("17", preferenceSource.getId(), billId.get(), String.valueOf(type))
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        DialogUtils.hintDialog();
                        MLog.e("vd", stringBaseEntity.toString());
                        if (stringBaseEntity.getCode() == 1) {
                            List<PriceEntity> priceEntities = Arrays.asList(new Gson().fromJson(stringBaseEntity.getResult(), PriceEntity[].class));
                            if (!priceEntities.isEmpty()) {
                                priceEntity.set(priceEntities.get(0));
                                price.set(priceEntity.get().getYuanjia());
                                shouldPay.set(priceEntity.get().getYinfu());
                                discount.set(priceEntity.get().getYouhui());
                                warePrice.set(priceEntity.get().getCanju());
                                activity.changedMoney(true);
                                MLog.e("vd", priceEntity.get().toString());
                            }
                        }else {
                            MToast.showToast(activity,"订单价格信息获取失败");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        DialogUtils.hintDialog();
                        MLog.e("vd",throwable.getMessage());
                        MToast.showToast(activity,"订单价格信息获取失败");
                    }
                });
    }

    public void scanBill(String code, double price, String billId) {
        DialogUtils.showDialog(activity, "获取数据中");
        Log.e("vd",code+"**"+price+"**"+ preferenceSource.getId()+"**"+billId);
        parishRepertory.scanBill("21", code, price, preferenceSource.getId(), billId).subscribe(
                new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        DialogUtils.hintDialog();
                        MLog.e("vd", stringBaseEntity.toString());
                        if (stringBaseEntity.getCode() == 1) {
                            if (stringBaseEntity.getResult().contains("SUCCESS")) {
                                String parType[] = stringBaseEntity.getResult().split("&");
                                activity.scanBillSuccess(parType[1], billId, price, "", "");
                            } else if (stringBaseEntity.getResult().contains("FAILED")) {
                                MToast.showToast(activity, "支付失败");
                            } else if (stringBaseEntity.getResult().contains("UNKNOWN")) {
                                MToast.showToast(activity, "支付错误");
                            } else if (stringBaseEntity.getResult().contains("USERPAYING")) {
                                activity.scanBillSuccess("3", billId, price, "", "用户正在支付中");
                            } else if (stringBaseEntity.getResult().contains("ORDERPAID")) {
                                MToast.showToast(activity, "订单已支付");
                            } else if (stringBaseEntity.getResult().contains("AUTHCODEEXPIRE")) {
                                MToast.showToast(activity, "二维码已过期");
                            } else if (stringBaseEntity.getResult().contains("NOTENOUGH")) {
                                MToast.showToast(activity, "余额不足");
                            } else if (stringBaseEntity.getResult().contains("OUT_TRADE_NO_USED")) {
                                MToast.showToast(activity, "订单号重复");
                            } else if (stringBaseEntity.getResult().contains("QITA")) {
                                MToast.showToast(activity, "其他错误");
                            } else if (stringBaseEntity.getResult().contains("CODEUNKNOWN")) {
                                MToast.showToast(activity, "二维码错误");
                            } else {
                                String parType[] = stringBaseEntity.getResult().split("&");
                                activity.scanBillSuccess(parType[1], billId, price, parType[0], "");
                            }
                        }else {
                            MToast.showToast(activity,"支付失败");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        DialogUtils.hintDialog();
                        Log.e("vd",throwable.getMessage());
                        MToast.showToast(activity,"支付失败");
                    }
                }
        );
    }

    public void weixinBill(String orderId, String weixinResult, double price) {
        DialogUtils.showDialog(activity, "获取数据中");
        parishRepertory.weixinBill("9", preferenceSource.getId(), orderId, weixinResult, (int) (price * 100))
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        DialogUtils.hintDialog();
                        if (stringBaseEntity.getCode() == 1) {
                            JSONObject jsonObject = new JSONObject(stringBaseEntity.getResult());
                            if (jsonObject.optString("result_code").equals("SUCCESS")) {
                                activity.weixinSuccess();

                            } else if (jsonObject.optString("result_code").equals("FAIL")) {
                                MToast.showToast(activity, jsonObject.optString("err_code_des"));
                                if (jsonObject.optString("err_code").equals("USERPAYING")) {//用户输入密码
                                    queryOrder(orderId);
                                }
                            } else {
                                MToast.showToast(activity, "微信扫描结算失败");
                            }
                        } else {
                            MToast.showToast(activity, "微信扫描结算失败");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        DialogUtils.hintDialog();
                        MToast.showToast(activity,"微信扫描结算失败");
                    }
                });

    }

    public void queryOrder(String orderId) {
        DialogUtils.showDialog(activity, "获取数据中");
        parishRepertory.query("10", preferenceSource.getId(), orderId).subscribe(
                new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        DialogUtils.hintDialog();
                        if (stringBaseEntity.getCode()==1) {
                            JSONObject jsonObject = new JSONObject(stringBaseEntity.getResult());
                            String result_code = jsonObject.optString("result_code");
                            String return_code = jsonObject.optString("return_code ");
                            String trade_state = jsonObject.optString("trade_state ");
                            if (result_code.equals("SUCCESS") && return_code.equals("SUCCESS")) {
                                switch (trade_state) {
                                    case "SUCCESS":
                                        activity.weixinSuccess();
                                        break;
                                    case "REFUND":
                                        MToast.showToast(activity, "转入退款");
                                        break;
                                    case "NOTPAY":
                                        MToast.showToast(activity, "未支付");
                                        break;
                                    case "CLOSED":
                                        MToast.showToast(activity, "已关闭");
                                        break;
                                    case "REVOKED":
                                        MToast.showToast(activity, "已撤销");
                                        break;
                                    case "USERPAYING":
                                        queryOrder(orderId);
                                        break;
                                    case "PAYERROR":
                                        MToast.showToast(activity, "支付失败");
                                        break;
                                }
                            } else if (jsonObject.optString("result_code").equals("FAIL")) {
                                if (jsonObject.optString("err_code").equals("ORDERNOTEXIST")) {
                                    MToast.showToast(activity, "订单不存在");
                                } else {
                                    MToast.showToast(activity, "查询订单失败");
                                }
                            }

                        } else {
                            MToast.showToast(activity, "查询订单失败");
                        }
                    }
                },
                new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        DialogUtils.hintDialog();
                        MToast.showToast(activity,"查询订单失败");
                    }
                }
        );
    }

    public void guaZhang(PayMeEntity entity){
        DialogUtils.showDialog(activity, "获取数据中");
        parishRepertory.getGuazhangData(preferenceSource.getId(),"17").subscribe(
                new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        MLog.e("vd",stringBaseEntity.toString());
                        DialogUtils.hintDialog();
                        if (stringBaseEntity.getCode()==1) {
                            if (stringBaseEntity.getResult().equals("0")) {
                                MToast.showToast(activity,"挂账失败");
                            } else {
                                GuaZhangEntity[] guaZhangBeen = new Gson().fromJson(stringBaseEntity.getResult(), GuaZhangEntity[].class);
                                activity.guazhangSuccess(Arrays.asList(guaZhangBeen), entity);
                            }
                        } else {
                            MToast.showToast(activity,"挂账失败");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        DialogUtils.hintDialog();
                        MToast.showToast(activity,"挂账失败");
                    }
                }
        );
    }

    public void rebill(String id,  String TableId, double zon, double can, String jsonObjquanxian,
                       String jsonObj, String jsonPay, String types, double free, String fanBill, double price, double maling, double rounding) {
        DialogUtils.showDialog(activity, "结账中...");
        parishRepertory.reBill("3", id, preferenceSource.getId(), member.get() == null ? "" : member.get().getId(), TableId, zon, can, 0, 0,
                types, jsonObjquanxian, jsonObj, "4", jsonPay, "", "", preferenceSource.getId(), preferenceSource.getName(), "",
                "", "", free, price, maling, rounding, fanBill)
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        DialogUtils.hintDialog();
                        if (stringBaseEntity.getCode() == 1) {
                            if (stringBaseEntity.getResult().equals("0")) {
                                MToast.showToast(activity, "反结账失败");
                            } else {
                                activity.billSuccess("反结账成功");
                                new Thread(() -> print.socketDataArrivalHandler(stringBaseEntity.getResult())).start();
                            }
                        } else {
                            MToast.showToast(activity, "反结账失败");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        DialogUtils.hintDialog();
                        MToast.showToast(activity, "反结账失败");
                    }
                });
    }


    public void print(String billid, int personcount, String tableid, String tablename, double priceold, double price, double free, String state) {
        DialogUtils.showDialog(activity, "账单预打中...");
        parishRepertory.print("3",preferenceSource.getId(),"1",state,billid,preferenceSource.getName(),personcount
            ,tableid,tablename,priceold,price,free,"1").subscribe(new Consumer<BaseEntity<String>>() {
            @Override
            public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                DialogUtils.hintDialog();
                MLog.e("vd",stringBaseEntity.toString());
                if (stringBaseEntity.getCode()==1){
                    if (stringBaseEntity.getResult().equals("0")){
                        MToast.showToast(activity, "账单预打失败");
                    }else {
                        MToast.showToast(activity, "账单预打成功");
                        new Thread(() -> print.socketDataArrivalHandler(stringBaseEntity.getResult())).start();
                    }
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                DialogUtils.hintDialog();
                MToast.showToast(activity, "账单预打失败");
            }
        });

    }
}
