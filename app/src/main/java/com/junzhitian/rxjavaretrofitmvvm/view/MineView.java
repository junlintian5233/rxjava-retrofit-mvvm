package com.junzhitian.rxjavaretrofitmvvm.view;

import com.junzhitian.rxjavaretrofitmvvm.base.BaseView;
import com.junzhitian.rxjavaretrofitmvvm.model.bean.UserInfo;

/**
 * @作者:TJ
 * @时间:2019/8/22
 * @描述:
 */
public interface MineView extends BaseView {

    void onUserInfoResult(UserInfo info);
}
