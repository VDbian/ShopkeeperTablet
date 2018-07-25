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
import com.administrator.shopkeepertablet.databinding.ItemRlvReturnReasonBinding;
import com.administrator.shopkeepertablet.model.entity.ReturnReasonEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Author CC
 * Time 2018/7/12
 */


public class ReturnFoodReasonAdapter extends RecyclerView.Adapter<ReturnFoodReasonAdapter.ItemViewHolder> {
    private Context context;
    //    private List<FoodTypeEntity> mList;
    private List<ReturnReasonEntity> selectList = new ArrayList<>();
    protected ReturnFoodReasonAdapter.OnItemClick onItemClick;

    public ReturnFoodReasonAdapter(Context context, List<ReturnReasonEntity> mList) {
        this.context = context;
        this.selectList = mList;
    }

    public void setOnItemClick(ReturnFoodReasonAdapter.OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(context).inflate(R.layout.item_rlv_return_reason, null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(116,48);
        view.setLayoutParams(params);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        final ReturnReasonEntity chooseBean = selectList.get(position);
        holder.setBinding(BR.entity, chooseBean);
        if (selectList.get(position).isSelect()) {
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
                selectList.get(i).setSelect(true);
            } else {
                selectList.get(i).setSelect(false);
            }
        }
        notifyDataSetChanged();
    }


    public interface OnItemClick {
        void onItemClick(ReturnReasonEntity entity, int position);
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        ItemRlvReturnReasonBinding binding;

        private ItemViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        private ReturnFoodReasonAdapter.ItemViewHolder setBinding(int variableId, Object object) {
            binding.setVariable(variableId, object);
            binding.executePendingBindings();
            return this;
        }
    }
}
