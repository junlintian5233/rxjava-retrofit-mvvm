package com.junzhitian.rxjavaretrofitmvvm.adaptor;

import android.content.Context;

import com.junzhitian.rxjavaretrofitmvvm.R;
import com.junzhitian.rxjavaretrofitmvvm.base.BaseAdapter;
import com.junzhitian.rxjavaretrofitmvvm.base.BaseViewHolder;
import com.junzhitian.rxjavaretrofitmvvm.databinding.AptSystemMsgDb;
import com.junzhitian.rxjavaretrofitmvvm.fragment.MsgFragment;
import com.junzhitian.rxjavaretrofitmvvm.model.bean.SystemMessage;

import java.util.List;

/**
 * @作者:TJ
 * @时间:2019/8/3
 * @描述:
 */
public class MessageAdapter extends BaseAdapter<SystemMessage> {

    private MsgFragment.EventHandler mHandler;

    public void setHandler(MsgFragment.EventHandler handler) {
        mHandler = handler;
    }

    public MessageAdapter(Context context, List<SystemMessage> dataList) {
        super(context, dataList);
    }

    @Override
    public int onSetAdapterLayout() {
        return R.layout.apt_system_msg;
    }

    @Override
    public void onBindVH(BaseViewHolder viewHolder, int position) {
        AptSystemMsgDb binding = (AptSystemMsgDb) viewHolder.getBinding();
        binding.setHandler(mHandler);
        binding.setData(mDataList.get(position));
    }

}
