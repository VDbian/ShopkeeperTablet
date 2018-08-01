package com.administrator.shopkeepertablet.view.widget;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.databinding.DialogChangePeopleBinding;
import com.administrator.shopkeepertablet.databinding.DialogNewMemberBinding;
import com.administrator.shopkeepertablet.utils.MToast;


/**
 * Description:
 * Author CC
 * Time 2018/7/12
 */

public class NewMemberDialog extends DialogFragment {

    private DialogNewMemberBinding binding;
    private OnConfirmClick onConfirmClick;


    public void setOnConfirmClick(OnConfirmClick onConfirmClick) {
        this.onConfirmClick = onConfirmClick;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        binding = DataBindingUtil.setContentView(getActivity(),R.layout.layout_confirm_info);
        View customView = LayoutInflater.from(getActivity()).inflate(
                R.layout.dialog_new_member, null);
        binding = DataBindingUtil.bind(customView);

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
                if (!TextUtils.isEmpty(binding.etName.getText().toString().trim())&&!TextUtils.isEmpty(binding.etName.getText().toString().trim()))
                {
                    dismiss();
                    if (onConfirmClick != null) {
                        onConfirmClick.confirm(binding.etName.getText().toString().trim(), binding.etPhone.getText().toString().trim());
                    }
                }else {
                    MToast.showToast(getActivity(),"姓名或电话未填");
                }
            }
        });
        return dialog;
    }

    public interface OnConfirmClick {
        void confirm(String name, String phone);
    }

}
