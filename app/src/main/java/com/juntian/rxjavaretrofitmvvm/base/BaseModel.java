package com.juntian.rxjavaretrofitmvvm.base;

import android.content.Context;

import com.juntian.rxjavaretrofitmvvm.http.HttpCallback;
import com.juntian.rxjavaretrofitmvvm.http.RetrofitApi;
import com.juntian.rxjavaretrofitmvvm.http.RetrofitClient;
import com.juntian.rxjavaretrofitmvvm.model.bean.HttpResult;
import com.juntian.basicapp.utils.LogUtils;
import com.juntian.basicapp.utils.SpUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @作者:TJ
 * @时间:2019-04-12 13:42
 * @描述:
 */
public class BaseModel {

    public final String TAG = this.getClass().getSimpleName();

    protected RetrofitApi         mRetrofitApi;
    protected Context             mContext;
    protected CompositeDisposable mDisposable;
    protected SpUtils             mSpUtils;

    public BaseModel(Context context) {
        mContext = context;
        mSpUtils = SpUtils.getInstance();
        //创建 CompositeDisposable 对象,然后在 onDestroy()里取消所有的订阅。
        mDisposable = new CompositeDisposable();
        mRetrofitApi = RetrofitClient.getRetrofitApi();
    }


    public void onDestroy() {
        if (mDisposable != null) {
            // 如果还想使用CompositeDisposable，就必须在创建一个新的对象了。
            //LogUtils.e(TAG, "onDestroy: mDisposable.dispose();");
            mDisposable.dispose();
        }
    }

    /**
     * 开始网络请求，分割数据
     */
    public <T> void startRequestData(Observable<HttpResult<T>> observable, final HttpCallback<T> callback) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<HttpResult<T>, T>() {
                    @Override
                    public T apply(HttpResult<T> tHttpResult) {
                        return tHttpResult.getData();
                    }
                })
                .subscribe(new Observer<T>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable.add(d);
                    }

                    @Override
                    public void onNext(T t) {
                        //LogUtils.e(TAG, "startRequestData-> onNext: ");
                        callback.onSuccess(t);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e(TAG, "startRequestData-> onError: " + e.getMessage());
                        callback.onFailure(e);
                    }

                    @Override
                    public void onComplete() {
                        //LogUtils.e(TAG, "startRequestData-> onComplete: ");
                        callback.onComplete();
                    }
                });
    }

    /**
     * 开始网络请求
     */
    public <T> void startRequestData2(Observable<HttpResult<T>> observable, final HttpCallback<HttpResult<T>> callback) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<T>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable.add(d);
                    }

                    @Override
                    public void onNext(HttpResult<T> result) {
                        //LogUtils.e(TAG, "startRequestData-> onNext: ");
                        callback.onSuccess(result);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e(TAG, "startRequestData-> onError: " + e.getMessage());
                        callback.onFailure(e);
                    }

                    @Override
                    public void onComplete() {
                        //LogUtils.e(TAG, "startRequestData-> onComplete: ");
                        callback.onComplete();
                    }
                });
    }
}
