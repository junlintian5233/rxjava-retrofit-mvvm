package com.juntian.basicapp.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.juntian.basicapp.R;


/**
 * @author:TJ
 * @date:2017/11/28 10:54
 * @description:
 */

public class TipDialog extends Dialog implements View.OnClickListener {

    private DialogCallback mCallback;
    private TextView       mTvTitle;
    private TextView       mTvContent;
    private String         mExtra;
    private TextView       mTvCancel;
    private TextView       mTvOk;
    private LinearLayout   mLinearLayout;


    public TipDialog(@NonNull Context context, DialogCallback callback) {
        super(context, R.style.DialogTransparent);
        this.mCallback = callback;
    }

    public TipDialog(@NonNull Context context, DialogCallback callback, String extra) {
        super(context, R.style.DialogTransparent);
        this.mCallback = callback;
        mExtra = extra;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //加载dialog的布局
        setContentView(R.layout.dialog_tip);
        initView();
        //拿到布局控件进行处理
        initListener();
        //初始化布局的位置
        initLayoutParams();
    }

    private void initView() {
        mTvTitle = this.findViewById(R.id.tv_dialog_title);
        mTvContent = findViewById(R.id.tv_dialog_content);
        mTvCancel = findViewById(R.id.tv_dialog_cancel);
        mTvOk = findViewById(R.id.tv_dialog_ok);
        mLinearLayout = findViewById(R.id.ll_btn);
    }


    private void initListener() {
        mTvCancel.setOnClickListener(this);
        mTvOk.setOnClickListener(this);
    }

    /**
     * 初始化布局的参数
     */
    private void initLayoutParams() {
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER;
        params.alpha = 1f;
        getWindow().setAttributes(params);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tv_dialog_cancel) {
            dismiss();
        } else if (id == R.id.tv_dialog_ok) {
            mCallback.onPositiveClick(this, mExtra);
            dismiss();
        }
    }

    public TipDialog setContent(String content) {
        if (!TextUtils.isEmpty(content)) {
            mTvContent.setVisibility(View.VISIBLE);
            mTvContent.setText(content);
        }
        return this;
    }


    public TipDialog setTitle(String title) {
        mTvTitle.setText(title);
        return this;
    }


    public TipDialog setTitleGravity(int gravity) {
        mTvTitle.setGravity(gravity);
        return this;
    }

    public TipDialog setContentVisible(int visibility) {
        mTvContent.setVisibility(visibility);
        return this;
    }


    public TipDialog setButtonVisible(int visible) {
        mLinearLayout.setVisibility(visible);
        return this;
    }

    public TipDialog setPositiveText(String text) {
        mTvOk.setText(text);
        return this;
    }

    public TipDialog setNegativeClickAsFinish(Activity activity, boolean finish) {
        if (finish) {
            activity.finish();
        }
        return this;
    }


    public interface DialogCallback {
        void onPositiveClick(TipDialog tipDialog, String extra);
    }
}
