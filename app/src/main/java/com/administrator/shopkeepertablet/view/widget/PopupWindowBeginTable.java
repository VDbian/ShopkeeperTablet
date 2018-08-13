package com.administrator.shopkeepertablet.view.widget;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.databinding.PopupwindowBeginTableBinding;
import com.administrator.shopkeepertablet.utils.MLog;
import com.administrator.shopkeepertablet.viewmodel.ParishFoodViewModel;


/**
 * Description:
 * Author CC
 * Time 2018/7/7
 */

public class PopupWindowBeginTable extends PopupWindow {

    private Context context;
    private ParishFoodViewModel viewModel;
    private DisplayMetrics metrics;
    private PopupwindowBeginTableBinding binding;


    private OnCallBackListener onCallBackListener;

    public PopupWindowBeginTable(Context context, ParishFoodViewModel viewModel) {
        this.context = context;
        this.viewModel = viewModel;
        initPopupWindow();
    }

    private void initPopupWindow() {
        //使用view来引入布局
        binding = DataBindingUtil.inflate(
                LayoutInflater.from(context), R.layout.popupwindow_begin_table, null, false);
//        setContentView((Activity) context, R.layout.popupwindow_begin_table)
        binding.setViewModel(viewModel);
        setListener();
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
        this.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体的高
//        if (viewHeight > h / 2) {
//            this.setHeight(h / 2);
//        } else {
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
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

    private void setListener() {
        binding.llCancel.setOnClickListener(listener);
        binding.tvBeginTable.setOnClickListener(listener);
        binding.tvBeginTableAndOrder.setOnClickListener(listener);
        binding.rlDinnersNumPlus.setOnClickListener(listener);
        binding.rlDinnersNumReduce.setOnClickListener(listener);
        binding.rlTablewareNumPlus.setOnClickListener(listener);
        binding.rlTablewareNumReduce.setOnClickListener(listener);

    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ll_cancel:
                    dismiss();
                    break;
                case R.id.rl_dinners_num_plus:
                    String s= binding.tvDinnersNum.getText().toString();
                    MLog.e("vd",s);
                    int num = Integer.parseInt(s);
                    viewModel.people.set(String.valueOf(num + 1));
                    break;
                case R.id.rl_dinners_num_reduce:
                    int numReduce = Integer.parseInt(binding.tvDinnersNum.getText().toString());
                    if (numReduce > 1) {
                        viewModel.people.set(String.valueOf(numReduce - 1));
                    }
                    break;
                case R.id.rl_tableware_num_plus:
                    int WareNum = Integer.parseInt(binding.tvTablewareNum.getText().toString());
                    viewModel.tableware.set(String.valueOf(WareNum + 1));
                    break;
                case R.id.rl_tableware_num_reduce:
                    int WareNumReduce = Integer.parseInt(binding.tvTablewareNum.getText().toString());
                    if (WareNumReduce > 1) {
                        viewModel.tableware.set(String.valueOf(WareNumReduce - 1));
                    }
                    break;
                case R.id.tv_begin_table:
                    if (onCallBackListener != null) {
                        onCallBackListener.open();
                    }
                    break;
                case R.id.tv_begin_table_and_order:
                    if (onCallBackListener != null) {
                        onCallBackListener.order();
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

    public void showPopupWindowUp(View parent) {
        if (!this.isShowing()) {
            // 以下拉方式显示popupwindow
            int[] location = new int[2];
            parent.getLocationOnScreen(location);
            this.showAtLocation(parent, Gravity.NO_GRAVITY,
                    location[0], location[1] - this.getHeight());
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
        void open();

        void order();
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
