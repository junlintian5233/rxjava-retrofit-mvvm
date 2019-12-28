package com.juntian.rxjavaretrofitmvvm.viewmodel;

import com.juntian.rxjavaretrofitmvvm.R;
import com.juntian.rxjavaretrofitmvvm.activity.MainActivity;
import com.juntian.rxjavaretrofitmvvm.base.BaseActivity;
import com.juntian.rxjavaretrofitmvvm.base.BaseView;
import com.juntian.rxjavaretrofitmvvm.base.BaseViewModel;
import com.juntian.rxjavaretrofitmvvm.constant.Constants;
import com.juntian.rxjavaretrofitmvvm.common.ParmsHelper;
import com.juntian.rxjavaretrofitmvvm.http.ResponseCallback;
import com.juntian.rxjavaretrofitmvvm.model.LoginModel;
import com.juntian.rxjavaretrofitmvvm.model.bean.HttpResult;
import com.juntian.rxjavaretrofitmvvm.model.bean.UserInfo;
import com.juntian.rxjavaretrofitmvvm.model.bean.WxUserInfo;
import com.juntian.rxjavaretrofitmvvm.model.viewdata.LoginData;
import com.juntian.rxjavaretrofitmvvm.utils.ToastUtils;
import com.juntian.rxjavaretrofitmvvm.view.VerifyCodeView;
import com.juntian.rxjavaretrofitmvvm.view.WxView;
import com.juntian.basicapp.event.MessageEvent;
import com.juntian.basicapp.utils.Tools;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

/**
 * @作者:TJ
 * @时间:2019/8/2
 * @描述:
 */
public class LoginVM extends BaseViewModel<LoginModel, BaseView> {

    public LoginVM(BaseActivity context, BaseView view) {
        super(context, view);
    }

    @Override
    public LoginModel createModel() {
        return new LoginModel(mContext);
    }


    /**
     * 注册逻辑
     *
     * @param data
     */
    public void register(LoginData data) {
        if (Tools.isEmpty(data.getAccount())) {
            mView.showToastMessage(R.string.pls_input_telphone);
            return;
        }

        if (Tools.isEmpty(data.getSmsCode())) {
            mView.showToastMessage(R.string.pls_input_sms_code);
            return;
        }
        if (checkPassword(data)) {
            return;
        }
        Map<String, Object> map = ParmsHelper.createEmpty();
        map.put("mobile", data.getAccount());
        map.put("code", data.getSmsCode());
        map.put("password", data.getPassword());
        map.put("repassword", data.getRepPassword());
        map.put("treaty", "1");
        map.put("school_id", "1");
        mModel.register(map, new ResponseCallback<HttpResult<UserInfo>>() {
            @Override
            public void onResponse(HttpResult<UserInfo> lzyResult) {
                super.onResponse(lzyResult);
                mView.showToastMessage(lzyResult.getMsg());
                mContext.onBackPressed();
            }
        });
    }

    private boolean checkPassword(LoginData data) {
        if (Tools.isEmpty(data.getPassword())) {
            mView.showToastMessage(R.string.pls_input_pwd);
            return true;
        }
        if (Tools.isEmpty(data.getRepPassword())) {
            mView.showToastMessage(R.string.pls_input_pwd_again);
            return true;
        }
        if (!data.getPassword().equals(data.getRepPassword())) {
            mView.showToastMessage(R.string.pwd_not_same);
            return true;
        }
        return false;
    }


    /**
     * 登录逻辑
     */
    public void login(LoginData data) {

        if (Tools.isEmpty(data.getAccount())) {
            mView.showToastMessage(R.string.pls_input_telphone);
            return;
        }
        if (Tools.isEmpty(data.getPassword())) {
            mView.showToastMessage(R.string.pls_input_pwd);
            return;
        }
        mView.showLoadDialog();
        Map<String, Object> map = ParmsHelper.createEmpty();
        map.put("mobile", data.getAccount());
        map.put("password", data.getPassword());
        mModel.login(map, new ResponseCallback<UserInfo>(mView) {
            @Override
            public void onResponse(UserInfo userInfo) {
                super.onResponse(userInfo);
                mView.showToastMessage("登录成功");
                saveUserInfoAndSkipMainPage(userInfo);
            }
        });
    }

    /**
     * 忘记密码
     */
    public void findPassword(LoginData data) {
        if (Tools.isEmpty(data.getAccount())) {
            mView.showToastMessage(R.string.pls_input_telphone);
            return;
        }
        if (Tools.isEmpty(data.getSmsCode())) {
            mView.showToastMessage(R.string.pls_input_sms_code);
            return;
        }
        if (checkPassword(data)) {
            return;
        }
        Map<String, Object> map = ParmsHelper.createEmpty();
        map.put("mobile", data.getAccount());
        map.put("code", data.getSmsCode());
        map.put("password", data.getPassword());
        map.put("repassword", data.getRepPassword());
        mModel.forgotPassword(map, new ResponseCallback<HttpResult<String>>() {
            @Override
            public void onResponse(HttpResult<String> lzyResult) {
                super.onResponse(lzyResult);
                mView.showToastMessage(lzyResult.getMsg());
                mContext.onBackPressed();
            }
        });
    }

    /**
     * 微信登录绑定手机号(新手机号)
     */
    public void bindPhoneAfterThirdLogin(LoginData data, UserInfo info) {
        if (Tools.isEmpty(data.getAccount())) {
            mView.showToastMessage(R.string.pls_input_telphone);
            return;
        }
        if (Tools.isEmpty(data.getSmsCode())) {
            mView.showToastMessage(R.string.pls_input_sms_code);
            return;
        }
        if (Tools.isEmpty(data.getPassword())) {
            mView.showToastMessage(R.string.pls_input_pwd);
            return;
        }
        if (Tools.isEmpty(data.getRepPassword())) {
            mView.showToastMessage(R.string.pls_input_pwd_again);
            return;
        }
        if (!data.getPassword().equals(data.getRepPassword())) {
            mView.showToastMessage(R.string.pwd_not_same);
        }

        Map<String, Object> map = ParmsHelper.createEmpty();
        map.put("mobile", data.getAccount());
        map.put("code", data.getSmsCode());
        map.put("password", data.getPassword());
        map.put("repassword", data.getRepPassword());
        map.put("register_type", info.getRegister_type());
        map.put("token", info.getToken());
        mModel.bindPhone(map, new ResponseCallback<HttpResult<UserInfo>>() {
            @Override
            public void onResponse(HttpResult<UserInfo> result) {
                super.onResponse(result);
                saveUserInfoAndSkipMainPage(result.getData());
            }
        });
    }

    /**
     * 微信登录绑定手机号(已有账户)
     */
    public void bindAccountAfterThirdLogin(LoginData data, UserInfo info) {
        if (Tools.isEmpty(data.getAccount())) {
            mView.showToastMessage(R.string.pls_input_telphone);
            return;
        }
//        if (Tools.isEmpty(data.getAccountPwd())) {
//            mView.showToastMessage(R.string.pls_input_pwd);
//            return;
//        }
        Map<String, Object> map = ParmsHelper.createEmpty();
        map.put("mobile", data.getAccount());
//        map.put("password", data.getAccountPwd());
        map.put("register_type", info.getRegister_type());
        map.put("token", info.getToken());
        mModel.bindAccount(map, new ResponseCallback<HttpResult<UserInfo>>() {
            @Override
            public void onResponse(HttpResult<UserInfo> result) {
                super.onResponse(result);
                saveUserInfoAndSkipMainPage(result.getData());
            }
        });
    }


    /**
     * 获取微信信息
     *
     * @param code
     */
    public void getWxUserInfo(String code) {
        mModel.getWXUserInfo(code, new ResponseCallback<WxUserInfo>() {
            @Override
            public void onResponse(WxUserInfo wxUserInfo) {
                super.onResponse(wxUserInfo);
                wechatLogin(wxUserInfo);
            }

            @Override
            public void onFailure(Throwable e) {
                super.onFailure(e);
                ((WxView) mView).onWxLoginFailed();
            }
        });
    }


    /**
     * 微信登录
     */
    public void wechatLogin(WxUserInfo info) {
        Map<String, Object> map = new HashMap<>();
        map.put("nickname", info.getNickname());
        map.put("head_img", info.getHeadimgurl());
        map.put("wx_open_id", info.getOpenid());
        map.put("qq_open_id", info.getOpenid());
        mModel.thirdLogin(map, new ResponseCallback<HttpResult<UserInfo>>() {
            @Override
            public void onResponse(HttpResult<UserInfo> result) {
                super.onResponse(result);
                if (Tools.isEmpty(result.getData().getMobile())) {
                    EventBus.getDefault().post(new MessageEvent("bindPhone", result.getData()));
                    ((WxView) mView).onWxLoginSuccess();
                } else {
                    mView.showToastMessage(result.getMsg());
                    saveUserInfoAndSkipMainPage(result.getData());
                    ((WxView) mView).onWxLoginSuccess();
                }
            }

            @Override
            public void onFailure(Throwable e) {
                super.onFailure(e);

            }
        });
    }


    /**
     * 登录成功，保存用户信息
     *
     * @param userInfo
     */
    private void saveUserInfoAndSkipMainPage(UserInfo userInfo) {
        mSpUtils.putString(Constants.UID, userInfo.getId());
        mSpUtils.putString(Constants.ACCOUNT, userInfo.getMobile());
        mSpUtils.putString(Constants.UPWD, userInfo.getPassword());
        mSpUtils.putBean(Constants.USERINFO, userInfo);
        EventBus.getDefault().post(new MessageEvent("notifyUserInfo"));
        mContext.skipAct(MainActivity.class);
    }


    /**
     * 获取验证码 未注册
     */
    public void getUnRegisterCode(LoginData data) {
        if (Tools.isEmpty(data.getAccount())) {
            mView.showToastMessage(mContext.getString(R.string.pls_input_telphone));
            return;
        }
        Map<String, Object> map = ParmsHelper.createEmpty();
        map.put("mobile", data.getAccount());
        mView.showLoadDialog();
        mModel.getUnRegisterCode(map, new ResponseCallback<HttpResult<String>>(mView) {
            @Override
            public void onResponse(HttpResult<String> lzyResult) {
                super.onResponse(lzyResult);
                ToastUtils.showLong(lzyResult.getData());
                if (mView instanceof VerifyCodeView) {
                    ((VerifyCodeView) mView).onVerifyCodeSend();
                }
            }
        });
    }

    /**
     * 获取验证码 注册
     */
    public void getRegisterCode(String phone) {
        if (Tools.isEmpty(phone)) {
            mView.showToastMessage(mContext.getString(R.string.pls_input_telphone));
            return;
        }
        Map<String, Object> map = ParmsHelper.createEmpty();
        map.put("mobile", phone);
        mView.showLoadDialog();
        mModel.getRegisterCode(map, new ResponseCallback<HttpResult<String>>(mView) {
            @Override
            public void onResponse(HttpResult<String> lzyResult) {
                super.onResponse(lzyResult);
                ToastUtils.showLong(lzyResult.getData());
                if (mView instanceof VerifyCodeView) {
                    ((VerifyCodeView) mView).onVerifyCodeSend();
                }
            }
        });
    }

}
