package com.juntian.rxjavaretrofitmvvm.http;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpUtil {

    private static OkHttpClient okHttpClient;

    static {
        // log拦截器  打印所有的log
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();
    }


    /**
     * get请求
     *
     * @param url
     * @param params
     * @param callback
     */
    public static void doGet(String url, String token, Map<String, String> params, final HttpCallback callback) {
        //URL带的参数
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        //请求带的Header
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Authorization", token);
        if (params != null) {
            for (String key : params.keySet()) {
                urlBuilder.setQueryParameter(key, params.get(key));
            }
        }
        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .headers(headers == null ? new Headers.Builder().build() : Headers.of(headers))
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callback.onSuccess(response.body().string());
            }
        });
    }


    /**
     * post请求
     *
     * @param url
     * @param params
     * @param callback
     */
    public static void doPost(String url, String token, Map<String, String> params, final HttpCallback callback) {
        //POST参数构造MultipartBody.Builder，表单提交
        MultipartBody.Builder urlBuilder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        //请求带的Header
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Authorization", token);
        if (params != null) {
            for (String key : params.keySet()) {
                if (params.get(key) != null) {
                    urlBuilder.addFormDataPart(key, params.get(key));
                }
            }
        }
        Request request = new Request.Builder()
                .url(url)
                .post(urlBuilder.build())
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callback.onSuccess(response.body().string());
            }
        });
    }
}