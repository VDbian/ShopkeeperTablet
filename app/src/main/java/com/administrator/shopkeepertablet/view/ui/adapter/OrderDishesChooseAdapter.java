package com.administrator.shopkeepertablet.view.ui.adapter;

import android.content.Context;

import com.administrator.shopkeepertablet.BR;
import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.model.entity.ChooseBean;
import com.administrator.shopkeepertablet.model.entity.FoodEntity;
import com.administrator.shopkeepertablet.view.ui.adapter.base.BaseRecycleAdapter;
import com.administrator.shopkeepertablet.view.ui.adapter.base.ItemViewHolder;

import java.util.List;


/**
 * Description:
 * Author CC
 * Time 2018/6/14
 */
public class OrderDishesChooseAdapter extends BaseRecycleAdapter<ChooseBean> {

    public OrderDishesChooseAdapter(Context context, List<ChooseBean> datas) {
        super(context, R.layout.item_rlv_choose, datas);
    }

    @Override
    public void convert(ItemViewHolder holder, final ChooseBean chooseBean) {
        holder.setBinding(BR.entity, chooseBean);
    }
}
