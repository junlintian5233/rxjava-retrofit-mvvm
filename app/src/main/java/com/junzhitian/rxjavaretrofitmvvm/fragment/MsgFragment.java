package com.junzhitian.rxjavaretrofitmvvm.fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.junzhitian.rxjavaretrofitmvvm.R;
import com.junzhitian.rxjavaretrofitmvvm.adaptor.MessageAdapter;
import com.junzhitian.rxjavaretrofitmvvm.base.BaseFragment;
import com.junzhitian.rxjavaretrofitmvvm.databinding.SystemMsgDb;
import com.junzhitian.rxjavaretrofitmvvm.model.bean.SystemMessage;
import com.junzhitian.rxjavaretrofitmvvm.viewmodel.UserVM;

import java.util.ArrayList;
import java.util.List;

/**
 * @作者:TJ
 * @时间:2019/8/3
 * @描述:
 */
public class MsgFragment extends BaseFragment<UserVM, SystemMsgDb> {

    private List<SystemMessage> mMessageList = new ArrayList<>();

    @Override
    protected UserVM createVM() {
        return null;
    }

    @Override
    public int onSetLayoutRes() {
        return R.layout.fmt_system_msg;
    }

    @Override
    public void initData() {
        for (int i = 0; i < 5; i++) {
            mMessageList.add(new SystemMessage());
        }
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        MessageAdapter adapter = new MessageAdapter(mActivity, mMessageList);

        mDataBinding.recycleView.setLayoutManager(new LinearLayoutManager(mActivity));
        mDataBinding.recycleView.setAdapter(adapter);

        adapter.setHandler(new EventHandler());
    }

    /**
     * 事件处理
     */
    public class EventHandler {

        public void onItemClick(int position) {
            changeFragment(R.id.msg_container, new MessageDetailFragment(), MsgFragment.this);
        }
    }

}
