package com.junzhitian.basicapp.utils;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;

import com.junzhitian.basicapp.R;


/**
 * Webview幫助類
 * Created by WQ on 2017/8/31.
 */

public class WebViewTool {





    /**
     * WebView 默認配置
     * @param webView
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @SuppressLint({"SetJavaScriptEnabled", "JavascriptInterface"})
    public static WebSettings webviewDefaultConfig(WebView webView) {
        webView.setBackgroundColor(0);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setDomStorageEnabled(true);
        webSettings.setGeolocationEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setBlockNetworkImage(true);
        webView.setWebViewClient(new WebViewClientBase());
        webView.requestFocus(View.FOCUS_DOWN);
        webSettings.setDefaultTextEncodingName("UTF-8");
        try {
            WebView.setWebContentsDebuggingEnabled(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                String message = consoleMessage.message();
                int lineNumber = consoleMessage.lineNumber();
                String sourceID = consoleMessage.sourceId();
                String messageLevel = consoleMessage.message();
                ConsoleMessage.MessageLevel messageLevel1 = consoleMessage.messageLevel();
                Log.e("app3cs", String.format("[%s] sourceID: %s lineNumber: %n message: %s",
                        messageLevel, sourceID, lineNumber, message));
                return super.onConsoleMessage(consoleMessage);
            }

            @Override
            public boolean onJsAlert(WebView webView, String url, String message, final JsResult result) {

                AlertDialog.Builder dialog = new AlertDialog
                        .Builder(webView.getContext())
                        .setTitle(R.string.app_name)
                        .setMessage(message)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                result.confirm();
                            }
                        });

                dialog.setCancelable(false);
                dialog.create();
                dialog.show();
                return true;
            }
        });
        return webSettings;
    }

    public static class WebViewClientBase extends WebViewClient {

        /**
         * 加载开始的监听
         *
         * @param view
         * @param url
         * @param favicon
         */
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }


        /**
         * 加载完成的监听
         *
         * @param view
         * @param url
         */
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            view.getSettings().setBlockNetworkImage(false);
//            UIUtils.cancelLoadDialog();
//            LinearLayout.LayoutParams  params = (LinearLayout.LayoutParams) view.getLayoutParams();
//            params.width = view.getWidth();
//            params.height =   view.getContentHeight();
//            view.setLayoutParams(params);
            view.loadUrl("javascript:setFontSize()");
        }

        /**
         * 加载失败的监听
         *
         * @param view
         * @param errorCode
         * @param description
         * @param failingUrl
         */
        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
//            String errorHtml = "<html><body><h2>网页加载失败</h2></body></html>";
//            view.loadDataWithBaseURL(null, errorHtml, "text/html", "UTF-8", null);
//            UIUtils.cancelLoadDialog();
        }
    }

    /**
     * 重置webview中的图片
     *
     * @param webview
     */
    public static void imgReset(WebView webview) {
        webview.loadUrl(WEBVIEW_IMG_DEAL);
    }

    public static final String WEBVIEW_IMG_DEAL = "javascript:(function(){" +
            "var objs = document.getElementsByTagName('img'); " +
            "for(var i=0;i<objs.length;i++)  " +
            "{"
            + "var img = objs[i];   " +
            "    img.style.maxWidth = '100%';   img.style.height = 'auto'; " +
            "}" +
            "})()";
}
