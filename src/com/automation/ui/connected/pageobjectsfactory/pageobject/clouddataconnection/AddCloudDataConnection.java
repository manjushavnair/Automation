package com.automation.ui.connected.pageobjectsfactory.pageobject.clouddataconnection;

import com.automation.ui.connected.pageobjectsfactory.pageobject.SiteBasePageObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.Reporter;

public class AddCloudDataConnection extends SiteBasePageObject {


    private static Logger logger = Logger.getLogger(AddCloudDataConnection.class);


    @FindBy(xpath = AddCloudDataCONSTANTS.ADDCONNECTIONSBUTTON)
    private WebElement addButton;

    public AddCloudDataConnection open( )
            {
        getUrl(  );
      //  waitForPageLoad();

        try {
            Thread.sleep(2000);
        } catch (Exception e) {

        }

        //driver.navigate().refresh();

        return new AddCloudDataConnection();
    }

    public AddCloudDataConnection addConnection( ) {
        try {
            logger.info("Entering  addConnection: ");
            Reporter.log("Entering  addConnection:");
            logger.info("click  ");


            addButton.click();

            logger.info("clicked ");

            logger.info("Exiting  addConnection");
            Reporter.log("Exiting  addConnection");

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Add failed");
            Reporter.log("Add failed");

        }
        return this;

    }



}
