package com.junzhitian.basicapp.utils;

import android.content.Context;

import com.junzhitian.basicapp.qmui.qmuiwidget.dialog.QMUIDialog;
import com.junzhitian.basicapp.qmui.qmuiwidget.dialog.QMUIDialogAction;
import com.junzhitian.basicapp.widget.QMUIDialogCallback;


/**
 * @作者:TJ
 * @时间:2019/8/15
 * @描述:
 */
public class QMUIDialogUtils {

    public static void show(Context context, String title, String message, final QMUIDialogCallback callback) {
        new QMUIDialog.MessageDialogBuilder(context)
                .setTitle(title)
                .setMessage(message)
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .addAction("确定", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                        callback.onPositive(dialog);
                    }
                })
                .create().show();
    }
}
