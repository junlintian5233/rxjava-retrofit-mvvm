package com.junzhitian.rxjavaretrofitmvvm.fragment;

import android.os.Bundle;

import com.junzhitian.rxjavaretrofitmvvm.R;
import com.junzhitian.rxjavaretrofitmvvm.base.BaseFragment;
import com.junzhitian.rxjavaretrofitmvvm.base.BaseViewModel;
import com.junzhitian.rxjavaretrofitmvvm.databinding.FmtMessageDetailBinding;

/**
 * @作者:TJ
 * @时间:2019/8/3
 * @描述:
 */
public class MessageDetailFragment extends BaseFragment<BaseViewModel, FmtMessageDetailBinding> {
    @Override
    protected BaseViewModel createVM() {
        return null;
    }

    @Override
    public int onSetLayoutRes() {
        return R.layout.fmt_message_detail;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void bindEvent() {

    }

}
