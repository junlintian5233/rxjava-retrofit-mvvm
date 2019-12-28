package com.juntian.rxjavaretrofitmvvm.fragment.login;

import android.os.Bundle;
import android.view.View;

import com.juntian.rxjavaretrofitmvvm.R;
import com.juntian.rxjavaretrofitmvvm.base.BaseFragment;
import com.juntian.rxjavaretrofitmvvm.databinding.WechatBindDb;
import com.juntian.rxjavaretrofitmvvm.model.viewdata.LoginData;
import com.juntian.rxjavaretrofitmvvm.viewmodel.LoginVM;

/**
 * @作者:TJ
 * @时间:2019/8/1
 * @描述:
 */
public class WechatBindFragment extends BaseFragment<LoginVM, WechatBindDb> {
    private LoginData mData;


    @Override
    protected LoginVM createVM() {
        return new LoginVM(mContext, this);
    }

    @Override
    public int onSetLayoutRes() {
        return R.layout.fmt_wechat_bind;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void initData() {
        mData = new LoginData();
        mDataBinding.setData(mData);
        mDataBinding.setHandler(new EventHandler());
    }

    @Override
    public void bindEvent() {
        mDataBinding.titleBar.setLeftAction(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.onBackPressed();
            }
        });
    }

    /**
     * 事件处理
     */
    public class EventHandler {

        public void getCode() {

        }

        public void submit() {

        }
    }
}
