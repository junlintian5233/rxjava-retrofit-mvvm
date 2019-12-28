package com.junzhitian.basicapp.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.junzhitian.basicapp.R;
import com.junzhitian.basicapp.widget.gridpasswordview.GridPasswordView;


/**
 * @version V1.0
 * @功能描述: Dialog对话框总工具类
 */
public final class AlertDialogUtil {

    /**
     * 密码对话框
     *
     * @param context
     * @return
     */
    public static Dialog showPwdDialog(final Context context, String money, GridPasswordView.OnPasswordChangedListener listener) {
        final Dialog dialog     = new Dialog(context, R.style.NoTitleDialog);
        View         dialogView = View.inflate(context, R.layout.dialog_input_password, null);
        dialog.setContentView(dialogView);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        ((TextView) dialogView.findViewById(R.id.money)).setText("¥" + money);
        GridPasswordView pwd_layout = dialogView.findViewById(R.id.pwd_layout);
        pwd_layout.setOnPasswordChangedListener(listener);
        dialogView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
        return dialog;
    }
}