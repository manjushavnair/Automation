package com.automation.ui.base.common.utils.i18n;


import java.util.HashMap;
import java.util.Locale;


public class LocaleUtil {

    /*
    ** Swiss operation area codes, and international prefixes.
    */
    public static final String SWISSCOM_AREA = "079";
    public static final String SUNRISE_AREA = "076";
    public static final String ORANGE_AREA = "078";
    public static final int TELEPHONE_ADDRESS_SIZE = 10;
    public static final String INTERNATIONAL_PREFIX_1 = "+";
    public static final String INTERNATIONAL_PREFIX_2 = "00";
    public static final String SWISS_COUNTRY_CODE = "41";
    /**
     * Contains the mapping of langauge vs. country.
     */
    private static HashMap langCountryMap_ = new HashMap();

    /**
     * Populates a mapping of two-letter language and country names.
     */
    static {
        langCountryMap_.put("en", "US");
        langCountryMap_.put("ar", "AE");
        langCountryMap_.put("be", "BY");
        langCountryMap_.put("bg", "BG");
        langCountryMap_.put("ca", "ES");
        langCountryMap_.put("cs", "CZ");
        langCountryMap_.put("da", "DK");
        langCountryMap_.put("de", "DE");
        langCountryMap_.put("el", "GR");
        langCountryMap_.put("es", "ES");
        langCountryMap_.put("et", "EE");
        langCountryMap_.put("fi", "FI");
        langCountryMap_.put("fr", "FR");
        langCountryMap_.put("hr", "HR");
        langCountryMap_.put("hu", "HU");
        langCountryMap_.put("is", "IS");
        langCountryMap_.put("it", "IT");
        langCountryMap_.put("iw", "IL");
        langCountryMap_.put("ja", "JA");
        langCountryMap_.put("ko", "KO");
        langCountryMap_.put("lt", "LT");
        langCountryMap_.put("lv", "LV");
        langCountryMap_.put("mk", "MK");
        langCountryMap_.put("nl", "NL");
        langCountryMap_.put("no", "NO");
        langCountryMap_.put("pl", "PL");
        langCountryMap_.put("pt", "PT");
        langCountryMap_.put("ro", "RO");
        langCountryMap_.put("sh", "YU");
        langCountryMap_.put("sk", "SK");
        langCountryMap_.put("sl", "SL");
        langCountryMap_.put("sq", "AL");
        langCountryMap_.put("sr", "YU");
        langCountryMap_.put("sv", "SE");
        langCountryMap_.put("th", "TH");
        langCountryMap_.put("tr", "TR");
        langCountryMap_.put("uk", "UA");
        langCountryMap_.put("zh", "CN");
    }

    /**
     * Returns the default country code for a given language code. For example,
     * default country code for "en" is "US", and default country code for "fr" is "FR".
     *
     * @param languageCode The 2-letter language code
     * @return The 2-letter country code
     */

    static public String getDefaultCountryCode(String languageCode) {
        if (langCountryMap_.containsKey(languageCode)) {
            return (String) langCountryMap_.get(languageCode);
        }
        return "";
    }

    /**
     * Get the Locale specified in the session or return a default locale.
     *
     * @param language
     * @return Locale
     */
    public static Locale getLocaleFromLanguage(String language) {
        Locale locale = Locale.US;

        if (language.equalsIgnoreCase("en"))
            locale = Locale.US;
        else if (language.equals("jp"))
            locale = Locale.JAPAN;
        return locale;
    }

} // END OF CLASS
