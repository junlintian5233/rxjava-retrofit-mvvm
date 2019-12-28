package com.juntian.rxjavaretrofitmvvm.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * @author:TJ
 * @ate:2017/8/19 14:09
 * @description:adapter基类
 */
public abstract class BaseCommonAdapter<T> extends BaseAdapter {

    public final String         TAG = this.getClass().getSimpleName();
    public       Context        mContext;
    public       List<T>        mDataList;
    public       LayoutInflater mInflater;

    public BaseCommonAdapter(Context context, List<T> mDataList) {
        this.mContext = context;
        this.mDataList = mDataList;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mDataList != null ? mDataList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mDataList != null ? mDataList.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getItemView(position, convertView, parent);
    }


    protected abstract View getItemView(int position, View convertView, ViewGroup parent);


    /**
     * 根据0,1获取状态
     *
     * @param status
     * @return
     */
    public boolean getStatus(int status) {
        if (status == 0) {
            return false;
        } else if (status == 1) {
            return true;
        }
        return false;
    }
}
