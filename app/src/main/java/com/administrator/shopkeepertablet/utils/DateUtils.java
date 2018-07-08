package com.administrator.shopkeepertablet.utils;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

/**
 * Description:
 * Author CC
 * Time 2018/7/7
 */


public class DateUtils {
    public static boolean isSameDay(Date date1, Date date2) {
        return formatDateOnly(date1).equals(formatDateOnly(date2));
    }

    public static String formatDateOnly(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }
    public static String formatDate(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    public static Date stringToDate(String strTime) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = formatter.parse(strTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String friendly_time(Date date) {
        if (date == null) {
            return "未知";
        } else {
            Calendar calendar = Calendar.getInstance();
            Date curTime = calendar.getTime();
            String time = "";
            /**
             * 判断传入时间和当前时间是否是同一天
             */
            if (isSameDay(date, curTime)) {
                int hour = (int) ((curTime.getTime() - date.getTime()) / 3600000);
                if (hour == 0)
                    time = Math.max(
                            (curTime.getTime() - date.getTime()) / 60000, 1)
                            + "m";
                else
                    time = hour + "h";
                return time;
            }

            long lt = date.getTime() / 86400000;
            long ct = curTime.getTime() / 86400000;
            int days = (int) (ct - lt);
            if (days == 0) {
                int hour = (int) ((curTime.getTime() - date.getTime()) / 3600000);
                if (hour == 0)
                    time = Math.max(
                            (curTime.getTime() - date.getTime()) / 60000, 1)
                            + "m";
                else
                    time = hour + "h";
            } else if (days == 1) {
                time = "昨天";
            } else if (days == 2) {
                time = "前天";
            } else if (days > 2 && days < 31) {
                time = days + "天前";
            } else if (days >= 31 && days <= 2 * 31) {
                time = "一个月前";
            } else if (days > 2 * 31 && days <= 3 * 31) {
                time = "2个月前";
            } else if (days > 3 * 31 && days <= 4 * 31) {
                time = "3个月前";
            } else {
                time =formatDateOnly(date);
            }

            return time;
        }
    }

    public static String getCurrentDate() {
        SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sFormat.format(Calendar.getInstance().getTime());
    }
}
