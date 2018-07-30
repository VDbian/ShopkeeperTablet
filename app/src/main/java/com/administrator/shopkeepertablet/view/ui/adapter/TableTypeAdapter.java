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
import com.administrator.shopkeepertablet.databinding.ItemRlvTableBinding;
import com.administrator.shopkeepertablet.databinding.ItemTableTypeBinding;
import com.administrator.shopkeepertablet.model.entity.FoodTypeEntity;
import com.administrator.shopkeepertablet.model.entity.FoodTypeSelectEntity;
import com.administrator.shopkeepertablet.model.entity.TableType;

import java.util.ArrayList;
import java.util.List;


/**
 * Description:
 * Author CC
 * Time 2018/6/14
 */

public class TableTypeAdapter extends RecyclerView.Adapter<TableTypeAdapter.ItemViewHolder> {
    private Context context;
    //    private List<FoodTypeEntity> mList;
    private List<TableType> selectList = new ArrayList<>();
    protected OnItemClick onItemClick;

    public TableTypeAdapter(Context context, List<TableType> mList) {
        this.context = context;
        this.selectList = mList;
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_table_type, null));
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        final TableType tableType = selectList.get(position);
        holder.setBinding(BR.entity, tableType);
        if (selectList.get(position).isSelect()) {
            holder.binding.tvName.setTextColor(context.getResources().getColor(R.color.color23cac0));
            holder.binding.tvName.setBackgroundColor(context.getResources().getColor(R.color.colorf3f8f9));
        } else {
            holder.binding.tvName.setTextColor(context.getResources().getColor(R.color.color798795));
            holder.binding.tvName.setBackgroundColor(context.getResources().getColor(R.color.colorWhite));
        }
        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectClick(position);
                if (onItemClick != null) {
                    onItemClick.onItemClick(tableType, position);
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
        void onItemClick(TableType entity, int position);
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        ItemTableTypeBinding binding;

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
