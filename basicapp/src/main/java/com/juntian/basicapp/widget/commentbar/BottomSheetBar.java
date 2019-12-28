package com.juntian.basicapp.widget.commentbar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.juntian.basicapp.R;
import com.juntian.basicapp.utils.KeyboardUtils;


/**
 * @作者:TJ
 * @时间:2019/11/20
 * @描述:
 */
public class BottomSheetBar {

    private Activity              mContext;
    private View                  mRootView;
    private EditText              mEtContent;
    private TextView              mBtnSend;
    private BottomDialog          mBottomDialog;
    private OnCommentSendListener mOnCommentSendListener;


    private BottomSheetBar(Activity context) {
        this.mContext = context;
    }

    public void setOnCommentSendListener(OnCommentSendListener onCommentSendListener) {
        mOnCommentSendListener = onCommentSendListener;
    }

    @SuppressLint("InflateParams")
    public static BottomSheetBar delegation(Activity context) {
        BottomSheetBar bar = new BottomSheetBar(context);
        bar.mRootView = LayoutInflater.from(context).inflate(R.layout.layout_bottom_sheet_comment_bar, null, false);
        bar.initView();
        return bar;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initView() {
        mEtContent = mRootView.findViewById(R.id.et_content);
        mBtnSend = mRootView.findViewById(R.id.btn_send);
        mBtnSend.setEnabled(false);

        mBottomDialog = new BottomDialog(mContext, R.style.DialogBottomSheetEdit, false);
        mBottomDialog.setContentView(mRootView);

        mBtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnCommentSendListener != null) {
                    mOnCommentSendListener.onCommentSend(getCommentText());
                    mEtContent.setText("");
                    closeKeyboard();
                }
            }
        });


        mBottomDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (KeyboardUtils.isKeyboardShowing(mContext))
                    KeyboardUtils.changeInputState(mContext);
            }
        });
        mBottomDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {

            }
        });

        mEtContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mBtnSend.setEnabled(s.length() > 0);
            }
        });

    }


    public void show() {
        mBottomDialog.show();
        mRootView.postDelayed(new Runnable() {
            @Override
            public void run() {
                showSoftKeyboard(mEtContent);
            }
        }, 50);
    }

    public void dismiss() {
        mBottomDialog.dismiss();
    }


    public String getCommentText() {
        String str = mEtContent.getText().toString();
        return TextUtils.isEmpty(str) ? "" : str.trim();
    }

    public TextView getBtnSend() {
        return mBtnSend;
    }


    private void closeKeyboard() {
        KeyboardUtils.keyBoard(mEtContent, "close");
    }

    private void showSoftKeyboard(View view) {
        if (view == null) {
            return;
        }
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        if (!view.isFocused()) {
            view.requestFocus();
        }

        InputMethodManager inputMethodManager = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(view, 0);
    }


    public interface OnCommentSendListener {
        void onCommentSend(String content);
    }

}

