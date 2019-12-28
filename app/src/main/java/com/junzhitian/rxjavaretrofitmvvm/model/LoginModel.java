package com.junzhitian.rxjavaretrofitmvvm.model;

import android.content.Context;

import com.junzhitian.rxjavaretrofitmvvm.base.BaseModel;
import com.junzhitian.rxjavaretrofitmvvm.http.HttpCallbackImpl;
import com.junzhitian.rxjavaretrofitmvvm.http.ResponseCallback;
import com.junzhitian.rxjavaretrofitmvvm.model.bean.HttpResult;
import com.junzhitian.rxjavaretrofitmvvm.model.bean.UserInfo;
import com.junzhitian.rxjavaretrofitmvvm.model.bean.WxUserInfo;

import java.util.Map;

/**
 * @作者:TJ
 * @时间:2019/7/414:57
 * @描述:
 */
public class LoginModel extends BaseModel {

    public LoginModel(Context context) {
        super(context);
    }


    public void getUnRegisterCode(Map<String, Object> map, final ResponseCallback<HttpResult<String>> callback) {
        startRequestData2(mRetrofitApi.getUnRegisterCode(map), new HttpCallbackImpl<>(callback));
    }

    public void getRegisterCode(Map<String, Object> map, final ResponseCallback<HttpResult<String>> callback) {
        startRequestData2(mRetrofitApi.getRegisterCode(map), new HttpCallbackImpl<>(callback));
    }

    public void register(Map<String, Object> map, final ResponseCallback<HttpResult<UserInfo>> callback) {
        startRequestData2(mRetrofitApi.register(map), new HttpCallbackImpl<>(callback));
    }

    public void forgotPassword(Map<String, Object> map, final ResponseCallback<HttpResult<String>> callback) {
        startRequestData2(mRetrofitApi.forgotPassword(map), new HttpCallbackImpl<>(callback));
    }

    public void login(Map<String, Object> map, final ResponseCallback<UserInfo> callback) {
        startRequestData(mRetrofitApi.login(map), new HttpCallbackImpl<>(callback));
    }

    public void getWXUserInfo(String code, final ResponseCallback<WxUserInfo> callback) {
        startRequestData(mRetrofitApi.getWXUserInfo(code), new HttpCallbackImpl<>(callback));
    }


    public void bindPhone(Map<String, Object> map, final ResponseCallback<HttpResult<UserInfo>> callback) {
        startRequestData2(mRetrofitApi.bindPhone(map), new HttpCallbackImpl<>(callback));
    }

    public void bindAccount(Map<String, Object> map, final ResponseCallback<HttpResult<UserInfo>> callback) {
        startRequestData2(mRetrofitApi.bindAccount(map), new HttpCallbackImpl<>(callback));
    }

    public void thirdLogin(Map<String, Object> map, final ResponseCallback<HttpResult<UserInfo>> callback) {
        startRequestData2(mRetrofitApi.thirdLogin(map), new HttpCallbackImpl<>(callback));
    }

}
