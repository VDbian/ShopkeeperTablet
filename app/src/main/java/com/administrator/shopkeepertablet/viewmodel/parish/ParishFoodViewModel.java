package com.administrator.shopkeepertablet.viewmodel.parish;

import android.content.Intent;
import android.databinding.ObservableField;

import com.administrator.shopkeepertablet.AppConstant;
import com.administrator.shopkeepertablet.di.app.AppComponent;
import com.administrator.shopkeepertablet.model.entity.BaseEntity;
import com.administrator.shopkeepertablet.model.entity.RoomEntity;
import com.administrator.shopkeepertablet.model.entity.TableEntity;
import com.administrator.shopkeepertablet.model.preference.PreferenceSource;
import com.administrator.shopkeepertablet.repository.parish.ParishRepertory;
import com.administrator.shopkeepertablet.utils.MLog;
import com.administrator.shopkeepertablet.utils.MToast;
import com.administrator.shopkeepertablet.view.ui.BaseFragment;
import com.administrator.shopkeepertablet.view.ui.fragment.ParishFoodFragment;
import com.administrator.shopkeepertablet.viewmodel.BaseViewModel;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Description:
 * Author CC
 * Time 2018/7/3
 */

public class ParishFoodViewModel extends BaseViewModel {
    private ParishFoodFragment fragment;
    private ParishRepertory parishRepertory;
    private PreferenceSource preferenceSource;
    public ObservableField<String> room = new ObservableField<>("");
    public ObservableField<String> table = new ObservableField<>("");
    public ObservableField<String> tableId = new ObservableField<>("");
    public ObservableField<String> people = new ObservableField<>("1");
    public ObservableField<String> tableware = new ObservableField<>("1");
    public ObservableField<String> time = new ObservableField<>("");


    public ParishFoodViewModel(ParishFoodFragment fragment, ParishRepertory parishRepertory, PreferenceSource preferenceSource) {
        this.fragment = fragment;
        this.parishRepertory = parishRepertory;
        this.preferenceSource = preferenceSource;
    }

    public void getRooms() {
        parishRepertory.getRooms("1", preferenceSource.getId(), 1, 100)
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        MLog.e("api_room", stringBaseEntity.getResult());
                        List<RoomEntity> rooms = Arrays.asList(new Gson().fromJson(stringBaseEntity.getResult(), RoomEntity[].class));
                        fragment.initTabView(rooms);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    public void getTables(RoomEntity roomEntity) {
        parishRepertory.getTables("0", roomEntity.getId(), preferenceSource.getId(), 1, 100)
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        MLog.e("api_table", stringBaseEntity.getResult());
                        TableEntity[] tableEntities = new Gson().fromJson(stringBaseEntity.getResult(), TableEntity[].class);
                        List<TableEntity> mList = Arrays.asList(tableEntities);
                        fragment.refreshVariety(mList);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    public void openTable(final boolean flag) {
        parishRepertory.openTable("1", tableId.get(), table.get(), preferenceSource.getId(),
                people.get(), tableware.get(), preferenceSource.getUserId(), preferenceSource.getName())
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
//                        MLog.e("api",stringBaseEntity.getResult());
                        if (stringBaseEntity.getCode() == 1) {
                            MToast.showToast(fragment.getActivity(), "开台成功");
                            fragment.openSuccess(flag);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    public void clearTable(String billId) {
        parishRepertory.clearTable("3", tableId.get(), billId, preferenceSource.getId())
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        MLog.e("api", stringBaseEntity.toString());
                        MToast.showToast(fragment.getActivity(), stringBaseEntity.getMessage());
                        if (stringBaseEntity.getCode() == AppConstant.REQUEST_SUCCESS) {
                            fragment.clearSuccess();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });

    }
}
