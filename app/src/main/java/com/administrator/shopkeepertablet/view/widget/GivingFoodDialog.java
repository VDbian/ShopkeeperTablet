package com.administrator.shopkeepertablet.view.widget;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.administrator.shopkeepertablet.R;
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
    private int givingNum;


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

    public int getGivingNum() {
        return givingNum;
    }

    public void setGivingNum(int givingNum) {
        this.givingNum = givingNum;
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
        View customView = LayoutInflater.from(getActivity()).inflate(
                R.layout.dialog_giving_food, null);
        binding = DataBindingUtil.bind(customView);
        binding.tvTitle.setText(Html.fromHtml(title));
        binding.tvNum.setText(String.valueOf(givingNum));
        binding.rlPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = Integer.parseInt(binding.tvNum.getText().toString().trim());
                if (num + 1 <= foodNum) {
                    num = num + 1;
                    binding.tvNum.setText(String.valueOf(num));
                }
            }
        });

        binding.rlReduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = Integer.parseInt(binding.tvNum.getText().toString().trim());
                if (num - 1 >= 0) {
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
        return binding.getRoot();
    }

    public interface OnConfirmClick {
        void confirm(String giving);
    }

}
