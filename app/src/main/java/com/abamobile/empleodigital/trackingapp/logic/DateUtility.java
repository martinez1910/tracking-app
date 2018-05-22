package com.abamobile.empleodigital.trackingapp.logic;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Set of static methods related to date formatting.
 * @version 1.0
 * @see Date
 * @see SimpleDateFormat
 */
public class DateUtility {

    /**
     * Returns the given date in 'hh:mm:ss' format
     * @param date
     * @return string in 'hh:mm:ss' format
     * @see Date
     * @see SimpleDateFormat
     */
    public static String hourFormat(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");

        return formatter.format(date);
    }

    /**
     * Returns the given date in 'dd Mmmm yyyy' format
     * @param date
     * @return string in 'dd Mmmm yyyy' format
     * @see Date
     * @see SimpleDateFormat
     */
    public static String dateFormat(Date date) {

        SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy");
        format.setTimeZone(TimeZone.getTimeZone("UTC"));

        return format.format(date);
    }


    /**
     * Returns the given date in 'dd Mmmm yyyy hh:mm:ss' format
     * @param date
     * @return string in 'dd Mmmm yyyy hh:mm:ss'format
     * @see Date
     * @see SimpleDateFormat
     */
    public static String dateHourFormat(Date date) {
        return dateFormat(date) + " " + hourFormat(date);
    }

    /**
     * Returns the given time (provided in milliseconds) in 'hh:mm:ss' format
     * @param time time in milliseconds
     * @return string in 'hh:mm:ss' format
     */
    public static String workTime(long time) {
        String hourString;
        String minuteString;
        String secondString;
        long temp = time;
        long miliseconds = temp % 1000;
        temp = (temp - miliseconds) / 1000;
        long second = temp % 60;
        temp = (temp - second) / 60;
        long minute = temp % 60;
        long hour = (temp - minute) / 60;
        if(second < 10){
            secondString = "0" + second;
        } else {
            secondString = "" + second;
        }
        if(minute < 10){
            minuteString = "0" + minute;
        } else {
            minuteString = "" + minute;
        }
        if(hour < 10){
            hourString = "0" + hour;
        } else {
            hourString = "" + hour;
        }
        return hourString + ":" + minuteString + ":" + secondString;
    }


    /**
     * Returns the given time(provided in milliseconds) in the given format.
     * @param format the format of the returning string
     * @param time the time in milliseconds
     * @return string in the given format
     */
    public static String dateFormat(String format, long time){
        SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.getDefault());
        return formatter.format(new Date(time));
    }
}
