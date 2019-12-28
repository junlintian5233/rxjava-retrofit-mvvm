package com.junzhitian.basicapp.utils;

import android.app.ActivityManager;
import android.content.Context;

/**
 * @作者:TJ
 * @时间:2019/7/25 16:09
 * @描述:
 */
public class Tools {

    /**
     * 判断空
     *
     * @param s
     * @return
     */
    public static boolean isEmpty(String s)  {
        return s == null || s.isEmpty();
    }

    /**
     * 判断非空
     *
     * @param s
     * @return
     */
    public static boolean isNotEmpty(String s) {
        return s != null && !s.isEmpty();
    }

    /**
     * 获取当前进程名字
     *
     * @param context
     * @return
     */
    public static String getCurProcessName(Context context) {
        int             pid              = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) { return appProcess.processName; }
        }
        return "";
    }

}
