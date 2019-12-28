package com.junzhitian.basicapp.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;


/**
 * 视图工具,视图相关工具类
 *
 * @author cnsunrun
 */
public class UiUtils {

    public static int WHD[];

    /**
     * 获取设备宽高密度等信息信息
     *
     * @param act
     */
    public static int[] initWHD(Activity act) {
        if (WHD == null)
            WHD = WHD(act);
        return WHD;
    }


    /**
     * 设置视图宽高(含weight属性时无效)
     *
     * @param view
     * @param W
     * @param H
     */
    public static void setViewWH(View view, int W, int H) {
        if (view == null)
            return;
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params == null)
            return;
        if (W > 0)
            params.width = W;
        if (H > 0)
            params.height = H;
        view.setLayoutParams(params);
    }

    /**
     * dp转px
     *
     * @param context
     * @param dp
     * @return
     */
    public static int dp2px(Context context, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                context.getResources().getDisplayMetrics());
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @param  （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 获取宽高密度信息
     *
     * @param context
     * @return [0]宽 [1]高 [2]密度
     */
    public static int[] WHD(@NonNull Context context) {
        DisplayMetrics outMetrics = new DisplayMetrics();
        WindowManager mm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        mm.getDefaultDisplay().getMetrics(outMetrics);
        return new int[]
                {outMetrics.widthPixels, outMetrics.heightPixels,
                        (int) outMetrics.density};
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

    public static int[] getBitmapWH(Bitmap bitmap) {
        return new int[]
                {bitmap.getWidth(), bitmap.getHeight()};
    }


    public static void addCut(final View add, final View cut,
                              final TextView numText, final int max) {
        if (add == null || cut == null || numText == null)
            return;
        // if (Utils.valueOf(numText.getText().toString(), 0) > max) {
        // numText.setText(Integer.toString(max));
        // }
        cut.setEnabled(true);
        add.setEnabled(true);
        add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = Integer.parseInt(numText.getText().toString());
                if (num + 1 <= max) {
                    numText.setText("" + (num + 1));
                }
                add.setEnabled(num + 1 <= max);
                cut.setEnabled(true);
            }
        });
        cut.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = Integer.parseInt(numText.getText().toString());
                if (num - 1 > 0) {
                    numText.setText("" + (num - 1));
                    add.setEnabled(true);
                }
                if (num - 1 <= 1) {
                    numText.setText("1");
                    cut.setEnabled(false);
                }
            }
        });
    }

    public interface OnNumberChangeListener {
        public boolean onChange(View view, int num);
    }

    public static void addCut(View add, final View cut, final TextView numText,
                              int max, final OnNumberChangeListener listener) {
        OnClickListener lin = new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                int num = Integer.parseInt(numText.getText().toString());
                if (v == cut) {
                    if (num > 1) {
                        numText.setText("" + (num - 1));
                        if (listener != null) {
                            listener.onChange(numText, num - 1);
                        }
                    }
                } else {
                    numText.setText("" + (num + 1));
                    if (listener != null) {
                        listener.onChange(numText, num + 1);
                    }
                }
                cut.setEnabled(true);

            }
        };
        add.setOnClickListener(lin);
        cut.setOnClickListener(lin);
        numText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                // TODO Auto-generated method stub
                // if (numText.getText().length() == 0 ||
                // numText.getText().toString().equals("0"))
                // numText.setText("1");
                if (numText.getText().toString().equals("")
                        || numText.getText().toString().equals("0")) {
                    numText.setText("1");
                }

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    /**
     * 当EditText获得焦点时自动隐藏提示文字
     *
     * @param
     */
    public static void hideHintTextOnFocus(final EditText... edits) {
        for (final EditText edit : edits) {
            edit.setOnFocusChangeListener(new EditText.OnFocusChangeListener() {
                String hintText = edit.getHint().toString();

                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        edit.setHint("");
                    } else {
                        edit.setHint(hintText);
                    }
                }
            });
        }
    }

    /**
     * 分享相关方法
     *
     * @param context
     * @param title
     *            分享标题
     * @param drawableId
     *            图片
     * @param TitleUrl
     *            //链接
     * @param content
     *            内容
     * @param platform
     *            分享到的品台标示
     */
    // public static void showShare(Context context, String title, int
    // drawableId, String TitleUrl,
    // String content, String platform) {
    // ShareSDK.initSDK(context);
    // OnekeyShare oks = new OnekeyShare();
    // // oks.setLatitude(23.056081f);
    // // oks.setLongitude(113.385708f);
    // // 关闭sso授权
    // // oks.disableSSOWhenAuthorize();
    // oks.setPlatform(platform);
    // // oks.setSilent(true);
    // // 分享时Notification的图标和文字
    // // oks.setNotification(R.drawable.ic_launcher,
    // // context.getString(R.string.app_name));
    // // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
    // Bitmap bit = BitmapFactory.decodeResource(context.getResources(),
    // drawableId);
    // String path = context.getCacheDir() + "/" + drawableId + ".png";
    // saveBitmapToFile(bit, path);
    // oks.setImagePath(path);// 确保SDcard下面存在此张图片
    // oks.setTitle(title);
    // // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
    // oks.setTitleUrl(TitleUrl);
    // // text是分享文本，所有平台都需要这个字段
    // oks.setText(content);
    // // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
    // // url仅在微信（包括好友和朋友圈）中使用
    // oks.setUrl(TitleUrl);
    // // comment是我对这条分享的评论，仅在人人网和QQ空间使用
    // // oks.setComment("我是测试评论文本");
    // // site是分享此内容的网站名称，仅在QQ空间使用
    // oks.setSite(context.getString(R.string.app_name));
    // // siteUrl是分享此内容的网站地址，仅在QQ空间使用
    // oks.setSiteUrl(TitleUrl);
    // // 启动分享GUI
    // oks.show(context);
    // }

    /**
     * 调用拍照
     *
     * @param activity
     * @return
     */
    public static Uri startCamera(Activity activity) {
        Uri    cameraUri         = null;
        Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 判断存储卡是否可以用，可用进行存储
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            File path = Environment
                    .getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
            File file = new File(path, System.currentTimeMillis() + ".jpg");
            cameraUri = Uri.fromFile(file);
            intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, cameraUri);
        }
        activity.startActivityForResult(intentFromCapture, 1);
        return cameraUri;
    }

    /**
     * 选择图片
     *
     * @param activity
     */
    public static void selectPhoto(Activity activity) {
        Intent intentFromGallery = new Intent();
        intentFromGallery.setType("image/*"); // 设置文件类型
        intentFromGallery.setAction(Intent.ACTION_GET_CONTENT);
        Log.d("weiquan ", "相册");
        activity.startActivityForResult(intentFromGallery, 2);
    }


    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    public static void startPhotoZoom(Activity activity, Uri uri, int W, int H) {
        Log.d("weiquan ", "图片剪裁");
        Intent intent = new Intent("com.android.camera.action.CROP");
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            //String url = FileUtils.getSaveFile(activity, uri);
            //intent.setDataAndType(Uri.fromFile(new File(url)), "image/*");
        } else {
            intent.setDataAndType(uri, "image/*");
        }
        //intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", W);
        intent.putExtra("outputY", H);
        intent.putExtra("return-data", true);
        activity.startActivityForResult(intent, 0);
    }


    /**
     * 获取图片文件的信息，是否旋转了90度，如果是则反转
     *
     * @param bitmap 需要旋转的图片
     * @param path   图片的路径
     */
    public static Bitmap reviewPicRotate(Bitmap bitmap, String path) {
        int degree = getPicRotate(path);
        if (degree != 0) {
            Matrix m      = new Matrix();
            int    width  = bitmap.getWidth();
            int    height = bitmap.getHeight();
            m.setRotate(degree); // 旋转angle度
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, m, true);// 从新生成图片
        }
        return bitmap;
    }

    /**
     * 读取图片文件旋转的角度
     *
     * @param path 图片绝对路径
     * @return 图片旋转的角度
     */
    public static int getPicRotate(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    private final static int UPPER_LEFT_X = 0;
    private final static int UPPER_LEFT_Y = 0;

    public static Drawable convertViewToDrawable(View view) {
        int spec = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        view.measure(spec, spec);
        view.layout(UPPER_LEFT_X, UPPER_LEFT_Y, view.getMeasuredWidth(),
                view.getMeasuredHeight());
        Bitmap b = Bitmap.createBitmap(view.getMeasuredWidth(),
                view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        c.translate(-view.getScrollX(), -view.getScrollY());
        view.draw(c);
        view.setDrawingCacheEnabled(true);
        Bitmap cacheBmp = view.getDrawingCache();
        Bitmap viewBmp  = cacheBmp.copy(Bitmap.Config.ARGB_8888, true);
        cacheBmp.recycle();
        view.destroyDrawingCache();
        return new BitmapDrawable(viewBmp);
    }

    public static Bitmap convertView2Bitmap(View view) {
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();  //启用DrawingCache并创建位图
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache()); //创建一个DrawingCache的拷贝，因为DrawingCache得到的位图在禁用后会被回收
        view.setDrawingCacheEnabled(false);
        return bitmap;
    }

    /**
     * Save Bitmap to a file.保存图片到SD卡。
     *
     * @param bitmap
     * @return error message if the saving is failed. null if the saving is
     * successful.
     * @throws IOException
     */
    public static boolean saveBitmapToFile(Bitmap bitmap, String _file) {
        return saveBitmap(bitmap, _file, 100);
    }

    private static boolean saveBitmap(Bitmap bitmap, String _file, int quality) {
        BufferedOutputStream os = null;
        try {
            File file = new File(_file);
            // String _filePath_file.replace(File.separatorChar +
            // file.getName(), "");
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
     * Save Bitmap to a file.保存图片到SD卡。
     *
     * @throws IOException
     */
    public static String saveBitmapToFile(String aimpath, String _file,
                                          int quality) {
        File file = new File(_file);
        if (file.exists() && file.length() != 0)
            return _file;
        if (saveBitmap(BitmapFactory.decodeFile(aimpath), _file, quality)) {
            return _file;
        }
        return aimpath;
    }

    // /**
    // * 显示加载对话框
    // * @param context
    // */
    // public static Dialog showLoadDialog(Context context,String msg)
    // {
    // if (dialog != null) dialog.cancel();
    // View
    // rootView=LayoutInflater.from(context).inflate(R.layout.loading_dialog,
    // null);
    // TextView msgText=(TextView) rootView.findViewById(R.id.msg);
    // msgText.setText(msg);
    // // ImageView img=(ImageView) rootView.findViewById(R.id.loading_anim);
    // // playBackAnim(img,R.anim.dialog_loding);
    // dialog=new Dialog(context,R.style.CustomDialog);
    // dialog.setContentView(rootView);
    // dialog.show();
    // return dialog;
    // }

    static Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (dialog != null)
                dialog.cancel();
            dialog = null;
        }

        ;
    };

    private static Dialog                   dialog;
    private static HashMap<Context, Dialog> dialogs = new HashMap<Context, Dialog>();


    /**
     * 获取图片宽高
     *
     * @param path
     * @return
     */
    public static int[] gePicWH(String path) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap bmp = BitmapFactory.decodeFile(path, options);
        // 这里返回的bmp是null
        return new int[]
                {options.outWidth, options.outHeight};
    }

    /**
     * 根据指定的图像路径和大小来获取缩略图 此方法有两点好处： 1.
     * 使用较小的内存空间，第一次获取的bitmap实际上为null，只是为了读取宽度和高度，
     * 第二次读取的bitmap是根据比例压缩过的图像，第三次读取的bitmap是所要的缩略图。 2.
     * 缩略图对于原图像来讲没有拉伸，这里使用了2.2版本的新工具ThumbnailUtils，使 用这个工具生成的图像不会被拉伸。
     *
     * @param imagePath 图像的路径
     * @param width     指定输出图像的宽度
     * @param height    指定输出图像的高度
     * @return 生成的缩略图
     */
    public static Bitmap getImageThumbnail(String imagePath, int width,
                                           int height) {
        Bitmap                bitmap  = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        // 获取这个图片的宽和高，注意此处的bitmap为null
        bitmap = BitmapFactory.decodeFile(imagePath, options);
        options.inJustDecodeBounds = false; // 设为 false
        // 计算缩放比
        int h        = options.outHeight;
        int w        = options.outWidth;
        int beWidth  = w / width;
        int beHeight = h / height;
        int be       = 1;
        if (beWidth < beHeight) {
            be = beWidth;
        } else {
            be = beHeight;
        }
        if (be <= 0) {
            be = 1;
        }
        options.inSampleSize = be;
        // 重新读入图片，读取缩放后的bitmap，注意这次要把options.inJustDecodeBounds 设为 false
        bitmap = BitmapFactory.decodeFile(imagePath, options);
        // 利用ThumbnailUtils来创建缩略图，这里要指定要缩放哪个Bitmap对象
        bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height,
                ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        return bitmap;
    }

    public static Bitmap getImageThumbnail(Bitmap bitmap, int width, int height) {
        // int w = bitmap.getWidth();
        // int h = bitmap.getHeight();
        // int beWidth = w / width;
        // int beHeight = h / height;
        // bitmap=Bitmap.createScaledBitmap(bitmap, beWidth, beHeight, false);
        return ThumbnailUtils.extractThumbnail(bitmap, width, height);
    }

    /**
     * 压缩图片
     *
     * @param bitmap  位图对象
     * @param quality 压缩质量 100为不压缩
     * @return
     */
    public static Bitmap compressImg(Bitmap bitmap, int quality) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
            // 把压缩后的数据baos存放到ByteArrayInputStream中
            ByteArrayInputStream isBm = new ByteArrayInputStream(
                    baos.toByteArray());
            // 把ByteArrayInputStream数据生成图片
            return BitmapFactory.decodeStream(isBm);
        } catch (Exception e) {
        }
        return bitmap;
    }

    /**
     * 压缩图片
     *
     * @param imgPath  图片绝对路径
     * @param quality  压缩质量 100为不压缩
     * @return 压缩成功则返回压缩后图片路径, 否则返回原图路径
     */
    public static String compressImg(String imgPath, String aimPath, int quality) {

        Bitmap  bitmap = compressImg(BitmapFactory.decodeFile(imgPath), quality);
        boolean flag   = saveBitmapToFile(bitmap, aimPath);
        return flag ? aimPath : imgPath;
    }

    public static int[] scalWH(String path, float scal, int minW, int minH) {
        int WH[] = UiUtils.gePicWH(path);
        int W    = (int) (WH[0] * scal), H = (int) (WH[1] * scal);
        if (W < minW || H < minH) {
            return WH;
        }
        return new int[]
                {W, H};
    }


    /**
     * 测量一个粗劣的文字行数
     *
     * @param act
     * @param paint
     * @param txt
     * @return
     */
    public static float meauTextLine(Activity act, TextPaint paint, String txt,
                                     int max) {
        if (txt.length() == 0)
            return 0;
        float width     = paint.measureText("字");
        int   WH[]      = initWHD(act);
        float numOfLine = WH[0] / width;// 一行多少字
        float lineNum   = txt.length() / numOfLine;// 粗略的行数
        int   num       = 0;
        for (int i = 0, len = txt.length(); i < len; i++) {// 统计换行和回车的个数,
            char ch = txt.charAt(i);
            if (ch == '\r' || ch == '\n') {
                num++;
                if (lineNum + num >= max)
                    return lineNum + num;
            }
        }
        lineNum = lineNum + num;
        return lineNum;

    }

    /**
     * @param listView
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

}
