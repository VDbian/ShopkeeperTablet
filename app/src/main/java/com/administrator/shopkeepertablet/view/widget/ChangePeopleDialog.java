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
import com.administrator.shopkeepertablet.databinding.DilogOrderDishesBinding;
import com.administrator.shopkeepertablet.model.entity.FoodEntity;



/**
 * Description:
 * Author CC
 * Time 2018/7/12
 */

public class ChangePeopleDialog extends DialogFragment {

    private String peopleNum;
    private String wareNum;
    private DialogChangePeopleBinding binding;
    private OnConfirmClick onConfirmClick;


    public void setOnConfirmClick(OnConfirmClick onConfirmClick) {
        this.onConfirmClick = onConfirmClick;
    }

    public void setPeopleNum(String peopleNum) {
        this.peopleNum = peopleNum;
    }

    public void setWareNum(String wareNum) {
        this.wareNum = wareNum;
    }

    public String getPeopleNum() {
        return peopleNum;
    }

    public String getWareNum() {
        return wareNum;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        binding = DataBindingUtil.setContentView(getActivity(),R.layout.layout_confirm_info);
        View customView = LayoutInflater.from(getActivity()).inflate(
                R.layout.dialog_change_people, null);
        binding = DataBindingUtil.bind(customView);
        binding.etPeopleNum.setText(peopleNum);
        binding.etWareNum.setText(wareNum);

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
                    onConfirmClick.confirm(binding.etPeopleNum.getText().toString().trim(),binding.etWareNum.getText().toString().trim());
                }
            }
        });
        return dialog;
    }

    public interface OnConfirmClick {
        void confirm(String peopleNum,String wareNum);
    }

}
