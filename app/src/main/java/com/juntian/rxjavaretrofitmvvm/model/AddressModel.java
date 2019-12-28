package com.juntian.rxjavaretrofitmvvm.model;

import android.content.Context;

import com.juntian.rxjavaretrofitmvvm.base.BaseModel;
import com.juntian.rxjavaretrofitmvvm.http.ResponseCallback;
import com.juntian.rxjavaretrofitmvvm.model.bean.Address;
import com.juntian.rxjavaretrofitmvvm.model.bean.HttpResult;

import java.util.List;
import java.util.Map;

/**
 * @作者:TJ
 * @时间:2019/8/3
 * @描述:
 */
public class AddressModel extends BaseModel {

    public AddressModel(Context context) {
        super(context);
    }


    public void getAddressList(Map<String, Object> map, final ResponseCallback<List<Address>> callback) {
        //startRequestData(mRetrofitApi.getAddressList(map), new HttpCallbackImpl<>(callback));
    }

    public void deleteAddress(Map<String, Object> map, final ResponseCallback<HttpResult<String>> callback) {
       // startRequestData2(mRetrofitApi.deleteAddress(map), new HttpCallbackImpl<>(callback));
    }

    public void editAddress(Map<String, Object> map, final ResponseCallback<HttpResult<String>> callback) {
        //startRequestData2(mRetrofitApi.editAddress(map), new HttpCallbackImpl<>(callback));
    }
}
