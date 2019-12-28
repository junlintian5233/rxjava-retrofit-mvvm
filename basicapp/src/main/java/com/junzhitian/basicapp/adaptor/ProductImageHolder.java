package com.junzhitian.basicapp.adaptor;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.junzhitian.basicapp.R;
import com.junzhitian.basicapp.model.BaseBanner;
import com.junzhitian.basicapp.utils.GlideUtils;

/**
 * banner图片显示适配器
 */
public class ProductImageHolder implements Holder<BaseBanner> {

    private ImageView imageView;

    @Override
    public View createView(Context context) {
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, BaseBanner banner) {
        GlideUtils.getInstance().loadImage(context, banner.getSave_path(), imageView, R.drawable.ic_default);
    }
}