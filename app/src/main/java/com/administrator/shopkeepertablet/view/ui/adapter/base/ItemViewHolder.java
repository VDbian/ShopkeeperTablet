package com.administrator.shopkeepertablet.view.ui.adapter.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Description:
 * Author CC
 * Time 2018/6/14
 */
public class ItemViewHolder extends RecyclerView.ViewHolder {

    private ViewDataBinding mBinding;
    private View item;

    public ItemViewHolder(View v) {
        super(v);
        this.item = v;
        mBinding = DataBindingUtil.bind(v);
    }

    public View getItem() {
        return item;
    }

    public static ItemViewHolder get(Context context, ViewGroup viewGroup, int layoutId) {
        View itemView = LayoutInflater.from(context).inflate(layoutId, viewGroup, false);
        return new ItemViewHolder(itemView);
    }

    public ItemViewHolder setBinding(int variableId , Object object){
        mBinding.setVariable(variableId , object);
        mBinding.executePendingBindings();
        return this;
    }

    public ViewDataBinding getmBinding() {
        return mBinding;
    }
}
