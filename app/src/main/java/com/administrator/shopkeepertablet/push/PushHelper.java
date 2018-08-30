package com.administrator.shopkeepertablet.push;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.administrator.shopkeepertablet.AppApplication;
import com.administrator.shopkeepertablet.R;
import com.administrator.shopkeepertablet.repository.BaseRepertory;
import com.administrator.shopkeepertablet.repository.BaseRepertoryImpl;
import com.administrator.shopkeepertablet.utils.NotifyUtils;
import com.administrator.shopkeepertablet.utils.Print;
import com.administrator.shopkeepertablet.view.ui.activity.MainActivity;
import com.umeng.message.entity.UMessage;

import org.json.JSONObject;


public class PushHelper {

    public static void notify(Context context, UMessage msg,BaseRepertory baseRepertory) {

        JSONObject jsonObject = new JSONObject(msg.extra);

        String type = jsonObject.optString("TYPE");

        if (type.equals("0")) {
            Intent intent = new Intent(context, MainActivity.class);

            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            PendingIntent pIntent = PendingIntent.getActivity(context,
                    1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            notify(pIntent, 1, context, R.mipmap.ic_launcher, msg.title, msg.text, msg.ticker);
        } else if (type.equals("1")) {
            new Thread(() -> {
                try {
                    JSONObject jsonObject1 = new JSONObject(msg.extra);
                    new Print(baseRepertory).socketDataArrivalHandler(jsonObject1.optString("KEY"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }


    }


    private static void notify(PendingIntent pIntent, int type, Context mContext, int icon, String title, String content, String ticker) {

        //实例化工具类，并且调用接口
        NotifyUtils notify = new NotifyUtils(mContext, type);
        notify.notify_normal_singline(pIntent, icon, ticker, title, content, false, true, true);
    }

}