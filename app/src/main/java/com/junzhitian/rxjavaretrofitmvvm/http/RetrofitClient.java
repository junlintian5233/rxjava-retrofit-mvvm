package com.junzhitian.rxjavaretrofitmvvm.http;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * @作者:TJ
 * @时间:2019-04-11 18:00
 * @描述:
 */
public class RetrofitClient {

    // 请求公共部分
    private static String      BASE_URL = "http://test.cnsunrun.com/wendujianceapp/";
    private static RetrofitApi api;
    private static Retrofit    retrofit;


    public static RetrofitApi getRetrofitApi() {
        if (api == null) {
            api = getRetrofit().create(RetrofitApi.class);
        }
        return api;
    }

    private static Retrofit getRetrofit() {
        if (retrofit == null) {
            //log拦截器  打印所有的log
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            Interceptor interceptor = new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request request = chain.request()
                            .newBuilder()
                            .addHeader("Accept-Encoding", "gzip, deflate")
                            .addHeader("Connection", "keep-alive")
                            .addHeader("X-HB-Client-Type", "ayb-android")
                            .addHeader("Content-Type", "multipart/form-data; boundary=7db372eb000e2")
                            .addHeader("Accept", "*/*")
                            .build();
                    return chain.proceed(request);
                }
            };

            OkHttpClient client = new OkHttpClient.Builder()
                    .readTimeout(10, TimeUnit.SECONDS)
                    .connectTimeout(10, TimeUnit.SECONDS)// 15秒连接超时
                    .addInterceptor(loggingInterceptor)
                    .addInterceptor(interceptor)
                    .build();

            retrofit = new Retrofit.Builder()
                    // 使用OkHttp Client
                    .client(client)
                    // baseUrl总是以/结束，@URL不要以/开头
                    .baseUrl(BASE_URL)
                    // 集成Gson转换器
                    .addConverterFactory(ResponseConverterFactory.create())
                    // 集成RxJava处理
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
