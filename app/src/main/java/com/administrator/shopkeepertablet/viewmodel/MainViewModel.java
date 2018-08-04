package com.administrator.shopkeepertablet.viewmodel;

import com.administrator.shopkeepertablet.model.entity.BaseEntity;
import com.administrator.shopkeepertablet.model.preference.PreferenceSource;
import com.administrator.shopkeepertablet.repository.main.MainRepertory;
import com.administrator.shopkeepertablet.utils.MToast;
import com.administrator.shopkeepertablet.utils.Print;
import com.administrator.shopkeepertablet.view.ui.activity.MainActivity;

import io.reactivex.functions.Consumer;

/**
 * Description:
 * Author CC
 * Time 2018/6/23
 */


public class MainViewModel extends BaseViewModel {

    private MainActivity mainActivity;
    private MainRepertory mainRepertory;
    private PreferenceSource preferenceSource;
    private Print print;

    public MainViewModel(MainActivity mainActivity, MainRepertory mainRepertory, PreferenceSource preferenceSource) {
        this.mainActivity = mainActivity;
        this.mainRepertory = mainRepertory;
        this.preferenceSource = preferenceSource;
        this.print = new Print(mainRepertory);
    }

    public String getUserName() {
        return preferenceSource.getName();
    }

    public void jiaoBan() {
        mainRepertory.jiaoBanPrint("6", "1", preferenceSource.getId(), preferenceSource.getName(), "", preferenceSource.getUserId())
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        if (stringBaseEntity.getResult().equals("1")) {
                            print(stringBaseEntity.getResult());
                            mainActivity.intentToLogin();
                        } else {
                            MToast.showToast(mainActivity, "获取交班打印数据失败");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    private void print(String result) {
//        new Thread(() -> Print.socketDataArrivalHandler(result)).start();
        print.rxPrint(result);
    }
}
