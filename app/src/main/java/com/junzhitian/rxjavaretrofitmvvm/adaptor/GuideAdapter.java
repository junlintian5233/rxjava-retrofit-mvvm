package com.junzhitian.rxjavaretrofitmvvm.adaptor;


import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.junzhitian.rxjavaretrofitmvvm.R;

import java.util.List;

/**
 * 启动页适配器
 */
public class GuideAdapter extends PagerAdapter {
    List<View> data;

    public GuideAdapter(List<View> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View      view = data.get(position);
        ImageView iv   = (ImageView) view.findViewById(R.id.iv_guide);
        if (position == 0) {
            Glide.with(container.getContext()).load(R.mipmap.splash1).into(iv);
        }
        if (position == 1) {
            Glide.with(container.getContext()).load(R.mipmap.splash2).into(iv);
        }
        if (position == 2) {
            Glide.with(container.getContext()).load(R.mipmap.splash3).into(iv);
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(data.get(position));
    }
}
