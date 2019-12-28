package com.junzhitian.rxjavaretrofitmvvm.model.viewdata;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.junzhitian.rxjavaretrofitmvvm.BR;
import com.junzhitian.rxjavaretrofitmvvm.model.bean.UserInfo;


/**
 * @作者:TJ
 * @时间:2019/7/16
 * @描述:
 */
public class UserData extends BaseObservable {

    private UserInfo userinfo;

    @Bindable
    public UserInfo getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(UserInfo userinfo) {
        this.userinfo = userinfo;
        notifyPropertyChanged(BR.userinfo);
    }
}
