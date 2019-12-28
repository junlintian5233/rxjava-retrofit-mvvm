package com.juntian.rxjavaretrofitmvvm.activity;

import android.os.Bundle;

import com.juntian.rxjavaretrofitmvvm.R;
import com.juntian.rxjavaretrofitmvvm.base.BaseActivity;
import com.juntian.rxjavaretrofitmvvm.base.BaseViewModel;
import com.juntian.rxjavaretrofitmvvm.fragment.user.AccountSafeFragment;

/**
 * @作者:TJ
 * @时间:2019/8/3
 * @描述:
 */
public class AccountSafeActivity extends BaseActivity {

    @Override
    protected BaseViewModel createVM() {
        return null;
    }

    @Override
    public int onSetLayoutRes() {
        return R.layout.act_account_safe;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView(Bundle savedInstanceState) {
        addFragmentToActivity(new AccountSafeFragment(), R.id.account_safe_container);
    }


    @Override
    public void onBackPressed() {
        setBackStack();
    }
}
