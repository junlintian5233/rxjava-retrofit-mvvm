package com.junzhitian.rxjavaretrofitmvvm.base;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @作者:TJ
 * @时间:2019-04-12 16:22
 * @描述:
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {

    private ViewDataBinding mBinding;


    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public BaseViewHolder(ViewDataBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }


    public ViewDataBinding getBinding() {
        return mBinding;
    }
}
