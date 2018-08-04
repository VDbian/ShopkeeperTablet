package com.administrator.shopkeepertablet.viewmodel;

import android.databinding.ObservableField;
import android.text.TextUtils;
import android.util.Log;

import com.administrator.shopkeepertablet.model.entity.BaseEntity;
import com.administrator.shopkeepertablet.model.entity.CardEntity;
import com.administrator.shopkeepertablet.model.entity.MemberEntity;
import com.administrator.shopkeepertablet.model.entity.TableEntity;
import com.administrator.shopkeepertablet.model.preference.PreferenceSource;
import com.administrator.shopkeepertablet.repository.parish.ParishRepertory;
import com.administrator.shopkeepertablet.utils.DateUtils;
import com.administrator.shopkeepertablet.utils.MToast;
import com.administrator.shopkeepertablet.view.ui.activity.parish.PayActivity;

import java.util.ArrayList;
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
    public ObservableField<MemberEntity> member = new ObservableField<>();
    public ObservableField<String> integral =new ObservableField<>("");
    public ObservableField<List<CardEntity>> cardList =new ObservableField<>();

    public PayViewModel(ParishRepertory parishRepertory, PreferenceSource preferenceSource, PayActivity activity) {
        this.parishRepertory = parishRepertory;
        this.preferenceSource = preferenceSource;
        this.activity = activity;
    }

    public String getTime(String time) {
        return DateUtils.friendly_time(DateUtils.stringToDate(time));
    }

    public void getMember(String num) {
        String bill = tableEntity.get().getBillId();
        if (tableList.get()!=null&&!tableList.get().isEmpty()){
           for (TableEntity entity :tableList.get()){
               bill += ","+entity.getBillId();
           }
        }
        parishRepertory.getMember("15",num,bill,preferenceSource.getId())
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        Log.e("vd",stringBaseEntity.toString());
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
                                Log.e("vd",member.get().toString());
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
                                        cardBeanList.add(cardBean);
                                    }
                                }
                                cardList.set(cardBeanList);
                                activity.searchSuccess();
                            }
                        }else {
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

    public Double getPay(){
        return (getShouldPay()-payment.get()) > 0 ? getShouldPay()-payment.get() : 0;
    }
}
