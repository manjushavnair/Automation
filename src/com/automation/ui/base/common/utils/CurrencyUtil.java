package com.automation.ui.base.common.utils;

import org.apache.log4j.Logger;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;


public class CurrencyUtil {

    /**
     * This is the static constant stores Default pattern for Currency.
     */
    private static final String DEFAULT_CURRENCY_PATTERN = "$ ###,###.00";

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

    private static Logger logger = Logger
            .getLogger(CurrencyUtil.class);


    /**
     * This method is used for formatting the given amount to string
     * using given currency pattern.
     *
     * @param a_amount  The amount to be converted to the string by using the
     *                  given currency pattern.
     * @param a_pattern The given pattern for converting the amount to string.
     * @return String     The converted string for the amount
     */
    public static final String formatCurrency(double a_amount, String a_pattern) {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        DecimalFormat df = (DecimalFormat) nf;
        df.applyPattern(a_pattern);
        df.setMinimumFractionDigits(MINIMUM_FRACTION_DIGITS);
        df.setMaximumFractionDigits(MAXIMUM_FRACTION_DIGITS);
        df.setDecimalSeparatorAlwaysShown(true);
        return df.format(a_amount);
    }


    /**
     * Method formatCurrency.
     *
     * @param amountString
     * @return String
     */
    public static String formatCurrency(String amountString) {
        try {
            double amount = Double.parseDouble(amountString);
            return formatCurrency(amount);
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    /**
     * Method formatCurrency.
     *
     * @param amountString
     * @param locale
     * @return String
     */
    public static String formatCurrency(String amountString, Locale locale) {
        try {
            double amount = Double.parseDouble(amountString);
            return formatCurrency(amount, locale);
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    /**
     * Method formatCurrency.
     *
     * @param amount
     * @return String
     */
    public static String formatCurrency(double amount) {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        DecimalFormat df = (DecimalFormat) nf;
        df.setMinimumFractionDigits(2);
        df.setMaximumFractionDigits(2);
        df.setDecimalSeparatorAlwaysShown(true);
        String pattern = "$###,###.00";
        df.applyPattern(pattern);
        return df.format(amount);
    }

    /**
     * Method formatPlainCurrency.
     *
     * @param amount
     * @return String
     */
    public static String formatPlainCurrency(double amount) {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        DecimalFormat df = (DecimalFormat) nf;
        df.setMinimumFractionDigits(2);
        df.setMaximumFractionDigits(2);
        df.setDecimalSeparatorAlwaysShown(true);
        String pattern = "###,###";
        df.applyPattern(pattern);
        return df.format(amount);
    }

    /**
     * Method formatCurrency.
     *
     * @param amount
     * @param locale
     * @return String
     */
    public static String formatCurrency(double amount, Locale locale) {
        NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
        return nf.format(amount);
    }


} // end of class CurrencyUtil
