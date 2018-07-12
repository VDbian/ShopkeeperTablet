package com.administrator.shopkeepertablet.view.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.administrator.shopkeepertablet.BR;
import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.databinding.DilogOrderDishesBinding;
import com.administrator.shopkeepertablet.databinding.ItemFoodTypeBinding;
import com.administrator.shopkeepertablet.databinding.ItemRlvChooseBinding;
import com.administrator.shopkeepertablet.model.entity.ChooseBean;
import com.administrator.shopkeepertablet.model.entity.FoodTypeEntity;
import com.administrator.shopkeepertablet.model.entity.FoodTypeSelectEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Author CC
 * Time 2018/7/12
 */


public class OrderDishesChooseAdapter extends RecyclerView.Adapter<OrderDishesChooseAdapter.ItemViewHolder> {
    private Context context;
    //    private List<FoodTypeEntity> mList;
    private List<ChooseBean> selectList = new ArrayList<>();
    protected OrderDishesChooseAdapter.OnItemClick onItemClick;

    public OrderDishesChooseAdapter(Context context, List<ChooseBean> mList) {
        this.context = context;
        this.selectList = mList;
    }

    public void setOnItemClick(OrderDishesChooseAdapter.OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(context).inflate(R.layout.item_rlv_choose, null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(116,48);
        view.setLayoutParams(params);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        final ChooseBean chooseBean = selectList.get(position);
        holder.setBinding(BR.entity, chooseBean);
        if (selectList.get(position).isChoose()) {
            holder.binding.tvName.setTextColor(context.getResources().getColor(R.color.colorffb001));
            holder.binding.tvName.setBackgroundDrawable(context.getResources().getDrawable( R.drawable.shape_choose_select));
        } else {
            holder.binding.tvName.setTextColor(context.getResources().getColor(R.color.color666666));
            holder.binding.tvName.setBackgroundDrawable(context.getResources().getDrawable( R.drawable.shape_choose_normal));
        }
        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectClick(position);
                if (onItemClick != null) {
                    onItemClick.onItemClick(chooseBean, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return selectList.size();
    }

    private void selectClick(int pos) {
        for (int i = 0; i < selectList.size(); i++) {
            if (i == pos) {
                selectList.get(i).setChoose(true);
            } else {
                selectList.get(i).setChoose(false);
            }
        }
        notifyDataSetChanged();
    }


    public interface OnItemClick {
        void onItemClick(ChooseBean entity, int position);
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        ItemRlvChooseBinding binding;

        private ItemViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        private OrderDishesChooseAdapter.ItemViewHolder setBinding(int variableId, Object object) {
            binding.setVariable(variableId, object);
            binding.executePendingBindings();
            return this;
        }
    }
}
