package com.junzhitian.basicapp.helper;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.junzhitian.basicapp.R;
import com.junzhitian.basicapp.widget.SelectConditionsDialog;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;


public final class SelectorHelperUtils {

    public static final int                 SHOW_Y_M_D     = 1024; //只显示年月日(默认)
    public static final int                 SHOW_Y         = 1021; //只显示年
    public static final int                 SHOW_Y_M_D_H_M = 1025; //不显示秒
    public static final int                 SHOW_ALL       = 1026; //显示全部(年月日时分秒)
    public static final int                 SHOW_D_M       = 1027; //时分
    private static      SelectorHelperUtils instance;
    private static      Context             mContext;

    public SelectorHelperUtils() {
    }

    public static synchronized SelectorHelperUtils getInstance() {
        if (instance == null) {
            instance = new SelectorHelperUtils();
        }
        return instance;
    }


    /**
     * 条件选择器
     *
     * @param context          上下文
     * @param mDatas           数据源
     * @param title            标题
     * @param onWheelViewClick 监听
     * @return
     */
    public static SelectConditionsDialog selectMoreTypeDialog(Context context, List<String> mDatas, String title, final SelectConditionsDialog.OnWheelViewClickListener onWheelViewClick) {
        SelectConditionsDialog selectConditionsDialog = new SelectConditionsDialog(context, mDatas);
        if (!TextUtils.isEmpty(title)) {
            selectConditionsDialog.setTitle(title);
        }
        selectConditionsDialog.setOnWheelViewClickListener(new SelectConditionsDialog.OnWheelViewClickListener() {
            @Override
            public void onClick(View view, int postion) {
                if (onWheelViewClick != null) {
                    onWheelViewClick.onClick(view, postion);
                }
            }
        });
        return selectConditionsDialog;
    }

    /**
     * 时间选择器
     *
     * @param context
     * @param title
     * @param onTimeSelectListener
     * @return
     */
    public static TimePickerView selectTimePickerView(Context context, String title, final OnTimeSelectListener onTimeSelectListener) {
        return selectTimePickerView(context, title, SHOW_Y_M_D, onTimeSelectListener);
    }

    /**
     * 时间选择器
     *
     * @param context              上下文
     * @param title                标题
     * @param type                 显示类型
     * @param onTimeSelectListener 回调
     * @return
     */
    public static TimePickerView selectTimePickerView(Context context, String title, int type, final OnTimeSelectListener onTimeSelectListener) {
        return selectTimePickerView(context, title, startCalendar(), endCalendar(), type, onTimeSelectListener);
    }

    /**
     * 时间选择器
     *
     * @param context              上下文
     * @param title                标题
     * @param type                 显示类型
     * @param onTimeSelectListener 回调
     * @return
     */
    public static TimePickerView selectTimePickerView(Context context, String title, Calendar startDate, Calendar endDate, int type, final OnTimeSelectListener onTimeSelectListener) {

        TimePickerBuilder builder = new TimePickerBuilder(context, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                if (onTimeSelectListener != null) {
                    onTimeSelectListener.onTimeSelect(date, v);
                }
            }
        });
        builder.setRangDate(startDate, endDate);
        builder.setDate(endDate);
        builder.isCenterLabel(false); //是否只显示中间选中项的label文字，false则每项item全部都带有label。
        if (type == SHOW_Y_M_D) {
            builder.setType(new boolean[]{true, true, true, false, false, false}); //只显示年月日
        } else if (type == SHOW_Y_M_D_H_M) {
            builder.setType(new boolean[]{true, true, true, true, true, false}); //不显示秒
        } else if (type == SHOW_D_M) {
            builder.setType(new boolean[]{false, false, false, true, true, false}); //全部都显示
        } else if (type == SHOW_Y) {
            builder.setType(new boolean[]{true, false, false, false, false, false}); //全部都显示
        } else {
            builder.setType(new boolean[]{true, true, true, true, true, true}); //全部都显示
        }
        builder.setDividerColor(ContextCompat.getColor(mContext, R.color.divider));
        if (!TextUtils.isEmpty(title)) {
            builder.setTitleText(title);
            builder.setTitleSize(15);
        }
        builder.setSubmitText("确定").setSubCalSize(14);
        builder.setCancelText("取消");
        final TimePickerView timePickerView = builder.build();
        builder.setLayoutRes(R.layout.pickerview_custom_time, new CustomListener() {
            @Override
            public void customLayout(View v) {
                final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                TextView       ivCancel = (TextView) v.findViewById(R.id.iv_cancel);
                tvSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        timePickerView.returnData();
                        timePickerView.dismiss();
                    }
                });
                ivCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        timePickerView.dismiss();
                    }
                });
            }
        });

        timePickerView.show();
        return timePickerView;
    }

    //获取当前时间
    private static Calendar startCalendar() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));    //获取东八区时间
        int      year     = calendar.get(Calendar.YEAR);    //获取年
        int      month    = calendar.get(Calendar.MONTH);   //获取月份，0表示1月份
        int      day      = calendar.get(Calendar.DAY_OF_MONTH);    //获取当前天数
        int      hour     = calendar.get(Calendar.HOUR_OF_DAY);       //获取当前小时
        int      minute   = calendar.get(Calendar.MINUTE);          //获取当前分钟
        calendar.set(year, month, day, hour, minute);
        return calendar;

    }

    //获取结束时间,
    private static Calendar endCalendar() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));    //获取东八区时间
        int      year     = calendar.get(Calendar.YEAR) + 30;    //获取年,当前年份+30年
        int      month    = calendar.get(Calendar.MONTH);   //获取月份，0表示1月份
        int      day      = calendar.get(Calendar.DAY_OF_MONTH);    //获取当前天数
        int      hour     = calendar.get(Calendar.HOUR_OF_DAY);       //获取当前小时
        int      minute   = calendar.get(Calendar.MINUTE);          //获取当前分钟
        calendar.set(year, month, day, hour, minute);
        return calendar;
    }

    //获取结束时间,
    public static Calendar getCalendar(int yearOffect) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));    //获取东八区时间
        calendar.add(Calendar.YEAR, yearOffect);
        int year   = calendar.get(Calendar.YEAR);    //获取年,当前年份+30年
        int month  = calendar.get(Calendar.MONTH);   //获取月份，0表示1月份
        int day    = calendar.get(Calendar.DAY_OF_MONTH);    //获取当前天数
        int hour   = calendar.get(Calendar.HOUR_OF_DAY);       //获取当前小时
        int minute = calendar.get(Calendar.MINUTE);          //获取当前分钟
        calendar.set(year, month, day, hour, minute);
        return calendar;
    }


    public interface OnCityPickerViewListener {

        void selectCityText(String text);

    }

    /**
     * 信用卡时间选择器
     *
     * @param context              上下文
     * @param title                标题
     * @param type                 显示类型
     * @param onTimeSelectListener 回调
     * @return
     */
    public static TimePickerView selectBankCardTimePickerView(final Context context, String title, int type, final OnTimeSelectListener onTimeSelectListener) {

        TimePickerBuilder builder = new TimePickerBuilder(context, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                if (onTimeSelectListener != null) {
                    onTimeSelectListener.onTimeSelect(date, v);
                }
            }
        });
        builder.setRangDate(startCalendar(), endCalendar());
        builder.isCenterLabel(false); //是否只显示中间选中项的label文字，false则每项item全部都带有label。
        if (type == SHOW_Y_M_D) {
            builder.setType(new boolean[]{true, true, false, false, false, false}); //只显示年月
        } else if (type == SHOW_Y_M_D_H_M) {
            builder.setType(new boolean[]{true, true, true, true, true, false}); //不显示秒
        } else {
            builder.setType(new boolean[]{true, true, true, true, true, true}); //全部都显示
        }
        builder.setDividerColor(Color.BLACK);
        builder.setSubmitColor(ContextCompat.getColor(mContext, R.color.comm_color));
        builder.setCancelColor(ContextCompat.getColor(mContext, R.color.comm_color));
        if (!TextUtils.isEmpty(title)) {
            builder.setTitleText(title);
            builder.setTitleSize(15);
        }
        final TimePickerView timePickerView = builder.build();
        timePickerView.setDate(startCalendar());
        builder.setLayoutRes(R.layout.pickerview_custom_time, new CustomListener() {
            @Override
            public void customLayout(View v) {
                final TextView tvSubmit =  v.findViewById(R.id.tv_finish);
                TextView       ivCancel =  v.findViewById(R.id.iv_cancel);
                tvSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        timePickerView.returnData();
                        timePickerView.dismiss();
                    }
                });
                ivCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        timePickerView.dismiss();
                    }
                });
            }
        });
        timePickerView.show();
        return timePickerView;

    }


}
