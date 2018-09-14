package com.automation.ui.githubtesting.pageobjectfactory.pageobject.home;

import com.automation.ui.base.common.core.configuration.Configuration;
import com.automation.ui.base.common.core.configuration.EnvType;
import com.automation.ui.githubtesting.common.prpreader.AssertDataReader;
import com.automation.ui.githubtesting.pageobjectfactory.pageobject.base.SiteBasePageObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.Reporter;

public class HomePage extends SiteBasePageObject {


    private static Logger logger = Logger.getLogger(HomePage.class);

    @FindBy(css = "primary")
    private WebElement primarysButton;





    //for GITHUB testing
    @FindBy(css = HomeConstants.NEWREPOCREATION)
    private WebElement addRepo;


    public HomePage() {
        super();
    }


    public void home() throws InterruptedException {

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
            //  getUrl(getCurrentUrl() + " ");
            getUrl(getCurrentUrl());
        }
        //waitForPageLoad();


        return this;
    }




    public void addRepo() {

        wait.forElementClickable(addRepo);
        addRepo.click();
    }


    public void addConnection_accessdenied() {

        logger.info("Entering  addConnection_accessdenied: ");
        Reporter.log("Entering  addConnection_accessdenied:");

        logger.info("Exiting  addConnection_accessdenied");
        Reporter.log("Exiting  addConnection_accessdenied");


    }

}