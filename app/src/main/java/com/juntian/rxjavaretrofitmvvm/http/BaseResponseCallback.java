package com.juntian.rxjavaretrofitmvvm.http;

/**
 * @作者:TJ
 * @时间:2019-04-15 10:41
 * @描述:
 */
public interface BaseResponseCallback<T> {

    void onResponse(T t);

    void onFailure(Throwable e);

}
