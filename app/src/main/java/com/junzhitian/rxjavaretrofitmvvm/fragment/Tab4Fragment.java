package com.junzhitian.rxjavaretrofitmvvm.fragment;

import android.os.Bundle;
import android.view.View;

import com.junzhitian.rxjavaretrofitmvvm.R;
import com.junzhitian.rxjavaretrofitmvvm.activity.AccountSafeActivity;
import com.junzhitian.rxjavaretrofitmvvm.activity.LoginActivity;
import com.junzhitian.rxjavaretrofitmvvm.activity.MessageActivity;
import com.junzhitian.rxjavaretrofitmvvm.activity.UserInfoActivity;
import com.junzhitian.rxjavaretrofitmvvm.base.BaseFragment;
import com.junzhitian.rxjavaretrofitmvvm.base.BaseViewModel;
import com.junzhitian.rxjavaretrofitmvvm.common.ActivityPageManager;
import com.junzhitian.rxjavaretrofitmvvm.common.ApiManager;
import com.junzhitian.rxjavaretrofitmvvm.databinding.FmtTab4Binding;
import com.junzhitian.basicapp.qmui.QMUIStatusBarHelper;

/**
 * @作者:TJ
 * @时间:2019/8/1
 * @描述:
 */

public class Tab4Fragment extends BaseFragment<BaseViewModel, FmtTab4Binding> {
    @Override
    protected BaseViewModel createVM() {
        return null;
    }

    @Override
    public int onSetLayoutRes() {
        return R.layout.fmt_tab4;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        QMUIStatusBarHelper.setStatusBarDarkMode(mActivity);
    }

    @Override
    public void initData() {
        mDataBinding.setHandler(new EventHandler());
    }

    @Override
    public void bindEvent() {
        super.bindEvent();
        mDataBinding.titleBar.setLeftAction(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mDataBinding.titleBar.setRightAction(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.skipAct(MessageActivity.class);
            }
        });
    }

    /**
     * 事件处理
     */
    public class EventHandler {

        public void userInfo() {
            skipAct(UserInfoActivity.class);
        }

        public void allOrder() {

        }

        public void recycleRecord() {

        }

        public void deliverAddress() {

        }

        public void myPromote() {

        }

        public void accountSafe() {
            skipAct(AccountSafeActivity.class);
        }

        public void aboutUs() {

        }

        public void clearCache() {

        }

        public void exitLogin() {
            ApiManager.getInstance().exitLogin();
            skipAct(LoginActivity.class);
            ActivityPageManager.getInstance().finishPreviousActivity();
        }


    }
}
