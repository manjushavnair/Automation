package com.automation.ui.connected.pageobjectsfactory.pageobject.globalnav;


import com.automation.ui.base.common.logging.Log;
import com.automation.ui.base.common.prpreaders.AssertDataReader;
import com.automation.ui.connected.pageobjectsfactory.pageobject.home.HomeConstants;
import com.automation.ui.connected.pageobjectsfactory.pageobject.home.HomePage;
import com.automation.ui.githubtesting.pageobjectfactory.pageobject.base.SiteBasePageObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.Reporter;

public class GlobalNavigation extends SiteBasePageObject {

    private static Logger logger = Logger.getLogger(GlobalNavigation.class);



    @FindBy(xpath = NAVIGATIONConstants.CLOUDAVATAR)
    private WebElement userAvatar;


    @FindBy(xpath = NAVIGATIONConstants.CLOUDDATABUTTON)
    private WebElement cloudButtonu;

    @FindBy(xpath = NAVIGATIONConstants.LOGOUTBUTTON)
    @CacheLookup
    private WebElement signOutButton;





    public String getTitle() {
        String title = driver.getTitle();

        return driver.getTitle();
    }


    public void goBack() {
        driver.navigate().back();

    }

    public void goForward() {
        driver.navigate().forward();

    }

    public void refresh() {
        driver.navigate().refresh();

    }


    public GlobalNavigation clickUserLogo() {
        wait.forElementClickable(userAvatar).click();
        Log.info("clicked on user logo in global nav bar");

        return this;
    }

    public void clickSignOut() {
        clickUserLogo();
        wait.forElementClickable(signOutButton).click();
        Log.info("link to sign out clicked");
    }


    //Go to LoginPage
    public void goToLogoutPage() {

        try {
            logger.info("Logging out of the URL ");
            Reporter.log("Logging out of the URL");
            // wait.forElementVisible(logout,BASEConstants.WAITTIME10000MILLISEC);

            signOutButton.click();

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(AssertDataReader.assertreader.getValue("OPCUA_LOGOUT_LOGOUTMSG"));
            Reporter.log("Logged out of the URL successfully");

        }


    }



    public boolean isUserLoggedOut() {
        return driver.findElements(By.cssSelector(".wds-global-navigation__account-menu")).size() > 0;
    }




    public boolean isUserAvatarVisible() {
        return isElementDisplayed(userAvatar, 3);
    }


}
