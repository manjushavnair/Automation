package com.automation.ui.base.common.utils;

import org.apache.log4j.Logger;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class DateUtil {


    /**
     * This is the static constant that stores pattern for Date-Time.
     */
    public static final String DATETIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";
    /**
     * This is the static constant that stores Default pattern for Date.
     */
    public static final String TIMESTAMP_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS";
    /**
     * This is the static constant that stores MM/dd/yyyy' - 'hh:mm' 'a pattern.
     */
    public static final String PATTERN_1 = "MM/dd/yyyy' - 'hh:mm' 'a";
    /**
     * This is the static constant that stores dd MMM yyyy a pattern.
     */
    public static final String PATTERN_2 = "dd MMM yyyy";
    /**
     * This is static constant that stores dd/MM/YYY pattern.
     **/

    public static final String PATTERN_3 = "dd/MM/yyyy";
    public static final String SCRIPT_NODE = "Script";
    public static final String MONTH_NAMES_NODE = "MonthNames";
    public static final String MONTH_ACPT_FORMATS = "AcceptableFormats";
    public static final String MONTH_DEF_FORMAT = "DefaultFormat";
    private static final String DEFAULT_DATE_PATTERN = "dd/MM/yyyy";
    public static String DEFAULT_DISPLAY_FORMAT_FOR_ALL = "dd MMM yyyy";
    public static String DD_MMM_YY = "dd MMM yyyy";
    public static String DD_MMM_YY_HH_MM_SS = "dd MMM yyyy hh:mm:ss";
    public static SimpleDateFormat dateFormater = new SimpleDateFormat(DD_MMM_YY);
    public static SimpleDateFormat dateTimeFormater = new SimpleDateFormat(DD_MMM_YY_HH_MM_SS);
    public static int[] MONTH_IDS =
            new int[]{
                    Calendar.JANUARY,
                    Calendar.FEBRUARY,
                    Calendar.MARCH,
                    Calendar.APRIL,
                    Calendar.MAY,
                    Calendar.JUNE,
                    Calendar.JULY,
                    Calendar.AUGUST,
                    Calendar.SEPTEMBER,
                    Calendar.OCTOBER,
                    Calendar.NOVEMBER,
                    Calendar.DECEMBER};
    private static Logger logger = Logger
            .getLogger(DateUtil.class);
    private static HashMap localeDateFormaters;
    private static DateUtil formaterFactory;

    private DateUtil() {
        localeDateFormaters = new HashMap();


    }

    public static DateUtil getInstance() {
        if (formaterFactory == null) {
            formaterFactory = new DateUtil();
        }

        return formaterFactory;
    }

    /**
     * A utility method that will return the Date in given the date format.  Ex : fromFormat = yyyy/MM/dd  value = 2001/02/01
     *
     * @param format String
     *               Ex : format = yyyy/MM/dd  return string will be 2001/02/01
     *               Symbol   Meaning                 Presentation        Example
     *               ------   -------                 ------------        -------
     *               G        era designator          (Text)              AD
     *               y        year                    (Number)            1996
     *               M        month in year           (Text &amp; Number)     July & 07
     *               d        day in month            (Number)            10
     *               h        hour in am/pm (1~12)    (Number)            12
     *               H        hour in day (0~23)      (Number)            0
     *               m        minute in hour          (Number)            30
     *               s        second in minute        (Number)            55
     *               S        millisecond             (Number)            978
     *               E        day in week             (Text)              Tuesday
     *               D        day in year             (Number)            189
     *               F        day of week in month    (Number)            2(2nd Wed in July)
     *               w        week in year            (Number)            27
     *               W        week in month           (Number)            2
     *               a        am/pm marker            (Text)              PM
     *               k        hour in day (1~24)      (Number)            24
     *               K        hour in am/pm (0~11)    (Number)            0
     *               z        time zone               (Text)              Pacific Standard Time
     *               '        escape for text         (Delimiter)
     *               ''       single quote            (Literal)           '
     */
    public static String dateFormatter(Date date, String format) {

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            return simpleDateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    } // END OF METHOD

    /**
     * A utility method that will return the Date in given the date format.  Ex : fromFormat = yyyy/MM/dd  value = 2001/02/01
     *
     * @param format dateString
     * @param format String
     * @author Aravind
     * Ex : format = yyyy/MM/dd  return string will be 2001/02/01
     * Symbol   Meaning                 Presentation        Example
     * ------   -------                 ------------        -------
     * G        era designator          (Text)              AD
     * y        year                    (Number)            1996
     * M        month in year           (Text &amp; Number)     July & 07
     * d        day in month            (Number)            10
     * h        hour in am/pm (1~12)    (Number)            12
     * H        hour in day (0~23)      (Number)            0
     * m        minute in hour          (Number)            30
     * s        second in minute        (Number)            55
     * S        millisecond             (Number)            978
     * E        day in week             (Text)              Tuesday
     * D        day in year             (Number)            189
     * F        day of week in month    (Number)            2(2nd Wed in July)
     * w        week in year            (Number)            27
     * W        week in month           (Number)            2
     * a        am/pm marker            (Text)              PM
     * k        hour in day (1~24)      (Number)            24
     * K        hour in am/pm (0~11)    (Number)            0
     * z        time zone               (Text)              Pacific Standard Time
     * '        escape for text         (Delimiter)
     * ''       single quote            (Literal)           '
     */
    public static Date dateFormatter(String dateString, String format) {

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            return simpleDateFormat.parse(dateString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    } // END OF METHOD

    public static String getMonth(String pMonthName) {
        if (pMonthName.equalsIgnoreCase("JAN"))
            return "00";
        else if (pMonthName.equalsIgnoreCase("FEB"))
            return "01";
        else if (pMonthName.equalsIgnoreCase("MAR"))
            return "02";
        else if (pMonthName.equalsIgnoreCase("APR"))
            return "03";
        else if (pMonthName.equalsIgnoreCase("MAY"))
            return "04";
        else if (pMonthName.equalsIgnoreCase("JUN"))
            return "05";
        else if (pMonthName.equalsIgnoreCase("JUL"))
            return "06";
        else if (pMonthName.equalsIgnoreCase("AUG"))
            return "07";
        else if (pMonthName.equalsIgnoreCase("SEP"))
            return "08";
        else if (pMonthName.equalsIgnoreCase("OCT"))
            return "09";
        else if (pMonthName.equalsIgnoreCase("NOV"))
            return "10";
        else if (pMonthName.equalsIgnoreCase("DEC"))
            return "11";
        return "00";
    }

    public static String getDateFormat(String dateTobeFormated) {

        logger.debug(" dateTobeFormated : " + dateTobeFormated);
        String day = dateTobeFormated.substring(0, 2);
        String month = dateTobeFormated.substring(3, 6);
        String year = dateTobeFormated.substring(6);
        logger.debug(" Month : " + month + " : Year : " + year + " : Day : " + day);

        String monthInNumeric = getMonth(month);

        logger.debug(" monthInNumeric : " + monthInNumeric);

        String formatedDate = monthInNumeric + "/" + day + "/" + year;

        logger.debug(" formatedDate : " + formatedDate);

        return formatedDate;

    }

    /**
     * Get the months in the passed in display pattern and Locale.
     * <p>
     * If the argument <code>displayPattern</code> is <code>null</code> then default pattern
     * for Month ("MMMMM") is used. If the argument <code>loc</code> is <code>null</code> then
     * English is used as Locale.
     *
     * @param displayPattern display pattern
     * @param loc            Locale
     * @return String[] months in specified dsiplay pattern and Locale
     */
    public static String[] getMonths(String displayPattern, Locale loc) {

        if (displayPattern == null) {
            displayPattern = "MMMMM";
        }

        if (loc == null) {
            loc = Locale.ENGLISH;
        }

        SimpleDateFormat formatter = new SimpleDateFormat(displayPattern, loc);

        Calendar cal = Calendar.getInstance(loc);

        final int months[] = {
                Calendar.JANUARY,
                Calendar.FEBRUARY,
                Calendar.MARCH,
                Calendar.APRIL,
                Calendar.MAY,
                Calendar.JUNE,
                Calendar.JULY,
                Calendar.AUGUST,
                Calendar.SEPTEMBER,
                Calendar.OCTOBER,
                Calendar.NOVEMBER,
                Calendar.DECEMBER
        };

        String[] monthsStr = new String[months.length];

        Date date = null;
        String monthStr = null;

        // set the day of the month to start day of the month
        cal.set(Calendar.DAY_OF_MONTH, 1);

        for (int i = 0; i < months.length; i++) {

            cal.set(Calendar.MONTH, months[i]);
            date = cal.getTime();

            monthStr = formatter.format(date);

            monthsStr[i] = monthStr;
        }

        return monthsStr;
    }

    public static final String getWeekDate(int dd, int mm, int yyyy) {
        DateUtil DateUtil = new DateUtil();
        //First week

        int noOfDays = DateUtil.getNumOfDaysInMonth(mm, yyyy);
        String WeekDate = "";
        String strMnthName = DateUtil.getMonthName(mm).substring(0, 3);
        String strYear = new Integer(yyyy).toString();

        WeekDate = dd + "-" + strMnthName + "-" + strYear;
        logger.debug("getWeekDate::WeekDate:" + WeekDate);

        return WeekDate;
    }

    public static final String whichday(int mnth, int year, int day) {
        String whichday = "";
        String formatedDate = "";
        GregorianCalendar lCal = new GregorianCalendar();
        lCal.set(Calendar.MONTH, mnth);
        lCal.set(Calendar.YEAR, year);
        lCal.set(Calendar.DAY_OF_MONTH, day);

        logger.debug("DAY of week: " + lCal.get(Calendar.DAY_OF_WEEK));
        if (lCal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {

            whichday = "SUNDAY";

        } else if (lCal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {

            whichday = "MONDAY";
        } else if (lCal.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {

            whichday = "TUESDAY";
        } else if (lCal.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {

            whichday = "WEDNESDAY";
        } else if (lCal.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {

            whichday = "THURSDAY";
        } else if (lCal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {

            whichday = "FRIDAY";
        } else if (lCal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {

            whichday = "SATURDAY";
        }
        logger.debug("Thie date is : " + whichday);
        return whichday;
    }

    public static final int getEndWeekDay(String day) {
        int returnDay = 0;
        if (day.equals("SUNDAY")) {
            returnDay = 6;
        } else if (day.equals("MONDAY")) {
            returnDay = 5;
        } else if (day.equals("TUESDAY")) {
            returnDay = 4;
        } else if (day.equals("WEDNESDAY")) {
            returnDay = 3;
        } else if (day.equals("THURSDAY")) {
            returnDay = 2;
        } else if (day.equals("FRIDAY")) {
            returnDay = 1;
        } else if (day.equals("SATURDAY")) {
            returnDay = 0;
        }
        return returnDay;
    }

    /**
     * This method is used for formatting the given date to string using
     * default date pattern.
     *
     * @param a_date The date to be converted into string using default pattern.
     * @return String    The converted string of the date using the default date
     * pattern.
     */
    public static final String formatDate(java.util.Date a_date) {
        return formatDate(a_date, DEFAULT_DATE_PATTERN);
    }

    /**
     * This method is used for formatting the given date to string using
     * given date pattern.
     *
     * @param a_date    The date to be converted into string using
     *                  default pattern.
     * @param a_pattern The pattern used for converting the date
     *                  to a string.
     * @return String    The converted string of the date using the
     * given date pattern.
     */
    public static final String formatDate(java.util.Date a_date,
                                          String a_pattern) {
        SimpleDateFormat formater = new SimpleDateFormat(a_pattern);
        String ds = formater.format(a_date);
        return ds;
    }

    // -------------

    /**
     * This method is used for parsing the given string to date using
     * default date pattern.
     *
     * @param a_date The string to be converted into date
     *               using default pattern.
     * @return Date        The converted date of the string using
     * the default date pattern.
     * @throws ParseException If the string passed as argument cannot
     *                        be parsed into date using default date pattern.
     */
    public static final java.util.Date parseDate(String a_date)
            throws ParseException {
        SimpleDateFormat formater = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
        java.util.Date d = formater.parse(a_date);
        return d;
    }

    public static final java.util.Date parseDate(String a_date, String strPattern)
            throws ParseException {
        SimpleDateFormat formater = new SimpleDateFormat(strPattern);
        java.util.Date d = formater.parse(a_date);
        return d;
    }

    /*
     * This method returns the Date  for the input string in the 11 Aug 2000 format.
     *
     * @param String strDate should be in 11 Aug 2000 format.
     * @reurn Date after the format.
     *        null for empty input and invalid formats.
     *
     */
    public static Date getDate(String strDate) {
        if (StringUtils.isNull(strDate)) {
            return null;
        }
        strDate = strDate.trim();
        //if (strDate.length() != 11) {
        //    return null;
        //}
        try {
            return dateFormater.parse(strDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
     * This method returns the Date  for the input string in the 11 Aug 2000 format.
     *
     * @param String strDate should be in 11 Aug 2000 format.
     * @reurn Date after the format.
     *        null for empty input and invalid formats.
     *
     */
    public static Date getDateTime(String strDate) {
        if (StringUtils.isNull(strDate)) {
            return null;
        }
        strDate = strDate.trim();
        //if (strDate.length() != 11) {
        //    return null;
        //}
        try {
            return dateTimeFormater.parse(strDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
     * This method returns the String value for the input Date in '11 Aug 2000' format.
     *
     * @param Date dtDate is the input date.
     * @reurn String in '11 Aug 2000' format.
     *        null for empty input.
     *
     */
    public static String getDateString(Date dtDate) {
        if (dtDate == null) {
            return "";
        }
        try {
            return dateFormater.format(dtDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
     * This method returns the String value for the input Date in '11 Aug 2000 11:12:12' format.
     *
     * @param Date dtDate is the input date.
     * @reurn String in '11 Aug 2000 11:11:11' format.
     *        null for empty input.
     *
     */
    public static String getDateTimeString(Date dtDate) {
        if (dtDate == null) {
            return "";
        }
        try {
            return dateTimeFormater.format(dtDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
     * This method returns the String value for the input Date in '11 Aug 2000 11:12:12' format.
     *
     * @param Timestamp timeStamp is the input timestamp value.
     * @reurn String in '11 Aug 2000 11:11:11' format.
     *        null for empty input.
     *
     */
    public static String getDateTimeString(Timestamp timeStamp) {
        if (timeStamp == null) {
            return "";
        }
        try {
            return dateTimeFormater.format(timeStamp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getDateDifferenceInDays(Date max, Date min) {
        if (max == null || min == null) {
            return 0;
        }
        return (int) ((max.getTime() - min.getTime()) / 86400000);
    }

    public static int getDateDifferenceInDays(String dt1, String dt2) {
        return getDateDifferenceInDays(getDate(dt1), getDate(dt2));
    }

    public static int getDateDifferenceInMinutes(String dt1, String dt2) {
        return getDateDifferenceInMinutes(getDate(dt1), getDate(dt2));
    }

    public static int getDateDifferenceInMinutes(Date max, Date min) {
        return (int) ((max.getTime() - min.getTime()) / 60000);
    }

    public static String getCurrentDateInFooterFormat() {
        SimpleDateFormat curDateFormater = new SimpleDateFormat("EEEEEEEEE, MMMMMMMMM dd, yyyy 'at' hh:mm:ss a");
        String curDate = curDateFormater.format(new Date());
        return curDate;
    }



    public static String getCurrentDateInReportFormat() {
        SimpleDateFormat curDateFormater = new SimpleDateFormat("yyyy-MM-dd_hh-mm");
        String curDate = curDateFormater.format(new Date());
        return curDate;
    }

    public static String getCurrentDateTime() {

        DateFormat dateFormat = new SimpleDateFormat("_yyyy-MM-dd_HH-mm-ss");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new java.util.Date());

       /* int intYear        = cal.get(Calendar.YEAR);
        int intMon         = cal.get(Calendar.MONTH);
        int intDate       = cal.get(Calendar.DATE);
        int intHH       = cal.get(Calendar.HOUR_OF_DAY);
        int intMM        = cal.get(Calendar.MINUTE);
        int intSS        = cal.get(Calendar.SECOND);

        String strDate = intDate + "-" + intMon + "-" + intYear + "-" + intHH + "-" +intMM  + "-" + intSS;
        */

        String time = "" + dateFormat.format(cal.getTime());
        return time;
    }

    public static String getCurrentDate() {
        return getCurrentDateTime().substring(0, 11);
    }

    /**
     * Method isValidDate.
     * Validates the date value in MM/DD/YYYY format and checks the numbers are
     * within the range or not
     *
     * @param date
     * @return boolean
     */
    public static boolean isValidDate(String date) {

        //date has to be in mm/dd/yyyy formate
        int month = 0;
        int day = 0;
        int year = 0;

        StringTokenizer st = new StringTokenizer(date, "/");
        if (st.countTokens() != 3) {
            return false;
        }

        String mm = st.nextToken();
        String dd = st.nextToken();
        String yyyy = st.nextToken();

        if (!NumberUtil.isNumber(mm)
                || !NumberUtil.isNumber(dd)
                || !NumberUtil.isNumber(yyyy)
                || !isValidFieldLength(yyyy, 4, 4)) {
            return false;
        }

        month = Integer.parseInt(mm);
        day = Integer.parseInt(dd);
        year = Integer.parseInt(yyyy);

        if ((month < 1) || (month > 12)) {
            return false;
        }

        if ((day < 1) || (day > 31)) {
            return false;
        }

        if ((month == 4 || month == 6 || month == 9 || month == 11)
                && day == 31) {
            return false;
        }

        if (month == 2) {
            // check for february 29th
            boolean isleap =
                    (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
            if (day > 29 || (day == 29 && !isleap)) {
                return false;
            }

        }

        return true;

    }

    /**
     * Method isLastDayOfMonth.
     * Returns true, if day in given date is equal to end of the month.
     *
     * @param date
     * @return boolean
     */
    public static boolean isLastDayOfMonth(String date) {
        StringTokenizer st = new StringTokenizer(date, "/");
        int mm = Integer.parseInt(st.nextToken());
        int dd = Integer.parseInt(st.nextToken());
        int yyyy = Integer.parseInt(st.nextToken());

        int lastDay = 0;
        if (mm == 4 || mm == 6 || mm == 9 || mm == 11) {
            lastDay = 30;
        }

        if (mm == 1
                || mm == 3
                || mm == 5
                || mm == 7
                || mm == 8
                || mm == 10
                || mm == 12) {
            lastDay = 31;
        }

        if (mm == 2) {
            // check for february 29th
            if (yyyy % 4 == 0 && (yyyy % 100 != 0 || yyyy % 400 == 0))
                lastDay = 29;
            else
                lastDay = 28;

        }
        if (dd != lastDay) {
            return false;
        }

        return true;
    }

    public static boolean isFirstDayOfMonth(String date) {
        StringTokenizer st = new StringTokenizer(date, "/");
        int mm = Integer.parseInt(st.nextToken());
        int dd = Integer.parseInt(st.nextToken());
        int yyyy = Integer.parseInt(st.nextToken());

        if (dd != 1) {
            return false;
        }

        return true;
    }

    /**
     * Method compareDateByGT.
     * Returns true, if begin date is less than or equal to end date.
     *
     * @param startDate
     * @param endDate
     * @return boolean
     * @throws Exception
     */
    public static boolean compareDateByGT(String startDate, String endDate)
            throws Exception {
        //enddate should be greater than startdate
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Date date1 = sdf.parse(startDate);
        Date date2 = sdf.parse(endDate);
        if (date1.compareTo(date2) > 0) {
            return false;
        }
        return true;
    }

    /**
     * Method compareDateByEQ.
     * Returns true, if the begin date and end dates are equal.
     *
     * @param startDate
     * @param endDate
     * @return boolean
     * @throws Exception
     */
    public static boolean compareDateByEQ(String startDate, String endDate)
            throws Exception {
        //enddate should be greater than startdate
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Date date1 = sdf.parse(startDate);
        Date date2 = sdf.parse(endDate);
        if (date1.compareTo(date2) != 0) {
            return false;
        }
        return true;
    }

    public static String getDateInMMDDYYYY() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        return sdf.format(new Date());
    }


    /**
     *  This method returns the currentdate in String format
     *  @return String
     */
    /*
    public static String getCurrentDate()
    {
        String   strDate  = null;
        Calendar objCalen = Calendar.getInstance();

        objCalen.setTime(new java.util.Date());

        int intYear        = objCalen.get(Calendar.YEAR);
        int intMon         = objCalen.get(Calendar.MONTH);
        int intDate        = objCalen.get(Calendar.DATE);

        strDate = intDate + "-" + intMon + "-" + intYear;

        return strDate;
    }*/

    public static String getDateInCCYYMMDD(String date) throws Exception {
        StringTokenizer st = new StringTokenizer(date, "/");
        String mm = st.nextToken();
        String dd = st.nextToken();
        String yyyy = st.nextToken();
        return (yyyy + mm + dd);
    }

    static public Date strToDate(DateFormat dateFormat, String str) {
        if (str == null || str.trim().length() == 0)
            return null;
        try {
            Date date = dateFormat.parse(str);
            return date;
        } catch (java.text.ParseException e) {
            return null;
        }
    }

    public static String getNextBusinessDate() {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new java.util.Date());
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
            cal.add(Calendar.DATE, 3);
        } else {
            cal.add(Calendar.DATE, 1);
        }
        String returnDate = dateFormat.format(cal.getTime());
        return dateFormat.format(cal.getTime());
    }

    /**
     * Method isValidFieldLength.
     * Returns true, if the input field length is within the specified range.
     *
     * @param value
     * @param startValue
     * @param endValue
     * @return boolean
     */
    public static boolean isValidFieldLength(
            String value,
            int startValue,
            int endValue) {
        if (value.length() < startValue || value.length() > endValue) {
            return false;
        }
        return true;
    }

    /**
     * Checks if date conforms to format specified
     *
     * @param date   String
     * @param format String
     * @return boolean -- true if date format is valid
     */
    public static boolean validateDate(String date, String format) {
        boolean result = false;
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        try {
            formatter.parse(date);
            result = true;
        } catch (java.text.ParseException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    public static Calendar addOrReduceDate(Calendar cc, int unit, int howLong) {
        //Calendar rightNow = Calendar.getInstance();
        //form.getBundle().setStartDate(rightNow.getTime());
        cc.add(unit, howLong);
        return cc;
        //form.getBundle().setEndDate(rightNow.getTime());

    }

    /**
     * is date1+ days < date2?
     *
     * @param date1
     * @param date2
     * @param days
     */
    public static boolean before(Date date1, Date date2, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        calendar.add(Calendar.DAY_OF_YEAR, days);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        return calendar.before(calendar2);
    }

    public static int getAge(Date date, TimeZone tz) {
        return getAge(date, new GregorianCalendar(tz));
    }

    public static int getAge(Date date, Calendar today) {
        Calendar birthday = new GregorianCalendar();
        birthday.setTime(date);

        int yearDiff = today.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);

        if (today.get(Calendar.MONTH) < birthday.get(Calendar.MONTH)) {
            yearDiff--;
        } else if (
                today.get(Calendar.MONTH) == birthday.get(Calendar.MONTH)
                        && today.get(Calendar.DATE) < birthday.get(Calendar.DATE)) {

            yearDiff--;
        }

        return yearDiff;
    }

    public static int getDaysInMonth(Calendar cal) {
        return getDaysInMonth(
                cal.get(Calendar.MONTH) + 1,
                cal.get(Calendar.YEAR));
    }

    public static int getDaysInMonth(int month, int year) {
        if ((month == 1)
                || (month == 3)
                || (month == 5)
                || (month == 7)
                || (month == 8)
                || (month == 10)
                || (month == 12)) {

            return 31;
        } else if (
                (month == 4) || (month == 6) || (month == 9) || (month == 11)) {

            return 30;
        } else {
            if (((year % 4) == 0)
                    && ((year % 100) != 0)
                    || ((year % 400) == 0)) {

                return 29;
            } else {
                return 28;
            }
        }
    }

    public static int getLastDayOfWeek(Calendar cal) {
        int firstDayOfWeek = cal.getFirstDayOfWeek();

        if (firstDayOfWeek == Calendar.SUNDAY) {
            return Calendar.SATURDAY;
        } else if (firstDayOfWeek == Calendar.MONDAY) {
            return Calendar.SUNDAY;
        } else if (firstDayOfWeek == Calendar.TUESDAY) {
            return Calendar.MONDAY;
        } else if (firstDayOfWeek == Calendar.WEDNESDAY) {
            return Calendar.TUESDAY;
        } else if (firstDayOfWeek == Calendar.THURSDAY) {
            return Calendar.WEDNESDAY;
        } else if (firstDayOfWeek == Calendar.FRIDAY) {
            return Calendar.THURSDAY;
        }

        return Calendar.FRIDAY;
    }

    public static int[] getMonthIds() {
        return MONTH_IDS;
    }

    public static boolean isAfter(
            int month1,
            int day1,
            int year1,
            int hour1,
            int minute1,
            int amPm1,
            int month2,
            int day2,
            int year2,
            int hour2,
            int minute2,
            int amPm2,
            TimeZone timeZone,
            Locale locale) {

        Calendar cal1 = new GregorianCalendar(timeZone, locale);
        cal1.set(Calendar.MONTH, month1);
        cal1.set(Calendar.DATE, day1);
        cal1.set(Calendar.YEAR, year1);
        cal1.set(Calendar.HOUR, hour1);
        cal1.set(Calendar.MINUTE, minute1);
        cal1.set(Calendar.AM_PM, amPm1);

        Calendar cal2 = new GregorianCalendar(timeZone, locale);
        cal2.set(Calendar.MONTH, month2);
        cal2.set(Calendar.DATE, day2);
        cal2.set(Calendar.YEAR, year2);
        cal2.set(Calendar.HOUR, hour2);
        cal2.set(Calendar.MINUTE, minute2);
        cal2.set(Calendar.AM_PM, amPm2);

        return cal1.after(cal2);
    }

    public static boolean isBroadcastDate(int month, int day, int year) {
        if (!isDate(month, day, year)) {
            return false;
        }

        Calendar cal1 = new GregorianCalendar();
        cal1.setFirstDayOfWeek(Calendar.MONDAY);
        cal1.set(Calendar.MONTH, month);
        cal1.set(Calendar.DATE, day);
        cal1.set(Calendar.YEAR, year);

        Calendar cal2 = new GregorianCalendar();
        cal2.setFirstDayOfWeek(Calendar.MONDAY);
        cal2.set(Calendar.MONTH, month + 1);
        cal2.set(Calendar.DATE, 1);
        cal2.set(Calendar.YEAR, year);

        if ((cal2.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY)
                && (cal2.get(Calendar.WEEK_OF_YEAR)
                == cal1.get(Calendar.WEEK_OF_YEAR))) {
            return false;
        }

        return true;
    }

    public static boolean isDate(int month, int day, int year) {
        return isGregorianDate(month, day, year);
    }

    public static boolean isFuture(
            int month,
            int day,
            int year,
            TimeZone timeZone,
            Locale locale) {

        Calendar curCal = new GregorianCalendar(timeZone, locale);
        curCal.set(Calendar.HOUR_OF_DAY, 0);
        curCal.set(Calendar.MINUTE, 0);

        Calendar cal = new GregorianCalendar(timeZone, locale);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DATE, day);
        cal.set(Calendar.YEAR, year);

        return cal.after(curCal);
    }

    public static boolean isFuture(
            int month,
            int day,
            int year,
            int hour,
            int minute,
            int amPm,
            TimeZone timeZone,
            Locale locale) {

        Calendar curCal = new GregorianCalendar(timeZone, locale);

        Calendar cal = new GregorianCalendar(timeZone, locale);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DATE, day);
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.HOUR, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.AM_PM, amPm);

        return cal.after(curCal);
    }

    public static boolean isGregorianDate(int month, int day, int year) {
        if ((month < 0) || (month > 11)) {
            return false;
        }

        int[] months = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if (month == 1) {
            int febMax = 28;

            if (((year % 100) == 0) && ((year % 400) != 0)) {
                febMax = 29;
            }

            if ((day < 0) || (day > febMax)) {
                return false;
            }
        } else if ((day < 0) || (day > months[month])) {
            return false;
        }

        return true;
    }

    public static Calendar roundByMinutes(Calendar cal) {
        int minutes = cal.get(Calendar.MINUTE);

        int delta = 0;

        if (minutes < 15) {
            delta = 15 - minutes;
        } else {
            delta = 15 - (minutes % 15);
        }

        if (delta == 15) {
            delta = 0;
        }

        cal.add(Calendar.MINUTE, delta);

        return cal;
    }

    private final static void appendMonthNamesXML(StringBuffer buffer, String value) {
        buffer.append("<").append(MONTH_NAMES_NODE).append(">");

        buffer.append(value);

        buffer.append("</").append(MONTH_NAMES_NODE).append(">");
    }

    private final static void appendAcceptableFormatsXML(StringBuffer buffer, String value) {
        buffer.append("<").append(MONTH_ACPT_FORMATS).append(">");
        buffer.append(value);
        buffer.append("</").append(MONTH_ACPT_FORMATS).append(">");
    }

    private final static void appendDefaultFormatXML(StringBuffer buffer, String value) {
        buffer.append("<").append(MONTH_DEF_FORMAT).append(">");
        buffer.append(value);
        buffer.append("</").append(MONTH_DEF_FORMAT).append(">");
    }

    /**
     * This method maps the Calendar int representation of days of the week
     * into a String.
     *
     * @param day !
     * @return !
     */
    public static String getDay(int day) {
        if (day == Calendar.MONDAY)
            return "Monday";
        else if (day == Calendar.TUESDAY)
            return "Tuesday";
        else if (day == Calendar.WEDNESDAY)
            return "Wednesday";
        else if (day == Calendar.THURSDAY)
            return "Thursday";
        else if (day == Calendar.FRIDAY)
            return "Friday";
        else if (day == Calendar.SATURDAY)
            return "Saturday";
        else if (day == Calendar.SUNDAY)
            return "Sunday";

        return null;
    }

    /**
     * Get this day e.g. 1=1st of moth using default timezone
     *
     * @return dd 1=first day of month.
     */
    public static int thisDayOfMonth() {
        return new GregorianCalendar().get(GregorianCalendar.DAY_OF_MONTH);
    }

    /**
     * Get this month e.g. 1=January using default timezone
     *
     * @return mm 1=Jan
     */
    public static int thisMonth() {
        return new GregorianCalendar().get(GregorianCalendar.MONTH) + 1;
    }

    /**
     * Get this year e.g. 2006 using default timezone
     *
     * @return yyyy
     */
    public static int thisYear() {
        return new GregorianCalendar().get(GregorianCalendar.YEAR);
    }

    public static String convertMonthToNumber(String month) {
        if (month.equalsIgnoreCase("JAN"))
            month = "01";
        else if (month.equalsIgnoreCase("FEB"))
            month = "02";
        else if (month.equalsIgnoreCase("MAR"))
            month = "03";
        else if (month.equalsIgnoreCase("APR"))
            month = "04";
        else if (month.equalsIgnoreCase("MAY"))
            month = "05";
        else if (month.equalsIgnoreCase("JUN"))
            month = "06";
        else if (month.equalsIgnoreCase("JUL"))
            month = "07";
        else if (month.equalsIgnoreCase("AUG"))
            month = "08";
        else if (month.equalsIgnoreCase("SEP"))
            month = "09";
        else if (month.equalsIgnoreCase("OCT"))
            month = "10";
        else if (month.equalsIgnoreCase("NOV"))
            month = "11";
        else if (month.equalsIgnoreCase("DEC"))
            month = "12";

        return month;
    }

    private void displayArray(String[] array) {
        logger.debug("the array contains ....");
        for (int i = 0; i < array.length; i++)
            logger.debug(" " + array[i]);
    }

    public String[] getListOfSundays(String pCurrentMonth, int pCurrentYear) {
        String[] lSundayArray = new String[5];
        GregorianCalendar lCal = new GregorianCalendar();
        lCal.set(Calendar.MONTH, getMonthNumber(pCurrentMonth));
        lCal.set(Calendar.YEAR, pCurrentYear);
        int counter = 0;
        for (int i = lCal.getMinimum(GregorianCalendar.DAY_OF_MONTH);
             i <= lCal.getMaximum(GregorianCalendar.DAY_OF_MONTH);
             i++) {
            lCal.set(Calendar.DATE, i);
            if (lCal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                lSundayArray[counter++] =
                        new Integer(lCal.get(Calendar.DATE)).toString();
                // log.debug("The Date is :::  " + lCal.get(Calendar.DATE));
            }
        }

        return lSundayArray;
    }

    public String[] getListOfSaturdays(String pCurrentMonth, int pCurrentYear) {

        String[] lSaturdayArray = new String[5];
        GregorianCalendar lCal = new GregorianCalendar();
        lCal.set(Calendar.MONTH, getMonthNumber(pCurrentMonth));
        lCal.set(Calendar.YEAR, pCurrentYear);
        int counter = 0;
        for (int i = lCal.getMinimum(GregorianCalendar.DAY_OF_MONTH);
             i <= lCal.getMaximum(GregorianCalendar.DAY_OF_MONTH);
             i++) {
            lCal.set(Calendar.DATE, i);
            if (lCal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                lSaturdayArray[counter++] =
                        new Integer(lCal.get(Calendar.DATE)).toString();
                // log.debug("The Date is :::  " + lCal.get(Calendar.DATE));
            }
        }

        return lSaturdayArray;
    }

    public String[] getMonthArray(String pCurrentMonth, int pNoOfMonths) {

        int lMonthArraySize = pNoOfMonths * 2 + 1;
        String[] lMonthArray = new String[lMonthArraySize];
        GregorianCalendar lCal = new GregorianCalendar();
        lCal.set(Calendar.MONTH, getMonthNumber(pCurrentMonth));

        for (int i = 0; i <= lMonthArraySize - 1; i++) {
            lMonthArray[i] =
                    getMonthName(lCal.get(Calendar.MONTH) - pNoOfMonths + i + 1);
            //log.debug("" + lMonthArray[i]);
        }

        return lMonthArray;
    }

    public String[] getYearArray(String pCurrentYear, int pNoOfYears) {
        int lYearArraySize = pNoOfYears * 2 + 1;
        String[] lYearArray = new String[lYearArraySize];
        GregorianCalendar lCal = new GregorianCalendar();

        lCal.set(Calendar.YEAR, Integer.parseInt(pCurrentYear));

        for (int i = 0; i <= lYearArraySize - 1; i++) {
            lYearArray[i] = "" + (lCal.get(Calendar.YEAR) - pNoOfYears + i + 1);

            logger.debug("" + lYearArray[i]);
        }
        return lYearArray;
    }

    public String[] getMonthByNumArray(String[] pMonthNameArray) {

        String[] lMonthArrayinNumbers = new String[pMonthNameArray.length];

        for (int i = 0; i < pMonthNameArray.length; i++)
            lMonthArrayinNumbers[i] =
                    ("" + (getMonthNumber(pMonthNameArray[i]) + 1)).trim();

        return lMonthArrayinNumbers;
    }

    public int getMonthNumber(String pMonthName) {
        if (pMonthName.equalsIgnoreCase("JANUARY"))
            return 0;
        else if (pMonthName.equalsIgnoreCase("FEBRUARY"))
            return 1;
        else if (pMonthName.equalsIgnoreCase("MARCH"))
            return 2;
        else if (pMonthName.equalsIgnoreCase("APRIL"))
            return 3;
        else if (pMonthName.equalsIgnoreCase("MAY"))
            return 4;
        else if (pMonthName.equalsIgnoreCase("JUNE"))
            return 5;
        else if (pMonthName.equalsIgnoreCase("JULY"))
            return 6;
        else if (pMonthName.equalsIgnoreCase("AUGUST"))
            return 7;
        else if (pMonthName.equalsIgnoreCase("SEPTEMBER"))
            return 8;
        else if (pMonthName.equalsIgnoreCase("OCTOBER"))
            return 9;
        else if (pMonthName.equalsIgnoreCase("NOVEMBER"))
            return 10;
        else if (pMonthName.equalsIgnoreCase("DECEMBER"))
            return 11;
        return 0;
    }

    public String getMonthName(int pMonthNum) {
        String lMonthName = "";
        int lMonthNumber = pMonthNum;
        if (pMonthNum <= 0)
            lMonthNumber = 12 + pMonthNum;
        else if (pMonthNum > 12)
            lMonthNumber = pMonthNum - 12;
        switch (lMonthNumber) {
            case 1:
                lMonthName = "JANUARY";
                break;

            case 2:
                lMonthName = "FEBRUARY";
                break;

            case 3:
                lMonthName = "MARCH";
                break;

            case 4:
                lMonthName = "APRIL";
                break;

            case 5:
                lMonthName = "MAY";
                break;

            case 6:
                lMonthName = "JUNE";
                break;

            case 7:
                lMonthName = "JULY";
                break;

            case 8:
                lMonthName = "AUGUST";
                break;

            case 9:
                lMonthName = "SEPTEMBER";
                break;

            case 10:
                lMonthName = "OCTOBER";
                break;

            case 11:
                lMonthName = "NOVEMBER";
                break;

            case 12:
                lMonthName = "DECEMBER";
                break;

        }

        return lMonthName;
    }

    public String getTMonthName(int pMonthNum) {
        String lMonthName = "";
        int lMonthNumber = pMonthNum;
        if (pMonthNum <= 0)
            lMonthNumber = 12 + pMonthNum;
        else if (pMonthNum > 12)
            lMonthNumber = pMonthNum - 12;
        switch (lMonthNumber) {
            case 1:
                lMonthName = "JAN";
                break;

            case 2:
                lMonthName = "FEB";
                break;

            case 3:
                lMonthName = "MAR";
                break;

            case 4:
                lMonthName = "APR";
                break;

            case 5:
                lMonthName = "MAY";
                break;

            case 6:
                lMonthName = "JUN";
                break;

            case 7:
                lMonthName = "JUL";
                break;

            case 8:
                lMonthName = "AUG";
                break;

            case 9:
                lMonthName = "SEP";
                break;

            case 10:
                lMonthName = "OCT";
                break;

            case 11:
                lMonthName = "NOV";
                break;

            case 12:
                lMonthName = "DEC";
                break;

        }

        return lMonthName;
    }

    public int getNumOfDaysInMonth(int pMonth, int pYear) {
        int lNumofDays = 0;

        switch (pMonth) {
            case 1:
                lNumofDays = 31;
                break;

            case 2:
                lNumofDays = 28;

                GregorianCalendar lCal = new GregorianCalendar();

                if (lCal.isLeapYear(pYear))
                    lNumofDays = 29;

                break;

            case 3:
                lNumofDays = 31;
                break;

            case 4:
                lNumofDays = 30;
                break;

            case 5:
                lNumofDays = 31;
                break;

            case 6:
                lNumofDays = 30;
                break;

            case 7:
                lNumofDays = 31;
                break;

            case 8:
                lNumofDays = 31;
                break;

            case 9:
                lNumofDays = 30;
                break;

            case 10:
                lNumofDays = 31;
                break;

            case 11:
                lNumofDays = 30;
                break;

            case 12:
                lNumofDays = 31;
                break;

        }

        return lNumofDays;
    }

    public boolean isDay(String pDate, String pDay, String pMonthName, String pYear) {

        boolean status = false;
        String days[] = null;

        if (pDay.equalsIgnoreCase("saturday")) {
            days = getListOfSaturdays(pMonthName, Integer.parseInt(pYear));
            // log.debug("saturdays ...");
            //displayArray(days);

        } else if (pDay.equalsIgnoreCase("sunday")) {
            //log.debug("sundays ...");
            days = getListOfSundays(pMonthName, Integer.parseInt(pYear));
            //displayArray(days);

        }

        for (int i = 0; i < days.length; i++) {
            if (days[i] != null && pDate != null) {

                if ((pDate.trim()).equals(days[i].trim())) {
                    status = true;
                    break;
                }
            }
        }


        return status;
    }


} // END OF CLASS
