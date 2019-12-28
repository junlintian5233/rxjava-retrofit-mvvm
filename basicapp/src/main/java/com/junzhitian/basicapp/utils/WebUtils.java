package com.junzhitian.basicapp.utils;

import android.util.DisplayMetrics;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * @作者:TJ
 * @时间:2019/8/16
 * @描述:
 */
public class WebUtils {

    public static void loadUrl(WebView webView, String url) {
        initWebView(webView);
        //WebView加载web资源
        webView.loadUrl(url);
        LogUtils.e("WebUtils", "initView: URL:" + url);
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开*/
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    /**
     * 初始化webview
     *
     * @param webView
     */
    private static void initWebView(WebView webView) {
        webView.setVerticalScrollBarEnabled(false);
        //声明WebSettings子类
        WebSettings webSettings = webView.getSettings();
        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
        //支持插件
        //webSettings.setPluginsEnabled(true);

        //设置自适应屏幕，两者合用
        DisplayMetrics dm    = webView.getContext().getResources().getDisplayMetrics();
        int            scale = dm.densityDpi;
        if (scale == 240) { //设置自动适配
            webView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
        } else if (scale == 160) {
            webView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
        } else {
            webView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.CLOSE);
        }
        //将图片调整到适合webview的大小
        //webSettings.setUseWideViewPort(true);
        //缩放至屏幕的大小
        //webSettings.setLoadWithOverviewMode(true);

        //缩放操作
        //支持缩放，默认为true。是下面那个的前提。
        webSettings.setSupportZoom(false);
        //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setBuiltInZoomControls(true);
        //隐藏原生的缩放控件
        webSettings.setDisplayZoomControls(false);

        //其他细节操作
        //关闭webview中缓存
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        //设置可以访问文件
        webSettings.setAllowFileAccess(true);
        //支持通过JS打开新窗口
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        //支持自动加载图片
        webSettings.setLoadsImagesAutomatically(true);
        //设置编码格式
        webSettings.setDefaultTextEncodingName("utf-8");

        //缓存
        if (HttpUtils.isNetworkConnected(webView.getContext())) {
            //根据cache-control决定是否从网络上取数据。
            webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        } else {
            //没网，则从本地获取，即离线加载
            webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }

        // 开启 DOM storage API 功能
        webSettings.setDomStorageEnabled(true);
        //开启 database storage API 功能
        webSettings.setDatabaseEnabled(true);
        //开启 Application Caches 功能
        webSettings.setAppCacheEnabled(true);

        String cacheDirPath = webView.getContext().getExternalCacheDir().getAbsolutePath();
        //设置  Application Caches 缓存目录
        webSettings.setAppCachePath(cacheDirPath);
    }

    /**
     * 加载html
     *
     * @param webView
     * @param content
     */
    public static void loadHtml(WebView webView, String content) {
        initWebView(webView);
        webView.loadData(HtmlFormatUtil.getNewContent(content), "text/html", "UTF-8");
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开*/
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }
}
