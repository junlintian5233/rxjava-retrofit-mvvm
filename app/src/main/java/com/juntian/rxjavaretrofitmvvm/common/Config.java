package com.juntian.rxjavaretrofitmvvm.common;


import com.juntian.rxjavaretrofitmvvm.constant.Constants;
import com.juntian.rxjavaretrofitmvvm.model.bean.Address;
import com.juntian.rxjavaretrofitmvvm.model.bean.UserInfo;
import com.juntian.basicapp.utils.SpUtils;

/**
 * App配置信息
 */

public class Config {

    //验证码倒计时
    public static final int COUNTDOWN_TIME = 60;

    //微信APPID
    public static final String WX_APP_ID   = "wx73d4a8ee52861529";
    //融云APPID
    public static final String RONG_APP_ID = "82hegw5u8x01x";
    //腾讯APPID
    public static final String QQ_APP_ID   = "1109882214";

    /**
     * 获取UID
     *
     * @return
     */
    public static String getUid() {
        return SpUtils.getInstance().getString(Constants.UID, "");
    }

    /**
     * 获取ACCOUNT
     *
     * @return
     */
    public static String getAccount() {
        return SpUtils.getInstance().getString(Constants.ACCOUNT, "");
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public static UserInfo getUserInfo() {
        return (UserInfo) SpUtils.getInstance().getBean(Constants.USERINFO);
    }


    /**
     * 获取用户信息
     *
     * @return
     */
    public static Address getDefaultAddrss() {
        return (Address) SpUtils.getInstance().getBean(Constants.DEFAULT_ADDRESS);
    }


    public static boolean isLogin() {
        return !Config.getUid().isEmpty() &&
                Config.getUserInfo() != null &&
                Config.getUserInfo().getPassword() != null;
    }
}
