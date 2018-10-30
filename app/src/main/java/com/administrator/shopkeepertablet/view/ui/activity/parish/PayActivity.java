package com.administrator.shopkeepertablet.view.ui.activity.parish;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
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
import com.administrator.shopkeepertablet.model.entity.BaseEntity;
import com.administrator.shopkeepertablet.model.entity.CardEntity;
import com.administrator.shopkeepertablet.model.entity.DiscountEntity;
import com.administrator.shopkeepertablet.model.entity.ElseCouponEntity;
import com.administrator.shopkeepertablet.model.entity.GuaZhangEntity;
import com.administrator.shopkeepertablet.model.entity.OrderEntity;
import com.administrator.shopkeepertablet.model.entity.OrderFoodEntity;
import com.administrator.shopkeepertablet.model.entity.PayMeEntity;
import com.administrator.shopkeepertablet.model.entity.TableEntity;
import com.administrator.shopkeepertablet.model.entity.bean.BillJson;
import com.administrator.shopkeepertablet.model.entity.bean.EventPayBean;
import com.administrator.shopkeepertablet.utils.DataEvent;
import com.administrator.shopkeepertablet.utils.MLog;
import com.administrator.shopkeepertablet.utils.MToast;
import com.administrator.shopkeepertablet.view.ui.BaseActivity;
import com.administrator.shopkeepertablet.view.ui.adapter.OrderFoodAdapter;
import com.administrator.shopkeepertablet.view.ui.adapter.PayWayAdapter;
import com.administrator.shopkeepertablet.view.widget.ConfirmDialog;
import com.administrator.shopkeepertablet.view.widget.CouponDialog;
import com.administrator.shopkeepertablet.view.widget.DiscountDialog;
import com.administrator.shopkeepertablet.view.widget.ElseDiscountsDialog;
import com.administrator.shopkeepertablet.view.widget.GuaZhangDialog;
import com.administrator.shopkeepertablet.view.widget.PayMoneyDialog;
import com.administrator.shopkeepertablet.view.widget.PermissionDiscountDialog;
import com.administrator.shopkeepertablet.view.widget.PermissionRemissionDialog;
import com.administrator.shopkeepertablet.view.widget.PopupWindowMember;
import com.administrator.shopkeepertablet.view.widget.RecyclerViewItemDecoration;
import com.administrator.shopkeepertablet.viewmodel.PayViewModel;
import com.google.gson.Gson;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.RejectedExecutionHandler;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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
    private String tableId = "";
    private String tableName = "";
    private int peopleCount = 1;
    private String guid = "";

    private OrderEntity orderEntity;
    private List<OrderFoodEntity> mList = new ArrayList<>();
    private PopupWindowMember popupWindowMember;
    private CouponDialog couponDialog;
    private Double oneDiscount = 0.0;//单个菜品打折金额
    private boolean isFree = false;
    private List<ElseCouponEntity> elseCouponEntities = new ArrayList<>();
    private Double scoreMoney = 0.0; //积分抵用金额
    private Double memberCardMoney = 0.0;//会员卡券优惠金额
    private Double memberMoney = 0.0; //会员优惠
    private Double couponMoney = 0.0;//优惠券金额
    private PayWayAdapter payWayAdapter;
    private List<PayMeEntity> payMeEntityList = new ArrayList<>();
    private OrderFoodAdapter adapter;

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
        viewModel.getOrderData(orderEntity == null ? 4 : orderEntity.getType());
        binding.llMember.setVisibility(View.INVISIBLE);
        binding.ivMember.setVisibility(View.INVISIBLE);
        binding.tvDiscountNum.setVisibility(View.INVISIBLE);
        switch (flag) {
            case 1://堂点
                binding.llTitle.setVisibility(View.VISIBLE);
                binding.tvReturnBill.setVisibility(View.GONE);
                break;
            case 2://并单
                binding.llTitle.setVisibility(View.GONE);
                binding.tvReturnBill.setVisibility(View.VISIBLE);
                binding.tvReturnBill.setText("并单");
                break;
            case 3://反结账
                binding.llTitle.setVisibility(View.GONE);
                binding.tvReturnBill.setVisibility(View.VISIBLE);
                break;
            case 4://快餐
                binding.llTitle.setVisibility(View.GONE);
                binding.tvReturnBill.setVisibility(View.VISIBLE);
                binding.tvReturnBill.setText("快餐");
                break;
        }

        adapter = new OrderFoodAdapter(this, mList);
        binding.rlvOrder.setAdapter(adapter);
        binding.rlvOrder.setLayoutManager(new LinearLayoutManager(this));
        binding.rlvOrder.addItemDecoration(new RecyclerViewItemDecoration(5));
        adapter.setOnItemClick(new OrderFoodAdapter.OnItemClick() {
            @Override
            public void onItemClick(OrderFoodEntity orderFoodEntity, int position) {
                if (AppConstant.getUser().getPermissionValue().contains("quanxiandazhe")) {
                    if (viewModel.permissionDiscount.get() > 0) {
                        MToast.showToast(PayActivity.this, "您已进行权限打折");
                        changeSelect();
                    } else {
                        DiscountDialog discountDialog = new DiscountDialog();
                        discountDialog.setTitle(orderFoodEntity.getProductName());
                        discountDialog.setDiscount(orderFoodEntity.getDiscount());
                        discountDialog.setOnConfirmClick(new DiscountDialog.OnConfirmClick() {
                            @Override
                            public void confirm(String discount) {
                                changeSelect();
                                int num = Integer.valueOf(discount);
                                if (num > 0 && num < 100) {
                                    if (orderFoodEntity.getDiscount() != 0) {
                                        oneDiscount -= twoDecimal(orderFoodEntity.getPrice() * (num - orderFoodEntity.getDiscount()) / 100);
                                        orderFoodEntity.setDiscount(num);
                                    } else {
                                        orderFoodEntity.setDiscount(num);
                                        oneDiscount += twoDecimal(orderFoodEntity.getPrice() * (100 - num) / 100);
                                    }
                                    changedMoney(true);
                                } else {
                                    MToast.showToast(PayActivity.this, "请输入正确的打折数");
                                    changeSelect();
                                }
                            }

                            @Override
                            public void cancel() {
                                changeSelect();
                            }
                        });
                        discountDialog.show(getFragmentManager(), "");
                    }
                } else {
                    MToast.showToast(PayActivity.this, "没有打折权限");
                    changeSelect();
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
                if (!isFree) {
                    choosePayWay(entity, position);
                }
            }
        });

        binding.tvMember.setOnClickListener(this);
        binding.tvCoupon.setOnClickListener(this);
        binding.tvDiscount.setOnClickListener(this);
        binding.tvFree.setOnClickListener(this);
        binding.tvRemission.setOnClickListener(this);
        binding.tvElseDiscounts.setOnClickListener(this);
        binding.rlBack.setOnClickListener(this);
        binding.tvPay.setOnClickListener(this);
        binding.tvScanPay.setOnClickListener(this);
        binding.ivPermissionDiscount.setOnClickListener(this);
        binding.ivPermissionRemission.setOnClickListener(this);
        binding.tvClearCoupon.setOnClickListener(this);
        binding.tvClearMember.setOnClickListener(this);
        binding.tvClearElse.setOnClickListener(this);
        binding.ivMember.setOnClickListener(this);
        binding.tvPrint.setOnClickListener(this);
    }

    public void changeSelect() {
        for (OrderFoodEntity entity : mList) {
            entity.setSelect(false);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_member:
                showPopMember();
                break;
            case R.id.tv_coupon:
                if (scoreMoney > 0 || memberCardMoney > 0) {
                    MToast.showToast(this, "您已选择积分或者会员卡券");
                } else {
                    showDialogCoupon();
                }
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
            case R.id.tv_scan_pay:
                initPayWay();
                Intent intent = new Intent(PayActivity.this, CaptureActivity.class);
                startActivityForResult(intent, 3);
                break;
            case R.id.tv_pay:
                if (!AppConstant.getUser().getPermissionValue().contains("queren")) {
                    MToast.showToast(this, "没有确认结账权限");
                    return;
                }
                bill();
                break;
            case R.id.iv_permission_discount:
                viewModel.permissionDiscount.set(0.0);
                binding.tvDiscountNum.setVisibility(View.INVISIBLE);
                changedMoney(false);
                break;
            case R.id.iv_permission_remission:
                viewModel.permissionRemission.set(0.0);
                changedMoney(false);
                break;
            case R.id.tv_clear_coupon:
                viewModel.cardSearch.set(null);
                couponMoney = 0.0;
                initMoney(true);
                break;
            case R.id.tv_clear_member:
                viewModel.cardEntity.set(null);
                binding.llMember.setVisibility(View.INVISIBLE);
                binding.ivMember.setVisibility(View.INVISIBLE);
                memberCardMoney = 0.0;
                viewModel.integral.set("");
                scoreMoney = 0.0;
                memberMoney = 0.0;
                initMoney(true);
                break;
            case R.id.tv_clear_else:
                viewModel.elsePrice.set(0.0);
                initMoney(true);
                break;
            case R.id.iv_member:
                binding.llMember.setVisibility(View.INVISIBLE);
                binding.ivMember.setVisibility(View.INVISIBLE);
                viewModel.member.set(null);
                memberMoney = 0.0;
                viewModel.cardEntity.set(null);
                memberCardMoney = 0.0;
                viewModel.integral.set("");
                scoreMoney = 0.0;
                initMoney(true);
                break;
            case R.id.tv_print:
                if (orderEntity == null || viewModel.priceEntity.get() == null) {
                    return;
                }
                viewModel.print(orderEntity.getBillId(), orderEntity.getPersonCount(), orderEntity.getTableId(), orderEntity.getTableName(),
                        viewModel.priceEntity.get().getYuanjia(), viewModel.shouldPay.get(), viewModel.discount.get(), "8");
                break;
        }
    }

    private void showPopMember() {
        popupWindowMember = new PopupWindowMember(this, viewModel);
        popupWindowMember.setOnCallBackListener(new PopupWindowMember.OnCallBackListener() {
            @Override
            public void confirm(double score, double card) {
                if (viewModel.member.get()!=null) {
                    binding.llMember.setVisibility(View.VISIBLE);
                    binding.ivMember.setVisibility(View.VISIBLE);
                    scoreMoney = twoDecimal(score);
                    memberCardMoney = twoDecimal(card);
                    memberMoney = twoDecimal(score + card );
                    changedMoney(true);
                }
            }
        });
        popupWindowMember.showPopupWindowUp();
    }

    public void searchSuccess() {
        if (popupWindowMember != null && popupWindowMember.isShowing()) {
            popupWindowMember.searchSuccess();
        }
        if (couponDialog != null && couponDialog.isVisible()) {
            couponDialog.searchSuccess();
        }

    }

    private void showDialogCoupon() {
        couponDialog = new CouponDialog();
        couponDialog.setViewModel(viewModel);
        couponDialog.setOnConfirmClick(new CouponDialog.OnConfirmClick() {
            @Override
            public void confirm() {
                Double money = viewModel.cardSearch.get().getMoney();
                if (money > viewModel.shouldPay.get()) {
                    MToast.showToast(PayActivity.this, "优惠券不能使用");
                } else {
                    couponMoney =twoDecimal(money);
                    changedMoney(true);
                }
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
                changedMoney(true);
            }

            @Override
            public void click(int i) {
                Double pay = viewModel.shouldPay.get() + viewModel.permissionRemission.get();
//                MLog.e("vd", twoDecimal(pay % 10) + "");
//                MLog.e("vd", twoDecimal(pay % 1) + "");
//                MLog.e("vd", twoDecimal(pay % 0.1) + "");
                switch (i) {
                    case 0:
                        viewModel.permissionRemission.set(twoDecimal(pay % 10));
                        changedMoney(true);
                        break;
                    case 1:
                        viewModel.permissionRemission.set(twoDecimal(pay % 1));
                        changedMoney(true);
                        break;
                    case 2:
                        viewModel.permissionRemission.set(twoDecimal(pay % 0.1));
                        changedMoney(true);
                        break;
                    case 3:
                        double k = pay % 1;
                        if (k >= 0.5) {
                            viewModel.permissionRemission.set(k - 1);
                        } else {
                            viewModel.permissionRemission.set(k);
                        }
                        changedMoney(true);
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
                    binding.tvFree.setText("取消免单");
                    oneDiscount = 0.0;
                    memberMoney = 0.0;
                    viewModel.elsePrice.set(0.0);
                    viewModel.permissionRemission.set(0.0);
                    viewModel.permissionDiscount.set(0.0);
                    viewModel.discountNum.set("");
                    binding.tvDiscountNum.setVisibility(View.INVISIBLE);
                } else {
                    isFree = false;
                    binding.tvFree.setText("权限免单");
                }
                changedMoney(true);
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

    private void choosePayWay(PayMeEntity payMeEntity, int position) {
        switch (payMeEntity.getName()) {
            case "会员卡":
                if (viewModel.member.get() == null) {
                    MToast.showToast(this, "请先选择会员");
                } else if (viewModel.member.get().getMoney() < viewModel.shouldPay.get()) {
                    MToast.showToast(this, "会员卡余额不足");
                } else {
                    payMeEntity.setMoney(viewModel.shouldPay.get());
                    initMoney(false);
                    choosePayWayOnly(payMeEntity);
                    bill();
                }
                break;
            case "主扫微信":
                payMeEntity.setMoney(viewModel.shouldPay.get());
                choosePayWayOnly(payMeEntity);
                Intent intent = new Intent(PayActivity.this, CaptureActivity.class);
                startActivityForResult(intent, 1);
                break;
            case "主扫支付宝":
                payMeEntity.setMoney(viewModel.shouldPay.get());
                choosePayWayOnly(payMeEntity);
                Intent intent2 = new Intent(PayActivity.this, CaptureActivity.class);
                startActivityForResult(intent2, 2);
                break;
            case "挂账":
                viewModel.guaZhang(payMeEntity);
                break;
            default:
                if (viewModel.needPay.get() != 0) {
                    showDialogPayMoney(payMeEntity, position);
                }
                break;
        }
    }

    public void guazhangSuccess(List<GuaZhangEntity> result, PayMeEntity payMeEntity) {
        GuaZhangDialog guaZhangDialog = new GuaZhangDialog();
        guaZhangDialog.setmList(result);
        guaZhangDialog.setOnConfirmClick(new GuaZhangDialog.OnConfirmClick() {
            @Override
            public void confirm(GuaZhangEntity entity, double money) {
                if (money <= (viewModel.needPay.get() + payMeEntity.getMoney()) && money >= 0) {
                    if (money == 0) {
                        payMeEntity.setSelected(false);
                        guid = "";
                    } else {
                        guid = entity.getGuid();
                        payMeEntity.setSelected(true);
                    }
                    payMeEntity.setMoney(money);
                    payWayAdapter.notifyDataSetChanged();
                    initMoney(false);
                } else {
                    MToast.showToast(PayActivity.this, "挂账金额不正确");
                    guid = "";
                    payMeEntity.setMoney(0);
                    entity.setSelected(false);
                    payWayAdapter.notifyDataSetChanged();
                    initMoney(false);
                }
            }

            @Override
            public void cancel() {
                guid = "";
                payMeEntity.setMoney(0);
                payMeEntity.setSelected(false);
                payWayAdapter.notifyDataSetChanged();
                initMoney(false);
            }
        });
        guaZhangDialog.show(getFragmentManager(), "");
    }

    public void showDialogPayMoney(PayMeEntity payMeEntity, int position) {
        PayMoneyDialog payMoneyDialog = new PayMoneyDialog();
        payMoneyDialog.setTitle(payMeEntity.getName());
        payMoneyDialog.setMoney(payMeEntity.getMoney() == 0 ? viewModel.needPay.get() : payMeEntity.getMoney());
        if (payMeEntity.getName().equals("会员卡")) {
            payMoneyDialog.setMax(viewModel.needPay.get() > viewModel.member.get().getMoney() ? viewModel.member.get().getMoney() : viewModel.needPay.get());
        } else {
            payMoneyDialog.setMax(viewModel.needPay.get());
        }
        payMoneyDialog.setOnConfirmClick(new PayMoneyDialog.OnConfirmClick() {
            @Override
            public void confirm(Double money) {
                payMeEntityList.get(position).setMoney(money);
                if (money == 0) {
                    payMeEntityList.get(position).setSelected(false);
                } else {
                    payMeEntityList.get(position).setSelected(true);
                    initMoney(false);
                }
                payWayAdapter.notifyDataSetChanged();
            }
        });
        payMoneyDialog.show(getFragmentManager(), "");
    }

    private void choosePayWayOnly(PayMeEntity payMeEntity) {
        for (PayMeEntity entity : payMeEntityList) {
            if (entity.getGuid() == payMeEntity.getGuid()) {
                entity.setSelected(true);
            } else {
                entity.setSelected(false);
                entity.setMoney(0);
            }
        }
        payWayAdapter.notifyDataSetChanged();
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onDataEvent(DataEvent event) {
        if (event.getMessageTag() == AppConstant.EVENT_PAY) {
            EventPayBean bean = (EventPayBean) event.getMessageData();
            Double price = 0.0;
            String billId = "";
            flag = bean.getFlag();
            mList = bean.getmList();
            orderEntity = bean.getOrder();
            viewModel.tableEntity.set(bean.getTableEntity());
            viewModel.roomName.set(bean.getRoomName());
            billId = bean.getId();
            if (bean.getTableEntity() != null) {
                viewModel.time.set(viewModel.getTime(bean.getTableEntity().getKaiTime()));
                price = bean.getTableEntity().getPrice();
                billId = bean.getTableEntity().getBillId();
                tableId = bean.getTableEntity().getRoomTableId();
                tableName = bean.getTableEntity().getTableName();
            }
            viewModel.tableList.set(bean.getTableEntityList());
            if (bean.getOrder() != null) {
                peopleCount = bean.getOrder().getPersonCount();
                billId = bean.getOrder().getBillId();
            }
            if (bean.getTableEntityList() != null && bean.getTableEntityList().size() != 0) {
                for (TableEntity tableEntity : bean.getTableEntityList()) {
                    price += tableEntity.getPrice();
                    billId += "," + tableEntity.getBillId();
                    tableId += "," + tableEntity.getRoomTableId();
                    tableName += "," + tableEntity.getTableName();
                }
            }
            viewModel.billId.set(billId);
            viewModel.price.set(price);
            Double p = bean.getPrice();
            if (p > 0) {
                viewModel.price.set(twoDecimal(p));
            }

        }
    }

    public void discountSuccess(String money) {
        Double d = Double.valueOf(money);
        if (d > 0) {
            viewModel.permissionDiscount.set(d);
            viewModel.permissionRemission.set(0.0);
            changedMoney(true);
        }
    }

    public void elseCouponSuccess(Double elseCoupon) {
        MToast.showToast(this, "其他优惠金额为" + twoDecimal(elseCoupon) + "元");
        viewModel.elsePrice.set(twoDecimal(elseCoupon));
        changedMoney(true);
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
            if (viewModel.cardEntity.get() != null) {
                CardEntity bean = viewModel.cardEntity.get();
                BillJson.BillJsonBase jsonBase = new BillJson.BillJsonBase();
                if (bean.getType().equals("1") || bean.getType().equals("2")) {
                    jsonBase.setType("2");
                } else {
                    jsonBase.setType("4");
                }
                jsonBase.setGuid(System.currentTimeMillis() + "0" + 1);
                jsonBase.setPiceGuid(bean.getId());
                jsonBase.setPice(bean.getMoney() + "");
                jsonBase.setSate("0");
                t.add(jsonBase);

            }
            if (viewModel.cardSearch.get() != null) {
                CardEntity bean = viewModel.cardSearch.get();
                BillJson.BillJsonBase jsonBase = new BillJson.BillJsonBase();
                if (bean.getType().equals("1") || bean.getType().equals("2")) {
                    jsonBase.setType("2");
                } else {
                    jsonBase.setType("4");
                }
                jsonBase.setGuid(System.currentTimeMillis() + "0" + 2);
                jsonBase.setPiceGuid(bean.getId());
                jsonBase.setPice(bean.getMoney() + "");
                jsonBase.setSate("0");
                t.add(jsonBase);
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
            if (viewModel.elsePrice.get() > 0) {
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

            if (flag == 4) {
                viewModel.rebill(viewModel.billId.get(), tableId, viewModel.priceEntity.get().getYuanjia(), viewModel.priceEntity.get().getCanju(), qStr
                        , tStr, pStr, orderEntity.getType() == 7 ? "7" : "4", free, viewModel.billId.get(), result, moLing, rounding);
            } else if (flag == 3) {//快餐
                viewModel.bill(viewModel.billId.get(), tableId, viewModel.priceEntity.get().getYuanjia(), viewModel.priceEntity.get().getCanju(), qStr, tStr, pStr, 1, result, tableName,
                        free, "4", "", "", moLing, rounding);
            } else {
                viewModel.bill(viewModel.billId.get(), tableId, viewModel.priceEntity.get().getYuanjia(), viewModel.priceEntity.get().getCanju(), qStr
                        , tStr, pStr, peopleCount, result, tableName, free, "7", "", "", moLing, rounding);
            }
        }
    }

    private void initPayWay() {
        payMeEntityList.clear();
        String[] payTypes = getResources().getStringArray(R.array.payType);
        for (int i = 0; i < payTypes.length; i++) {
            MLog.e("vd", AppConstant.getUser().getCashPayType());
            if (AppConstant.getUser().getCashPayType().contains(i + 1 + "")) {
                String anA = payTypes[i];
                payMeEntityList.add(new PayMeEntity(anA, false, i + 1));
            }
        }
        payWayAdapter.notifyDataSetChanged();
    }

    //结账方式所有金额
    private Double getPayMoney() {
        Double payMoney = 0.0;
        if (payMeEntityList != null && !payMeEntityList.isEmpty()) {
            for (PayMeEntity entity : payMeEntityList) {
                payMoney += entity.getMoney();
            }
        }
        return payMoney;
    }

    /**
     * 当任一金额变动时，重新计算金额
     *
     * @param bool true 非选择支付方式引起的变化  false 因选择支付方式引起的变化
     */
    private void initMoney(boolean bool) {
//        viewModel.price.set(viewModel.priceEntity.get().getYuanjia());
//        viewModel.shouldPay.set( viewModel.priceEntity.get().getYinfu());
//        viewModel.discount.set( viewModel.priceEntity.get().getYouhui());
        if (isFree) {
            viewModel.discount.set(twoDecimal(viewModel.price.get()));
        } else {
            viewModel.discount.set(twoDecimal(oneDiscount + viewModel.permissionRemission.get() + viewModel.permissionDiscount.get() + viewModel.elsePrice.get() + memberMoney
                    + couponMoney+viewModel.priceEntity.get().getYouhui()));
        }
        viewModel.shouldPay.set(twoDecimal(viewModel.price.get() + viewModel.warePrice.get() - viewModel.discount.get()));
        viewModel.shouldReturn.set(twoDecimal(viewModel.payment.get() - viewModel.shouldPay.get() > 0 ? viewModel.payment.get() - viewModel.shouldPay.get() : 0.0));
        if (bool) {
            viewModel.needPay.set(twoDecimal(viewModel.shouldPay.get() - viewModel.payment.get() > 0 ? viewModel.shouldPay.get() - viewModel.payment.get() : 0.0));
        } else {
            Double d = viewModel.shouldPay.get() - viewModel.payment.get() - getPayMoney();
            viewModel.needPay.set(d > 0 ? twoDecimal(d) : 0.0);
        }
        if (viewModel.shouldPay.get()==0){
            initPayWay();
            choosePayWayOnly(payMeEntityList.get(0));
        }
    }

    public void changedMoney(boolean bool) {
        initPayWay();
        initMoney(bool);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            changedMoney(true);
            return;
        }
        if (requestCode == 1) {
            Bundle bundle = data.getExtras();
            if (bundle == null) {
                return;
            }
            if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                String result = bundle.getString(CodeUtils.RESULT_STRING);
                viewModel.weixinBill(viewModel.billId.get(), result, viewModel.shouldPay.get());
            } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                MToast.showToast(this, "解析二维码失败");
            }
        } else if (requestCode == 2) {
            Bundle bundle = data.getExtras();
            if (bundle == null) {
                return;
            }
            if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                String result = bundle.getString(CodeUtils.RESULT_STRING);
                viewModel.scanBill(result, viewModel.shouldPay.get(), viewModel.billId.get());
            } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                MToast.showToast(this, "解析二维码失败");
            }
        } else if (requestCode == 3) {
            Bundle bundle = data.getExtras();
            if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                String result = bundle.getString(CodeUtils.RESULT_STRING);
                viewModel.scanBill(result, viewModel.shouldPay.get(), viewModel.billId.get());
            } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                MToast.showToast(this, "解析二维码失败");
            }
        }
    }

    public void billSuccess(String msg) {
        MToast.showToast(this, msg);
        EventBus.getDefault().postSticky(DataEvent.make(AppConstant.EVENT_SUCCESS, ""));
        finish();
    }

    private void scanBill(String payType, String result, double money, String memberId) {
        List<BillJson.BillJsonBase> t = new ArrayList<>();
        BillJson.BillJsonBase base = new BillJson.BillJsonBase();
        t.add(base);
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
        if (viewModel.elsePrice.get() > 0) {
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

        double free = viewModel.discount.get();

        Log.i("ttt", "totleMoney:" + result);

        Double moLing = 0.0;
        Double rounding = 0.0;
        if (viewModel.permissionRemission.get() > 0) {
            moLing = viewModel.permissionRemission.get();
        } else {
            rounding = -viewModel.permissionRemission.get();
        }

        if (flag == 4) {//快餐
            viewModel.bill(viewModel.billId.get(), tableId, viewModel.price.get(), viewModel.priceEntity.get().getCanju(), qStr
                    , tStr, pStr, 1, viewModel.shouldPay.get(), tableName, free, "4", guid, payType, moLing, rounding);
        } else if (flag == 1) {
            viewModel.bill(viewModel.billId.get(), tableId, viewModel.price.get(), viewModel.priceEntity.get().getCanju(), qStr
                    , tStr, pStr, 1, viewModel.shouldPay.get(), tableName, free, "7", guid, payType, moLing, rounding);
        }
    }

    public void scanBillSuccess(String payType, String result, double money, final String memberId, String str) {
        if (str.contains("支付中")) {
            ConfirmDialog confirmDialog = new ConfirmDialog();
            confirmDialog.setTitle("支付中");
            confirmDialog.setMessage("用户正在支付中，是否确认已支付");
            confirmDialog.setOnDialogSure(new ConfirmDialog.OnDialogSure() {
                @Override
                public void confirm() {
                    scanBill(payType, result, money, memberId);
                }

                @Override
                public void cancel() {

                }
            });
            confirmDialog.show(getFragmentManager(), "");
        } else {
            scanBill(payType, result, money, memberId);
        }
    }

    public void weixinSuccess() {
        double result = viewModel.shouldPay.get();
        List<BillJson.BillJsonBase> t = new ArrayList<>();
        BillJson.BillJsonBase base = new BillJson.BillJsonBase();
        t.add(base);
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
        if (viewModel.elsePrice.get() > 0) {
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


        BillJson.Pays pays = new BillJson.Pays();
        List<BillJson.BillJsonBase> p = new ArrayList<>();
        p.add(base);

        BillJson.BillJsonBase pe = new BillJson.BillJsonBase();
        pe.setGuid(System.currentTimeMillis() + "");
        pe.setPice(result + "");
        pe.setPiceGuid("7");
        p.add(pe);
        pays.setQuanxian(p);
        String pStr = new Gson().toJson(pays);

        double free = viewModel.discount.get();
        Double moLing = 0.0;
        Double rounding = 0.0;
        if (viewModel.permissionRemission.get() > 0) {
            moLing = viewModel.permissionRemission.get();
        } else {
            rounding = -viewModel.permissionRemission.get();
        }

        if (flag == 4) {//快餐
            viewModel.bill(viewModel.billId.get(), tableId, viewModel.price.get(), viewModel.priceEntity.get().getCanju(), qStr
                    , tStr, pStr, 1, viewModel.shouldPay.get(), tableName, free, "4", guid, "", moLing, rounding);
        } else {
            viewModel.bill(viewModel.billId.get(), tableId, viewModel.price.get(), viewModel.priceEntity.get().getCanju(), qStr
                    , tStr, pStr, 1, viewModel.shouldPay.get(), tableName, free, "7", guid, "", moLing, rounding);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
