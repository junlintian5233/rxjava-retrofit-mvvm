package com.juntian.basicapp.qmui.qmuiwidget;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

/**
 * Created by Kinva on 16/9/23.
 */
public class FixedPopupWindow extends PopupWindow {
    public FixedPopupWindow(Context context) {
        super(context);
    }

    public FixedPopupWindow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FixedPopupWindow(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public FixedPopupWindow(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public FixedPopupWindow(View contentView) {
        super(contentView);
    }

    public FixedPopupWindow() {
        super();
    }

    public FixedPopupWindow(int width, int height) {
        super(width, height);
    }

    public FixedPopupWindow(View contentView, int width, int height, boolean focusable) {
        super(contentView, width, height, focusable);
    }

    public FixedPopupWindow(View contentView, int width, int height) {
        super(contentView, width, height);
    }

    @Override
    public void setOutsideTouchable(boolean touchable) {
        if(touchable){
            getContentView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
        }
        super.setOutsideTouchable(touchable);
    }
    public void showAsDropDown(View anchor) {
        if (Build.VERSION.SDK_INT >= 24) {
            Rect rect = new Rect();
            anchor.getGlobalVisibleRect(rect);
            int h = anchor.getResources().getDisplayMetrics().heightPixels - rect.bottom;
            setHeight(h);
        }
        if (Build.VERSION.SDK_INT < 24) {
            super.showAsDropDown(anchor);
        } else {
            int[] location = new int[2];  // 获取控件在屏幕的位置
            anchor.getLocationOnScreen(location);
            if (Build.VERSION.SDK_INT == 25) {
                int screenHeight = anchor.getResources().getDisplayMetrics().heightPixels;
                int tempheight = getHeight();
                if (tempheight == WindowManager.LayoutParams.MATCH_PARENT || screenHeight <= tempheight) {
                    setHeight(screenHeight - location[1] - anchor.getHeight());
                }
            }
            showAtLocation(anchor, Gravity.NO_GRAVITY, location[0], location[1] + anchor.getHeight());
        }
    }

}