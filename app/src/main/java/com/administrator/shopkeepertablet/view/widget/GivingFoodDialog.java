package com.administrator.shopkeepertablet.view.widget;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.databinding.DialogChangePeopleBinding;
import com.administrator.shopkeepertablet.databinding.DialogGivingFoodBinding;


/**
 * Description:
 * Author CC
 * Time 2018/7/12
 */

public class GivingFoodDialog extends DialogFragment {

    private DialogGivingFoodBinding binding;
    private OnConfirmClick onConfirmClick;
    private String title;
    private double foodNum;


    public void setOnConfirmClick(OnConfirmClick onConfirmClick) {
        this.onConfirmClick = onConfirmClick;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = String.format("赠送 <font color=\"#FBBC05\">（%s）", title);
    }

    public double getFoodNum() {
        return foodNum;
    }

    public void setFoodNum(double foodNum) {
        this.foodNum = foodNum;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        binding = DataBindingUtil.setContentView(getActivity(),R.layout.layout_confirm_info);
        View customView = LayoutInflater.from(getActivity()).inflate(
                R.layout.dialog_giving_food, null);
        binding = DataBindingUtil.bind(customView);
        binding.tvTitle.setText(Html.fromHtml(title));
        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).setView(binding.getRoot()).create();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        binding.rlPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double num = Double.valueOf(binding.tvNum.getText().toString().trim());
                if (num + 1 <= foodNum) {
                    num = num + 1;
                    binding.tvNum.setText(String.valueOf(num));
                }
            }
        });

        binding.rlReduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double num =  Double.valueOf(binding.tvNum.getText().toString().trim());
                if (num - 1 > 0) {
                    num -= 1;
                    binding.tvNum.setText(String.valueOf(num));
                }
            }
        });

        binding.tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (onConfirmClick != null) {
                    onConfirmClick.confirm(binding.tvNum.getText().toString().trim());
                }
            }
        });
        return dialog;
    }

    public interface OnConfirmClick {
        void confirm(String giving);
    }

}
