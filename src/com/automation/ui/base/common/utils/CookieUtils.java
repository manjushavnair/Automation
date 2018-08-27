package com.automation.ui.base.common.utils;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.util.Date;
import java.util.Set;

public class CookieUtils {


    private static Log log = LogFactory.getLog(CookieUtils.class);

    private CookieUtils() {
    }

    public static void deleteAllCookies(WebDriver driver) {
        driver.manage().deleteAllCookies();

    }

    /**
     * Clears the given cookie by setting the maxAge to 0 and the value to ""
     *
     * @param cookieName The name of the cookie (can't be null)
     * @throws IllegalArgumentException Description of the Exception
     */
    public static void clearCookie(WebDriver driver,
                                   String cookieName) throws IllegalArgumentException {

        driver.manage().deleteCookieNamed(cookieName);
    }

    public static void deleteCookiesOneByOne(WebDriver driver) {
        Set<Cookie> allCookies = driver.manage().getCookies();
        for (Cookie cookie : allCookies) {
            driver.manage().deleteCookieNamed(cookie.getName());
        }


    }


    /**
     * Clears the given cookie by setting the maxAge to 0 and the value to ""
     *
     * @param cookieName The name of the cookie (can't be null)
     * @param valueName  The name of the cookie (can't be null)
     * @throws IllegalArgumentException Description of the Exception
     */
    public static void clearCookie(WebDriver driver,
                                   String cookieName, String valueName) throws IllegalArgumentException {

        driver.manage().deleteCookie(new Cookie(cookieName, valueName));
    }


    /**
     * Creates and adds a new cookie to the response. Sets the domain, path, maxAge
     * attributes to defaults
     *
     * @param name  The name of the cookie (can't be null)
     * @param value The value of the cookie (can't be null)
     * @throws IllegalArgumentException Description of the Exception
     * @returns The newly created cookie
     */
    public static Cookie addCookie(
            String name,
            String value) throws IllegalArgumentException {

        return addCookie(name, value, new Date(-1));
    }


    /**
     * Creates and adds a new cookie to the response. Sets the domain/path to defaults
     *
     * @param name   The name of the cookie (can't be null)
     * @param value  The value of the cookie (can't be null)
     * @param maxAge The number of seonds to expire the cookie.  A zero causes
     *               the cookie to be deleted.  A negative indicates this is a session
     *               cookie.
     * @throws IllegalArgumentException Description of the Exception
     * @returns The newly created cookie
     */
    public static Cookie addCookie(
            String name,
            String value,
            Date maxAge) throws IllegalArgumentException {
        return addCookie(name, value, null, null, maxAge);

    }


    /**
     * Creates and adds a new cookie to the response. Sets the non-null
     * attributes for domain/path
     *
     * @param name   The name of the cookie (can't be null)
     * @param value  The value of the cookie (can't be null)
     * @param domain The domain to use for the cookie OR null
     * @param path   The path to use for the cookie OR null
     * @param maxAge The number of seonds to expire the cookie.  A zero causes
     *               the cookie to be deleted.  A negative indicates this is a session
     *               cookie.
     * @throws IllegalArgumentException Description of the Exception
     * @returns The newly created cookie
     */
    public static Cookie addCookie(
            String name,
            String value,
            String domain,
            String path,
            Date maxAge) throws IllegalArgumentException {

        if (name == null || name.equals("") || value == null || value.equals("")) {

            throw new IllegalArgumentException("Name or value cannot be null for new Cookie ");
        }

        Cookie cookie = new Cookie(name, value, domain, path, maxAge);
        return cookie;
    }

    /**
     * Retrieves the named cookie
     *
     * @param cookieName Name of the cookie to search for
     * @return Cookie if it exists in the request; null otherwise.
     */
    public static Cookie getCookie(WebDriver driver, String cookieName) {

        if (cookieName == null)
            throw new IllegalArgumentException("  cookie name was null!");

        Set cookieSet = driver.manage().getCookies();
        Cookie ck = null;
        if (cookieSet != null) {
            while (cookieSet.iterator().hasNext()) {
                ck = (Cookie) cookieSet.iterator().next();
                if (ck.getName().equalsIgnoreCase(cookieName))
                    return ck;
            }

        }
        return null;
    }

    /**
     * Retrieves the named cookie's value
     *
     * @param cookieName Name of the cookie to search for
     * @return Cookie if it exists in the request; null otherwise.
     */
    public static String getCookieValue(WebDriver driver, String cookieName) {
        Cookie cookie = getCookie(driver, cookieName);
        return cookie == null ? null : cookie.getValue();
    }


}
