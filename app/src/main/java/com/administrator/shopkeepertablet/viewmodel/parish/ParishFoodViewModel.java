package com.administrator.shopkeepertablet.viewmodel.parish;

import com.administrator.shopkeepertablet.di.app.AppComponent;
import com.administrator.shopkeepertablet.model.entity.BaseEntity;
import com.administrator.shopkeepertablet.model.preference.PreferenceSource;
import com.administrator.shopkeepertablet.repository.parish.ParishRepertory;
import com.administrator.shopkeepertablet.utils.MLog;
import com.administrator.shopkeepertablet.view.ui.BaseFragment;
import com.administrator.shopkeepertablet.view.ui.fragment.ParishFoodFragment;
import com.administrator.shopkeepertablet.viewmodel.BaseViewModel;

import io.reactivex.functions.Consumer;

/**
 * Description:
 * Author chencheng
 * Time 2018/7/3
 */

public class ParishFoodViewModel extends BaseViewModel {
    private ParishFoodFragment fragment;
    private ParishRepertory parishRepertory;
    private PreferenceSource preferenceSource;

    public ParishFoodViewModel(ParishFoodFragment fragment, ParishRepertory parishRepertory, PreferenceSource preferenceSource) {
        this.fragment = fragment;
        this.parishRepertory = parishRepertory;
        this.preferenceSource = preferenceSource;
    }

    public void getRooms() {
        parishRepertory.getRooms("1", preferenceSource.getId())
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        MLog.e("api_room", stringBaseEntity.getResult());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    public void getTables() {
        parishRepertory.getTables("0", "param1", preferenceSource.getId(), 1, 1000)
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        MLog.e("api_table", stringBaseEntity.getResult());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

}
