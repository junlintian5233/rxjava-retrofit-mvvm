package com.juntian.rxjavaretrofitmvvm.view;

import com.juntian.rxjavaretrofitmvvm.base.BaseView;
import com.juntian.rxjavaretrofitmvvm.model.bean.UserInfo;

/**
 * @作者:TJ
 * @时间:2019/8/22
 * @描述:
 */
public interface MineView extends BaseView {

    void onUserInfoResult(UserInfo info);
}
