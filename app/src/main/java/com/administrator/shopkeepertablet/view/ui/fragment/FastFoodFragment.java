package com.administrator.shopkeepertablet.view.ui.fragment;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.databinding.FragmentFastFoodBinding;
import com.administrator.shopkeepertablet.di.app.AppComponent;
import com.administrator.shopkeepertablet.di.fast.DaggerFastComponent;
import com.administrator.shopkeepertablet.di.fast.FastModule;
import com.administrator.shopkeepertablet.model.entity.FoodEntity;
import com.administrator.shopkeepertablet.model.entity.FoodTypeEntity;
import com.administrator.shopkeepertablet.model.entity.FoodTypeSelectEntity;
import com.administrator.shopkeepertablet.model.entity.KouWeiEntity;
import com.administrator.shopkeepertablet.model.entity.bean.CartBean;
import com.administrator.shopkeepertablet.model.entity.bean.FoodAddBean;
import com.administrator.shopkeepertablet.utils.MToast;
import com.administrator.shopkeepertablet.view.ui.BaseFragment;
import com.administrator.shopkeepertablet.view.ui.adapter.FoodTypeAdapter;
import com.administrator.shopkeepertablet.view.ui.adapter.OrderDishesCartAdapter;
import com.administrator.shopkeepertablet.view.ui.adapter.OrderDishesVarietyAdapter;
import com.administrator.shopkeepertablet.view.ui.adapter.base.AdapterOnItemClick;
import com.administrator.shopkeepertablet.view.widget.PopupWindowAllKouwei;
import com.administrator.shopkeepertablet.view.widget.PopupWindowOrderDishesChange;
import com.administrator.shopkeepertablet.view.widget.PopupWindowOrderDishesChoose;
import com.administrator.shopkeepertablet.view.widget.RecyclerViewItemDecoration;
import com.administrator.shopkeepertablet.viewmodel.FastViewModel;

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

    private void initView(){
        adapter = new OrderDishesVarietyAdapter(getActivity(), mList);
        binding.rlvVariety.setAdapter(adapter);
        binding.rlvVariety.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        binding.rlvVariety.addItemDecoration(new RecyclerViewItemDecoration(5));
        adapter.setOnItemClick(new AdapterOnItemClick<FoodEntity>() {
            @Override
            public void onItemClick(FoodEntity foodEntity, int position) {
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
            }
        });

        Drawable drawable1 = getResources().getDrawable(R.mipmap.search);
        drawable1.setBounds(25, 0, 45, 20);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
        binding.etSearch.setCompoundDrawables(drawable1, null, null, null);//只放左边

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
                        cartBeanList.remove(entity);
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
                } else if (binding.rlvOrder.getVisibility() == View.VISIBLE) {
                    viewModel.getAllKouwei();
                }
                break;
        }
    }
}
