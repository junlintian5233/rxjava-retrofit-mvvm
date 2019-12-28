package com.junzhitian.basicapp.qmui.qmuiwidget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.view.View;


/**
 * 绘制虚线
 * Created by Administrator on 2015/12/22.
 */
public class DashedLineView extends View {
    int color = Color.parseColor("#DDDDDD");

    public DashedLineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DashedLineView(Context context) {
        super(context);
    }


    public DashedLineView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public DashedLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(color);//颜色可以自己设置
        Path path = new Path();
        path.moveTo(0, 0);//起始坐标
        if(getWidth()<getHeight()) {
            path.lineTo(0, getHeight());//终点坐标
        }else
        {
            path.lineTo(getWidth(), 0);//终点坐标
        }
        PathEffect effects = new DashPathEffect(new float[]{8, 4, 8, 4}, 1);//设置虚线的间隔和点的长度
        paint.setPathEffect(effects);
        canvas.drawPath(path, paint);
    }
}