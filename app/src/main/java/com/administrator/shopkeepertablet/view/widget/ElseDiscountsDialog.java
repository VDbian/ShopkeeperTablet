package com.administrator.shopkeepertablet.view.widget;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.databinding.DialogElseDiscountsBinding;
import com.administrator.shopkeepertablet.model.entity.DiscountEntity;
import com.administrator.shopkeepertablet.model.entity.ElseCouponEntity;
import com.administrator.shopkeepertablet.view.ui.adapter.DiscountAdapter;
import com.administrator.shopkeepertablet.view.ui.adapter.ElseCouponAdapter;
import com.administrator.shopkeepertablet.view.ui.adapter.base.AdapterOnItemClick;
import com.administrator.shopkeepertablet.viewmodel.PayViewModel;

import java.util.ArrayList;
import java.util.List;


/**
 * Description:
 * Author CC
 * Time 2018/7/12
 */

public class ElseDiscountsDialog extends DialogFragment {

    private DialogElseDiscountsBinding binding;
    private OnConfirmClick onConfirmClick;
    private PayViewModel viewModel;
    private ElseCouponAdapter adapter;
    private List<ElseCouponEntity> entityList =new ArrayList<>();

    public void setOnConfirmClick(OnConfirmClick onConfirmClick) {
        this.onConfirmClick = onConfirmClick;
    }

    public PayViewModel getViewModel() {
        return viewModel;
    }

    public void setViewModel(PayViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public List<ElseCouponEntity> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<ElseCouponEntity> entityList) {
        this.entityList = entityList;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);
        setStyle(STYLE_NO_FRAME,R.style.Theme_AppCompat_Dialog);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View customView = LayoutInflater.from(getActivity()).inflate(
                R.layout.dialog_else_discounts, null);
        binding = DataBindingUtil.bind(customView);

        binding.ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        adapter = new ElseCouponAdapter(getActivity(), entityList);
        binding.rlvElseDiscount.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        binding.rlvElseDiscount.setAdapter(adapter);
        binding.rlvElseDiscount.addItemDecoration(new RecyclerViewItemDecoration(5));


        binding.tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                List<ElseCouponEntity> mList = new ArrayList<>();
                for (ElseCouponEntity entity:entityList){
                    if (entity.isSelect()){
                        mList.add(entity);
                    }
                }
                if (onConfirmClick != null) {
                    onConfirmClick.confirm(mList);
                }
            }
        });
        return binding.getRoot();
    }

    public interface OnConfirmClick {
        void confirm(List<ElseCouponEntity> mList);
    }

}
