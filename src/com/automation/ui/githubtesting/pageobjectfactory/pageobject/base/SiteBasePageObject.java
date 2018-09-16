package com.automation.ui.githubtesting.pageobjectfactory.pageobject.base;

import com.automation.ui.base.common.auth.User;
import com.automation.ui.base.common.core.Helios;
import com.automation.ui.base.common.core.configuration.Configuration;
import com.automation.ui.base.common.logging.Log;
import com.automation.ui.base.pageobjectsfactory.pageobject.BasePageObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.TimeoutException;

import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class SiteBasePageObject extends BasePageObject {



    private static Logger logger = Logger.getLogger(SiteBasePageObject.class);
    protected By parentBy = By.xpath("./..");

    public SiteBasePageObject() {
        super();
    }


    //NEED TO CHANGE the context based on site context
    public String getContextUrl() {
        String currentURL = driver.getCurrentUrl();
        return currentURL.substring(0, currentURL.lastIndexOf("/login"));
    }


    public String getUrl() {
        return driver.getCurrentUrl();
    }

    private void logUserId() {
        Object scriptOut = driver.executeScript("return window.M && window.M.prop('userId')");

        if (scriptOut != null) {
            Log.info("  userID: " + scriptOut.toString());
        }
    }

    /**
     * Logout by clicking on "Sign out" option in global navigation
     */
    public void logOut() {
        try {
            //getGlobalNavigation().clickSignOut();
        } catch (TimeoutException e) {
            Log.log("logOut", "page loads for more than 30 seconds", true);
        }
    }


    public String loginAs(User user) {

        return loginAs(user.getUserName(), user.getPassword(), urlBuilder.getUrl());
    }

    public String loginAs(String userName, String password, String siteURL) {
        String token = Helios.getAccessToken(userName);

        driver.manage().addCookie(new Cookie("access_token", token,
                String.format(".%s", Configuration.getEnvType().getSiteDomain()), null, null));

        logger.info("user was logged in by helios using cookietoken: " + String.format(".%s", Configuration.getEnvType().getSiteDomain()));

        if (driver.getCurrentUrl().contains("Logout")) {
            driver.get(siteURL);
        } else {
            refreshPageAddingCacheBuster();
        }

        logger.info("user was logged in by helios using userName: " + userName);


        this.verifyUserLoggedIn(userName);
        Log.info("loginCookie",
                "user was logged in by helios using access token: " + token);
        logger.info("user was logged in by helios using access token: " + token);
        logUserId();

        return token;
    }

    public void verifyUserLoggedIn(final String userName) {
        changeImplicitWait(0, TimeUnit.MILLISECONDS);
        try {
            if (driver.findElements(By.cssSelector("#PreviewFrame")).size() > 0) {
                driver.switchTo().frame("PreviewFrame");
            }

        } finally {
            restoreDefaultImplicitWait();
            driver.switchTo().defaultContent();
        }
        Log.log("verifyUserLoggedIn", "user " + userName + " logged in", true);
    }

    public void verifyUserLoggedIn(User user) {
        this.verifyUserLoggedIn(user.getUserName());
    }

    //"script[src*='/scripts/beacon.js']";
    // wait for comscore to load
    @Override
    public void waitForPageLoad() {

        wait.forElementPresent(By.cssSelector(".octicon.octicon-mark-github"));

    }

    @Override
    public BasePageObject waitForPageReload() {
        waitSafely(
                () -> wait.forElementVisible(By.cssSelector(".octicon.octicon-mark-github"), Duration.ofSeconds(3)));

        waitSafely(() -> wait.forElementNotVisible(By.cssSelector(".octicon.octicon-mark-github")),
                "Loading overlay still visible, page not loaded in expected time");

        logger.info("Loading overlay still visible, page not loaded in expected time");
        return this;
    }

    public static class AssertionMessages {

        public static final String INVALID_NUMBER_OF_CONFIRMING_NOTIFICATIONS =
                "Number of action confirming notifications is invalid";
        public static final String BANNER_NOTIFICATION_NOT_VISIBLE = "Banner notification message is not visible";

        private AssertionMessages() {
            throw new IllegalAccessError("Utility class");
        }
    }


}
