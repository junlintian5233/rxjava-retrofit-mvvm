package com.junzhitian.basicapp.utils;

import android.app.Activity;
import android.widget.TextView;


import com.junzhitian.basicapp.config.Config;
import com.junzhitian.basicapp.R;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @作者:TJ
 * @时间:2019/8/22
 * @描述:
 */
public class VertifyCodeUtil {

    private        int                         mCountDown = Config.COUNTDOWN_TIME;
    private        ScheduledThreadPoolExecutor mThreadPoolExecutor;
    private static VertifyCodeUtil             instance;

    private Activity mActivity;
    private TextView mCodeButton;

    private VertifyCodeUtil() {
    }

    public static VertifyCodeUtil getInstance() {
        if (instance == null) {
            instance = new VertifyCodeUtil();
        }
        return instance;
    }


    public VertifyCodeUtil setActivity(Activity activity) {
        mActivity = activity;
        return this;
    }

    public VertifyCodeUtil setCodeBtn(TextView button) {
        mCodeButton = button;
        return this;
    }

    /**
     * 开始倒计时
     */
    public void startCountdown() {
        //初始化一个线程池大小为 1 的 ScheduledExecutorService
        if (mThreadPoolExecutor == null || mThreadPoolExecutor.isShutdown()) {
            mThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
        }
        mCodeButton.setEnabled(false);
        mThreadPoolExecutor.scheduleAtFixedRate(new CountDwonThread(), 0, 1, TimeUnit.SECONDS);
    }


    /**
     * 倒计时处理
     */
    class CountDwonThread implements Runnable {

        @Override
        public void run() {
            mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (mCountDown > 0) {
                        mCodeButton.setText(mCountDown + "秒");
                        mCountDown--;
                    } else {
                        mCodeButton.setEnabled(true);
                        mCodeButton.setText(R.string.get_code);
                        mCountDown = Config.COUNTDOWN_TIME;
                        mThreadPoolExecutor.shutdown();
                    }
                }
            });
        }
    }
}
