package com.juntian.rxjavaretrofitmvvm.model;

import android.content.Context;

import com.juntian.rxjavaretrofitmvvm.base.BaseModel;
import com.juntian.rxjavaretrofitmvvm.http.HttpCallbackImpl;
import com.juntian.rxjavaretrofitmvvm.http.ResponseCallback;
import com.juntian.rxjavaretrofitmvvm.model.bean.HttpResult;
import com.juntian.rxjavaretrofitmvvm.model.bean.LzyResult;
import com.juntian.rxjavaretrofitmvvm.model.bean.UserInfo;

import java.util.Map;

/**
 * @作者:TJ
 * @时间:2019/7/8 14:40
 * @描述:
 */
public class UserModel extends BaseModel {

    public UserModel(Context context) {
        super(context);
    }

    /**
     * 获取用户信息
     *
     * @param callback
     */
    public void getUserInfo(Map<String, Object> map, final ResponseCallback<UserInfo> callback) {
        startRequestData(mRetrofitApi.getUserInfo(map), new HttpCallbackImpl<>(callback));
    }

    public void editUserInfo(Map<String, Object> map, final ResponseCallback<HttpResult<String>> callback) {
        startRequestData2(mRetrofitApi.editUserInfo(map), new HttpCallbackImpl<>(callback));
    }

    public void uploadIcon(Map<String, Object> map, final ResponseCallback<HttpResult<LzyResult>> callback) {
        startRequestData2(mRetrofitApi.uploadIcon(map), new HttpCallbackImpl<>(callback));
    }


    public void updatePassword(Map<String, Object> map, final ResponseCallback<HttpResult<String>> callback) {
        startRequestData2(mRetrofitApi.updatePassword(map), new HttpCallbackImpl<>(callback));
    }

    public void setPayPassword(Map<String, Object> map, final ResponseCallback<HttpResult<String>> callback) {
        startRequestData2(mRetrofitApi.setPayPassword(map), new HttpCallbackImpl<>(callback));
    }

    public void resetPayPassword(Map<String, Object> map, final ResponseCallback<HttpResult<String>> callback) {
        startRequestData2(mRetrofitApi.resetPayPassword(map), new HttpCallbackImpl<>(callback));
    }
}
