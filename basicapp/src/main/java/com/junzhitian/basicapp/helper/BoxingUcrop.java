/*
 *  Copyright (C) 2017 Bilibili
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.junzhitian.basicapp.helper;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bilibili.boxing.Boxing;
import com.bilibili.boxing.loader.IBoxingCrop;
import com.bilibili.boxing.model.config.BoxingConfig;
import com.bilibili.boxing.model.config.BoxingCropOption;
import com.bilibili.boxing.utils.BoxingFileHelper;
import com.bilibili.boxing_impl.ui.BoxingActivity;

import com.junzhitian.basicapp.R;
import com.yalantis.ucrop.UCrop;

import java.util.Locale;

/**
 * use Ucrop(https://github.com/Yalantis/uCrop) as the implement for {@link IBoxingCrop}
 *
 * @author ChenSL
 */
public class BoxingUcrop implements IBoxingCrop {

    @Override
    public void onStartCrop(Context context, Fragment fragment, @NonNull BoxingCropOption cropConfig,
                            @NonNull String path, int requestCode) {
        Uri uri = new Uri.Builder()
                .scheme("file")
                .appendPath(path)
                .build();
        UCrop.Options crop = new UCrop.Options();
        // do not copy exif information to crop pictures
        // because png do not have exif and png is not Distinguishable
        crop.setCompressionFormat(Bitmap.CompressFormat.PNG);
        crop.withMaxResultSize(cropConfig.getMaxWidth(), cropConfig.getMaxHeight());
        crop.withAspectRatio(cropConfig.getAspectRatioX(), cropConfig.getAspectRatioY());

        UCrop.of(uri, cropConfig.getDestination())
                .withOptions(crop)

                .start(context, fragment, requestCode);
    }

    @Override
    public Uri onCropFinish(int resultCode, Intent data) {
        if (data == null) {
            return null;
        }
        Throwable throwable = UCrop.getError(data);
        if (throwable != null) {
            return null;
        }
        return UCrop.getOutput(data);
    }

    /**
     * 单张图片配置-比例1:1
     *
     * @param context
     * @return
     */
    public static BoxingConfig singleImageConfig(Context context) {
        //获取文件夹的路径  如果没有的这个路径的话就创建一个默认的路径
        String cachePath = BoxingFileHelper.getCacheDir(context);
        if (TextUtils.isEmpty(cachePath)) {
            Toast.makeText(context, R.string.storage_deny, Toast.LENGTH_SHORT).show();
            return new BoxingConfig(BoxingConfig.Mode.SINGLE_IMG);
        }
        Uri destUri = new Uri.Builder()
                .scheme("file")
                .appendPath(cachePath)
                .appendPath(String.format(Locale.US, "%s.jpg", System.currentTimeMillis()))
                .build();
        //这里设置的config的mode是sngle_img  就是单选图片的模式  支持相机
        BoxingConfig singleCropImgConfig = new BoxingConfig(BoxingConfig.Mode.SINGLE_IMG)
                .needCamera(R.drawable.open_camera)
                .needGif()
                .withCropOption(new BoxingCropOption(destUri).withMaxResultSize(400, 800).aspectRatio(1, 1));
        return singleCropImgConfig;
    }


    /**
     * 单张图片配置-比例2.5:1
     *
     * @param context
     * @return
     */
    public static BoxingConfig supplyDemandBgConfig(Context context) {
        //获取文件夹的路径  如果没有的这个路径的话就创建一个默认的路径
        String cachePath = BoxingFileHelper.getCacheDir(context);
        if (TextUtils.isEmpty(cachePath)) {
            Toast.makeText(context, R.string.storage_deny, Toast.LENGTH_SHORT).show();
            return new BoxingConfig(BoxingConfig.Mode.SINGLE_IMG);
        }
        Uri destUri = new Uri.Builder()
                .scheme("file")
                .appendPath(cachePath)
                .appendPath(String.format(Locale.US, "%s.jpg", System.currentTimeMillis()))
                .build();
        //这里设置的config的mode是sngle_img  就是单选图片的模式  支持相机
        BoxingConfig singleCropImgConfig = new BoxingConfig(BoxingConfig.Mode.SINGLE_IMG)
                .needCamera(R.drawable.open_camera)
                .needGif()
                .withCropOption(new BoxingCropOption(destUri).withMaxResultSize(400, 800).aspectRatio(2.5f, 1));
        return singleCropImgConfig;
    }


    /**
     * 单张图片配置-身份证比例
     *
     * @param context
     * @return
     */
    public static BoxingConfig idCardConfig(Context context) {
        //获取文件夹的路径  如果没有的这个路径的话就创建一个默认的路径
        String cachePath = BoxingFileHelper.getCacheDir(context);
        if (TextUtils.isEmpty(cachePath)) {
            Toast.makeText(context, R.string.storage_deny, Toast.LENGTH_SHORT).show();
            return new BoxingConfig(BoxingConfig.Mode.SINGLE_IMG);
        }
        Uri destUri = new Uri.Builder()
                .scheme("file")
                .appendPath(cachePath)
                .appendPath(String.format(Locale.US, "%s.jpg", System.currentTimeMillis()))
                .build();
        //这里设置的config的mode是sngle_img  就是单选图片的模式  支持相机
        BoxingConfig singleCropImgConfig = new BoxingConfig(BoxingConfig.Mode.SINGLE_IMG)
                .needCamera(R.drawable.open_camera)
                .needGif()
                .withCropOption(new BoxingCropOption(destUri).withMaxResultSize(480, 800).aspectRatio(1.58f, 1));
        return singleCropImgConfig;
    }

    /**
     * 多张图片配置
     *
     * @param context
     * @return
     */
    public static Boxing multiImageConfig(Context context, int size) {
        BoxingConfig mulitImgConfig = new BoxingConfig(BoxingConfig.Mode.MULTI_IMG)
                .needCamera(R.drawable.open_camera)
                .withMaxCount(size);
        return Boxing.of(mulitImgConfig).withIntent(context, BoxingActivity.class);
    }

    /**
     * 视频选择
     *
     * @param context
     * @return
     */
    public static Boxing videoConfig(Context context) {
        BoxingConfig videoConfig = new BoxingConfig(BoxingConfig.Mode.VIDEO);
        return Boxing.of(videoConfig).withIntent(context, BoxingActivity.class);
    }
}
