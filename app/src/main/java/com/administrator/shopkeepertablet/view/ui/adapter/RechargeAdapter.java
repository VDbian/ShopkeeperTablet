package com.administrator.shopkeepertablet.view.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.administrator.shopkeepertablet.BR;
import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.databinding.ItemRlvLineUpBinding;
import com.administrator.shopkeepertablet.databinding.ItemRlvRechargeBinding;
import com.administrator.shopkeepertablet.model.entity.LineUpEntity;
import com.administrator.shopkeepertablet.model.entity.RechargeEntity;

import java.util.ArrayList;
import java.util.List;


/**
 * Description:
 * Author CC
 * Time 2018/6/14
 */

public class RechargeAdapter extends RecyclerView.Adapter<RechargeAdapter.ItemViewHolder> {
    private Context context;
    private List<RechargeEntity> mList = new ArrayList<>();
    protected OnClick onItemClick;

    public RechargeAdapter(Context context, List<RechargeEntity> mList) {
        this.context = context;
        this.mList = mList;
    }

    public void setOnItemClick(OnClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_rlv_recharge, null));
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        final RechargeEntity entity = mList.get(position);
        holder.setBinding(BR.entity, entity);
        holder.binding.tvRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick!=null){
                    onItemClick.recharge(entity,position);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public interface OnClick {
        void recharge(RechargeEntity entity, int position);
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        ItemRlvRechargeBinding binding;

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
