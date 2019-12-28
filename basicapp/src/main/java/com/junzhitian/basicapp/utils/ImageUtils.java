package com.junzhitian.basicapp.utils;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.media.ExifInterface;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import static android.os.Environment.DIRECTORY_DCIM;

/**
 * @作者:TJ
 * @时间:2019/9/7 22:23
 * @描述:
 */
public class ImageUtils {

    private static final String TAG = "ImageUtils";

    /**
     * 加载本地图片
     *
     * @param url
     * @return
     */
    public static Bitmap getLoacalBitmap(String url) {
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);  ///把流转化为Bitmap图片

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 截图
     *
     * @param view
     */
    public static void getScreenShot(View view) {
        Context context = view.getContext();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();  //启用DrawingCache并创建位图
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache()); //创建一个DrawingCache的拷贝，因为DrawingCache得到的位图在禁用后会被回收
        view.setDrawingCacheEnabled(false);  //禁用DrawingCahce否则会影响性能
        File dcimDirectory = Environment.getExternalStoragePublicDirectory(DIRECTORY_DCIM);
        File screenshot    = new File(dcimDirectory, System.currentTimeMillis() + ".jpg");
        if (saveBitmapToFile(bitmap, screenshot.getAbsolutePath())) {
            Toast.makeText(context, "截图成功,保存在" + screenshot.getAbsolutePath(), Toast.LENGTH_SHORT).show();
            // 其次把文件插入到系统图库
            try {
                MediaStore.Images.Media.insertImage(context.getContentResolver(),
                        screenshot.getAbsolutePath(), screenshot.getName(), null);
                // 最后通知图库更新
                context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + screenshot.getAbsolutePath())));
            } catch (Exception e) {
                e.printStackTrace();
                //ToastUtils.showShort("截图失败,请检查存储权限");
            }

        } else {
            //ToastUtils.showShort("截图失败,请检查存储权限");
        }
    }


    /**
     * 保存图片到SD卡
     */
    public static boolean saveBitmapToFile(Bitmap bitmap, String _file) {
        return saveBitmap(bitmap, _file, 100);
    }

    private static boolean saveBitmap(Bitmap bitmap, String _file, int quality) {
        BufferedOutputStream os = null;
        try {
            File   file      = new File(_file);
            int    end       = _file.lastIndexOf(File.separator);
            String _filePath = _file.substring(0, end);
            File   filePath  = new File(_filePath);
            if (!filePath.exists()) {// 路径不存在时尝试创建路径
                filePath.mkdirs();
            }
            file.createNewFile();
            os = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, os);
            return true;
        } catch (IOException e) {
            Log.e("-->", e.getMessage() + "  " + _file);
            e.printStackTrace();
        }
        finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    Log.e("-->", e.getMessage(), e);
                }
            }
        }
        return false;
    }


    /**
     * 读取资源目录下的图片
     *
     * @param context
     * @param uri
     * @return
     */
    public static Bitmap getBitmapForasses(Context context, String uri) {
        try {
            Bitmap bm = BitmapFactory.decodeStream(context.getAssets()
                    .open(uri));
            return bm;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取图片的旋转角度
     *
     * @param filePath
     * @return
     */
    public static int getRotateAngle(String filePath) {
        int rotate_angle = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(filePath);
            int           orientation   = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotate_angle = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotate_angle = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotate_angle = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rotate_angle;
    }

    /**
     * 旋转图片角度
     *
     * @param angle
     * @param bitmap
     * @return
     */
    public static Bitmap setRotateAngle(int angle, Bitmap bitmap) {

        if (bitmap != null) {
            Matrix m = new Matrix();
            m.postRotate(angle);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                    bitmap.getHeight(), m, true);
            return bitmap;
        }
        return bitmap;

    }

    /**
     * 转换为圆形状的bitmap
     *
     * @param source
     * @return
     */
    public static Bitmap createCircleImage(Bitmap source) {
        int   length = source.getWidth() < source.getHeight() ? source.getWidth() : source.getHeight();
        Paint paint  = new Paint();
        paint.setAntiAlias(true);
        Bitmap target = Bitmap.createBitmap(length, length, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(target);
        canvas.drawCircle(length / 2, length / 2, length / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(source, 0, 0, paint);
        return target;
    }


    /**
     * 图片压缩-质量压缩
     *
     * @param filePath 源图片路径
     * @return 压缩后的路径
     */

    public static String compressImage(String filePath) {

        //原文件
        File oldFile = new File(filePath);
        //压缩文件路径 照片路径/
        String targetPath = oldFile.getPath();
        int    quality    = 50;//压缩比例0-100
        Bitmap bm         = getSmallBitmap(filePath);//获取一定尺寸的图片
        int    degree     = getRotateAngle(filePath);//获取相片拍摄角度

        if (degree != 0) {//旋转照片角度，防止头像横着显示
            bm = setRotateAngle(degree, bm);
        }
        File outputFile = new File(targetPath);
        try {
            if (!outputFile.exists()) {
                outputFile.getParentFile().mkdirs();
                //outputFile.createNewFile();
            } else {
                outputFile.delete();
            }
            FileOutputStream out = new FileOutputStream(outputFile);
            bm.compress(Bitmap.CompressFormat.JPEG, quality, out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            return filePath;
        }
        return outputFile.getPath();
    }

    /**
     * 根据路径获得图片信息并按比例压缩，返回bitmap
     */
    public static Bitmap getSmallBitmap(String filePath) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;//只解析图片边沿，获取宽高
        BitmapFactory.decodeFile(filePath, options);
        // 计算缩放比
        options.inSampleSize = calculateInSampleSize(options, 480, 480);
        // 完整解析图片返回bitmap
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }


    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        final int height       = options.outHeight;
        final int width        = options.outWidth;
        int       inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio  = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }


    /**
     * 根据文件Uri获取路径
     *
     * @param context
     * @param uri
     * @return
     */
    public static String getFilePathByFileUri(Context context, Uri uri) {
        String filePath = null;
        Cursor cursor = context.getContentResolver().query(uri, null, null,
                null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                filePath = cursor.getString(cursor
                        .getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return filePath;
    }

    /**
     * 根据图片原始路径获取图片缩略图
     *
     * @param imagePath 图片原始路径
     * @param width     缩略图宽度
     * @param height    缩略图高度
     * @return
     */
    public static Bitmap getImageThumbnail(String imagePath, int width, int height) {
        Bitmap                bitmap;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;//不加载直接获取Bitmap宽高
        // 获取这个图片的宽和高，注意此处的bitmap为null
        bitmap = BitmapFactory.decodeFile(imagePath, options);
        if (bitmap == null) {
            // 计算缩放比
            int h        = options.outHeight;
            int w        = options.outWidth;
            int beWidth  = w / width;
            int beHeight = h / height;
            int rate     = 1;
            if (beWidth < beHeight) {
                rate = beWidth;
            } else {
                rate = beHeight;
            }
            if (rate <= 0) {//图片实际大小小于缩略图,不缩放
                rate = 1;
            }
            options.inSampleSize = rate;
            options.inJustDecodeBounds = false;
            // 重新读入图片，读取缩放后的bitmap，注意这次要把options.inJustDecodeBounds 设为 false
            bitmap = BitmapFactory.decodeFile(imagePath, options);
            // 利用ThumbnailUtils来创建缩略图，这里要指定要缩放哪个Bitmap对象
            bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height,
                    ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        }
        return bitmap;
    }


    /**
     * 获取根目录
     *
     * @return
     */
    public static File getRootPath() {
        File path = null;
        if (sdCardIsAvailable()) {
            path = Environment.getExternalStorageDirectory(); // 取得sdcard文件路径
        } else {
            path = Environment.getDataDirectory();
        }
        return path;
    }

    /**
     * SD卡是否可用.
     */
    public static boolean sdCardIsAvailable() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File sd = new File(Environment.getExternalStorageDirectory().getPath());
            return sd.canWrite();
        } else
            return false;
    }

    /**
     * 图片按比例大小压缩方法
     *
     * @param image （根据Bitmap图片压缩）
     * @return
     */
    public static Bitmap compressScale(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);

        // 判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
        if (baos.toByteArray().length / 1024 > 1024) {
            baos.reset();// 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, 80, baos);// 这里压缩50%，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream  isBm    = new ByteArrayInputStream(baos.toByteArray());
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        LogUtils.e(TAG, w + "---------------" + h);
        // 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        // float hh = 800f;// 这里设置高度为800f
        // float ww = 480f;// 这里设置宽度为480f
        float hh = 512f;
        float ww = 512f;
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;// be=1表示不缩放
        if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) { // 如果高度高的话根据高度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be; // 设置缩放比例
        // newOpts.inPreferredConfig = Config.RGB_565;//降低图片从ARGB888到RGB565

        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        isBm = new ByteArrayInputStream(baos.toByteArray());
        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);

        return compressImage(bitmap);// 压缩好比例大小后再进行质量压缩

        //return bitmap;
    }


    /**
     * 图片按比例大小压缩方法
     *
     * @param srcPath （根据路径获取图片并压缩）
     * @return
     */
    public static Bitmap CompressImageToBitmap(String srcPath) {
        LogUtils.e(TAG, "CompressImageToBitmap: 开始图片按比例大小压缩");
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);// 此时返回bm为空

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        // 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 800f;// 这里设置高度为800f
        float ww = 480f;// 这里设置宽度为480f
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;// be=1表示不缩放
        if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;// 设置缩放比例
        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        LogUtils.e(TAG, "CompressImageToBitmap: 图片按比例大小压缩完成，开始质量压缩");
        return compressImage(bitmap);// 压缩好比例大小后再进行质量压缩
    }


    /**
     * 质量压缩方法
     *
     * @param image
     * @return
     */
    public static Bitmap compressImage(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 90;

        while (baos.toByteArray().length / 1024 > 100) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset(); // 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;// 每次都减少10
        }
        ByteArrayInputStream isBm   = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap               bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
        LogUtils.e(TAG, "compressImage: 质量压缩完成开始生成图片文件");
        return bitmap;
    }

    /**
     * Bitmap对象保存味图片文件
     *
     * @param bitmap
     */
    public static File saveBitmapFile(Context context, Bitmap bitmap) {
        File file = new File(FileUtils.getCacheFile(context), createImageName());//将要保存图片的路径
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            LogUtils.e(TAG, "saveBitmapFile: 图片文件生成路径：" + file.getAbsolutePath());
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * 生成图片名字
     *
     * @return
     */
    public static String createImageName() {
        String ts = String.valueOf(System.currentTimeMillis());
        return StrUtils.plusString("small_", ts, ".png");
    }


    /**
     * 获取网络视频的第一帧
     *
     * @param videoUrl
     * @return
     */
    public static Bitmap getNetVideoBitmap(String videoUrl) {
        Bitmap                 bitmap    = null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            //根据url获取缩略图
            retriever.setDataSource(videoUrl, new HashMap());
            //获得第一帧图片
            bitmap = retriever.getFrameAtTime();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        finally {
            retriever.release();
        }
        return bitmap;
    }

    /**
     * 获取本地视频的第一帧
     *
     * @param videoPath
     * @return
     */
    public static Bitmap getLocalVideoBitmap(String videoPath) {
        MediaMetadataRetriever media = new MediaMetadataRetriever();
        media.setDataSource(videoPath);// videoPath 本地视频的路径
        Bitmap bitmap = media.getFrameAtTime(1, MediaMetadataRetriever.OPTION_CLOSEST_SYNC);
        return bitmap;
    }
}
