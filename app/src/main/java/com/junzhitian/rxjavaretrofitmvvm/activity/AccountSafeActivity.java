package com.junzhitian.rxjavaretrofitmvvm.activity;

import android.os.Bundle;

import com.junzhitian.rxjavaretrofitmvvm.R;
import com.junzhitian.rxjavaretrofitmvvm.base.BaseActivity;
import com.junzhitian.rxjavaretrofitmvvm.base.BaseViewModel;
import com.junzhitian.rxjavaretrofitmvvm.fragment.AccountSafeFragment;

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
