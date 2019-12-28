package com.junzhitian.rxjavaretrofitmvvm.activity;

import android.os.Bundle;

import com.junzhitian.rxjavaretrofitmvvm.R;
import com.junzhitian.rxjavaretrofitmvvm.base.BaseActivity;
import com.junzhitian.rxjavaretrofitmvvm.base.BaseViewModel;
import com.junzhitian.rxjavaretrofitmvvm.fragment.PayFragment;

/**
 * @作者:TJ
 * @时间:2019/8/3
 * @描述:
 */
public class PayActivity extends BaseActivity {

    @Override
    protected BaseViewModel createVM() {
        return null;
    }

    @Override
    public int onSetLayoutRes() {
        return R.layout.act_pay;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView(Bundle savedInstanceState) {
        Bundle args = new Bundle();
        args.putBoolean("isPurchase", getIntent().getBooleanExtra("isPurchase", false));
        args.putString("orderNo", getIntent().getStringExtra("orderNo"));
        args.putString("sumMoney", getIntent().getStringExtra("sumMoney"));
        PayFragment fragment = new PayFragment();
        fragment.setArguments(args);
        addFragmentToActivity(fragment, R.id.pay_container);
    }

    @Override
    public void onBackPressed() {
        setBackStack();
    }
}
