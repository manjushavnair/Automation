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


    @FindBy(xpath =AddCloudDataCONSTANTS.ADDCONNECTIONSCANELBUTTON)
    private WebElement cancelButton;



    public AddCloudDataConnection open( )
            {
      // getUrl(  );
      //  waitForPageLoad();



        //driver.navigate().refresh();

        return new AddCloudDataConnection();
    }


    public void addConnecetion_Cancel()
    {


            logger.info("Entering  addConnecetion_Cancel:" );
            Reporter.log("Entering  addConnecetion_Cancel:" );

            jsActions.click(cancelButton);
            waitAndClick(cancelButton);
            logger.info("Exiting  addConnecetion_Cancel");
            Reporter.log("Exiting  addConnecetion_Cancel");



    }




}
