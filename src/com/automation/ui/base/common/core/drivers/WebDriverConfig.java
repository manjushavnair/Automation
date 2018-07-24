package com.automation.ui.base.common.core.drivers;

import com.automation.ui.connected.common.prpreader.PropertyReader;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;


/**
 * @author manjusha saju
 */
public class WebDriverConfig {
    PropertyReader prpr;

    public WebDriverConfig(PropertyReader prp) {
        prpr = prp;
    }

    public String[] getExtensions() {
        String exts = prpr.getValue("extensions");

        if (StringUtils.isEmpty(exts)) {
            return new String[]{};
        }

        ArrayList<String> res = new ArrayList<String>();
        for (String ext : exts.replace("[", "").replace("]", "").split(",")) {
            res.add(ext.trim());
        }

        return res.toArray(new String[res.size()]);
    }

    public String getBrowserName() {

        return prpr.getValue("BROWSER_TYPE");
    }

    public int getImplicitlyWait() {
        return prpr.getInt("IMPLICITLY_WAIT");
    }

    public int getExplicitWait() {

        return prpr.getInt("EXPLICIT_WAIT");
    }

    public int getDomMaxScriptRunTime() {

        return prpr.getInt("DOM.MAX_SCRIPT_RUN_TIME");
    }

    public String getMODE() {
        return prpr.getValue("MODE");
    }

    public String getAPPLICATION_URL() {
        return prpr.getValue("APPLICATION_URL");
    }

    public String getLOGIN_USERNAME() {
        return prpr.getValue("LOGIN_USERNAME");
    }

    public String getLOGIN_PASSWORD() {
        return prpr.getValue("LOGIN_PASSWORD");
    }

    public String getORGANIZATION_NAME() {
        return prpr.getValue("ORGANIZATION_NAME");
    }

    public String getIE_DRIVER_SERVER_PATH() {
        return prpr.getValue("IE_DRIVER_SERVER_PATH");
    }

    public String getCHROME_DRIVER_SERVER_PATH() {
        return prpr.getValue("CHROME_DRIVER_SERVER_PATH");
    }

    public int getPAGE_LOAD_TIMEOUT_SECONDS() {
        return prpr.getInt("PAGE_LOAD_TIMEOUT_SECONDS");
    }

    public int getIMPLICIT_TIMEOUT_SECONDS() {
        return prpr.getInt("IMPLICIT_TIMEOUT_SECONDS");
    }

    public int getAJAX_TIMEOUT_SECONDS() {
        return prpr.getInt("AJAX_TIMEOUT_SECONDS");
    }

    public int getSCRIPT_TIMEOUT_SECONDS() {
        return prpr.getInt("SCRIPT_TIMEOUT_SECONDS");
    }

    public String getWINDOW_STATE() {
        return prpr.getValue("WINDOW_STATE");
    }

    public int getMOUSE_RELOCATE_LEFT_POSITION() {
        return prpr.getInt("MOUSE_RELOCATE_LEFT_POSITION");
    }

    public int getMOUSE_RELOCATE_TOP_POSITION() {
        return prpr.getInt("MOUSE_RELOCATE_TOP_POSITION");
    }

    public int getRETRYCOUNT() {
        return prpr.getInt("RETRYCOUNT");
    }

    public String getREMOTE_BROWSER_HOSTNAME() {
        return prpr.getValue("REMOTE_BROWSER_HOSTNAME");
    }

    public int getREMOTE_BROWSER_PORT() {
        return prpr.getInt("REMOTE_BROWSER_PORT");
    }

    public String getSELENIUM_WEBDRIVER_CAPABILITIES() {
        return prpr.getValue("SELENIUM_WEBDRIVER_CAPABILITIES");
    }


    public boolean isPROXY() {
        return prpr.getBoolean("PROXY");
    }

    public String getSSLPROXY_ADDR() {
        return prpr.getValue("SSLPROXY_ADDR");
    }

    public String getHTTPPROXY_ADDR() {
        return prpr.getValue("HTTPPROXY_ADDR");
    }

    public String getSSLPROXY_PORT() {
        return prpr.getValue("SSLPROXY_PORT");
    }

    public String getHTTPPROXY_PORT() {
        return prpr.getValue("HTTPPROXY_PORT");
    }


}