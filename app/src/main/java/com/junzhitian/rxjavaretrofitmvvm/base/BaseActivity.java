package com.junzhitian.rxjavaretrofitmvvm.base;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.junzhitian.rxjavaretrofitmvvm.R;
import com.junzhitian.rxjavaretrofitmvvm.common.ActivityPageManager;
import com.junzhitian.rxjavaretrofitmvvm.utils.ToastUtils;
import com.junzhitian.basicapp.qmui.QMUIStatusBarHelper;
import com.junzhitian.basicapp.qmui.qmuiwidget.dialog.QMUITipDialog;
import com.junzhitian.basicapp.qmui.qmuiwidget.titlebar.TitleBar;
import com.junzhitian.basicapp.utils.SpUtils;

import org.greenrobot.eventbus.EventBus;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;


/**
 * @作者:TJ
 * @时间:2019-04-11 16:44
 * @描述:
 */
public abstract class BaseActivity<VM extends BaseViewModel, DB extends ViewDataBinding> extends AppCompatActivity implements
        BaseView, View.OnClickListener {

    public  String        TAG = this.getClass().getSimpleName();
    public  BaseActivity  mContext;
    public  SpUtils       mSpUtils;
    public  VM            mViewModel;
    public  DB            mDataBinding;
    private QMUITipDialog mDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mDataBinding = DataBindingUtil.setContentView(this, onSetLayoutRes());
        QMUIStatusBarHelper.translucent(this);
        QMUIStatusBarHelper.setStatusBarLightMode(this);
        ActivityPageManager.getInstance().addActivity(this);
        mContext = this;
        mSpUtils = SpUtils.getInstance();
        mViewModel = createVM();
        init(savedInstanceState);
    }


    protected abstract VM createVM();

    protected abstract int onSetLayoutRes();


    /**
     * 初始化页面
     *
     * @param savedInstanceState
     */
    protected void init(Bundle savedInstanceState) {
        initData();
        initView(savedInstanceState);
        bindEvent();
    }


    @Override
    public void onClick(View view) {
    }

    /**
     * 初始化数据
     */
    public abstract void initData();

    /**
     * 初始化view
     *
     * @param savedInstanceState
     */
    public abstract void initView(Bundle savedInstanceState);

    /**
     * 绑定事件
     */
    public void bindEvent() {

    }


    /**
     * 跳转页面
     *
     * @param clazz
     */
    public void skipAct(Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    public void skipAct(Class clazz, String key, String value) {
        Intent intent = new Intent(this, clazz);
        intent.putExtra(key, value);
        startActivity(intent);
    }

    public void skipAct(Class clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    /**
     * 显示等待dialog
     */
    @Override
    public void showLoadDialog(boolean outTouchCancel) {
        mDialog = new QMUITipDialog.Builder(mContext)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord(getString(R.string.loading))
                .create();
        mDialog.show();
        mDialog.setCanceledOnTouchOutside(outTouchCancel);
    }

    @Override
    public void showLoadDialog() {
        showLoadDialog(true);
    }

    /**
     * 关闭dialog
     */
    @Override
    public void hideLoadDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
            mDialog = null;
        }
    }


    /**
     * 显示一个Toast信息
     */
    @Override
    public void showToastMessage(String message) {
        if (message != null) {
            ToastUtils.showShort(message);
        }
    }

    @Override
    public void showToastMessage(int res) {
        if (res != 0) {
            ToastUtils.showShort(getString(res));
        }
    }

    @Override
    public void onLoadSuccess() {

    }

    @Override
    public void onLoadFailure() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        if (mViewModel != null) {
            mViewModel.onDestroy();
            mViewModel = null;
        }
    }


    /**
     * 将 Fragment添加到Acitvtiy
     *
     * @param fragment
     * @param frameId
     */
    public void addFragmentToActivity(@NonNull Fragment fragment, int frameId) {
        checkNotNull(fragment);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(frameId, fragment)
                .setCustomAnimations(R.anim.push_right_in, R.anim.push_left_out, R.anim.back_left_in, R.anim.back_right_out)
                .addToBackStack(fragment.getClass().getName())
                .commit();
    }

    /**
     * 获取editText 内容
     *
     * @param editText
     * @return
     */
    public String getText(EditText editText) {
        return editText.getText().toString().trim();
    }

    /**
     * 设置回退栈返回
     */
    public void setBackStack() {
        FragmentManager manager = getSupportFragmentManager();
        if (manager.getBackStackEntryCount() == 0) {
            //super.onBackPressed();
            finish();
        } else {
            int backStackEntryCount = manager.getBackStackEntryCount();
            if (backStackEntryCount == 1) {
                finish();
                return;
            }
            manager.popBackStack();
        }
    }

}
