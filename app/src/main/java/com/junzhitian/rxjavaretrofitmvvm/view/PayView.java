package com.junzhitian.rxjavaretrofitmvvm.view;

import com.junzhitian.rxjavaretrofitmvvm.base.BaseView;
import com.junzhitian.rxjavaretrofitmvvm.model.bean.WechatPayInfo;

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
