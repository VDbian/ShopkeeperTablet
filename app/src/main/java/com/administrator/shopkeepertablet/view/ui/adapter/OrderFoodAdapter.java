package com.administrator.shopkeepertablet.view.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.administrator.shopkeepertablet.BR;
import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.databinding.ItemFoodTypeBinding;
import com.administrator.shopkeepertablet.databinding.ItemRlvAddBinding;
import com.administrator.shopkeepertablet.databinding.ItemRlvOrderFoodBinding;
import com.administrator.shopkeepertablet.model.entity.FoodTypeEntity;
import com.administrator.shopkeepertablet.model.entity.OrderFoodEntity;
import com.administrator.shopkeepertablet.model.entity.bean.FoodAddBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Author CC
 * Time 2018/7/12
 */


public class OrderFoodAdapter extends RecyclerView.Adapter<OrderFoodAdapter.ItemViewHolder> {
    private Context context;
    private List<OrderFoodEntity> mList;
    protected OrderFoodAdapter.OnItemClick onItemClick;

    public OrderFoodAdapter(Context context, List<OrderFoodEntity> mList) {
        this.context = context;
        this.mList = mList;
    }

    public void setOnItemClick(OrderFoodAdapter.OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rlv_order_food, null);
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 56);
//        view.setLayoutParams(params);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        OrderFoodEntity entity = mList.get(position);
        holder.binding.setEntity(entity);
        holder.binding.llSeason.setVisibility(TextUtils.isEmpty(entity.getSeasonID()) ? View.GONE : View.VISIBLE);
        if (entity.getGiving()==0){
            holder.binding.rlGive.setVisibility(View.GONE);
        }else {
            holder.binding.rlGive.setVisibility(View.VISIBLE);
        }

        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick != null) {
                    onItemClick.onItemClick(entity,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public interface OnItemClick {
        void onItemClick(OrderFoodEntity orderFoodEntity, int position);
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        ItemRlvOrderFoodBinding binding;

        private ItemViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        private OrderFoodAdapter.ItemViewHolder setBinding(int variableId, Object object) {
            binding.setVariable(variableId, object);
            binding.executePendingBindings();
            return this;
        }
    }
}
