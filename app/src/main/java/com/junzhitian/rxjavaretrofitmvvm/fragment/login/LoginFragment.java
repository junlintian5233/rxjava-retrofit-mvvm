package com.junzhitian.rxjavaretrofitmvvm.fragment.login;

import android.os.Bundle;

import com.junzhitian.rxjavaretrofitmvvm.R;
import com.junzhitian.rxjavaretrofitmvvm.activity.MainActivity;
import com.junzhitian.rxjavaretrofitmvvm.base.BaseFragment;
import com.junzhitian.rxjavaretrofitmvvm.common.Constants;
import com.junzhitian.rxjavaretrofitmvvm.databinding.FmtLoginDb;
import com.junzhitian.rxjavaretrofitmvvm.model.viewdata.LoginData;
import com.junzhitian.rxjavaretrofitmvvm.viewmodel.LoginVM;

/**
 * @作者:TJ
 * @时间:2019/8/1
 * @描述:
 */
public class LoginFragment extends BaseFragment<LoginVM, FmtLoginDb> {

    private LoginData mData;

    @Override
    protected LoginVM createVM() {
        return new LoginVM(mActivity, this);
    }

    @Override
    public int onSetLayoutRes() {
        return R.layout.fmt_login;
    }

    @Override
    public void initData() {
        mData = new LoginData();
        mDataBinding.setData(mData);
        mDataBinding.setHandler(new EventHandler());
    }


    @Override
    public void initView(Bundle savedInstanceState) {

    }


    /**
     * 事件处理
     */
    public class EventHandler {

        public void login() {
            showLoadDialog();
            mSpUtils.putString(Constants.TOKEN, ".....");
//            mViewModel.login(mData);
            skipAct(MainActivity.class);
        }

        public void register() {
            changeFragment(R.id.fragment_container, new RegisterFragment());
        }

        public void forgetPassword() {
            changeFragment(R.id.fragment_container, new FindPasswordFragment() );
        }

        public void qqLogin() {
            changeFragment(R.id.fragment_container, new WechatBindFragment());
        }

        public void wechatLogin() {
            changeFragment(R.id.fragment_container, new WechatBindFragment());
        }
    }

}
