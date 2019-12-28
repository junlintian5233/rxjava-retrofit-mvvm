package com.juntian.rxjavaretrofitmvvm.http;


import com.juntian.rxjavaretrofitmvvm.R;
import com.juntian.rxjavaretrofitmvvm.common.ApiManager;
import com.juntian.rxjavaretrofitmvvm.model.bean.ApiException;
import com.juntian.basicapp.utils.LogUtils;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

/**
 * @作者:TJ
 * @时间:2019-04-12 15:32
 * @描述:
 */
public abstract class HttpLzyCallback<T> implements HttpCallback<T> {

    @Override
    public void onComplete() {

    }

    @Override
    public abstract void onSuccess(T t);

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
    }
}
