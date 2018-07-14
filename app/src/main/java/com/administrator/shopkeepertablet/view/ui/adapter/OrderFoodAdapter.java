package com.administrator.shopkeepertablet.view.ui.adapter;

import android.content.Context;

import com.administrator.shopkeepertablet.BR;
import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.model.entity.FoodEntity;
import com.administrator.shopkeepertablet.model.entity.OrderFoodEntity;
import com.administrator.shopkeepertablet.view.ui.adapter.base.BaseRecycleAdapter;
import com.administrator.shopkeepertablet.view.ui.adapter.base.ItemViewHolder;

import java.util.List;


/**
 * Description:
 * Author CC
 * Time 2018/6/14
 */
public class OrderFoodAdapter extends BaseRecycleAdapter<OrderFoodEntity> {

    public OrderFoodAdapter(Context context, List<OrderFoodEntity> datas) {
        super(context, R.layout.item_rlv_order_food, datas);
    }

    @Override
    public void convert(ItemViewHolder holder, final OrderFoodEntity foodEntity) {
        holder.setBinding(BR.entity, foodEntity);
    }
}
