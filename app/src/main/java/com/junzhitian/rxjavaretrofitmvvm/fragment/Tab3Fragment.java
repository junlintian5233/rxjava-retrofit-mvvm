package com.junzhitian.rxjavaretrofitmvvm.fragment;

import android.os.Bundle;

import com.junzhitian.rxjavaretrofitmvvm.R;
import com.junzhitian.rxjavaretrofitmvvm.base.BaseFragment;
import com.junzhitian.rxjavaretrofitmvvm.base.BaseViewModel;

/**
 * @作者:TJ
 * @时间:2019/8/1
 * @描述:
 */
public class Tab3Fragment extends BaseFragment {
    @Override
    protected BaseViewModel createVM() {
        return null;
    }

    @Override
    public int onSetLayoutRes() {
        return R.layout.fmt_tab3;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void initData() {

    }
}
