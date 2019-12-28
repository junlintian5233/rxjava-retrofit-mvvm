package com.junzhitian.rxjavaretrofitmvvm.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.databinding.DataBindingUtil;

import com.junzhitian.rxjavaretrofitmvvm.R;
import com.junzhitian.rxjavaretrofitmvvm.databinding.SettingViewDB;


/**
 * @作者:TJ
 * @时间:2019/7/518:02
 * @描述:
 */
public class SettingView extends FrameLayout {

    private SettingViewDB mBinding;

    public SettingView(Context context) {
        super(context);
        init(context, null);
    }

    public SettingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SettingView(Context context, AttributeSet attrs, int defStyle) {
        this(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.widget_setting_view, this);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        mBinding = DataBindingUtil.inflate(inflater, R.layout.widget_setting_view, null, false);
        addView(mBinding.getRoot());
        parseStyle(context, attrs);
    }


    private void parseStyle(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SettingView);
            String left = ta.getString(R.styleable.SettingView_sv_left_text);
            mBinding.tvLeft.setText(left);

            String right = ta.getString(R.styleable.SettingView_sv_right_text);
            mBinding.tvRight.setText(right);


            boolean visible = ta.getBoolean(R.styleable.SettingView_sv_more_visible, true);
            if (!visible) {
                mBinding.tvRight.setCompoundDrawables(null, null, null, null);
            }
            ta.recycle();
        }
    }

    public void setOnClickLitener(OnClickListener onClickLitener) {
        setOnClickListener(onClickLitener);
    }

    public void setContent(String content) {
        mBinding.tvRight.setText(content);
    }
}
