package com.juntian.basicapp.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;

import com.alibaba.fastjson.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author : tj
 * @date : 2017/5/27 17:29
 * @description : 字符串处理工具类
 */
public class StrUtils {

    private static String hexString = "0123456789ABCDEF";

    /**
     * 拼接字符串
     *
     * @param strs
     * @return
     */
    public static String plusString(String... strs) {
        StringBuilder builder = new StringBuilder();
        String[]      var2    = strs;
        int           var3    = strs.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            String str = var2[var4];
            if (!TextUtils.isEmpty(str)) {
                builder.append(str);
            }
        }
        return builder.toString();
    }

    /**
     * 匹配是否手机号码，是 True
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9]|17[0|1|2|3|5|6|7|8|9])\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 匹配邮箱格式是否正确
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        Pattern p = Pattern.compile("^([a-zA-Z0-9_\\.-])+@(([a-zA-Z0-9-])+\\.)+([a-zA-Z0-9]{2,4})+$");
        Matcher m = p.matcher(email);
        return m.matches();
    }


    /**
     * 隐藏手机号
     *
     * @param phone
     * @return
     */
    public static String encryptionPhone(String phone) {
        if (Tools.isEmpty(phone)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(phone.substring(0, 3));
        sb.append("****");
        sb.append(phone.substring(7, 11));
        return sb.toString();
    }


    /**
     * 判断密码复杂度以及长度
     *
     * @param str
     * @return
     */
    public static boolean isValidPassword(String str) {
        if (str.length() < 6 || str.length() > 30) {
            return false;
        }
        String  regExp = "^^(?![a-zA-z]+$)(?!\\\\d+$)(?![!@#$%^&*_-]+$)(?![a-zA-z\\\\d]+$)(?![a-zA-z!@#$%^&*_-]+$)(?![\\\\d!@#$%^&*_-]+$)[a-zA-Z\\\\d!@#$%^&*_-]+$";
        Pattern p      = Pattern.compile(regExp);
        Matcher m      = p.matcher(str);
        return m.matches();
    }

    /**
     * 复制文本到系统剪切板
     */
    public static void copyTextToSystem(Context context, String text) {
        ClipboardManager cm        = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData         mClipData = ClipData.newPlainText("Label", text);
        cm.setPrimaryClip(mClipData);
    }

    /**
     * 从系统剪切板获取文本
     */
    public static String getSystemCopyText(Context context) {
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        return cm.getPrimaryClip().toString();
    }

    /**
     * Base 64加密
     *
     * @param str
     * @return
     */
    public static String base64Encode(String str) {
        if (str == null)
            return null;
        return new String(Base64.encode(str.getBytes(), Base64.DEFAULT)).trim();
    }

    /**
     * Base 64解密
     *
     * @param str
     * @return
     */
    public static String base64Decode(String str) {
        if (str == null)
            return null;
        return new String(Base64.decode(str, Base64.DEFAULT));
    }

    /**
     * 对字符串进行MD5加密
     *
     * @param str
     * @return
     */
    public static String getMD5(String str) {
        try {
            return getMD5(str.getBytes("utf-8"));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }

    public static String getMD5(byte[] hash) {
        try {
            hash = MessageDigest.getInstance("MD5").digest(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (Exception e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10)
                hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }

    /**
     * 格式化金额
     *
     * @param money
     * @return
     */
    public static String formatMoney(String money) {
        StringBuffer sb    = new StringBuffer(money);
        int          index = money.indexOf(".");
        index = index == -1 ? money.length() : index;
        for (int len = money.length(), i = len - 1; i >= 0; i--)
            if (i < index && (len - i - 1 - 2) % 3 == 0 && i != 0)
                sb.insert(i, ',');
        return sb.toString();
    }


    /**
     * 暴力解析:Alibaba fastjson
     *
     * @param test
     * @return
     */
    public final static boolean isJSONValid(String test) {
        try {
            JSONObject.parseObject(test);
        } catch (com.alibaba.fastjson.JSONException ex) {
            try {
                JSONObject.parseArray(test);
            } catch (com.alibaba.fastjson.JSONException ex1) {
                return false;
            }
        }
        return true;
    }
}
