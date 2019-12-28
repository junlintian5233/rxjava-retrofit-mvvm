package com.junzhitian.basicapp.common;

import android.content.Context;

import com.junzhitian.basicapp.utils.FileUtils;
import com.junzhitian.basicapp.utils.LogUtils;
import com.junzhitian.basicapp.utils.TimeUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private Context mContext;

    private static CrashHandler instance = null;

    private CrashHandler(Context cxt) {
        Thread.setDefaultUncaughtExceptionHandler(this);
        this.mContext = cxt.getApplicationContext();
    }

    public static synchronized CrashHandler create(Context cxt) {
        if (instance == null) {
            instance = new CrashHandler(cxt);
        }
        return instance;
    }


    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        try {
            saveToSDCard(ex);
            /*Intent intent = new Intent(YZApplication.getInstance(), LoginActivity.class);
            PendingIntent restartIntent = PendingIntent.getActivity(YZApplication.getInstance().getApplicationContext(),
                    0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
            // 1秒钟后重启应用
            AlarmManager mgr = (AlarmManager) YZApplication.getInstance().getSystemService(Context.ALARM_SERVICE);
            mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1000, restartIntent);*/
            //退出程序
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveToSDCard(Throwable ex) throws Exception {
        File        file = FileUtils.getSaveFile(mContext.getExternalCacheDir() + this.mContext.getPackageName() + File.separator + "log", TimeUtils.getDateTime("yyyy-MM-dd-HH-mm-ss") + ".log");
        PrintWriter pw   = new PrintWriter(new BufferedWriter(new FileWriter(file)));

        LogUtils.e("CrashHandler", "Exception: " + ex.getMessage() + "       " + ex.getLocalizedMessage());
        pw.println(TimeUtils.getDateTime("yyyy-MM-dd-HH-mm-ss"));
        pw.println();
        ex.printStackTrace(pw);
        pw.flush();
        pw.close();
    }

}