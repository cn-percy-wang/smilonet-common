///////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (C) 2012 smilonet.
// All rights reserved
///////////////////////////////////////////////////////////////////////////////////////////////////
package com.smilonet.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间对象和字符串的转换工具
 * 
 * @author wanglong(a)smilonet.com
 * @version 1.0
 * @since 1.0
 */
public class DateTimeUtils {

    // 工具类不允许创建实例
    private DateTimeUtils() {
    }

    public enum TimeUnit {
        year, month, week, day, hour, minute, secend, millisecond
    }

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final Long MSEL_PER_SECEND = 1000l;
    public static final Long MSEL_PER_MINUTE = MSEL_PER_SECEND * 60;
    public static final Long MSEL_PER_HOUR = MSEL_PER_MINUTE * 60;
    public static final Long MSEL_PER_DAY = MSEL_PER_HOUR * 24;
    public static final Long MSEL_PER_WEEK = MSEL_PER_DAY * 7;

    /**
     * 按照yyyy-MM-dd的格式将日期转换为字符串
     * 
     * @param date
     * @return
     */
    public static String convertDateToString(Date date) {
        return convertDateToString(date, DEFAULT_DATE_FORMAT);
    }

    /**
     * 按照yyyy-MM-dd HH:mm:ss的格式将日期转换为字符串
     * 
     * @param date
     * @return
     */
    public static String convertTimeToString(Date date) {
        return convertTimeToString(date, DEFAULT_DATE_FORMAT);
    }

    /**
     * 按照指定格式，将日期转换为字符串
     * 
     * @param date
     * @param pattern
     * @return
     */
    public static String convertDateToString(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 按照指定格式，将日期转换为字符串
     * 
     * @param date
     * @param pattern
     * @return
     */
    public static String convertTimeToString(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 按照格式将字符串转换为date
     * 
     * @param dateString
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Date convertStringToDate(String dateString, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(dateString);
    }

    /**
     * 按照yyyy-MM-dd的格式将字符串转换为日期
     * 
     * @param dateString
     * @return
     * @throws ParseException
     */
    public static Date convertStringToDate(String dateString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
            return sdf.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 按照yyyy-MM-dd的格式将字符串转换为日期
     * 
     * @param dateString
     * @return
     * @throws ParseException
     */
    public static Date convertStringToTime(String dateString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_TIME_FORMAT);
        return sdf.parse(dateString);
    }

    /**
     * 获取当前的系统日期
     * 
     * @return 系统当前日期，格式年(4位)
     */
    public static String getYear() {
        Calendar CD = Calendar.getInstance();
        int YY = CD.get(Calendar.YEAR);
        StringBuffer curDateString = new StringBuffer();
        curDateString.append(YY);
        return curDateString.toString();
    }

    /**
     * 获取当前的系统日期
     * 
     * @return 系统当前日期，格式月（两位）
     */
    public static String getMonth() {
        Calendar CD = Calendar.getInstance();
        int MM = CD.get(Calendar.MONTH) + 1;
        StringBuffer curDateString = new StringBuffer();
        curDateString.append(MM);
        return curDateString.toString();
    }

    /**
     * 获取当前的系统日期
     * 
     * @return 系统当前日期，格式日（两位）
     */
    public static String getDay() {
        Calendar CD = Calendar.getInstance();
        int DD = CD.get(Calendar.DATE);
        StringBuffer curDateString = new StringBuffer();
        curDateString.append(DD);
        return curDateString.toString();
    }

    public static String getDate(String dateFormat) {
        return convertDateToString(new Date(), dateFormat);
    }

    /**
     * 获取当前的系统日期
     * 
     * @return 系统当前日期，格式西元年(4位)+月(2位)+日(2位)
     */
    public static String getDate() {
        return convertDateToString(new Date());
        // Calendar CD = Calendar.getInstance();
        // int YY = CD.get(Calendar.YEAR);
        // int MM = CD.get(Calendar.MONTH) + 1;
        // int DD = CD.get(Calendar.DATE);
        // StringBuffer curDateString = new StringBuffer();
        // curDateString.append(YY);
        // curDateString.append("-");
        // curDateString.append(StringUtils.formatStringLength(String.valueOf(MM),
        // '0', 2));
        // curDateString.append("-");
        // curDateString.append(StringUtils.formatStringLength(String.valueOf(DD),
        // '0', 2));
        // return curDateString.toString();
    }

    /**
     * 获取当前的系统时间
     * 
     * @return 系统当前时间，格式时(2位)+分(2位)+秒(2位)
     */
    public static String getDateTime() {
        Calendar CD = Calendar.getInstance();
        int YY = CD.get(Calendar.YEAR);
        int MM = CD.get(Calendar.MONTH) + 1;
        int DD = CD.get(Calendar.DATE);
        int HH = CD.get(Calendar.HOUR_OF_DAY);
        int mm = CD.get(Calendar.MINUTE);
        int ss = CD.get(Calendar.SECOND);
        StringBuffer curDateTimeString = new StringBuffer();
        curDateTimeString.append(String.valueOf(YY));
        curDateTimeString.append("-");
        curDateTimeString.append(StringUtils.formatStringLength(String.valueOf(MM), '0', -2));
        curDateTimeString.append("-");
        curDateTimeString.append(StringUtils.formatStringLength(String.valueOf(DD), '0', -2));
        curDateTimeString.append(" ");
        curDateTimeString.append(StringUtils.formatStringLength(String.valueOf(HH), '0', -2));
        curDateTimeString.append(":");
        curDateTimeString.append(StringUtils.formatStringLength(String.valueOf(mm), '0', -2));
        curDateTimeString.append(":");
        curDateTimeString.append(StringUtils.formatStringLength(String.valueOf(ss), '0', -2));
        return curDateTimeString.toString();
    }

    /**
     * 获取当前的系统时间
     * 
     * @return 系统当前时间，格式时(2位)+分(2位)+秒(2位)
     */
    public static String getTime() {
        Calendar CD = Calendar.getInstance();
        int HH = CD.get(Calendar.HOUR_OF_DAY);
        int mm = CD.get(Calendar.MINUTE);
        int ss = CD.get(Calendar.SECOND);
        StringBuffer curTimeString = new StringBuffer();
        curTimeString.append(StringUtils.formatStringLength(String.valueOf(HH), '0', -2));
        curTimeString.append(":");
        curTimeString.append(StringUtils.formatStringLength(String.valueOf(mm), '0', -2));
        curTimeString.append(":");
        curTimeString.append(StringUtils.formatStringLength(String.valueOf(ss), '0', -2));
        return curTimeString.toString();
    }

    /**
     * 获取当前的系统时间的小时
     * 
     * @return 系统当前小时
     */
    public static int getHour() {
        Calendar CD = Calendar.getInstance();
        int HH = CD.get(Calendar.HOUR_OF_DAY);
        return HH;
    }

    /**
     * 获取当前的系统时间的分钟
     * 
     * @return 系统当前分钟
     */
    public static int getMinute() {
        Calendar CD = Calendar.getInstance();
        int mm = CD.get(Calendar.MINUTE);
        return mm;
    }

    public static Long getTimeDispersion(Date beginTime, Date endTime, TimeUnit timeUnit) {

        if (endTime == null) {
            return null;
        }

        Long dis = endTime.getTime() - beginTime.getTime();
        Long result = dis;
        switch (timeUnit) {
        case year:
        case month:
            break;
        case week:
            result = dis / MSEL_PER_WEEK;
            break;
        case day:
            result = dis / MSEL_PER_DAY;
            break;
        case hour:
            result = dis / MSEL_PER_HOUR;
            break;
        case minute:
            result = dis / MSEL_PER_MINUTE;
            break;
        case secend:
            result = dis / MSEL_PER_SECEND;
            break;
        case millisecond:
            break;
        }
        return result;
    }

    /**
     * 获取两个日期的差
     * 
     * @param beginTime
     * @param endTime
     * @param timeUnit
     * @return
     */
    public static Long getTimeDispersion(String beginTime, String endTime, TimeUnit timeUnit) {
        try {
            if (StringUtils.isEmpty(endTime)) {
                return null;
            }
            return getTimeDispersion(convertStringToTime(endTime), convertStringToTime(beginTime), timeUnit);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 将时间向前（正数），后（负数）移动
     * 
     * @param beMovedDateString
     * @param movedDays
     * @return
     * @throws ParseException
     */
    public static String moveTime(String beMovedTimeString, int movedDays) throws ParseException {
        if (StringUtils.isEmpty(beMovedTimeString)) {
            beMovedTimeString = getDateTime();
        }
        Date beMovedTime = convertStringToTime(beMovedTimeString);
        Date moveResult = moveDateTime(beMovedTime, movedDays);
        return convertTimeToString(moveResult);
    }

    /**
     * 将日期向前（正数），后（负数）移动
     * 
     * @param beMovedDateString
     * @param movedDays
     * @return
     * @throws ParseException
     */
    public static String moveDate(String beMovedDateString, int movedDays) throws ParseException {
        if (StringUtils.isEmpty(beMovedDateString)) {
            beMovedDateString = getDate();
        }
        Date beMovedDate = convertStringToTime(beMovedDateString);
        Date moveResult = moveDateTime(beMovedDate, movedDays);
        return convertDateToString(moveResult);
    }

    /**
     * 移动日期
     * 
     * @param movedDate
     * @param movedNum
     * @return
     */
    public static Date moveDateTime(Date movedDate, int movedNum) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(movedDate);
        calendar.add(Calendar.DAY_OF_MONTH, movedNum);
        return calendar.getTime();
    }

    /**
     * 判断字符串是否是日期
     * 
     * @param targetString
     * @return
     */
    public static boolean isDate(String targetString) {
        if (StringUtils.matchReg(targetString, "^(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)$")) {
            return true;
        } else {
            return false;
        }
    }

}
