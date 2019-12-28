package com.juntian.basicapp.utils;

import android.graphics.drawable.Drawable;
import android.widget.TextView;

/**
 * @作者:TJ
 * @时间:2019/10/23
 * @描述:视图操作工具类
 */
public class ViewTools {

    /**
     * 设置TextView drawableRight 图片
     *
     * @param textView
     * @param resId
     */
    public static void setDrawableRightImage(TextView textView, int resId) {
        Drawable drawable = textView.getContext().getResources().getDrawable(resId);
        textView.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
    }
}
