package com.administrator.shopkeepertablet.view.widget;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.databinding.PopupwindowMemberBinding;
import com.administrator.shopkeepertablet.utils.MToast;
import com.administrator.shopkeepertablet.viewmodel.PayViewModel;


/**
 * Description:
 * Author CC
 * Time 2018/7/7
 */

public class PopupWindowMember extends PopupWindow {

    private Context context;
    private DisplayMetrics metrics;
    private PopupwindowMemberBinding binding;
    private PayViewModel viewModel;

    private OnCallBackListener onCallBackListener;

    public PopupWindowMember(Context context, PayViewModel viewModel) {
        this.context = context;
        this.viewModel =viewModel;
        initPopupWindow();
    }

    private void initPopupWindow() {
        //使用view来引入布局
        binding = DataBindingUtil.inflate(
                LayoutInflater.from(context), R.layout.popupwindow_member, null, false);
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
        this.setWidth(720);
        // 设置SelectPicPopupWindow弹出窗体的高
//        if (viewHeight > h / 2) {
//            this.setHeight(h / 2);
//        } else {
        this.setHeight(520);
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
                if (onCallBackListener!=null){
                    onCallBackListener.dismiss();
                }
            }
        });

    }

    private void initView() {
        if (viewModel.member.get()!=null){
            searchSuccess();
        }
        binding.llMemberInfo.setVisibility(View.INVISIBLE);
        binding.llIntegral.setVisibility(View.INVISIBLE);
        binding.ivCancel.setOnClickListener(listener);
        binding.tvSearchMember.setOnClickListener(listener);
        binding.tvIntegral.setOnClickListener(listener);
        binding.tvConfirm.setOnClickListener(listener);
    }

    public void searchSuccess(){
        binding.llMemberInfo.setVisibility(View.VISIBLE);
        binding.llIntegral.setVisibility(View.VISIBLE);
        binding.tvNo.setText(String.format(context.getResources().getString(R.string.member_num), viewModel.member.get().getNo()));
        binding.tvName.setText(String.format(context.getResources().getString(R.string.member_name), viewModel.member.get().getName()));
        binding.tvMoney.setText(String.format(context.getResources().getString(R.string.member_balance), viewModel.member.get().getMoney()));
        binding.tvPhone.setText(String.format(context.getResources().getString(R.string.member_phone), viewModel.member.get().getPhone()));
        binding.tvScore.setText(String.format(context.getResources().getString(R.string.member_points), viewModel.member.get().getScore()));
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_search_member:
                    String num = binding.etMemberNum.getText().toString().trim();
                    if (TextUtils.isEmpty(num)){
                        MToast.showToast(context,"请输入会员号");
                    }else {
                        viewModel.getMember(num);
                    }
                    break;
                case R.id.tv_integral:
                    String integral =binding.etIntegral.getText().toString().trim();
                    if (!TextUtils.isEmpty(integral)&&Integer.getInteger(integral)<=viewModel.member.get().getScore()){
                        viewModel.integral.set(integral);
                    }
                    break;
                case R.id.tv_confirm:
                    dismiss();
                    if (onCallBackListener!=null){
                        onCallBackListener.confirm();
                    }
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
        void confirm();

        void dismiss();
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
