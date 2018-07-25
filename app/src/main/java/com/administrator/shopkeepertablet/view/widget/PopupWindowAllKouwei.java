package com.administrator.shopkeepertablet.view.widget;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.databinding.DialogAllKouweiBinding;
import com.administrator.shopkeepertablet.model.entity.KouWeiEntity;
import com.administrator.shopkeepertablet.model.entity.bean.ChooseBean;
import com.administrator.shopkeepertablet.view.ui.adapter.OrderDishesChooseAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Description:
 * Author CC
 * Time 2018/7/7
 */

public class PopupWindowAllKouwei extends PopupWindow {

    private Context context;
    private DisplayMetrics metrics;
    private DialogAllKouweiBinding binding;
    private List<KouWeiEntity> mList;
    private List<ChooseBean> chooseBeanList = new ArrayList<>();
    private KouWeiEntity kouWeiEntity;
    private String kouweiRemark;

    private OnCallBackListener onCallBackListener;

    public PopupWindowAllKouwei(Context context, List<KouWeiEntity> mList, KouWeiEntity kouWeiEntity, String kouweiRemark) {
        this.context = context;
        this.mList = mList;
        this.kouWeiEntity = kouWeiEntity;
        this.kouweiRemark = kouweiRemark;
        initPopupWindow();
    }

    private void initPopupWindow() {
        //使用view来引入布局
        binding = DataBindingUtil.inflate(
                LayoutInflater.from(context), R.layout.dialog_all_kouwei, null, false);
//        setContentView((Activity) context, R.layout.popupwindow_begin_table)

        initView();
        int width = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int height = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        binding.getRoot().measure(width, height);
//        view.getMeasuredWidth(); // 获取宽度
        int viewWidth = binding.getRoot().getMeasuredWidth();
        int viewHeight = binding.getRoot().getMeasuredHeight(); // 获取高度

        //获取屏幕的高度与宽度
        int w = getDisplayMetrics(context).widthPixels;
        int h = getDisplayMetrics(context).heightPixels;
        // 设置SelectPicPopupWindow的View
        this.setContentView(binding.getRoot());
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(380);
        // 设置SelectPicPopupWindow弹出窗体的高
//        if (viewHeight > h / 2) {
//            this.setHeight(h / 2);
//        } else {
        this.setHeight(581);
//        }
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(false);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(00000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
//        this.setBackgroundDrawable(dw);
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });

    }

    private void initView() {
        if (mList != null) {
            for (KouWeiEntity entity : mList) {
                if (kouWeiEntity != null && kouWeiEntity.getGuId().equals(entity.getGuId())) {
                    ChooseBean chooseKouwei = new ChooseBean(true, entity.getName());
                    chooseBeanList.add(chooseKouwei);
                } else {
                    ChooseBean chooseKouwei = new ChooseBean(false, entity.getName());
                    chooseBeanList.add(chooseKouwei);
                }
            }
        }

        if (chooseBeanList.size() > 0) {
            final OrderDishesChooseAdapter adapterKouwei = new OrderDishesChooseAdapter(context, chooseBeanList);
            binding.rlvKouwei.setAdapter(adapterKouwei);
            binding.rlvKouwei.setLayoutManager(new GridLayoutManager(context, 3));
            binding.rlvKouwei.addItemDecoration(new RecyclerViewItemDecoration(3));
            adapterKouwei.setOnItemClick(new OrderDishesChooseAdapter.OnItemClick() {
                @Override
                public void onItemClick(ChooseBean entity, int position) {
                    if (entity.isChoose()) {
                        kouWeiEntity = mList.get(position);
                    } else {
                        kouWeiEntity = null;
                    }
                }
            });
        }

        binding.etKouwei.setText(kouweiRemark);
        binding.ivCancel.setOnClickListener(listener);
        binding.tvConfirm.setOnClickListener(listener);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.iv_cancel:
                    dismiss();
                    break;
                case R.id.tv_confirm:
                    dismiss();
                    kouweiRemark = binding.etKouwei.getText().toString().trim();
                    if (onCallBackListener != null) {
                        onCallBackListener.confirm(kouWeiEntity, kouweiRemark);
                    }
                    break;
            }
        }
    };


    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
            // 以下拉方式显示popupwindow
            this.showAsDropDown(parent, parent.getLayoutParams().width / 2, 18);
            backgroundAlpha(0.5f);
        } else {
            this.dismiss();
        }
    }

    public void showPopupWindowUp() {
        if (!this.isShowing()) {
            this.showAtLocation(((Activity) context).getWindow().getDecorView(), Gravity.CENTER, 0, 0);
            backgroundAlpha(0.5f);
        } else {
            this.dismiss();
        }
    }

    public void showPopupWindowBottom(View parent) {
        this.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        backgroundAlpha(0.5f);
    }

    public void showAtDropDownCenter(View parent) {
        if (parent.getVisibility() == View.GONE) {
            this.showAtLocation(parent, 0, 0, 0);
        } else {
            // x y
            int[] location = new int[2];
            //获取在整个屏幕内的绝对坐标
            parent.getLocationOnScreen(location);
            this.showAtLocation(parent, 0, location[0] / 2 + parent.getWidth() / 2 - this.getWidth() / 2, location[1] / 2 + parent.getHeight());
            backgroundAlpha(0.5f);
        }
    }

    private void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = ((Activity) context).getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        ((Activity) context).getWindow().setAttributes(lp);
    }


    public interface OnCallBackListener {
        void confirm(KouWeiEntity entity, String remark);
    }

    public void setOnCallBackListener(OnCallBackListener onCallBackListener) {
        this.onCallBackListener = onCallBackListener;
    }


    private DisplayMetrics getDisplayMetrics(Context context) {
        if (metrics != null) {
            return metrics;
        }
        metrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay()
                .getMetrics(metrics);
        return metrics;
    }
}
