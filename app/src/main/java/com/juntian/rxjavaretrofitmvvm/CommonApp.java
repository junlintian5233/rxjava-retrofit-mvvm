package com.juntian.rxjavaretrofitmvvm;

import androidx.multidex.MultiDexApplication;

import com.juntian.basicapp.utils.SpUtils;
import com.tencent.bugly.crashreport.CrashReport;


public class CommonApp extends MultiDexApplication {

    private static CommonApp application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        CrashReport.initCrashReport(getApplicationContext(), "1a8ee79f26", true);
        SpUtils.getInstance().init(this);
//        IBoxingMediaLoader loader = new BoxingGlideLoader();
//        BoxingMediaLoader.getInstance().init(loader);
//        BoxingCrop.getInstance().init(new BoxingUcrop());
    }
    public static CommonApp getInstance() {
        return application;
    }
}
