package com.juntian.basicapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * @作者: TJ
 * @时间: 2018/8/10 15:45
 * @描述:
 */
public class HttpUtils {


    /**
     * 判断是否有网络连接
     *
     * @param context
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo         mNetworkInfo         = mConnectivityManager.getActiveNetworkInfo();
        if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {   //判断网络连接是否打开
            return mNetworkInfo.isConnected();
        }
        return false;
    }


    /**
     * 网络请求-Get
     *
     * @param urlString
     * @return
     */
    public static byte[] doGetRequest(Context context, String urlString) {
        ByteArrayOutputStream baos       = new ByteArrayOutputStream();
        InputStream           is         = null;
        byte[]                result     = null;
        URL                   url        = null;
        HttpURLConnection     connection = null;
        try {
            url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5 * 1000);
            connection.setReadTimeout(5 * 1000);
            //connection.setRequestProperty("token", SpUtils.getInstance(context).getToken());
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                is = connection.getInputStream();
                byte[] buffer = new byte[1024];
                int    length = 0;
                while ((length = is.read(buffer)) != -1) {
                    baos.write(buffer, 0, length);
                    baos.flush();
                }
                result = baos.toByteArray();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (connection != null) {
                    connection.disconnect();
                }
                if (baos != null) {
                    baos.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 网络请求-Post
     *
     * @param urlString
     * @param params
     * @return
     */
    public static byte[] doPostRequest(String urlString, Map<String, String> params) {
        LogUtils.e("HttpUtils", "doPostRequest: 开始网络加载啦");
        ByteArrayOutputStream baos       = new ByteArrayOutputStream();
        InputStream           is         = null;
        byte[]                result     = null;
        URL                   url        = null;
        HttpURLConnection     connection = null;
        OutputStream          out        = null;
        StringBuilder         sb         = new StringBuilder();
        if (params != null && params.size() != 0) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                try {
                    sb.append(entry.getKey());
                    sb.append("=");
                    sb.append(URLEncoder.encode(String.valueOf(entry.getValue()), "utf-8"));
                    sb.append("&");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            sb.deleteCharAt(sb.length() - 1);
        }

        LogUtils.e("HttpUtils", "doPostRequest: body:" + sb.toString());
        byte[] entity = sb.toString().getBytes();
        try {
            url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setChunkedStreamingMode(0);
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(15 * 1000);
            connection.setReadTimeout(15 * 1000);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            connection.setRequestProperty("Content-Length", entity.length + "");
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(entity);
            outputStream.flush();
            outputStream.close();
            if (connection.getResponseCode() == 200) {
                is = connection.getInputStream();
                byte[] buffer = new byte[1024];
                int    length = 0;
                while ((length = is.read(buffer)) != -1) {
                    baos.write(buffer, 0, length);
                    baos.flush();
                }
                result = baos.toByteArray();
                LogUtils.e("HttpUtils", "doPostRequest: 请求成功:" + connection.getResponseCode());
            } else {
                LogUtils.e("HttpUtils", "doPostRequest: 请求失败:" + connection.getResponseCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (connection != null) {
                    connection.disconnect();
                }
                if (baos != null) {
                    baos.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (result != null) {
            String json = new String(result).toString();
            LogUtils.e("HttpUtils", "doPostRequest: " + json);
        } else {
            LogUtils.e("HttpUtils", "doPostRequest: 返回为空,可能是服务器内部请求超时!!!");
        }
        return result;
    }

    /**
     * 网络请求-Put
     *
     * @param urlPath
     * @param data
     * @param charSet
     * @param header
     * @return
     */
    public static String doPutRequest(String urlPath, String data, String charSet, String[] header) {
        String            result            = null;
        URL               url               = null;
        HttpURLConnection httpurlconnection = null;
        try {
            url = new URL(urlPath);
            httpurlconnection = (HttpURLConnection) url.openConnection();
            httpurlconnection.setDoInput(true);
            httpurlconnection.setDoOutput(true);
            httpurlconnection.setConnectTimeout(2000000);// 设置连接主机超时（单位：毫秒）
            httpurlconnection.setReadTimeout(2000000);// 设置从主机读取数据超时（单位：毫秒）

            if (header != null) {
                for (int i = 0; i < header.length; i++) {
                    String[] content = header[i].split(":");
                    httpurlconnection.setRequestProperty(content[0], content[1]);
                }
            }

            httpurlconnection.setRequestMethod("PUT");
            httpurlconnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            if (!TextUtils.isEmpty(data)) {
                httpurlconnection.getOutputStream().write(data.getBytes("UTF-8"));
            }
            httpurlconnection.getOutputStream().flush();
            httpurlconnection.getOutputStream().close();
            int code = httpurlconnection.getResponseCode();

            if (code == 200) {
                DataInputStream in  = new DataInputStream(httpurlconnection.getInputStream());
                int             len = in.available();
                byte[]          by  = new byte[len];
                in.readFully(by);
                if (TextUtils.isEmpty(charSet)) {
                    result = new String(by, Charset.forName(charSet));
                } else {
                    result = new String(by);
                }
                in.close();
            } else {
                LogUtils.e("", "请求地址：" + urlPath + "返回状态异常，异常号为：" + code);
            }
        } catch (Exception e) {
            LogUtils.e("", "访问url地址：" + urlPath + "发生异常" + e);
        }
        finally {
            url = null;
            if (httpurlconnection != null) {
                httpurlconnection.disconnect();
            }
        }
        return result;
    }
}
