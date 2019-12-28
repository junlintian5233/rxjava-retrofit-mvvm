package com.junzhitian.basicapp.config;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.module.GlideModule;
import com.junzhitian.basicapp.utils.FileUtils;


/**
 * @作者:TJ
 * @时间:2017/4/1 上午 9:54
 * @描述:Glide配置
 */
public class GlideConfig implements GlideModule {

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        //设置图片的显示格式ARGB_8888(指图片大小为32bit)
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
        //设置磁盘缓存目录（和创建的缓存目录相同）
        String downloadDirectoryPath = FileUtils.getCacheFile(context);
        //设置缓存的大小为100M
        int cacheSize = 100 * 1000 * 1000;
        builder.setDiskCache(new DiskLruCacheFactory(downloadDirectoryPath, cacheSize));
    }


    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}
