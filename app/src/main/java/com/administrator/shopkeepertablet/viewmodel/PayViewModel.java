package com.administrator.shopkeepertablet.viewmodel;

import android.databinding.ObservableField;
import android.text.TextUtils;
import android.util.Log;

import com.administrator.shopkeepertablet.model.entity.BaseEntity;
import com.administrator.shopkeepertablet.model.entity.CardEntity;
import com.administrator.shopkeepertablet.model.entity.DiscountEntity;
import com.administrator.shopkeepertablet.model.entity.ElseCouponEntity;
import com.administrator.shopkeepertablet.model.entity.MemberEntity;
import com.administrator.shopkeepertablet.model.entity.TableEntity;
import com.administrator.shopkeepertablet.model.preference.PreferenceSource;
import com.administrator.shopkeepertablet.repository.parish.ParishRepertory;
import com.administrator.shopkeepertablet.utils.DateUtils;
import com.administrator.shopkeepertablet.utils.MToast;
import com.administrator.shopkeepertablet.view.ui.activity.parish.PayActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Description:
 * Author CC
 * Time 2018/7/22
 */


public class PayViewModel extends BaseViewModel {
    private ParishRepertory parishRepertory;
    private PreferenceSource preferenceSource;
    private PayActivity activity;
    public ObservableField<TableEntity> tableEntity = new ObservableField<>();
    public ObservableField<String> roomName = new ObservableField<>("");
    public ObservableField<String> time = new ObservableField<>("");
    public ObservableField<Double> price = new ObservableField<>(0.0);
    public ObservableField<Double> warePrice = new ObservableField<>(0.0);
    public ObservableField<Double> discount = new ObservableField<>(0.0);
    public ObservableField<Double> payment = new ObservableField<>(0.0);
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
    public ObservableField<Double> permissionDiscount = new ObservableField<>(0.0);
    public ObservableField<Double> permissionRemission = new ObservableField<>(0.0);

    public PayViewModel(ParishRepertory parishRepertory, PreferenceSource preferenceSource, PayActivity activity) {
        this.parishRepertory = parishRepertory;
        this.preferenceSource = preferenceSource;
        this.activity = activity;
    }

    public String getTime(String time) {
        return DateUtils.friendly_time(DateUtils.stringToDate(time));
    }

    public double discountPer() {
        return permissionDiscount.get() + permissionRemission.get();
    }

    public void getMember(String num) {
        String bill = tableEntity.get().getBillId();
        if (tableList.get() != null && !tableList.get().isEmpty()) {
            for (TableEntity entity : tableList.get()) {
                bill += "," + entity.getBillId();
            }
        }
        parishRepertory.getMember("15", num, bill, preferenceSource.getId())
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        Log.e("vd", stringBaseEntity.toString());
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
//                                Log.e("vd",member.get().toString());
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
                                        Log.e("vd", cardBean.toString());
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

                    }
                });
    }

    public Double getShouldPay() {
        return price.get() + warePrice.get() - discount.get();
    }

    public Double getShouldReturn() {
        return (payment.get() - getShouldPay()) > 0 ? payment.get() - getShouldPay() : 0;
    }

    public Double getPay() {
        return (getShouldPay() - payment.get()) > 0 ? getShouldPay() - payment.get() : 0;
    }

    public void getDiscount(String num) {
        String bill = tableEntity.get().getBillId();
        if (tableList.get() != null && !tableList.get().isEmpty()) {
            for (TableEntity entity : tableList.get()) {
                bill += "," + entity.getBillId();
            }
        }
        parishRepertory.getDiscount("2", bill, num).subscribe(
                new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        Log.e("vd", stringBaseEntity.toString());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                }
        );
    }

    public void getDiscountList() {
        parishRepertory.getDiscountList(preferenceSource.getId(), "12")
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        Log.e("vd", stringBaseEntity.toString());
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
                        activity.showDialogDiscount();
                    }
                });
    }

    public void getDazhe(String billId, int num, String id) {
        parishRepertory.getDazhe("24", billId, preferenceSource.getId(), "0", num, id)
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        Log.e("vd", stringBaseEntity.toString());
                        if (stringBaseEntity.getCode() == 1) {
                            if (stringBaseEntity.getResult().equals("0")) {
                                MToast.showToast(activity, "存在无法打折菜品");
                            } else {
                                MToast.showToast(activity, "打折优惠价格为" + stringBaseEntity.getResult());
                                activity.discountSuccess(stringBaseEntity.getResult());
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    public void getElseCoupon(){
        parishRepertory.getLineDownInfo("5",preferenceSource.getId(),20,1,"")
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        Log.e("vd",stringBaseEntity.toString());
                        if (stringBaseEntity.getCode()==1) {
                            List<ElseCouponEntity> elseCouponEntities =Arrays.asList( new Gson().fromJson(stringBaseEntity.getResult(), ElseCouponEntity[].class));
                            activity.showDialogElseDiscount(elseCouponEntities);
                        } else {
                           MToast.showToast(activity,"加载失败");
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        MToast.showToast(activity,"加载失败");
                    }
                });
    }

    public void getOtherYouhui(String couponId, String billId, double couponPrice, double yingFu, String json){
        parishRepertory.getOherYouhui("23",couponId,billId,preferenceSource.getId(),couponPrice,yingFu,json)
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                           Log.e("vd",stringBaseEntity.toString());
                            if (stringBaseEntity.getCode() ==1){
                                if (stringBaseEntity.getResult().equals("0")){
                                    MToast.showToast(activity,"获取其他优惠金额失败");
                                }else {
                                    activity.elseCouponSuccess(Double.parseDouble(stringBaseEntity.getResult()));
                                }
                            }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

}
