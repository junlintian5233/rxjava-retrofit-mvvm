package com.junzhitian.rxjavaretrofitmvvm.activity;

import android.os.Bundle;

import com.junzhitian.rxjavaretrofitmvvm.R;
import com.junzhitian.rxjavaretrofitmvvm.base.BaseActivity;
import com.junzhitian.rxjavaretrofitmvvm.base.BaseViewModel;
import com.junzhitian.rxjavaretrofitmvvm.fragment.login.LoginFragment;

public class LoginActivity extends BaseActivity {


    @Override
    protected BaseViewModel createVM() {
        return null;
    }

    @Override
    protected int onSetLayoutRes() {
        return R.layout.act_login;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView(Bundle savedInstanceState) {
        addFragmentToActivity(new LoginFragment(), R.id.fragment_container);
    }


    @Override
    public void onBackPressed() {
        setBackStack();
    }
}
