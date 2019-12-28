package com.junzhitian.basicapp.utils;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @作者:TJ
 * @时间:2018/10/10-16:25
 * @描述:线程池管理
 */
public class ThreadPoolManager {

    private static final ThreadPoolManager manager = new ThreadPoolManager();

    private ScheduledExecutorService mHeartBeatService;
    private ScheduledExecutorService mDelayService;



    public static ThreadPoolManager getInstance() {
        return manager;
    }


    /**
     * 开启一个固定频率持续动作线程:心跳发送
     *
     * @param runnable
     * @param delay
     * @param period
     */
    public void startHeartBeatThread(Runnable runnable, long delay, long period) {
        if (mHeartBeatService == null || mHeartBeatService.isShutdown()) {
            mHeartBeatService = Executors.newSingleThreadScheduledExecutor();
            mHeartBeatService.scheduleAtFixedRate(runnable, delay, period, TimeUnit.SECONDS);
        }
    }

    public void stopHeartBeatThread() {
        if (mHeartBeatService != null && !mHeartBeatService.isShutdown()) {
            mHeartBeatService.shutdownNow();
            LogUtils.e("ThreadPoolManager", "stopHeartBeatThread: shutdownNow");
        }
    }


    /**
     * 开启一个延迟线程1
     *
     * @param runnable
     * @param delay
     */
    public void startDelayThread(Runnable runnable, long delay) {
        mDelayService = Executors.newSingleThreadScheduledExecutor();
        mDelayService.schedule(runnable, delay, TimeUnit.MILLISECONDS);
    }

    public void stopDelayThread() {
        if (mDelayService != null && !mDelayService.isShutdown()) {
            mDelayService.shutdownNow();
            LogUtils.e("ThreadPoolManager", "stopDelayThread: shutdown");
        }
    }

}
