package com.automation.ui.base.common.driverprovider;

import com.automation.ui.base.common.core.UIWebDriver;
import com.automation.ui.base.common.core.configuration.Configuration;
import com.automation.ui.base.common.core.drivers.Browser;
import com.automation.ui.base.common.logging.Log;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class DriverProvider {

    private static final List<UIWebDriver> drivers = new ArrayList<>();
    private static int ACTIVE_BROWSER_INDEX = 0;
    private static Logger logger = Logger.getLogger(DriverProvider.class);


    private DriverProvider() {
    }

    private static void newInstance() {
        drivers.add(Browser.lookup(Configuration.getBrowser()).getInstance());
    }

    private static UIWebDriver getBrowserDriver(int index) {
        for (; drivers.size() <= index; ) {
            newInstance();
        }

        return drivers.get(index);
    }

    public static UIWebDriver getActiveDriver() {
        return getBrowserDriver(ACTIVE_BROWSER_INDEX);
    }

    public static UIWebDriver switchActiveWindow(int index) {
        ACTIVE_BROWSER_INDEX = index;
        return getActiveDriver();
    }

    public static void close() {
        for (UIWebDriver webDriver : drivers) {
            if (webDriver != null) {
                try {
                    String path = System.getenv("PATH");

                    webDriver.quit(); //quit whole sessions and windows
                //    webDriver.close();  //close browser if we have nutiple browser window

                } catch (UnsatisfiedLinkError | NoClassDefFoundError | NullPointerException e) {

                    Log.log("Closing Browser", e, true);
                }
            }
        }
        drivers.clear();
        ACTIVE_BROWSER_INDEX = 0;
    }
}
