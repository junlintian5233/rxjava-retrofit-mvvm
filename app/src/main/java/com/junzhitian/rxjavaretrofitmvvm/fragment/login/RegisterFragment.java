package com.junzhitian.rxjavaretrofitmvvm.fragment.login;

import android.os.Bundle;

import com.junzhitian.rxjavaretrofitmvvm.R;
import com.junzhitian.rxjavaretrofitmvvm.base.BaseFragment;
import com.junzhitian.rxjavaretrofitmvvm.databinding.RegisterDb;
import com.junzhitian.rxjavaretrofitmvvm.model.viewdata.LoginData;
import com.junzhitian.rxjavaretrofitmvvm.viewmodel.LoginVM;

/**
 * @作者:TJ
 * @时间:2019/8/1
 * @描述:
 */
public class RegisterFragment extends BaseFragment<LoginVM, RegisterDb> {

    private LoginData mData;


    @Override
    protected LoginVM createVM() {
        return new LoginVM(mActivity, this);
    }

    @Override
    public int onSetLayoutRes() {
        return R.layout.fmt_register;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void initData() {
        mData = new LoginData();
        mDataBinding.setData(mData);
        mDataBinding.setHandler(new EventHandler());
    }


    /**
     * 事件处理
     */
    public class EventHandler {

        public void refreshCode() {

        }

        public void getCode() {
        }

        public void selectSchool() {

        }

        public void register() {

        }

        public void login() {
            mActivity.onBackPressed();
        }
    }
}
