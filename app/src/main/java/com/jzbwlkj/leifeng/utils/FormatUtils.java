package com.jzbwlkj.leifeng.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatUtils {

    private static final long ONE_MINUTE = 60000L;
    private static final long ONE_HOUR = 3600000L;
    private static final long ONE_DAY = 86400000L;
    private static final long ONE_WEEK = 604800000L;

    private static final String ONE_SECOND_AGO = "秒前";
    private static final String ONE_MINUTE_AGO = "分钟前";
    private static final String ONE_HOUR_AGO = "小时前";
    private static final String ONE_DAY_AGO = "天前";
    private static final String ONE_MONTH_AGO = "月前";
    private static final String ONE_YEAR_AGO = "年前";

    private static SimpleDateFormat sdf = new SimpleDateFormat();
    public final static String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    public final static String FORMAT_DATE_TIME2 = "yyyy-MM-dd HH:mm";
    public final static String FORMAT_DATE_TIME3 = "yyyy-MM-dd";
    public final static String FORMAT_DATE_TIME4 = "HH:mm";

    /**
     * 获取当前日期的指定格式的字符串
     *
     * @param format 指定的日期时间格式，若为null或""则使用指定的格式"yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static String getCurrentTimeString(String format) {
        if (format == null || format.trim().equals("")) {
            sdf.applyPattern(FORMAT_DATE_TIME);
        } else {
            sdf.applyPattern(format);
        }
        return sdf.format(new Date());
    }

    /**
     * 根据时间字符串获取描述性时间，如3分钟前，1天前
     *
     * @param dateString 时间字符串
     * @return
     */
    public static String getDescriptionTimeFromDateString(String dateString) {
        LogUtils.e("time:" + dateString);
        if (TextUtils.isEmpty(dateString))
            return "";
        sdf.applyPattern(FORMAT_DATE_TIME);
        try {
            String format = sdf.format(Long.valueOf(dateString));
            return getDescriptionTimeFromDate(sdf.parse(format));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 格式化时间
     *
     * @param time
     * @return
     */
    public static String formatTime(long time) {
        if (time == 0) return "";
        sdf.applyPattern(FORMAT_DATE_TIME2);
        return sdf.format(time * 1000);
    }

    /**
     * 格式化时间
     *
     * @param time
     * @return
     */
    public static String formatTime3(long time) {
        if (time == 0) return "";
        sdf.applyPattern(FORMAT_DATE_TIME4);
        return sdf.format(time * 1000);
    }

    /**
     * 转换时间差
     *
     * @param time
     * @return
     */
    public static String formatTime2(long time) {
        if (time == 0) return "";
        sdf.applyPattern(FORMAT_DATE_TIME4);
        return sdf.format(time * 1000);
    }

    /**
     * 格式化时间
     *
     * @param time
     * @return
     */
    public static String formatTime5(long time) {
        if (time == 0) return "";
        long miao = time % 60 % 60;//剩余秒数
        long fen = time / 60 % 60;//剩余分数
        long hh = time / 60 / 60;//小时数
        String mm = "";
        if (miao < 10) {
            mm = "0" + miao;
        } else {
            mm = miao + "";
        }
        String ff = "";
        if (fen < 10) {
            ff = "0" + fen;
        } else {
            ff = fen + "";
        }
        String shi = "";
        if (hh < 10) {
            shi = "0" + hh;
        } else {
            shi = hh + "";
        }
        String ss = shi + ":" + ff + ":" + mm;
        return ss;
    }

    /*将字符串转为时间戳*/
    public static long getStringToStamp(String time) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        try {
            date = sf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    /**
     * 根据Date获取描述性时间，如3分钟前，1天前
     *
     * @param date
     * @return
     */
    public static String getDescriptionTimeFromDate(Date date) {
        long delta = new Date().getTime() - date.getTime();
        if (delta < 1L * ONE_MINUTE) {
            long seconds = toSeconds(delta);
            return (seconds <= 0 ? 1 : seconds) + ONE_SECOND_AGO;
        }
        if (delta < 45L * ONE_MINUTE) {
            long minutes = toMinutes(delta);
            return (minutes <= 0 ? 1 : minutes) + ONE_MINUTE_AGO;
        }
        if (delta < 24L * ONE_HOUR) {
            long hours = toHours(delta);
            return (hours <= 0 ? 1 : hours) + ONE_HOUR_AGO;
        }
        if (delta < 48L * ONE_HOUR) {
            return "昨天";
        }
        if (delta < 30L * ONE_DAY) {
            long days = toDays(delta);
            return (days <= 0 ? 1 : days) + ONE_DAY_AGO;
        }
        if (delta < 12L * 4L * ONE_WEEK) {
            long months = toMonths(delta);
            return (months <= 0 ? 1 : months) + ONE_MONTH_AGO;
        } else {
            long years = toYears(delta);
            return (years <= 0 ? 1 : years) + ONE_YEAR_AGO;
        }
    }

    private static long toSeconds(long date) {
        return date / 1000L;
    }

    private static long toMinutes(long date) {
        return toSeconds(date) / 60L;
    }

    private static long toHours(long date) {
        return toMinutes(date) / 60L;
    }

    private static long toDays(long date) {
        return toHours(date) / 24L;
    }

    private static long toMonths(long date) {
        return toDays(date) / 30L;
    }

    private static long toYears(long date) {
        return toMonths(date) / 365L;
    }

    public static String formatWordCount(int wordCount) {
        if (wordCount / 10000 > 0) {
            return (int) ((wordCount / 10000f) + 0.5) + "万字";
        } else if (wordCount / 1000 > 0) {
            return (int) ((wordCount / 1000f) + 0.5) + "千字";
        } else {
            return wordCount + "字";
        }
    }
}