package com.administrator.shopkeepertablet.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.databinding.TabViewBinding;


/**
 * Description:
 * Author CC
 * Time 2018/6/14
 */
public class TabView extends LinearLayout {

    protected Context mContext;
    protected TabViewBinding viewBinding;
    private String name;
    private Drawable iconDrawable;
    private String news;

    public TabView(Context context) {
        this(context, null);
    }

    public TabView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public TabView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        if (attrs != null) {
            TypedArray ta = getResources().obtainAttributes(attrs, R.styleable.TabView);
            name = ta.getString(R.styleable.TabView_tab_name);
            iconDrawable = ta.getDrawable(R.styleable.TabView_tab_icon);
            news = ta.getString(R.styleable.TabView_tab_news);
            ta.recycle();
        }
        initView();
    }

    private void initView() {
        viewBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.tab_view, this, true);
        viewBinding.tabNameView.setText(name);
//        viewBinding.tabNameView.setTextColor(R.drawable.selector_tab_text);
        viewBinding.tabIconView.setImageDrawable(iconDrawable);
        viewBinding.tabNewsView.setText(news);
        if (TextUtils.isEmpty(news)){
            viewBinding.tabNewsView.setVisibility(GONE);
        }
    }

    public void setViewSelect(boolean  isselect) {
        viewBinding.tabNameView.setSelected(isselect);
        viewBinding.tabIconView.setSelected(isselect);
        viewBinding.tabNameView.setTextColor(isselect?getResources().getColor(R.color.colorWhite):getResources().getColor(R.color.color798795));
//        if(isselect){
//            viewBinding.tabNameView.setTextColor(getResources().getColor(R.color.colorWhite));
//        }
    }
}
