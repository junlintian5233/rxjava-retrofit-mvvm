package com.junzhitian.rxjavaretrofitmvvm.viewmodel;

import com.junzhitian.rxjavaretrofitmvvm.base.BaseActivity;
import com.junzhitian.rxjavaretrofitmvvm.base.BaseView;
import com.junzhitian.rxjavaretrofitmvvm.base.BaseViewModel;
import com.junzhitian.rxjavaretrofitmvvm.common.Config;
import com.junzhitian.rxjavaretrofitmvvm.common.ParmsHelper;
import com.junzhitian.rxjavaretrofitmvvm.http.ResponseCallback;
import com.junzhitian.rxjavaretrofitmvvm.model.OrderModel;
import com.junzhitian.rxjavaretrofitmvvm.model.bean.HttpResult;
import com.junzhitian.rxjavaretrofitmvvm.model.bean.LzyResult;
import com.junzhitian.rxjavaretrofitmvvm.model.bean.OrderDetails;
import com.junzhitian.rxjavaretrofitmvvm.model.bean.OrderInfo;
import com.junzhitian.rxjavaretrofitmvvm.view.OrderDetailsView;
import com.junzhitian.rxjavaretrofitmvvm.view.OrderView;
import com.junzhitian.basicapp.utils.FileUtils;
import com.junzhitian.basicapp.utils.Tools;

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
     * 评价
     */
    public void comment(List<String> list, String id, String content, String score) {
        if (Tools.isEmpty(score)) {
            mView.showToastMessage("请给予评分");
            return;
        }
        if (Tools.isEmpty(content)) {
            mView.showToastMessage("请输入评论内容");
            return;
        }
        if (list.size() <= 1) {
            mView.showToastMessage("选择评论图片");
            return;
        }

        Map<String, Object> map = ParmsHelper.create();
        map.put("id", id);
        map.put("score", score);
        map.put("content", content);

        for (int i = 0; i < list.size() - 1; i++) {
            if (!list.get(i).isEmpty()) {
                map.put("image[" + i + "]", FileUtils.bitmapToBase64(list.get(i)));
            }
        }
        mView.showLoadDialog();
        mModel.comment(map, new ResponseCallback<HttpResult<List<LzyResult>>>(mView) {
            @Override
            public void onResponse(HttpResult<List<LzyResult>> result) {
                super.onResponse(result);
                mView.showToastMessage(result.getMsg());
                mContext.finish();
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
