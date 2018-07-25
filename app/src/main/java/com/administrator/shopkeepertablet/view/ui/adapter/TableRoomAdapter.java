package com.administrator.shopkeepertablet.view.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.administrator.shopkeepertablet.BR;
import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.databinding.ItemTableRoomBinding;
import com.administrator.shopkeepertablet.model.entity.RoomEntity;

import java.util.ArrayList;
import java.util.List;


/**
 * Description:
 * Author CC
 * Time 2018/6/14
 */

public class TableRoomAdapter extends RecyclerView.Adapter<TableRoomAdapter.ItemViewHolder> {
    private Context context;
    //    private List<FoodTypeEntity> mList;
    private List<RoomEntity> selectList = new ArrayList<>();
    protected OnItemClick onItemClick;

    public TableRoomAdapter(Context context, List<RoomEntity> mList) {
        this.context = context;
        this.selectList = mList;
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_table_room, null));
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        final RoomEntity roomEntity = selectList.get(position);
        holder.setBinding(BR.entity, roomEntity);
        if (selectList.get(position).isSelect()) {
            holder.binding.tvName.setTextColor(context.getResources().getColor(R.color.colorWhite));
            holder.binding.tvName.setBackgroundColor(context.getResources().getColor(R.color.color23cac0));
        } else {
            holder.binding.tvName.setTextColor(context.getResources().getColor(R.color.color666666));
            holder.binding.tvName.setBackgroundColor(context.getResources().getColor(R.color.colord2d6dc));
        }
        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectClick(position);
                if (onItemClick != null) {
                    onItemClick.onItemClick(roomEntity, position);
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
        void onItemClick(RoomEntity entity, int position);
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        ItemTableRoomBinding binding;

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
