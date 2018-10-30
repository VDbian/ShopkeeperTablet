package com.administrator.shopkeepertablet.view.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.databinding.ItemRlvOrderFoodBinding;
import com.administrator.shopkeepertablet.model.entity.OrderFoodEntity;

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
        if (entity.isSelect()){
//            holder.binding.rlMain.setBackground(context.getResources().getDrawable(R.drawable.shape_order_list_select_bg));
            holder.binding.rlMain.setBackgroundColor(context.getResources().getColor(R.color.colorb2b2b2));
        }else {
//            holder.binding.rlMain.setBackground(context.getResources().getDrawable(R.drawable.shape_order_list_bg));
            holder.binding.rlMain.setBackgroundColor(context.getResources().getColor(R.color.White));
        }
        holder.binding.llSeason.setVisibility(TextUtils.isEmpty(entity.getSeasonID()) ? View.GONE : View.VISIBLE);
        if (entity.getGiving() == 0) {
            holder.binding.rlGive.setVisibility(View.GONE);
        } else {
            holder.binding.rlGive.setVisibility(View.VISIBLE);
        }

        if (entity.getWeight() <= 1) {
//            String format = context.getResources().getString(R.string.num_prefix);
//            holder.binding.tvNum.setText(String.format(format, String.valueOf(cartBean.getNum())));
            holder.binding.tvNum.setText(entity.getCount() + "份");
        } else {
            holder.binding.tvNum.setText(entity.getWeight() + entity.getUnit() + "(1份)");
        }

        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelect(entity,position);
                if (onItemClick != null) {
                    onItemClick.onItemClick(entity, position);
                }
            }
        });
    }

    private void setSelect(OrderFoodEntity orderFoodEntity,int position){
        if (orderFoodEntity.isSelect()){
            mList.get(position).setSelect(false);
        }else {
            for (OrderFoodEntity entity:mList){
                if (orderFoodEntity.getId().equals(entity.getId())){
                    entity.setSelect(true);
                }else {
                    entity.setSelect(false);
                }
            }
        }
        notifyDataSetChanged();
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
