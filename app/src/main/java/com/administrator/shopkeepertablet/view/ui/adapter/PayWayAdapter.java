package com.administrator.shopkeepertablet.view.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.administrator.shopkeepertablet.BR;
import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.databinding.ItemRlvCardBinding;
import com.administrator.shopkeepertablet.databinding.ItemRlvPayBinding;
import com.administrator.shopkeepertablet.model.entity.CardEntity;
import com.administrator.shopkeepertablet.model.entity.PayMeEntity;

import java.util.ArrayList;
import java.util.List;


/**
 * Description:
 * Author CC
 * Time 2018/6/14
 */

public class PayWayAdapter extends RecyclerView.Adapter<PayWayAdapter.ItemViewHolder> {
    private Context context;
    private List<PayMeEntity> mList = new ArrayList<>();
    protected OnItemClick onItemClick;

    public PayWayAdapter(Context context, List<PayMeEntity> mList) {
        this.context = context;
        this.mList = mList;
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_rlv_pay, null));
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        final PayMeEntity entity = mList.get(position);
        holder.setBinding(BR.entity, entity);
        if (mList.get(position).isSelected()) {
            holder.binding.llMain.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.shape_card_select));
        } else {
            holder.binding.llMain.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.shape_pay_way));
        }
        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick != null) {
                    onItemClick.onItemClick(entity, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public interface OnItemClick {
        void onItemClick(PayMeEntity entity, int position);
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        ItemRlvPayBinding binding;

        private ItemViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        private ItemViewHolder setBinding(int variableId, Object object) {
            binding.setVariable(variableId, object);
            binding.executePendingBindings();
            return this;
        }
    }
}
