package com.junzhitian.rxjavaretrofitmvvm.model.viewdata;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

/**
 * @作者:TJ
 * @时间:2019/8/2
 * @描述:
 */
public class LoginData extends BaseObservable {

    private String account;
    private String password;
    private String repPassword;

    private String imageCode;
    private String smsCode;
    private String inviteCode;

    private String originPassword;

    private String payPassword;
    private String repPayPassword;

    @Bindable
    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
        notifyPropertyChanged(BR.inviteCode);
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public String getRepPayPassword() {
        return repPayPassword;
    }

    public void setRepPayPassword(String repPayPassword) {
        this.repPayPassword = repPayPassword;
    }

    public String getOriginPassword() {
        return originPassword;
    }

    public void setOriginPassword(String originPassword) {
        this.originPassword = originPassword;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepPassword() {
        return repPassword;
    }

    public void setRepPassword(String repPassword) {
        this.repPassword = repPassword;
    }

    public String getImageCode() {
        return imageCode;
    }

    public void setImageCode(String imageCode) {
        this.imageCode = imageCode;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }
}
