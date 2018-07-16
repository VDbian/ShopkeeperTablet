package com.administrator.shopkeepertablet.view.widget;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Window;

/**
 * Description:
 * Author chencheng
 * Time 2018/7/15
 */

public class ConfirmDialog extends DialogFragment {

    private OnDialogSure onDialogSure;
    private String title;
    private String message;

    public void setOnDialogSure(OnDialogSure onDialogSure) {
        this.onDialogSure = onDialogSure;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确认",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                        if (onDialogSure != null) {
                            onDialogSure.confirm();
                        }
                    }
                });
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //取消什么也不用做
                        dismiss();
                        if (onDialogSure != null) {
                            onDialogSure.cancel();
                        }
                    }
                });
        return dialog;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public interface OnDialogSure {
        void confirm();

        void cancel();
    }
}