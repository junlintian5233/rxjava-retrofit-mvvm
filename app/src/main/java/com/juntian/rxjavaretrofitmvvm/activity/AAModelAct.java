package com.juntian.rxjavaretrofitmvvm.activity;

import android.os.Bundle;

import com.juntian.rxjavaretrofitmvvm.R;
import com.juntian.rxjavaretrofitmvvm.base.BaseActivity;
import com.juntian.rxjavaretrofitmvvm.base.BaseViewModel;

/**
 * @作者:TJ
 * @时间:2019/8/3
 * @描述:
 */
public class AAModelAct extends BaseActivity {

    @Override
    protected BaseViewModel createVM() {
        return null;
    }

    @Override
    public int onSetLayoutRes() {
        return R.layout.aa_ui;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }
}
