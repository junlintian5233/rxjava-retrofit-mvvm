package com.junzhitian.basicapp.adaptor;


import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.PagerAdapter;


import java.util.List;

/**
 * 适配器
 */
public class VIBannerAdapter extends PagerAdapter {

    List<View> data;

    public VIBannerAdapter(List<View> data) {
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
        View view = data.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(data.get(position));
    }
}
