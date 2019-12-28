package com.junzhitian.rxjavaretrofitmvvm.model.bean;

/**
 * @作者:TJ
 * @时间:2019/7/31 11:01
 * @描述:
 */
public class WxLogin {

    /**
     * {
     * "token": "MDAwMDAwMDAwMH-Omdl_e8_dhc2csIG5jJiP3bNthaVtoA",   //用户秘钥
     * "is_mobile": "0",  //是否绑定手机
     * "is_email": "0",  //是否绑定邮箱
     * "is_extract": "1"  //是否是第三方登录
     * }
     */

    private String token;
    private String is_mobile;
    private String is_email;
    private String is_extract;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getIs_mobile() {
        return is_mobile;
    }

    public void setIs_mobile(String is_mobile) {
        this.is_mobile = is_mobile;
    }

    public String getIs_email() {
        return is_email;
    }

    public void setIs_email(String is_email) {
        this.is_email = is_email;
    }

    public String getIs_extract() {
        return is_extract;
    }

    public void setIs_extract(String is_extract) {
        this.is_extract = is_extract;
    }
}
