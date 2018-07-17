package com.administrator.shopkeepertablet.view.widget;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.administrator.shopkeepertablet.R;;
import com.administrator.shopkeepertablet.databinding.PopupwindowOrderPayBinding;
import com.administrator.shopkeepertablet.model.entity.OrderFoodEntity;
import com.administrator.shopkeepertablet.view.ui.adapter.OrderFoodAdapter;
import com.administrator.shopkeepertablet.view.ui.adapter.base.AdapterOnItemClick;
import com.administrator.shopkeepertablet.viewmodel.parish.ParishFoodViewModel;

import java.util.List;


/**
 * Description:
 * Author CC
 * Time 2018/7/7
 */

public class PopupWindowPay extends PopupWindow {

    private Context context;
    private ParishFoodViewModel viewModel;
    private DisplayMetrics metrics;
    private PopupwindowOrderPayBinding binding;
    private List<OrderFoodEntity> mList;
    private OrderFoodEntity orderFood;


    private OnCallBackListener onCallBackListener;

    public PopupWindowPay(Context context, ParishFoodViewModel viewModel, List<OrderFoodEntity> mList) {
        this.context = context;
        this.viewModel = viewModel;
        this.mList = mList;
        initPopupWindow();
    }

    private void initPopupWindow() {
        //使用view来引入布局
        binding = DataBindingUtil.inflate(
                LayoutInflater.from(context), R.layout.popupwindow_order_pay, null, false);
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
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(00000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });

    }

    private void initView() {
        OrderFoodAdapter adapter = new OrderFoodAdapter(context, mList);
        binding.rlvOrder.setAdapter(adapter);
        binding.rlvOrder.setLayoutManager(new LinearLayoutManager(context));
        binding.rlvOrder.addItemDecoration(new RecyclerViewItemDecoration(5));
        adapter.setOnItemClick(new AdapterOnItemClick<OrderFoodEntity>() {
            @Override
            public void onItemClick(OrderFoodEntity orderFoodEntity, int position) {
                orderFood = orderFoodEntity;
                binding.llMore.setVisibility(View.GONE);
                binding.llItem.setVisibility(View.VISIBLE);
            }
        });
        binding.llMore.setVisibility(View.INVISIBLE);
        binding.llItem.setVisibility(View.INVISIBLE);
        setListener();
    }

    private void setListener() {
        binding.ivMore.setOnClickListener(listener);
        binding.llCancel.setOnClickListener(listener);
        binding.tvPay.setOnClickListener(listener);
        binding.tvScanPay.setOnClickListener(listener);
        binding.llAddFood.setOnClickListener(listener);//加菜 0
        binding.llPushFood.setOnClickListener(listener);//催菜 1
        binding.llChangeTable.setOnClickListener(listener);//换桌 2
        binding.llCancelOrder.setOnClickListener(listener);//撤单 3
        binding.llFoodPrinter.setOnClickListener(listener);//商品补打 4
        binding.llMergeOrder.setOnClickListener(listener);//并单 5
        binding.llChangePeople.setOnClickListener(listener);//修改人数 6
        binding.llTransferFood.setOnClickListener(listener);//转菜 0
        binding.llUrgedFood.setOnClickListener(listener);// 单品催菜 1
        binding.llRefundFood.setOnClickListener(listener);//退菜 2
        binding.llGivingFood.setOnClickListener(listener);//赠送 3

    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ll_cancel:
                    dismiss();
                    break;
                case R.id.tv_pay:
                    if (onCallBackListener != null) {
                        onCallBackListener.pay();
                    }
                    break;
                case R.id.tv_scan_pay:
                    if (onCallBackListener != null) {
                        onCallBackListener.scanPay();
                    }
                    break;
                case R.id.iv_more:
                    if (binding.llMore.getVisibility() == View.VISIBLE){
                        binding.llMore.setVisibility(View.INVISIBLE);
                    }else{
                        binding.llMore.setVisibility(View.VISIBLE);
                    }
                    binding.llItem.setVisibility(View.INVISIBLE);
                    break;
                case R.id.ll_add_food:
                    dismiss();
                    if (onCallBackListener != null) {
                        onCallBackListener.more(0);
                    }
                    break;
                case R.id.ll_change_table:
                    if (onCallBackListener != null) {
                        onCallBackListener.more(1);
                    }
                    break;
                case R.id.ll_cancel_order:
                    binding.llMore.setVisibility(View.INVISIBLE);
                    if (onCallBackListener != null) {
                        onCallBackListener.more(2);
                    }
                    break;
                case R.id.ll_merge_order:
                    if (onCallBackListener != null) {
                        onCallBackListener.more(3);
                    }
                    break;
                case R.id.ll_food_printer:
                    if (onCallBackListener != null) {
                        onCallBackListener.more(4);
                    }
                    break;
                case R.id.ll_push_food:
                    if (onCallBackListener != null) {
                        onCallBackListener.more(5);
                    }
                    break;
                case R.id.ll_change_people:
                    binding.llMore.setVisibility(View.INVISIBLE);
                    if (onCallBackListener != null) {
                        onCallBackListener.more(6);
                    }
                    break;
                case R.id.ll_transfer_food:
                    if (onCallBackListener!=null){
                        onCallBackListener.item(orderFood,0);
                    }
                    break;
                case R.id.ll_urged_food:
                    if (onCallBackListener!=null){
                        onCallBackListener.item(orderFood,1);
                    }
                    break;
                case R.id.ll_refund_food:
                    if (onCallBackListener!=null){
                        onCallBackListener.item(orderFood,2);
                    }
                    break;
                case R.id.ll_giving_food:
                    if (onCallBackListener!=null){
                        onCallBackListener.item(orderFood,3);
                    }
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
        void pay();

        void scanPay();

        void more(int position);

        void item(OrderFoodEntity entity,int position);
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
