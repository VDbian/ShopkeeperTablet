package com.administrator.shopkeepertablet.view.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.administrator.shopkeepertablet.BR;
import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.databinding.ItemFoodTypeBinding;
import com.administrator.shopkeepertablet.databinding.ItemRlvMessageBinding;
import com.administrator.shopkeepertablet.model.entity.FoodTypeEntity;
import com.administrator.shopkeepertablet.model.entity.FoodTypeSelectEntity;
import com.administrator.shopkeepertablet.model.entity.OrderEntity;

import java.util.ArrayList;
import java.util.List;


/**
 * Description:
 * Author CC
 * Time 2018/6/14
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ItemViewHolder> {
    private Context context;
    private List<OrderEntity> mList = new ArrayList<>();
    protected OnItemClick onItemClick;

    public MessageAdapter(Context context, List<OrderEntity> mList) {
        this.context = context;
        this.mList = mList;
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_rlv_message, null));
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        final OrderEntity orderEntity = mList.get(position);
        holder.setBinding(BR.entity, orderEntity);
        if (mList.get(position).isSelect()) {
            holder.binding.rlMain.setBackgroundColor(context.getResources().getColor(R.color.colorf3f8f9));
        } else {
            holder.binding.rlMain.setBackgroundColor(context.getResources().getColor(R.color.colorffffff));
        }
        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectClick(position);
                if (onItemClick != null) {
                    onItemClick.onItemClick(orderEntity, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    private void selectClick(int pos) {
        for (int i = 0; i < mList.size(); i++) {
            if (i == pos) {
                mList.get(i).setSelect(true);
            } else {
                mList.get(i).setSelect(false);
            }
        }
        notifyDataSetChanged();
    }


    public interface OnItemClick {
        void onItemClick(OrderEntity entity, int position);
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        ItemRlvMessageBinding binding;

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
