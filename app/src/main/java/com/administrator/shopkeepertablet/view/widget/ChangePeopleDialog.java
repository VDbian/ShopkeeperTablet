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
import com.administrator.shopkeepertablet.databinding.DialogChangePeopleBinding;



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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);
        setStyle(STYLE_NO_FRAME,R.style.Theme_AppCompat_Dialog);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View customView = LayoutInflater.from(getActivity()).inflate(
                R.layout.dialog_change_people, null);
        binding = DataBindingUtil.bind(customView);
        binding.etPeopleNum.setText(peopleNum);
        binding.etWareNum.setText(wareNum);
        binding.etPeopleNum.setSelection(binding.etPeopleNum.getText().toString().length());
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
                    onConfirmClick.confirm(binding.etPeopleNum.getText().toString().trim(),binding.etPeopleNum.getText().toString().trim());
                }
            }
        });
        return binding.getRoot();
    }

    public interface OnConfirmClick {
        void confirm(String peopleNum,String wareNum);
    }

}
