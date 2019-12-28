package com.junzhitian.basicapp.ui;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.junzhitian.basicapp.R;
import com.junzhitian.basicapp.qmui.QMUIStatusBarHelper;
import com.junzhitian.basicapp.qmui.qmuiwidget.titlebar.TitleBar;
import com.junzhitian.basicapp.utils.WebUtils;


public class WebViewActivity extends AppCompatActivity {

    private WebView      mWebView;
    private LinearLayout mLlWebView;

    public static Intent getLaunchIntent(Context context, String url, String title) {
        return new Intent(context, WebViewActivity.class).putExtra("url", url).putExtra("title", title);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.act_webview);
        QMUIStatusBarHelper.translucent(this);
        QMUIStatusBarHelper.setStatusBarLightMode(this);
        initView();
    }

    public void initView() {
        mLlWebView = findViewById(R.id.ll_web);
        mWebView = findViewById(R.id.webview);
        TitleBar titleBar = findViewById(R.id.title_bar);
        titleBar.setTitle(getIntent().getStringExtra("title"));
        WebUtils.loadUrl(mWebView, getIntent().getExtras().getString("url"));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent keyEvent) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebView.canGoBack()) {
                mWebView.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, keyEvent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLlWebView.removeView(mWebView);
        mWebView.removeAllViews();
        mWebView.destroy();
    }
}
