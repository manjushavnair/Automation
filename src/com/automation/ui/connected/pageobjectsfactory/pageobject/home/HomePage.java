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
    @CacheLookup
    private WebElement addButton;

    @FindBy(xpath = HomeConstants.ADDCONNECTIONSBUTTON_TOP)
    @CacheLookup
    private WebElement addButtonTop;




    @FindBy(xpath = HomeConstants.MENUBUTTON)
    @CacheLookup
    private WebElement menubutton;


    @FindBy(xpath = HomeConstants.ADDCONNECTIONSBUTTON_ERROR_MSG)
    @CacheLookup
    private WebElement msg;

    @FindBy(xpath = HomeConstants.ADDCONNECTIONSBUTTON_ERROR_MSG_EXTRA)
    @CacheLookup
    private WebElement msgExtra;


    /*public  HomePage open() {
        return open(Configuration.getSiteName());
    }

    public  HomePage open(String siteName) {
        getUrl(UrlBuilder.createUrlBuilderForSite(siteName).getUrl());
        waitForPageLoad();

        return this;
    }

    */

    public HomePage() {
        super();
    }


    public HomePage open() {

        logger.debug("open the URL" + getCurrentUrl());
        if (Configuration.getEnvType().equals(EnvType.DEV)) {

            logger.debug(getCurrentUrl());
            getUrl("http://localhost:9000");
            // getUrl(getCurrentUrl());
        } else {
            logger.debug(getCurrentUrl());
            // getUrl("http://localhost:9000");
            // getUrl(getCurrentUrl() + " ");
            getUrl(getCurrentUrl());
        }
        //waitForPageLoad();


        return this;
    }




    public AddServerDetails addConnection(String method) {


        try {




            if (ElementStateHelper.isElementVisible(addButton)) {
                logger.warn("Entering  addConnection: if " +method);

                waitAndClick(addButton);


            } else {
                logger.warn("Entering  addConnection: else " +method);


               // wait.forElementVisible(addButtonTop,15);

                logger.warn("Entering  addConnection: 2 " );

                Thread.sleep(10000);

                if (ElementStateHelper.isElementVisible(addButtonTop)) {

                    logger.warn("Entering  addConnection: 3 " );

                    logger.warn("Entering  addConnection: else->if");


                    waitAndClick(addButtonTop);

                }
            }


            logger.debug("Exiting  addConnection");

        } catch (Exception e) {
            e.printStackTrace();

        }
        return new AddServerDetails();

    }


    public void addConnection_accessdenied() {

        logger.debug("Entering  addConnection_accessdenied: ");
        Reporter.log("Entering  addConnection_accessdenied:");
        try {
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }


        logger.debug("accessdenied " + msg.getText());


        logger.debug("Exiting  addConnection_accessdenied");
        Reporter.log("Exiting  addConnection_accessdenied");


    }


}