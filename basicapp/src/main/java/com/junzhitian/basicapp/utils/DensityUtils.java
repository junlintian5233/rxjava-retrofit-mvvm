/*
 * Copyright (c) 2014,KJFrameForAndroid Open Source Project,张涛.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.junzhitian.basicapp.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

public final class DensityUtils {

    /**
     * 获取屏幕分辨率
     *
     * @param context
     * @return
     */
    @SuppressWarnings("deprecation")
    public static int[] getScreenDispaly(Context context) {
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        int width    = windowManager.getDefaultDisplay().getWidth();// 手机屏幕的宽度
        int height   = windowManager.getDefaultDisplay().getHeight();// 手机屏幕的高度
        int result[] = {width, height};
        return result;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        Resources r = context.getResources();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpValue, r.getDisplayMetrics());
        return (int) px;
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 sp
     */
    public static int px2sp(Context context, float pxValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 sp 的单位 转成为 px
     */
    public static int sp2px(Context context, float spValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 获取dialog宽度
     */
    public static int getDialogW(Context aty) {
        DisplayMetrics dm = new DisplayMetrics();
        dm = aty.getResources().getDisplayMetrics();
        int w = dm.widthPixels - 100;
        // int w = aty.getWindowManager().getDefaultDisplay().getWidth() - 100;
        return w;
    }

    /**
     * 获取屏幕宽度
     */
    public static int getScreenW(Context aty) {
        DisplayMetrics dm = aty.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    /**
     * 获取屏幕高度
     */
    public static int getScreenH(Context aty) {
        DisplayMetrics dm = aty.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    /**
     * 获取屏幕的高度的一半
     *
     * @return
     */
    public static int getScreenPartH(Context aty) {
        DisplayMetrics dm = aty.getResources().getDisplayMetrics();
        return dm.heightPixels / 2;
    }

    /**
     * 状态栏的高度
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        Resources resources  = context.getResources();
        int       resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int       height     = resources.getDimensionPixelSize(resourceId);
        return height;
    }

    /**
     * 得到底部导航栏的高度
     *
     * @param context
     * @return
     */
    public static int getNavigationBarHeight(Context context) {
        Resources resources  = context.getResources();
        int       resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        int       height     = resources.getDimensionPixelSize(resourceId);
        return height;
    }

    /**
     * 判断是否有导航栏
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static boolean IsNavigationBar(WindowManager wm) {
        Display        defaultDisplay     = wm.getDefaultDisplay();
        DisplayMetrics realdisplayMetrics = new DisplayMetrics();
        defaultDisplay.getRealMetrics(realdisplayMetrics);
        int realheightPixels = realdisplayMetrics.heightPixels;
        int realwidthPixels  = realdisplayMetrics.widthPixels;

        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        int heightPixels = displayMetrics.heightPixels;
        int widthPixels  = displayMetrics.widthPixels;
        return realheightPixels > heightPixels || realwidthPixels > widthPixels;
    }

    /**
     * 字体适配
     */
    public static int FontSize(int screensize, int fontSize) {
        //这个倍数比较适合，当然你可以测试后再修改
        int rate = (int) (fontSize * (float) screensize / 320);
        //字体太小也不好看的  1080 3
        return rate < 15 ? 15 : rate;
    }


    /**
     * @param view
     */
    public static void showInputLayout(final Activity activity, final View view, final View root) {
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.requestFocus();

        /** 目前测试来看，还是挺准的
         * 原理：OnGlobalLayoutListener 每次布局变化时都会调用
         * 界面view 显示消失都会调用，软键盘显示与消失时都调用
         * */
        ViewTreeObserver.OnGlobalLayoutListener listener = new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //判断窗口可见区域大小
                Rect r = new Rect();
                // getWindowVisibleDisplayFrame()会返回窗口的可见区域高度
                activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
                //如果屏幕高度和Window可见区域高度差值大于整个屏幕高度的1/3，则表示软键盘显示中，否则软键盘为隐藏状态。
                int     heightDifference  = DensityUtils.getScreenH(activity) - (r.bottom);
                boolean isKeyboardShowing = heightDifference > DensityUtils.getScreenH(activity) * 1 / 3;
                if (isKeyboardShowing) {
                    // bottomView 需要跟随软键盘移动的布局
                    // setDuration(0) 默认300, 设置 0 ，表示动画执行时间为0，没有过程，只有动画结果了
                    view.animate().translationY(-heightDifference).setDuration(0).start();
                } else {
                    view.animate().translationY(0).start();
                }
            }
        };
        root.getViewTreeObserver().addOnGlobalLayoutListener(listener);
        InputMethodManager inputManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }

}