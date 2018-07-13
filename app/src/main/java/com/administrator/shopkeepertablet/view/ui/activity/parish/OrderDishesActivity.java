package com.administrator.shopkeepertablet.view.ui.activity.parish;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;

import com.administrator.shopkeepertablet.AppConstant;
import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.databinding.ActivityBeginOrderDishesBinding;
import com.administrator.shopkeepertablet.di.app.AppComponent;
import com.administrator.shopkeepertablet.di.parish.DaggerParishActivityComponent;
import com.administrator.shopkeepertablet.di.parish.ParishActivityModule;
import com.administrator.shopkeepertablet.model.entity.EventOrderDishesEntity;
import com.administrator.shopkeepertablet.model.entity.FoodEntity;
import com.administrator.shopkeepertablet.model.entity.FoodTypeEntity;
import com.administrator.shopkeepertablet.model.entity.FoodTypeSelectEntity;
import com.administrator.shopkeepertablet.model.entity.bean.CartBean;
import com.administrator.shopkeepertablet.model.entity.bean.FoodAddBean;
import com.administrator.shopkeepertablet.utils.DataEvent;
import com.administrator.shopkeepertablet.utils.DateUtils;
import com.administrator.shopkeepertablet.view.ui.BaseActivity;
import com.administrator.shopkeepertablet.view.ui.adapter.FoodTypeAdapter;
import com.administrator.shopkeepertablet.view.ui.adapter.OrderDishesCartAdapter;
import com.administrator.shopkeepertablet.view.ui.adapter.OrderDishesVarietyAdapter;
import com.administrator.shopkeepertablet.view.ui.adapter.base.AdapterOnItemClick;
import com.administrator.shopkeepertablet.view.ui.fragment.ParishFoodFragment;
import com.administrator.shopkeepertablet.view.widget.PopupWindowOrderDishesChoose;
import com.administrator.shopkeepertablet.view.widget.RecyclerViewItemDecoration;
import com.administrator.shopkeepertablet.viewmodel.parish.OrderDishesViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.administrator.shopkeepertablet.R.*;

/**
 * Description:
 * Author CC
 * Time 2018/6/30
 */


public class OrderDishesActivity extends BaseActivity {
    ActivityBeginOrderDishesBinding binding;
    @Inject
    OrderDishesViewModel viewModel;
    private OrderDishesVarietyAdapter adapter;
    private FoodTypeAdapter foodTypeAdapter;
    private List<FoodEntity> mList = new ArrayList<>();
    private List<FoodTypeSelectEntity> foodTypeEntityList = new ArrayList<>();
    private OrderDishesCartAdapter cartAdapter;
    private List<CartBean> cartBeanList = new ArrayList<>();


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
        binding = DataBindingUtil.setContentView(this, R.layout.activity_begin_order_dishes);
        binding.setViemModel(viewModel);
        EventBus.getDefault().register(this);
        initView();
        viewModel.getFoodList();
        viewModel.getFoodType();
    }

    private void initView() {
        adapter = new OrderDishesVarietyAdapter(this, mList);
        binding.rlvVariety.setAdapter(adapter);
        binding.rlvVariety.setLayoutManager(new GridLayoutManager(this, 4));
        binding.rlvVariety.addItemDecoration(new RecyclerViewItemDecoration(5));
        adapter.setOnItemClick(new AdapterOnItemClick<FoodEntity>() {
            @Override
            public void onItemClick(FoodEntity foodEntity, int position) {
                final PopupWindowOrderDishesChoose OrderDishesChoose = new PopupWindowOrderDishesChoose(OrderDishesActivity.this, foodEntity);
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

        foodTypeAdapter = new FoodTypeAdapter(this, foodTypeEntityList);
        binding.rlvFoodType.setAdapter(foodTypeAdapter);
        binding.rlvFoodType.setLayoutManager(new LinearLayoutManager(this));
        foodTypeAdapter.setOnItemClick(new FoodTypeAdapter.OnItemClick() {
            @Override
            public void onItemClick(FoodTypeEntity entity, int position) {
                mList.clear();
                mList.addAll(entity.getFoodEntityList());
                adapter.notifyDataSetChanged();
            }
        });

        cartAdapter = new OrderDishesCartAdapter(this, cartBeanList);
        binding.rlvOrder.setAdapter(cartAdapter);
        binding.rlvOrder.setLayoutManager(new LinearLayoutManager(this));
        cartAdapter.setOnItemClick(new OrderDishesCartAdapter.OnItemClick() {
            @Override
            public void onItemClick(CartBean entity, int position) {

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


    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onDataEvent(DataEvent event) {
        if (event.getMessageTag() == AppConstant.EVENT_ORDER_DISHES) {
            EventOrderDishesEntity eventOrderDishesEntity = (EventOrderDishesEntity) event.getMessageData();
            viewModel.table.set(eventOrderDishesEntity.getTableName());
            viewModel.room.set(eventOrderDishesEntity.getRoomName());
            viewModel.people.set(eventOrderDishesEntity.getPeopleNum());
            viewModel.time.set(DateUtils.friendly_time(DateUtils.stringToDate(eventOrderDishesEntity.getTime())));
        }
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
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
