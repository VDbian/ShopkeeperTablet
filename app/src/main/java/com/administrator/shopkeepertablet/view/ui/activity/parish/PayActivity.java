package com.administrator.shopkeepertablet.view.ui.activity.parish;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.administrator.shopkeepertablet.AppConstant;
import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.databinding.ActivityPayBinding;
import com.administrator.shopkeepertablet.di.app.AppComponent;
import com.administrator.shopkeepertablet.di.parish.DaggerParishActivityComponent;
import com.administrator.shopkeepertablet.di.parish.ParishActivityModule;
import com.administrator.shopkeepertablet.model.entity.OrderFoodEntity;
import com.administrator.shopkeepertablet.model.entity.TableEntity;
import com.administrator.shopkeepertablet.model.entity.bean.EventPayBean;
import com.administrator.shopkeepertablet.model.entity.bean.EventTableBean;
import com.administrator.shopkeepertablet.utils.DataEvent;
import com.administrator.shopkeepertablet.view.ui.BaseActivity;
import com.administrator.shopkeepertablet.view.ui.adapter.OrderFoodAdapter;
import com.administrator.shopkeepertablet.view.widget.ConfirmDialog;
import com.administrator.shopkeepertablet.view.widget.CouponDialog;
import com.administrator.shopkeepertablet.view.widget.DiscountDialog;
import com.administrator.shopkeepertablet.view.widget.ElseDiscountsDialog;
import com.administrator.shopkeepertablet.view.widget.PermissionDiscountDialog;
import com.administrator.shopkeepertablet.view.widget.PermissionRemissionDialog;
import com.administrator.shopkeepertablet.view.widget.PopupWindowMember;
import com.administrator.shopkeepertablet.view.widget.RecyclerViewItemDecoration;
import com.administrator.shopkeepertablet.viewmodel.parish.PayViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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

    private List<OrderFoodEntity> mList = new ArrayList<>();


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
        binding.llMember.setVisibility(View.INVISIBLE);
//        binding.llDiscount.setVisibility(View.INVISIBLE);
//        binding.llRemission.setVisibility(View.INVISIBLE);

        OrderFoodAdapter adapter = new OrderFoodAdapter(this, mList);
        binding.rlvOrder.setAdapter(adapter);
        binding.rlvOrder.setLayoutManager(new LinearLayoutManager(this));
        binding.rlvOrder.addItemDecoration(new RecyclerViewItemDecoration(5));
        adapter.setOnItemClick(new OrderFoodAdapter.OnItemClick() {
            @Override
            public void onItemClick(OrderFoodEntity orderFoodEntity, int position) {
                DiscountDialog discountDialog = new DiscountDialog();
                discountDialog.setTitle(orderFoodEntity.getProductName());
                discountDialog.setOnConfirmClick(new DiscountDialog.OnConfirmClick() {
                    @Override
                    public void confirm(String discount) {

                    }
                });
                discountDialog.show(getFragmentManager(), "");
            }
        });

        binding.tvMember.setOnClickListener(this);
        binding.tvCoupon.setOnClickListener(this);
        binding.tvDiscount.setOnClickListener(this);
        binding.tvFree.setOnClickListener(this);
        binding.tvRemission.setOnClickListener(this);
        binding.tvElseDiscounts.setOnClickListener(this);

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
                showDialogDiscount();
                break;
            case R.id.tv_free:
                showDialogFree();
                break;
            case R.id.tv_remission:
                showDialogRemission();
                break;
            case R.id.tv_else_discounts:
                showDialogElseDiscount();
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }


    private void showPopMember() {
        PopupWindowMember popupWindowMember = new PopupWindowMember(this, viewModel);
        popupWindowMember.setOnCallBackListener(new PopupWindowMember.OnCallBackListener() {
            @Override
            public void confirm() {

            }
        });
        popupWindowMember.showPopupWindowUp();
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

    private void showDialogDiscount() {
        PermissionDiscountDialog permissionDiscountDialog = new PermissionDiscountDialog();
        permissionDiscountDialog.setViewModel(viewModel);
        permissionDiscountDialog.setOnConfirmClick(new PermissionDiscountDialog.OnConfirmClick() {
            @Override
            public void confirm() {

            }
        });
        permissionDiscountDialog.show(getFragmentManager(), "");
    }

    private void showDialogRemission() {
        PermissionRemissionDialog permissionRemissionDialog = new PermissionRemissionDialog();
        permissionRemissionDialog.setViewModel(viewModel);
        permissionRemissionDialog.setOnConfirmClick(new PermissionRemissionDialog.OnConfirmClick() {
            @Override
            public void confirm() {

            }
        });
        permissionRemissionDialog.show(getFragmentManager(), "");
    }

    private void showDialogFree() {
        ConfirmDialog confirmDialog = new ConfirmDialog();
        confirmDialog.setMessage("是否免单");
        confirmDialog.setOnDialogSure(new ConfirmDialog.OnDialogSure() {
            @Override
            public void confirm() {

            }

            @Override
            public void cancel() {

            }
        });
        confirmDialog.show(getFragmentManager(), "");
    }

    private void showDialogElseDiscount() {
        ElseDiscountsDialog elseDiscountsDialog = new ElseDiscountsDialog();
        elseDiscountsDialog.setViewModel(viewModel);
        elseDiscountsDialog.setOnConfirmClick(new ElseDiscountsDialog.OnConfirmClick() {
            @Override
            public void confirm() {

            }
        });
        elseDiscountsDialog.show(getFragmentManager(), "");
    }


    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onDataEvent(DataEvent event) {
        if (event.getMessageTag() == AppConstant.EVENT_PAY) {
            EventPayBean bean = (EventPayBean) event.getMessageData();
            mList = bean.getmList();
            viewModel.tableEntity.set(bean.getTableEntity());
            viewModel.roomName.set(bean.getRoomName());
            viewModel.time.set(viewModel.getTime(bean.getTableEntity().getKaiTime()));
            viewModel.tableList.set(bean.getTableEntityList());
            Double price = bean.getTableEntity().getPrice();
            if (bean.getTableEntityList() != null && bean.getTableEntityList().size() != 0) {
                for (TableEntity tableEntity : bean.getTableEntityList()) {
                    price += tableEntity.getPrice();
                }
            }
            viewModel.price.set(price);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
