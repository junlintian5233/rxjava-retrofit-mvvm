package com.juntian.rxjavaretrofitmvvm.viewmodel;


import com.juntian.rxjavaretrofitmvvm.R;
import com.juntian.rxjavaretrofitmvvm.base.BaseActivity;
import com.juntian.rxjavaretrofitmvvm.base.BaseView;
import com.juntian.rxjavaretrofitmvvm.base.BaseViewModel;
import com.juntian.rxjavaretrofitmvvm.constant.Constants;
import com.juntian.rxjavaretrofitmvvm.common.ParmsHelper;
import com.juntian.rxjavaretrofitmvvm.http.ResponseCallback;
import com.juntian.rxjavaretrofitmvvm.model.UserModel;
import com.juntian.rxjavaretrofitmvvm.model.bean.HttpResult;
import com.juntian.rxjavaretrofitmvvm.model.bean.LzyResult;
import com.juntian.rxjavaretrofitmvvm.model.bean.UserInfo;
import com.juntian.rxjavaretrofitmvvm.model.viewdata.LoginData;
import com.juntian.rxjavaretrofitmvvm.view.MineView;
import com.juntian.basicapp.event.MessageEvent;
import com.juntian.basicapp.utils.DataCleanManager;
import com.juntian.basicapp.utils.LogUtils;
import com.juntian.basicapp.utils.SpUtils;
import com.juntian.basicapp.utils.Tools;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

/**
 * @作者:TJ
 * @时间:2019/7/8 14:40
 * @描述:
 */
public class UserVM extends BaseViewModel<UserModel, BaseView> {


    public UserVM(BaseActivity context, BaseView view) {
        super(context, view);
    }

    @Override
    public UserModel createModel() {
        return new UserModel(mContext);
    }

    /**
     * 获取用户信息
     */
    public void getUserInfo() {
        Map<String, Object> map = ParmsHelper.create();
        mModel.getUserInfo(map, new ResponseCallback<UserInfo>() {
            @Override
            public void onResponse(UserInfo userInfo) {
                super.onResponse(userInfo);
                SpUtils.getInstance().putBean(Constants.USERINFO, userInfo);
                if (mView instanceof MineView) {
                    ((MineView) mView).onUserInfoResult(userInfo);
                }
            }
        });
    }


    /**
     * 上传用户头像
     */
    public void uploadUserIcon(String base64) {
        mModel.uploadIcon(ParmsHelper.create("avatar_img", base64), new ResponseCallback<HttpResult<LzyResult>>(mView) {
            @Override
            public void onResponse(HttpResult<LzyResult> s) {
                super.onResponse(s);
                mView.showToastMessage(s.getMsg());
                EventBus.getDefault().post(new MessageEvent("notifyUserInfo"));
            }
        });
    }

    /**
     * 修改用户名
     */
    public void editUserInfo(final String key, String value) {
        if (Tools.isEmpty(value)) {
            switch (key) {
                case "nickname":
                    mView.showToastMessage("请输入用户昵称");
                    break;
                case "avatar_img":
                    mView.showToastMessage("请选择图片");
                    break;
            }
        }
        mView.showLoadDialog();
        mModel.editUserInfo(ParmsHelper.create(key, value), new ResponseCallback<HttpResult<String>>(mView) {
            @Override
            public void onResponse(HttpResult<String> s) {
                super.onResponse(s);
                mView.showToastMessage(s.getMsg());
                EventBus.getDefault().post(new MessageEvent("notifyUserInfo"));
                if (key.equals("nickname")) {
                    mContext.onBackPressed();
                }
            }
        });
    }


    /**
     * 修改登录密码
     *
     * @param data
     */
    public void updatePassword(LoginData data) {
        if (Tools.isEmpty(data.getOriginPassword())) {
            mView.showToastMessage(R.string.pls_input_origin_pwd);
            return;
        }
        if (Tools.isEmpty(data.getPassword())) {
            mView.showToastMessage(R.string.pls_input_new_pwd);
            return;
        }
        if (Tools.isEmpty(data.getRepPassword())) {
            mView.showToastMessage(R.string.pls_confirm_new_pwd);
            return;
        }
        Map<String, Object> map = ParmsHelper.create();
        map.put("o_password", data.getOriginPassword());
        map.put("c_password", data.getPassword());
        map.put("n_password", data.getRepPassword());
        mView.showLoadDialog();
        mModel.updatePassword(map, new ResponseCallback<HttpResult<String>>(mView) {
            @Override
            public void onResponse(HttpResult<String> result) {
                super.onResponse(result);
                mView.showToastMessage(result.getMsg());
                mContext.onBackPressed();
            }
        });
    }


    /**
     * 【个人中心】设置支付密码
     *
     * @param data
     */
    public void setPayPassword(LoginData data) {
        if (Tools.isEmpty(data.getPayPassword())) {
            mView.showToastMessage(R.string.pls_input_pay_pwd);
            return;
        }
        if (Tools.isEmpty(data.getRepPayPassword())) {
            mView.showToastMessage(R.string.pls_input_confirm_pay_pwd);
            return;
        }
        Map<String, Object> map = ParmsHelper.create();
        map.put("pay_password", data.getPayPassword());
        map.put("pay_passwords", data.getRepPayPassword());
        mView.showLoadDialog();
        mModel.setPayPassword(map, new ResponseCallback<HttpResult<String>>(mView) {
            @Override
            public void onResponse(HttpResult<String> result) {
                super.onResponse(result);
                mView.showToastMessage(result.getMsg());
                mContext.onBackPressed();
            }
        });
    }

    /**
     * 【个人中心】重置支付密码
     *
     * @param data
     */
    public void resetPayPassword(LoginData data) {
        if (Tools.isEmpty(data.getSmsCode())) {
            mView.showToastMessage(R.string.pls_input_sms_code);
            return;
        }
        if (Tools.isEmpty(data.getPayPassword())) {
            mView.showToastMessage(R.string.pls_input_pay_pwd);
            return;
        }
        if (Tools.isEmpty(data.getRepPayPassword())) {
            mView.showToastMessage(R.string.pls_input_confirm_pay_pwd);
            return;
        }
        Map<String, Object> map = ParmsHelper.create();
        map.put("mobile", data.getAccount());
        map.put("code", data.getSmsCode());
        map.put("pay_password", data.getPayPassword());
        map.put("pay_passwords", data.getRepPayPassword());
        mView.showLoadDialog();
        mModel.resetPayPassword(map, new ResponseCallback<HttpResult<String>>(mView) {
            @Override
            public void onResponse(HttpResult<String> result) {
                super.onResponse(result);
                mView.showToastMessage(result.getMsg());
                mContext.onBackPressed();
            }
        });
    }

    /**
     * 获取钱包列表
     */
    public void getBillList(int currentPage) {
//        mModel.getBillList(ParmsHelper.create("p", currentPage), new ResponseCallback<List<BillInfo>>() {
//            @Override
//            public void onResponse(List<BillInfo> list) {
//                super.onResponse(list);
//                ((WalletView) mView).onBillListResponse(list);
//            }
//
//            @Override
//            public void onFailure(Throwable e) {
//                super.onFailure(e);
//                ((WalletView) mView).onBillListFailure();
//            }
//        });
    }


    /**
     * 获取收藏列表
     */
    public void getFavoriteList() {
//        mModel.getFavoriteList(ParmsHelper.create(), new ResponseCallback<List<Favorite>>() {
//            @Override
//            public void onResponse(List<Favorite> list) {
//                super.onResponse(list);
//                ((FavoriteView) mView).onFavoriteListResponse(list);
//            }
//        });
    }

    /**
     * 收藏
     */
    public void favorites(String id) {
        mView.showLoadDialog();
//        mModel.favorites(ParmsHelper.create("product_id", id), new ResponseCallback<HttpResult<LzyResult>>(mView) {
//            @Override
//            public void onResponse(HttpResult<LzyResult> result) {
//                super.onResponse(result);
//                mView.showToastMessage(result.getMsg());
//            }
//
//            @Override
//            public void onFailure(Throwable e) {
//                super.onFailure(e);
//                ((ProductDetailView) mView).onFavoriteFailure();
//            }
//        });
    }

    /**
     * 取消收藏
     */
    public void delFavorites(String ids) {
        mView.showLoadDialog();
        LogUtils.e(TAG, "delFavorites: " + ids);
//        mModel.delFavorites(ParmsHelper.create("id", ids), new ResponseCallback<HttpResult<List<LzyResult>>>(mView) {
//            @Override
//            public void onResponse(HttpResult<List<LzyResult>> result) {
//                super.onResponse(result);
//                mView.showToastMessage(result.getMsg());
//                mView.onLoadSuccess();
//            }
//        });
    }

    /**
     * 获取缓存size
     *
     * @return
     */
    public String getCacheSize() {
        try {
            return DataCleanManager.getCacheSize(mContext);
        } catch (Exception e) {
            e.printStackTrace();
            return "0KB";
        }
    }


}
