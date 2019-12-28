package com.juntian.basicapp.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.juntian.basicapp.R;

/**
 * @作者:TJ
 * @时间:2019/10/21
 * @描述:
 */
public class PagerController extends LinearLayout {

    private int   numberOfPagers;
    private float padding; //设置间距
    public  int   normalResource;
    public  int   selectedResource;

    //当使用java代码创建控件时，用这个构造方法
    public PagerController(Context context, float padding) {
        super(context, null);
        normalResource = R.drawable.shape_dot_grey;
        selectedResource = R.drawable.shape_dot_red;
        this.padding = padding;
        setOrientation(LinearLayout.HORIZONTAL);
    }


    public PagerController(Context context, int normalRes, int selectRes, float padding) {
        super(context, null);
        //保存外部传来的数据
        normalResource = normalRes;
        selectedResource = selectRes;
        this.padding = padding;
        setOrientation(LinearLayout.HORIZONTAL);
    }

    public PagerController(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PagerController(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(LinearLayout.HORIZONTAL);
    }

    public float getPadding() {
        return padding;
    }

    public void setPadding(float padding) {
        this.padding = padding;
    }

    public int getNumberOfPagers() {
        return numberOfPagers;
    }

    public void setNumberOfPagers(int numberOfPagers) {
        this.numberOfPagers = numberOfPagers;
        for (int i = 0; i < numberOfPagers; i++) {
            //getContext 是指这个界面
            //创建控件
            ImageView dotView = new ImageView(getContext());
            //创建布局参数
            LayoutParams params = new LayoutParams
                    (ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
            //设置资源
            if (i == 0) {
                dotView.setImageResource(selectedResource);
            } else {
                dotView.setImageResource(normalResource);
                params.leftMargin = (int) padding;
            }

            //垂直居中
            params.gravity = Gravity.CENTER_VERTICAL;
            //添加控件
            addView(dotView, params);
        }
    }
}

