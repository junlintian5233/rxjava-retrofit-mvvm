package com.junzhitian.rxjavaretrofitmvvm.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.junzhitian.basicapp.utils.KeyboardUtils;
import com.junzhitian.rxjavaretrofitmvvm.R;
import com.junzhitian.basicapp.utils.LogUtils;
import com.junzhitian.basicapp.utils.SpUtils;

import org.greenrobot.eventbus.EventBus;

/**
 * @作者:TJ
 * @时间:2019-04-11 16:44
 * @描述:
 */
public abstract class BaseFragment<VM extends BaseViewModel, DB extends ViewDataBinding> extends Fragment implements BaseView {

    public String       TAG = this.getClass().getSimpleName();
    public BaseActivity mActivity;
    public SpUtils      mSpUtils;
    public VM           mViewModel;
    public DB           mDataBinding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mDataBinding = DataBindingUtil.inflate(inflater, onSetLayoutRes(), container, false);
        mActivity = (BaseActivity) getActivity();
        mSpUtils = SpUtils.getInstance();
        mViewModel = createVM();
        return mDataBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(savedInstanceState);
    }

    protected abstract VM createVM();


    public abstract int onSetLayoutRes();


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
        Intent intent = new Intent(mActivity, clazz);
        startActivity(intent);
    }

    public void skipAct(Class clazz, Bundle bundle) {
        Intent intent = new Intent(mActivity, clazz);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    /**
     * 改变fmt从右边进入
     *
     * @param resView
     * @param fragment
     */
    public void changeFragment(int resView, Fragment fragment, Fragment hiddenFragment) {
        getActivity().getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.push_right_in, R.anim.push_left_out, R.anim.back_left_in, R.anim.back_right_out)
                .add(resView, fragment)
                .hide(hiddenFragment)
                .addToBackStack(fragment.getClass().getName())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }

    public void changeFragment(int resView, Fragment fragment) {
        KeyboardUtils.closeKeyboard(mActivity);
        getActivity().getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.push_right_in, R.anim.push_left_out, R.anim.back_left_in, R.anim.back_right_out)
                .add(resView, fragment)
                .hide(this)
                .addToBackStack(fragment.getClass().getName())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }

    /**
     * 替换fmt从右边进入
     *
     * @param resView
     * @param fragment
     */
    public void replaceFragment(int resView, Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.push_right_in, R.anim.push_left_out, R.anim.back_left_in, R.anim.back_right_out)
                .replace(resView, fragment)
                .addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }


    /**
     * 显示等待dialog
     */
    @Override
    public void showLoadDialog(boolean outTouchCancel) {
        mActivity.showLoadDialog(outTouchCancel);
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
        mActivity.hideLoadDialog();
    }


    /**
     * 显示一个Toast信息
     */
    @Override
    public void showToastMessage(String message) {
        mActivity.showToastMessage(message);
    }

    @Override
    public void showToastMessage(int res) {
        mActivity.showToastMessage(res);
    }

    @Override
    public void onLoadSuccess() {

    }

    @Override
    public void onLoadFailure() {

    }


    @Override
    public void onResume() {
        super.onResume();
        //LogUtils.e(TAG, "onResume: " );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtils.e(TAG, "onDestroyView: ");
        if (mViewModel != null) {
            mViewModel.onDestroy();
            mViewModel = null;
        }
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        hideLoadDialog();
    }

}
