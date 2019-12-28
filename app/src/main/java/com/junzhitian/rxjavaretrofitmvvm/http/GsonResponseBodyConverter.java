package com.junzhitian.rxjavaretrofitmvvm.http;

import com.junzhitian.rxjavaretrofitmvvm.model.bean.ApiException;
import com.junzhitian.rxjavaretrofitmvvm.model.bean.HttpResult;
import com.junzhitian.basicapp.utils.LogUtils;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * @作者:TJ
 * @时间:2017-06-12
 * @描述:
 */
public class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final Type type;


    public GsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {

        String     response = value.string();
        JSONObject json     = null;
        //先将返回的json数据解析到Response中，如果rt==1，则解析到我们的实体基类中，否则抛异常
        LogUtils.e("GsonResponseBodyConverter", "convert: Json:   " + response);
        HttpResult httpResult = gson.fromJson(response, HttpResult.class);
        if (httpResult.getStatus() == 1) {
            //1的时候就直接解析，不可能出现解析异常。因为我们实体基类中传入的泛型，就是数据成功时候的格式
            try {
                return gson.fromJson(response, type);
            } catch (Exception e) {
                LogUtils.e("GsonResponseBodyConverter", "convert: 解析异常：数据类型非指定泛型");
                ApiException exception = new ApiException(9999, "数据解析异常");
                throw exception;
            }
        } else {
            LogUtils.e("GsonResponseBodyConverter", "API异常:" + httpResult.getMsg());
            ApiException exception = gson.fromJson(response, ApiException.class);
            throw exception;
        }
    }
}