package com.automation.ui.base.common.core.drivers.browsers;


import com.automation.ui.base.common.core.UIWebDriver;
import com.automation.ui.base.common.core.drivers.BrowserAbstract;
import com.automation.ui.base.common.logging.Log;
import org.apache.log4j.Logger;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;

import java.io.File;

public class IEBrowser extends BrowserAbstract {


    private static Logger logger = Logger
            .getLogger(IEBrowser.class);




  /*
   public abstract void setOptions();

    protected abstract void startService();


   */
  private static final String CHROMEDRIVER_PATH_WINDOWS = "test/IEDriver/IEDriverServer.exe";



    @Override
    public void setOptions() {
        String ieBinaryPath = CHROMEDRIVER_PATH_WINDOWS;
        String osName = System.getProperty("os.name").toUpperCase();



        File iedriver= new File(ClassLoader.getSystemResource(ieBinaryPath).getPath());

        logger.info("IEDriverPath:"+ ieBinaryPath + " : " +iedriver.getPath());



        // set application user permissions to 455
       // iedriver.setExecutable(true);


        System.setProperty("webdriver.ie.driver", iedriver.getPath());
        Log.info("Using ie driver: ", iedriver.getPath());


    }

    @Override
    public UIWebDriver create() {

        caps.setCapability(CapabilityType.BROWSER_NAME, "IE");
        caps.setCapability(InternetExplorerDriver.
                INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        caps.setCapability("disable-popup-blocking", false);
        caps.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
        //caps.setBrowserName(wdConfig.getBrowserName());
        return new UIWebDriver(new InternetExplorerDriver( caps), server, false);
    }

    @Override
    public void addExtension(String extensionName) {

    }
}
