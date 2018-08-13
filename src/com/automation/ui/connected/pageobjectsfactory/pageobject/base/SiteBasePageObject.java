package com.automation.ui.connected.pageobjectsfactory.pageobject.base;

import com.automation.ui.base.common.auth.User;
import com.automation.ui.base.common.core.Helios;
import com.automation.ui.base.common.core.configuration.Configuration;
import com.automation.ui.base.common.logging.Log;
import com.automation.ui.base.pageobjectsfactory.pageobject.BasePageObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;


public class SiteBasePageObject extends BasePageObject {


    private static final String LOGGED_IN_USER_SELECTOR_OASIS =
            ".wds-global-navigation__user-menu.wds-global-navigation__user-logged-in img, "
                    + ".wds-global-navigation__user-menu.wds-global-navigation__user-logged-in svg";

    private static final By MERCURY_SKIN = By.cssSelector("#ember-container");
    private static final By MERCURY_NAV_ICON = By.cssSelector(".site-head .site-head-icon-nav");
    private static Logger logger = Logger.getLogger(SiteBasePageObject.class);
    @FindBy(css = "#globalNavigation,.site-head.no-shadow,.wds-global-navigation")
    protected WebElement navigationBar;
    @FindBy(css = "#globalNavigation")
    protected WebElement newGlobalNavigation;
    protected By parentBy = By.xpath("./..");
    @FindBy(css = ".banner-notifications-placeholder,.smart-banner")
    private WebElement bannerNotificationContainer;
    @FindBy(css = ".wds-dropdown__toggle .wds-avatar")
    private WebElement globalNavigationAvatar;
    @FindBy(css = ".wds-global-navigation")
    private WebElement globalNavigationBar;

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
            //  getGlobalNavigation().clickSignOut();
        } catch (TimeoutException e) {
            Log.log("logOut", "page loads for more than 30 seconds", true);
        }
    }


    public int getBannerNotificationsHeight() {
        return bannerNotificationContainer.getSize().getHeight();
    }

    public int getNavigationBarOffsetFromTop() {
        return Integer.parseInt(navigationBar.getAttribute("offsetTop")) + navigationBar.getSize().height;
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
            // open nav , required to see login data
            if (driver.findElements(MERCURY_SKIN).size() > 0) {
                wait.forElementClickable(MERCURY_NAV_ICON);
                driver.findElement(MERCURY_NAV_ICON).click();
                //     wait.forElementVisible(By.cssSelector(
                //           LOGGED_IN_USER_SELECTOR_MERCURY.replace("%userName%", userName.replace(" ", "_"))));
                // close nav
                wait.forElementClickable(MERCURY_NAV_ICON);
                driver.findElement(MERCURY_NAV_ICON).click();
            } else {
                WebElement logo = wait.forElementPresent(By.cssSelector(LOGGED_IN_USER_SELECTOR_OASIS));
                String loggedInUserName = logo.getAttribute("alt");
                if (!loggedInUserName.equals(userName) && !loggedInUserName.equals(userName + " logo")) {
                    throw new IllegalArgumentException(
                            "Invalid user, expected " + userName + ", but found: " + loggedInUserName);
                }
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


    public void verifyGlobalNavigation() {
        //NEEDTOCHECK
        wait.forElementVisible(globalNavigationBar);
        Log.log("verifyGlobalNavigation", "Verified global navigation", true);
    }

    public void verifyAvatarVisible() {
        //NEEDTOCHECK

        wait.forElementVisible(globalNavigationAvatar);
        Log.log("verifyAvatarVisible", "desired logo is visible on navbar", true);
    }


    protected Boolean isNewGlobalNavPresent() {
        return isElementOnPage(newGlobalNavigation);
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
