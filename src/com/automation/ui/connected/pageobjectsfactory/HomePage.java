package com.automation.ui.connected.pageobjectsfactory;

import com.automation.ui.base.common.core.configuration.Configuration;
import com.automation.ui.base.common.core.configuration.EnvType;
import com.automation.ui.connected.pageobjectsfactory.pageobject.SiteBasePageObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

public class HomePage extends SiteBasePageObject {


    private static Logger logger = Logger.getLogger(HomePage.class);

    // @FindBy(xpath = "//a[contains(@href,'/home')and contains(.,'Customer Experience')]")
    //@FindBy(xpath = "//a[@class='active']")
    private WebElement homepage;

    public HomePage() {
        super();
    }


    public void home() throws InterruptedException {

    }

    public HomePage open() {

        logger.info("open");
        if (Configuration.getEnvType().equals(EnvType.DEV)) {

            logger.info(getCurrentUrl());
             getUrl("http://localhost:9000");
            //  getUrl(getCurrentUrl() + "?action=history");
           // getUrl(getCurrentUrl());
        } else {
            logger.info(getCurrentUrl());
            getUrl("http://localhost:9000");
            //  getUrl(getCurrentUrl() + "?action=history");
           // getUrl(getCurrentUrl());
        }
       // waitForPageLoad();




        return this;
    }


    //Go to LoginPage
    public void goToLoginPage() {


    }

}