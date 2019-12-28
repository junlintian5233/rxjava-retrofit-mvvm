package com.juntian.rxjavaretrofitmvvm.viewmodel;

import com.juntian.rxjavaretrofitmvvm.base.BaseActivity;
import com.juntian.rxjavaretrofitmvvm.base.BaseView;
import com.juntian.rxjavaretrofitmvvm.base.BaseViewModel;
import com.juntian.rxjavaretrofitmvvm.common.Config;
import com.juntian.rxjavaretrofitmvvm.common.ParmsHelper;
import com.juntian.rxjavaretrofitmvvm.http.ResponseCallback;
import com.juntian.rxjavaretrofitmvvm.model.OrderModel;
import com.juntian.rxjavaretrofitmvvm.model.bean.OrderDetails;
import com.juntian.rxjavaretrofitmvvm.model.bean.OrderInfo;
import com.juntian.rxjavaretrofitmvvm.view.OrderDetailsView;
import com.juntian.rxjavaretrofitmvvm.view.OrderView;
import com.juntian.basicapp.utils.Tools;

import java.io.File;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @作者:TJ
 * @时间:2019/8/6
 * @描述:
 */
public class OrderVM extends BaseViewModel<OrderModel, BaseView> {

    public OrderVM(BaseActivity context, BaseView view) {
        super(context, view);
    }

    @Override
    public OrderModel createModel() {
        return new OrderModel(mContext);
    }

    private OrderView getOrderView() {
        return (OrderView) mView;
    }

    /**
     * 获取订单列表
     */
    public void getCartList(String status, int currentPage) {
        Map<String, Object> map = ParmsHelper.create();
        map.put("status", status);
        map.put("p", currentPage);
        mModel.getCartList(map, new ResponseCallback<List<OrderInfo>>() {
            @Override
            public void onResponse(List<OrderInfo> list) {
                super.onResponse(list);
                getOrderView().onOrderListResult(list);
            }

            @Override
            public void onFailure(Throwable e) {
                super.onFailure(e);
                getOrderView().onOrderListFailure();
            }
        });
    }


    /**
     * 取消订单
     */
    public void cancelOrder(String id) {
        mView.showLoadDialog();
        mModel.cancelOrder(ParmsHelper.create("id", id), new ResponseCallback<String>(mView) {
            @Override
            public void onResponse(String s) {
                super.onResponse(s);
                getOrderView().onStateChangedSuccess();
            }
        });
    }


    /**
     * 确认收货
     */
    public void confirmReceipt(String id) {
        mView.showLoadDialog();
        mModel.confirmReceipt(ParmsHelper.create("id", id), new ResponseCallback<String>(mView) {
            @Override
            public void onResponse(String s) {
                super.onResponse(s);
                getOrderView().onStateChangedSuccess();
            }
        });
    }


    /**
     * 确认收货
     */
    public void getOrderDetails(String id) {
        mView.showLoadDialog();
        mModel.getOrderDetails(ParmsHelper.create("id", id), new ResponseCallback<OrderDetails>(mView) {
            @Override
            public void onResponse(OrderDetails s) {
                super.onResponse(s);
                ((OrderDetailsView) mView).onOrderDetailsResult(s);
            }
        });
    }


    /**
     * 申请售后
     */
    public void applySaleBack(String id, String reason, String info, String money, List<String> list) {
        if (Tools.isEmpty(reason)) {
            mView.showToastMessage("请选择退款理由");
            return;
        }
        if (Tools.isEmpty(info)) {
            mView.showToastMessage("请输入退款说明");
            return;
        }
        MultipartBody.Builder builder = new MultipartBody.Builder()
                //表单类型
                .setType(MultipartBody.FORM)
                .addFormDataPart("uid", Config.getUid())
                .addFormDataPart("password", Config.getUserInfo().getPassword())
                .addFormDataPart("order_id", id)
                .addFormDataPart("back_reason", reason)
                .addFormDataPart("back_money", money)
                .addFormDataPart("back_info", info);

        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).isEmpty()) {
                File        file      = new File(list.get(i));
                RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                builder.addFormDataPart("file", file.getName(), imageBody);
            }
        }

        List<MultipartBody.Part> parts = builder.build().parts();
//        mModel.applySaleBack(parts, new ResponseCallback<String>() {
//            @Override
//            public void onResponse(String s) {
//                super.onResponse(s);
//                mView.showToastMessage(s);
//                mContext.finish();
//            }
//        });


        /*Map<String, Object> map = ParmsHelper.create();
        map.put("order_id", id);
        map.put("back_reason", reason);
        map.put("back_money", money);
        map.put("back_info", info);
        mView.showLoadDialog();
        mModel.applySaleBack(map, new ResponseCallback<HttpResult<LzyResult>>(mView) {
            @Override
            public void onResponse(HttpResult<LzyResult> result) {
                super.onResponse(result);
            }
        });*/
    }
}
