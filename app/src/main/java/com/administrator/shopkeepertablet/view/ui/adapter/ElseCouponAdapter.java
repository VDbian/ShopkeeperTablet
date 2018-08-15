package com.administrator.shopkeepertablet.view.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.administrator.shopkeepertablet.BR;
import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.databinding.ItemRlvElseCouponBinding;
import com.administrator.shopkeepertablet.model.entity.CardEntity;
import com.administrator.shopkeepertablet.model.entity.DiscountEntity;
import com.administrator.shopkeepertablet.model.entity.ElseCouponEntity;
import com.administrator.shopkeepertablet.view.ui.adapter.base.BaseRecycleAdapter;
import com.administrator.shopkeepertablet.view.ui.adapter.base.ItemViewHolder;

import java.util.ArrayList;
import java.util.List;


/**
 * Description:
 * Author CC
 * Time 2018/6/14
 */
public class ElseCouponAdapter extends RecyclerView.Adapter<ElseCouponAdapter.ItemViewHolder> {

    private Context context;
    private List<ElseCouponEntity> mList = new ArrayList<>();
    protected OnItemClick onItemClick;

    public ElseCouponAdapter(Context context, List<ElseCouponEntity> mList) {
        this.context = context;
        this.mList = mList;
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_rlv_else_coupon, null));
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        final ElseCouponEntity entity = mList.get(position);
        holder.setBinding(BR.entity, entity);
        if (mList.get(position).isSelect()) {
            holder.binding.tvName.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.shape_choose_select));
        } else {
            holder.binding.tvName.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.shape_choose_normal));
        }
        holder.binding.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mList.get(position).setSelect(!entity.isSelect());
                notifyDataSetChanged();
//                if (onItemClick != null) {
//                    onItemClick.onItemClick(entity, position);
//                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

//    private void selectClick(int pos) {
//        for (int i = 0; i < mList.size(); i++) {
//            if (i == pos) {
//                mList.get(i).setSelect(true);
//            } else {
//                mList.get(i).setSelect(false);
//            }
//        }
//        notifyDataSetChanged();
//    }


    public interface OnItemClick {
        void onItemClick(ElseCouponEntity entity, int position);
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        ItemRlvElseCouponBinding binding;

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