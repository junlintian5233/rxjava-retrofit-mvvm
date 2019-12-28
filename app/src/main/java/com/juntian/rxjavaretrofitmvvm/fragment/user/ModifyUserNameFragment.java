package com.juntian.rxjavaretrofitmvvm.fragment.user;

import android.os.Bundle;
import android.view.View;

import com.juntian.rxjavaretrofitmvvm.R;
import com.juntian.rxjavaretrofitmvvm.base.BaseFragment;
import com.juntian.rxjavaretrofitmvvm.databinding.ModifyUserNameDb;
import com.juntian.rxjavaretrofitmvvm.viewmodel.UserVM;

/**
 * @作者:TJ
 * @时间:2019/8/2
 * @描述:
 */
public class ModifyUserNameFragment extends BaseFragment<UserVM, ModifyUserNameDb> {
    @Override
    protected UserVM createVM() {
        return new UserVM(mContext, this);
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
                mContext.onBackPressed();
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
