package com.junzhitian.rxjavaretrofitmvvm.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * @作者:TJ
 * @时间:2019-04-12 16:15
 * @描述:
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    public String                   TAG = this.getClass().getSimpleName();
    public OnItemClickListener      mOnItemClickListener;
    public OnItemChildClickListener mOnItemChildClickListener;
    public Context                  mContext;
    public List<T>                  mDataList;
    public LayoutInflater           mInflater;


    public BaseAdapter(Context context, List<T> dataList) {
        mContext = context;
        mDataList = dataList;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        ViewDataBinding dataBinding = DataBindingUtil.inflate(mInflater, onSetAdapterLayout(), viewGroup, false);
        return new BaseViewHolder(dataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, int i) {
        onBindVH(baseViewHolder, i);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    /**
     * 设置layout
     *
     * @return
     */
    public abstract int onSetAdapterLayout();


    /**
     * 绑定VH
     *
     * @param viewHolder
     * @param position
     * @return
     */
    public abstract void onBindVH(BaseViewHolder viewHolder, int position);


    /**
     * 刷新数据
     *
     * @param data 数据源
     */
    public void refreshData(List<T> data) {
        mDataList.clear();
        mDataList.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * 加载更多
     *
     * @param data 加载的新数据
     */
    public void loadMoreData(List<T> data) {
        mDataList.addAll(data);
        notifyDataSetChanged();
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemChildClickListener(OnItemChildClickListener onItemChildClickListener) {
        mOnItemChildClickListener = onItemChildClickListener;
    }

    /**
     * item点击事件
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    /**
     * item中view的点击事件
     */
    public interface OnItemChildClickListener {
        void onItemChildClick(View view, int position);
    }
}
