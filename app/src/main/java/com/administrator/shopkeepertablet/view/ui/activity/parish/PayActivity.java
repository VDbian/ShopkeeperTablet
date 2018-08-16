package com.administrator.shopkeepertablet.view.ui.activity.parish;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.administrator.shopkeepertablet.AppConstant;
import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.databinding.ActivityPayBinding;
import com.administrator.shopkeepertablet.di.app.AppComponent;
import com.administrator.shopkeepertablet.di.parish.DaggerParishActivityComponent;
import com.administrator.shopkeepertablet.di.parish.ParishActivityModule;
import com.administrator.shopkeepertablet.model.entity.CardEntity;
import com.administrator.shopkeepertablet.model.entity.DiscountEntity;
import com.administrator.shopkeepertablet.model.entity.ElseCouponEntity;
import com.administrator.shopkeepertablet.model.entity.OrderEntity;
import com.administrator.shopkeepertablet.model.entity.OrderFoodEntity;
import com.administrator.shopkeepertablet.model.entity.PayMeEntity;
import com.administrator.shopkeepertablet.model.entity.TableEntity;
import com.administrator.shopkeepertablet.model.entity.bean.BillJson;
import com.administrator.shopkeepertablet.model.entity.bean.EventPayBean;
import com.administrator.shopkeepertablet.utils.DataEvent;
import com.administrator.shopkeepertablet.utils.MToast;
import com.administrator.shopkeepertablet.view.ui.BaseActivity;
import com.administrator.shopkeepertablet.view.ui.adapter.OrderFoodAdapter;
import com.administrator.shopkeepertablet.view.ui.adapter.PayWayAdapter;
import com.administrator.shopkeepertablet.view.widget.ConfirmDialog;
import com.administrator.shopkeepertablet.view.widget.CouponDialog;
import com.administrator.shopkeepertablet.view.widget.DiscountDialog;
import com.administrator.shopkeepertablet.view.widget.ElseDiscountsDialog;
import com.administrator.shopkeepertablet.view.widget.PayMoneyDialog;
import com.administrator.shopkeepertablet.view.widget.PermissionDiscountDialog;
import com.administrator.shopkeepertablet.view.widget.PermissionRemissionDialog;
import com.administrator.shopkeepertablet.view.widget.PopupWindowMember;
import com.administrator.shopkeepertablet.view.widget.RecyclerViewItemDecoration;
import com.administrator.shopkeepertablet.viewmodel.PayViewModel;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Description:
 * Author CC
 * Time 2018/7/22
 */


public class PayActivity extends BaseActivity implements View.OnClickListener {

    private ActivityPayBinding binding;
    @Inject
    PayViewModel viewModel;
    private int flag;

    private OrderEntity orderEntity;
    private List<OrderFoodEntity> mList = new ArrayList<>();
    private PopupWindowMember popupWindowMember;
    private Double oneDiscount = 0.0;//单个菜品打折金额
    private boolean isFree = false;
    private List<ElseCouponEntity> elseCouponEntities = new ArrayList<>();
    private Double elseCouponMoney = 0.0; //其他优惠金额
    private Double payMoney = 0.0; //结账方式所有金额
    private Double scoreMoney = 0.0; //积分抵用金额
    private Double memberMoney = 0.0; //会员优惠
    private PayWayAdapter payWayAdapter;
    private List<PayMeEntity> payMeEntityList = new ArrayList<>();

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerParishActivityComponent.builder()
                .appComponent(appComponent)
                .parishActivityModule(new ParishActivityModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pay);
        binding.setViewModel(viewModel);
        initView();
    }

    private void initView() {
        viewModel.getOrderData(orderEntity.getType());
        binding.llMember.setVisibility(View.INVISIBLE);
        binding.tvDiscountNum.setVisibility(View.INVISIBLE);
        switch (flag) {
            case 1://堂点
                binding.llTitle.setVisibility(View.VISIBLE);
                binding.tvReturnBill.setVisibility(View.GONE);
                break;
            case 2://并单
                break;
            case 3://反结账
                binding.llTitle.setVisibility(View.GONE);
                binding.tvReturnBill.setVisibility(View.VISIBLE);
                break;
            case 4://快餐
                break;
        }

        OrderFoodAdapter adapter = new OrderFoodAdapter(this, mList);
        binding.rlvOrder.setAdapter(adapter);
        binding.rlvOrder.setLayoutManager(new LinearLayoutManager(this));
        binding.rlvOrder.addItemDecoration(new RecyclerViewItemDecoration(5));
        adapter.setOnItemClick(new OrderFoodAdapter.OnItemClick() {
            @Override
            public void onItemClick(OrderFoodEntity orderFoodEntity, int position) {
                if (AppConstant.getUser().getPermissionValue().contains("quanxiandazhe")) {
                    if (viewModel.permissionDiscount.get() > 0) {
                        MToast.showToast(PayActivity.this, "您已进行权限打折");
                    } else {
                        DiscountDialog discountDialog = new DiscountDialog();
                        discountDialog.setTitle(orderFoodEntity.getProductName());
                        discountDialog.setDiscount(orderFoodEntity.getDiscount());
                        discountDialog.setOnConfirmClick(new DiscountDialog.OnConfirmClick() {
                            @Override
                            public void confirm(String discount) {
                                int num = Integer.valueOf(discount);
                                if (num > 0 && num < 100) {
                                    if (orderFoodEntity.getDiscount() != 0) {
                                        oneDiscount -= twoDecimal(orderFoodEntity.getPrice() * (num - orderFoodEntity.getDiscount()) / 100);
                                        orderFoodEntity.setDiscount(num);
                                    } else {
                                        orderFoodEntity.setDiscount(num);
                                        oneDiscount += twoDecimal(orderFoodEntity.getPrice() * (100 - num) / 100);
                                    }
                                    changedMoney();
                                } else {
                                    MToast.showToast(PayActivity.this, "请输入正确的打折数");
                                }
                            }
                        });
                        discountDialog.show(getFragmentManager(), "");
                    }
                } else {
                    MToast.showToast(PayActivity.this, "没有打折权限");
                }
            }
        });

        payWayAdapter = new PayWayAdapter(this, payMeEntityList);
        binding.rlvPay.setAdapter(payWayAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        binding.rlvPay.setLayoutManager(linearLayoutManager);
        binding.rlvPay.addItemDecoration(new RecyclerViewItemDecoration(6));
        payWayAdapter.setOnItemClick(new PayWayAdapter.OnItemClick() {
            @Override
            public void onItemClick(PayMeEntity entity, int position) {
                showDialogPayMoney(entity, position);
            }
        });

        binding.tvMember.setOnClickListener(this);
        binding.tvCoupon.setOnClickListener(this);
        binding.tvDiscount.setOnClickListener(this);
        binding.tvFree.setOnClickListener(this);
        binding.tvRemission.setOnClickListener(this);
        binding.tvElseDiscounts.setOnClickListener(this);
        binding.rlBack.setOnClickListener(this);
        changedMoney();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_member:
                showPopMember();
                break;
            case R.id.tv_coupon:
                showDialogCoupon();
                break;
            case R.id.tv_discount:
                if (AppConstant.getUser().getPermissionValue().contains("quanxiandazhe")) {
                    if (oneDiscount > 0) {
                        MToast.showToast(this, "您已进行单个菜品打折");
                    } else {
                        viewModel.getDiscountList();
                    }
                } else {
                    MToast.showToast(this, "没有打折权限");
                }
                break;
            case R.id.tv_free:
                if (!AppConstant.getUser().getPermissionValue().contains("quanxianmiandan")) {
                    MToast.showToast(this, "没有免单权限");
                    return;
                }
                showDialogFree();
                break;
            case R.id.tv_remission:
                if (!AppConstant.getUser().getPermissionValue().contains("quanxianjianmian")) {
                    MToast.showToast(this, "没有减免权限");
                    return;
                }
                showDialogRemission();
                break;
            case R.id.tv_else_discounts:
//                showDialogElseDiscount();
                viewModel.getElseCoupon();
                break;
            case R.id.rl_back:
                finish();
                break;
        }
    }


    private void showPopMember() {
        popupWindowMember = new PopupWindowMember(this, viewModel);
        popupWindowMember.setOnCallBackListener(new PopupWindowMember.OnCallBackListener() {
            @Override
            public void confirm() {
                binding.llMember.setVisibility(View.VISIBLE);

            }
        });
        popupWindowMember.showPopupWindowUp();
    }

    public void searchSuccess() {
        if (popupWindowMember != null && popupWindowMember.isShowing()) {
            popupWindowMember.searchSuccess();
        }
    }

    private void showDialogCoupon() {
        CouponDialog couponDialog = new CouponDialog();
        couponDialog.setViewModel(viewModel);
        couponDialog.setOnConfirmClick(new CouponDialog.OnConfirmClick() {
            @Override
            public void confirm() {

            }
        });
        couponDialog.show(getFragmentManager(), "");
    }

    public void showDialogDiscount() {
        PermissionDiscountDialog permissionDiscountDialog = new PermissionDiscountDialog();
        permissionDiscountDialog.setViewModel(viewModel);
        permissionDiscountDialog.setOnConfirmClick(new PermissionDiscountDialog.OnConfirmClick() {
            @Override
            public void confirm() {
                if (!TextUtils.isEmpty(viewModel.discountNum.get())) {
                    int num = Integer.valueOf(viewModel.discountNum.get());
                    if (num > 0 && num < 100) {
                        viewModel.getDazhe(viewModel.billId.get(), num, "");
                        binding.tvDiscountNum.setText(num / 10.0 + "折");
                        binding.tvDiscountNum.setVisibility(View.VISIBLE);
                    } else {
                        MToast.showToast(PayActivity.this, "输入的折扣数不符合规范");
                    }
                }
            }

            @Override
            public void itemClick(DiscountEntity discountEntity) {
                binding.tvDiscountNum.setText(Double.valueOf(discountEntity.getCount()) / 10.0 + "折");
                binding.tvDiscountNum.setVisibility(View.VISIBLE);
                viewModel.getDazhe(viewModel.billId.get(), 0, discountEntity.getId());
            }
        });
        permissionDiscountDialog.show(getFragmentManager(), "PermissionDiscount");
    }

    private void showDialogRemission() {
        PermissionRemissionDialog permissionRemissionDialog = new PermissionRemissionDialog();
        permissionRemissionDialog.setViewModel(viewModel);
        permissionRemissionDialog.setOnConfirmClick(new PermissionRemissionDialog.OnConfirmClick() {
            @Override
            public void confirm(double d) {
                viewModel.permissionRemission.set(d);
                changedMoney();
            }

            @Override
            public void click(int i) {
                Double pay = viewModel.shouldPay.get() + viewModel.permissionRemission.get();
//                Log.e("vd", twoDecimal(pay % 10) + "");
//                Log.e("vd", twoDecimal(pay % 1) + "");
//                Log.e("vd", twoDecimal(pay % 0.1) + "");
                switch (i) {
                    case 0:
                        viewModel.permissionRemission.set(twoDecimal(pay % 10));
                        changedMoney();
                        break;
                    case 1:
                        viewModel.permissionRemission.set(twoDecimal(pay % 1));
                        changedMoney();
                        break;
                    case 2:
                        viewModel.permissionRemission.set(twoDecimal(pay % 0.1));
                        changedMoney();
                        break;
                    case 3:
                        double k = pay % 1;
                        if (k >= 0.5) {
                            viewModel.permissionRemission.set(k - 1);
                        } else {
                            viewModel.permissionRemission.set(k);
                        }
                        changedMoney();
                        break;
                }
            }
        });
        permissionRemissionDialog.show(getFragmentManager(), "");
    }

    private void showDialogFree() {
        ConfirmDialog confirmDialog = new ConfirmDialog();
        confirmDialog.setMessage(isFree ? "是否取消免单" : "是否免单");
        confirmDialog.setOnDialogSure(new ConfirmDialog.OnDialogSure() {
            @Override
            public void confirm() {
                if (!isFree) {
                    isFree = true;
                    oneDiscount = 0.0;
                    memberMoney = 0.0;
                    elseCouponMoney = 0.0;
                    viewModel.permissionRemission.set(0.0);
                    viewModel.permissionDiscount.set(0.0);
                    viewModel.discountNum.set("");
                    binding.tvDiscountNum.setVisibility(View.INVISIBLE);
                } else {
                    isFree = false;
                }
                changedMoney();
            }

            @Override
            public void cancel() {

            }
        });
        confirmDialog.show(getFragmentManager(), "");
    }

    public void showDialogElseDiscount(List<ElseCouponEntity> couponEntities) {
        ElseDiscountsDialog elseDiscountsDialog = new ElseDiscountsDialog();
        elseDiscountsDialog.setViewModel(viewModel);
        elseDiscountsDialog.setEntityList(couponEntities);
        elseDiscountsDialog.setOnConfirmClick(new ElseDiscountsDialog.OnConfirmClick() {
            @Override
            public void confirm(List<ElseCouponEntity> mList) {
                if (mList != null && !mList.isEmpty()) {
                    Double elseCouponMoneyTemp = 0.0;
                    elseCouponEntities = mList;
                    String couponId = "";
                    for (ElseCouponEntity bean : mList) {
                        elseCouponMoneyTemp += bean.getPrice();
                        couponId += bean.getId() + ",";
                    }
                    couponId = couponId.substring(0, couponId.length() - 1);

                    BillJson.Quanxian quanxian = new BillJson.Quanxian();
                    List<BillJson.BillJsonBase> q = new ArrayList<>();
                    if (elseCouponMoneyTemp > 0) {
                        for (int k = 0; k < elseCouponEntities.size(); k++) {
                            ElseCouponEntity entity = elseCouponEntities.get(k);
                            if (entity != null) {
                                BillJson.BillJsonBase pe = new BillJson.BillJsonBase();
                                pe.setGuid(System.currentTimeMillis() + "");
                                pe.setPice(entity.getPrice() + "");
                                pe.setPiceGuid("");
                                pe.setSate(entity.getName() + "");
                                pe.setType(5 + "");
                                pe.setIsSql(entity.getId() + "");
                                q.add(pe);
                            }
                        }
                    }
                    quanxian.setQuanxian(q);
                    String qStr = new Gson().toJson(quanxian);
                    viewModel.getOtherYouhui(couponId, viewModel.billId.get(), elseCouponMoneyTemp, viewModel.shouldPay.get(), qStr);
                }
            }
        });
        elseDiscountsDialog.show(getFragmentManager(), "");
    }

    public void showDialogPayMoney(PayMeEntity payMeEntity, int position) {
        PayMoneyDialog payMoneyDialog = new PayMoneyDialog();
        payMoneyDialog.setTitle(payMeEntity.getName());
        payMoneyDialog.setMoney(payMeEntity.getMoney() == 0 ? viewModel.shouldPay.get() : payMeEntity.getMoney());
        payMoneyDialog.setOnConfirmClick(new PayMoneyDialog.OnConfirmClick() {
            @Override
            public void confirm(Double money) {
                payMeEntityList.get(position).setMoney(money);
                if (money == 0) {
                    payMeEntityList.get(position).setSelected(false);
                } else {
                    payMeEntityList.get(position).setSelected(true);
                }
                payWayAdapter.notifyDataSetChanged();
            }
        });
        payMoneyDialog.show(getFragmentManager(), "");
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onDataEvent(DataEvent event) {
        if (event.getMessageTag() == AppConstant.EVENT_PAY) {
            EventPayBean bean = (EventPayBean) event.getMessageData();
            flag = bean.getFlag();
            mList = bean.getmList();
            orderEntity = bean.getOrder();
            viewModel.tableEntity.set(bean.getTableEntity());
            viewModel.roomName.set(bean.getRoomName());
            viewModel.time.set(viewModel.getTime(bean.getTableEntity().getKaiTime()));
            viewModel.tableList.set(bean.getTableEntityList());
            Double price = bean.getTableEntity().getPrice();
            String billId = bean.getTableEntity().getBillId();
            if (bean.getTableEntityList() != null && bean.getTableEntityList().size() != 0) {
                for (TableEntity tableEntity : bean.getTableEntityList()) {
                    price += tableEntity.getPrice();
                    billId += "," + tableEntity.getBillId();
                }
            }
            viewModel.billId.set(billId);
            viewModel.price.set(price);
        }
    }

    public void discountSuccess(String money) {
        Double d = Double.valueOf(money);
        if (d > 0) {
            viewModel.permissionDiscount.set(d);
            viewModel.permissionRemission.set(0.0);
            changedMoney();
        }
    }

    public void elseCouponSuccess(Double elseCoupon) {
        MToast.showToast(this, "其他优惠金额为" + elseCoupon + "元");
        elseCouponMoney = elseCoupon;
        changedMoney();
    }

    private double twoDecimal(double d) {
        return new BigDecimal(d).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    private void bill() {
        if (!AppConstant.getUser().getPermissionValue().contains("queren")) {
            MToast.showToast(this, "没有确认结账权限");
            return;
        }
        if (viewModel.needPay.get() == 0) {
            List<BillJson.BillJsonBase> t = new ArrayList<>();
            BillJson.BillJsonBase base = new BillJson.BillJsonBase();
            t.add(base);
            if (scoreMoney > 0) {
                BillJson.BillJsonBase jsonBase = new BillJson.BillJsonBase();
                jsonBase.setType("1");
                jsonBase.setPice(scoreMoney + "");
                jsonBase.setSate("0");
                jsonBase.setGuid(System.currentTimeMillis() + "00");
                jsonBase.setPiceGuid(viewModel.integral + "");
                t.add(jsonBase);
            }
            for (int i = 0; i < viewModel.cardList.get().size(); i++) {
                CardEntity bean = viewModel.cardList.get().get(i);
                if (bean.isSelect()) {
                    BillJson.BillJsonBase jsonBase = new BillJson.BillJsonBase();
                    if (bean.getType().equals("1") || bean.getType().equals("2")) {
                        jsonBase.setType("2");
                    } else {
                        jsonBase.setType("4");
                    }
                    jsonBase.setGuid(System.currentTimeMillis() + "0" + i);
                    jsonBase.setPiceGuid(bean.getId());
                    jsonBase.setPice(bean.getMoney() + "");
                    jsonBase.setSate("0");
                    t.add(jsonBase);
                }
            }

            BillJson.TeacherJson teacherJson = new BillJson.TeacherJson();
            teacherJson.setTeacher(t);
            String tStr = new Gson().toJson(teacherJson);

            BillJson.Quanxian quanxian = new BillJson.Quanxian();
            List<BillJson.BillJsonBase> q = new ArrayList<>();
            q.add(base);

            if (oneDiscount + viewModel.permissionDiscount.get() > 0) {
                BillJson.BillJsonBase d = new BillJson.BillJsonBase();
                d.setGuid(System.currentTimeMillis() + "");
                d.setPice(oneDiscount + viewModel.permissionDiscount.get() + "");
                if (oneDiscount > 0) {
                    d.setType("6");
                } else {
                    d.setType("1");
                }
                q.add(d);
            }
            if (viewModel.permissionRemission.get() > 0) {
                BillJson.BillJsonBase j = new BillJson.BillJsonBase();
                j.setGuid(System.currentTimeMillis() + "");
                j.setPice(viewModel.permissionRemission.get() + "");
                j.setType("2");
                q.add(j);
            }
            if (isFree) {
                BillJson.BillJsonBase m = new BillJson.BillJsonBase();
                m.setGuid(System.currentTimeMillis() + "");
//                m.setPice(weixinOrderBean.getYinfu() + "");
                m.setPice(viewModel.shouldPay.get() + "");
                m.setType("3");
                q.add(m);
            }
            if (elseCouponMoney > 0) {
                for (int k = 0; k < elseCouponEntities.size(); k++) {
                    ElseCouponEntity entity = elseCouponEntities.get(k);
                    if (entity != null) {
                        BillJson.BillJsonBase pe = new BillJson.BillJsonBase();
                        pe.setGuid(System.currentTimeMillis() + "");
                        pe.setPice(entity.getPrice() + "");
                        pe.setPiceGuid("");
                        pe.setSate(entity.getName() + "");
                        pe.setType(5 + "");
                        pe.setIsSql(entity.getId() + "");
                        q.add(pe);
                    }
                }
            }
            quanxian.setQuanxian(q);
            String qStr = new Gson().toJson(quanxian);
            Log.d("ttt", "quanxian:" + qStr);

            BillJson.Pays pays = new BillJson.Pays();
            List<BillJson.BillJsonBase> p = new ArrayList<>();
            p.add(base);

            for (int k = 0; k < payMeEntityList.size(); k++) {
                PayMeEntity entity = payMeEntityList.get(k);
                if (entity != null && entity.isSelected()) {
                    BillJson.BillJsonBase pe = new BillJson.BillJsonBase();
                    pe.setGuid(System.currentTimeMillis() + "");
                    pe.setPice(entity.getMoney() + "");
                    pe.setPiceGuid(entity.getGuid() + "");
                    p.add(pe);
                }
            }

            pays.setQuanxian(p);
            String pStr = new Gson().toJson(pays);

            double result = viewModel.shouldPay.get();
            double free = viewModel.discount.get();

            Log.i("ttt", "totleMoney:" + result);

            Double moLing = 0.0;
            Double rounding = 0.0;
            if (viewModel.permissionRemission.get() > 0) {
                moLing = viewModel.permissionRemission.get();
            } else {
                rounding = -viewModel.permissionRemission.get();
            }

            viewModel.bill(viewModel.billId.get(), "", viewModel.price.get(), viewModel.warePrice.get(), qStr, tStr, pStr, 1, result, "",
                    free, "4", "", "", moLing, rounding);
//            if (type == P3) {
//                if (!TextUtils.isEmpty(tabName)) {
//                    presenter.bill(billId, App.INSTANCE().getShopID(), tableEntity != null ? tableEntity.getRoomTableID() : "",
//                            memberId, weixinOrderBean.getYuanjia(), weixinOrderBean.getCanju(), qStr, tStr, pStr, 1, result, tabName,
//                            free, "4", guiId, "", mading, rounding);
//                } else {
//                    presenter.bill(billId, App.INSTANCE().getShopID(), tableEntity != null ? tableEntity.getRoomTableID() : "",
//                            memberId, weixinOrderBean.getYuanjia(), weixinOrderBean.getCanju(), qStr, tStr, pStr, 1, result, tableEntity != null ? tableEntity.getTableName() : "",
//                            free, "4", guiId, "", mading, rounding);
//                }
//            } else if (type == P4) {
//                presenter.bill(billId, App.INSTANCE().getShopID(), tableEntity != null ? tableEntity.getRoomTableID() : "",
//                        memberId, weixinOrderBean.getYuanjia(), weixinOrderBean.getCanju(), qStr
//                        , tStr, pStr, 1, result, tableEntity != null ? tableEntity.getTableName() : "", free, "3", guiId, "", mading, rounding);
//            } else if (type == P6) {
//                presenter.rebill(order.getBillid(), App.INSTANCE().getShopID(), order.getTableId(), memberId, weixinOrderBean.getYuanjia(), weixinOrderBean.getCanju(), qStr
//                        , tStr, pStr, order.getType().equals("7") ? "7" : "4", free, order.getBillid(), result, mading, rounding);
//            } else {
//                presenter.bill(order.getBillid(), App.INSTANCE().getShopID(), order.getTableId(), memberId, weixinOrderBean.getYuanjia(), weixinOrderBean.getCanju(), qStr
//                        , tStr, pStr, order.getPeopleCount(), result, order.getTableName(), free, "7", guiId, "", mading, rounding);
//            }
        }
    }

    private void initPayWay() {
        payMeEntityList.clear();
        String[] payTypes = getResources().getStringArray(R.array.payType);
        for (int i = 0; i < payTypes.length; i++) {
            Log.e("vd", AppConstant.getUser().getCashPayType());
            if (AppConstant.getUser().getCashPayType().contains(i + 1 + "")) {
                String anA = payTypes[i];
                if (anA.equals("现金")) {
                    payMeEntityList.add(new PayMeEntity(anA, true, i + 1));
                } else {
                    payMeEntityList.add(new PayMeEntity(anA, false, i + 1));
                }
            }
        }
        payWayAdapter.notifyDataSetChanged();
    }

    private Double getPayMoney() {
        payMoney = 0.0;
        if (payMeEntityList != null && !payMeEntityList.isEmpty()) {
            for (PayMeEntity entity : payMeEntityList) {
                payMoney += entity.getMoney();
            }
        }
        return payMoney;
    }

    private void initMoney() {
        if (isFree) {
            viewModel.discount.set(viewModel.price.get());
        } else {
            viewModel.discount.set(oneDiscount + viewModel.permissionRemission.get() + viewModel.permissionDiscount.get() + elseCouponMoney + memberMoney);
        }
        viewModel.shouldPay.set(viewModel.price.get() + viewModel.warePrice.get() - viewModel.discount.get());
        viewModel.shouldReturn.set(viewModel.payment.get() - viewModel.shouldPay.get() > 0 ? viewModel.payment.get() - viewModel.shouldPay.get() : 0.0);
        viewModel.needPay.set(viewModel.shouldPay.get() - viewModel.payment.get() > 0 ? viewModel.shouldPay.get() - viewModel.payment.get() : 0.0);
    }

    private void changedMoney() {
        initMoney();
        initPayWay();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
