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
import com.administrator.shopkeepertablet.databinding.ItemRlvLineUpBinding;
import com.administrator.shopkeepertablet.model.entity.FoodTypeEntity;
import com.administrator.shopkeepertablet.model.entity.FoodTypeSelectEntity;
import com.administrator.shopkeepertablet.model.entity.LineUpEntity;

import java.util.ArrayList;
import java.util.List;


/**
 * Description:
 * Author CC
 * Time 2018/6/14
 */

public class LineUpAdapter extends RecyclerView.Adapter<LineUpAdapter.ItemViewHolder> {
    private Context context;
    //    private List<FoodTypeEntity> mList;
    private List<LineUpEntity> mList = new ArrayList<>();
    protected OnClick onItemClick;

    public LineUpAdapter(Context context, List<LineUpEntity> mList) {
        this.context = context;
        this.mList = mList;
    }

    public void setOnItemClick(OnClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_rlv_line_up, null));
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        final LineUpEntity entity = mList.get(position);
        holder.setBinding(BR.entity, entity);
        holder.binding.tvSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick!=null){
                    onItemClick.speak(entity,position);
                }
            }
        });
        holder.binding.tvBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick!=null){
                    onItemClick.bind(entity,position);
                }
            }
        });
        holder.binding.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick!=null){
                    onItemClick.delete(entity,position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public interface OnClick {
        void speak(LineUpEntity entity,int position);

        void bind(LineUpEntity entity,int position);

        void delete(LineUpEntity entity,int position);
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        ItemRlvLineUpBinding binding;

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
