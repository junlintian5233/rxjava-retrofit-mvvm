package com.juntian.rxjavaretrofitmvvm.model;

import android.content.Context;

import com.juntian.rxjavaretrofitmvvm.base.BaseModel;
import com.juntian.rxjavaretrofitmvvm.http.ResponseCallback;
import com.juntian.rxjavaretrofitmvvm.model.bean.HttpResult;
import com.juntian.rxjavaretrofitmvvm.model.bean.LzyResult;
import com.juntian.rxjavaretrofitmvvm.model.bean.OrderDetails;
import com.juntian.rxjavaretrofitmvvm.model.bean.OrderInfo;

import java.util.List;
import java.util.Map;

/**
 * @作者:TJ
 * @时间:2019/8/3
 * @描述:
 */
public class OrderModel extends BaseModel {

    public OrderModel(Context context) {
        super(context);
    }


    public void getCartList(Map<String, Object> map, final ResponseCallback<List<OrderInfo>> callback) {
        //startRequestData(mRetrofitApi.getOrderList(map), new HttpCallbackImpl<>(callback));
    }

    public void cancelOrder(Map<String, Object> map, final ResponseCallback<String> callback) {
        //startRequestData(mRetrofitApi.cancelOrder(map), new HttpCallbackImpl<>(callback));
    }

    public void confirmReceipt(Map<String, Object> map, final ResponseCallback<String> callback) {
        //startRequestData(mRetrofitApi.confirmReceipt(map), new HttpCallbackImpl<>(callback));
    }

    public void getOrderDetails(Map<String, Object> map, final ResponseCallback<OrderDetails> callback) {
        //startRequestData(mRetrofitApi.getOrderDetails(map), new HttpCallbackImpl<>(callback));
    }

    public void comment(Map<String, Object> map, final ResponseCallback<HttpResult<List<LzyResult>>> callback) {
        //startRequestData2(mRetrofitApi.comment(map), new HttpCallbackImpl<>(callback));
    }

    public void applySaleBack(Map<String, Object> map, final ResponseCallback<HttpResult<LzyResult>> callback) {
        //startRequestData2(mRetrofitApi.applySaleBack(map), new HttpCallbackImpl<>(callback));
    }


}
