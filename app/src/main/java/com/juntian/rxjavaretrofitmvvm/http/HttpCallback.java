package com.juntian.rxjavaretrofitmvvm.http;

/**
 * @作者:TJ
 * @时间:2019-04-12 15:31
 * @描述:
 */
public interface HttpCallback<T> {

    void onSuccess(T t);

    void onFailure(Throwable throwable);

    void onComplete();
}
