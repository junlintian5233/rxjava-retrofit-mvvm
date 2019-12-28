package com.junzhitian.rxjavaretrofitmvvm.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.junzhitian.rxjavaretrofitmvvm.CommonApp;


/**
 * Toast 工具类
 */

public class ToastUtils {

    /**
     * 短时间显示Toast
     *
     * @param message
     */
    public static void showShort(CharSequence message) {
        if (TextUtils.isEmpty(message)) {
            message = "";
        }
        Toast.makeText(CommonApp.getInstance().getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 短时间显示Toast
     *
     * @param resId
     */
    public static void showShort(int resId) {
        Context context = CommonApp.getInstance().getApplicationContext();
        String  msg     = context.getString(resId);
        Toast.makeText(CommonApp.getInstance(), msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 长时间显示Toast
     *
     * @param message
     */
    public static void showLong(CharSequence message) {
        if (TextUtils.isEmpty(message)) {
            message = "";
        }
        Toast.makeText(CommonApp.getInstance(), message, Toast.LENGTH_LONG).show();
    }

    /**
     * 长时间显示Toast
     *
     * @param message
     */
    public static void showLong(int message) {
        Toast.makeText(CommonApp.getInstance(), message, Toast.LENGTH_LONG).show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param message
     * @param duration
     */
    public static void show(CharSequence message, int duration) {
        if (TextUtils.isEmpty(message)) {
            message = "";
        }
        Toast.makeText(CommonApp.getInstance(), message, duration).show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param message
     * @param duration
     */
    public static void show(int message, int duration) {
        Toast.makeText(CommonApp.getInstance(), message, duration).show();
    }

}
