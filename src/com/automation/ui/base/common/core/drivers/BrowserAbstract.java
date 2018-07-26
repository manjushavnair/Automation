package com.automation.ui.base.common.core.drivers;

import com.automation.ui.base.common.core.UIWebDriver;
import com.automation.ui.base.common.core.XMLReader;
import com.automation.ui.base.common.core.configuration.Configuration;
import com.automation.ui.base.common.core.networktrafficinterceptor.NetworkTrafficInterceptor;
import com.automation.ui.base.common.testnglisteners.BrowserAndTestEventListener;
import net.lightbody.bmp.mitm.TrustSource;
import net.lightbody.bmp.proxy.CaptureType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public abstract class BrowserAbstract {

    protected DesiredCapabilities caps = new DesiredCapabilities();
    protected NetworkTrafficInterceptor server;

    /**
     * Get a ready to work instance for chosen browser
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
     */
    public abstract void setOptions();

    /**
     * Create a working instance of a Browser
     */
    public abstract UIWebDriver create();

    protected void setBrowserLogging(Level logLevel) {
        LoggingPreferences loggingprefs = new LoggingPreferences();
        loggingprefs.enable(LogType.BROWSER, logLevel);
        caps.setCapability(CapabilityType.LOGGING_PREFS, loggingprefs);
    }

    protected void setTimeputs(WebDriver webDriver) {
        webDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    protected void setListeners(UIWebDriver webDriver) {
        webDriver.register(new BrowserAndTestEventListener());
    }

    /**
     * Add browser extensions
     */
    public abstract void addExtension(String extensionName);

    protected void setExtensions() {
        for (String name : Configuration.getExtensions()) {
            addExtension(name);
        }
    }


    /**
     * Set Proxy instance for a Browser instance
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