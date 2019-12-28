package com.junzhitian.rxjavaretrofitmvvm.activity;

import android.os.Bundle;

import com.junzhitian.rxjavaretrofitmvvm.R;
import com.junzhitian.rxjavaretrofitmvvm.base.BaseActivity;
import com.junzhitian.rxjavaretrofitmvvm.base.BaseViewModel;
import com.junzhitian.rxjavaretrofitmvvm.fragment.UserInfoFragment;

public class UserInfoActivity extends BaseActivity {


    @Override
    protected BaseViewModel createVM() {
        return null;
    }

    @Override
    protected int onSetLayoutRes() {
        return R.layout.act_user_info;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView(Bundle savedInstanceState) {
        addFragmentToActivity(new UserInfoFragment(), R.id.user_info_contaioner);
    }

    @Override
    public void onBackPressed() {
        setBackStack();
    }
}
