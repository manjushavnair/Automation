package com.automation.ui.base.common.core.drivers.browsers;

import com.automation.ui.base.common.core.ExtHelper;
import com.automation.ui.base.common.core.UIWebDriver;
import com.automation.ui.base.common.core.configuration.Configuration;
import com.automation.ui.base.common.core.drivers.BrowserAbstract;
import com.automation.ui.base.common.core.helpers.Emulator;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.CapabilityType;
import com.automation.ui.base.common.driverprovider.UserAgentsRegistry;
import com.automation.ui.base.common.logging.Log;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ChromeBrowser extends BrowserAbstract {

    private static final String CHROMEDRIVER_PATH_FORMAT = "test/ChromeDriver/chromedriver_%s";
    private static final String CHROMEDRIVER_PATH_MAC =
            String.format(CHROMEDRIVER_PATH_FORMAT, "mac64/chromedriver");
    private static final String CHROMEDRIVER_PATH_LINUX =
            String.format(CHROMEDRIVER_PATH_FORMAT, "linux64/chromedriver");
    private static final String CHROMEDRIVER_PATH_WINDOWS =
            String.format(CHROMEDRIVER_PATH_FORMAT, "win32/chromedriver.exe");
    private ChromeOptions chromeOptions = new ChromeOptions();
    private boolean useMobile = "CHROMEMOBILEMERCURY".equals(Configuration.getBrowser());

    private static Logger logger = Logger.getLogger(ChromeBrowser.class);

    @Override
    public void setOptions() {
        String chromeBinaryPath = "";
        String osName = System.getProperty("os.name").toUpperCase();
        Emulator emulator = Configuration.getEmulator();

        if (osName.contains("WINDOWS")) {
            chromeBinaryPath = CHROMEDRIVER_PATH_WINDOWS;
        } else if (osName.contains("MAC")) {
            chromeBinaryPath = CHROMEDRIVER_PATH_MAC;
        } else if (osName.contains("LINUX")) {
            chromeBinaryPath = CHROMEDRIVER_PATH_LINUX;
        }


        Log.info("Using chromedriver: ", chromeBinaryPath);
        File chromedriver = new File(ClassLoader.getSystemResource(chromeBinaryPath).getPath());

        // set application user permissions to 455
        chromedriver.setExecutable(true);

        System.setProperty("webdriver.chrome.driver", chromedriver.getPath());
        Log.info("Using chromedriver: ", chromedriver.getPath());

        chromeOptions.addArguments("start-maximized");
        chromeOptions.addArguments("disable-notifications");
        chromeOptions.addArguments("process-per-site");
        chromeOptions.addArguments("dns-prefetch-disable");
        chromeOptions.addArguments("allow-running-insecure-content");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("user-data-dir=" + System.getProperty("user.dir")+File.separator+"logs"+File.separator+"chromeprofile");


        if ("true".equals(Configuration.getDisableFlash())) {
            chromeOptions.addArguments("disable-bundled-ppapi-flash");
        }

        if (useMobile) {
            chromeOptions.addArguments("--user-agent=" + UserAgentsRegistry.IPHONE.getUserAgent());
        }

        if (!emulator.equals(Emulator.DEFAULT)) {
            Map<String, Object> mobileEmulation = new HashMap<>();
            if (StringUtils.isNotBlank(emulator.getUserAgent())) {
                mobileEmulation.put("userAgent", emulator.getUserAgent());
            }
            if (StringUtils.isNotBlank(emulator.getDeviceName())) {
                mobileEmulation.put("deviceName", emulator.getDeviceName());
            } else {
                mobileEmulation.put("deviceMetrics", emulator.getDeviceMetrics());
            }

            chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
        }
    }


    @Override
    public UIWebDriver create() {
        caps.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        if (Configuration.isUnsafePageLoad()) {
            caps.setCapability("pageLoadStrategy", "none");
        }
        caps.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);


        return new UIWebDriver(new ChromeDriver(caps), server, useMobile);
    }

    @Override
    public void addExtension(String extensionName) {
        chromeOptions.addExtensions(ExtHelper.findExtension(extensionName, "crx"));
    }
}
