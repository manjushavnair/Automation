package com.automation.ui.connected.pageobjectsfactory.pageobject.home;

import com.automation.ui.base.common.core.configuration.Configuration;
import com.automation.ui.base.common.core.configuration.EnvType;
import com.automation.ui.base.common.core.element.ElementStateHelper;
import com.automation.ui.base.common.prpreaders.AssertDataReader;
import com.automation.ui.connected.pageobjectsfactory.pageobject.base.SiteBasePageObject;
import com.automation.ui.connected.pageobjectsfactory.pageobject.serverdetails.AddServerDetails;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.Reporter;

public class HomePage extends SiteBasePageObject {


    private static Logger logger = Logger.getLogger(HomePage.class);

    @FindBy(xpath = HomeConstants.ADDCONNECTIONSBUTTON)
    private WebElement addButton;

    @FindBy(xpath = HomeConstants.ADDCONNECTIONSBUTTON_TOP)
    private WebElement addButtonTop;


    @FindBy(css = "primary")
    private WebElement primaryButton;


    @FindBy(css = "a.logouts")
    private WebElement logoutLink;

    @FindBy(xpath = HomeConstants.LOGOUTBUTTON)
    @CacheLookup
    private WebElement logout;

    @FindBy(xpath = HomeConstants.MENUBUTTON)
    @CacheLookup
    private WebElement menubutton;


    @FindBy(xpath = HomeConstants.ADDCONNECTIONSBUTTON_ERROR_MSG)
    private WebElement msg;

    @FindBy(xpath = HomeConstants.ADDCONNECTIONSBUTTON_ERROR_MSG_EXTRA)
    private WebElement msgExtra;


    /*public  HomePage open() {
        return open(Configuration.getSiteName());
    }

    public  HomePage open(String siteName) {
        getUrl(UrlBuilder.createUrlBuilderForSite(siteName).getUrl());
        waitForPageLoad();

        return this;
    }

    public com.automation.ui.connected.pageobjectsfactory.pageobject.home.HomePage openAndWaitForGlobalShortcuts() {
        open();
        waitFor.until((Function<WebDriver, Boolean>) arg0 -> driver
                .executeScript(
                        "return typeof window.wgGlobalShortcutsLoaded !== 'undefined' && window.wgGlobalShortcutsLoaded")
                .equals(true));

        return this;
    }*/

    public HomePage() {
        super();
    }


    public HomePage open() {

        logger.info("open the URL" + getCurrentUrl());
        if (Configuration.getEnvType().equals(EnvType.DEV)) {

            logger.info(getCurrentUrl());
            getUrl("http://localhost:9000");
            // getUrl(getCurrentUrl());
        } else {
            logger.info(getCurrentUrl());
            // getUrl("http://localhost:9000");
            // getUrl(getCurrentUrl() + " ");
            getUrl(getCurrentUrl());
        }
        //waitForPageLoad();


        return this;
    }


    //Go to LoginPage
    public void goToLogoutPage() {

        try {
            logger.info("Logging out of the URL ");
            Reporter.log("Logging out of the URL");
            // wait.forElementVisible(logout,BASEConstants.WAITTIME10000MILLISEC);

            logout.click();

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(AssertDataReader.assertreader.getValue("OPCUA_LOGOUT_LOGOUTMSG"));
            Reporter.log("Logged out of the URL successfully");

        }


    }


    public AddServerDetails addConnection() {


        try {
            logger.info("Entering  addConnection:");

            Thread.sleep(2000);


            if (ElementStateHelper.isElementVisible(addButton)) {

                waitAndClick(addButton);


            } else {


                wait.forElementVisible(addButtonTop);

                if (ElementStateHelper.isElementVisible(addButtonTop)) {


                    waitAndClick(addButtonTop);

                }
            }


            logger.info("Exiting  addConnection");

        } catch (Exception e) {
            e.printStackTrace();

        }
        return new AddServerDetails();

    }


    public void addConnection_accessdenied() {

        logger.info("Entering  addConnection_accessdenied: ");
        Reporter.log("Entering  addConnection_accessdenied:");
        try {
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }


        logger.info("accessdenied " + msg.getText());


        logger.info("Exiting  addConnection_accessdenied");
        Reporter.log("Exiting  addConnection_accessdenied");


    }


}