package com.junzhitian.rxjavaretrofitmvvm.fragment;

import android.os.Bundle;

import com.junzhitian.rxjavaretrofitmvvm.R;
import com.junzhitian.rxjavaretrofitmvvm.base.BaseFragment;
import com.junzhitian.rxjavaretrofitmvvm.databinding.UserInfoDb;
import com.junzhitian.rxjavaretrofitmvvm.model.viewdata.UserData;
import com.junzhitian.rxjavaretrofitmvvm.viewmodel.UserVM;

/**
 * @作者:TJ
 * @时间:2019/8/2
 * @描述:
 */
public class UserInfoFragment extends BaseFragment<UserVM, UserInfoDb> {

    private UserData mData;

    @Override
    protected UserVM createVM() {
        return new UserVM(mActivity, this);
    }

    @Override
    public int onSetLayoutRes() {
        return R.layout.fmt_user_info;
    }

    @Override
    public void initData() {
        mData = new UserData();
        mDataBinding.setData(mData);
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mDataBinding.setHandler(new EventHandler());
    }


    /**
     * 事件处理
     */
    public class EventHandler {

        public void avatar() {

        }


        public void username() {
            changeFragment(R.id.user_info_contaioner, new ModifyUserNameFragment(), UserInfoFragment.this);
        }


        public void gender() {

        }

        public void birthday() {

        }
    }

}
