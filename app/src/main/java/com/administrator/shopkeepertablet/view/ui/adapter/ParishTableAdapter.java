package com.administrator.shopkeepertablet.view.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.BR;
import com.administrator.shopkeepertablet.databinding.ItemRlvTableBinding;
import com.administrator.shopkeepertablet.model.entity.TableEntity;
import com.administrator.shopkeepertablet.utils.MLog;

import java.util.List;


/**
 * Description:
 * Author CC
 * Time 2018/6/14
 */

public class ParishTableAdapter extends RecyclerView.Adapter<ParishTableAdapter.ItemViewHolder> {
    private Context context;
    private List<TableEntity> mList;
    protected OnItemClick onItemClick;

    public ParishTableAdapter(Context context, List<TableEntity> mList) {
        this.context = context;
        this.mList = mList;
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_rlv_table, null));
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, final int position) {
        final TableEntity tableEntity = mList.get(position);
        holder.setBinding(BR.entity, tableEntity);
        switch (tableEntity.getIsOpen()) {
            case "0":
                holder.binding.rlMain.setBackgroundResource(R.drawable.shape_table_leisure_bg);
                holder.binding.tvLeisure.setVisibility(View.VISIBLE);
                holder.binding.llNoLeisure.setVisibility(View.GONE);
                break;
            case "1":
                holder.binding.rlMain.setBackgroundResource(R.drawable.shape_table_open_bg);
                holder.binding.tvLeisure.setVisibility(View.GONE);
                holder.binding.llNoLeisure.setVisibility(View.VISIBLE);
                break;
            default:
                if (tableEntity.isSelect()){
                    holder.binding.rlMain.setBackgroundResource(R.drawable.shape_table_order_select_bg);
                }else {
                    holder.binding.rlMain.setBackgroundResource(R.drawable.shape_table_order_bg);
                }
                holder.binding.tvLeisure.setVisibility(View.GONE);
                holder.binding.llNoLeisure.setVisibility(View.VISIBLE);
                break;
        }

        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick != null) {
                    onItemClick.onItemClick(tableEntity, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public interface OnItemClick {
        void onItemClick(TableEntity entity, int position);
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        ItemRlvTableBinding binding;

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
