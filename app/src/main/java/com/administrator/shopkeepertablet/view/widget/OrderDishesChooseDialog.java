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
import com.administrator.shopkeepertablet.databinding.DilogOrderDishesBinding;
import com.administrator.shopkeepertablet.model.entity.FoodEntity;



/**
 * Description:
 * Author CC
 * Time 2018/7/12
 */

public class OrderDishesChooseDialog extends DialogFragment {

    private FoodEntity entity;
    private DilogOrderDishesBinding binding;
    private OnConfirmClick onConfirmClick;

    public void setEntity(FoodEntity entity) {
        this.entity = entity;
    }

    public void setOnConfirmClick(OnConfirmClick onConfirmClick) {
        this.onConfirmClick = onConfirmClick;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        binding = DataBindingUtil.setContentView(getActivity(),R.layout.layout_confirm_info);
        View customView = LayoutInflater.from(getActivity()).inflate(
                R.layout.dilog_order_dishes, null);
        binding = DataBindingUtil.bind(customView);



        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).setView(binding.getRoot()).create();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确认",
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                    if (onConfirmClick!=null){
//                        onConfirmClick.confirm();
//                    }
//                    }
//                });
//        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "去修改",
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        //取消什么也不用做
//                        if (onConfirmClick!=null){
//                            onConfirmClick.update();
//                        }
//                    }
//                });
        binding.ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        binding.tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onConfirmClick != null) {
                    onConfirmClick.confirm();
                }
            }
        });
        return dialog;
    }

    public interface OnConfirmClick {
        void confirm();

        void update();
    }

}
