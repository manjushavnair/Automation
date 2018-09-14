package com.automation.ui.base.common.core.drivers.browsers;


import com.automation.ui.base.common.core.UIWebDriver;
import com.automation.ui.base.common.core.configuration.Configuration;
import com.automation.ui.base.common.core.drivers.BrowserAbstract;
import com.automation.ui.base.common.logging.Log;
import com.automation.ui.base.common.utils.BrowserExtentionHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.CapabilityType;

import java.io.File;
import java.io.IOException;

public class FirefoxBrowser extends BrowserAbstract {


    private static final String FIREFOXDRIVER_PATH_FORMAT = "FireFoxDriver/firefoxdriver_%s";

    private static final String FIREFOXDRIVER_PATH_MAC =
            String.format(FIREFOXDRIVER_PATH_FORMAT, "mac64/geckodriver");

    private static final String FIREFOXDRIVER_PATH_LINUX =
            String.format(FIREFOXDRIVER_PATH_FORMAT, "linux64/geckodriver");

    private static final String FIREFOXDRIVER_PATH_WINDOWS =
            String.format(FIREFOXDRIVER_PATH_FORMAT, "win32/geckodriver.exe");
    private static Logger logger = Logger
            .getLogger(FirefoxBrowser.class);
    private FirefoxProfile firefoxProfile;
    private GeckoDriverService fxService;
    private FirefoxOptions opt;

  /*
   public abstract void setOptions();

    protected abstract void startService();

    protected abstract void stopService();



   */

    @Override
    public void setOptions() {
        // Windows 8 requires to set webdriver.firefox.bin system variable
        // to path where executive file of FF is placed


        String firefoxBinaryPath = "";
        String osName = System.getProperty("os.name").toUpperCase();
        if (osName.contains("WINDOWS")) {
            firefoxBinaryPath = FIREFOXDRIVER_PATH_WINDOWS;
        } else if (osName.contains("MAC")) {
            firefoxBinaryPath = FIREFOXDRIVER_PATH_MAC;
        } else if (osName.contains("LINUX")) {
            firefoxBinaryPath = FIREFOXDRIVER_PATH_LINUX;
        }


        File ffdriver = new File(ClassLoader.getSystemResource(firefoxBinaryPath).getPath());

        logger.info("FirefoxDriverPath:" + firefoxBinaryPath + " : " + ffdriver.getPath());


        // set application user permissions to 455
        ffdriver.setExecutable(true);


        //System.setProperty("webdriver.firefox.driver", ffdriver.getPath());
        //System.setProperty("webdriver.firefox.marionette", ffdriver.getPath());
        System.setProperty("webdriver.gecko.driver", ffdriver.getPath());
        Log.info("Using firefox driver: ", ffdriver.getPath());


       /* if ("WINDOWS 8".equalsIgnoreCase(System.getProperty("os.name"))) {
            logger.info("Firefox setOptions"+System.getProperty("os.name"));
            System.setProperty("webdriver.gecko.driver", "c:" + File.separator + "Program Files (x86)"
                    + File.separator + "Mozilla Firefox" + File.separator + "Firefox.exe");
					//webdriver.gecko.driver
					//webdriver.firefox.bin
					//webdriver.firefox.marionette

					// System.setProperty("webdriver.firefox.marionette", System.getProperty("user.dir") + "path");
        }
        if ("WINDOWS 7".equalsIgnoreCase(System.getProperty("os.name"))) {
			logger.info("Firefox setOptions"+System.getProperty("os.name"));
          //  System.setProperty("webdriver.gecko.driver", "c:" + File.separator + "Program Files (x86)"
            //        + File.separator + "Mozilla Firefox" + File.separator + "Firefox.exe");
					//webdriver.gecko.driver
					//webdriver.firefox.bin


            //webdriver.firefox.driver
            System.setProperty("webdriver.firefox.driver", System.getProperty("user.dir")+"\\"+"geckodriver-v0.21.0-win64.zip");


        }

        else if (System.getProperty("os.name")
                .contains("Windows Server") ){
					logger.info("Firefox setOptions"+System.getProperty("os.name"));
            System.setProperty("webdriver.gecko.driver", "c:" + File.separator + "Program Files"
                    + File.separator + "Mozilla Firefox" + File.separator + "Firefox.exe");


            logger.info("Windows Server");
            // System.setProperty("webdriver.firefox.marionette", System.getProperty("user.dir") + "path");
        }

 */

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

        opt = new FirefoxOptions();
        opt.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        if ("true".equals(Configuration.getPageLoadStrategy())) {

            firefoxProfile.setPreference("webdriver.load.strategy", "normal");
        }

        if ("true".equals(Configuration.getDisableFlash())) {
            firefoxProfile.setPreference("plugin.state.flash", 0);
        }


        firefoxProfile.setPreference("network.proxy.type", 0);
        // profile.setPreference("general.useragent.override", userAgent);
        // if (disableCookies) {
        // 	profile.setPreference("network.cookie.cookieBehavior", 2);
        // }

        firefoxProfile.setPreference("http.response.timeout", 15);
        firefoxProfile.setPreference("dom.max_script_run_time", 35);


        firefoxProfile.setPreference("browser.download.folderList", 2);
        firefoxProfile.setPreference("browser.download.manager.showWhenStarting", false);
        //profile.setPreference("browser.download.dir", System.getProperty("user.dir")+ File.separator +"Download");
        //profile.setPreference("browser.download.downloadDir", System.getProperty("user.dir")+ File.separator +"Download");
        //profile.setPreference("browser.download.defaultFolder", System.getProperty("user.dir")+ File.separator +"Download");
        firefoxProfile.setPreference("browser.download.manager.closeWhenDone", true);
        firefoxProfile.setPreference("pdfjs.disabled", true);
        firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/zip,text/csv,application/msword,application/excel,application/pdf," +
                "application/vnd.ms-excel,application/msword,application/unknown,application/vnd.openxmlformats-officedocument.wordprocessingml.document");

        firefoxProfile.setAcceptUntrustedCertificates(true);
        firefoxProfile.setAssumeUntrustedCertificateIssuer(true);

        // firefoxProfile.setCapability("marionette", true);

    }

    @Override
    public UIWebDriver create() {
        caps.setCapability(FirefoxDriver.PROFILE, firefoxProfile);
        //     caps.setCapability("marionette", false);
        caps.setCapability("gecko", true);

        caps.setCapability("disable-popup-blocking", false);
        caps.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);

        //return new UIWebDriver(new RemoteWebDriver(new URL(hubUrl), caps));
        //caps.setBrowserName(wdConfig.getBrowserName());
        //return new UIWebDriver(new FirefoxDriver(caps ), server, false);  //non deprecated

        return new UIWebDriver(new FirefoxDriver(caps), server, false);
    }

    @Override
    public void addExtension(String extensionName) {
        firefoxProfile.addExtension(BrowserExtentionHelper.findExtension(extensionName, "xpi"));
    }
}
