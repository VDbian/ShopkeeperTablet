package com.administrator.shopkeepertablet.view.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.administrator.shopkeepertablet.BR;
import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.databinding.ItemRlvOrderDishesOrderBinding;
import com.administrator.shopkeepertablet.model.entity.bean.CartBean;
import com.administrator.shopkeepertablet.model.entity.bean.FoodAddBean;
import com.administrator.shopkeepertablet.view.widget.OrderRemarkView;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Author CC
 * Time 2018/7/12
 */


public class OrderDishesCartAdapter extends RecyclerView.Adapter<OrderDishesCartAdapter.ItemViewHolder> {
    private Context context;
    //    private List<FoodTypeEntity> mList;
    private List<CartBean> cartBeanList = new ArrayList<>();
    protected OrderDishesCartAdapter.OnItemClick onItemClick;

    public OrderDishesCartAdapter(Context context, List<CartBean> mList) {
        this.context = context;
        this.cartBeanList = mList;
    }

    public void setOnItemClick(OrderDishesCartAdapter.OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rlv_order_dishes_order, null);
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(116,48);
//        view.setLayoutParams(params);
        return new ItemViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        final CartBean cartBean = cartBeanList.get(position);
        holder.setBinding(BR.entity, cartBean);
        holder.binding.llRemark.removeAllViews();
        if (Double.valueOf(cartBean.getGiveNum()) != 0) {
            View view = LayoutInflater.from(context).inflate(R.layout.view_order_dishes_give, null);
            ((TextView) view.findViewById(R.id.tv_num)).setText(cartBean.getGiveNum());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            params.setMargins(0, 8, 0, 0);
            view.setLayoutParams(params);
            holder.binding.llRemark.addView(view);
        }
        if (cartBean.getFoodAddBeanList().size() != 0) {
            for (FoodAddBean foodAddBean : cartBean.getFoodAddBeanList()) {
                OrderRemarkView view = new OrderRemarkView(context);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                params.setMargins(0, 8, 0, 0);
                view.setLayoutParams(params);
                view.setData(foodAddBean);
                holder.binding.llRemark.addView(view);
            }
        }


        if (TextUtils.isEmpty(cartBean.getUnit())) {
            String format = context.getResources().getString(R.string.num_prefix);
            holder.binding.tvNum.setText(String.format(format, String.valueOf(cartBean.getNum())));
        } else {
            holder.binding.tvNum.setText(cartBean.getNum() + cartBean.getUnit());
        }


        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick != null) {
                    onItemClick.onItemClick(cartBean, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartBeanList.size();
    }


    public interface OnItemClick {
        void onItemClick(CartBean entity, int position);
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        ItemRlvOrderDishesOrderBinding binding;

        private ItemViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        private OrderDishesCartAdapter.ItemViewHolder setBinding(int variableId, Object object) {
            binding.setVariable(variableId, object);
            binding.executePendingBindings();
            return this;
        }
    }
}
