package com.administrator.shopkeepertablet.viewmodel;

import android.databinding.ObservableField;
import android.util.Log;

import com.administrator.shopkeepertablet.model.entity.BaseEntity;
import com.administrator.shopkeepertablet.model.entity.OrderEntity;
import com.administrator.shopkeepertablet.model.entity.OrderFoodEntity;
import com.administrator.shopkeepertablet.model.entity.PayTypeEntity;
import com.administrator.shopkeepertablet.model.entity.RoomEntity;
import com.administrator.shopkeepertablet.model.entity.TableEntity;
import com.administrator.shopkeepertablet.model.preference.PreferenceSource;
import com.administrator.shopkeepertablet.repository.message.MessageRepository;
import com.administrator.shopkeepertablet.utils.MLog;
import com.administrator.shopkeepertablet.utils.MToast;
import com.administrator.shopkeepertablet.view.ui.fragment.MessageFragment;
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

public class MessageViewModel extends BaseViewModel {

    private MessageFragment fragment;
    private MessageRepository repository;
    private PreferenceSource preferenceSource;
    public int page = 1;
    public int size = 10;


    public ObservableField<List<OrderFoodEntity>> detailFoods = new ObservableField<>();
    public ObservableField<OrderEntity> orderEntity = new ObservableField<>();
    public ObservableField<String> payInfo = new ObservableField<>("");
    public List<RoomEntity> roomEntityList = new ArrayList<>();
    public List<TableEntity> tableEntityList = new ArrayList<>();


    public MessageViewModel(MessageFragment fragment, MessageRepository repository, PreferenceSource preferenceSource) {
        this.fragment = fragment;
        this.repository = repository;
        this.preferenceSource = preferenceSource;
    }

    public void getMessage(String state) {
        page = 1;
        repository.getMessage("8", preferenceSource.getId(), state, "all", "all", page, size).subscribe(
                new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        Log.e("VD", stringBaseEntity.toString());
                        if (stringBaseEntity.getCode() == 1) {
                            List<OrderEntity> orderEntities = Arrays.asList(new Gson().fromJson(stringBaseEntity.getResult(), OrderEntity[].class));
                            fragment.refresh(orderEntities);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                }
        );
    }

    public void getMoreMessage(String state) {
        page += 1;
        repository.getMessage("8", preferenceSource.getId(), state, "all", "all", page, size).subscribe(
                new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        List<OrderEntity> orderEntities = Arrays.asList(new Gson().fromJson(stringBaseEntity.getResult(), OrderEntity[].class));
                        fragment.loadMore(orderEntities);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                }
        );
    }

    public void getOrderDetail(String billId) {
        repository.getOrderDetail(preferenceSource.getId(), "9", billId)
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        Log.e("vd", stringBaseEntity.getResult());
                        if (stringBaseEntity.getCode() == 1) {
                            String[] result = stringBaseEntity.getResult().split("\\^");
                            detailFoods.set(Arrays.asList(new Gson().fromJson(result[0], OrderFoodEntity[].class)));
                            if (result.length > 1) {
                                List<PayTypeEntity> tPayTypes = Arrays.asList(new Gson().fromJson(result[1], PayTypeEntity[].class));
                                initPayInfo(tPayTypes);
                                fragment.notifyFood(detailFoods.get());
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("vd", throwable.getMessage());
                    }
                });
    }

    private void initPayInfo(List<PayTypeEntity> tPayTypes) {
        StringBuilder builder = new StringBuilder();
        if (tPayTypes != null && tPayTypes.size() > 0) {
            for (int i = 0; i < tPayTypes.size(); i++) {
                switch (tPayTypes.get(i).getPayType()) {
                    case "1":
                        builder = builder.append("现金：" + tPayTypes.get(i).getPice() + " |");
                        break;
                    case "2":
                        builder = builder.append("银行卡：" + tPayTypes.get(i).getPice() + " |");
                        break;
                    case "3":
                        builder = builder.append("主扫微信：" + tPayTypes.get(i).getPice() + " |");
                        break;
                    case "4":
                        builder = builder.append("挂账：" + tPayTypes.get(i).getPice() + " |");
                        break;
                    case "5":
                        builder = builder.append("会员卡：" + tPayTypes.get(i).getPice() + " |");
                        break;
                    case "6":
                        builder = builder.append("被扫支付宝：" + tPayTypes.get(i).getPice() + " |");
                        break;
                    case "7":
                        builder = builder.append("被扫微信：" + tPayTypes.get(i).getPice() + " |");
                        break;
                    case "8":
                        builder = builder.append("美团券：" + tPayTypes.get(i).getPice() + " |");
                        break;
                    case "9":
                        builder = builder.append("大众点评券：" + tPayTypes.get(i).getPice() + " |");
                        break;
                    default:
                        builder = builder.append("主扫支付宝：" + tPayTypes.get(i).getPice() + " |");
                        break;
                }
            }
            builder.deleteCharAt(builder.length() - 1);
        }
        payInfo.set(builder.toString());
    }

    public void confirm(){
        repository.confirm("1",preferenceSource.getId(),orderEntity.get().getId(),orderEntity.get().getBillId(),orderEntity.get().getType()+"")
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        Log.e("vd",stringBaseEntity.toString());
                        if (stringBaseEntity.getCode() == 1) {
                            MToast.showToast(fragment.getActivity(), "已确认");
                            fragment.success();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    public void bind(TableEntity table){
        repository.bindTable("4",orderEntity.get().getId(),table.getRoomTableId(),preferenceSource.getId(),"1",
                preferenceSource.getUserId(),table.getTableName() )
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        Log.e("api", stringBaseEntity.toString());
                        if (stringBaseEntity.getCode() == 1) {
                            MToast.showToast(fragment.getActivity(), "绑定成功");
                            fragment.success();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("api", throwable.getMessage());
                    }
                });
    }

    public void cancel(){
        repository.cancel("2",preferenceSource.getId(),orderEntity.get().getId())
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        Log.e("vd",stringBaseEntity.toString());
                        if (stringBaseEntity.getCode() == 1) {
                            MToast.showToast(fragment.getActivity(), "已取消");
                            fragment.success();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    public void getRooms() {
        repository.getRooms("1", preferenceSource.getId(), 1, 100)
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        MLog.e("api_room", stringBaseEntity.getResult());
                        if (stringBaseEntity.getCode() == 1) {
                            List<RoomEntity> rooms = Arrays.asList(new Gson().fromJson(stringBaseEntity.getResult(), RoomEntity[].class));
                            roomEntityList = rooms;
                            if (!rooms.isEmpty()) {
                                getTables(rooms.get(0));
                            }
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    public void getTables(RoomEntity roomEntity) {
        repository.getTables("0", roomEntity.getId(), preferenceSource.getId(), 1, 100)
                .subscribe(new Consumer<BaseEntity<String>>() {
                    @Override
                    public void accept(BaseEntity<String> stringBaseEntity) throws Exception {
                        MLog.e("api_table", stringBaseEntity.getResult());
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
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

}
