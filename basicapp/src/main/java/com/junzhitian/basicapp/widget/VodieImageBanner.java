package com.junzhitian.basicapp.widget;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.junzhitian.basicapp.R;
import com.junzhitian.basicapp.adaptor.ProductImageHolder;
import com.junzhitian.basicapp.adaptor.VIBannerAdapter;
import com.junzhitian.basicapp.databinding.LayoutVibannerBannerBinding;
import com.junzhitian.basicapp.databinding.LayoutVibannerVodieBinding;
import com.junzhitian.basicapp.databinding.WidgetVideoImageBannerBinding;
import com.junzhitian.basicapp.model.BaseBanner;
import com.junzhitian.basicapp.utils.Tools;

import java.util.ArrayList;
import java.util.List;

import cn.jzvd.Jzvd;


/**
 * @作者:TJ
 * @时间:2019/10/10
 * @描述:
 */
public class VodieImageBanner extends FrameLayout implements RadioGroup.OnCheckedChangeListener, OnItemClickListener, ViewPager.OnPageChangeListener {

    public String TAG = this.getClass().getSimpleName();

    private WidgetVideoImageBannerBinding mVibannerBinding;

    private List<View>                  mViewList = new ArrayList<>();
    private LayoutInflater              mInflater;
    private LayoutVibannerVodieBinding  mVodieBinding;
    private LayoutVibannerBannerBinding mBannerBinding;
    private OnViBannerListener          mOnViBannerListener;
    private VIBannerAdapter             mViBannerAdapter;

    public VodieImageBanner(@NonNull Context context) {
        super(context);
    }

    public VodieImageBanner(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }


    public VodieImageBanner(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, null);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public VodieImageBanner(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    private void init(Context context, AttributeSet attrs) {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mVibannerBinding = DataBindingUtil.inflate(mInflater, R.layout.widget_video_image_banner, null, false);
        addView(mVibannerBinding.getRoot());

        mVodieBinding = DataBindingUtil.inflate(mInflater, R.layout.layout_vibanner_vodie, mVibannerBinding.vibViewPager, false);
        mBannerBinding = DataBindingUtil.inflate(mInflater, R.layout.layout_vibanner_banner, mVibannerBinding.vibViewPager, false);
        mViewList.add(mVodieBinding.getRoot());
        mViewList.add(mBannerBinding.getRoot());

        mViBannerAdapter = new VIBannerAdapter(mViewList);
        mVibannerBinding.vibViewPager.setAdapter(mViBannerAdapter);
        mVibannerBinding.vibViewPager.addOnPageChangeListener(this);

        mVibannerBinding.vibRadioGroup.check(R.id.vib_rbtn_vodie);
        mVibannerBinding.vibRadioGroup.setOnCheckedChangeListener(this);

        parseStyle(context, attrs);
    }

    private void parseStyle(Context context, AttributeSet attrs) {

    }

    public VodieImageBanner setBannerUrls(List<BaseBanner> list) {
        if (list == null || list.isEmpty()) {
            mBannerBinding.banner.setBackgroundResource(R.drawable.ic_default);
            return this;
        }
        CBViewHolderCreator<ProductImageHolder> holderCreator = new CBViewHolderCreator<ProductImageHolder>() {
            @Override
            public ProductImageHolder createHolder() {
                return new ProductImageHolder();
            }
        };
        mBannerBinding.banner.setPages(holderCreator, list);
        mBannerBinding.banner.setPageIndicator(new int[]{R.mipmap.dot_light, R.mipmap.dot_white})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this);
        mBannerBinding.banner.setCanLoop(false);
        return this;
    }


    public VodieImageBanner setVideoUrl(String url, String title) {
        if (Tools.isEmpty(url)) {
            mVibannerBinding.vibRbtnVodie.setVisibility(GONE);
            mVibannerBinding.vibRadioGroup.check(R.id.vib_rbtn_image);
            mViewList.remove(0);
            mViBannerAdapter.notifyDataSetChanged();
            mBannerBinding.banner.setCanLoop(true);
        } else {
            mVodieBinding.videoplayer.setUp(url, title);
        }
        return this;
    }

    public VodieImageBanner setThumbImage(Context context, String url) {
        if (!Tools.isEmpty(url)) {
            Glide.with(context).load(url).centerCrop().into(mVodieBinding.videoplayer.thumbImageView);
        } else {
            mVodieBinding.videoplayer.thumbImageView.setImageResource(R.drawable.ic_default);
        }
        return this;
    }

    public void setOnViBannerListener(OnViBannerListener onViBannerListener) {
        mOnViBannerListener = onViBannerListener;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.vib_rbtn_vodie) {
            mVibannerBinding.vibViewPager.setCurrentItem(0);
        } else if (checkedId == R.id.vib_rbtn_image) {
            mVibannerBinding.vibViewPager.setCurrentItem(1);
        }
    }

    @Override
    public void onItemClick(int position) {
        if (mOnViBannerListener != null) {
            mOnViBannerListener.onBannerItemClick(position);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        ((RadioButton) mVibannerBinding.vibRadioGroup.getChildAt(position)).setChecked(true);
        if (position == 1) {
            Jzvd.releaseAllVideos();
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public interface OnViBannerListener {

        void onBannerItemClick(int position);

    }
}
