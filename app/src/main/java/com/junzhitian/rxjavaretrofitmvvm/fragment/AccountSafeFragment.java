package com.junzhitian.rxjavaretrofitmvvm.fragment;

import android.os.Bundle;

import com.junzhitian.rxjavaretrofitmvvm.R;
import com.junzhitian.rxjavaretrofitmvvm.base.BaseFragment;
import com.junzhitian.rxjavaretrofitmvvm.base.BaseViewModel;

/**
 * @作者:TJ
 * @时间:2019/8/3
 * @描述:
 */
public class AccountSafeFragment extends BaseFragment {
    @Override
    protected BaseViewModel createVM() {
        return null;
    }

    @Override
    public int onSetLayoutRes() {
        return R.layout.fmt_account_safe;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }


    /**
     * 事件处理
     */
    public class EventHandler {

        public void loginPwd() {

        }

        public void payPwd() {

        }

        public void updatePhone() {

        }
    }
}
