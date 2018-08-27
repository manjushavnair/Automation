package com.automation.ui.base.common.core.drivers.browsers;

import com.automation.ui.base.common.constants.BASEConstants;
import com.automation.ui.base.common.core.UIWebDriver;
import com.automation.ui.base.common.core.configuration.Configuration;
import com.automation.ui.base.common.core.drivers.BrowserAbstract;
import com.automation.ui.base.common.logging.Log;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class AndroidBrowser extends BrowserAbstract {

    private static AndroidDriver mobileDriver;

    public static AndroidDriver getMobileDriver() {
        return mobileDriver;
    }

    @Override
    public void setOptions() {
        DesiredCapabilities destCaps = new DesiredCapabilities();
        destCaps.setCapability("deviceName", Configuration.getDeviceName());
        URL url = null;
        try {
            url = new URL(BASEConstants.HTTP_PREFIX + Configuration.getAppiumIp() + "/wd/hub");
        } catch (MalformedURLException e) {
            Log.log("getAndroindInstance", e, false);
        }
        mobileDriver = new AndroidDriver(url, destCaps);

    }

    @Override
    public UIWebDriver create() {
        return new UIWebDriver(mobileDriver, true);
    }

    @Override
    public void addExtension(String extensionName) {
        // No extensions are applied to android
    }
}
