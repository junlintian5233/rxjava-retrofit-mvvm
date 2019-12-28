package com.junzhitian.rxjavaretrofitmvvm.fragment;

import android.os.Bundle;

import com.junzhitian.rxjavaretrofitmvvm.R;
import com.junzhitian.rxjavaretrofitmvvm.base.BaseFragment;
import com.junzhitian.rxjavaretrofitmvvm.base.BaseViewModel;
import com.junzhitian.rxjavaretrofitmvvm.databinding.MsgListDb;

/**
 * @作者:TJ
 * @时间:2019/8/3
 * @描述:
 */
public class MessageListFragment extends BaseFragment<BaseViewModel, MsgListDb> {
    @Override
    protected BaseViewModel createVM() {
        return null;
    }

    @Override
    public int onSetLayoutRes() {
        return R.layout.fmt_msg_list;
    }

    @Override
    public void initData() {
        mDataBinding.setHandler(new EventHandler());
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }


    /**
     * 事件处理
     */
    public class EventHandler {

        public void systemMsg() {
            changeFragment(R.id.msg_container, new MsgFragment(), MessageListFragment.this);
        }
    }
}
