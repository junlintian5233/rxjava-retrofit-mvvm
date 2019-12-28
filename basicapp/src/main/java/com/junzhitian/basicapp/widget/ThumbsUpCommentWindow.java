package com.junzhitian.basicapp.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import androidx.databinding.DataBindingUtil;

import com.junzhitian.basicapp.R;
import com.junzhitian.basicapp.databinding.PopupThumbsupCommentBinding;
import com.junzhitian.basicapp.utils.DensityUtils;

/**
 * @作者:TJ
 * @时间:2019/10/9
 * @描述: <p>
 * int[] location = new int[2];
 * <p>
 * v.getLocationOnScreen(location);
 * <p>
 * 上方
 * popupWindow.showAtLocation(v, Gravity.NO_GRAVITY, location[0], location[1]-popupWindow.getHeight());
 * <p>
 * 下方
 * popupWindow.showAsDropDown(v);
 * <p>
 * 左方
 * popupWindow.showAtLocation(v, Gravity.NO_GRAVITY, location[0]-popupWindow.getWidth(), location[1]);
 * <p>
 * 右方
 * popupWindow.showAtLocation(v, Gravity.NO_GRAVITY, location[0]+v.getWidth(), location[1]);
 */
public class ThumbsUpCommentWindow extends PopupWindow {

    private final String                      TAG      = this.getClass().getSimpleName();
    private       Context                     mContext;
    private       PopupThumbsupCommentBinding mDataBinding;
    private       ThumbsUpCommentListener     mListener;
    private       int                         mIndex   = -1;
    private       EventHandler                mHandler = new EventHandler();


    public ThumbsUpCommentWindow(Context context, ThumbsUpCommentListener listener) {
        super(context);
        mContext = context;
        mListener = listener;
        init();
    }

    public ThumbsUpCommentWindow(Context context, int index, ThumbsUpCommentListener listener) {
        super(context);
        mContext = context;
        mIndex = index;
        mListener = listener;
        init();
    }

    private void init() {
        mDataBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.popup_thumbsup_comment, null, false);
        setContentView(mDataBinding.getRoot());
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setFocusable(true);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setAnimationStyle(R.style.fadeInWindowAnim);
        mDataBinding.setHandler(mHandler);
    }


    public void showLocation(View view) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);

        //Log.e(TAG, "showLocation: " + location[0] + "         " + location[1]);
        //Log.e(TAG, "showLocation: getWidth: " + DensityUtils.dip2px(mContext, 130));
        showAtLocation(view, Gravity.NO_GRAVITY, location[0] - DensityUtils.dip2px(mContext, 130), location[1]);
    }


    /**
     * 事件处理
     */
    public class EventHandler {

        public void thumbsUp() {
            dismiss();
            if (mListener != null) {
                mListener.thumbsUp(mIndex);
            }
        }

        public void comment() {
            dismiss();
            if (mListener != null) {
                mListener.comment(mIndex);
            }
        }
    }


    public EventHandler getHandler() {
        return mHandler;
    }

    public interface ThumbsUpCommentListener {

        void thumbsUp(int index);

        void comment(int index);
    }

}