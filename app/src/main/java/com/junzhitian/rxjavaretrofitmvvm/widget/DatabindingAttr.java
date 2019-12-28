package com.junzhitian.rxjavaretrofitmvvm.widget;

import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.junzhitian.rxjavaretrofitmvvm.R;
import com.junzhitian.rxjavaretrofitmvvm.utils.GlideUtils;
import com.junzhitian.basicapp.qmui.qmuiwidget.titlebar.TitleBar;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;

/**
 * @作者:TJ
 * @时间:2019/7/6 10:01
 * @描述:databinding自定义属性设置
 */
public class DatabindingAttr {


    /**
     * SettingView 设置右边的内容
     */
    @BindingAdapter("content")
    public static void setContent(SettingView view, String content) {
        view.setContent(content);
    }


    @BindingAdapter({"avatarUrl"})
    public static void loadAvatar(ImageView imageView, String url) {
        GlideUtils.getInstance().loadAvatar(imageView.getContext(), url, imageView);
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView imageView, String url) {
        GlideUtils.getInstance().loadImage(imageView.getContext(), url, imageView, R.drawable.default_cover);
    }

    @BindingAdapter({"title"})
    public static void setTitle(TitleBar titleBar, String title) {
        titleBar.setTitle(title);
    }

    @BindingAdapter("android:onClick")
    public static void onClick(final View view, final View.OnClickListener listener) {
        RxView.clicks(view)
                //两秒钟之内只取一个点击事件，防抖操作
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        listener.onClick(view);
                    }
                });
    }
}
