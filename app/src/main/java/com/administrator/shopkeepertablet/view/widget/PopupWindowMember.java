package com.administrator.shopkeepertablet.view.widget;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.databinding.PopupwindowMemberBinding;
import com.administrator.shopkeepertablet.model.entity.CardEntity;
import com.administrator.shopkeepertablet.utils.MToast;
import com.administrator.shopkeepertablet.view.ui.adapter.CardAdapter;
import com.administrator.shopkeepertablet.viewmodel.PayViewModel;

import java.util.ArrayList;
import java.util.List;

import static android.icu.lang.UProperty.MATH;


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
    private CardAdapter adapter;
    private List<CardEntity> cardEntityList = new ArrayList<>();
    private Double scoreMoney = 0.0;
    private Double cardMoney = 0.0;

    private OnCallBackListener onCallBackListener;

    public PopupWindowMember(Context context, PayViewModel viewModel) {
        this.context = context;
        this.viewModel = viewModel;
        initPopupWindow();
    }

    private void initPopupWindow() {
        //使用view来引入布局
        binding = DataBindingUtil.inflate(
                LayoutInflater.from(context), R.layout.popupwindow_member, null, false);
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
        this.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体的高
//        if (viewHeight > h / 2) {
//            this.setHeight(h / 2);
//        } else {
        this.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
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
        adapter = new CardAdapter(context, cardEntityList);
        binding.rlvCard.setAdapter(adapter);
        binding.rlvCard.setLayoutManager(new LinearLayoutManager(context));
        binding.rlvCard.addItemDecoration(new RecyclerViewItemDecoration(5));
        adapter.setOnItemClick(new CardAdapter.OnItemClick() {
            @Override
            public void onItemClick(CardEntity entity, int position) {
                if (scoreMoney > 0) {
                    MToast.showToast(context, "使用积分后不能使用卡券");
                    return;
                }
                for (CardEntity card : cardEntityList) {
                    if (card.isSelect()) {
                        viewModel.cardEntity.set(card);
                        cardMoney = card.getMoney();
                    }
                }
            }
        });

        if (viewModel.member.get() != null) {
            searchSuccess();
        } else {
            binding.llMemberInfo.setVisibility(View.INVISIBLE);
            binding.llIntegral.setVisibility(View.INVISIBLE);
        }
        binding.ivCancel.setOnClickListener(listener);
        binding.tvSearchMember.setOnClickListener(listener);
        binding.tvIntegral.setOnClickListener(listener);
        binding.tvConfirm.setOnClickListener(listener);

    }

    public void searchSuccess() {
        binding.llMemberInfo.setVisibility(View.VISIBLE);
        binding.llIntegral.setVisibility(View.VISIBLE);
        cardEntityList.clear();
        cardEntityList.addAll(viewModel.cardList.get());
        adapter.notifyDataSetChanged();
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_search_member:
                    String num = binding.etMemberNum.getText().toString().trim();
                    if (TextUtils.isEmpty(num)) {
                        MToast.showToast(context, "请输入会员号");
                    } else {
                        viewModel.getMember(num);
                    }
                    break;
                case R.id.tv_integral:
                    if (cardMoney>0) {
                        MToast.showToast(context, "已选择卡券状态下不能进行积分兑换");
                        return;
                    }
                    String integral = binding.etIntegral.getText().toString().trim();
                    if (!TextUtils.isEmpty(integral)) {
                        if (viewModel.priceEntity.get().getMemberpice() > 0 || viewModel.member.get().getRate() == 0) {
                            MToast.showToast(context, "不能积分兑换");
                            return;
                        }
                        int scoreNum = Integer.parseInt(integral);
                        if (scoreNum > viewModel.member.get().getScore()) {
                            MToast.showToast(context, "积分不足");
                            return;
                        }
                        scoreMoney = (scoreNum * viewModel.member.get().getRate());
                        if (scoreMoney > viewModel.priceEntity.get().getYinfu()) {
                            MToast.showToast(context, "优惠金额不能大于应付金额");
                            return;
                        }
                    }
                    break;
                case R.id.tv_confirm:
                    dismiss();
                    if (onCallBackListener != null) {
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
