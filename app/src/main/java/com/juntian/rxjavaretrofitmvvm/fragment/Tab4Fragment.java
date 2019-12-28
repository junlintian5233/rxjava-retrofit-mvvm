package com.juntian.rxjavaretrofitmvvm.fragment;

import android.os.Bundle;

import com.juntian.rxjavaretrofitmvvm.R;
import com.juntian.rxjavaretrofitmvvm.activity.LoginActivity;
import com.juntian.rxjavaretrofitmvvm.base.BaseFragment;
import com.juntian.rxjavaretrofitmvvm.base.BaseViewModel;
import com.juntian.rxjavaretrofitmvvm.databinding.FmtTab4Binding;

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
    }

    @Override
    public void initData() {
        mDataBinding.setHandler(new EventHandler());
    }

    @Override
    public void bindEvent() {
        super.bindEvent();
    }

    /**
     * 事件处理
     */
    public class EventHandler {

        public void exit() {
            skipAct(LoginActivity.class);
            mContext.finish();
        }
    }
}
