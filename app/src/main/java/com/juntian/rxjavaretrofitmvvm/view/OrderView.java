package com.juntian.rxjavaretrofitmvvm.view;

import com.juntian.rxjavaretrofitmvvm.base.BaseView;
import com.juntian.rxjavaretrofitmvvm.model.bean.OrderInfo;

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
