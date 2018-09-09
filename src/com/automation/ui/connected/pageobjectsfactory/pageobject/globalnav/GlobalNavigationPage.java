package com.automation.ui.connected.pageobjectsfactory.pageobject.globalnav;


import com.automation.ui.base.common.core.element.ElementStateHelper;
import com.automation.ui.base.common.logging.Log;
import com.automation.ui.githubtesting.pageobjectfactory.pageobject.base.SiteBasePageObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class GlobalNavigationPage extends SiteBasePageObject {

    private static Logger logger = Logger.getLogger(GlobalNavigationPage.class);




    @FindBy(xpath = NAVIGATIONConstants.CLOUDAVATAR)
    private WebElement userAvatar;


    @FindBy(xpath = NAVIGATIONConstants.CLOUDDATABUTTON)
    private WebElement cloudButtonu;

    @FindBy(xpath = NAVIGATIONConstants.LOGOUTBUTTON)
    private WebElement signOutButton;

    @FindBy(css = NAVIGATIONConstants.LOGOUTBUTTONLINK)
    private WebElement logOutButtonLink;




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


    public GlobalNavigationPage clickUserLogo() {
        wait.forElementClickable(userAvatar).click();
        Log.info("clicked on user logo in global nav bar");

        return this;
    }

    public void clickSignOut() {

        logger.warn("---------clickSignOut-------- 1");
        try {
            Thread.sleep(10000);
        }catch(Exception e){
            e.printStackTrace();
        }
        scrollAndClick(signOutButton, 1);
        waitAndClick(signOutButton);

/*
        if (ElementStateHelper.isElementVisible(logOutButtonLink)) {
            logger.warn("---------clickSignOut----------- 2");
            wait.forElementClickable(logOutButtonLink).click();
            // waitAndClick(signOutButton);
            //  signOutButton.click();

            Log.info("link to sign out clicked");
        }
        */
    }


    public boolean isUserLoggedOut() {
        return driver.findElements(By.cssSelector(".wds-global-navigation__account-menu")).size() > 0;
    }




    public boolean isUserAvatarVisible() {
        return isElementDisplayed(userAvatar, 3);
    }


}
