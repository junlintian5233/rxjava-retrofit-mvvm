package com.junzhitian.rxjavaretrofitmvvm.view;

import com.junzhitian.rxjavaretrofitmvvm.base.BaseView;
import com.junzhitian.rxjavaretrofitmvvm.model.bean.OrderInfo;

import java.util.List;

/**
 * @作者:TJ
 * @时间:2019/8/22
 * @描述:
 */
public interface OrderView extends BaseView {

    void onOrderListResult(List<OrderInfo> list);

    void onOrderListFailure();

    void onStateChangedSuccess();
}
