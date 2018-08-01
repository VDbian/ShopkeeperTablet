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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.databinding.PopupwindowBeginTableBinding;
import com.administrator.shopkeepertablet.databinding.PopupwindowRechargeBinding;
import com.administrator.shopkeepertablet.model.entity.RechargeTypeEntity;
import com.administrator.shopkeepertablet.utils.MLog;
import com.administrator.shopkeepertablet.utils.MToast;
import com.administrator.shopkeepertablet.viewmodel.ParishFoodViewModel;
import com.administrator.shopkeepertablet.viewmodel.RechargeViewModel;

import java.util.ArrayList;
import java.util.List;


/**
 * Description:
 * Author CC
 * Time 2018/7/7
 */

public class PopupWindowRechargeDetail extends PopupWindow {

    private Context context;
    private RechargeViewModel viewModel;
    private DisplayMetrics metrics;
    private PopupwindowRechargeBinding binding;
    private final String[] payStrs = {"现金", "银行卡", "主扫微信", "会员卡", "被扫支付宝", "被扫微信", "美团券", "主扫支付宝"};
    private int payType = 1;
    private String typeId;

    private OnCallBackListener onCallBackListener;

    public PopupWindowRechargeDetail(Context context, RechargeViewModel viewModel) {
        this.context = context;
        this.viewModel = viewModel;
        initPopupWindow();
        viewModel.searchMember();
    }

    private void initPopupWindow() {
        //使用view来引入布局
        binding = DataBindingUtil.inflate(
                LayoutInflater.from(context), R.layout.popupwindow_recharge, null, false);
//        setContentView((Activity) context, R.layout.popupwindow_begin_table)
        binding.setViewModel(viewModel);

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
        this.setWidth(viewWidth);
        // 设置SelectPicPopupWindow弹出窗体的高
//        if (viewHeight > h / 2) {
//            this.setHeight(h / 2);
//        } else {
        this.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
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
        binding.tvDiy.setTextColor(context.getResources().getColor(R.color.color23cac0));
        binding.tvPro.setTextColor(context.getResources().getColor(R.color.colorc0c8ce));
        binding.llDiyMoney.setVisibility(View.VISIBLE);
        binding.llName.setVisibility(View.GONE);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                context,
                R.layout.item_spinner,
                R.id.tv_name,
                payStrs);
        binding.spinnerType.setAdapter(adapter);
        binding.spinnerType.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if (position == 8) {
                            payType = 10;
                        } else {
                            payType = position + 1;
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        payType = 1;
                    }
                }
        );

        binding.ivCancel.setOnClickListener(listener);
        binding.tvDiy.setOnClickListener(listener);
        binding.tvPro.setOnClickListener(listener);
        binding.tvRecharge.setOnClickListener(listener);
    }

    public void setSpinnerName(List<RechargeTypeEntity> mList) {
        List<String> strings = new ArrayList<>();
        for (RechargeTypeEntity entity : mList) {
            strings.add(entity.getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                context,
                R.layout.item_spinner,
                R.id.tv_name,
                strings);
        binding.spinnerName.setAdapter(adapter);
        binding.spinnerName.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        typeId = mList.get(position).getId();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        typeId = mList.get(0).getId();
                    }
                }
        );

    }


    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.iv_cancel:
                    dismiss();
                    break;
                case R.id.tv_diy:
                    binding.tvDiy.setTextColor(context.getResources().getColor(R.color.color23cac0));
                    binding.tvPro.setTextColor(context.getResources().getColor(R.color.colorc0c8ce));
                    binding.llDiyMoney.setVisibility(View.VISIBLE);
                    binding.llName.setVisibility(View.GONE);
                    break;
                case R.id.tv_pro:
                    binding.tvDiy.setTextColor(context.getResources().getColor(R.color.colorc0c8ce));
                    binding.tvPro.setTextColor(context.getResources().getColor(R.color.color23cac0));
                    binding.llDiyMoney.setVisibility(View.GONE);
                    binding.llName.setVisibility(View.VISIBLE);
                    viewModel.getRechargeType();
                    break;
                case R.id.tv_recharge:
                    if (binding.llDiyMoney.getVisibility()==View.VISIBLE){
                        if (TextUtils.isEmpty(viewModel.money.get())){
                            MToast.showToast(context,"充值金额不能为空");
                            return;
                        }
                        if (TextUtils.isEmpty(viewModel.proCode.get())){
                            MToast.showToast(context,"校验码不能为空");
                            return;
                        }
                        viewModel.checkCode("",payType);
                    }else {
                        if (TextUtils.isEmpty(viewModel.proCode.get())){
                            MToast.showToast(context,"校验码不能为空");
                            return;
                        }
                        viewModel.checkCode(typeId,payType);
                    }
                    break;
            }
        }
    };

    public void showPopupWindowRight(View parent) {
        if (!this.isShowing()) {
            this.showAtLocation(parent, Gravity.RIGHT, 0, 0);
            backgroundAlpha(0.5f);
        } else {
            this.dismiss();
        }
    }

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
