package com.juntian.rxjavaretrofitmvvm.activity;

import android.os.Bundle;

import com.juntian.rxjavaretrofitmvvm.R;
import com.juntian.rxjavaretrofitmvvm.base.BaseActivity;
import com.juntian.rxjavaretrofitmvvm.base.BaseViewModel;
import com.juntian.rxjavaretrofitmvvm.fragment.user.UserInfoFragment;

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
