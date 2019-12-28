package com.junzhitian.rxjavaretrofitmvvm.utils;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.junzhitian.rxjavaretrofitmvvm.R;
import com.junzhitian.rxjavaretrofitmvvm.widget.CircleTransform;

/**
 * @author:TJ
 * @date:2017/9/19 19:34
 * @description:Glide封装
 */

public class GlideUtils {

    private final  String     TAG = this.getClass().getSimpleName();
    private static GlideUtils instance;

    public GlideUtils() {
    }

    public static GlideUtils getInstance() {
        if (instance == null) {
            instance = new GlideUtils();
        }
        return instance;
    }


    /**
     * 加载普通图片
     *
     * @param url
     * @param imageView
     */
    public void loadVerifyCodeUrl(Activity activity, String url, ImageView imageView) {
        Glide.with(activity)
                .load(url)
                .skipMemoryCache(true) // 不使用内存缓存
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.NONE) // 不使用磁盘缓存
//                .placeholder(R.drawable.register_verificationcode_bg)
                .into(imageView);
    }


    /**
     * 加载普通图片
     *
     * @param url
     * @param imageView
     */
    public void loadImage(Activity activity, String url, ImageView imageView, int placeholderRes) {
        Glide.with(activity)
                .load(url)
                .placeholder(placeholderRes)
                .into(imageView);
    }


    public void loadImage(FragmentActivity fragment, String url, ImageView imageView, int placeholderRes) {
        Glide.with(fragment)
                .load(url)
                .placeholder(placeholderRes)
                .into(imageView);
    }

    public void loadImage(Context context, String url, ImageView imageView, int placeholderRes) {
        Glide.with(context)
                .load(url)
                .placeholder(placeholderRes)
                .error(placeholderRes)
                .into(imageView);
    }


    /**
     * 加载圆形图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public void loadAvatar(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .fitCenter()
                .transform(new CircleTransform(context))
                .placeholder(R.drawable.ic_def_head)
                .into(imageView);
    }
}
