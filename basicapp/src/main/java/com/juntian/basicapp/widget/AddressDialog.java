package com.juntian.basicapp.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.juntian.basicapp.utils.DensityUtils;

import chihane.jdaddressselector.AddressProvider;
import chihane.jdaddressselector.AddressSelector;
import chihane.jdaddressselector.DefaultAddressProvider;
import chihane.jdaddressselector.OnAddressSelectedListener;

/**
 * @作者:TJ
 * @时间:2019/8/3
 * @描述:仿京东地址选择Dialog
 */
public class AddressDialog extends Dialog {
    private AddressSelector selector;

    public AddressDialog(Context context) {
        super(context, chihane.jdaddressselector.R.style.bottom_dialog);
        init(context);
    }

    public AddressDialog(Context context, int themeResId) {
        super(context, themeResId);
        init(context);
    }

    public AddressDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init(context);
    }

    private void init(Context context) {
        selector = new AddressSelector(context);
        selector.setAddressProvider(new DefaultAddressProvider(context));
        setContentView(selector.getView());

        Window                     window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = DensityUtils.dip2px(context, 260);
        window.setAttributes(params);
        window.setGravity(Gravity.BOTTOM);
    }

    /**
     * 设置地址内容提供者
     *
     * @param addressProvider
     */
    public void setAddressProvider(AddressProvider addressProvider) {
        selector.setAddressProvider(addressProvider);
    }

    public void setOnAddressSelectedListener(OnAddressSelectedListener listener) {
        this.selector.setOnAddressSelectedListener(listener);
    }

    public static AddressDialog show(Context context) {
        return show(context, null);
    }

    public static AddressDialog show(Context context, OnAddressSelectedListener listener) {
        AddressDialog dialog = new AddressDialog(context, chihane.jdaddressselector.R.style.bottom_dialog);
        dialog.selector.setOnAddressSelectedListener(listener);
        dialog.show();
        return dialog;
    }

}
