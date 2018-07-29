package com.administrator.shopkeepertablet.view.ui.activity.parish;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.administrator.shopkeepertablet.AppConstant;
import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.databinding.ActivityBeginOrderDishesBinding;
import com.administrator.shopkeepertablet.di.app.AppComponent;
import com.administrator.shopkeepertablet.di.parish.DaggerParishActivityComponent;
import com.administrator.shopkeepertablet.di.parish.ParishActivityModule;
import com.administrator.shopkeepertablet.model.entity.EventOrderDishesEntity;
import com.administrator.shopkeepertablet.model.entity.FoodEntity;
import com.administrator.shopkeepertablet.model.entity.FoodTypeEntity;
import com.administrator.shopkeepertablet.model.entity.FoodTypeSelectEntity;
import com.administrator.shopkeepertablet.model.entity.KouWeiEntity;
import com.administrator.shopkeepertablet.model.entity.OrderEntity;
import com.administrator.shopkeepertablet.model.entity.OrderFoodEntity;
import com.administrator.shopkeepertablet.model.entity.ProductKouWeiEntity;
import com.administrator.shopkeepertablet.model.entity.SeasonEntity;
import com.administrator.shopkeepertablet.model.entity.SpecEntity;
import com.administrator.shopkeepertablet.model.entity.bean.CartBean;
import com.administrator.shopkeepertablet.model.entity.bean.EventPayBean;
import com.administrator.shopkeepertablet.model.entity.bean.EventReturnBean;
import com.administrator.shopkeepertablet.model.entity.bean.FoodAddBean;
import com.administrator.shopkeepertablet.utils.DataEvent;
import com.administrator.shopkeepertablet.utils.DateUtils;
import com.administrator.shopkeepertablet.utils.MToast;
import com.administrator.shopkeepertablet.view.ui.BaseActivity;
import com.administrator.shopkeepertablet.view.ui.adapter.FoodTypeAdapter;
import com.administrator.shopkeepertablet.view.ui.adapter.OrderDishesCartAdapter;
import com.administrator.shopkeepertablet.view.ui.adapter.OrderDishesVarietyAdapter;
import com.administrator.shopkeepertablet.view.ui.adapter.OrderFoodAdapter;
import com.administrator.shopkeepertablet.view.ui.adapter.base.AdapterOnItemClick;
import com.administrator.shopkeepertablet.view.ui.fragment.ParishFoodFragment;
import com.administrator.shopkeepertablet.view.widget.PopupWindowAllKouwei;
import com.administrator.shopkeepertablet.view.widget.PopupWindowOrderDishesChange;
import com.administrator.shopkeepertablet.view.widget.PopupWindowOrderDishesChoose;
import com.administrator.shopkeepertablet.view.widget.RecyclerViewItemDecoration;
import com.administrator.shopkeepertablet.viewmodel.OrderDishesViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.administrator.shopkeepertablet.R.color;

/**
 * Description:
 * Author CC
 * Time 2018/6/30
 */


public class OrderDishesActivity extends BaseActivity implements View.OnClickListener {
    ActivityBeginOrderDishesBinding binding;
    @Inject
    OrderDishesViewModel viewModel;
    private OrderDishesVarietyAdapter adapter;
    private FoodTypeAdapter foodTypeAdapter;
    private List<FoodEntity> mList = new ArrayList<>();
    private List<FoodTypeSelectEntity> foodTypeEntityList = new ArrayList<>();
    private OrderDishesCartAdapter cartAdapter;
    private List<CartBean> cartBeanList = new ArrayList<>();
    private KouWeiEntity kouWeiEntity;
    private String remark;
    private List<OrderFoodEntity> orderFoodEntityList = new ArrayList<>();
    public boolean isBill = false;


    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerParishActivityComponent.builder()
                .appComponent(appComponent)
                .parishActivityModule(new ParishActivityModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_begin_order_dishes);
        binding.setViemModel(viewModel);
        EventBus.getDefault().register(this);
        initView();
        viewModel.getFoodList();
        viewModel.getFoodType();
    }

    private void initView() {
        foodToCart();
        sumPrice();
        if (orderFoodEntityList.size() > 0) {
            binding.tvOrdered.setVisibility(View.VISIBLE);
            binding.tvOrdered.setTextColor(getResources().getColor(color.colorc0c8ce));
            OrderFoodAdapter adapter = new OrderFoodAdapter(this, orderFoodEntityList);
            binding.rlvOrdered.setAdapter(adapter);
            binding.rlvOrdered.setLayoutManager(new LinearLayoutManager(this));
            binding.rlvOrdered.addItemDecoration(new RecyclerViewItemDecoration(5));
            binding.rlvOrdered.setVisibility(View.GONE);
        } else {
            binding.tvOrdered.setVisibility(View.GONE);
            binding.rlvOrdered.setVisibility(View.GONE);
        }
        if (isBill) {
            binding.llOrderDishes.setVisibility(View.GONE);
            binding.tvReturnBill.setVisibility(View.VISIBLE);
        } else {
            binding.llOrderDishes.setVisibility(View.VISIBLE);
            binding.tvReturnBill.setVisibility(View.GONE);
        }

        adapter = new OrderDishesVarietyAdapter(this, mList);
        binding.rlvVariety.setAdapter(adapter);
        binding.rlvVariety.setLayoutManager(new GridLayoutManager(this, 4));
        binding.rlvVariety.addItemDecoration(new RecyclerViewItemDecoration(5));
        adapter.setOnItemClick(new AdapterOnItemClick<FoodEntity>() {
            @Override
            public void onItemClick(FoodEntity foodEntity, int position) {
                final PopupWindowOrderDishesChoose OrderDishesChoose = new PopupWindowOrderDishesChoose(OrderDishesActivity.this, foodEntity);
                OrderDishesChoose.showPopupWindowUp(binding.llOrder);
                OrderDishesChoose.setOnCallBackListener(new PopupWindowOrderDishesChoose.OnCallBackListener() {
                    @Override
                    public void confirm(CartBean cartBean) {
                        OrderDishesChoose.dismiss();
                        cartBeanList.add(cartBean);
                        cartAdapter.notifyDataSetChanged();
                        sumPrice();
                    }
                });
            }
        });

        Drawable drawable1 = getResources().getDrawable(R.mipmap.search);
        drawable1.setBounds(25, 0, 45, 20);//第一0是距左边距离，第二0是距上边距离，40分别是长宽
        binding.etSearch.setCompoundDrawables(drawable1, null, null, null);//只放左边

        foodTypeAdapter = new FoodTypeAdapter(this, foodTypeEntityList);
        binding.rlvFoodType.setAdapter(foodTypeAdapter);
        binding.rlvFoodType.setLayoutManager(new LinearLayoutManager(this));
        foodTypeAdapter.setOnItemClick(new FoodTypeAdapter.OnItemClick() {
            @Override
            public void onItemClick(FoodTypeEntity entity, int position) {
                mList.clear();
                mList.addAll(entity.getFoodEntityList());
                adapter.notifyDataSetChanged();
            }
        });

        cartAdapter = new OrderDishesCartAdapter(this, cartBeanList);
        binding.rlvOrder.setAdapter(cartAdapter);
        binding.rlvOrder.setLayoutManager(new LinearLayoutManager(this));
        cartAdapter.setOnItemClick(new OrderDishesCartAdapter.OnItemClick() {
            @Override
            public void onItemClick(final CartBean entity, final int position) {
                final PopupWindowOrderDishesChange orderDishesChange = new PopupWindowOrderDishesChange(OrderDishesActivity.this, entity);
                orderDishesChange.showPopupWindowUp(binding.llOrder);
                orderDishesChange.setOnCallBackListener(new PopupWindowOrderDishesChange.OnCallBackListener() {
                    @Override
                    public void save(CartBean cartBean) {
                        cartBeanList.set(position, cartBean);
                        cartAdapter.notifyDataSetChanged();
                        sumPrice();
                    }

                    @Override
                    public void delete() {
                        cartBeanList.remove(entity);
                        cartAdapter.notifyDataSetChanged();
                        sumPrice();
                        if (cartBeanList.size() == 0) {
                            kouWeiEntity = null;
                            remark = "";
                        }
                    }
                });
            }
        });

        binding.tvCancel.setOnClickListener(this);
        binding.tvOrder.setOnClickListener(this);
        binding.ivBack.setOnClickListener(this);
        binding.ivMore.setOnClickListener(this);
        binding.tvOrdered.setOnClickListener(this);
        binding.tvShopCart.setOnClickListener(this);
    }

    private void foodToCart() {
        if (viewModel.mList.get() != null && !viewModel.mList.get().isEmpty()) {
            for (OrderFoodEntity entity : viewModel.mList.get()) {
                CartBean cartBean = new CartBean();
                cartBean.setGiveNum(String.valueOf(entity.getGiving()));
                cartBean.setFoodEntity(viewModel.getFoodEntity(entity.getProductName()));
                if (!TextUtils.isEmpty(entity.getProductShuXinId())) {
                    cartBean.setSpec(viewModel.getSpec(entity.getProductShuXinId()));
                }
                cartBean.setUnit(entity.getUnit());
                cartBean.setPrice(entity.getPrice());
                cartBean.setNum(entity.setNum());
                if (TextUtils.isEmpty(entity.getRemarkId())) {
                    cartBean.setKouwei(entity.getRemark());
                } else {
                    List<ProductKouWeiEntity> kouWeiEntities = new ArrayList<>();
                    String[] kouwei = entity.getRemark().split("\\*");
                    String[] kouweiId = entity.getRemarkId().split("\\*");
                    for (int j = 0; j < kouweiId.length; j++) {
                        if (TextUtils.isEmpty(kouweiId[j])) {
                            cartBean.setKouwei(kouwei[j]);
                        } else {
                            kouWeiEntities.add(viewModel.getKouWei(kouweiId[j]));
                        }
                    }
                    if (!kouWeiEntities.isEmpty()) {
                        cartBean.setProductKouWeiEntity(kouWeiEntities.get(0));
                    }
                }
                String[] seasonId = entity.getSeasonID().split("\\*");
                String[] seasonNum = entity.getSeasonNum().split("\\*");
                List<FoodAddBean> foodAddBeans = new ArrayList<>();
                for (int k = 0; k < seasonId.length; k++) {
                    if (!TextUtils.isEmpty(seasonId[k])) {
                        SeasonEntity seasonEntity = viewModel.getSeason(seasonId[k]);
                        FoodAddBean foodAddBean = new FoodAddBean(seasonEntity.getSeasonId(), seasonEntity.getName(), Double.valueOf(seasonEntity.getPrice()), Integer.valueOf(seasonNum[k]), true);
                        foodAddBeans.add(foodAddBean);
                    }
                }
                cartBean.setFoodAddBeanList(foodAddBeans);
                cartBeanList.add(cartBean);
            }
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_cancel:
                cartBeanList.clear();
                cartAdapter.notifyDataSetChanged();
                sumPrice();
                kouWeiEntity = null;
                remark = "";
                break;
            case R.id.tv_order:
                if (cartBeanList.size() == 0) {
                    MToast.showToast(this, "购物车是空的");
                } else if (!isBill){
                    String foodType = binding.tvOrdered.getVisibility() == View.GONE ? "0" : "1";
                    viewModel.order(getInfo(), foodType, "");
                }else {
                    viewModel.reBill(getInfo());
                }
                break;
            case R.id.iv_more:
                if (cartBeanList.size() == 0) {
                    MToast.showToast(this, "购物车是空的");
                } else if (binding.rlvOrder.getVisibility() == View.VISIBLE) {
                    viewModel.getAllKouwei();
                }
                break;
            case R.id.tv_shop_cart:
                binding.tvOrdered.setTextColor(getResources().getColor(R.color.colorc0c8ce));
                binding.tvShopCart.setTextColor(getResources().getColor(R.color.color23cac0));
                binding.rlvOrder.setVisibility(View.VISIBLE);
                binding.rlvOrdered.setVisibility(View.GONE);
                break;
            case R.id.tv_ordered:
                binding.tvOrdered.setTextColor(getResources().getColor(R.color.color23cac0));
                binding.tvShopCart.setTextColor(getResources().getColor(R.color.colorc0c8ce));
                binding.rlvOrder.setVisibility(View.GONE);
                binding.rlvOrdered.setVisibility(View.VISIBLE);
                break;

        }
    }


    public void intentToPay(List<OrderFoodEntity> orderFoodEntities){
        EventPayBean bean =new EventPayBean();
        bean.setFlag(2);
//            bean.setTableEntity(entity);
        bean.setmList(orderFoodEntities);
//        bean.setRoomName(viewModel.room.get());
        EventBus.getDefault().postSticky(DataEvent.make(AppConstant.EVENT_PAY,bean));
        Intent intent = new Intent(this, PayActivity.class);
        startActivity(intent);
        finish();
    }


    public void showPopAllKouwei(List<KouWeiEntity> list) {
        PopupWindowAllKouwei popupWindowAllKouwei = new PopupWindowAllKouwei(this, list, kouWeiEntity, remark);
        popupWindowAllKouwei.showPopupWindowUp();
        popupWindowAllKouwei.setOnCallBackListener(new PopupWindowAllKouwei.OnCallBackListener() {
            @Override
            public void confirm(KouWeiEntity entity, String re) {
                kouWeiEntity = entity;
                remark = re;
            }
        });
    }

    private void sumPrice() {
        double sum = 0;
        if (cartBeanList.size() > 0) {
            for (CartBean cartBean : cartBeanList) {
                sum = sum + Double.valueOf(cartBean.getNum()) * cartBean.getPrice();
                for (FoodAddBean foodAddBean : cartBean.getFoodAddBeanList()) {
                    sum = sum + foodAddBean.getNum() * foodAddBean.getPrice();
                }
            }
        }
        viewModel.price.set(sum);
    }

    private String getInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        if (cartBeanList.size() > 0) {
            for (int i = 0; i < cartBeanList.size(); i++) {
                CartBean bean = cartBeanList.get(i);
                double total = (Double.valueOf(bean.getNum()) - Double.valueOf(bean.getGiveNum())) * bean.getPrice();
                String kouwei = "";
                String kouweiId = "";
                if (bean.getProductKouWeiEntity() != null) {
                    kouwei = bean.getProductKouWeiEntity().getName() + "*";
                    kouweiId = bean.getProductKouWeiEntity().getuId() + "*";
                }
                if (!TextUtils.isEmpty(bean.getKouwei())) {
                    kouwei = kouwei + bean.getKouwei() + "*";
                    kouweiId = kouweiId + "" + "*";
                }
                if (kouWeiEntity != null) {
                    kouwei = kouwei + kouWeiEntity.getName() + "*";
                    kouweiId = kouweiId + kouWeiEntity.getGuId() + "*";
                }
                if (!TextUtils.isEmpty(remark)) {
                    kouwei = kouwei + bean.getKouwei() + "*";
                    kouweiId = kouweiId + "" + "*";
                }
                if (!TextUtils.isEmpty(kouwei)) {
                    kouwei = kouwei.substring(0, kouwei.length() - 1);
                }
                if (!TextUtils.isEmpty(kouweiId)) {
                    kouweiId = kouweiId.substring(0, kouweiId.length() - 1);
                }
                String addId = "";
                String addName = "";
                Double addPrice = 0.0;
                String addNum = "";
                String specName = "";
                String specId = "";
                String specPrice = "";
                for (int j = 0; j < bean.getFoodAddBeanList().size(); j++) {
                    if (j == bean.getFoodAddBeanList().size() - 1) {
                        addId = addId + bean.getFoodAddBeanList().get(j).getId();
                        addName = addName + bean.getFoodAddBeanList().get(j).getName() + "(" + (bean.getFoodAddBeanList().get(j).getNum() + "份）");
                        addPrice = addPrice + bean.getFoodAddBeanList().get(j).getPrice() * bean.getFoodAddBeanList().get(j).getNum();
                        addNum = addNum + bean.getFoodAddBeanList().get(j).getNum();
                    } else {
                        addId = addId + bean.getFoodAddBeanList().get(j).getId() + "*";
                        addName = addName + bean.getFoodAddBeanList().get(j).getName() + "(" + (bean.getFoodAddBeanList().get(j).getNum() + "份）") + "*";
                        addPrice = addPrice + bean.getFoodAddBeanList().get(j).getPrice() * bean.getFoodAddBeanList().get(j).getNum();
                        addNum = addNum + bean.getFoodAddBeanList().get(j).getNum() + "*";
                    }
                }
                if (bean.getSpec() != null) {
                    specName = bean.getSpec().getName();
                    specId = bean.getSpec().getUId();
                    specPrice = bean.getSpec().getPrice() + "";
                }
                if (bean.getFoodEntity().getType()) {
                    stringBuilder.append(bean.getFoodEntity().getPackageName());
                    stringBuilder.append("@");
                    stringBuilder.append(bean.getFoodEntity().getMemberPice());
                    stringBuilder.append("@");
                    stringBuilder.append("-1");
                    stringBuilder.append("@");
                    stringBuilder.append(bean.getFoodEntity().getPrice());
                    stringBuilder.append("@");
                    stringBuilder.append(bean.getUnit());
                    stringBuilder.append("$");
                    stringBuilder.append(bean.getFoodEntity().getProductId());
                    stringBuilder.append("$");
                    stringBuilder.append(0.0);
                    stringBuilder.append("$");
                    stringBuilder.append(bean.getNum());
                    stringBuilder.append("$");
                    stringBuilder.append(kouwei);
                    stringBuilder.append("$");
                    stringBuilder.append(kouweiId);
                    stringBuilder.append("$");
                    stringBuilder.append("1");
                    stringBuilder.append("$");
                    stringBuilder.append(bean.getPrice());
                    stringBuilder.append("$");
                    stringBuilder.append(bean.getFoodEntity().getProductName().replace(",", "^"));
                    stringBuilder.append("$");
                    stringBuilder.append(bean.getFoodEntity().getCounts().replace(",", "^"));
                    stringBuilder.append("$");
                    stringBuilder.append(bean.getGiveNum());
                    stringBuilder.append("$");
                    stringBuilder.append(addId);
                    stringBuilder.append("$");
                    stringBuilder.append(addName);
                    stringBuilder.append("$");
                    stringBuilder.append(addPrice);
                    stringBuilder.append("$");
                    stringBuilder.append(addNum);
                    stringBuilder.append(",");
                } else {
                    //        菜品名称@ 会员价格@  状态@ 单价$  菜品ID$ 总价$ 份数$  口味$  口味ID$  份数$ 总价$ 规格$ 规格ID
                    stringBuilder.append(bean.getFoodEntity().getProductName());
                    stringBuilder.append("@");
                    stringBuilder.append(bean.getFoodEntity().getMemberPice());
                    stringBuilder.append("@");
                    stringBuilder.append("0");
                    stringBuilder.append("@");
                    stringBuilder.append(bean.getFoodEntity().getPrice());
                    stringBuilder.append("@");
                    stringBuilder.append(bean.getUnit());
                    stringBuilder.append("$");
                    stringBuilder.append(bean.getFoodEntity().getProductId());
                    stringBuilder.append("$");
                    stringBuilder.append(total);
                    stringBuilder.append("$");
                    stringBuilder.append(bean.getNum());
                    stringBuilder.append("$");
                    stringBuilder.append(kouwei);
                    stringBuilder.append("$");
                    stringBuilder.append(kouweiId);
                    stringBuilder.append("$");
                    stringBuilder.append("1");
                    stringBuilder.append("$");
                    stringBuilder.append(bean.getPrice());
                    stringBuilder.append("$");
                    stringBuilder.append(specName);
                    stringBuilder.append("$");
                    stringBuilder.append(specId);
                    stringBuilder.append("$");
                    stringBuilder.append(bean.getGiveNum());
                    stringBuilder.append("$");
                    stringBuilder.append(addId);
                    stringBuilder.append("$");
                    stringBuilder.append(addName);
                    stringBuilder.append("$");
                    stringBuilder.append(addPrice);
                    stringBuilder.append("$");
                    stringBuilder.append(addNum);
                    stringBuilder.append(",");
                }
            }

        }
        String info = stringBuilder.toString();
        if (info.endsWith(",")) {
            info = info.substring(0, info.length() - 1);
        }
        return info;
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onDataEvent(DataEvent event) {
        if (event.getMessageTag() == AppConstant.EVENT_ORDER_DISHES) {
            isBill = false;
            EventOrderDishesEntity eventOrderDishesEntity = (EventOrderDishesEntity) event.getMessageData();
            viewModel.tableId.set(eventOrderDishesEntity.getTableId());
            viewModel.table.set(eventOrderDishesEntity.getTableName());
            viewModel.room.set(eventOrderDishesEntity.getRoomName());
            viewModel.people.set(eventOrderDishesEntity.getPeopleNum());
            viewModel.time.set(DateUtils.friendly_time(DateUtils.stringToDate(eventOrderDishesEntity.getTime())));
            viewModel.billId.set(eventOrderDishesEntity.getBillId());
            orderFoodEntityList = eventOrderDishesEntity.getOrderFoodEntityList();
        } else if (event.getMessageTag() == AppConstant.EVENT_RETURN_BILL) {
            isBill = true;
            EventReturnBean bean = (EventReturnBean) event.getMessageData();
            OrderEntity orderEntity = bean.getOrderEntity();
            viewModel.tableId.set(orderEntity.getTableId());
            viewModel.table.set(orderEntity.getTableName());
            viewModel.people.set(String.valueOf(orderEntity.getPersonCount()));
            viewModel.time.set(orderEntity.getRecordDate());
            viewModel.billId.set(orderEntity.getBillId());
            viewModel.mList.set(bean.getOrderFoodEntities());
        }
    }


    public void refreshVariety(List<FoodEntity> foodEntities) {
        mList.clear();
        mList.addAll(foodEntities);
        adapter.notifyDataSetChanged();
    }

    public void refreshFoodType(List<FoodTypeSelectEntity> foodTypeEntities) {
        foodTypeEntityList.clear();
        foodTypeEntityList.addAll(foodTypeEntities);
        foodTypeAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


}
