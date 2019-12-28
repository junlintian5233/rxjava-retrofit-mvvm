package com.juntian.rxjavaretrofitmvvm.view;

import com.juntian.rxjavaretrofitmvvm.base.BaseView;
import com.juntian.rxjavaretrofitmvvm.model.bean.WechatPayInfo;

/**
 * @作者:TJ
 * @时间:2019/8/22
 * @描述:
 */
public interface PayView extends BaseView {

    void onPaySuccess();

    void onPayFailure();

    void onWechatPayResponse(WechatPayInfo result);

    void onAliPayResponse(String result);
}
