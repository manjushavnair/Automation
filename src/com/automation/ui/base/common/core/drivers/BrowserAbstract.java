package com.automation.ui.base.common.core.drivers;

import com.automation.ui.base.common.core.UIWebDriver;
import com.automation.ui.base.common.core.XMLReader;
import com.automation.ui.base.common.core.configuration.Configuration;
import com.automation.ui.base.common.core.networktrafficinterceptor.NetworkTrafficInterceptor;
import com.automation.ui.base.common.testnglisteners.BrowserAndTestEventListener;
import com.automation.ui.base.common.utils.CookieUtils;
import net.lightbody.bmp.mitm.TrustSource;
import net.lightbody.bmp.proxy.CaptureType;
import org.apache.log4j.Logger;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import com.automation.ui.base.common.constants.*;
public abstract class BrowserAbstract {

    private static Logger logger = Logger.getLogger(BrowserAbstract.class);
    protected DesiredCapabilities caps = new DesiredCapabilities();
    protected NetworkTrafficInterceptor server;

    /**
     * Get a ready to work instance for chosen browser
     * @return UIWebDriver
     */
    public UIWebDriver getInstance() {
        setOptions();
        setProxy();
        setExtensions();
        setBrowserLogging(Level.SEVERE);
        UIWebDriver webdriver = create();
        setTimeputs(webdriver);
        setListeners(webdriver);

        return webdriver;
    }

    /**
     * Set Browser specific options, before creating a working instance
     * @return void
     */
    public abstract void setOptions();

    /**
     * Create a working instance of a Browser
     *  @return UIWebDriver
     */
    public abstract UIWebDriver create();

    protected void setBrowserLogging(Level logLevel) {
        LoggingPreferences loggingprefs = new LoggingPreferences();
        loggingprefs.enable(LogType.BROWSER, logLevel);
        caps.setCapability(CapabilityType.LOGGING_PREFS, loggingprefs);
    }

    protected void setTimeputs(WebDriver webDriver) {
        webDriver.manage().timeouts().implicitlyWait(BASEConstants.WAITTIME15SEC, TimeUnit.SECONDS);
    }

    protected void setListeners(UIWebDriver webDriver) {
        webDriver.register(new BrowserAndTestEventListener());
    }

    /**
     * Add browser extensions
     *  @return void
     */
    public abstract void addExtension(String extensionName);

    protected void setExtensions() {
        for (String name : Configuration.getExtensions()) {
            addExtension(name);
        }
    }


    protected void deleteAllCookies(WebDriver driver) {
        CookieUtils.deleteAllCookies(driver);

    }

    protected void deleteCookiesOneByOne(WebDriver driver) {
        CookieUtils.deleteCookiesOneByOne(driver);
    }


    /**
     * Set Proxy instance for a Browser instance
     *  @return void
     */
    protected void setProxy() {
        if (Configuration.useProxy()) {
            Proxy proxyServer = new Proxy();
            if ("true".equals(Configuration.useZap())) {
                String zapProxyAddress = String.format("%s:%s", XMLReader.getValue("zap_proxy.address"),
                        Integer
                                .parseInt(XMLReader.getValue("zap_proxy.port")));
                String zapSSLProxyAddress = String.format("%s:%s", XMLReader.getValue("zap_proxy.address"),
                        Integer
                                .parseInt(XMLReader.getValue("zap_proxy.port")));
                proxyServer.setHttpProxy(zapProxyAddress);

                proxyServer.setSslProxy(zapSSLProxyAddress);
            } else {
                server = new NetworkTrafficInterceptor();
                server.setTrustAllServers(true);
                server.setConnectTimeout(90, TimeUnit.SECONDS);
                server.setTrustSource(TrustSource.defaultTrustSource());
                server.setMitmDisabled(!Boolean.parseBoolean(Configuration.useMITM()));
                server.setRequestTimeout(90, TimeUnit.SECONDS);
                server.enableHarCaptureTypes(CaptureType.REQUEST_HEADERS,
                        CaptureType.RESPONSE_HEADERS);
                server.setUseEcc(true);
                proxyServer = server.startBrowserMobProxyServer();
            }
            caps.setCapability(CapabilityType.PROXY, proxyServer);
        }
    }
}
