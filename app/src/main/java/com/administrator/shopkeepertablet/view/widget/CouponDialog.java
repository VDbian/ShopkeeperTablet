package com.administrator.shopkeepertablet.view.widget;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.databinding.DialogCouponBinding;
import com.administrator.shopkeepertablet.viewmodel.PayViewModel;


/**
 * Description:
 * Author CC
 * Time 2018/7/12
 */

public class CouponDialog extends DialogFragment {

    private DialogCouponBinding binding;
    private OnConfirmClick onConfirmClick;
    private PayViewModel viewModel;


    public void setOnConfirmClick(OnConfirmClick onConfirmClick) {
        this.onConfirmClick = onConfirmClick;
    }

    public PayViewModel getViewModel() {
        return viewModel;
    }

    public void setViewModel(PayViewModel viewModel) {
        this.viewModel = viewModel;
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
//        return super.onCreateView(inflater, container, savedInstanceState);
        View customView = LayoutInflater.from(getActivity()).inflate(
                R.layout.dialog_coupon, null);
        binding = DataBindingUtil.bind(customView);
        binding.setViewModel(viewModel);
        if (viewModel.cardSearch.get() != null) {
            binding.llCoupon.setVisibility(View.VISIBLE);
        } else {
            binding.llCoupon.setVisibility(View.INVISIBLE);
        }
//        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).setView(customView).create();
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        binding.tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.getDiscount(viewModel.couponNum.get());
                if (viewModel.cardSearch.get() != null) {
                    binding.llCoupon.setVisibility(View.VISIBLE);
                } else {
                    binding.llCoupon.setVisibility(View.INVISIBLE);
                }
            }
        });

        binding.ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
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
    }

}
