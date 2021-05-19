package com.example.huhong.painDiary.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: yzh
 * @Description: 获取时间字符串的工具类
 * @Date: 2020/8/18 16:01
 * @LastModify: 2020/8/19 17:00
 */
public class DatetimeUtil {

    /**
     * 获取当前时间的8位字符串
     * @return 返回时间字符串yyyy-MM-dd
     */
    public static String getNowDate() {
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return time.format(date);
    }

    /**
     *
     * 获取当前时间（精确到分）的字符串
     * @return 返回时间字符串yyyy-MM-dd HH:mm
     */
    public static String getTheTimeInMinutes() {
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        return time.format(date);
    }

    /**
     * 获取当前时间（精确到秒）的字符串
     * @return 返回时间字符串yyyy-MM-dd HH:mm:ss
     */
    public static String getTheTimeInSeconds(Date date) {
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return time.format(date);
    }

    /**
     * 获取当前时间（精确到毫秒）的字符串
     * @return 返回时间字符串yyyy-MM-dd HH:mm:ss.SSS
     */
    public static String getTheTimeInMilliseconds() {
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date date = new Date();
        return time.format(date);
    }

}

