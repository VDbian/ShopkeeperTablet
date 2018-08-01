package com.administrator.shopkeepertablet.viewmodel;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.text.TextUtils;
import android.util.Log;

import com.administrator.shopkeepertablet.model.entity.BaseEntity;
import com.administrator.shopkeepertablet.model.entity.MemberEntity;
import com.administrator.shopkeepertablet.model.entity.RechargeEntity;
import com.administrator.shopkeepertablet.model.entity.RechargeTypeEntity;
import com.administrator.shopkeepertablet.model.preference.PreferenceSource;
import com.administrator.shopkeepertablet.repository.recharge.RechargeRepository;
import com.administrator.shopkeepertablet.utils.MToast;
import com.administrator.shopkeepertablet.view.ui.fragment.RechargeFragment;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Description:
 * Author chencheng
 * Time 2018/7/25
 */

public class RechargeViewModel extends BaseViewModel {

    private RechargeFragment fragment;
    private RechargeRepository repository;
    private PreferenceSource preferenceSource;
    public ObservableField<String> search = new ObservableField<>("");
    public ObservableField<String> money = new ObservableField<>("");
    public ObservableField<String> proCode = new ObservableField<>("");
    public ObservableField<MemberEntity> member = new ObservableField<>();
    public ObservableField<RechargeEntity> recharge = new ObservableField<>();
    public int index = 1;
    public int size = 10;


    public RechargeViewModel(RechargeFragment fragment, RechargeRepository repository, PreferenceSource preferenceSource) {
        this.fragment = fragment;
        this.repository = repository;
        this.preferenceSource = preferenceSource;
    }

    public void getRecharge(String name, String phone) {
        String pageIndex = "";
        String pageSize = "";
        index = 1;
        if (TextUtils.isEmpty(search.get())) {
            pageIndex = index + "";
            pageSize = size + "";
        }
        repository.getRechargeMember("1", preferenceSource.getId(), pageIndex, pageSize, name, phone)
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        Log.e("api", stringBaseEntity.toString());
                        if (stringBaseEntity.getCode() == 1) {
                            List<RechargeEntity> entities = Arrays.asList(new Gson().fromJson(stringBaseEntity.getResult(), RechargeEntity[].class));
                            fragment.refreshRecharge(entities);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    public void getMoreRecharge(String name, String phone) {
        index += 1;
        repository.getRechargeMember("1", preferenceSource.getId(), index * size + "", size + "", name, phone)
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        Log.e("api", stringBaseEntity.toString());
                        if (stringBaseEntity.getCode() == 1) {
                            List<RechargeEntity> entities = Arrays.asList(new Gson().fromJson(stringBaseEntity.getResult(), RechargeEntity[].class));
                            fragment.loadMoreRecharge(entities);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    public void addRecharge(String name, String phone) {
        repository.addRecharge("8", preferenceSource.getId(), phone, name, preferenceSource.getName(), preferenceSource.getUserId())
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        if (stringBaseEntity.getResult().equals("1")) {
                            getRecharge("", "");
                            MToast.showToast(fragment.getActivity(), "提交成功");
                        } else if (stringBaseEntity.getResult().equals("0")) {
                            MToast.showToast(fragment.getActivity(), "提交失败");
                        } else {
                            MToast.showToast(fragment.getActivity(), "该号码已注册");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        MToast.showToast(fragment.getActivity(), "提交失败");
                    }
                });
    }

    public void searchMember() {
        if (recharge.get()==null){
            MToast.showToast(fragment.getActivity(),"无会员信息");
            return;
        }
        repository.getMember("15", recharge.get().getStaffTel(), "", preferenceSource.getId())
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        if (stringBaseEntity.getCode() == 1) {
                            if (stringBaseEntity.getResult().equals("0")) {
                                MToast.showToast(fragment.getActivity(), "查询失败");
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

//                                //笑笑@满30送5块@5.00@908fe556-e5b7-4446-ac26-0848c991061c@1^
//                                List<CardBean> cardBeanList = new ArrayList<CardBean>();
//                                if (split.length >= 2 && !TextUtils.isEmpty(split[1])) {
//                                    String[] strings = split[1].split("\\^");
//                                    for (String str : strings) {
//                                        String[] cardStr = str.split("@");
//                                        CardBean cardBean = new CardBean();
//                                        cardBean.setId(cardStr[3]);
//                                        cardBean.setMoney(Double.parseDouble(cardStr[2]));
//                                        cardBean.setType(cardStr[4]);
//                                        cardBean.setName(cardStr[1]);
//                                        cardBean.setUsername(cardStr[0]);
//                                        cardBeanList.add(cardBean);
//                                    }
//                                }
                            }
                        }else {
                            MToast.showToast(fragment.getActivity(), "查询失败");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        MToast.showToast(fragment.getActivity(), "查询失败");
                    }
                });

    }

    public void getRechargeType(){
        repository.getRecharge("6",preferenceSource.getId())
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        if (stringBaseEntity.getCode()==1){
                            List<RechargeTypeEntity> typeEntities =Arrays.asList(new Gson().fromJson(stringBaseEntity.getResult(),RechargeTypeEntity[].class));
                            fragment.setSpinnerName(typeEntities);
                        }else {
                            MToast.showToast(fragment.getActivity(), "获取充值方案失败");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    public void checkCode(String typeId,int payType){
        Log.e("vd",proCode.get());
        repository.checkCode("10",preferenceSource.getId(),proCode.get())
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        Log.e("vd",stringBaseEntity.toString());
                        if (stringBaseEntity.getResult().equals("1")){
                            if (TextUtils.isEmpty(typeId)){
                                moneyCharge(payType);
                            }else {
                                proCharge(payType,typeId);
                            }
                        }else {
                            MToast.showToast(fragment.getActivity(),"校验码错误");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        MToast.showToast(fragment.getActivity(),"校验失败");
                    }
                });
    }

    public void moneyCharge(int payType){
        repository.moneyCharge("9",member.get().getId(),preferenceSource.getId(),money.get(),payType,preferenceSource.getName(),preferenceSource.getUserId())
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        Log.e("vd",stringBaseEntity.toString());
                        if (stringBaseEntity.getCode() ==1){
                          fragment.success();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });

    }

    public void proCharge(int payType,String typeId){
        repository.productCharge("7",member.get().getId(),preferenceSource.getId(),typeId,payType,preferenceSource.getName(),preferenceSource.getUserId())
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        Log.e("vd",stringBaseEntity.toString());
                        if (stringBaseEntity.getCode() ==1){
                            fragment.success();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }



}
