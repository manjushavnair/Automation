package com.automation.ui.base.common.core.drivers.browsers;

import com.automation.ui.base.common.core.ExtHelper;
import com.automation.ui.base.common.core.UIWebDriver;
import com.automation.ui.base.common.core.configuration.Configuration;
import com.automation.ui.base.common.core.drivers.BrowserAbstract;
import com.automation.ui.base.common.logging.Log;
import org.apache.log4j.Logger;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.CapabilityType;

import java.io.File;
import java.io.IOException;

public class FirefoxBrowser extends BrowserAbstract {

    private FirefoxProfile firefoxProfile;
    private GeckoDriverService fxService;
    private static Logger logger = Logger
            .getLogger(FirefoxBrowser.class);

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
        // Windows 8 requires to set webdriver.firefox.bin system variable
        // to path where executive file of FF is placed
        if ("WINDOWS 8".equalsIgnoreCase(System.getProperty("os.name"))) {
            System.setProperty("webdriver.gecko.driver", "c:" + File.separator + "Program Files (x86)"
                    + File.separator + "Mozilla Firefox" + File.separator + "Firefox.exe");
					//webdriver.gecko.driver
					//webdriver.firefox.bin

					// System.setProperty("webdriver.firefox.marionette", System.getProperty("user.dir") + "path");
        }
        else if (System.getProperty("os.name")
                .contains("Windows Server") ){
            System.setProperty("webdriver.gecko.driver", "c:" + File.separator + "Program Files"
                    + File.separator + "Mozilla Firefox" + File.separator + "Firefox.exe");


            logger.info("Windows Server");
            // System.setProperty("webdriver.firefox.marionette", System.getProperty("user.dir") + "path");
        }


        // Check if user who is running tests have write access in ~/.mozilla dir and home dir
        if ("LINUX".equalsIgnoreCase(System.getProperty("os.name"))) {
            File homePath = new File(System.getenv("HOME") + File.separator);
            File mozillaPath = new File(homePath + File.separator + ".mozilla");
            File tmpFile;
            if (mozillaPath.exists()) {
                try {
                    tmpFile = File.createTempFile("webdriver", null, mozillaPath);
                } catch (IOException ex) {
                    Log.log("Can't create file", ex, false);
                    throw new WebDriverException(
                            "Can't create file in path: %s".replace("%s", mozillaPath.getAbsolutePath()));
                }
            } else {
                try {
                    tmpFile = File.createTempFile("webdriver", null, homePath);
                } catch (IOException ex) {
                    Log.log("Can't create file", ex, false);
                    throw new WebDriverException(
                            "Can't create file in path: %s".replace("%s", homePath.getAbsolutePath()));
                }
            }
            tmpFile.delete();
        }

        firefoxProfile = new FirefoxProfile(
                new File(ClassLoader.getSystemResource("test/FirefoxProfiles/Default").getPath()));

        if ("true".equals(Configuration.getPageLoadStrategy())) {
            firefoxProfile.setPreference("webdriver.load.strategy", "unstable");
        }

        if ("true".equals(Configuration.getDisableFlash())) {
            firefoxProfile.setPreference("plugin.state.flash", 0);
        }


        firefoxProfile.setPreference("network.proxy.type", 0);
        // profile.setPreference("general.useragent.override", userAgent);
        // if (disableCookies) {
        // 	profile.setPreference("network.cookie.cookieBehavior", 2);
        // }
        firefoxProfile.setPreference("browser.download.folderList", 2);
        firefoxProfile.setPreference("browser.download.manager.showWhenStarting", false);
        //profile.setPreference("browser.download.dir", System.getProperty("user.dir")+ File.separator +"Download");
        //profile.setPreference("browser.download.downloadDir", System.getProperty("user.dir")+ File.separator +"Download");
        //profile.setPreference("browser.download.defaultFolder", System.getProperty("user.dir")+ File.separator +"Download");
        firefoxProfile.setPreference("browser.download.manager.closeWhenDone", true);
        firefoxProfile.setPreference("pdfjs.disabled", true);
        firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/zip,text/csv,application/msword,application/excel,application/pdf," +
                "application/vnd.ms-excel,application/msword,application/unknown,application/vnd.openxmlformats-officedocument.wordprocessingml.document");


    }

    @Override
    public UIWebDriver create() {
        caps.setCapability(FirefoxDriver.PROFILE, firefoxProfile);
        caps.setCapability("marionette", false);
        caps.setCapability("disable-popup-blocking", false);
        caps.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);

        //caps.setBrowserName(wdConfig.getBrowserName());


        return new UIWebDriver(new FirefoxDriver(caps), server, false);
    }

    @Override
    public void addExtension(String extensionName) {
        firefoxProfile.addExtension(ExtHelper.findExtension(extensionName, "xpi"));
    }
}
