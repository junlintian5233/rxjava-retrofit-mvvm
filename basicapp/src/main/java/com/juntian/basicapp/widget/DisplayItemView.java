package com.juntian.basicapp.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.juntian.basicapp.R;
import com.juntian.basicapp.databinding.WidgetDisplayViewBinding;
import com.juntian.basicapp.utils.DensityUtils;


/**
 * @作者:TJ
 * @时间:2019/7/5 18:02
 * @描述:展示信息item
 */
public class DisplayItemView extends FrameLayout {

    private WidgetDisplayViewBinding mBinding;

    public DisplayItemView(Context context) {
        super(context);
        init(context, null);
    }

    public DisplayItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public DisplayItemView(Context context, AttributeSet attrs, int defStyle) {
        this(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.widget_display_view, null, false);
        addView(mBinding.getRoot());
        parseStyle(context, attrs);
    }


    private void parseStyle(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray ta    = context.obtainStyledAttributes(attrs, R.styleable.DisplayItemView);
            String     title = ta.getString(R.styleable.DisplayItemView_div_title);
            mBinding.title.setText(title);

            String content = ta.getString(R.styleable.DisplayItemView_div_content);
            mBinding.content.setText(content);

            int titleColor = ta.getColor(R.styleable.DisplayItemView_div_title_color, ContextCompat.getColor(context, R.color.clr_333));
            mBinding.title.setTextColor(titleColor);

            int contentColor = ta.getColor(R.styleable.DisplayItemView_div_content_color, ContextCompat.getColor(context, R.color.clr_666));
            mBinding.content.setTextColor(contentColor);

            String hint = ta.getString(R.styleable.DisplayItemView_div_content_hint);
            mBinding.content.setHint(hint);

            String gravity = ta.getString(R.styleable.DisplayItemView_div_content_gravity);
            if (gravity == null) {
                gravity = "";
            }
            switch (gravity) {
                case "left":
                    mBinding.content.setGravity(Gravity.LEFT);
                    break;
                default:
                    mBinding.content.setGravity(Gravity.RIGHT);
                    break;
            }
            boolean visible = ta.getBoolean(R.styleable.DisplayItemView_div_more_visible, true);
            mBinding.more.setVisibility(visible ? VISIBLE : GONE);

            int horizontalPadding = ta.getDimensionPixelSize(R.styleable.DisplayItemView_div_vertical_padding, DensityUtils.dip2px(context, 16));
            mBinding.title.setPadding(0, horizontalPadding, 0, horizontalPadding);
            mBinding.content.setPadding(0, horizontalPadding, 0, horizontalPadding);


            float                     weight = ta.getFloat(R.styleable.DisplayItemView_div_content_weight, 2.5f);
            LinearLayout.LayoutParams lp     = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, weight);
            mBinding.content.setLayoutParams(lp);

            ta.recycle();
        }
    }

    public void setOnClickLitener(OnClickListener onClickLitener) {
        setOnClickListener(onClickLitener);
    }

    public void setContent(String content) {
        mBinding.content.setText(content);
    }
}
