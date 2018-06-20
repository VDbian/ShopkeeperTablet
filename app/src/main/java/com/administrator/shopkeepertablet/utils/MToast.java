package com.administrator.shopkeepertablet.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Description:
 * Author CC
 * Time 2018/6/11
 */
public class MToast {
    private static Toast mToast = null;

    public static void showToast(Context context, String text) {
        if (mToast == null) {
            mToast = Toast.makeText(context.getApplicationContext(), text, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }
}
