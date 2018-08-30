package com.administrator.shopkeepertablet.view.ui.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.administrator.shopkeepertablet.AppConstant;
import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.databinding.FragmentFastFoodBinding;
import com.administrator.shopkeepertablet.di.app.AppComponent;
import com.administrator.shopkeepertablet.di.fast.DaggerFastComponent;
import com.administrator.shopkeepertablet.di.fast.FastModule;
import com.administrator.shopkeepertablet.model.entity.FoodEntity;
import com.administrator.shopkeepertablet.model.entity.FoodTypeEntity;
import com.administrator.shopkeepertablet.model.entity.FoodTypeSelectEntity;
import com.administrator.shopkeepertablet.model.entity.KouWeiEntity;
import com.administrator.shopkeepertablet.model.entity.OrderEntity;
import com.administrator.shopkeepertablet.model.entity.OrderFoodEntity;
import com.administrator.shopkeepertablet.model.entity.RoomEntity;
import com.administrator.shopkeepertablet.model.entity.TableEntity;
import com.administrator.shopkeepertablet.model.entity.bean.BillJson;
import com.administrator.shopkeepertablet.model.entity.bean.CartBean;
import com.administrator.shopkeepertablet.model.entity.bean.EventFastPayBean;
import com.administrator.shopkeepertablet.model.entity.bean.EventPayBean;
import com.administrator.shopkeepertablet.model.entity.bean.FoodAddBean;
import com.administrator.shopkeepertablet.model.preference.PreferenceSource;
import com.administrator.shopkeepertablet.utils.DataEvent;
import com.administrator.shopkeepertablet.utils.MToast;
import com.administrator.shopkeepertablet.view.ui.BaseFragment;
import com.administrator.shopkeepertablet.view.ui.activity.parish.PayActivity;
import com.administrator.shopkeepertablet.view.ui.adapter.FoodTypeAdapter;
import com.administrator.shopkeepertablet.view.ui.adapter.OrderDishesCartAdapter;
import com.administrator.shopkeepertablet.view.ui.adapter.OrderDishesVarietyAdapter;
import com.administrator.shopkeepertablet.view.ui.adapter.base.AdapterOnItemClick;
import com.administrator.shopkeepertablet.view.widget.BindLineUpDialog;
import com.administrator.shopkeepertablet.view.widget.ConfirmDialog;
import com.administrator.shopkeepertablet.view.widget.PopupWindowAllKouwei;
import com.administrator.shopkeepertablet.view.widget.PopupWindowOrderDishesChange;
import com.administrator.shopkeepertablet.view.widget.PopupWindowOrderDishesChoose;
import com.administrator.shopkeepertablet.view.widget.RecyclerViewItemDecoration;
import com.administrator.shopkeepertablet.view.widget.ReserveDialog;
import com.administrator.shopkeepertablet.viewmodel.FastViewModel;
import com.google.gson.Gson;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Description:
 * Author CC
 * Time 2018/7/23
 */


public class FastFoodFragment extends BaseFragment implements View.OnClickListener {

    private FragmentFastFoodBinding binding;
    @Inject
    FastViewModel viewModel;

    private OrderDishesVarietyAdapter adapter;
    private FoodTypeAdapter foodTypeAdapter;
    private List<FoodEntity> mList = new ArrayList<>();
    private List<FoodTypeSelectEntity> foodTypeEntityList = new ArrayList<>();
    private OrderDishesCartAdapter cartAdapter;
    private List<CartBean> cartBeanList = new ArrayList<>();
    private KouWeiEntity kouWeiEntity;
    private String remark;
    private String name;
    private BindLineUpDialog bindLineUpDialog;
    private String billId;
    private TableEntity table;

    @Override
    protected void setupFragmentComponent(AppComponent appComponent) {
        DaggerFastComponent.builder()
                .appComponent(appComponent)
                .fastModule(new FastModule(this))
                .build()
                .inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_fast_food, container, false);
        binding.setViewModel(viewModel);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        EventBus.getDefault().register(this);
        initView();
        viewModel.getFoodList();
        viewModel.getFoodType();
    }

    private void initView() {
//        Log.e("vd",name);
        if (!TextUtils.isEmpty(name)) {
            switch (name) {
                case "fast":
                    binding.llFast.setVisibility(View.VISIBLE);
                    binding.tvReserve.setVisibility(View.GONE);
                    binding.tvTitle.setText("快餐");
                    break;
                case "reserve":
                    binding.llFast.setVisibility(View.GONE);
                    binding.tvReserve.setVisibility(View.VISIBLE);
                    binding.tvTitle.setText("预定");
                    break;
            }
        } else {
            binding.llFast.setVisibility(View.VISIBLE);
            binding.tvReserve.setVisibility(View.GONE);
            binding.tvTitle.setText("快餐");
        }
        if (AppConstant.getUser() != null) {
            if (AppConstant.getUser().getOperaType().contains("2")) {
                binding.tvTable.setText("排号");
            } else if (AppConstant.getUser().getOperaType().contains("3")) {
                binding.tvTable.setText("桌位");
            }
            switch (AppConstant.getUser().getPayType()) {
                case "1":
                    binding.tvMoneyPay.setText("现金");
                    break;
                case "2":
                    binding.tvMoneyPay.setText("银行卡");
                    break;
                case "3":
                    binding.tvMoneyPay.setText("微信");
                    break;
                case "5":
                    binding.tvMoneyPay.setText("会员卡");
                    break;
                case "6":
                    binding.tvMoneyPay.setText("线下支付宝");
                    break;
                case "7":
                    binding.tvMoneyPay.setText("线下微信");
                    break;
                default:
                    binding.tvMoneyPay.setText("快速结账");
                    break;
            }
        }
        adapter = new OrderDishesVarietyAdapter(getActivity(), mList);
        binding.rlvVariety.setAdapter(adapter);
        binding.rlvVariety.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        binding.rlvVariety.addItemDecoration(new RecyclerViewItemDecoration(5));
        adapter.setOnItemClick(new AdapterOnItemClick<FoodEntity>() {
            @Override
            public void onItemClick(FoodEntity foodEntity, int position) {
//                if (foodEntity.getSpecEntityList() != null && !foodEntity.getSpecEntityList().isEmpty() &&
//                        foodEntity.getProductKouWeiEntityList() != null && !foodEntity.getProductKouWeiEntityList().isEmpty() &&
//                        foodEntity.getSeasonEntityList() != null && !foodEntity.getSeasonEntityList().isEmpty()) {
                    final PopupWindowOrderDishesChoose OrderDishesChoose = new PopupWindowOrderDishesChoose(getActivity(), foodEntity);
                    OrderDishesChoose.showPopupWindowUp(binding.llOrder);
                    OrderDishesChoose.setOnCallBackListener(new PopupWindowOrderDishesChoose.OnCallBackListener() {
                        @Override
                        public void confirm(CartBean cartBean) {
                            OrderDishesChoose.dismiss();
                            cartBeanList.add(cartBean);
                            cartAdapter.notifyDataSetChanged();
                            sumPrice();
                        }
                    });
//                } else {
//                    CartBean cartBean = new CartBean();
//                    cartBean.setFoodEntity(foodEntity);
//                    if (foodEntity.getProductProperty().equals("1")) {
//                        cartBean.setNum("1");
//                        cartBean.setUnit(foodEntity.getUnit());
//                    } else {
//                        cartBean.setNum("1");
//                    }
//                    cartBean.setFoodAddBeanList(new ArrayList<>());
//                    cartBean.setPrice(foodEntity.getPrice());
//                    cartBeanList.add(cartBean);
//                    cartAdapter.notifyDataSetChanged();
//                    sumPrice();
//                }
            }
        });

        Drawable drawable1 = getResources().getDrawable(R.mipmap.search);
        drawable1.setBounds(25, 0, 45, 20);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
        binding.etSearch.setCompoundDrawables(drawable1, null, null, null);//只放左边
        binding.etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                viewModel.search(viewModel.search.get());
                return false;
            }
        });

        foodTypeAdapter = new FoodTypeAdapter(getActivity(), foodTypeEntityList);
        binding.rlvFoodType.setAdapter(foodTypeAdapter);
        binding.rlvFoodType.setLayoutManager(new LinearLayoutManager(getActivity()));
        foodTypeAdapter.setOnItemClick(new FoodTypeAdapter.OnItemClick() {
            @Override
            public void onItemClick(FoodTypeEntity entity, int position) {
                mList.clear();
                mList.addAll(entity.getFoodEntityList());
                adapter.notifyDataSetChanged();
            }
        });

        cartAdapter = new OrderDishesCartAdapter(getActivity(), cartBeanList);
        binding.rlvOrder.setAdapter(cartAdapter);
        binding.rlvOrder.setLayoutManager(new LinearLayoutManager(getActivity()));
        cartAdapter.setOnItemClick(new OrderDishesCartAdapter.OnItemClick() {
            @Override
            public void onItemClick(final CartBean entity, final int position) {
                final PopupWindowOrderDishesChange orderDishesChange = new PopupWindowOrderDishesChange(getActivity(), entity);
                orderDishesChange.showPopupWindowUp(binding.llOrder);
                orderDishesChange.setOnCallBackListener(new PopupWindowOrderDishesChange.OnCallBackListener() {
                    @Override
                    public void save(CartBean cartBean) {
                        cartBeanList.set(position, cartBean);
                        cartAdapter.notifyDataSetChanged();
                        sumPrice();
                    }

                    @Override
                    public void delete() {
                        cartBeanList.remove(position);
                        cartAdapter.notifyDataSetChanged();
                        sumPrice();
                        if (cartBeanList.size() == 0) {
                            kouWeiEntity = null;
                            remark = "";
                        }
                    }
                });
            }
        });

        binding.ivMore.setOnClickListener(this);
        binding.tvScanPay.setOnClickListener(this);
        binding.tvMoneyPay.setOnClickListener(this);
        binding.tvOtherPay.setOnClickListener(this);
        binding.tvReserve.setOnClickListener(this);
    }

    public void showPopAllKouwei(List<KouWeiEntity> list) {
        PopupWindowAllKouwei popupWindowAllKouwei = new PopupWindowAllKouwei(getActivity(), list, kouWeiEntity, remark);
        popupWindowAllKouwei.showPopupWindowUp();
        popupWindowAllKouwei.setOnCallBackListener(new PopupWindowAllKouwei.OnCallBackListener() {
            @Override
            public void confirm(KouWeiEntity entity, String re) {
                kouWeiEntity = entity;
                remark = re;
            }
        });
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private void sumPrice() {
        double sum = 0;
        if (cartBeanList.size() > 0) {
            for (CartBean cartBean : cartBeanList) {
                sum = sum + Double.valueOf(cartBean.getNum()) * cartBean.getPrice();
                for (FoodAddBean foodAddBean : cartBean.getFoodAddBeanList()) {
                    sum = sum + foodAddBean.getNum() * foodAddBean.getPrice();
                }
            }
        }
        viewModel.price.set(sum);
    }

    private String getInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        if (cartBeanList.size() > 0) {
            for (int i = 0; i < cartBeanList.size(); i++) {
                CartBean bean = cartBeanList.get(i);
                double total = (Double.valueOf(bean.getNum()) - Double.valueOf(bean.getGiveNum())) * bean.getPrice();
                String kouwei = "";
                String kouweiId = "";
                if (bean.getProductKouWeiEntity() != null) {
                    kouwei = bean.getProductKouWeiEntity().getName() + "*";
                    kouweiId = bean.getProductKouWeiEntity().getuId() + "*";
                }
                if (!TextUtils.isEmpty(bean.getKouwei())) {
                    kouwei = kouwei + bean.getKouwei() + "*";
                    kouweiId = kouweiId + "" + "*";
                }
                if (kouWeiEntity != null) {
                    kouwei = kouwei + kouWeiEntity.getName() + "*";
                    kouweiId = kouweiId + kouWeiEntity.getGuId() + "*";
                }
                if (!TextUtils.isEmpty(remark)) {
                    kouwei = kouwei + bean.getKouwei() + "*";
                    kouweiId = kouweiId + "" + "*";
                }
                if (!TextUtils.isEmpty(kouwei)) {
                    kouwei = kouwei.substring(0, kouwei.length() - 1);
                }
                if (!TextUtils.isEmpty(kouweiId)) {
                    kouweiId = kouweiId.substring(0, kouweiId.length() - 1);
                }
                String addId = "";
                String addName = "";
                Double addPrice = 0.0;
                String addNum = "";
                String specName = "";
                String specId = "";
                String specPrice = "";
                for (int j = 0; j < bean.getFoodAddBeanList().size(); j++) {
                    if (j == bean.getFoodAddBeanList().size() - 1) {
                        addId = addId + bean.getFoodAddBeanList().get(j).getId();
                        addName = addName + bean.getFoodAddBeanList().get(j).getName() + "(" + (bean.getFoodAddBeanList().get(j).getNum() + "份）");
                        addPrice = addPrice + bean.getFoodAddBeanList().get(j).getPrice() * bean.getFoodAddBeanList().get(j).getNum();
                        addNum = addNum + bean.getFoodAddBeanList().get(j).getNum();
                    } else {
                        addId = addId + bean.getFoodAddBeanList().get(j).getId() + "*";
                        addName = addName + bean.getFoodAddBeanList().get(j).getName() + "(" + (bean.getFoodAddBeanList().get(j).getNum() + "份）") + "*";
                        addPrice = addPrice + bean.getFoodAddBeanList().get(j).getPrice() * bean.getFoodAddBeanList().get(j).getNum();
                        addNum = addNum + bean.getFoodAddBeanList().get(j).getNum() + "*";
                    }
                }
                if (bean.getSpec() != null) {
                    specName = bean.getSpec().getName();
                    specId = bean.getSpec().getUId();
                    specPrice = bean.getSpec().getPrice() + "";
                }
                if (bean.getFoodEntity().getType()) {
                    stringBuilder.append(bean.getFoodEntity().getPackageName());
                    stringBuilder.append("@");
                    stringBuilder.append(bean.getFoodEntity().getMemberPice());
                    stringBuilder.append("@");
                    stringBuilder.append("-1");
                    stringBuilder.append("@");
                    stringBuilder.append(bean.getFoodEntity().getPrice());
                    stringBuilder.append("@");
                    stringBuilder.append(bean.getUnit());
                    stringBuilder.append("$");
                    stringBuilder.append(bean.getFoodEntity().getProductId());
                    stringBuilder.append("$");
                    stringBuilder.append(0.0);
                    stringBuilder.append("$");
                    stringBuilder.append(bean.getNum());
                    stringBuilder.append("$");
                    stringBuilder.append(kouwei);
                    stringBuilder.append("$");
                    stringBuilder.append(kouweiId);
                    stringBuilder.append("$");
                    stringBuilder.append("1");
                    stringBuilder.append("$");
                    stringBuilder.append(bean.getPrice());
                    stringBuilder.append("$");
                    stringBuilder.append(bean.getFoodEntity().getProductName().replace(",", "^"));
                    stringBuilder.append("$");
                    stringBuilder.append(bean.getFoodEntity().getCounts().replace(",", "^"));
                    stringBuilder.append("$");
                    stringBuilder.append(bean.getGiveNum());
                    stringBuilder.append("$");
                    stringBuilder.append(addId);
                    stringBuilder.append("$");
                    stringBuilder.append(addName);
                    stringBuilder.append("$");
                    stringBuilder.append(addPrice);
                    stringBuilder.append("$");
                    stringBuilder.append(addNum);
                    stringBuilder.append(",");
                } else {
                    //        菜品名称@ 会员价格@  状态@ 单价$  菜品ID$ 总价$ 份数$  口味$  口味ID$  份数$ 总价$ 规格$ 规格ID
                    stringBuilder.append(bean.getFoodEntity().getProductName());
                    stringBuilder.append("@");
                    stringBuilder.append(bean.getFoodEntity().getMemberPice());
                    stringBuilder.append("@");
                    stringBuilder.append("0");
                    stringBuilder.append("@");
                    stringBuilder.append(bean.getFoodEntity().getPrice());
                    stringBuilder.append("@");
                    stringBuilder.append(bean.getUnit());
                    stringBuilder.append("$");
                    stringBuilder.append(bean.getFoodEntity().getProductId());
                    stringBuilder.append("$");
                    stringBuilder.append(total);
                    stringBuilder.append("$");
                    stringBuilder.append(bean.getNum());
                    stringBuilder.append("$");
                    stringBuilder.append(kouwei);
                    stringBuilder.append("$");
                    stringBuilder.append(kouweiId);
                    stringBuilder.append("$");
                    stringBuilder.append("1");
                    stringBuilder.append("$");
                    stringBuilder.append(bean.getPrice());
                    stringBuilder.append("$");
                    stringBuilder.append(specName);
                    stringBuilder.append("$");
                    stringBuilder.append(specId);
                    stringBuilder.append("$");
                    stringBuilder.append(bean.getGiveNum());
                    stringBuilder.append("$");
                    stringBuilder.append(addId);
                    stringBuilder.append("$");
                    stringBuilder.append(addName);
                    stringBuilder.append("$");
                    stringBuilder.append(addPrice);
                    stringBuilder.append("$");
                    stringBuilder.append(addNum);
                    stringBuilder.append(",");
                }
            }

        }
        String info = stringBuilder.toString();
        if (info.endsWith(",")) {
            info = info.substring(0, info.length() - 1);
        }
        return info;
    }

    public void refreshVariety(List<FoodEntity> foodEntities) {
        mList.clear();
        mList.addAll(foodEntities);
        adapter.notifyDataSetChanged();
    }

    public void refreshFoodType(List<FoodTypeSelectEntity> foodTypeEntities) {
        foodTypeEntityList.clear();
        foodTypeEntityList.addAll(foodTypeEntities);
        foodTypeAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_more:
                if (cartBeanList.size() == 0) {
                    MToast.showToast(getActivity(), "购物车是空的");
                } else {
                    viewModel.getAllKouwei();
                }
                break;
            case R.id.tv_scan_pay:
                if (cartBeanList.size() == 0) {
                    MToast.showToast(getActivity(), "购物车是空的");
                } else {
                    viewModel.fastFood(getInfo(), 0, AppConstant.getUser().getOperaType().contains("2") ? "0" : "1",
                            "", AppConstant.getUser().getOperaType().contains("2") ? viewModel.table.get() : "");
                }
                break;
            case R.id.tv_money_pay:
                if (cartBeanList.size() == 0) {
                    MToast.showToast(getActivity(), "购物车是空的");
                } else {
                    viewModel.fastFood(getInfo(), 1, AppConstant.getUser().getOperaType().contains("2") ? "0" : "1",
                            "", AppConstant.getUser().getOperaType().contains("2") ? viewModel.table.get() : "");
                }
                break;
            case R.id.tv_other_pay:
                if (cartBeanList.size() == 0) {
                    MToast.showToast(getActivity(), "购物车是空的");
                } else {
//                    viewModel.fastFood(getInfo(), 2, AppConstant.getUser().getOperaType().contains("2") ? "0" : "1");
                    otherPay();
                }
                break;
            case R.id.tv_reserve:
                ReserveDialog dialog = new ReserveDialog();
                dialog.setOnConfirmClick(new ReserveDialog.OnConfirmClick() {
                    @Override
                    public void confirm(String name, String phone, double money, String remark) {
                        viewModel.reserve(getInfo(), name, phone, remark, money);
                    }
                });
                dialog.show(getActivity().getFragmentManager(), "reserve");
                break;
        }
    }

    public void otherPay() {
        if (AppConstant.getUser().getOperaType().contains("3")) {
            showBindDialog();
        } else if (AppConstant.getUser().getOperaType().contains("2")) {
            viewModel.fastFood(getInfo(), 2, "0", "", viewModel.table.get());
        }
    }

    public void fastSuccess(String id, int type) {
//        cartBeanList.clear();
//        cartAdapter.notifyDataSetChanged();
        switch (type) {
            case 0://扫码
                billId = id;
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent, 1001);
                break;
            case 1://快速
                viewModel.getPay(id);
                break;
            case 2://不设置桌位
                viewModel.getOrderFoodList(id, false);
                break;
            case 3://选择桌位
                viewModel.getOrderFoodList(id, false);
                break;
            case 4://输入桌位
                viewModel.getOrderFoodList(id, true);
                break;
        }
    }

    public void reserveSuccess(String id) {
        cartBeanList.clear();
        cartAdapter.notifyDataSetChanged();
        viewModel.price.set(0.0);
    }

    public void showBindDialog() {
        if (bindLineUpDialog != null) {
            bindLineUpDialog.refreshTable(viewModel.tableEntityList);
        } else {
            bindLineUpDialog = new BindLineUpDialog();
            bindLineUpDialog.setTableList(viewModel.tableEntityList);
            bindLineUpDialog.setRoomList(viewModel.roomEntityList);
            bindLineUpDialog.setOnConfirmClick(new BindLineUpDialog.OnConfirmClick() {
                @Override
                public void confirm(RoomEntity roomEntity, TableEntity tableEntity) {
//                    viewModel.bind(tableEntity);
                    table = tableEntity;
                    viewModel.fastFood(getInfo(), 3, "", tableEntity.getRoomTableId(), tableEntity.getTableName());
                }

                @Override
                public void selectRoom(RoomEntity roomEntity) {
                    viewModel.getTables(roomEntity);
                }

                @Override
                public void cancel() {
                    bindLineUpDialog = null;
                    if (!TextUtils.isEmpty(viewModel.table.get())) {
                        MToast.showToast(getActivity(), "请输入桌号");
                    } else {
                        viewModel.fastFood(getInfo(), 4, "1", "", viewModel.table.get());
                    }
                }
            });
            bindLineUpDialog.show(getActivity().getFragmentManager(), "");
        }
    }

    public void intentToPay(List<OrderFoodEntity> orderFoodEntities, String id, boolean b) {
        EventPayBean bean = new EventPayBean();
        bean.setFlag(3);
        bean.setId(id);
        bean.setmList(orderFoodEntities);
        if (table != null) {
            bean.setTableEntity(table);
        }
        bean.setPrice(viewModel.price.get());
        bean.setName(b ? viewModel.table.get() : "");
        EventBus.getDefault().postSticky(DataEvent.make(AppConstant.EVENT_PAY, bean));
        Intent intent = new Intent(getActivity(), PayActivity.class);
        startActivity(intent);
        viewModel.price.set(0.0);
        cartBeanList.clear();
        cartAdapter.notifyDataSetChanged();
    }


    private void scanOrQuickbill(String payType, String result, double money, String memberId) {
        List<BillJson.BillJsonBase> t = new ArrayList<>();
        BillJson.BillJsonBase base2 = new BillJson.BillJsonBase();
        t.add(base2);

        BillJson.BillJsonBase base = new BillJson.BillJsonBase();
        base.setGuid(String.valueOf(System.currentTimeMillis()) + result);
        base.setPice(String.valueOf(money));
        base.setPiceGuid(payType);
        base.setSate("0");
        base.setType("1");
        base.setIsSql("1");
        t.add(base);

        BillJson.TeacherJson teacherJson = new BillJson.TeacherJson();
        List<BillJson.BillJsonBase> youHui = new ArrayList<>();
        youHui.add(base2);
        teacherJson.setTeacher(youHui);
        String tStr = new Gson().toJson(teacherJson);
        Log.i("ttt", "---tStr:" + tStr);

        BillJson.Quanxian quanxian = new BillJson.Quanxian();
        List<BillJson.BillJsonBase> q = new ArrayList<>();
        q.add(base2);
        quanxian.setQuanxian(q);
        String qStr = new Gson().toJson(quanxian);
        Log.i("ttt", "---qStr:" + qStr);
        BillJson.Pays pays = new BillJson.Pays();
        List<BillJson.BillJsonBase> p = new ArrayList<>();
        p.add(base);
        pays.setQuanxian(p);
        String pStr = new Gson().toJson(pays);
        Log.i("ttt", "---pStr:" + pStr);

        viewModel.bill(result, "", money, 0, qStr
                , tStr, pStr, payType, 1, money, "", 0, "4", memberId);
    }

    public void billSuccess(String msg) {
        MToast.showToast(getActivity(), msg);
        cartBeanList.clear();
        cartAdapter.notifyDataSetChanged();
        viewModel.price.set(0.0);
    }

    public void bill(String payType, String result, double money, String memberId, String str) {
        if (str.contains("支付中")) {
            ConfirmDialog confirmDialog = new ConfirmDialog();
            confirmDialog.setTitle("支付中");
            confirmDialog.setMessage("用户正在支付中，是否确认已支付");
            confirmDialog.setOnDialogSure(new ConfirmDialog.OnDialogSure() {
                @Override
                public void confirm() {
                    scanOrQuickbill(payType, result, money, memberId);
                }

                @Override
                public void cancel() {

                }
            });
            confirmDialog.show(getActivity().getFragmentManager(), "");
        } else {
            scanOrQuickbill(payType, result, money, memberId);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        Bundle bundle = data.getExtras();
        if (bundle == null) {
            return;
        }
        if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
            String result = bundle.getString(CodeUtils.RESULT_STRING);
            viewModel.scanBill(result, viewModel.price.get(), billId);
        } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
            MToast.showToast(getActivity(), "解析二维码失败");
        }
    }


}
