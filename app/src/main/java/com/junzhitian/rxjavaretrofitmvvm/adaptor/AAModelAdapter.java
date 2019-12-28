package com.junzhitian.rxjavaretrofitmvvm.adaptor;

import android.content.Context;

import com.junzhitian.rxjavaretrofitmvvm.R;
import com.junzhitian.rxjavaretrofitmvvm.base.BaseAdapter;
import com.junzhitian.rxjavaretrofitmvvm.base.BaseViewHolder;
import com.junzhitian.rxjavaretrofitmvvm.model.bean.UserInfo;

import java.util.List;

/**
 * @作者:TJ
 * @时间:2019/8/3
 * @描述:
 */
public class AAModelAdapter extends BaseAdapter<UserInfo> {

    public AAModelAdapter(Context context, List<UserInfo> dataList) {
        super(context, dataList);
    }

    @Override
    public int onSetAdapterLayout() {
        return R.layout.aa_apt;
    }

    @Override
    public void onBindVH(BaseViewHolder viewHolder, int position) {
        viewHolder.getBinding();
    }

}
