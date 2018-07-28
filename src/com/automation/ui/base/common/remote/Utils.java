package com.automation.ui.base.common.remote;

import com.automation.ui.base.common.core.XMLReader;
import com.automation.ui.base.common.core.configuration.Configuration;
import com.automation.ui.base.common.core.url.UrlBuilder;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.util.Objects;

public final class Utils {


    private Utils() {
        throw new AssertionError();
    }

    public static String extractSiteIdFromMediaSiteUsing(final WebDriver driver) {
        String text = driver.findElement(By.cssSelector(".colorb tr:nth-child(5) td:nth-child(2)")).getText();
        // Found text: "city_id: 1362702, cluster: c7, dc: sjc"
        return StringUtils.substringBetween(text, ": ", ",");
    }

    private static String extractSiteIdFromMediaSite(String siteUrl) {
        return new SiteId(siteUrl).getSiteId();
    }

    public static String excractSiteIdFromSiteName(String siteName) {
        String siteUrl = UrlBuilder.createUrlBuilderForSite(siteName).getUrl().replace("https://", "http://");
        return extractSiteIdFromMediaSite(siteUrl);
    }

    public static String buildServicesUrl() {
        File configurationFile = new File(Configuration.getCredentialsFilePath());
        final String environment = Configuration.getEnvType().getKey();
        final String url = XMLReader.getValue(configurationFile, "services." + environment);
        Objects.requireNonNull(url,
                "Please check if your configuration file contains url for service " + environment);
        return url;
    }
}
