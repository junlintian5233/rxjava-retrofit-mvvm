package com.juntian.basicapp.qmui.qmuiwidget.ratbar;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.widget.RatingBar;


public class YellowRatingBar extends RatingBar {
    public YellowRatingBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getProgressDrawable().setColorFilter(Color.parseColor("#f2ae0b"), PorterDuff.Mode.SRC_ATOP);
    }

    public YellowRatingBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public YellowRatingBar(Context context) {
        this(context, null);
    }
}
