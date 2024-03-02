package com.xftxyz.gymadmin.helper;

import java.util.Calendar;
import java.util.Date;

public class DateHelper {

    // 获取若干天后的日期
    public static Date getAfterDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }
}
