package com.administrator.shopkeepertablet.view.widget;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.administrator.shopkeepertablet.BR;
import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.databinding.ViewOrderDishesRemarkBinding;
import com.administrator.shopkeepertablet.model.entity.bean.FoodAddBean;



/**
 * Description:
 * Author zhengkewen
 * Time 2018/3/19
 */
public class OrderRemarkView extends LinearLayout {

    protected Context mContext;
    protected ViewOrderDishesRemarkBinding viewBinding;
    private FoodAddBean entity;

    public OrderRemarkView(Context context) {
        this(context, null);
    }

    public OrderRemarkView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OrderRemarkView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public OrderRemarkView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
    }

    private void initView() {
        viewBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.view_order_dishes_remark, this, true);
    }

    public void setData(FoodAddBean entity) {
        this.entity = entity;
        viewBinding.setVariable(BR.entity, this.entity);
    }
}
