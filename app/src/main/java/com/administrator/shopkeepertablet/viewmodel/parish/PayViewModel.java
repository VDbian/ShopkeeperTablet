package com.administrator.shopkeepertablet.viewmodel.parish;

import android.databinding.ObservableField;
import android.util.Log;

import com.administrator.shopkeepertablet.model.entity.BaseEntity;
import com.administrator.shopkeepertablet.model.entity.TableEntity;
import com.administrator.shopkeepertablet.model.preference.PreferenceSource;
import com.administrator.shopkeepertablet.repository.parish.ParishRepertory;
import com.administrator.shopkeepertablet.utils.DateUtils;
import com.administrator.shopkeepertablet.view.ui.activity.parish.PayActivity;
import com.administrator.shopkeepertablet.viewmodel.BaseViewModel;

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