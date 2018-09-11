package com.administrator.shopkeepertablet.view.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.administrator.shopkeepertablet.BR;
import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.databinding.ItemRlvAddBinding;
import com.administrator.shopkeepertablet.model.entity.bean.FoodAddBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Author CC
 * Time 2018/7/12
 */


public class OrderDishesAddAdapter extends RecyclerView.Adapter<OrderDishesAddAdapter.ItemViewHolder> {
    private Context context;
    //    private List<FoodTypeEntity> mList;
    private List<FoodAddBean> selectList = new ArrayList<>();
    protected OrderDishesAddAdapter.OnItemClick onItemClick;

    public OrderDishesAddAdapter(Context context, List<FoodAddBean> mList) {
        this.context = context;
        this.selectList = mList;
    }

    public void setOnItemClick(OrderDishesAddAdapter.OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(context).inflate(R.layout.item_rlv_add, null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,56);
        view.setLayoutParams(params);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        final FoodAddBean foodAddBean = selectList.get(position);
        holder.setBinding(BR.entity, foodAddBean);
        if (foodAddBean.isSelect()) {
            holder.binding.llMain.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.shape_choose_select));
        } else {
            holder.binding.llMain.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.shape_login_edit));
        }
        holder.binding.rlNumPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foodAddBean.setNum(foodAddBean.getNum() + 1);
                foodAddBean.setSelect(true);
                notifyDataSetChanged();
            }
        });
        holder.binding.rlNumReduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (foodAddBean.getNum() > 1) {
                    foodAddBean.setNum(foodAddBean.getNum() - 1);
                    foodAddBean.setSelect(true);
                    notifyDataSetChanged();
                }
            }
        });

        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick!=null){
                    onItemClick.onItemClick(foodAddBean,position,foodAddBean.isSelect());
                }
                foodAddBean.setSelect(!foodAddBean.isSelect());
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return selectList.size();
    }


    public interface OnItemClick {
        void onItemClick(FoodAddBean entity, int position,boolean select);
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        ItemRlvAddBinding binding;

        private ItemViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        private OrderDishesAddAdapter.ItemViewHolder setBinding(int variableId, Object object) {
            binding.setVariable(variableId, object);
            binding.executePendingBindings();
            return this;
        }
    }
}
