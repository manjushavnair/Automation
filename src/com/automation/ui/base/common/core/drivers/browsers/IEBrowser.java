package com.automation.ui.base.common.core.drivers.browsers;


import com.automation.ui.base.common.core.UIWebDriver;
import com.automation.ui.base.common.core.drivers.BrowserAbstract;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;

public class IEBrowser extends BrowserAbstract {




  /*
   public abstract void setOptions();

    protected abstract void startService();

    protected abstract void stopService();
    public void quitDriver() {
        if (null != driver) {
            driver.quit();
            driver = null;
        }

    }
   */


    @Override
    public void setOptions() {
    }

    @Override
    public UIWebDriver create() {

        caps.setCapability(CapabilityType.BROWSER_NAME, "IE");
        caps.setCapability(InternetExplorerDriver.
                INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        caps.setCapability("disable-popup-blocking", false);
        caps.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
        //caps.setBrowserName(wdConfig.getBrowserName());
        return new UIWebDriver(new InternetExplorerDriver(caps), server, false);
    }

    @Override
    public void addExtension(String extensionName) {

    }
}
