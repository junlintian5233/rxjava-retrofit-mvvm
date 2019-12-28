package com.juntian.basicapp.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * @作者:TJ
 * @时间:2019/10/30
 * @描述:
 */
public class IntentUtils {
    /**
     * 调用拨号界面
     *
     * @param context
     * @param phone
     */
    public static void invokeDial(Context context, String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 调用拨号功能
     *
     * @param context
     * @param phone
     */
    public static void invokeCall(Context context, String phone) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
        context.startActivity(intent);
    }
}
