package com.administrator.shopkeepertablet.view.widget;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.databinding.PopupwindowReturnFoodBinding;
import com.administrator.shopkeepertablet.model.entity.OrderFoodEntity;
import com.administrator.shopkeepertablet.model.entity.ReturnReasonEntity;
import com.administrator.shopkeepertablet.view.ui.adapter.ReturnFoodReasonAdapter;
import com.administrator.shopkeepertablet.viewmodel.ParishFoodViewModel;

import java.util.ArrayList;
import java.util.List;


/**
 * Description:
 * Author CC
 * Time 2018/7/7
 */

public class PopupWindowReturnFood extends PopupWindow {

    private Context context;
    private DisplayMetrics metrics;
    private PopupwindowReturnFoodBinding binding;
    private OrderFoodEntity orderFoodEntity;
    private List<ReturnReasonEntity> mList = new ArrayList<>();
    private ReturnReasonEntity returnReasonEntity = new ReturnReasonEntity();
    private ParishFoodViewModel viewModel;

    private OnCallBackListener onCallBackListener;

    public PopupWindowReturnFood(Context context, OrderFoodEntity orderFoodEntity, List<ReturnReasonEntity> mList,ParishFoodViewModel viewModel) {
        this.context = context;
        this.orderFoodEntity = orderFoodEntity;
        this.mList = mList;
        this.viewModel =viewModel;
        initPopupWindow();
    }

    private void initPopupWindow() {
        //使用view来引入布局
        binding = DataBindingUtil.inflate(
                LayoutInflater.from(context), R.layout.popupwindow_return_food, null, false);
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
        this.setHeight(448);
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
        String title = String.format("退菜 <font color=\"#FBBC05\">（%s）", orderFoodEntity.getProductName());
        binding.tvReturn.setText(Html.fromHtml(title));

        ReturnFoodReasonAdapter adapter = new ReturnFoodReasonAdapter(context, mList);
        binding.rlvReason.setAdapter(adapter);
        binding.rlvReason.setLayoutManager(new GridLayoutManager(context, 3));
        binding.rlvReason.addItemDecoration(new RecyclerViewItemDecoration(5));
        adapter.setOnItemClick(new ReturnFoodReasonAdapter.OnItemClick() {
            @Override
            public void onItemClick(ReturnReasonEntity entity, int position) {
                returnReasonEntity = entity;
            }
        });

        binding.ivCancel.setOnClickListener(listener);
        binding.rlPlus.setOnClickListener(listener);
        binding.rlReduce.setOnClickListener(listener);
        binding.tvConfirm.setOnClickListener(listener);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.rl_plus:
                    double num = Double.valueOf(binding.tvNum.getText().toString());
                    if (num + 1 <= orderFoodEntity.getCount()) {
                        num = num + 1;
                        binding.tvNum.setText(String.valueOf(num));
                    }
                    break;
                case R.id.rl_reduce:
                    double numReduce = Double.valueOf(binding.tvNum.getText().toString());
                    if (numReduce - 1 > 0) {
                        numReduce -= 1;
                        binding.tvNum.setText(String.valueOf(numReduce));
                    }
                    break;
                case R.id.tv_confirm:
                    dismiss();
                    viewModel.returnFood(orderFoodEntity,binding.etRemark.getText().toString(),returnReasonEntity,binding.tvNum.getText().toString());
                    break;
                case R.id.iv_cancel:
                    dismiss();
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
        void confirm(String num, String reason);
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
