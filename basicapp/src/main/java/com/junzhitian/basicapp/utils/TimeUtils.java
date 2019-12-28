package com.junzhitian.basicapp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @作者:TJ
 * @时间:2018/2/27 15:57
 * @描述:时间相关转换
 */
public class TimeUtils {

    /**
     * 时间戳转换成日期格式字符串
     *
     * @param seconds 精确到秒的字符串
     * @param format
     * @return
     */
    public static String timeStampToDate(String seconds, String format) {
        if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty())
            format = "yyyy-MM-dd HH:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        return sdf.format(new Date(Long.valueOf(seconds + "000")));
    }

    /**
     * 时间戳转换成日期格式字符串
     *
     * @param seconds 精确到毫秒
     * @param format
     * @return
     */
    public static String timeStampToDate(long seconds, String format) {
        if (seconds == 0) {
            return "";
        }
        if (format == null || format.isEmpty())
            format = "yyyy-MM-dd HH:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        return sdf.format(new Date(seconds));
    }


    /**
     * 将时间转换为时间戳
     */
    public static String dateToTimeStamp(String s) {
        String           res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date             date             = null;
        try {
            date = simpleDateFormat.parse(s);
            long ts = date.getTime();
            res = String.valueOf(ts);
            return res;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 将时间转换为时间戳
     */
    public static long date2TimeStamp(String s) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date             date             = null;
        try {
            date = simpleDateFormat.parse(s);
            long ts = date.getTime();
            return ts;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 将时间转换为时间戳
     */
    public static long date2TimeStamp(String s, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date             date;
        try {
            date = simpleDateFormat.parse(s);
            long ts = date.getTime();
            return ts;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 根据当前时间，获取N天后的日期 年-月-日
     *
     * @param days
     * @return
     */
    public static String getAfterDaysToYMD(int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, days);
        String endDate = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
        return endDate;
    }


    /**
     * 获取某天的day后的时间
     *
     * @param days
     * @return
     */
    public static String getAfterDaysToYMD(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        String endDate = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
        return endDate;
    }

    public static Calendar getAfterDaysCalendar(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar;
    }

    public static Calendar getAfterMonthsCalendar(Date date, int months) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, months);
        return calendar;
    }

    /**
     * 获取某月的months后的时间
     *
     * @return
     */
    public static String getAfterMonthsToYMD(Date date, int moths) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, moths);
        String endDate = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
        return endDate;
    }


    /**
     * 格式化data
     *
     * @param date
     * @return
     */
    public static String getFormatYMD(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date.getTime());
    }

    public static String getFormatYM(Date date) {
        return new SimpleDateFormat("yyyy-MM").format(date.getTime());
    }


    /**
     * 获取当前的时间并格式化
     *
     * @param format
     * @return
     */
    public static String getDateTime(String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(new Date());
    }


    /**
     * 根据时间获取当前星期几
     */
    public static int getDayOfWeek(long ts) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(ts);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }


    public static Date getThisWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        return cal.getTime();
    }

    public static Date getNextWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getThisWeekMonday(date));
        cal.add(Calendar.DATE, 7);
        return cal.getTime();
    }

    /**
     * 获取某月第index个周一的日期
     *
     * @param date
     * @param index
     * @return
     */
    public static Date getTheMonthMonday(Date date, int index) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getThisWeekMonday(date));
        cal.add(Calendar.DATE, 7 * index);
        return cal.getTime();
    }

    /**
     * 格式化标准年月日
     *
     * @param y
     * @param m
     * @param d
     * @return
     */
    public static String formatYMD(String y, String m, String d) {
        return String.format("%d-%02d-%02d", y, m, d);
    }
}
