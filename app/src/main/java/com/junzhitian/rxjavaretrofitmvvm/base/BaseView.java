package com.junzhitian.rxjavaretrofitmvvm.base;

/**
 * @作者:TJ
 * @时间:2019-04-15 11:02
 * @描述:
 */
public interface BaseView {
    /**
     * 显示提示消息
     */
    void showToastMessage(String message);

    /**
     * 显示提示消息
     */
    void showToastMessage(int message);

    /**
     * 显示dilaog
     */
    void showLoadDialog(boolean outTouchCancel);

    /**
     * 显示dilaog
     */
    void showLoadDialog();

    /**
     * 隐藏dialog
     */
    void hideLoadDialog();

    /**
     * 加载完成
     */
    void onLoadSuccess();

    /**
     * 加载失败
     *
     * @param
     */
    void onLoadFailure();


}
