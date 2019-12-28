package com.juntian.rxjavaretrofitmvvm.fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.juntian.rxjavaretrofitmvvm.R;
import com.juntian.rxjavaretrofitmvvm.adaptor.MessageAdapter;
import com.juntian.rxjavaretrofitmvvm.base.BaseFragment;
import com.juntian.rxjavaretrofitmvvm.databinding.SystemMsgDb;
import com.juntian.rxjavaretrofitmvvm.model.bean.SystemMessage;
import com.juntian.rxjavaretrofitmvvm.viewmodel.UserVM;

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
        MessageAdapter adapter = new MessageAdapter(mContext, mMessageList);

        mDataBinding.recycleView.setLayoutManager(new LinearLayoutManager(mContext));
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
