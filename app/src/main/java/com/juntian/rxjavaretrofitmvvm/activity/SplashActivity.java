package com.juntian.rxjavaretrofitmvvm.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;

import com.juntian.basicapp.utils.LogUtils;
import com.juntian.rxjavaretrofitmvvm.R;
import com.juntian.rxjavaretrofitmvvm.adaptor.GuideAdapter;
import com.juntian.rxjavaretrofitmvvm.base.BaseActivity;
import com.juntian.rxjavaretrofitmvvm.base.BaseViewModel;
import com.juntian.rxjavaretrofitmvvm.common.Config;
import com.juntian.rxjavaretrofitmvvm.constant.Constants;
import com.juntian.rxjavaretrofitmvvm.databinding.ActSplashBinding;

import java.util.Arrays;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * @作者:TJ
 * @时间:2019/8/3
 * @描述:
 */
public class SplashActivity extends BaseActivity<BaseViewModel, ActSplashBinding> implements EasyPermissions.PermissionCallbacks {

    private final int     REQUEST_PERMISSIONS = 0x110;
    private       boolean isFirstRun;

    @Override
    protected BaseViewModel createVM() {
        return null;
    }

    @Override
    public int onSetLayoutRes() {
        return R.layout.act_splash;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView(Bundle savedInstanceState) {
        isFirstRun = mSpUtils.getBoolean(Constants.IS_FIRST_RUN, true);
        if (isFirstRun) {
            initViewPager();
        }
        requestPermissions();
    }

    private void initViewPager() {
        View[] views = new View[]{
                getLayoutInflater().inflate(R.layout.layout_guide, mDataBinding.viewPager, false),
                getLayoutInflater().inflate(R.layout.layout_guide, mDataBinding.viewPager, false),
                getLayoutInflater().inflate(R.layout.layout_guide, mDataBinding.viewPager, false)};
        mDataBinding.viewPager.setOffscreenPageLimit(views.length);//设置引导页预加载n页，防止滑动卡顿的情况
        mDataBinding.viewPager.setAdapter(new GuideAdapter(Arrays.asList(views)));

        views[views.length - 1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSpUtils.putBoolean(Constants.IS_FIRST_RUN, false);
                isFirstRun = false;
                switchPage();
            }
        });
    }


    @AfterPermissionGranted(REQUEST_PERMISSIONS)
    private void requestPermissions() {
        String[] perms = {Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};
        if (!EasyPermissions.hasPermissions(this, perms)) {
            EasyPermissions.requestPermissions(this, getString(R.string.pls_agree_permission), REQUEST_PERMISSIONS, perms);
        } else {
            switchPage();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        LogUtils.e(TAG, "onPermissionsGranted : ");
        switchPage();
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        LogUtils.e(TAG, "onPermissionsDenied : ");
        new AlertDialog.Builder(this, R.style.AlertDialogTheme)
                .setMessage("需要手机存储权限，请到【设置】【应用】打开")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
    }

    private void switchPage() {
        if (isFirstRun) {
            return;
        }
        if (Config.getUid().isEmpty()) {
            skipAct(LoginActivity.class);
        } else {
            skipAct(MainActivity.class);
        }
        finish();
    }
}
