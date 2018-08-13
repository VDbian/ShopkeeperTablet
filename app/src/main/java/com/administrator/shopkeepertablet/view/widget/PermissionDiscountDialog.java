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
import com.administrator.shopkeepertablet.databinding.DialogPermissionDiscountBinding;
import com.administrator.shopkeepertablet.databinding.ViewOrderDishesGiveBinding;
import com.administrator.shopkeepertablet.model.entity.DiscountEntity;
import com.administrator.shopkeepertablet.view.ui.adapter.DiscountAdapter;
import com.administrator.shopkeepertablet.view.ui.adapter.base.AdapterOnItemClick;
import com.administrator.shopkeepertablet.viewmodel.PayViewModel;

import java.util.ArrayList;
import java.util.List;


/**
 * Description:
 * Author CC
 * Time 2018/7/12
 */

public class PermissionDiscountDialog extends DialogFragment {

    private DialogPermissionDiscountBinding binding;
    private OnConfirmClick onConfirmClick;
    private PayViewModel viewModel;
    private List<DiscountEntity> discountEntityList = new ArrayList<>();
    private  DiscountAdapter adapter;

    public void setOnConfirmClick(OnConfirmClick onConfirmClick) {
        this.onConfirmClick = onConfirmClick;
    }

    public PayViewModel getViewModel() {
        return viewModel;
    }

    public void setViewModel(PayViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void notifyData(){
        discountEntityList.clear();
        discountEntityList.addAll(viewModel.discountList.get());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);
        setStyle(STYLE_NO_FRAME, R.style.Theme_AppCompat_Dialog);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//        binding = DataBindingUtil.setContentView(getActivity(),R.layout.layout_confirm_info);
        View customView = LayoutInflater.from(getActivity()).inflate(
                R.layout.dialog_permission_discount, null);
        binding = DataBindingUtil.bind(customView);
        binding.setViewModel(viewModel);
        binding.ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        adapter = new DiscountAdapter(getActivity(), viewModel.discountList.get());
        binding.rlvDiscount.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        binding.rlvDiscount.setAdapter(adapter);
        binding.rlvDiscount.addItemDecoration(new RecyclerViewItemDecoration(5));
        adapter.setOnItemClick(new AdapterOnItemClick<DiscountEntity>() {
            @Override
            public void onItemClick(DiscountEntity discountEntity, int position) {
                dismiss();
                if (onConfirmClick != null) {
                    onConfirmClick.itemClick(discountEntity);
                }
            }
        });

        binding.tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (onConfirmClick != null) {
                    onConfirmClick.confirm();
                }
            }
        });
        return binding.getRoot();
    }

    public interface OnConfirmClick {
        void confirm();

        void itemClick(DiscountEntity discountEntity);
    }

}
