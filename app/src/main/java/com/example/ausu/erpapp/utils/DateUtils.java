package com.example.ausu.erpapp.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Lanxumit on 2016/9/13.
 */
public class DateUtils {
    public static Calendar instance = Calendar.getInstance();

    public static String getTime(Long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date d1 = new Date(time);
        String timeStr = format.format(d1);
        return timeStr;
    }

    public static String getCurrentDate(int i) {
        int month = instance.get(Calendar.MONTH) + 1;
        int day = instance.get(Calendar.DAY_OF_MONTH) + i;
        return month + "月" + day + "日";
    }

    public static String getCurrentDay(int day) {
        int i = instance.get(Calendar.DAY_OF_WEEK) + day;
        switch (i) {
            case 1:
                return "星期一";
            case 2:
                return "星期二";
            case 3:
                return "星期三";
            case 4:
                return "星期四";
            case 5:
                return "星期五";
            case 6:
                return "星期六";
            case 7:
                return "星期日";
        }
        return "";
    }

    public static List getNextServenDays() {
        SimpleDateFormat fomater = new SimpleDateFormat("MM月dd日");
        SimpleDateFormat eeee = new SimpleDateFormat("EEEE");
        List<String> dates = new ArrayList<String>();
        for (int i = 0; i < 7; i++) {
            Calendar calendar = Calendar.getInstance();
            int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
            calendar.set(Calendar.DAY_OF_YEAR, dayOfYear + i);
            dates.add(fomater.format(calendar.getTime()) + eeee.format(calendar.getTime()));
        }
        return dates;
    }

    public static String getAppointTime(String time) {
        SimpleDateFormat fomater = new SimpleDateFormat("yyyyMMddHHss");
        return fomater.format(time);
    }

}
