package com.juntian.rxjavaretrofitmvvm.common;

import android.content.Context;
import android.text.TextUtils;

import com.juntian.rxjavaretrofitmvvm.CommonApp;
import com.juntian.rxjavaretrofitmvvm.R;
import com.juntian.rxjavaretrofitmvvm.constant.Constants;
import com.juntian.rxjavaretrofitmvvm.model.bean.ApiException;
import com.juntian.rxjavaretrofitmvvm.model.bean.UserInfo;
import com.juntian.rxjavaretrofitmvvm.utils.ToastUtils;
import com.juntian.basicapp.utils.LogUtils;
import com.juntian.basicapp.utils.SpUtils;

/**
 * @author : TJ
 * @date : 2017/9/18 19:54
 * @description :返回异常状态处理
 */

public class ApiManager {

    private        Context    mContext;
    private static ApiManager instance;


    public ApiManager() {
        mContext = CommonApp.getInstance().getApplicationContext();
    }

    public static ApiManager getInstance() {
        if (instance == null) {
            instance = new ApiManager();
        }
        return instance;
    }


    /**
     * 处理api异常
     *
     * @param e
     */
    public void handleApiException(ApiException e) {
        ApiException apiException = e;
        ToastUtils.showShort(apiException.getMsg());
        LogUtils.e("ApiManager", "handleApiException->----ApiException:" + apiException.getMsg() + "  当前线程：" + Thread.currentThread().getName());
    }


    /**
     * 弹出错误信息
     *
     * @param error
     */
    public void showErrorToast(String error) {
        if (error == null) {
            ToastUtils.showShort(R.string.network_exception);
            return;
        }
        if (!TextUtils.isEmpty(error)) {
            ToastUtils.showShort(error);
        }
    }

    public void showErrorToast(int strRes) {
        ToastUtils.showShort(strRes);
    }


    public void exitLogin() {
        SpUtils.getInstance().putString(Constants.TOKEN, "");
        SpUtils.getInstance().putBean(Constants.USERINFO, new UserInfo());
    }
}
