package com.automation.ui.base.common.utils;

/* java specific imports */

import org.apache.log4j.Logger;

import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParseException;

/**
 * NumberUtilities class for miscallaneous functions
 *
 * @author
 */
public final class NumberUtil {

    /**
     * This is the static constant stores Default pattern for Currency.
     */
    private static final String DEFAULT_QTY_PATTERN = "###,###,###,###";
    /**
     * This is the static constant that stores Default pattern for Number.
     */
    private static final String DEFAULT_NUMBER_PATTERN = "###,###.00";
    /**
     * This is the static constant that stores minimum fraction digits
     */
    private static final int MINIMUM_FRACTION_DIGITS = 2;
    /**
     * This is the static constant that stores maximum fraction digits
     */
    private static final int MAXIMUM_FRACTION_DIGITS = 2;
    public static FieldPosition objFieldPosition =
            new FieldPosition(NumberFormat.FRACTION_FIELD);
    public static DecimalFormat objDecimalFormat =
            new DecimalFormat("########0.00");
    private static Logger logger = Logger
            .getLogger(NumberUtil.class);

    public static double roundFloat(double dbl) {

        double intPortion = Math.floor(dbl);
        // Get the unrounded decimal portion
        double unroundedDecimalPortion = (dbl - intPortion);
        double roundedDecimalPortion = Math.round(unroundedDecimalPortion * 100);
        // Shift the decimal point back two places to the right
        roundedDecimalPortion = roundedDecimalPortion / 100;
        // Add the int portion and decimal portions back together
        double total = intPortion + roundedDecimalPortion;
        return total;

    }


    public static String padZero(int i, int strlength) {
        String itemNo = String.valueOf(i);
        for (int j = 1; j <= (strlength - itemNo.length()); ) {
            itemNo = "0" + itemNo;
            logger.debug("itemNo:" + itemNo);
        }
        return itemNo;
    }

    public static boolean isNumber(String strValue) {

        char c[] = strValue.toCharArray();

        for (int i = 0; i < c.length; i++) {

            if (c[i] < '0') return false;
            if (c[i] > '9') return false;

        }
        return true;
    }

    public static int getInt(String strValue) {
        if (strValue != null) {
            strValue = strValue.trim();
            if (strValue.length() > 0) {
                return Integer.parseInt(strValue);
            }
        }
        return 0;
    }


    /**
     * This method formats the number to a decimal format
     *
     * @param String The value to be formatted
     * @return returns String
     * @throws Exception
     */
    public static String formatNumber(String strValue) throws Exception {
        Double objDblInputVal = new Double(strValue);
        StringBuffer objSbfInputVal = new StringBuffer();

        if (strValue != null) {
            objSbfInputVal = objDecimalFormat.
                    format(objDblInputVal, objSbfInputVal, objFieldPosition);
            return (objSbfInputVal.toString());
        } else {
            return (" ");
        }
    }


    /**
     * This  method determines if the specified text contains only digits
     *
     * @param strText String
     * @return returns boolean
     */
    public static boolean numericCheck(String strText) {
        int intSize = strText.length();
        boolean blnTemp = false;

        for (int intCount = 0; intCount < intSize; intCount++) {
            blnTemp = Character.isDigit(strText.charAt(intCount));
            if (!blnTemp) {
                return false;
            }
        }
        return true;
    }

    /**
     * check if a string constains only numbers or not.
     */
    public static boolean checkDigits(String msg) {
        boolean retVal = true;
        for (int i = 0; i < msg.length(); i++) {
            if (Character.isDigit(msg.charAt(i)) == false) {
                retVal = false;
                break;
            } // if
        } // for
        return retVal;
    } // checkDigits

    /**
     * This method is used for Formatting the given amount to string
     * using default currency pattern.
     *
     * @param a_amount The amount to be converted to the string by using the
     *                 default currency pattern.
     * @return String     The converted string for the amount.
     */
    public static final String formatQuantity(double a_amount, String a_pattern) {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        DecimalFormat df = (DecimalFormat) nf;
        df.applyPattern(a_pattern);
        df.setMinimumFractionDigits(0);
        df.setMaximumFractionDigits(0);
        df.setDecimalSeparatorAlwaysShown(false);
        return df.format(a_amount);
    }


    /**
     * This method is used for Formatting the given number to string
     * using default currency pattern.
     *
     * @param a_number The number to be converted to the string by using the
     *                 default currency pattern.
     * @return String     The converted string for the number.
     */
    public static final String formatNumber(double a_number) {
        NumberFormat nf = NumberFormat.getInstance();
        DecimalFormat df = (DecimalFormat) nf;
        df.setMinimumFractionDigits(MINIMUM_FRACTION_DIGITS);
        df.setMaximumFractionDigits(MAXIMUM_FRACTION_DIGITS);
        df.setDecimalSeparatorAlwaysShown(true);
        df.applyPattern(DEFAULT_NUMBER_PATTERN);
        return df.format(a_number);
    }

    /**
     * This method is used for Formatting the given number to string
     * using given currency pattern.
     *
     * @param a_number  The number to be converted to the string by using the
     *                  given currency pattern.
     * @param a_pattern The given pattern for converting the number to string.
     * @return String     The converted string for the number.
     */
    public static final String formatNumber(double a_number, String a_pattern) {
        NumberFormat nf = NumberFormat.getInstance();
        DecimalFormat df = (DecimalFormat) nf;
        df.setMinimumFractionDigits(MINIMUM_FRACTION_DIGITS);
        df.setMaximumFractionDigits(MAXIMUM_FRACTION_DIGITS);
        df.setDecimalSeparatorAlwaysShown(true);
        df.applyPattern(a_pattern);
        return df.format(a_number);
    }

    /**
     * This method is used for parsing the given string to number using
     * default currency pattern.
     *
     * @param a_number The string to be parsed to the number by using
     *                 the default currency pattern.
     * @return double    The number for the parsed string
     * @throws ParseException When the string passed as
     *                        argument cannot be parsed.
     */
    public static final double parseNumber(String a_number)
            throws ParseException {
        NumberFormat nf = NumberFormat.getInstance();
        DecimalFormat df = (DecimalFormat) nf;
        df.setMinimumFractionDigits(MINIMUM_FRACTION_DIGITS);
        df.setMaximumFractionDigits(MAXIMUM_FRACTION_DIGITS);
        df.setDecimalSeparatorAlwaysShown(true);
        String pattern = "######.00";
        df.applyPattern(pattern);
        return df.parse(a_number).doubleValue();
    }

    /**
     * This method is used for parsing the given  string to number
     * using given currency pattern.
     *
     * @param a_number  The string to be parsed to the number by using
     *                  the given currency pattern.
     * @param a_pattern The given pattern for parsing the string into
     *                  number.
     * @return double            The number for the parsed string.
     * @exception ParseException When the string passed as
     * argument cannot be parsed.
     */
    public static final double parseNumber(String a_number, String a_pattern)
            throws ParseException {
        NumberFormat nf = NumberFormat.getInstance();
        DecimalFormat df = (DecimalFormat) nf;
        df.setMinimumFractionDigits(MINIMUM_FRACTION_DIGITS);
        df.setMaximumFractionDigits(MAXIMUM_FRACTION_DIGITS);
        df.setDecimalSeparatorAlwaysShown(true);
        df.applyPattern(a_pattern);
        return df.parse(a_number).doubleValue();
    }


    /**
     * Method isNumber.
     * Performs the number validation on a given value and returns true
     * if it has all numbers
     *
     * @param value
     * @return boolean
     */
     /*
	public static boolean isNumber(String value) {
		String validNumbers = "1234567890";
		byte[] count = value.getBytes();
		for (int i = 0; i < count.length; i++) {
			if (validNumbers.indexOf(count[i]) == -1) {
				return false;
			}
		}

		return true;
	}*/
    public static String lpad(int value) {
        String mystring = "000";
        if (value <= 9) {
            mystring = "00" + Integer.toString(value);
        } else if (value <= 99) {
            mystring = "0" + Integer.toString(value);
        } else {
            mystring = Integer.toString(value);
        }
        return mystring;
    }

    static public Float strToFloat(String str) {
        if (str == null || str.trim().length() == 0)
            return null;
        try {
            Float data = new Float(str);
            return data;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    static public Double strToDouble(String str) {
        if (str == null || str.trim().length() == 0)
            return null;
        try {
            Double data = new Double(str.trim());
            return data;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    static public Integer strToInteger(String str) {
        if (str == null || str.trim().length() == 0)
            return null;
        try {
            Integer data = new Integer(str.trim());
            return data;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    static public Long strToLong(String str) {
        if (str == null || str.trim().length() == 0)
            return null;
        try {
            Long data = new Long(str.trim());
            return data;
        } catch (NumberFormatException e) {
            return null;
        }
    }


    /**
     * Caps the max value, ensuring it does not go too high. alias for min.
     *
     * @param v    the value
     * @param high the high bound above which v cannot go.
     * @return the lesser of v and high.
     * @see Math#min(int, int)
     */
    public static int cap(int v, int high) {
        if (v > high) {
            return high;
        } else {
            return v;
        }
    } // end cap

    /**
     * Corrals a value back into safe bounds.
     *
     * @param v    the value
     * @param low  the low bound below which v cannot go.
     * @param high the high bound above which v cannot go.
     * @return low if v &lt; low, high if v &gt; high, but normally just v.
     */
    public static int corral(int v, int low, int high) {
        if (v < low) {
            return low;
        } else if (v > high) {
            return high;
        } else {
            return v;
        }
    } // end corral

    /**
     * Ensures a value does not go too low. alias for max
     *
     * @param v   the value
     * @param low the low bound below which v cannot go.
     * @return the greater of v and low.
     * @see Math#max(int, int)
     */
    public static int hem(int v, int low) {
        if (v < low) {
            return low;
        } else {
            return v;
        }
    } // end hem


} // end of class NumberUtil
