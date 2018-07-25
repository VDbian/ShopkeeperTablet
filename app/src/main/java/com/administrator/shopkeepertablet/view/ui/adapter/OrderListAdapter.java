package com.administrator.shopkeepertablet.view.ui.adapter;

import android.content.Context;

import com.administrator.shopkeepertablet.BR;
import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.model.entity.FoodEntity;
import com.administrator.shopkeepertablet.model.entity.OrderEntity;
import com.administrator.shopkeepertablet.view.ui.adapter.base.BaseRecycleAdapter;
import com.administrator.shopkeepertablet.view.ui.adapter.base.ItemViewHolder;

import java.util.List;


/**
 * Description:
 * Author CC
 * Time 2018/6/14
 */
public class OrderListAdapter extends BaseRecycleAdapter<OrderEntity> {

    public OrderListAdapter(Context context, List<OrderEntity> datas) {
        super(context, R.layout.item_rlv_order, datas);
    }

    @Override
    public void convert(ItemViewHolder holder, final OrderEntity orderEntity) {
        holder.setBinding(BR.entity, orderEntity);
    }
}
