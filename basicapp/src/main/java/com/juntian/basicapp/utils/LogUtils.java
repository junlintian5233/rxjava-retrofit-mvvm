package com.juntian.basicapp.utils;

import android.text.TextUtils;
import android.util.Log;


/**
 * @作者:TJ
 * @时间:2018/7/24 9:59
 * @描述:日志管理
 */
public class LogUtils {

    public static final int     VERBOSE  = 1;
    public static final int     DEBUG    = 2;
    public static final int     INFO     = 3;
    public static final int     WARN     = 4;
    public static final int     ERROR    = 5;
    public static final boolean IS_DEBUG = true;

    public static void v(String TAG, String msg) {
        if (IS_DEBUG && !TextUtils.isEmpty(msg)) {
            log(VERBOSE, TAG, msg);
        }
    }

    public static void d(String TAG, String msg) {
        if (IS_DEBUG && !TextUtils.isEmpty(msg)) {
            log(DEBUG, TAG, msg);
        }
    }

    public static void i(String TAG, String msg) {
        if (IS_DEBUG && !TextUtils.isEmpty(msg)) {
            log(INFO, TAG, msg);
        }
    }

    public static void w(String TAG, String msg) {
        if (IS_DEBUG && !TextUtils.isEmpty(msg)) {
            log(WARN, TAG, msg);
        }
    }

    public static void e(String TAG, String msg) {
        if (IS_DEBUG && !TextUtils.isEmpty(msg)) {
            log(ERROR, TAG, msg);
        }
    }


    private static void log(int type, String TAG, String msg) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        int    index      = 4;
        String className  = stackTrace[index].getFileName();
        String methodName = stackTrace[index].getMethodName();
        int    lineNumber = stackTrace[index].getLineNumber();

//        methodName = methodName.substring(0, 1).toUpperCase() + methodName.substring(1);

        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("[ (").append(className).append(":").append(lineNumber).append(")#").append(methodName).append(" ] ");
        stringBuilder.append("(").append(className).append(":").append(lineNumber).append(") ");
        stringBuilder.append(msg);
        String logStr = stringBuilder.toString();
        switch (type) {
            case VERBOSE:
                Log.v(TAG, logStr);
                break;
            case DEBUG:
                Log.d(TAG, logStr);
                break;
            case INFO:
                Log.i(TAG, logStr);
                break;
            case WARN:
                Log.w(TAG, logStr);
                break;
            case ERROR:
                Log.e(TAG, logStr);
                break;
            default:
                break;
        }
    }
}
