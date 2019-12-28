package com.junzhitian.rxjavaretrofitmvvm.http;


import com.junzhitian.rxjavaretrofitmvvm.model.bean.HttpResult;
import com.junzhitian.rxjavaretrofitmvvm.model.bean.LzyResult;
import com.junzhitian.rxjavaretrofitmvvm.model.bean.UserInfo;
import com.junzhitian.rxjavaretrofitmvvm.model.bean.WxLogin;
import com.junzhitian.rxjavaretrofitmvvm.model.bean.WxUserInfo;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * @作者:TJ
 * @时间:2019-04-11 18:00
 * @描述:
 */
public interface RetrofitApi {

    /****************************************登录注册*****************************************/

    /**
     * 公共接口-获取验证码 未注册
     */
    @FormUrlEncoded
    @POST("Appapi/User/Register/send_code")
    Observable<HttpResult<String>> getUnRegisterCode(@FieldMap Map<String, Object> map);

    /**
     * 公共接口-获取验证码 已注册
     */
    @FormUrlEncoded
    @POST("Appapi/User/Register/send_code_login")
    Observable<HttpResult<String>> getRegisterCode(@FieldMap Map<String, Object> map);

    /**
     * 普通注册
     */
    @FormUrlEncoded
    @POST("Appapi/User/Register/register")
    Observable<HttpResult<UserInfo>> register(@FieldMap Map<String, Object> map);


    /**
     * 普通登录
     */
    @FormUrlEncoded
    @POST("Appapi/User/Login/login")
    Observable<HttpResult<UserInfo>> login(@FieldMap Map<String, Object> map);


    /**
     * 微信登录
     */
    @FormUrlEncoded
    @POST("Api/V1/Login/extract_login")
    Observable<HttpResult<WxLogin>> wechatLogin(@FieldMap Map<String, Object> map);

    /**
     * 忘记密码
     */
    @FormUrlEncoded
    @POST("Appapi/User/Login/forget_password")
    Observable<HttpResult<String>> forgotPassword(@FieldMap Map<String, Object> map);


    /**
     * 获取WX用户资料
     */
    @GET("Appapi/User/Register/get_wx_info")
    Observable<HttpResult<WxUserInfo>> getWXUserInfo(@Query("code") String code);


    /**
     * 微信登录-绑定手机号
     */
    @FormUrlEncoded
    @POST("Appapi/User/Register/bind_mobile_no")
    Observable<HttpResult<UserInfo>> bindPhone(@FieldMap Map<String, Object> map);


    /**
     * 微信登录-绑定账号
     */
    @FormUrlEncoded
    @POST("Appapi/User/Register/bind_mobile_has")
    Observable<HttpResult<UserInfo>> bindAccount(@FieldMap Map<String, Object> map);

    /**
     * 【公共】第三方登录
     */
    @FormUrlEncoded
    @POST("Appapi/User/Register/oauth_login")
    Observable<HttpResult<UserInfo>> thirdLogin(@FieldMap Map<String, Object> map);


    /****************************************用户中心*****************************************/

    /**
     * 获取用户资料
     */
    @GET("Appapi/Account/Account/index")
    Observable<HttpResult<UserInfo>> getUserInfo(@QueryMap Map<String, Object> map);

    /**
     * 基础资料-修改昵称
     */
    @FormUrlEncoded
    @POST("Appapi/Account/Account/edit_info")
    Observable<HttpResult<String>> editUserInfo(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("Appapi/Account/Account/edit_info")
    Observable<HttpResult<LzyResult>> uploadIcon(@FieldMap Map<String, Object> map);

    /**
     * 【个人中心】修改登录密码
     */
    @FormUrlEncoded
    @POST("Appapi/Account/Account/update_password")
    Observable<HttpResult<String>> updatePassword(@FieldMap Map<String, Object> map);

    /**
     * 【个人中心】设置支付密码
     */
    @FormUrlEncoded
    @POST("Appapi/Account/Account/set_payment_password")
    Observable<HttpResult<String>> setPayPassword(@FieldMap Map<String, Object> map);

    /**
     * 【个人中心】重置支付密码
     */
    @FormUrlEncoded
    @POST("Appapi/Account/Account/reset_payment_password")
    Observable<HttpResult<String>> resetPayPassword(@FieldMap Map<String, Object> map);

    /**
     * 基础资料-修改密码第2步
     */
    @FormUrlEncoded
    @POST("Api/V1/Account/change_password")
    Observable<HttpResult<String>> changePassword(@FieldMap Map<String, Object> map);

    /****************************************用户中心*****************************************/


    /****************************************用户中心*****************************************/


    /****************************************用户中心*****************************************/


    /****************************************用户中心*****************************************/

}
