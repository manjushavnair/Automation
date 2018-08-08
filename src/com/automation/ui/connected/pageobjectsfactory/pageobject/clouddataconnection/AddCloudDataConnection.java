package com.automation.ui.connected.pageobjectsfactory.pageobject.clouddataconnection;

import com.automation.ui.base.common.constants.BASEConstants;
import com.automation.ui.connected.pageobjectsfactory.pageobject.base.*;
import com.automation.ui.connected.pageobjectsfactory.pageobject.home.HomeConstants;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.Reporter;

public class AddCloudDataConnection extends SiteBasePageObject {


    private static Logger logger = Logger.getLogger(AddCloudDataConnection.class);



    // @FindBy(css = HomeConstants.ADDCONNECTIONSBUTTON)
    @FindBy(xpath = AddCloudDataCONSTANTS.ADDCONNECTIONSBUTTON)
    //@FindBy(className  = HomeConstants.ADDCONNECTIONSBUTTON)
    private WebElement addButton;

    public AddCloudDataConnection open( )
            {
        getUrl(  );
      //  waitForPageLoad();



        //driver.navigate().refresh();

        return new AddCloudDataConnection();
    }

    public AddCloudDataConnection addConnection() {



        try {
            logger.info("Entering  addConnection: "+addButton.getTagName() + " " +addButton.getText()+ " "+addButton.getLocation());

            Reporter.log("Entering  addConnection:"+addButton.getTagName() + " " +addButton.getText()+ " "+addButton.getLocation());
            logger.info("click  ");
            // wait.forElementVisible(addButton, 30);

            jsActions.click(addButton);
            waitAndClick(addButton);
            Thread.sleep(20000);


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
