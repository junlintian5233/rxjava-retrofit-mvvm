package com.juntian.rxjavaretrofitmvvm.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.juntian.rxjavaretrofitmvvm.R;
import com.juntian.rxjavaretrofitmvvm.base.BaseActivity;
import com.juntian.rxjavaretrofitmvvm.base.BaseFragment;
import com.juntian.rxjavaretrofitmvvm.base.BaseViewModel;
import com.juntian.basicapp.common.ActivityPageManager;
import com.juntian.rxjavaretrofitmvvm.databinding.MainDb;
import com.juntian.rxjavaretrofitmvvm.fragment.Tab2Fragment;
import com.juntian.rxjavaretrofitmvvm.fragment.Tab4Fragment;
import com.juntian.rxjavaretrofitmvvm.fragment.Tab3Fragment;
import com.juntian.rxjavaretrofitmvvm.fragment.Tab1Fragment;

import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.juntian.rxjavaretrofitmvvm.model.bean.TabEntity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<BaseViewModel, MainDb> {

    private long               mFirstTime    = 0;
    private List<BaseFragment> mFragmentList = new ArrayList<>();

    private ArrayList<CustomTabEntity> mTabEntities     = new ArrayList<>();
    private String[]                   mTitles          = {"首页", "消息", "联系人", "更多"};
    private int[]                      mIconUnselectIds = {
            R.mipmap.tab_home_unselect, R.mipmap.tab_speech_unselect,
            R.mipmap.tab_contact_unselect, R.mipmap.tab_more_unselect};
    private int[]                      mIconSelectIds   = {
            R.mipmap.tab_home_select, R.mipmap.tab_speech_select,
            R.mipmap.tab_contact_select, R.mipmap.tab_more_select};


    @Override
    protected BaseViewModel createVM() {
        return null;
    }

    @Override
    protected int onSetLayoutRes() {
        return R.layout.act_main;
    }

    @Override
    public void initData() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }

        mFragmentList.add(new Tab1Fragment());
        mFragmentList.add(new Tab2Fragment());
        mFragmentList.add(new Tab3Fragment());
        mFragmentList.add(new Tab4Fragment());
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mDataBinding.viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        mDataBinding.tabLayout.setTabData(mTabEntities);
    }

    @Override
    public void bindEvent() {
        mDataBinding.tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mDataBinding.viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        mDataBinding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mDataBinding.tabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mDataBinding.viewPager.setCurrentItem(0);
    }


    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }
    }

    /**
     * 两次退出程序
     */
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
            long secondTime = System.currentTimeMillis();
            // 如果两次按键时间间隔大于2000毫秒，则不退出
            if (secondTime - mFirstTime > 2000) {
                Toast.makeText(mContext, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mFirstTime = secondTime;
                return true;
            } else {
                ActivityPageManager.getInstance().exit(mContext);
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }
}
