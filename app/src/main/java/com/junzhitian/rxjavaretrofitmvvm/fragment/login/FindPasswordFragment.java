package com.junzhitian.rxjavaretrofitmvvm.fragment.login;

import android.os.Bundle;
import android.view.View;

import com.junzhitian.rxjavaretrofitmvvm.R;
import com.junzhitian.rxjavaretrofitmvvm.base.BaseFragment;
import com.junzhitian.rxjavaretrofitmvvm.databinding.FindPwdDb;
import com.junzhitian.rxjavaretrofitmvvm.model.viewdata.LoginData;
import com.junzhitian.rxjavaretrofitmvvm.viewmodel.LoginVM;

/**
 * @作者:TJ
 * @时间:2019/8/1
 * @描述:
 */
public class FindPasswordFragment extends BaseFragment<LoginVM, FindPwdDb> {
    private LoginData mData;

    @Override
    protected LoginVM createVM() {
        return new LoginVM(mActivity, this);
    }

    @Override
    public int onSetLayoutRes() {
        return R.layout.fmt_find_pwd;
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

    @Override
    public void bindEvent() {
        mDataBinding.titleBar.setLeftAction(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.onBackPressed();
            }
        });
    }

    /**
     * 事件处理
     */
    public class EventHandler {

        public void getCode() {

        }

        public void submit() {

        }
    }
}
