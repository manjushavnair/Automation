package com.automation.ui.base.common.utils;


/* java specific imports */

import org.apache.log4j.Logger;

import java.util.Date;
import java.util.TimeZone;


public class TimeZoneUtil {
    private static String longName = null;
    private static String shortName = null;
    private static TimeZone defaultTZ = TimeZone.getDefault();
    //Log4j category logger object.


    private static Logger logger = Logger
            .getLogger(TimeZoneUtil.class);


    public TimeZoneUtil() {
    }

    /**
     * This method returns a String representing the current hour of the day.
     *
     * @param hour DOCPARAM!
     * @return DOCRETURN!
     */
    public static String getHour(int hour) {
        if (hour <= 11)
            return new Integer(hour).toString() + ":00 AM";
        else if (hour == 12)
            return "12:00 PM";
        else
            return new Integer(hour - 12).toString() + ":00 PM";
    }


    public static boolean isDayLight(Date date) {
        return defaultTZ.inDaylightTime(date);

    }

    public static TimeZone getDefaultTimeZone() {
        return defaultTZ;
    }


    public static String getServerTZShortName() {
        if (shortName == null) {
            shortName = getServerTimeZone(defaultTZ.SHORT);
        }
        return shortName;
    }


    public static String getServerTZLongName() {
        if (longName == null) {
            longName = getServerTimeZone(defaultTZ.LONG);
        }
        return longName;
    }


    private static String getServerTimeZone(int TYPE) {
        Date d = new Date();
        boolean dLight = (isDayLight(d)) ? true : false;
        // serverTZ = defaultTZ.getDisplayName(dLight,defaultTZ.SHORT);
        return defaultTZ.getDisplayName(dLight, TYPE);


    }


    public static String getTimeStamp() {
        Date dTime = new Date();

        long timeCurrent = dTime.getTime();
        return String.valueOf(timeCurrent);


    }


    public static void main(String[] args) {
        TimeZoneUtil t = new TimeZoneUtil();
        logger.debug("long name:(" + t.getServerTZLongName() + ")");
        logger.debug("short name tz:(" + t.getServerTZShortName() + ")");

    }
}