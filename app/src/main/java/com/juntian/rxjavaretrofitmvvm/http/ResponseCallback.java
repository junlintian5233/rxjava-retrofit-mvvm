package com.juntian.rxjavaretrofitmvvm.http;


import com.juntian.rxjavaretrofitmvvm.base.BaseView;

/**
 * @作者:TJ
 * @时间:2019-04-15 9:59
 * @描述:
 */
public abstract class ResponseCallback<T> implements BaseResponseCallback<T> {

    private BaseView mBaseView;

    public ResponseCallback() {
    }

    public ResponseCallback(BaseView baseView) {
        mBaseView = baseView;
    }


    @Override
    public void onResponse(T t) {
        if (mBaseView != null) {
            mBaseView.hideLoadDialog();
        }
    }

    @Override
    public void onFailure(Throwable e) {
        if (mBaseView != null) {
            mBaseView.hideLoadDialog();
        }
    }
}
