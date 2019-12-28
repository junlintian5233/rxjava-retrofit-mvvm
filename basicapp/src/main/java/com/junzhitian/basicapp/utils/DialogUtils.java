package com.junzhitian.basicapp.utils;

import android.app.Dialog;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * @作者:TJ
 * @时间:2019/9/2
 * @描述:
 */
public class DialogUtils {

    public static void setBottomFillWidth(Dialog dialog) {
        Window window = dialog.getWindow();
        if (dialog != null && window != null) {
            window.getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams attr = window.getAttributes();
            if (attr != null) {
                attr.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                attr.width = ViewGroup.LayoutParams.MATCH_PARENT;
                attr.gravity = Gravity.BOTTOM;//设置dialog 在布局中的位置
                window.setAttributes(attr);
                window.getDecorView().setBackgroundColor(Color.WHITE);
            }
        }
    }
}
