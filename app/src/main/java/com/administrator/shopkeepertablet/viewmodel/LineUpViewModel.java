package com.administrator.shopkeepertablet.viewmodel;

import android.util.Log;

import com.administrator.shopkeepertablet.model.entity.BaseEntity;
import com.administrator.shopkeepertablet.model.entity.LineUpEntity;
import com.administrator.shopkeepertablet.model.entity.RoomEntity;
import com.administrator.shopkeepertablet.model.entity.TableEntity;
import com.administrator.shopkeepertablet.model.entity.TableType;
import com.administrator.shopkeepertablet.model.preference.PreferenceSource;
import com.administrator.shopkeepertablet.repository.line.LineUpRepository;
import com.administrator.shopkeepertablet.utils.DialogUtils;
import com.administrator.shopkeepertablet.utils.MLog;
import com.administrator.shopkeepertablet.utils.MToast;
import com.administrator.shopkeepertablet.utils.Print;
import com.administrator.shopkeepertablet.view.ui.fragment.LineUpFragment;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Description:
 * Author chencheng
 * Time 2018/7/25
 */

public class LineUpViewModel extends BaseViewModel {

    private LineUpFragment fragment;
    private LineUpRepository repository;
    private PreferenceSource preferenceSource;
    private Print print;

    public List<LineUpEntity> entityList = new ArrayList<>();
    public List<TableType> tableTypeList = new ArrayList<>();
    public List<RoomEntity> roomEntityList = new ArrayList<>();
    public List<TableEntity> tableEntityList = new ArrayList<>();

    public LineUpViewModel(LineUpFragment fragment, LineUpRepository repository, PreferenceSource preferenceSource) {
        this.fragment = fragment;
        this.repository = repository;
        this.preferenceSource = preferenceSource;
        this.print = new Print(repository);
    }

    private void printResult(final String result) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                print.socketDataArrivalHandler(result);
            }
        }).start();
    }

    public void getTableType() {
        DialogUtils.showDialog(fragment.getActivity(), "获取数据中");
        repository.getTableType("6", preferenceSource.getId())
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        MLog.e("api", stringBaseEntity.toString());
                        DialogUtils.hintDialog();
                        if (stringBaseEntity.getCode() == 1) {
                            List<TableType> tableTypes = Arrays.asList(new Gson().fromJson(stringBaseEntity.getResult(), TableType[].class));
                            tableTypeList = tableTypes;
                            fragment.refreshType(tableTypes);
                        } else {
                            MToast.showToast(fragment.getActivity(), "获取桌位类别失败");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        DialogUtils.hintDialog();
                        MToast.showToast(fragment.getActivity(),"获取桌位类别失败");
                    }
                });
    }

    public void getLineUp() {
        DialogUtils.showDialog(fragment.getActivity(), "获取数据中");
        repository.getQueue("1", "all", "", preferenceSource.getId())
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        MLog.e("api", stringBaseEntity.toString());
                        DialogUtils.hintDialog();
                        if (stringBaseEntity.getCode() == 1) {
                            List<LineUpEntity> lineUpEntities = Arrays.asList(new Gson().fromJson(stringBaseEntity.getResult(), LineUpEntity[].class));
                            entityList = lineUpEntities;
                            fragment.refreshLineUp(lineUpEntities);
                        }else {
                            MToast.showToast(fragment.getActivity(),"排队信息获取失败");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                       DialogUtils.hintDialog();
                        MToast.showToast(fragment.getActivity(),"排队信息获取失败");
                    }
                });
    }

    public void addLineUp(String id) {
        DialogUtils.showDialog(fragment.getActivity(), "数据提交中");
        repository.addQueue("2", id, preferenceSource.getId())
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        MLog.e("api", stringBaseEntity.toString());
                        DialogUtils.hintDialog();
                        if (stringBaseEntity.getCode() == 1) {
                            printResult(stringBaseEntity.getResult());
                            getLineUp();
                        } else {
                            MToast.showToast(fragment.getActivity(), "新增排号失败");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        DialogUtils.hintDialog();
                        MToast.showToast(fragment.getActivity(), "新增排号失败");
                    }
                });
    }

    public void deleteLineUp(String id) {
        DialogUtils.showDialog(fragment.getActivity(), "数据提交中");
        repository.deleteQueue("4", id)
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        MLog.e("api", stringBaseEntity.toString());
                        DialogUtils.hintDialog();
                        if (stringBaseEntity.getCode() == 1) {
                            MToast.showToast(fragment.getActivity(), "删除排号成功");
                            getLineUp();
                        }else {
                            MToast.showToast(fragment.getActivity(),"删除排号失败");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        MLog.e("api", throwable.getMessage());
                        DialogUtils.hintDialog();
                        MToast.showToast(fragment.getActivity(), "删除排号失败");
                    }
                });
    }

    public void bindLineUp(String typeId, String tableId, String tableName, String lineId) {
        DialogUtils.showDialog(fragment.getActivity(), "数据提交中");
        repository.bindQueue("3", typeId, preferenceSource.getName(),
                preferenceSource.getUserId(), tableId, tableName,
                "1", lineId, preferenceSource.getId())
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        MLog.e("api", stringBaseEntity.toString());
                        DialogUtils.hintDialog();
                        if (stringBaseEntity.getCode() == 1) {
                            MToast.showToast(fragment.getActivity(), "绑定成功");
                            getLineUp();
                        }else {
                            MToast.showToast(fragment.getActivity(),"绑定桌位失败");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        MLog.e("api", throwable.getMessage());
                        DialogUtils.hintDialog();
                        MToast.showToast(fragment.getActivity(),"绑定桌位失败");
                    }
                });
    }

    public void getRooms() {
        DialogUtils.showDialog(fragment.getActivity(), "获取数据中");
        repository.getRooms("1", preferenceSource.getId(), 1, 100)
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
        repository.getTables("0", roomEntity.getId(), preferenceSource.getId(), 1, 100)
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

}
