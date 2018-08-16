package com.administrator.shopkeepertablet.view.widget;

import android.app.DialogFragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.databinding.DialogDiscountBinding;
import com.administrator.shopkeepertablet.databinding.DialogPayMoneyBinding;
import com.administrator.shopkeepertablet.utils.MToast;


/**
 * Description:
 * Author CC
 * Time 2018/7/12
 */

public class PayMoneyDialog extends DialogFragment {

    private String title;
    private DialogPayMoneyBinding binding;
    private OnConfirmClick onConfirmClick;
    private Double money;


    public void setOnConfirmClick(OnConfirmClick onConfirmClick) {
        this.onConfirmClick = onConfirmClick;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
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
                R.layout.dialog_pay_money, null);
        binding = DataBindingUtil.bind(customView);
        binding.tvTitle.setText(title);
        binding.etMoney.setText(String.valueOf(money));

        binding.ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (onConfirmClick != null) {
                    onConfirmClick.confirm(0.0);
                }
            }
        });

        binding.tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = binding.etMoney.getText().toString().trim();
                Double aDouble = Double.valueOf(str);
                if (aDouble >= 0 && aDouble <= money) {
                    dismiss();
                    if (onConfirmClick != null) {
                        onConfirmClick.confirm(aDouble);
                    }
                } else {
                    MToast.showToast(getActivity(), "输入的金额不符合规范");
                }
            }
        });
        return binding.getRoot();
    }

    public interface OnConfirmClick {
        void confirm(Double money);
    }

}
