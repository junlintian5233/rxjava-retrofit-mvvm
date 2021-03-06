package com.juntian.rxjavaretrofitmvvm.model;

import android.content.Context;

import com.juntian.rxjavaretrofitmvvm.base.BaseModel;
import com.juntian.rxjavaretrofitmvvm.http.ResponseCallback;
import com.juntian.rxjavaretrofitmvvm.model.bean.Categary;

import java.util.List;

/**
 * @作者:TJ
 * @时间:2019/8/3
 * @描述:
 */
public class CommonModel extends BaseModel {

    public CommonModel(Context context) {
        super(context);
    }


    public void getAboutUs(final ResponseCallback<String> callback) {
        //startRequestData(mRetrofitApi.getAboutUs(), new HttpCallbackImpl<>(callback));
    }


    public void getUseHelpCategary(final ResponseCallback<List<Categary>> callback) {
        //startRequestData(mRetrofitApi.getUseHelpCategary(), new HttpCallbackImpl<>(callback));
    }

    public void getUseHelpList(String id, final ResponseCallback<List<Categary>> callback) {
        //startRequestData(mRetrofitApi.getUseHelpList(id), new HttpCallbackImpl<>(callback));
    }
}
