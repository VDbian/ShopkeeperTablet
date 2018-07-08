package com.administrator.shopkeepertablet.view.ui.activity.parish;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;

import com.administrator.shopkeepertablet.AppConstant;
import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.databinding.ActivityBeginOrderDishesBinding;
import com.administrator.shopkeepertablet.di.app.AppComponent;
import com.administrator.shopkeepertablet.di.parish.DaggerParishActivityComponent;
import com.administrator.shopkeepertablet.di.parish.ParishActivityModule;
import com.administrator.shopkeepertablet.model.entity.EventOrderDishesEntity;
import com.administrator.shopkeepertablet.model.entity.FoodEntity;
import com.administrator.shopkeepertablet.utils.DataEvent;
import com.administrator.shopkeepertablet.utils.DateUtils;
import com.administrator.shopkeepertablet.view.ui.BaseActivity;
import com.administrator.shopkeepertablet.view.ui.adapter.OrderDishesVarietyAdapter;
import com.administrator.shopkeepertablet.view.ui.adapter.base.AdapterOnItemClick;
import com.administrator.shopkeepertablet.view.ui.fragment.ParishFoodFragment;
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
    private List<FoodEntity> mList = new ArrayList<>();



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
    }

    private void initView(){
        adapter = new OrderDishesVarietyAdapter(this,mList);
        binding.rlvVariety.setAdapter(adapter);
        binding.rlvVariety.setLayoutManager(new GridLayoutManager(this, 5));
        binding.rlvVariety.addItemDecoration(new RecyclerViewItemDecoration(5));
        adapter.setOnItemClick(new AdapterOnItemClick<FoodEntity>() {
            @Override
            public void onItemClick(FoodEntity foodEntity, int position) {

            }
        });
//        viewModel.getFoodList();
        viewModel.getFoodType();
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onDataEvent(DataEvent event) {
        if (event.getMessageTag() == AppConstant.EVENT_ORDER_DISHES) {
            EventOrderDishesEntity eventOrderDishesEntity = (EventOrderDishesEntity) event.getMessageData();
            viewModel.table.set(eventOrderDishesEntity.getTableName());
            viewModel.room.set(eventOrderDishesEntity.getRoomName());
            viewModel.people.set(eventOrderDishesEntity.getPeopleNum());
            viewModel.time.set(DateUtils.friendly_time(DateUtils.stringToDate(eventOrderDishesEntity.getTime())));
        }
    }


    public void refreshVariety(List<FoodEntity> foodEntities){
        mList.clear();
        mList.addAll(foodEntities);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
