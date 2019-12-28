package com.junzhitian.rxjavaretrofitmvvm.http;


import com.junzhitian.rxjavaretrofitmvvm.R;
import com.junzhitian.rxjavaretrofitmvvm.common.ApiManager;
import com.junzhitian.rxjavaretrofitmvvm.model.bean.ApiException;
import com.junzhitian.basicapp.utils.LogUtils;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

/**
 * @作者:TJ
 * @时间:2019/7/31 13:58
 * @描述:
 */
public class HttpCallbackImpl<T> implements HttpCallback<T> {

    private ResponseCallback<T> mCallback;

    public HttpCallbackImpl(ResponseCallback<T> callback) {
        mCallback = callback;
    }

    @Override
    public void onSuccess(T t) {
        if (mCallback != null) {
            mCallback.onResponse(t);
        }
    }

    @Override
    public void onFailure(Throwable e) {
        ApiManager apiManager = ApiManager.getInstance();
        if (e instanceof ApiException) {
            apiManager.handleApiException((ApiException) e);
        } else if (e instanceof SocketTimeoutException) {
            apiManager.showErrorToast(R.string.netword_connect_timeout);
        } else if (e instanceof ConnectException) {
            apiManager.showErrorToast(R.string.network_exception);
        } else {
            LogUtils.e("HttpLzyCallback", "onError->----其他异常: " + e.toString());
        }
        if (mCallback != null) {
            mCallback.onFailure(e);
        }
    }

    @Override
    public void onComplete() {

    }
}
