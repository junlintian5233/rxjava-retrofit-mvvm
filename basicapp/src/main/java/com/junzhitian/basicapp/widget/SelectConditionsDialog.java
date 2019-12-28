package com.junzhitian.basicapp.widget;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.adapter.ArrayWheelAdapter;
import com.junzhitian.basicapp.R;
import com.contrarywind.view.WheelView;

import java.util.List;

/**
 * Created by ZhouBin on 2017/9/12.
 * Effect:  条件选择器
 */

public class SelectConditionsDialog extends Dialog {

    private TextView iv_cancel, tv_title, tv_finish;
    private LinearLayout optionspicker;
    private WheelView    options1, options2, options3;
    public  OnWheelViewClickListener onWheelViewClickListener;
    private Context                  mContext;

    public SelectConditionsDialog(Context context, List<String> mDatas) {
        super(context);
        mContext = context;
        Window window = this.getWindow();
        window.requestFeature(Window.FEATURE_NO_TITLE);
        window.getDecorView().setPadding(0, 0, 0, 0);
        View popupView = View.inflate(context, R.layout.layout_bottom_wheel_option, null);
        window.setContentView(popupView);
        initViews(popupView, mDatas);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.windowAnimations = R.style.bottomInWindowAnim;
        lp.gravity = Gravity.BOTTOM;
        window.setAttributes(lp);
        window.setBackgroundDrawableResource(android.R.color.transparent);
        SelectConditionsDialog.this.show();
    }

    private void initViews(final View popupView, List<String> mDatas) {
        iv_cancel = (TextView) popupView.findViewById(R.id.iv_cancel);
        tv_title = (TextView) popupView.findViewById(R.id.tv_title);
        tv_finish = (TextView) popupView.findViewById(R.id.tv_finish);
        optionspicker = (LinearLayout) findViewById(R.id.optionspicker);
        options1 = (WheelView) findViewById(R.id.options1);
        options2 = (WheelView) findViewById(R.id.options2);
        options3 = (WheelView) findViewById(R.id.options3);
        options1.setAdapter(new ArrayWheelAdapter(mDatas));
        options1.setCurrentItem(0);
        options1.setCyclic(false);
        options1.setTextSize(18);
        iv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectConditionsDialog.this.dismiss();
            }
        });
        tv_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onWheelViewClickListener != null) {
                    onWheelViewClickListener.onClick(view, options1.getCurrentItem());
                }
                SelectConditionsDialog.this.dismiss();
            }
        });
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {
                int top = popupView.findViewById(R.id.optionspicker).getTop();
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    int y = (int) motionEvent.getY();
                    if (y < top) {
                        SelectConditionsDialog.this.dismiss();
                    }
                }
                return true;
            }
        });

    }


    /**
     * 底部滚轮点击事件回调
     */
    public interface OnWheelViewClickListener {
        void onClick(View view, int position);
    }

    public void setOnWheelViewClickListener(OnWheelViewClickListener onWheelViewClickListener) {
        this.onWheelViewClickListener = onWheelViewClickListener;
    }

    /**
     * 设置标题
     *
     * @param text
     */
    public void setTitle(String text) {
        if (!TextUtils.isEmpty(text)) {
            tv_title.setText(text);
        }
    }


}
