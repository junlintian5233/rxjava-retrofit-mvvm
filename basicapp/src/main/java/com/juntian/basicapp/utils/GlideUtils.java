package com.juntian.basicapp.utils;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.juntian.basicapp.R;
import com.juntian.basicapp.widget.glidetransform.CircleTransform;
import com.juntian.basicapp.widget.glidetransform.GlideRoundTransform;


/**
 * @AUTHOR:TJ
 * @DATE:2017/9/19 19:34
 * @DESCRIPTION:GLIDE封装
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
                .dontAnimate()
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
                .dontAnimate()
                .into(imageView);
    }


    public void loadImage(FragmentActivity fragment, String url, ImageView imageView, int placeholderRes) {
        Glide.with(fragment)
                .load(url)
                .placeholder(placeholderRes)
                .dontAnimate()
                .into(imageView);
    }

    public void loadImage(Context context, String url, ImageView imageView, int placeholderRes) {
        Glide.with(context)
                .load(url)
                .placeholder(placeholderRes)
                .error(placeholderRes)
                .dontAnimate()
                .into(imageView);
    }


    /**
     * 加载圆角图片
     *
     * @param context
     * @param url
     * @param imageView
     * @param placeholderRes
     */
    public void loadRadiusImage(Context context, String url, ImageView imageView, int radius, int placeholderRes) {
        Glide.with(context)
                .load(url)
                .transform(new CenterCrop(context), new GlideRoundTransform(context, radius))
                .error(placeholderRes)
                .dontAnimate()
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
