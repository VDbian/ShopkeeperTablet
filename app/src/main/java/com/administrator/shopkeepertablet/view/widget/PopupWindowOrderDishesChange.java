package com.administrator.shopkeepertablet.view.widget;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.databinding.DilogOrderDishesChangeBinding;
import com.administrator.shopkeepertablet.model.entity.FoodEntity;
import com.administrator.shopkeepertablet.model.entity.ProductKouWeiEntity;
import com.administrator.shopkeepertablet.model.entity.SeasonEntity;
import com.administrator.shopkeepertablet.model.entity.SpecEntity;
import com.administrator.shopkeepertablet.model.entity.bean.CartBean;
import com.administrator.shopkeepertablet.model.entity.bean.ChooseBean;
import com.administrator.shopkeepertablet.model.entity.bean.FoodAddBean;
import com.administrator.shopkeepertablet.utils.MToast;
import com.administrator.shopkeepertablet.view.ui.adapter.OrderDishesAddAdapter;
import com.administrator.shopkeepertablet.view.ui.adapter.OrderDishesChooseAdapter;
import com.administrator.shopkeepertablet.view.ui.adapter.ProductKouWeiAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Description:
 * Author CC
 * Time 2018/7/7
 */

public class PopupWindowOrderDishesChange extends PopupWindow {

    private Context context;
    private FoodEntity foodEntity;
    private DisplayMetrics metrics;
    private DilogOrderDishesChangeBinding binding;
    private CartBean cartBean;
    private List<SpecEntity> specEntityList = new ArrayList<>();
    private List<ChooseBean> chooseSpecList = new ArrayList<>();
    private List<ProductKouWeiEntity> productKouWeiEntityList = new ArrayList<>();
    private List<ChooseBean> chooseKouweiList = new ArrayList<>();
    private List<SeasonEntity> seasonEntityList = new ArrayList<>();
    private List<FoodAddBean> SeasonChooseList = new ArrayList<>();
    private double price = 0.0;
    private List<FoodAddBean> foodAddBeanList = new ArrayList<>();
    private SpecEntity specEntity;
    private List<ProductKouWeiEntity> productKouWeiEntity = new ArrayList<>();
    private int giveNum = 0;


    private OnCallBackListener onCallBackListener;

    public PopupWindowOrderDishesChange(Context context, CartBean cartBean) {
        this.context = context;
        this.cartBean = cartBean;
        initPopupWindow();
    }

    private void initPopupWindow() {
        //使用view来引入布局
        binding = DataBindingUtil.inflate(
                LayoutInflater.from(context), R.layout.dilog_order_dishes_change, null, false);
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
        this.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体的高
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
                save();
            }
        });

    }

    private void initView() {
        foodEntity = cartBean.getFoodEntity();
        giveNum = cartBean.getGiveNum();
        specEntity = cartBean.getSpec();
        productKouWeiEntity = cartBean.getProductKouWeiEntity();
        SeasonChooseList = cartBean.getFoodAddBeanList();
        if (foodEntity.getSpecEntityList() != null) {
            specEntityList = foodEntity.getSpecEntityList();
            for (SpecEntity specEntity : specEntityList) {
                if (cartBean.getSpec() != null && cartBean.getSpec().getUId().equals(specEntity.getUId())) {
                    ChooseBean chooseSpec = new ChooseBean(true, specEntity.getName());
                    chooseSpecList.add(chooseSpec);
                } else {
                    ChooseBean chooseSpec = new ChooseBean(false, specEntity.getName());
                    chooseSpecList.add(chooseSpec);
                }
            }
        }
        if (foodEntity.getProductKouWeiEntityList() != null) {
            productKouWeiEntityList = foodEntity.getProductKouWeiEntityList();
            for (ProductKouWeiEntity kouWei : productKouWeiEntityList) {
                if (productKouWeiEntity != null && !productKouWeiEntity.isEmpty()) {
                    for (ProductKouWeiEntity productKouWei : productKouWeiEntity) {
                        if (productKouWei.getuId().equals(kouWei.getuId())) {
                            kouWei.setSelect(true);
                        }
                    }
                }
            }
        }
        if (foodEntity.getSeasonEntityList() != null) {
            seasonEntityList = foodEntity.getSeasonEntityList();
            for (SeasonEntity seasonEntity : seasonEntityList) {
                FoodAddBean foodAddBean = new FoodAddBean(seasonEntity.getSeasonId(), seasonEntity.getName(), Double.valueOf(seasonEntity.getPrice()), 1, false);
                foodAddBeanList.add(foodAddBean);
            }
            if (cartBean.getFoodAddBeanList() != null && cartBean.getFoodAddBeanList().size() > 0) {
                for (FoodAddBean addBean : cartBean.getFoodAddBeanList()) {
                    for (FoodAddBean bean : foodAddBeanList) {
                        if (addBean.getId().equals(bean.getId())) {
                            bean.setNum(addBean.getNum());
                            bean.setSelect(true);
                        }
                    }
                }
            }
        }

        binding.rlSpec.setVisibility(specEntityList.size() > 0 ? View.VISIBLE : View.GONE);
        binding.rlKouwei.setVisibility(productKouWeiEntityList.size() > 0 ? View.VISIBLE : View.GONE);
        binding.rlAdd.setVisibility(seasonEntityList.size() > 0 ? View.VISIBLE : View.GONE);
        if (specEntityList.size() > 0) {
            setSelect(0);
        } else if (productKouWeiEntityList.size() > 0) {
            setSelect(1);
        } else if (foodEntity.getSeasonEntityList().size() > 0) {
            setSelect(2);
        }
        setRlvAdapter();

        if (foodEntity.getProductProperty().equals("1")) {
            binding.llCopies.setVisibility(View.GONE);
            binding.llWeigh.setVisibility(View.VISIBLE);
            binding.tvWeighUnit.setText(foodEntity.getUnit());
            binding.etWeight.setText(cartBean.getWeight());
        } else {
            binding.llCopies.setVisibility(View.VISIBLE);
            binding.tvNum.setText(cartBean.getNum());
            binding.llWeigh.setVisibility(View.GONE);
        }

        binding.tvName.setText(foodEntity.getType() ? foodEntity.getPackageName() : foodEntity.getProductName());
        price = cartBean.getPrice();
        String format = context.getResources().getString(R.string.price_and_unit);
        binding.tvPriceAndUnit.setText(String.format(format, price, specEntity == null ? "份" : specEntity.getName()));
        if (!TextUtils.isEmpty(cartBean.getKouwei())) {
            binding.etKouwei.setText(cartBean.getKouwei());
        }

        binding.tvGiving.setText(String.valueOf(giveNum));

        binding.rlSpec.setOnClickListener(listener);
        binding.rlKouwei.setOnClickListener(listener);
        binding.rlAdd.setOnClickListener(listener);
        binding.rlNumPlus.setOnClickListener(listener);
        binding.rlNumReduce.setOnClickListener(listener);
        binding.llGive.setOnClickListener(listener);
        binding.llDelete.setOnClickListener(listener);
        binding.ivCancel.setOnClickListener(listener);
        binding.rlPlus.setOnClickListener(listener);
        binding.rlReduce.setOnClickListener(listener);
    }

    private void setRlvAdapter() {
        if (specEntityList.size() > 0) {
            final OrderDishesChooseAdapter adapterSpec = new OrderDishesChooseAdapter(context, chooseSpecList);
            binding.rlvSpec.setAdapter(adapterSpec);
            binding.rlvSpec.setLayoutManager(new GridLayoutManager(context, 3));
            binding.rlvSpec.addItemDecoration(new RecyclerViewItemDecoration(5));
            adapterSpec.setOnItemClick(new OrderDishesChooseAdapter.OnItemClick() {
                @Override
                public void onItemClick(ChooseBean entity, int position) {
                    specEntity = specEntityList.get(position);
                    price = specEntity.getPrice();
                    String format = context.getResources().getString(R.string.price_and_unit);
                    binding.tvPriceAndUnit.setText(String.format(format, price, specEntity.getName()));
                    save();
                }
            });
        }
        if (productKouWeiEntityList.size() > 0) {
            final ProductKouWeiAdapter adapterKouwei = new ProductKouWeiAdapter(context, productKouWeiEntityList);
            binding.rlvKouwei.setAdapter(adapterKouwei);
            binding.rlvKouwei.setLayoutManager(new GridLayoutManager(context, 3));
            binding.rlvSpec.addItemDecoration(new RecyclerViewItemDecoration(5));
            adapterKouwei.setOnItemClick(new ProductKouWeiAdapter.OnItemClick() {
                @Override
                public void onItemClick(int position) {
                    ProductKouWeiEntity entity = productKouWeiEntityList.get(position);
                    if (entity.isSelect()) {
                        productKouWeiEntity.add(entity);
                    } else {
                        for (ProductKouWeiEntity e : productKouWeiEntity) {
                            if (e.getuId().equals(entity.getuId())) {
                                productKouWeiEntity.remove(e);
                            }
                        }
                    }
                    save();
                }
            });

        }
        if (seasonEntityList.size() > 0) {
            final OrderDishesAddAdapter adapterSeason = new OrderDishesAddAdapter(context, foodAddBeanList);
            binding.rlvAdd.setAdapter(adapterSeason);
            binding.rlvAdd.setLayoutManager(new LinearLayoutManager(context));
            binding.rlvAdd.addItemDecoration(new RecyclerViewItemDecoration(5));
            adapterSeason.setOnItemClick(new OrderDishesAddAdapter.OnItemClick() {
                @Override
                public void onItemClick(FoodAddBean entity, int position, boolean select) {
                    if (select) {
                        SeasonChooseList.remove(entity);
                    } else {
                        SeasonChooseList.add(entity);
                    }
                    save();
                }
            });
        }
    }


    private void setSelect(int position) {
        binding.tvSpec.setBackgroundColor(context.getResources().getColor(position == 0 ? R.color.color23cac0 : R.color.colorWhite));
        binding.tvSpec.setTextColor(context.getResources().getColor(position == 0 ? R.color.colorWhite : R.color.color666666));
        binding.tvKouwei.setBackgroundColor(context.getResources().getColor(position == 1 ? R.color.color23cac0 : R.color.colorWhite));
        binding.tvKouwei.setTextColor(context.getResources().getColor(position == 1 ? R.color.colorWhite : R.color.color666666));
        binding.tvAdd.setBackgroundColor(context.getResources().getColor(position == 2 ? R.color.color23cac0 : R.color.colorWhite));
        binding.tvAdd.setTextColor(context.getResources().getColor(position == 2 ? R.color.colorWhite : R.color.color666666));
//        binding.tvRemark.setBackgroundColor(context.getResources().getColor(position == 3 ? R.color.color23cac0 : R.color.colorWhite));
//        binding.tvRemark.setTextColor(context.getResources().getColor(position == 3 ? R.color.colorWhite : R.color.color666666));
        binding.llSpec.setVisibility(position == 0 ? View.VISIBLE : View.GONE);
        binding.llKouwei.setVisibility(position == 1 ? View.VISIBLE : View.GONE);
        binding.llAdd.setVisibility(position == 2 ? View.VISIBLE : View.GONE);
        binding.llGiveDetail.setVisibility(position == 3 ? View.VISIBLE : View.GONE);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.rl_spec:
                    setSelect(0);
                    break;
                case R.id.rl_kouwei:
                    setSelect(1);
                    break;
                case R.id.rl_add:
                    setSelect(2);
                    break;
                case R.id.rl_num_plus:
                    int num = Integer.parseInt(binding.tvNum.getText().toString());
                    binding.tvNum.setText(String.valueOf(num + 1));
                    save();
                    break;
                case R.id.rl_num_reduce:
                    int num1 = Integer.parseInt(binding.tvNum.getText().toString());
                    if (num1 > 1) {
                        binding.tvNum.setText(String.valueOf(num1 - 1));
                        save();
                    }
                    break;
                case R.id.ll_give:
                    setSelect(3);
                    break;
                case R.id.ll_delete:
                    dismiss();
                    if (onCallBackListener != null) {
                        onCallBackListener.delete();
                    }
                    break;
                case R.id.rl_reduce:
                    int Num2 = Integer.parseInt(binding.tvGiving.getText().toString());
                    if (Num2 >= 1) {
                        binding.tvGiving.setText(String.valueOf(Num2 - 1));
                        giveNum = Num2 - 1;
                        save();
                    }
                    break;
                case R.id.rl_plus:
                    int Num3 = Integer.parseInt(binding.tvGiving.getText().toString());
                    if (cartBean.getFoodEntity().getProductProperty().equals("1")) {
                        if (Num3 == 0) {
                            binding.tvGiving.setText(String.valueOf(1));
                            giveNum = 1;
                        }
                    } else {
                        if (Num3 < Integer.parseInt(binding.tvNum.getText().toString())) {
                            binding.tvGiving.setText(String.valueOf(Num3 + 1));
                            giveNum = Num3 + 1;
                        }
                    }
                    save();
                    break;
                case R.id.iv_cancel:
                    dismiss();
                    break;
            }
        }
    };


    public void save() {
        if (onCallBackListener != null) {
            CartBean cartBean = new CartBean();
            cartBean.setGiveNum(giveNum);
            cartBean.setFoodEntity(foodEntity);
            cartBean.setKouwei(binding.etKouwei.getText().toString());
            cartBean.setProductKouWeiEntity(productKouWeiEntity);
            if (binding.llCopies.getVisibility() == View.VISIBLE) {
                cartBean.setNum(binding.tvNum.getText().toString());
                cartBean.setWeight("");
            } else {
                cartBean.setWeight(binding.etWeight.getText().toString());
                cartBean.setUnit(binding.tvWeighUnit.getText().toString());
                cartBean.setNum("1");
            }
            cartBean.setPrice(price);
            if (specEntity != null) {
                cartBean.setSpec(specEntity);
            }
            SeasonChooseList.clear();
            for (FoodAddBean foodAddBean : foodAddBeanList) {
                if (foodAddBean.isSelect()) {
                    SeasonChooseList.add(foodAddBean);
                }
            }
            cartBean.setFoodAddBeanList(SeasonChooseList);
            onCallBackListener.save(cartBean);
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
//            // 以下拉方式显示popupwindow
//            int[] location = new int[2];
//            parent.getLocationOnScreen(location);
//            this.showAsDropDown(parent, location[0]+this.getWidth(), location[1] + (this.getHeight())/2,Gravity.END);
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
        void save(CartBean cartBean);

        void delete();
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
