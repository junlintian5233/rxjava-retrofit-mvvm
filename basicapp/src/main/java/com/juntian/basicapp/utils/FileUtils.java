package com.juntian.basicapp.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public final class FileUtils {

    /**
     * 检查SD卡
     *
     * @return
     */
    public static boolean checkSDcard() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    /**
     * 获取缓存目录
     *
     * @return
     */
    public static String getCacheFile(Context context) {
        if (checkSDcard()) {
            return context.getExternalCacheDir().getAbsolutePath() + File.separator;
        } else {
            return context.getCacheDir().getAbsolutePath() + File.separator;
        }
    }


    /**
     * 保存文件
     *
     * @param folderPath
     * @param fileNmae
     * @return
     */
    public static File getSaveFile(String folderPath, String fileNmae) {
        File file = new File(getSavePath(folderPath) + File.separator
                + fileNmae);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static String getSavePath(String folderName) {
        return getSaveFolder(folderName).getAbsolutePath();
    }


    public static File getSaveFolder(String folderName) {
        File file = new File(Environment.getExternalStorageDirectory()
                .getAbsoluteFile()
                + File.separator
                + folderName
                + File.separator);
        file.mkdirs();
        return file;
    }


    /**
     * 删除文件
     *
     * @param file
     */
    public static void deleteFile(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                File f = files[i];
                deleteFile(f);
            }
            file.delete();//如要保留文件夹，只删除文件，请注释这行
        } else if (file.exists()) {
            file.delete();
        }
    }

    /**
     * 获取数据保存目录
     *
     * @param context
     * @return
     */
    public static String getDBPath(Context context) {
        if (!checkSDcard()) {// 如果不存在
            LogUtils.e("SD卡管理:", "SD卡不存在,请加载SD卡");
            return null;
        } else {// 如果存在
            // 数据库所在目录
            return Environment.getExternalStorageDirectory().getAbsolutePath()
                    + File.separator
                    + context.getPackageName()
                    + File.separator
                    + "db";
        }
    }


    /**
     * 追加写入字符串为文件
     *
     * @param str
     */
    public static void writeAppendStrToFile(Context context, String str) {
        try {
            String     allData = getCacheFile(context) + File.separator + TimeUtils.timeStampToDate(System.currentTimeMillis(), "yyyy-MM-dd每日闹钟更新数据") + ".txt";
            FileWriter fw      = new FileWriter(allData, true);
            fw.flush();
            fw.write(str);
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 写入字符串为文件
     *
     * @param str
     */
    public static void writeStrToFile(Context context, String str) {
        try {
            FileWriter fw = new FileWriter(getCacheFile(context) + File.separator +
                    TimeUtils.timeStampToDate(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss") + ".txt");//SD卡中的路径
            fw.flush();
            fw.write(str);
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}