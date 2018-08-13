package com.administrator.shopkeepertablet.view.ui.adapter;

import android.content.Context;

import com.administrator.shopkeepertablet.BR;
import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.model.entity.DiscountEntity;
import com.administrator.shopkeepertablet.model.entity.FoodEntity;
import com.administrator.shopkeepertablet.view.ui.adapter.base.BaseRecycleAdapter;
import com.administrator.shopkeepertablet.view.ui.adapter.base.ItemViewHolder;

import java.util.List;


/**
 * Description:
 * Author CC
 * Time 2018/6/14
 */
public class DiscountAdapter extends BaseRecycleAdapter<DiscountEntity> {

    public DiscountAdapter(Context context, List<DiscountEntity> datas) {
        super(context, R.layout.item_rlv_discount, datas);
    }

    @Override
    public void convert(ItemViewHolder holder, final DiscountEntity discountEntity) {
        holder.setBinding(BR.entity, discountEntity);
    }
}
