package com.junzhitian.rxjavaretrofitmvvm.fragment;

import android.os.Bundle;
import android.view.View;

import com.junzhitian.rxjavaretrofitmvvm.R;
import com.junzhitian.rxjavaretrofitmvvm.base.BaseFragment;
import com.junzhitian.rxjavaretrofitmvvm.databinding.ModifyUserNameDb;
import com.junzhitian.rxjavaretrofitmvvm.viewmodel.UserVM;

/**
 * @作者:TJ
 * @时间:2019/8/2
 * @描述:
 */
public class ModifyUserNameFragment extends BaseFragment<UserVM, ModifyUserNameDb> {
    @Override
    protected UserVM createVM() {
        return new UserVM(mActivity, this);
    }

    @Override
    public int onSetLayoutRes() {
        return R.layout.fmt_modify_username;
    }

    @Override
    public void initData() {
        mDataBinding.setHandler(new EventHandler());
    }

    @Override
    public void initView(Bundle savedInstanceState) {

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

        public void save() {

        }
    }
}
