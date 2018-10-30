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
import com.administrator.shopkeepertablet.databinding.ItemRlvChooseBinding;
import com.administrator.shopkeepertablet.databinding.ItemRlvProductKouweiBinding;
import com.administrator.shopkeepertablet.model.entity.KouWeiEntity;
import com.administrator.shopkeepertablet.model.entity.ProductKouWeiEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Author CC
 * Time 2018/7/12
 */


public class ProductKouWeiAdapter extends RecyclerView.Adapter<ProductKouWeiAdapter.ItemViewHolder> {
    private Context context;
    private List<ProductKouWeiEntity> selectList = new ArrayList<>();
    protected ProductKouWeiAdapter.OnItemClick onItemClick;

    public ProductKouWeiAdapter(Context context, List<ProductKouWeiEntity> mList) {
        this.context = context;
        this.selectList = mList;
    }

    public void setOnItemClick(ProductKouWeiAdapter.OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(context).inflate(R.layout.item_rlv_product_kouwei, null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(116,48);
        view.setLayoutParams(params);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        final ProductKouWeiEntity chooseBean = selectList.get(position);
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
//                selectClick(position);
                chooseBean.setSelect(!chooseBean.isSelect());
                notifyDataSetChanged();
                if (onItemClick != null) {
                    onItemClick.onItemClick(position);
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
        void onItemClick(int position);
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        ItemRlvProductKouweiBinding binding;

        private ItemViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        private ProductKouWeiAdapter.ItemViewHolder setBinding(int variableId, Object object) {
            binding.setVariable(variableId, object);
            binding.executePendingBindings();
            return this;
        }
    }
}
