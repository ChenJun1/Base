package com.base.kiap.utlis;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author: June
 * @CreateDate: 2020/11/7 4:27 PM
 * @Description: java类作用描述
 */
public class DateUtil {
    /*
     * 将时间转换为时间戳
     */
    public static String dateToStamp(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = simpleDateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd
     *
     * @param strDate
     * @return
     */
    public static Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }




    /**
     * 将短时间格式时间转换为字符串 yyyy-MM-dd
     *
     * @param dateDate
     * @return
     */
    public static String dateToStr(Date dateDate) {
        if (dateDate == null) {
            return "";
        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /**
     * 30天后
     * @param now
     * @return
     */
    public static String to30(Date now) {
        // 时间格式
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        // 计算某一月份的最大天数
        Calendar cal = Calendar.getInstance();
        // Date转化为Calendar
        cal.setTime(now);
        // 一月后的1天前
        cal.add(Calendar.DAY_OF_MONTH, 30);
        return df.format(cal.getTime());
    }

    /**
     * 当前往后多少天
     * @param day
     * @return
     */
    public static String onLaterDay(int day) {
        // 时间格式
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        Date date = new Date(System.currentTimeMillis());
        // 计算某一月份的最大天数
        Calendar cal = Calendar.getInstance();
        // Date转化为Calendar
        cal.setTime(date);
        // 一月后的1天前
        cal.add(Calendar.DAY_OF_MONTH, day);
        return df.format(cal.getTime());
    }

    /**
     * 当前往后多少月
     * @param month
     * @return
     */
    public static String onLaterMonth(int month) {
        // 时间格式
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

        Date date = new Date(System.currentTimeMillis());
        // 计算某一月份的最大天数
        Calendar cal = Calendar.getInstance();
        // Date转化为Calendar
        cal.setTime(date);
        // 一月后的1天前
        cal.add(Calendar.MONTH, month);
        return df.format(cal.getTime());
    }


}
