package com.automation.ui.connected.pageobjectsfactory.pageobject.base;

import com.automation.ui.base.common.auth.User;
import com.automation.ui.base.common.core.Helios;
import com.automation.ui.base.common.core.configuration.Configuration;
import com.automation.ui.base.common.logging.Log;
import com.automation.ui.base.pageobjectsfactory.pageobject.BasePageObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;


public class SiteBasePageObject extends BasePageObject {


    private static final String LOGGED_IN_USER_SELECTOR =
            ".wds-global-navigation__user-menu.wds-global-navigation__user-logged-in img, "
                    + ".wds-global-navigation__user-menu.wds-global-navigation__user-logged-in svg";

     private static Logger logger = Logger.getLogger(SiteBasePageObject.class);
    @FindBy(css = "#globalNavigation,.site-head.no-shadow,.wds-global-navigation")
    protected WebElement navigationBar;
    @FindBy(css = "#globalNavigation")
    protected WebElement newGlobalNavigation;


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

                WebElement logo = wait.forElementPresent(By.cssSelector(LOGGED_IN_USER_SELECTOR));
                String loggedInUserName = logo.getAttribute("alt");
                if (!loggedInUserName.equals(userName) && !loggedInUserName.equals(userName + " logo")) {
                    throw new IllegalArgumentException(
                            "Invalid user, expected " + userName + ", but found: " + loggedInUserName);
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





    protected Boolean isNewGlobalNavPresent() {

        return isElementOnPage(newGlobalNavigation);
    }





}
