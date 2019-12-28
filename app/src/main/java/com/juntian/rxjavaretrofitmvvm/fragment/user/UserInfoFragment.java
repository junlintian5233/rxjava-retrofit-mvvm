package com.juntian.rxjavaretrofitmvvm.fragment.user;

import android.os.Bundle;

import com.juntian.rxjavaretrofitmvvm.R;
import com.juntian.rxjavaretrofitmvvm.base.BaseFragment;
import com.juntian.rxjavaretrofitmvvm.databinding.UserInfoDb;
import com.juntian.rxjavaretrofitmvvm.model.viewdata.UserData;
import com.juntian.rxjavaretrofitmvvm.viewmodel.UserVM;

/**
 * @作者:TJ
 * @时间:2019/8/2
 * @描述:
 */
public class UserInfoFragment extends BaseFragment<UserVM, UserInfoDb> {

    private UserData mData;

    @Override
    protected UserVM createVM() {
        return new UserVM(mContext, this);
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
