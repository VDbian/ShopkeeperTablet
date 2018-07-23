package com.administrator.shopkeepertablet.view.widget;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.databinding.DialogChangePeopleBinding;
import com.administrator.shopkeepertablet.databinding.DialogDiscountBinding;


/**
 * Description:
 * Author CC
 * Time 2018/7/12
 */

public class DiscountDialog extends DialogFragment {

    private String title;
    private DialogDiscountBinding binding;
    private OnConfirmClick onConfirmClick;


    public void setOnConfirmClick(OnConfirmClick onConfirmClick) {
        this.onConfirmClick = onConfirmClick;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        binding = DataBindingUtil.setContentView(getActivity(),R.layout.layout_confirm_info);
        View customView = LayoutInflater.from(getActivity()).inflate(
                R.layout.dialog_discount, null);
        binding = DataBindingUtil.bind(customView);
        binding.tvTitle.setText(title);

        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).setView(binding.getRoot()).create();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

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
                    onConfirmClick.confirm(binding.etDiscountNum.getText().toString().trim());
                }
            }
        });
        return dialog;
    }

    public interface OnConfirmClick {
        void confirm(String discount);
    }

}
