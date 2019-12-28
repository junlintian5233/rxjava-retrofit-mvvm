package com.juntian.basicapp.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.juntian.basicapp.R;
import com.juntian.basicapp.databinding.PopupSpinnerBinding;

/**
 * @作者:TJ
 * @时间:2019/10/9
 * @描述:
 */
public class SpinnerWindow extends PopupWindow implements PopupWindow.OnDismissListener {


    private Context                  mContext;
    private PopupSpinnerBinding      mDataBinding;
    private SpinnerItenClickListener mListener;
    private EventHandler             mHandler;
    private int                      mIndex;


    public SpinnerWindow(Context context, SpinnerItenClickListener listener) {
        super(context);
        mContext = context;
        mListener = listener;
        init();
    }

    public SpinnerWindow(Context context, SpinnerItenClickListener listener, int index) {
        super(context);
        mContext = context;
        mListener = listener;
        mIndex = index;
        init();
    }

    private void init() {
        mDataBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.popup_spinner, null, false);
        setContentView(mDataBinding.getRoot());
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setFocusable(true);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setAnimationStyle(R.style.fadeInWindowAnim);
        mHandler = new EventHandler();
        mDataBinding.setHandler(mHandler);
        setOnDismissListener(this);
    }


    public SpinnerWindow addItemDecoration(DividerDecoration decoration) {
        mDataBinding.recycleView.addItemDecoration(decoration);
        return this;
    }

    public SpinnerWindow setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        mDataBinding.recycleView.setLayoutManager(layoutManager);
        return this;
    }

    public SpinnerWindow setAdapter(RecyclerView.Adapter adapter) {
        mDataBinding.recycleView.setAdapter(adapter);
        return this;
    }

    public void showDropDown(View view) {
        showAsDropDown(view);
    }

    @Override
    public void onDismiss() {
        if (mListener != null) {
            mListener.onDismiss(mIndex);
        }
    }


    /**
     * 事件处理
     */
    public class EventHandler {

        public void onItemClick(int position) {
            if (mListener != null) {
                mListener.onSpinnerItemClick(mIndex, position);
                dismiss();
            }
        }

        public void mask() {
            dismiss();
        }

    }


    public EventHandler getHandler() {
        return mHandler;
    }

    public interface SpinnerItenClickListener {
        void onSpinnerItemClick(int index, int position);

        void onDismiss(int index);
    }

}